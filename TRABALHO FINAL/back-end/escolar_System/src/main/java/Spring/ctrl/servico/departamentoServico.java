package Spring.ctrl.servico;

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

import Spring.ctrl.negocio.departamentoNegocio;
import Spring.model.entidades.departamento;
import Spring.ctrl.excecao.notFoundException;

@CrossOrigin
@RestController
@RequestMapping(value="/departamentos")
public class departamentoServico {

	@Autowired
	private departamentoNegocio negocioDepartamento;
	
	@GetMapping
	public ResponseEntity<List<departamento>> inicio() {
	  List<departamento> list = negocioDepartamento.findAll();	
	  return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<departamento> findById(@PathVariable Integer id){
		departamento retorno = null;
		try {
			retorno = negocioDepartamento.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(retorno);
		} catch (notFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(retorno);
		}
	}
	
	@PostMapping
	public ResponseEntity<departamento> insert(@RequestBody departamento departamento){
		try {
			departamento = negocioDepartamento.insert(departamento);
			return ResponseEntity.status(HttpStatus.CREATED).body(departamento);
		} catch (notFoundException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(departamento);
		}		
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		try {
			negocioDepartamento.delete(id);
			return ResponseEntity.noContent().build();
		} catch (notFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<departamento> update(@PathVariable Integer id, @RequestBody departamento departamento){
		try {
			departamento = negocioDepartamento.update(departamento);
			return ResponseEntity.status(HttpStatus.CREATED).body(departamento);
		} catch (notFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(departamento);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(departamento);
		}
	}
	
	@GetMapping(value="/filtro/{str}")
	public ResponseEntity<List<departamento>> FiltroPorNome(@PathVariable String str){
		List<departamento> departamentos = null;
		try {
			departamentos = negocioDepartamento.findAllNome(str);
			return ResponseEntity.status(HttpStatus.OK).body(departamentos);
		} catch (notFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(departamentos);		
		}
	}
	

	@GetMapping(value="ordem")
	public ResponseEntity<List<departamento>> findAllOrderNome() {		
		List<departamento> list = negocioDepartamento.findAllOrderNome();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/matricula_prof/{id}")
	public ResponseEntity<List<departamento>> FiltroPorProfessor(@PathVariable Integer id){
		List<departamento> departamentos = null;
		try {
			departamentos = negocioDepartamento.buscaPorProfessor(id);
			return ResponseEntity.status(HttpStatus.OK).body(departamentos);
		} catch (notFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(departamentos);		
		}
	}
	
	@GetMapping(value="/cod_curso/{id}")
	public ResponseEntity<List<departamento>> FiltroPorCurso(@PathVariable Integer id){
		List<departamento> departamentos = null;
		try {
			departamentos = negocioDepartamento.buscarPorCurso(id);
			return ResponseEntity.status(HttpStatus.OK).body(departamentos);
		} catch (notFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(departamentos);		
		}
	}
	
	@GetMapping("/datatable")
    public Page<departamento> search(
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
                    defaultValue = "id") String active) {
		return negocioDepartamento.search(searchTerm, page, size, order, active);
		}
}
