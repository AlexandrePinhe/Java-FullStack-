
import { ProfessorService } from 'src/app/service/professor.service';
import { Component,ViewChild, AfterViewInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { merge, of as ofasobservableOf } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ConfirmDeleteComponent } from 'src/app/views/template/confirm-delete/confirm-delete.component';
import { professor } from 'src/app/models/professor.model';

@Component({
  selector: 'app-professor-list',
  templateUrl: './professor-list.component.html',
  styleUrls: ['./professor-list.component.css']
})
export class ProfessorListComponent implements AfterViewInit {

  displayedColumns: string[] = ['matricula', 'nome', 'acao'];
  professores: professor[] = [];

  resultsLength = 0;
  isLoadingResults = false;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort, {static:false}) sort: MatSort;

  constructor(private servico : ProfessorService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar,
    private router: Router) { }

  atualizarProfessor(): void {
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
          this.servico.showMessage("Houve um erro no carregamento dos Professores "+err.message, true);
          return ofasobservableOf([]);
        })
      ).subscribe(data => {
        this.professores = data;
      });
  } 

  ngAfterViewInit(): void {
    this.atualizarProfessor();
  }

  excluir(professor: professor): void{
    const dialogRef = this.dialog.open(ConfirmDeleteComponent,{
     data: {
       message: `Deseja realmente excluir o Professor ${professor.nome}`,
       buttonText: {
         ok: 'Excluir',
         cancel: 'Cancelar'
       }
     }
    });
    dialogRef.afterClosed().subscribe((confirmed : boolean) =>{
     if(confirmed){
       this.servico.delete(professor).subscribe(() =>{
         this.servico.showMessage("Professor excluido com sucesso");
         this.atualizarProfessor();
       }, err =>{
         this.isLoadingResults = false;
         this.servico.showMessage("Professor não pode ser excluido "+ err.message, true);
       })
     }
    })
  }

}
