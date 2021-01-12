import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { professor } from '../models/professor.model';
import { ProfessorPage } from '../component/pages/professor-page';

@Injectable({
    providedIn: 'root'
  })
  export class ProfessorService{

    baseUrl = 'http://localhost:8080/professores/';

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
    create(professor : professor): Observable<professor>{
        return this.http.post<professor>( this.baseUrl, professor);
      }

    //Read
   read(): Observable<professor[]>{
    return this.http.get<professor[]>(this.baseUrl);
   }

   //ReadTable
   readTable(size: number, page: number): Observable<ProfessorPage> {
     const url = `${this.baseUrl}/datatable`
     console.log(url);
     return this.http.get<ProfessorPage>(url, {
       params: new HttpParams()
         .set('page', page.toString())
         .set('size', size.toString())
     });
   }

  //Read By ID
  readById(id: string): Observable<professor>{
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<professor>(url);
  }
 //Uptade
  update(professor: professor): Observable<professor>{
    const url = `${this.baseUrl}/${professor.matricula}`;
    return this.http.put<professor>(url, professor);
  }
 //Delete
 delete(professor: professor): Observable<professor>{
   const url = `${this.baseUrl}/${professor.matricula}`;
   return this.http.delete<professor>(url);
 }  
  }