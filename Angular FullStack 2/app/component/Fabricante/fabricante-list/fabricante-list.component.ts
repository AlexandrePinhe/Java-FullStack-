import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { fabricante } from 'src/app/models/fabricante.model';
import { FabricanteService } from 'src/app/service/fabricante.service';
import { ConfirmDeleteComponent } from 'src/app/views/template/confirm-delete/confirm-delete.component';

@Component({
  selector: 'app-fabricante-list',
  templateUrl: './fabricante-list.component.html',
  styleUrls: ['./fabricante-list.component.css']
})
export class FabricanteListComponent implements OnInit {
  fabricantes = [];
  displayedColumns: string[] = ['ID', 'Nome Fabricante', 'acao'];
  constructor(private servico : FabricanteService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar,
    private router: Router) { }
  
  atualizarFabricante(): void {
    this.servico.read().subscribe(fabricantes => {
      this.fabricantes = fabricantes;
      console.log(this.fabricantes) })
  }  
  ngOnInit(): void {
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
       })
     }
    })
  }
}
