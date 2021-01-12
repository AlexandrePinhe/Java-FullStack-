import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { professor } from 'src/app/models/professor.model';
import { ProfessorService } from 'src/app/service/professor.service';

@Component({
  selector: 'app-professor-update',
  templateUrl: './professor-form.component.html',
  styleUrls: ['./professor-form.component.css']
})
export class ProfessorUpdateComponent implements OnInit {
    isLoadingResults = false;
    tituloPagina = "Alterar Professor";

    professor: professor;
    constructor(private servico: ProfessorService,
        private router: Router,
        private route: ActivatedRoute){}
    
    
    ngOnInit(): void {
        const id= this.route.snapshot.paramMap.get('id');
        this.servico.readById(id).subscribe(professorUP =>{
          this.professor = professorUP;
        })
    }

    salvar(): void{
        this.isLoadingResults = true;
        this.servico.update(this.professor).subscribe(() =>{
          this.servico.showMessage("Professor foi alterado com sucesso!");
          this.isLoadingResults = false;
          this.router.navigate(['/professores']);
        },err => {
          this.isLoadingResults = false;
          this.servico.showMessage("Professor Aluno não alterado!" + err.message, true)
        })
      }
}