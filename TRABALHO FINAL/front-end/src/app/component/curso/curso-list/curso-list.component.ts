import { ViewChild } from '@angular/core';
import { Component, AfterViewInit } from '@angular/core';
import { curso } from 'src/app/models/curso.model';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { merge, of as ofasobservableOf } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CursoService } from 'src/app/service/curso.service';
import { ConfirmDeleteComponent } from 'src/app/views/template/confirm-delete/confirm-delete.component';

@Component({
  selector: 'app-curso-list',
  templateUrl: './curso-list.component.html',
  styleUrls: ['./curso-list.component.css']
})
export class CursoListComponent implements AfterViewInit {

  displayedColumns: string[] = ['id', 'nome', 'cod_disciplina', 'data_matricula',
   'id_aluno', 'acao'];
  cursos: curso[] = [];

  resultsLength = 0;
  isLoadingResults = false;
  
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort, {static:false}) sort: MatSort;
  
  constructor(private servico : CursoService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar) { }

    atualizarCurso(): void {

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
          this.servico.showMessage("Houve um erro no carregamento dos cursos "+err.message, true);
          return ofasobservableOf([]);
        })
      ).subscribe(data => {
        this.cursos = data;
      });
    }

    ngAfterViewInit(): void {
      this.atualizarCurso();
  }

  excluir(curso: curso): void{
    const dialogRef = this.dialog.open(ConfirmDeleteComponent,{
     data: {
       message: `Deseja realmente excluir o Curso ${curso.nome}`,
       buttonText: {
         ok: 'Excluir',
         cancel: 'Cancelar'
       }
     }
    });
    dialogRef.afterClosed().subscribe((confirmed : boolean) =>{
     if(confirmed){
       this.servico.delete(curso).subscribe(() =>{
         this.servico.showMessage("Curso excluido com sucesso");
         this.atualizarCurso();
       }, err => {
        this.isLoadingResults = false;
        this.servico.showMessage("Curso não pode ser excluido "+ err.message, true);
       })
     }
    })
  }
}
