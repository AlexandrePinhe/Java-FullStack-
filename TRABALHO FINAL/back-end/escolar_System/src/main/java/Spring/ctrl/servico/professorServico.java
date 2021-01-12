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

import Spring.ctrl.excecao.notFoundException;
import Spring.ctrl.negocio.professorNegocio;
import Spring.model.entidades.professor;

@CrossOrigin
@RestController
@RequestMapping(value="/professores")
public class professorServico {
  
	@Autowired
	private professorNegocio professorNegocio;

	@GetMapping
	public ResponseEntity<List<professor>> inicio() {
	  List<professor> list = professorNegocio.findAll();	
	  return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<professor> findById(@PathVariable Integer id){
		professor retorno = null;
		try {
			retorno = professorNegocio.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(retorno);
		} catch (notFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(retorno);
		}	
	}
	
	@GetMapping(value="/nome/{str}")
	public ResponseEntity<professor> findByName(@PathVariable String str){
		professor professor = null;
		try {
			professor = professorNegocio.findByNome(str);
			return ResponseEntity.status(HttpStatus.OK).body(professor);
		} catch (notFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(professor);
		}
	}
	
	@PostMapping
	public ResponseEntity<professor> insert(@RequestBody professor professor){
		try {
			professor = professorNegocio.insert(professor);
			return ResponseEntity.status(HttpStatus.CREATED).body(professor);
		} catch (notFoundException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(professor);
		}		
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		try {
			professorNegocio.delete(id);
			return ResponseEntity.noContent().build();
		} catch (notFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<professor> update(@PathVariable Integer id, @RequestBody professor professor){
		try {
			professor = professorNegocio.update(professor);
			return ResponseEntity.status(HttpStatus.CREATED).body(professor);
		} catch (notFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(professor);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(professor);
		}
		
	}

	@GetMapping("/datatable")
    public Page<professor> search(
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
                    defaultValue = "nome") String active) {
		return professorNegocio.search(searchTerm, page, size, order, active);

    }
}
