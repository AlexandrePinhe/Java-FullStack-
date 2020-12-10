import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { UsuarioPage } from '../component/usuario/usuario-table/usuario-pages';
import { usuario } from '../models/usuario.model';


@Injectable({
    providedIn: 'root'
  })
  export class UsuarioService{
    baseUrl = 'http://localhost:8080/usuarios/';

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
  create(usuario : usuario): Observable<usuario>{
    return this.http.post<usuario>( this.baseUrl, usuario);
  }

  //Read
  read(): Observable<usuario[]>{
    return this.http.get<usuario[]>(this.baseUrl);
  }

  //ReadTable
  readTable(size: number, page: number): Observable<UsuarioPage> {
    const url = `${this.baseUrl}/datatable`
    console.log(url);
    return this.http.get<UsuarioPage>(url, {
      params: new HttpParams()
        .set('page', page.toString())
        .set('size', size.toString())
    });
  }
  //Read By ID
  readById(id: string): Observable<usuario>{
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<usuario>(url);
  }
 //Uptade
  update(usuario: usuario): Observable<usuario>{
    const url = `${this.baseUrl}/${usuario.idUsuario}`;
    return this.http.put<usuario>(url, usuario);
  }
 //Delete
 delete(usuario: usuario): Observable<usuario>{
   const url = `${this.baseUrl}/${usuario.idUsuario}`;
   return this.http.delete<usuario>(url);
 }

  }