import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ModeloService } from 'src/app/service/modelo.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { modelo } from 'src/app/models/modelo.model';
import { ConfirmDeleteComponent } from 'src/app/views/template/confirm-delete/confirm-delete.component';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { merge, of as ofasobservableOf } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-modelo-list',
  templateUrl: './modelo-list.component.html',
  styleUrls: ['./modelo-list.component.css']
})
export class ModeloListComponent implements AfterViewInit {
  displayedColumns: string[] = ['ID', 'Nome Modelo','Id Fabricante','Nome Fabricante', 'acao'];

  modelos: modelo[] = [];

  resultsLength = 0;
  isLoadingResults = false;
  
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort, {static:false}) sort: MatSort;
  
  constructor(private servico : ModeloService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar,
    private router: Router) { }

  atualizarModelos(): void {
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
          this.servico.showMessage("Houve um erro no carregamento dos Modelos "+err.message, true);
          return ofasobservableOf([]);
        })
      ).subscribe(data => {
        this.modelos = data;
      });
  }  

  ngAfterViewInit(): void {
    this.atualizarModelos();
  }
   
  excluir(modelo: modelo): void{
    const dialogRef = this.dialog.open(ConfirmDeleteComponent,{
     data: {
       message: `Deseja realmente excluir o modelo ${modelo.nomeModelo}`,
       buttonText: {
         ok: 'Excluir',
         cancel: 'Cancelar'
       }
     }
    });
    dialogRef.afterClosed().subscribe((confirmed : boolean) =>{
     if(confirmed){
       this.servico.delete(modelo).subscribe(() =>{
         this.servico.showMessage("modelo excluido com sucesso");
         this.atualizarModelos();
       }, err => {
        this.isLoadingResults = false;
        this.servico.showMessage("Modelo não pode ser excluido "+ err.message, true);
       })
      }
    })
  }
  
}
