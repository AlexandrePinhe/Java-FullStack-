import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './component/home/home.component';
import { CarroListComponent } from './component/Carro/carro-list/carro-list.component';
import { CarroFormComponent } from './component/Carro/carro-form/carro-form.component';
import { ModeloListComponent } from './component/Modelo/modelo-list/modelo-list.component';
import { ModeloFormComponent } from './component/Modelo/modelo-form/modelo-form.component';
import { FabricanteListComponent } from './component/Fabricante/fabricante-list/fabricante-list.component';
import { FabricanteFormComponent } from './component/Fabricante/fabricante-form/fabricante-form.component';

const routes: Routes = [
  {path: "", component: HomeComponent},
  {path:"carros", component: CarroListComponent},
  {path:"carros/form", component: CarroFormComponent},
  {path:"modelos", component: ModeloListComponent},
  {path: "modelos/form", component: ModeloFormComponent},
  {path: "fabricantes", component: FabricanteListComponent},
  {path: "fabricantes/form", component: FabricanteFormComponent}
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
