import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { modelo } from '../models/modelo.model';


@Injectable({
  providedIn: 'root'
})
export class ModeloService {

  baseUrl = 'http://localhost:8080/modelos/';

  constructor(private snackBar: MatSnackBar, private http: HttpClient) { }

  showMessage(msg: string): void{
    this.snackBar.open(msg, 'Fechar', {
      verticalPosition: "top",
      horizontalPosition: "right",
      duration: 3000
    });
  }

  //CRUD

  //Create
  create(modelo : modelo): Observable<modelo>{
    return this.http.post<modelo>( this.baseUrl, modelo);
  }
  //Read
   read(): Observable<modelo[]>{
     return this.http.get<modelo[]>(this.baseUrl);
   }
//Read By ID
   readById(id: string): Observable<modelo>{
     const url = `${this.baseUrl}/${id}`;
     return this.http.get<modelo>(url);
  }

  //Uptade
  update(modelo: modelo): Observable<modelo>{
    const url = `${this.baseUrl}/${modelo.modeloId}`;
    return this.http.put<modelo>(url, modelo);
  }
  //delete
  delete(modelo: modelo): Observable<modelo>{
    const url = `${this.baseUrl}/${modelo.modeloId}`;
    return this.http.delete<modelo>(url);
  }
}