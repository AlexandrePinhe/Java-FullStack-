import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { curso } from 'src/app/models/curso.model';
import { departamento } from 'src/app/models/departamento.model';
import { professor } from 'src/app/models/professor.model';
import { CursoService } from 'src/app/service/curso.service';
import { DepartamentoService } from 'src/app/service/departamento.service';
import { ProfessorService } from 'src/app/service/professor.service';
import { DepartamentoAtivo } from '../departamento-ativo';
import { DepartamentoTipo } from '../Departamento-Tipo';

@Component({
  selector: 'app-departamento-form',
  templateUrl: './departamento-form.component.html',
  styleUrls: ['./departamento-form.component.css']
})
export class DepartamentoFormComponent implements OnInit {

  tituloPaginaDepartamento = "Cadastrar Departamento";

  departamento: departamento = {
    nome: "",
    data_contratacao: null,
    matricula_prof: null,
    cod_curso: null,
    tipo_departamento: null,
    departamento_ativo: null,
  }

   tipos: DepartamentoTipo[] = [
    {id: 0, desc: "Biologicas"},
    {id: 1, desc: "Exatas"},
    {id: 2, desc: "Humanas"}
   ]

   ativos: DepartamentoAtivo [] = [
     {id: 0, desc: "Inativo"},
     {id: 1, desc: "Ativo"}
   ]
   professores : professor[];
   cursos: curso[];

  constructor(private departamentoService: DepartamentoService,
    private router: Router,
    private professorService: ProfessorService,
    private cursoService: CursoService) { }

    atualizarProfessor(): void{
      this.professorService.read().subscribe(professores =>{
        this.professores = professores
      })
    }

    atualizarCursos(): void{
      this.cursoService.read().subscribe(cursos =>{
        this.cursos = cursos
      })
    }

  ngOnInit(): void {
    this.atualizarCursos();
    this.atualizarProfessor();
  }

  salvar(): void{
    this.departamentoService.create(this.departamento).subscribe( () => {
    this.departamentoService.showMessage("Departamento salvo com sucesso");
    this.router.navigate(['/departamentos']);
  }, err => {this.departamentoService.showMessage("Erro Departamento não cadastrado!"+err.message, true)})
 }

}
