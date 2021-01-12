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
import Spring.ctrl.negocio.disciplinaNegocio;
import Spring.model.entidades.disciplina;

@CrossOrigin
@RestController
@RequestMapping(value="/disciplinas")
public class disciplinaServico {

	@Autowired
	private disciplinaNegocio disciplinaNegocio;

	@GetMapping
	public ResponseEntity<List<disciplina>> inicio() {
	  List<disciplina> list = disciplinaNegocio.findAll();	
	  return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<disciplina> findById(@PathVariable Integer id){
		disciplina retorno = null;
		try {
			retorno = disciplinaNegocio.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(retorno);
		} catch (notFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(retorno);
		}	
	}
	
	@GetMapping(value="/nome/{str}")
	public ResponseEntity<disciplina> findByName(@PathVariable String str){
		disciplina disciplina = null;
		try {
			disciplina = disciplinaNegocio.findByNome(str);
			return ResponseEntity.status(HttpStatus.OK).body(disciplina);
		} catch (notFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(disciplina);
		}
	}
	
	@PostMapping
	public ResponseEntity<disciplina> insert(@RequestBody disciplina disciplina){
		try {
			disciplina = disciplinaNegocio.insert(disciplina);
			return ResponseEntity.status(HttpStatus.CREATED).body(disciplina);
		} catch (notFoundException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(disciplina);
		}		
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		try {
			disciplinaNegocio.delete(id);
			return ResponseEntity.noContent().build();
		} catch (notFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<disciplina> update(@PathVariable Integer id, @RequestBody disciplina disciplina){
		try {
			disciplina = disciplinaNegocio.update(disciplina);
			return ResponseEntity.status(HttpStatus.CREATED).body(disciplina);
		} catch (notFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(disciplina);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(disciplina);
		}
		
	}

	@GetMapping("/datatable")
    public Page<disciplina> search(
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
		return disciplinaNegocio.search(searchTerm, page, size, order, active);

    }
}
