import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { usuario } from 'src/app/models/usuario.model';
import { UsuarioService } from 'src/app/service/usuario.service';

@Component({
    selector: 'app-usuario-update',
    templateUrl: './usuario-form.component.html',
    styleUrls: ['./usuario-form.component.css']
})
export class UsuarioUpdateComponent implements OnInit {
  
  isLoadingResults = false;
  tituloPagina = "Alterar Usuário";
  hide = true;

  usuario: usuario;
  constructor(private servico: UsuarioService,
    private router: Router,
    private route: ActivatedRoute) { 

    }

  ngOnInit(): void {
    const id= this.route.snapshot.paramMap.get('id');
    this.servico.readById(id).subscribe(usuario =>{
      this.usuario = usuario;
    })
  }
  
  salvar(): void{
    this.isLoadingResults = true;
    this.servico.update(this.usuario).subscribe(() =>{
      this.servico.showMessage("Usuário foi alterado com sucesso!");
      this.isLoadingResults = false;
      this.router.navigate(['/usuarios']);
    },err => {
      this.isLoadingResults = false;
      this.servico.showMessage("Erro Usuário não alterado!" + err.message, true)
    })
  }
}