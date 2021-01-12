import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { aluno } from 'src/app/models/aluno.model';
import { curso } from 'src/app/models/curso.model';
import { disciplina } from 'src/app/models/disciplina.model';
import { AlunoService } from 'src/app/service/aluno.service';
import { CursoService } from 'src/app/service/curso.service';
import { DisciplinaService } from 'src/app/service/disciplina.service';

@Component({
  selector: 'app-curso-form',
  templateUrl: './curso-form.component.html',
  styleUrls: ['./curso-form.component.css']
})
export class CursoFormComponent implements OnInit {

  tituloPaginaCurso = "Cadastrar Curso";

  curso: curso = {
    nome: "",
    cod_disciplina: null,
    data_matricula: null,
    id_aluno: null
  }

  disciplinas: disciplina[];
  alunos: aluno[];

  constructor(private cursoServico: CursoService,
    private router: Router,
    private disciplinaServico: DisciplinaService,
    private alunoServico: AlunoService) { }

    atualizarDisciplina(): void{
      this.disciplinaServico.read().subscribe(disciplinas =>{
        this.disciplinas = disciplinas
      })
    }

    atualizarAlunos(): void{
      this.alunoServico.read().subscribe(alunos =>{
        this.alunos = alunos
      })
    }
  ngOnInit(): void {
    this.atualizarDisciplina();
    this.atualizarAlunos();
  }

  salvar(): void{
    this.cursoServico.create(this.curso).subscribe( () => {
    this.cursoServico.showMessage("Curso salvo com sucesso");
    this.router.navigate(['/cursos']);
  }, err => {this.cursoServico.showMessage("Erro Curso não cadastrado!"+err.message, true)})
 }
}
