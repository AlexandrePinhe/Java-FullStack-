import { Component, ViewChild, AfterViewInit  } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { fabricante } from 'src/app/models/fabricante.model';
import { FabricanteService } from 'src/app/service/fabricante.service';
import { ConfirmDeleteComponent } from 'src/app/views/template/confirm-delete/confirm-delete.component';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { merge, of as ofasobservableOf } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-fabricante-list',
  templateUrl: './fabricante-list.component.html',
  styleUrls: ['./fabricante-list.component.css']
})
export class FabricanteListComponent implements AfterViewInit  {
  displayedColumns: string[] = ['ID', 'Nome Fabricante', 'acao'];
  fabricantes: fabricante[] = [];

  resultsLength = 0;
  isLoadingResults = false;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort, {static:false}) sort: MatSort;

  constructor(private servico : FabricanteService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar,
    private router: Router) { }
  
  atualizarFabricante(): void {

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
          this.servico.showMessage("Houve um erro no carregamento dos Fabricante "+err.message, true);
          return ofasobservableOf([]);
        })
      ).subscribe(data => {
        this.fabricantes = data;
      });
  }  
  
  ngAfterViewInit(): void {
    this.atualizarFabricante();
  }

  excluir(fabricante: fabricante): void{
    const dialogRef = this.dialog.open(ConfirmDeleteComponent,{
     data: {
       message: `Deseja realmente excluir o Fabricante ${fabricante.fabricanteNome}`,
       buttonText: {
         ok: 'Excluir',
         cancel: 'Cancelar'
       }
     }
    });
    dialogRef.afterClosed().subscribe((confirmed : boolean) =>{
     if(confirmed){
       this.servico.delete(fabricante).subscribe(() =>{
         this.servico.showMessage("Fabricante excluido com sucesso");
         this.atualizarFabricante();
       }, err =>{
         this.isLoadingResults = false;
         this.servico.showMessage("Fabricante não pode ser excluido "+ err.message, true);
       })
     }
    })
  }
}
