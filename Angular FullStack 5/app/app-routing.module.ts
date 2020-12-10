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
import { FabricanteUpdateComponent } from './component/Fabricante/fabricante-form/fabricante-update.component';
import { ModeloUpdateComponent } from './component/Modelo/modelo-form/modelo-update.component';
import { CarroUpdateComponent } from './component/Carro/carro-form/carro-update.component';
import { UsuarioListComponent } from './component/usuario/usuario-list/usuario-list.component';
import { UsuarioFormComponent } from './component/usuario/usuario-form/usuario-form.component';
import { UsuarioUpdateComponent } from './component/usuario/usuario-form/usuario-update-component';
import { MainComponent } from './component/autenticacao/main/main.component';
import { LoginComponent } from './component/autenticacao/login/login.component';
import { CadastroComponent } from './component/autenticacao/cadastro/cadastro.component';
import { AutenticacaoGuard } from './guard/autenticacao.guard';


const routes: Routes = [
  {path: "", component: HomeComponent,
   children: [
    {path:"carros", component: CarroListComponent},
    {path:"carros/form", component: CarroFormComponent},
    {path:"modelos", component: ModeloListComponent},
    {path: "modelos/form", component: ModeloFormComponent},
    {path: "fabricantes", component: FabricanteListComponent},
    {path: "fabricantes/form", component: FabricanteFormComponent},
    {path: "fabricantes/update/:id", component: FabricanteUpdateComponent},
    {path: "modelos/update/:id", component: ModeloUpdateComponent},
    {path: "carros/update/:id", component: CarroUpdateComponent},
    {path: "usuarios", component: UsuarioListComponent},
    {path: "usuarios/update/:id", component: UsuarioUpdateComponent},
    {path: "usuarios/form", component: UsuarioFormComponent}
   ], canActivate: [AutenticacaoGuard]
},
  {
    path: "",
    component: MainComponent,
    children: [
      {path: "", redirectTo: 'login', pathMatch: 'full'},
      {path: "login", component: LoginComponent},
      {path: "cadastro", component: CadastroComponent}
    ]
  }
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
