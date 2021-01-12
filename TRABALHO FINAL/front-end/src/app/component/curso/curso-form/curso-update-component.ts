import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { aluno } from 'src/app/models/aluno.model';
import { curso } from 'src/app/models/curso.model';
import { disciplina } from 'src/app/models/disciplina.model';
import { AlunoService } from 'src/app/service/aluno.service';
import { CursoService } from 'src/app/service/curso.service';
import { DisciplinaService } from 'src/app/service/disciplina.service';

@Component({
  selector: 'app-curso-update',
  templateUrl: './curso-form.component.html',
  styleUrls: ['./curso-form.component.css']
})
export class CursoUpdateComponent implements OnInit {
    isLoadingResults = false;
    tituloPaginaCurso = "Alterar Curso";

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
    private route: ActivatedRoute,
    private disciplinaServico: DisciplinaService,
    private alunoServico: AlunoService){}

    ngOnInit(): void {

        const id= this.route.snapshot.paramMap.get('id');
    this.cursoServico.readById(id).subscribe(curso =>{
      this.curso = curso;
    })
    
    this.disciplinaServico.read().subscribe(disciplinas =>{
      this.disciplinas = disciplinas
    })

    this.alunoServico.read().subscribe(alunos =>{
      this.alunos = alunos
    })
    }

    salvar(): void{
        this.cursoServico.update(this.curso).subscribe(() =>{
          this.cursoServico.showMessage("Curso foi alterado com sucesso!");
          this.router.navigate(['/cursos']);
        }, err => {this.cursoServico.showMessage("Erro Curso não alterado!", true)})
      }
}