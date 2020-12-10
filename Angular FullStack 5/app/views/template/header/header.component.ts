import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AutenticacaoService } from 'src/app/service/autenticacao.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private servico: AutenticacaoService,
    private router: Router) { }

  ngOnInit(): void {
  }

  onLogout(): void{
    window.localStorage.clear();
    this.servico.showMessage("Logout realizado com sucesso!");
    this.router.navigate(["/login"]);
  }
}
