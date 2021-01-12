import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { disciplina } from 'src/app/models/disciplina.model';
import { DisciplinaService } from 'src/app/service/disciplina.service';

@Component({
  selector: 'app-disciplina-form',
  templateUrl: './disciplina-form.component.html',
  styleUrls: ['./disciplina-form.component.css']
})
export class DisciplinaFormComponent implements OnInit {

  tituloPagina = "Cadastrar Disciplina";

  disciplina: disciplina = {
    nome: " ",
    qtde_Alunos: null
  }
  constructor(private servico: DisciplinaService,
    private router: Router) { }

  ngOnInit(): void {
  }

  salvar(): void{
    this.servico.create(this.disciplina).subscribe( () => {
     this.servico.showMessage("Disciplina salva com sucesso");
     this.router.navigate(['/disciplinas']);
    }, err => {this.servico.showMessage("Erro Disciplina não cadastrada!"+err.message, true)})
  }
}
