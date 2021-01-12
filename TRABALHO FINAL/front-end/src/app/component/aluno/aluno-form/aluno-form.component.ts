import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { aluno } from 'src/app/models/aluno.model';
import { AlunoService } from 'src/app/service/aluno.service';

@Component({
  selector: 'app-aluno-form',
  templateUrl: './aluno-form.component.html',
  styleUrls: ['./aluno-form.component.css']
})
export class AlunoFormComponent implements OnInit {

  tituloPagina = "Cadastrar Aluno";

  aluno: aluno = {
    cpf: null, 
    nome: "",
    endereco: "",
  }
  constructor(private servico: AlunoService,
    private router: Router) { }

  ngOnInit(): void {
  }

  salvar(): void{
    this.servico.create(this.aluno).subscribe( () => {
     this.servico.showMessage("Aluno salvo com sucesso");
     this.router.navigate(['/alunos']);
    }, err => {this.servico.showMessage("Erro Aluno não cadastrado!"+err.message, true)})
  }
}
