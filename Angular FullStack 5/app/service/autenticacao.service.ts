import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { usuario } from '../models/usuario.model';

@Injectable({
  providedIn: 'root'
})
export class AutenticacaoService {

  baseUrl = "http://localhost:8080/autenticacao";
  constructor(
    private snackBar: MatSnackBar,
    private http: HttpClient
  ) { }

  showMessage(msg: string, isError: boolean = false): void{
    this.snackBar.open(msg, 'Fechar', {
      verticalPosition: "top",
      horizontalPosition: "right",
      duration: 3000,
      panelClass: isError ? ['msg-error']: ['msg-sucess']
    });
  }

  login(usuario : usuario): Observable<usuario>{
    return this.http.post<usuario>( this.baseUrl, usuario);
  }
}
