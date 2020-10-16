package spring.ctrl.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.ctrl.excecao.FabricanteException;
import spring.ctrl.excecao.NotFoundException;
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
		Fabricante retorno = null;
		try {
			retorno = fabricanteNegocio.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(retorno);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(retorno);
		}	
	}
	
	@GetMapping(value="/nome/{str}")
	public ResponseEntity<Fabricante> findByName(@PathVariable String str){
		Fabricante fabricante = null;
		try {
			fabricante = fabricanteNegocio.findByNome(str);
			return ResponseEntity.status(HttpStatus.OK).body(fabricante);
		} catch (FabricanteException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(fabricante);
		}
	}
	
	
	@PostMapping
	public ResponseEntity<Fabricante> insert(@RequestBody Fabricante fabricante){
		try {
			fabricante = fabricanteNegocio.insert(fabricante);
			return ResponseEntity.status(HttpStatus.CREATED).body(fabricante);
		} catch (FabricanteException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(fabricante);
		}		
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		try {
			fabricanteNegocio.delete(id);
			return ResponseEntity.noContent().build();
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Fabricante> update(@PathVariable Integer id, @RequestBody Fabricante fabricante){
		try {
			fabricante = fabricanteNegocio.update(fabricante);
			return ResponseEntity.status(HttpStatus.CREATED).body(fabricante);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(fabricante);
		} catch (FabricanteException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(fabricante);
		}
		
	}

}
