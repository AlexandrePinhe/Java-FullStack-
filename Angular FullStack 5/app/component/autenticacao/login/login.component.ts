import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { usuario } from 'src/app/models/usuario.model';
import { AutenticacaoService } from 'src/app/service/autenticacao.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usuario: usuario = {
    idUsuario: null,
    nomeUsuario: "",
    login: "",
    dtNascimento: null,
    senha: "",
    nivel: 0,
  }
  constructor(
    private servico: AutenticacaoService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
   this.servico.login(this.usuario).subscribe(usuario =>{
     console.log(usuario);
     if(usuario.idUsuario > 0){
      window.localStorage.setItem('token', usuario.idUsuario.toString() + usuario.login);
      window.localStorage.setItem('nome', usuario.nomeUsuario);
      window.localStorage.setItem('login', usuario.login);
      this.servico.showMessage(usuario.nomeUsuario+" Seja Bem Vindo!");
      this.router.navigate(["/"]);
     }else{
      this.servico.showMessage("Senha ou Login Inválido!", true);
     }
   },
    err => this.servico.showMessage("Senha ou Login Inválido!", true)
   );
  }

}
