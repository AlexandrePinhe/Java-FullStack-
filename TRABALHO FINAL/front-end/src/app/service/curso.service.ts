import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { curso } from '../models/curso.model';
import { CursoPage } from '../component/pages/curso-page';

@Injectable({
    providedIn: 'root'
  })
  export class CursoService{

    baseUrl = 'http://localhost:8080/cursos/';

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
    create(curso : curso): Observable<curso>{
        return this.http.post<curso>( this.baseUrl, curso);
      }

    //Read
   read(): Observable<curso[]>{
    return this.http.get<curso[]>(this.baseUrl);
   }

   //ReadTable
   readTable(size: number, page: number): Observable<CursoPage> {
     const url = `${this.baseUrl}/datatable`
     console.log(url);
     return this.http.get<CursoPage>(url, {
       params: new HttpParams()
         .set('page', page.toString())
         .set('size', size.toString())
     });
   }

  //Read By ID
  readById(id: string): Observable<curso>{
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<curso>(url);
  }
 //Uptade
  update(curso: curso): Observable<curso>{
    const url = `${this.baseUrl}/${curso.id}`;
    return this.http.put<curso>(url, curso);
  }
 //Delete
 delete(curso: curso): Observable<curso>{
   const url = `${this.baseUrl}/${curso.id}`;
   return this.http.delete<curso>(url);
 } 
  }