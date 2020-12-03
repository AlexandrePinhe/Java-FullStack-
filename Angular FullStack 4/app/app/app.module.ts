import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './views/template/header/header.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import { FooterComponent } from './views/template/footer/footer.component';
import { NavComponent } from './views/template/nav/nav.component';
import {MatSidenavModule} from '@angular/material/sidenav';
import { MatSliderModule } from '@angular/material/slider';
import { RouterModule } from '@angular/router';
import {MatListModule} from '@angular/material/list';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { HomeComponent } from './component/home/home.component';
import { CarroFormComponent } from './component/Carro/carro-form/carro-form.component';
import { CarroListComponent } from './component/Carro/carro-list/carro-list.component';
import { ModeloFormComponent } from './component/Modelo/modelo-form/modelo-form.component';
import { ModeloListComponent } from './component/Modelo/modelo-list/modelo-list.component';
import { FabricanteFormComponent } from './component/Fabricante/fabricante-form/fabricante-form.component';
import { FabricanteListComponent } from './component/Fabricante/fabricante-list/fabricante-list.component';
import { AppRoutingModule } from './app-routing.module';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import { DirCorDirective } from './diretive/dir-cor.directive';
import { ParaDirective } from './diretive/para.directive';
import {HttpClientModule} from '@angular/common/http';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatTableModule} from '@angular/material/table';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { FabricanteUpdateComponent } from './component/Fabricante/fabricante-form/fabricante-update.component';
import { ConfirmDeleteComponent } from './views/template/confirm-delete/confirm-delete.component';
import {MatDialogModule} from '@angular/material/dialog';
import { ModeloUpdateComponent } from './component/Modelo/modelo-form/modelo-update.component';
import {MatSelectModule} from '@angular/material/select';
import { CarroUpdateComponent } from './component/Carro/carro-form/carro-update.component';
import { DatatableComponent } from './component/datatable/datatable.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { FabricanteTableComponent } from './component/fabricante/fabricante-table/fabricante-table.component';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import { ModeloTableComponent } from './component/modelo/modelo-table/modelo-table.component';
import { UsuarioListComponent } from './component/usuario/usuario-list/usuario-list.component';
import { UsuarioFormComponent } from './component/usuario/usuario-form/usuario-form.component';
import { UsuarioUpdateComponent } from './component/usuario/usuario-form/usuario-update-component';
import {MatIconModule} from '@angular/material/icon';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    NavComponent,
    HomeComponent,
    CarroFormComponent,
    CarroListComponent,
    ModeloFormComponent,
    ModeloListComponent,
    FabricanteFormComponent,
    FabricanteListComponent,
    DirCorDirective,
    ParaDirective,
    FabricanteUpdateComponent,
    ConfirmDeleteComponent,
    ModeloUpdateComponent,
    CarroUpdateComponent,
    DatatableComponent,
    FabricanteTableComponent,
    ModeloTableComponent,
    UsuarioListComponent,
    UsuarioFormComponent,
    UsuarioUpdateComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    MatToolbarModule,
    MatSidenavModule,
    MatSliderModule,
    RouterModule,
    MatListModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,
    HttpClientModule,
    MatSnackBarModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatDialogModule,
    MatSelectModule,
    MatProgressSpinnerModule,
    MatPaginatorModule,
    MatSortModule,
    MatIconModule,
    MatDatepickerModule,
    MatNativeDateModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
