import { Component,ViewChild, AfterViewInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { merge, of as ofasobservableOf } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ConfirmDeleteComponent } from 'src/app/views/template/confirm-delete/confirm-delete.component';
import { disciplina } from 'src/app/models/disciplina.model';
import { DisciplinaService } from 'src/app/service/disciplina.service';

@Component({
  selector: 'app-disciplina-list',
  templateUrl: './disciplina-list.component.html',
  styleUrls: ['./disciplina-list.component.css']
})
export class DisciplinaListComponent implements AfterViewInit {

  displayedColumns: string[] = ['id', 'nome', 'qtde_Alunos', 'acao'];
  disciplinas: disciplina[] = [];

  resultsLength = 0;
  isLoadingResults = false;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort, {static:false}) sort: MatSort;

  constructor(private servico : DisciplinaService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar,
    private router: Router) { }

    atualizarDisciplina(): void {
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
            this.servico.showMessage("Houve um erro no carregamento das Disciplinas "+err.message, true);
            return ofasobservableOf([]);
          })
        ).subscribe(data => {
          this.disciplinas = data;
        });
    } 
  
  ngAfterViewInit(): void {
    this.atualizarDisciplina();
  }

  excluir(disciplina: disciplina): void{
    const dialogRef = this.dialog.open(ConfirmDeleteComponent,{
     data: {
       message: `Deseja realmente excluir a Disciplina ${disciplina.nome}`,
       buttonText: {
         ok: 'Excluir',
         cancel: 'Cancelar'
       }
     }
    });
    dialogRef.afterClosed().subscribe((confirmed : boolean) =>{
     if(confirmed){
       this.servico.delete(disciplina).subscribe(() =>{
         this.servico.showMessage("Disciplina excluida com sucesso");
         this.atualizarDisciplina();
       }, err =>{
         this.isLoadingResults = false;
         this.servico.showMessage("Disciplina não pode ser excluida "+ err.message, true);
       })
     }
    })
  }
}
