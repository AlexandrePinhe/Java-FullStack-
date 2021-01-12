import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { aluno } from '../models/aluno.model';
import { AlunoPage } from '../component/pages/aluno-page';

@Injectable({
    providedIn: 'root'
  })
  export class AlunoService{

    baseUrl = 'http://localhost:8080/alunos/';

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
    create(aluno : aluno): Observable<aluno>{
        return this.http.post<aluno>( this.baseUrl, aluno);
      }

    //Read
   read(): Observable<aluno[]>{
    return this.http.get<aluno[]>(this.baseUrl);
   }

   //ReadTable
   readTable(size: number, page: number): Observable<AlunoPage> {
     const url = `${this.baseUrl}/datatable`
     console.log(url);
     return this.http.get<AlunoPage>(url, {
       params: new HttpParams()
         .set('page', page.toString())
         .set('size', size.toString())
     });
   }

  //Read By ID
  readById(id: string): Observable<aluno>{
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<aluno>(url);
  }
 //Uptade
  update(aluno: aluno): Observable<aluno>{
    const url = `${this.baseUrl}/${aluno.id}`;
    return this.http.put<aluno>(url, aluno);
  }
 //Delete
 delete(aluno: aluno): Observable<aluno>{
   const url = `${this.baseUrl}/${aluno.id}`;
   return this.http.delete<aluno>(url);
 }  
  }