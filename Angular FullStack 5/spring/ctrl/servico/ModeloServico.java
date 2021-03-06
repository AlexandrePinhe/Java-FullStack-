package spring.ctrl.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

import spring.ctrl.excecao.ModeloException;
import spring.ctrl.excecao.NotFoundException;
import spring.ctrl.negocio.ModeloNegocio;
import spring.model.entidades.Modelo;

@CrossOrigin
@RestController
@RequestMapping(value="/modelos")
public class ModeloServico {

	@Autowired
	private ModeloNegocio negocio;
	
	@GetMapping
	public ResponseEntity<List<Modelo>> inicio() {
	  List<Modelo> list = negocio.findAll();	
	  return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Modelo> findById(@PathVariable Integer id){
		Modelo retorno = null;
		try {
			retorno = negocio.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(retorno);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(retorno);
		}
	}
	
	@GetMapping(value="/nome/{str}")
	public ResponseEntity<Modelo> findByName(@PathVariable String str){
		Modelo modelo = null;
		try {
			modelo = negocio.findByNome(str);
			return ResponseEntity.status(HttpStatus.OK).body(modelo);
		} catch (ModeloException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(modelo);
		}
	}
	
	
	@PostMapping
	public ResponseEntity<Modelo> insert(@RequestBody Modelo modelo){
		try {
			modelo = negocio.insert(modelo);
			return ResponseEntity.status(HttpStatus.CREATED).body(modelo);
		} catch (ModeloException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(modelo);
		}		
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		try {
			negocio.delete(id);
			return ResponseEntity.noContent().build();
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Modelo> update(@PathVariable Integer id, @RequestBody Modelo modelo){
		try {
			modelo = negocio.update(modelo);
			return ResponseEntity.status(HttpStatus.CREATED).body(modelo);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(modelo);
		} catch (ModeloException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(modelo);
		}
	}
	
	@GetMapping(value="/filtro/{str}")
	public ResponseEntity<List<Modelo>> FiltroPorNome(@PathVariable String str){
		List<Modelo> modelos = null;
		try {
			modelos = negocio.findAllNome(str);
			return ResponseEntity.status(HttpStatus.OK).body(modelos);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(modelos);		
		}
	}
	

	@GetMapping(value="ordem")
	public ResponseEntity<List<Modelo>> findAllOrderNome() {
		
		List<Modelo> list = negocio.findAllOrderNome();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/fabricante/{id}")
	public ResponseEntity<List<Modelo>> FiltroPorFabricante(@PathVariable Integer id){
		List<Modelo> modelos = null;
		try {
			modelos = negocio.buscaPorFabricante(id);
			return ResponseEntity.status(HttpStatus.OK).body(modelos);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(modelos);		
		}
	}
	
	@GetMapping("/datatable")
    public Page<Modelo> search(
            @RequestParam(
            		value = "searchTerm",
            		required = false,
                    defaultValue = "") String searchTerm,
            @RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "5") int size,
            @RequestParam(
            		value="order",
                    required = false,
                    defaultValue = "ASC") String order,
            @RequestParam(
            		value="active",
                    required = false,
                    defaultValue = "nomeModelo") String active) {
		return negocio.search(searchTerm, page, size, order, active);
	}

}
