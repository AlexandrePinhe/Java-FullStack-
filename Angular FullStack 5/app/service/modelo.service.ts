import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { ModeloPage } from '../component/Modelo/modelo-page';
import { modelo } from '../models/modelo.model';


@Injectable({
  providedIn: 'root'
})
export class ModeloService {

  baseUrl = 'http://localhost:8080/modelos/';

  constructor(private snackBar: MatSnackBar, private http: HttpClient) { }

  showMessage(msg: string, isError: boolean = false): void{
    this.snackBar.open(msg, 'Fechar', {
      verticalPosition: "top",
      horizontalPosition: "right",
      duration: 3000,
      panelClass: isError ? ['msg-error']: ['msg-sucess']
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

   //ReadTable
   readTable(size: number, page: number): Observable<ModeloPage> {
    const url = `${this.baseUrl}/datatable`
    console.log(url);
    return this.http.get<ModeloPage>(url, {
      params: new HttpParams()
        .set('page', page.toString())
        .set('size', size.toString())
    });
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