import { Component, OnInit } from '@angular/core';
import { modelo } from 'src/app/models/modelo.model';
import { ModeloService } from 'src/app/service/modelo.service';
import { ActivatedRoute, Router } from '@angular/router';
import { fabricante } from 'src/app/models/fabricante.model';
import { FabricanteService } from 'src/app/service/fabricante.service';

@Component({
  selector: 'app-modelo-update',
  templateUrl: './modelo-update.component.html',
  styleUrls: ['./modelo-update.component.css']
})
export class ModeloUpdateComponent implements OnInit {

  modelo: modelo ={
    nomeModelo: "",
    fabricante: null
  }
   fabricantes: fabricante[];

  constructor(private servico: ModeloService,
    private router: Router,
    private route: ActivatedRoute,
    private fabricanteServico: FabricanteService) { }

  ngOnInit(): void {
    const id= this.route.snapshot.paramMap.get('id');
    this.servico.readById(id).subscribe(modelo =>{
      this.modelo = modelo;
    })
    
    this.fabricanteServico.read().subscribe(fabricantes =>{
      this.fabricantes = fabricantes
    })
  }
  

  salvar(): void{
    this.servico.update(this.modelo).subscribe(() =>{
      this.servico.showMessage("Modelo foi alterado com sucesso!");
      this.router.navigate(['/modelos']);
    })
  }

}
