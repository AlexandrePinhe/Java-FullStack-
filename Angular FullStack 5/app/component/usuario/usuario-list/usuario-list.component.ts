import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { usuario } from 'src/app/models/usuario.model';
import { UsuarioService } from 'src/app/service/usuario.service';
import { merge, of as ofasobservableOf } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';
import { ConfirmDeleteComponent } from 'src/app/views/template/confirm-delete/confirm-delete.component';

@Component({
  selector: 'app-usuario-list',
  templateUrl: './usuario-list.component.html',
  styleUrls: ['./usuario-list.component.css']
})
export class UsuarioListComponent implements AfterViewInit {
  displayedColumns: string[] = ['ID', 'nome', 'login','data nascimento', 'nivel', 'acao'];
  usuarios: usuario[] = [];

  resultsLength = 0;
  isLoadingResults = false;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort, {static:false}) sort: MatSort;
  constructor(private servico : UsuarioService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar,
    private router: Router) { }

    atualizarUsuario(): void {
      this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);
   
      merge(this.sort.sortChange, this.paginator.page)
        .pipe(
          startWith({}),
          switchMap(() => { 
            this.isLoadingResults = true;
            return this.servico!.readTable(this.paginator.pageSize, this.paginator.pageIndex);
          }),
          map(page => {
            this.isLoadingResults = false;
            this.resultsLength = page.totalElements;
            return page.content;
          }),
          catchError(err => {
            this.isLoadingResults = false;
            this.servico.showMessage("Houve um erro no carregamento dos usuários "+err.message, true);
            return ofasobservableOf([]);
          })
        ).subscribe(data => {
          this.usuarios = data;
        });
    }  

    ngAfterViewInit(): void {
      this.atualizarUsuario();
    }

    excluir(usuario: usuario): void{
      const dialogRef = this.dialog.open(ConfirmDeleteComponent,{
       data: {
         message: `Deseja realmente excluir o Usuário ${usuario.nomeUsuario}`,
         buttonText: {
           ok: 'Excluir',
           cancel: 'Cancelar'
         }
       }
      });
      dialogRef.afterClosed().subscribe((confirmed : boolean) =>{
       if(confirmed){
         this.servico.delete(usuario).subscribe(() =>{
           this.servico.showMessage("Usuário excluido com sucesso");
           this.atualizarUsuario();
         }, err =>{
           this.isLoadingResults = false;
           this.servico.showMessage("Usuário não pode ser excluido "+ err.message, true);
         })
       }
      })
    }
}
