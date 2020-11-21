import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { fabricante } from '../models/fabricante.model';

@Injectable({
  providedIn: 'root'
})
export class FabricanteService {

  baseUrl = 'http://localhost:8080/fabricantes/';

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
  create(fabricante : fabricante): Observable<fabricante>{
    return this.http.post<fabricante>( this.baseUrl, fabricante);
  } 
  //Read
   read(): Observable<fabricante[]>{
     return this.http.get<fabricante[]>(this.baseUrl);
   }

   //Read By ID
   readById(id: string): Observable<fabricante>{
     const url = `${this.baseUrl}/${id}`;
     return this.http.get<fabricante>(url);
   }
  //Uptade
   update(fabricante: fabricante): Observable<fabricante>{
     const url = `${this.baseUrl}/${fabricante.fabricanteId}`;
     return this.http.put<fabricante>(url, fabricante);
   }
  //Delete
  delete(fabricante: fabricante): Observable<fabricante>{
    const url = `${this.baseUrl}/${fabricante.fabricanteId}`;
    return this.http.delete<fabricante>(url);
  }
}
