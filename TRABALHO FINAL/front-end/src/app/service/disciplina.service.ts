import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { disciplina } from '../models/disciplina.model';
import { DisciplinaPage } from '../component/pages/disciplina-page';

@Injectable({
    providedIn: 'root'
  })
  export class DisciplinaService{

    baseUrl = 'http://localhost:8080/disciplinas/';

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
    create(disciplina : disciplina): Observable<disciplina>{
        return this.http.post<disciplina>( this.baseUrl, disciplina);
      }

    //Read
   read(): Observable<disciplina[]>{
    return this.http.get<disciplina[]>(this.baseUrl);
   }

   //ReadTable
   readTable(size: number, page: number): Observable<DisciplinaPage> {
     const url = `${this.baseUrl}/datatable`
     console.log(url);
     return this.http.get<DisciplinaPage>(url, {
       params: new HttpParams()
         .set('page', page.toString())
         .set('size', size.toString())
     });
   }

  //Read By ID
  readById(id: string): Observable<disciplina>{
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<disciplina>(url);
  }
 //Uptade
  update(disciplina: disciplina): Observable<disciplina>{
    const url = `${this.baseUrl}/${disciplina.id}`;
    return this.http.put<disciplina>(url, disciplina);
  }
 //Delete
 delete(disciplina: disciplina): Observable<disciplina>{
   const url = `${this.baseUrl}/${disciplina.id}`;
   return this.http.delete<disciplina>(url);
 }  
  }