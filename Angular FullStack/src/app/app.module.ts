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
    FabricanteListComponent
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
    MatButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
