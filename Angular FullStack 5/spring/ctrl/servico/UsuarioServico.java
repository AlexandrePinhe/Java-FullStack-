package spring.ctrl.servico;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import spring.ctrl.negocio.UsuarioNegocio;
import spring.model.entidades.Usuario;

@CrossOrigin
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioServico {
	
	@Autowired
	private UsuarioNegocio negocio;

	@GetMapping
	public ResponseEntity<List<Usuario>> findAll() {
		System.out.println("servico 1");
		List<Usuario> list = negocio.findAllSimples();
		System.out.println("servico 2");
		return ResponseEntity.ok().body(list);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Integer id) {
		Usuario retorno = negocio.findById(id);
		return ResponseEntity.ok(retorno);
	}

	@PostMapping
	public ResponseEntity<Usuario> insert(@RequestBody Usuario usuario) {
		usuario = negocio.insert(usuario);

		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
//				.buildAndExpand(usuario.getIdUsuario()).toUri();
				.buildAndExpand(1).toUri();
		return ResponseEntity.created(uri).body(usuario);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		negocio.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Usuario> update(@PathVariable Integer id, @RequestBody Usuario usuario) {
		usuario = negocio.update(usuario);
		return ResponseEntity.ok().body(usuario);
	}

	@GetMapping("/datatable")
	public Page<Usuario> search(
			@RequestParam(value = "searchTerm", required = false, defaultValue = "") String searchTerm,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "5") int size,
			@RequestParam(value = "order", required = false, defaultValue = "ASC") String order,
			@RequestParam(value = "active", required = false, defaultValue = "nomeUsuario") String active) {
		return negocio.search(searchTerm, page, size, order, active);

	}
	
	/*
	 * @GetMapping public Page<Usuario> getAll() { return negocio.findAll(); }
	 */
}
