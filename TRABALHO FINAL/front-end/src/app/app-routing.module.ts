import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AlunoFormComponent } from "./component/aluno/aluno-form/aluno-form.component";
import { AlunoUpdateComponent } from "./component/aluno/aluno-form/aluno-update.component";
import { AlunoListComponent } from "./component/aluno/aluno-list/aluno-list.component";
import { CursoFormComponent } from "./component/curso/curso-form/curso-form.component";
import { CursoUpdateComponent } from "./component/curso/curso-form/curso-update-component";
import { CursoListComponent } from "./component/curso/curso-list/curso-list.component";
import { DepartamentoFormComponent } from "./component/departamento/departamento-form/departamento-form.component";
import { DepartamentoUpdateComponent } from "./component/departamento/departamento-form/departamento-update-component";
import { DepartamentoListComponent } from "./component/departamento/departamento-list/departamento-list.component";
import { DisciplinaFormComponent } from "./component/disciplina/disciplina-form/disciplina-form.component";
import { DisciplinaUpdateComponent } from "./component/disciplina/disciplina-form/disciplina-update.component";
import { DisciplinaListComponent } from "./component/disciplina/disciplina-list/disciplina-list.component";
import { HomeComponent } from "./component/home/home.component";
import { ProfessorFormComponent } from "./component/professor/professor-form/professor-form.component";
import { ProfessorUpdateComponent } from "./component/professor/professor-form/professor-update.component";
import { ProfessorListComponent } from "./component/professor/professor-list/professor-list.component";

const routes: Routes = [
    {path: "", component: HomeComponent},
    {path: "alunos", component: AlunoListComponent},
    {path: "professores", component: ProfessorListComponent},
    {path: "disciplinas", component: DisciplinaListComponent},
    {path: "cursos", component: CursoListComponent},
    {path: "departamentos", component: DepartamentoListComponent},
    {path: "alunos/form", component: AlunoFormComponent},
    {path: "professores/form", component: ProfessorFormComponent},
    {path: "disciplinas/form", component: DisciplinaFormComponent},
    {path: "cursos/form", component: CursoFormComponent},
    {path: "departamentos/form", component: DepartamentoFormComponent},
    {path: "alunos/update/:id", component: AlunoUpdateComponent},
    {path: "professores/update/:id",component: ProfessorUpdateComponent},
    {path: "disciplinas/update/:id", component: DisciplinaUpdateComponent},
    {path: "cursos/update/:id", component: CursoUpdateComponent},
    {path: "departamentos/update/:id", component: DepartamentoUpdateComponent}
];

@NgModule({
    declarations: [],
    imports: [
      CommonModule,
      RouterModule.forRoot(routes)
    ],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }