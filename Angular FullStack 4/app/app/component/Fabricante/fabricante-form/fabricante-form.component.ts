import { Component, OnInit } from '@angular/core';
import { Router, Routes } from '@angular/router';
import { fabricante } from 'src/app/models/fabricante.model';
import { FabricanteService } from 'src/app/service/fabricante.service';

@Component({
  selector: 'app-fabricante-form',
  templateUrl: './fabricante-form.component.html',
  styleUrls: ['./fabricante-form.component.css']
})
export class FabricanteFormComponent implements OnInit {

  tituloPagina = "Cadastrar Fabricante";
  fabricante: fabricante = {
    fabricanteNome : ""
  }
  constructor(private servico: FabricanteService,
    private router: Router) { }

  ngOnInit(): void {
  }
 
  salvar(): void{
    this.servico.create(this.fabricante).subscribe( () => {
     this.servico.showMessage("Fabricante salvo com sucesso");
     this.router.navigate(['/fabricantes']);
    }, err => {this.servico.showMessage("Erro Fabricante não cadastrado!"+err.message, true)})
  }
}
