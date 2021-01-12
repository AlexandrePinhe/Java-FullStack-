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
import Spring.ctrl.negocio.alunoNegocio;
import Spring.model.entidades.aluno;

@CrossOrigin
@RestController
@RequestMapping(value="/alunos")
public class alunoServico {
	
	@Autowired
	private alunoNegocio alunoNegocio;

	@GetMapping
	public ResponseEntity<List<aluno>> inicio() {
	  List<aluno> list = alunoNegocio.findAll();	
	  return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<aluno> findById(@PathVariable Integer id){
		aluno retorno = null;
		try {
			retorno = alunoNegocio.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(retorno);
		} catch (notFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(retorno);
		}	
	}
	
	@GetMapping(value="/nome/{str}")
	public ResponseEntity<aluno> findByName(@PathVariable String str){
		aluno aluno = null;
		try {
			aluno = alunoNegocio.findByNome(str);
			return ResponseEntity.status(HttpStatus.OK).body(aluno);
		} catch (notFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(aluno);
		}
	}
	
	@PostMapping
	public ResponseEntity<aluno> insert(@RequestBody aluno aluno){
		try {
			aluno = alunoNegocio.insert(aluno);
			return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
		} catch (notFoundException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(aluno);
		}		
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		try {
			alunoNegocio.delete(id);
			return ResponseEntity.noContent().build();
		} catch (notFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<aluno> update(@PathVariable Integer id, @RequestBody aluno aluno){
		try {
			aluno = alunoNegocio.update(aluno);
			return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
		} catch (notFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(aluno);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(aluno);
		}
		
	}

	@GetMapping("/datatable")
    public Page<aluno> search(
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
		return alunoNegocio.search(searchTerm, page, size, order, active);

    }
}
