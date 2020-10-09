package spring.ctrl.servico;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import spring.ctrl.negocio.FabricanteNegocio;
import spring.model.entidades.Fabricante;

@RestController
@RequestMapping(value="/fabricantes")
public class FabricanteServico {
	
	@Autowired
	private FabricanteNegocio fabricanteNegocio;
	
	@GetMapping
	public ResponseEntity<List<Fabricante>> inicio() {
	  List<Fabricante> list = fabricanteNegocio.findAll();	
	  return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Fabricante> findById(@PathVariable Integer id){
		Fabricante retorno = fabricanteNegocio.findById(id);
		return ResponseEntity.ok(retorno);
	}
	
	@PostMapping
	public ResponseEntity<Fabricante> insert(@RequestBody Fabricante fabricante){
		fabricante = fabricanteNegocio.insert(fabricante);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("{id}").buildAndExpand(fabricante.getFabricanteId()).toUri();
		return ResponseEntity.created(uri).body(fabricante);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		fabricanteNegocio.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Fabricante> update(@PathVariable Integer id, @RequestBody Fabricante fabricante){
		fabricante = fabricanteNegocio.update(fabricante);
		return ResponseEntity.ok().body(fabricante);
	}

}
