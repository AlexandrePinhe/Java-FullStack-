import { Component, OnInit } from '@angular/core';
import { ModeloService } from 'src/app/service/modelo.service';
import { modelo } from 'src/app/models/modelo.model';
import { FabricanteService } from 'src/app/service/fabricante.service';
import { Carro } from 'src/app/models/carro.model';
import { CarroService } from 'src/app/service/carro.service';
import { fabricante } from 'src/app/models/fabricante.model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-carro-update',
  templateUrl: './carro-update.component.html',
  styleUrls: ['./carro-update.component.css']
})
export class CarroUpdateComponent implements OnInit {

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
    private route: ActivatedRoute,
    private fabricanteServico: FabricanteService,
    private carroServico: CarroService) { }

  ngOnInit(): void {

    const id= this.route.snapshot.paramMap.get('id');
    this.carroServico.readById(id).subscribe(carro =>{
      this.carro = carro;
    })
    
    this.fabricanteServico.read().subscribe(fabricantes =>{
      this.fabricantes = fabricantes
    })

    this.modeloServico.read().subscribe(modelos =>{
      this.modelos = modelos
    })
  }

  salvar(): void{
    this.carroServico.create(this.carro).subscribe( () => {
    this.carroServico.showMessage("Carro alterado com sucesso");
    this.router.navigate(['/carros']);
  })
 }
}
