import { Component, OnInit } from '@angular/core';
import { Router, Routes } from '@angular/router';
import { ModeloService } from 'src/app/service/modelo.service';
import { modelo } from 'src/app/models/modelo.model';
import { FabricanteService } from 'src/app/service/fabricante.service';
import { Carro } from 'src/app/models/carro.model';
import { CarroService } from 'src/app/service/carro.service';
import { fabricante } from 'src/app/models/fabricante.model';

@Component({
  selector: 'app-carro-form',
  templateUrl: './carro-form.component.html',
  styleUrls: ['./carro-form.component.css']
})
export class CarroFormComponent implements OnInit {

  tituloPaginaCarro = "Cadastrar Carro";

  carro: Carro = {
      fabricante: null,
      tipo: null,
      placa: "",
      ano: null,
      cor: "",
      modelo: null
  }

  fabricantes: fabricante[];
  modelos: modelo[];

  constructor(private modeloServico: ModeloService,
    private router: Router,
    private fabricanteServico: FabricanteService,
    private carroServico: CarroService) { }
  
    atualizarFabricante(): void{
      this.fabricanteServico.read().subscribe(fabricantes =>{
        this.fabricantes = fabricantes
      })
    }

    atualizarModelo(): void{
      this.modeloServico.read().subscribe(modelos =>{
        this.modelos = modelos
      })

    }
  ngOnInit(): void {
    this.atualizarFabricante();
    this.atualizarModelo();
  }

  salvar(): void{
    this.carroServico.create(this.carro).subscribe( () => {
    this.carroServico.showMessage("Carro salvo com sucesso");
    this.router.navigate(['/carros']);
  }, err => {this.carroServico.showMessage("Erro Carro não cadastrado!"+err.message, true)})
 }
}
