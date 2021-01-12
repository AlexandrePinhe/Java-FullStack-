import { Component,ViewChild, AfterViewInit } from '@angular/core';
import { aluno } from 'src/app/models/aluno.model';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { merge, of as ofasobservableOf } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AlunoService } from 'src/app/service/aluno.service';
import { ConfirmDeleteComponent } from 'src/app/views/template/confirm-delete/confirm-delete.component';

@Component({
  selector: 'app-aluno-list',
  templateUrl: './aluno-list.component.html',
  styleUrls: ['./aluno-list.component.css']
})
export class AlunoListComponent implements AfterViewInit {

  displayedColumns: string[] = ['cpf', 'nome', 'endereco', 'acao'];
  alunos: aluno[] = [];

  resultsLength = 0;
  isLoadingResults = false;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort, {static:false}) sort: MatSort;

  constructor(private servico : AlunoService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar,
    private router: Router) { }

  atualizarAluno(): void {
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
          this.servico.showMessage("Houve um erro no carregamento dos Alunos "+err.message, true);
          return ofasobservableOf([]);
        })
      ).subscribe(data => {
        this.alunos = data;
      });
  } 

  ngAfterViewInit(): void {
    this.atualizarAluno();
  }

  excluir(aluno: aluno): void{
    const dialogRef = this.dialog.open(ConfirmDeleteComponent,{
     data: {
       message: `Deseja realmente excluir o Aluno ${aluno.nome}`,
       buttonText: {
         ok: 'Excluir',
         cancel: 'Cancelar'
       }
     }
    });
    dialogRef.afterClosed().subscribe((confirmed : boolean) =>{
     if(confirmed){
       this.servico.delete(aluno).subscribe(() =>{
         this.servico.showMessage("Aluno excluido com sucesso");
         this.atualizarAluno();
       }, err =>{
         this.isLoadingResults = false;
         this.servico.showMessage("Aluno não pode ser excluido "+ err.message, true);
       })
     }
    })
  }
}
