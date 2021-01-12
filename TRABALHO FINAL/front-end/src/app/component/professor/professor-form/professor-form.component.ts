import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { professor } from 'src/app/models/professor.model';
import { ProfessorService } from 'src/app/service/professor.service';

@Component({
  selector: 'app-professor-form',
  templateUrl: './professor-form.component.html',
  styleUrls: ['./professor-form.component.css']
})
export class ProfessorFormComponent implements OnInit {
  tituloPagina = "Cadastrar Professor";

  professor: professor = {
    nome: ""
  }
  constructor(private servico: ProfessorService,
    private router: Router) { }

  ngOnInit(): void {
  }

  salvar(): void{
    this.servico.create(this.professor).subscribe( () => {
     this.servico.showMessage("Professor salvo com sucesso");
     this.router.navigate(['/professores']);
    }, err => {this.servico.showMessage("Erro Professor não cadastrado!"+err.message, true)})
  }
}
