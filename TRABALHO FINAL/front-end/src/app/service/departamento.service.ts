import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { departamento } from '../models/departamento.model';
import { DepartamentoPage } from '../component/pages/departamento-page';

@Injectable({
    providedIn: 'root'
  })
  export class DepartamentoService{

    baseUrl = 'http://localhost:8080/departamentos/';

    constructor(private snackBar: MatSnackBar, private http: HttpClient) { }

    showMessage(msg: string, isError: boolean = false): void{
        this.snackBar.open(msg, 'Fechar', {
          verticalPosition: "top",
          horizontalPosition: "right",
          duration: 3000,
          panelClass: isError ? ['msg-error']: ['msg-sucess']
        });
    }

    //create
    create(departamento : departamento): Observable<departamento>{
        return this.http.post<departamento>( this.baseUrl, departamento);
      }

    //Read
   read(): Observable<departamento[]>{
    return this.http.get<departamento[]>(this.baseUrl);
   }

   //ReadTable
   readTable(size: number, page: number): Observable<DepartamentoPage> {
     const url = `${this.baseUrl}/datatable`
     console.log(url);
     return this.http.get<DepartamentoPage>(url, {
       params: new HttpParams()
         .set('page', page.toString())
         .set('size', size.toString())
     });
   }

  //Read By ID
  readById(id: string): Observable<departamento>{
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<departamento>(url);
  }
 //Uptade
  update(departamento: departamento): Observable<departamento>{
    const url = `${this.baseUrl}/${departamento.id}`;
    return this.http.put<departamento>(url, departamento);
  }
 //Delete
 delete(departamento: departamento): Observable<departamento>{
   const url = `${this.baseUrl}/${departamento.id}`;
   return this.http.delete<departamento>(url);
 } 
  }