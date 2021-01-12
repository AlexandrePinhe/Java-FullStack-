import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { aluno } from 'src/app/models/aluno.model';
import { AlunoService } from 'src/app/service/aluno.service';

@Component({
    selector: 'app-aluno-update',
    templateUrl: './aluno-form.component.html',
    styleUrls: ['./aluno-form.component.css']
  })
  export class AlunoUpdateComponent implements OnInit {
    isLoadingResults = false;
    tituloPagina = "Alterar Aluno";

    aluno: aluno;
    constructor(private servico: AlunoService,
        private router: Router,
        private route: ActivatedRoute){}
    
    
    ngOnInit(): void {
        const id= this.route.snapshot.paramMap.get('id');
        this.servico.readById(id).subscribe(alunoUP =>{
          this.aluno = alunoUP;
        })
    }

    salvar(): void{
        this.isLoadingResults = true;
        this.servico.update(this.aluno).subscribe(() =>{
          this.servico.showMessage("Aluno foi alterado com sucesso!");
          this.isLoadingResults = false;
          this.router.navigate(['/alunos']);
        },err => {
          this.isLoadingResults = false;
          this.servico.showMessage("Erro Aluno não alterado!" + err.message, true)
        })
      }
      
  }
