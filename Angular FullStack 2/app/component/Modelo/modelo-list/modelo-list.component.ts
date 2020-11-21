import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ModeloService } from 'src/app/service/modelo.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { modelo } from 'src/app/models/modelo.model';
import { ConfirmDeleteComponent } from 'src/app/views/template/confirm-delete/confirm-delete.component';

@Component({
  selector: 'app-modelo-list',
  templateUrl: './modelo-list.component.html',
  styleUrls: ['./modelo-list.component.css']
})
export class ModeloListComponent implements OnInit {

  modelos = [];
  
  displayedColumns: string[] = ['ID', 'Nome Modelo','Id Fabricante','Nome Fabricante', 'acao'];
  constructor(private servico : ModeloService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar,
    private router: Router) { }

  atualizarModelos(): void {
    this.servico.read().subscribe(modelos => {
        this.modelos = modelos;
        console.log(this.modelos) })
  }  

  ngOnInit(): void {
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
       })
     }
    })
  }
  
}
