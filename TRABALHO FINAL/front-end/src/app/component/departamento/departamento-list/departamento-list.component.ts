import { ViewChild } from '@angular/core';
import { Component, AfterViewInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { departamento } from 'src/app/models/departamento.model';
import { DepartamentoService } from 'src/app/service/departamento.service';
import { merge, of as ofasobservableOf } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';
import { ConfirmDeleteComponent } from 'src/app/views/template/confirm-delete/confirm-delete.component';

@Component({
  selector: 'app-departamento-list',
  templateUrl: './departamento-list.component.html',
  styleUrls: ['./departamento-list.component.css']
})
export class DepartamentoListComponent implements AfterViewInit {

  displayedColumns: string[] = ['id', 'nome', 'data_contratacao', 'matricula_prof',
   'cod_curso', 'tipo_departamento', 'departamento_ativo', 'acao'];
   departamentos: departamento[] = [];

  resultsLength = 0;
  isLoadingResults = false;
  
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort, {static:false}) sort: MatSort;

  constructor(private servico : DepartamentoService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar) { }

    
    atualizarDepartamento(): void {

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
          this.servico.showMessage("Houve um erro no carregamento dos Departamentos "+err.message, true);
          return ofasobservableOf([]);
        })
      ).subscribe(data => {
        this.departamentos = data;
      });
    }
  ngAfterViewInit(): void {
    this.atualizarDepartamento();
  }

  excluir(departamento: departamento): void{
    const dialogRef = this.dialog.open(ConfirmDeleteComponent,{
     data: {
       message: `Deseja realmente excluir o Departamento ${departamento.nome}`,
       buttonText: {
         ok: 'Excluir',
         cancel: 'Cancelar'
       }
     }
    });
    dialogRef.afterClosed().subscribe((confirmed : boolean) =>{
     if(confirmed){
       this.servico.delete(departamento).subscribe(() =>{
         this.servico.showMessage("Departamento excluido com sucesso");
         this.atualizarDepartamento();
       }, err => {
        this.isLoadingResults = false;
        this.servico.showMessage("Departamento não pode ser excluido "+ err.message, true);
       })
     }
    })
  }
}
