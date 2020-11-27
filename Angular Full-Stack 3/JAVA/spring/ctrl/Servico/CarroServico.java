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

import spring.ctrl.excecao.CarroException;
import spring.ctrl.excecao.NotFoundException;
import spring.ctrl.negocio.CarroNegocio;
import spring.model.entidades.Carro;
@CrossOrigin
@RestController
@RequestMapping(value="/carros")
public class CarroServico {
    
	@Autowired
	private CarroNegocio negocio;
	
	@GetMapping
	public ResponseEntity<List<Carro>> inicio() {
	  List<Carro> list = negocio.findAll();	
	  return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Carro> findById(@PathVariable Integer id){
		Carro retorno = null;
		try {
			retorno = negocio.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(retorno);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(retorno);
		}

	}
	
	@PostMapping
	public ResponseEntity<Carro> insert(@RequestBody Carro carro){
		try {
			carro = negocio.insert(carro);
			return ResponseEntity.status(HttpStatus.CREATED).body(carro);
		} catch (CarroException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(carro);
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
	public ResponseEntity<Carro> update(@PathVariable Integer id, @RequestBody Carro carro){
		try {
			carro = negocio.update(carro);
			return ResponseEntity.status(HttpStatus.CREATED).body(carro);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(carro);
		} catch (CarroException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(carro);
		}
	}

	@GetMapping(value="/filtro/{str}")
	public ResponseEntity<List<Carro>> FiltroPorNome(@PathVariable String str){
		List<Carro> carros = null;
		try {
			carros = negocio.findAllPlaca(str);
			return ResponseEntity.status(HttpStatus.OK).body(carros);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(carros);		
		}
	}
	

	@GetMapping(value="ordem")
	public ResponseEntity<List<Carro>> findAllOrderNome() {
		
		List<Carro> list = negocio.findAllOrderNome();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/fabricante/{id}")
	public ResponseEntity<List<Carro>> FiltroPorFabricante(@PathVariable Integer id){
		List<Carro> carros = null;
		try {
			carros = negocio.buscaPorFabricante(id);
			return ResponseEntity.status(HttpStatus.OK).body(carros);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(carros);		
		}
	}
	
	@GetMapping(value="/modelo/{id}")
	public ResponseEntity<List<Carro>> FiltroPorModelo(@PathVariable Integer id){
		List<Carro> carros = null;
		try {
			carros = negocio.buscaPorModelo(id);
			return ResponseEntity.status(HttpStatus.OK).body(carros);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(carros);		
		}
	}
	
	@GetMapping("/datatable")
    public Page<Carro> search(
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
                    defaultValue = "placa") String active) {
		return negocio.search(searchTerm, page, size, order, active);
		}

}
