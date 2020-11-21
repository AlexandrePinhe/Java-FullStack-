import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { Carro } from '../models/carro.model';


@Injectable({
  providedIn: 'root'
})
export class CarroService {

  baseUrl = 'http://localhost:8080/carros/';

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
  create(carro : Carro): Observable<Carro>{
    return this.http.post<Carro>( this.baseUrl, carro);
  }
  //Read
   read(): Observable<Carro[]>{
     return this.http.get<Carro[]>(this.baseUrl);
   }
   //Read By ID
   readById(id: string): Observable<Carro>{
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<Carro>(url);
  }
  //Uptade
  update(carro: Carro): Observable<Carro>{
    const url = `${this.baseUrl}/${carro.idCarro}`;
    return this.http.put<Carro>(url, carro);
  }
  //Delete
  delete(carro: Carro): Observable<Carro>{
    const url = `${this.baseUrl}/${carro.idCarro}`;
    return this.http.delete<Carro>(url);
  }
}
