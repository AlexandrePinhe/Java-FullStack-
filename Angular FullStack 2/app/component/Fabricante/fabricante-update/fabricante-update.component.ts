import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { fabricante } from 'src/app/models/fabricante.model';
import { FabricanteService } from 'src/app/service/fabricante.service';

@Component({
  selector: 'app-fabricante-update',
  templateUrl: './fabricante-update.component.html',
  styleUrls: ['./fabricante-update.component.css']
})
export class FabricanteUpdateComponent implements OnInit {

  fabricante: fabricante;
  constructor(private servico: FabricanteService,
    private router: Router,
    private route: ActivatedRoute) { 

    }

  ngOnInit(): void {
    const id= this.route.snapshot.paramMap.get('id');
    this.servico.readById(id).subscribe(fabricante =>{
      this.fabricante = fabricante;
    })
  }
  
  salvar(): void{
    this.servico.update(this.fabricante).subscribe(() =>{
      this.servico.showMessage("Fabricante foi alterado com sucesso!");
      this.router.navigate(['/fabricantes']);
    })
  }
}
