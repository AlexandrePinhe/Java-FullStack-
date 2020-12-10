import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { usuario } from 'src/app/models/usuario.model';
import { UsuarioService } from 'src/app/service/usuario.service';

@Component({
  selector: 'app-usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrls: ['./usuario-form.component.css']
})
export class UsuarioFormComponent implements OnInit {

  tituloPagina = "Cadastrar Usuario";
   hide = true;
   
  usuario : usuario = {
    nomeUsuario: "",
    login: "",
    dtNascimento: null,
    senha: "",
    nivel: null
  }
  constructor(private servico: UsuarioService,
    private router: Router) { }

  ngOnInit(): void {
  }

  salvar(): void{
    this.servico.create(this.usuario).subscribe( () => {
     this.servico.showMessage("Usuário salvo com sucesso");
     this.router.navigate(['/usuarios']);
    }, err => {this.servico.showMessage("Erro Usuário não cadastrado!"+err.message, true)})
  }
}
