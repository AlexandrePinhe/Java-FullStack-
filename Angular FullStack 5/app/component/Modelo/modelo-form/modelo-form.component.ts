import { Component, OnInit } from '@angular/core';
import { Router, Routes } from '@angular/router';
import { ModeloService } from 'src/app/service/modelo.service';
import { modelo } from 'src/app/models/modelo.model';
import { fabricante } from 'src/app/models/fabricante.model';
import { FabricanteService } from 'src/app/service/fabricante.service';


@Component({
  selector: 'app-modelo-form',
  templateUrl: './modelo-form.component.html',
  styleUrls: ['./modelo-form.component.css']
})
export class ModeloFormComponent implements OnInit {

  tituloPaginaModelo = "Cadastrar Modelo";

   modelo: modelo = {
     nomeModelo : "",
      fabricante: null
  }

  fabricantes: fabricante[];

  constructor(private servico: ModeloService,
    private router: Router,
    private fabricanteServico: FabricanteService) { }

  ngOnInit(): void {
    this.fabricanteServico.read().subscribe(fabricantes =>{
      this.fabricantes = fabricantes
    })
  }

   salvar(): void{
     this.servico.create(this.modelo).subscribe( () => {
     this.servico.showMessage("Modelo salvo com sucesso");
     this.router.navigate(['/modelos']);
   }, err => {this.servico.showMessage("Erro Modelo não cadastrado!"+err.message, true)})
  }

}
