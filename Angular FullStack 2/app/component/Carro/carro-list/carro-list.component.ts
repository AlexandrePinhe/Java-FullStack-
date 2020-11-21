import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Carro } from 'src/app/models/carro.model';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { CarroService } from 'src/app/service/carro.service';
import { ConfirmDeleteComponent } from 'src/app/views/template/confirm-delete/confirm-delete.component';

@Component({
  selector: 'app-carro-list',
  templateUrl: './carro-list.component.html',
  styleUrls: ['./carro-list.component.css']
})
export class CarroListComponent implements OnInit {
  carros = [];
  displayedColumns: string[] = ['ID', 'Nome Fabricante', 'Tipo Carro', 'Placa', 'Ano'
  ,'Cor', 'Nome modelo', 'acao'];

  constructor(private servico : CarroService,
    public dialog: MatDialog,
    public snackBar: MatSnackBar) { }

    atualizarCarros(): void {
      this.servico.read().subscribe(carros => {
          this.carros = carros;
          console.log(this.carros) })
    }

  ngOnInit(): void {
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
       })
     }
    })
  }

}
