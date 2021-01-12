import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { aluno } from 'src/app/models/aluno.model';
import { disciplina } from 'src/app/models/disciplina.model';
import { AlunoService } from 'src/app/service/aluno.service';
import { DisciplinaService } from 'src/app/service/disciplina.service';

@Component({
    selector: 'app-disciplina-update',
    templateUrl: './disciplina-form.component.html',
    styleUrls: ['./disciplina-form.component.css']
  })
  export class DisciplinaUpdateComponent implements OnInit {
    isLoadingResults = false;
    tituloPagina = "Alterar Disciplina";

    disciplina: disciplina;
    constructor(private servico: DisciplinaService,
        private router: Router,
        private route: ActivatedRoute){}
    
    
    ngOnInit(): void {
        const id= this.route.snapshot.paramMap.get('id');
        this.servico.readById(id).subscribe(disciplinaUP =>{
          this.disciplina = disciplinaUP;
        })
    }

    salvar(): void{
        this.isLoadingResults = true;
        this.servico.update(this.disciplina).subscribe(() =>{
          this.servico.showMessage("Disciplina foi alterada com sucesso!");
          this.isLoadingResults = false;
          this.router.navigate(['/disciplinas']);
        },err => {
          this.isLoadingResults = false;
          this.servico.showMessage("Erro Disciplina não alterada!" + err.message, true)
        })
      }
      
  }