import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FooterComponent } from './views/template/footer/footer.component';
import { HeaderComponent } from './views/template/header/header.component';
import { NavComponent } from './views/template/nav/nav.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatBadgeModule} from '@angular/material/badge';
import {MatListModule} from '@angular/material/list';
import {MatIconModule} from '@angular/material/icon';
import { RouterModule } from '@angular/router';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatInputModule} from '@angular/material/input';
import {MatDividerModule} from '@angular/material/divider';
import {HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { HomeComponent } from './component/home/home.component';
import { AlunoListComponent } from './component/aluno/aluno-list/aluno-list.component';
import { ProfessorListComponent } from './component/professor/professor-list/professor-list.component';
import { DisciplinaListComponent } from './component/disciplina/disciplina-list/disciplina-list.component';
import { CursoListComponent } from './component/curso/curso-list/curso-list.component';
import { DepartamentoListComponent } from './component/departamento/departamento-list/departamento-list.component';
import { ConfirmDeleteComponent } from './views/template/confirm-delete/confirm-delete.component';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatTableModule} from '@angular/material/table';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDialogModule} from '@angular/material/dialog';
import {MatSelectModule} from '@angular/material/select';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { DepartamentoFormComponent } from './component/departamento/departamento-form/departamento-form.component';
import { AlunoFormComponent } from './component/aluno/aluno-form/aluno-form.component';
import { ProfessorFormComponent } from './component/professor/professor-form/professor-form.component';
import { CursoFormComponent } from './component/curso/curso-form/curso-form.component';
import { DisciplinaFormComponent } from './component/disciplina/disciplina-form/disciplina-form.component';
import { FormsModule }   from '@angular/forms';
import { AlunoUpdateComponent } from './component/aluno/aluno-form/aluno-update.component';
import { ProfessorUpdateComponent } from './component/professor/professor-form/professor-update.component';
import { DisciplinaUpdateComponent } from './component/disciplina/disciplina-form/disciplina-update.component';
import { CursoUpdateComponent } from './component/curso/curso-form/curso-update-component';
import { DepartamentoUpdateComponent } from './component/departamento/departamento-form/departamento-update-component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    NavComponent,
    HomeComponent,
    AlunoListComponent,
    ProfessorListComponent,
    DisciplinaListComponent,
    CursoListComponent,
    DepartamentoListComponent,
    ConfirmDeleteComponent,
    DepartamentoFormComponent,
    AlunoFormComponent,
    ProfessorFormComponent,
    CursoFormComponent,
    DisciplinaFormComponent,
    AlunoUpdateComponent,
    ProfessorUpdateComponent,
    DisciplinaUpdateComponent,
    CursoUpdateComponent,
    DepartamentoUpdateComponent
  ],
  imports: [
    BrowserModule, BrowserAnimationsModule,
    MatSidenavModule, MatToolbarModule,
    MatBadgeModule, MatListModule,
    MatIconModule, RouterModule,
    MatInputModule, MatDividerModule,
    HttpClientModule, AppRoutingModule,
    MatCardModule, MatButtonModule,
    MatSnackBarModule, MatTableModule,
    MatFormFieldModule, MatDialogModule,
    MatSelectModule, MatProgressSpinnerModule,
    MatPaginatorModule, MatSortModule,
    MatDatepickerModule, MatNativeDateModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
