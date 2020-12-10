package spring.ctrl.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.ctrl.negocio.UsuarioNegocio;
import spring.model.entidades.Usuario;

@CrossOrigin
@RestController
@RequestMapping(value = "/autenticacao")
public class AutenticacaoServico {

	@Autowired
	private UsuarioNegocio negocio;
	
	@PostMapping
	public ResponseEntity<Usuario> insert(@RequestBody Usuario usuario){
		Usuario usuLog = negocio.findByLogin(usuario.getLogin());
		
		if(usuLog != null && usuario.getSenha().equals(usuLog.getSenha())) {
			usuario = usuLog;
			return ResponseEntity.ok().body(usuario);
		}else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(usuario);
		
	}
}
