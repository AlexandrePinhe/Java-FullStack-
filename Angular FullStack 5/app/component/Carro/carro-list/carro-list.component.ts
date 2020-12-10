import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Carro } from 'src/app/models/carro.model';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { CarroService } from 'src/app/service/carro.service';
import { ConfirmDeleteComponent } from 'src/app/views/template/confirm-delete/confirm-delete.component';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { merge, of as ofasobservableOf } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-carro-list',
  templateUrl: './carro-list.component.html',
  styleUrls: ['./carro-list.component.css']
})
export class CarroListComponent implements AfterViewInit {

  displayedColumns: string[] = ['ID', 'Nome Fabricante', 'Tipo Carro', 'Placa', 'Ano'
  ,'Cor', 'Nome modelo', 'acao'];
  carros: Carro[] = [];

  resultsLength = 0;
  isLoadingResults = false;
  
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort, {static:false}) sort: MatSort;


  constructor(private servico : CarroService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar) { }

    atualizarCarros(): void {

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
          this.servico.showMessage("Houve um erro no carregamento dos carros "+err.message, true);
          return ofasobservableOf([]);
        })
      ).subscribe(data => {
        this.carros = data;
      });
    }

    ngAfterViewInit(): void {
    this.atualizarCarros();
  }

  excluir(carro: Carro): void{
    const dialogRef = this.dialog.open(ConfirmDeleteComponent,{
     data: {
       message: `Deseja realmente excluir o carro da placa ${carro.placa}`,
       buttonText: {
         ok: 'Excluir',
         cancel: 'Cancelar'
       }
     }
    });
    dialogRef.afterClosed().subscribe((confirmed : boolean) =>{
     if(confirmed){
       this.servico.delete(carro).subscribe(() =>{
         this.servico.showMessage("Carro excluido com sucesso");
         this.atualizarCarros();
       }, err => {
        this.isLoadingResults = false;
        this.servico.showMessage("Carro não pode ser excluido "+ err.message, true);
       })
     }
    })
  }

}
