import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { curso } from 'src/app/models/curso.model';
import { departamento } from 'src/app/models/departamento.model';
import { professor } from 'src/app/models/professor.model';
import { CursoService } from 'src/app/service/curso.service';
import { DepartamentoService } from 'src/app/service/departamento.service';
import { ProfessorService } from 'src/app/service/professor.service';
import { DepartamentoAtivo } from '../departamento-ativo';
import { DepartamentoTipo } from '../Departamento-Tipo';

@Component({
  selector: 'app-departamento-update',
  templateUrl: './departamento-form.component.html',
  styleUrls: ['./departamento-form.component.css']
})
export class DepartamentoUpdateComponent implements OnInit {
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
    
    constructor(private departamentoServico: DepartamentoService,
     private router: Router,
     private route: ActivatedRoute,
     private professorServico: ProfessorService,
     private cursoServico: CursoService){}  

    ngOnInit(): void{
        const id= this.route.snapshot.paramMap.get('id');
        this.departamentoServico.readById(id).subscribe(departamento =>{
          this.departamento = departamento;
        })
        
        this.professorServico.read().subscribe(professores =>{
          this.professores = professores
        })
    
        this.cursoServico.read().subscribe(cursos =>{
          this.cursos = cursos
        })
    }

    salvar(): void{
        this.departamentoServico.update(this.departamento).subscribe(() =>{
          this.departamentoServico.showMessage("Departamento foi alterado com sucesso!");
          this.router.navigate(['/departamentos']);
        }, err => {this.cursoServico.showMessage("Erro Departamento não alterado!", true)})
      }
}