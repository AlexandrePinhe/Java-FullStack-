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
import Spring.ctrl.negocio.cursoNegocio;
import Spring.model.entidades.curso;

@CrossOrigin
@RestController
@RequestMapping(value="/cursos")
public class cursoServico {

	@Autowired
	private cursoNegocio negocioCurso;
	
	@GetMapping
	public ResponseEntity<List<curso>> inicio() {
	  List<curso> list = negocioCurso.findAll();	
	  return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<curso> findById(@PathVariable Integer id){
		curso retorno = null;
		try {
			retorno = negocioCurso.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(retorno);
		} catch (notFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(retorno);
		}

	}
	
	@PostMapping
	public ResponseEntity<curso> insert(@RequestBody curso curso){
		try {
			curso = negocioCurso.insert(curso);
			return ResponseEntity.status(HttpStatus.CREATED).body(curso);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(curso);
		}		
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		try {
			negocioCurso.delete(id);
			return ResponseEntity.noContent().build();
		} catch (notFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<curso> update(@PathVariable Integer id, @RequestBody curso curso){
		try {
			curso = negocioCurso.update(curso);
			return ResponseEntity.status(HttpStatus.CREATED).body(curso);
		} catch (notFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(curso);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(curso);
		}
	}

	@GetMapping(value="/filtro/{str}")
	public ResponseEntity<List<curso>> FiltroPorNome(@PathVariable String str){
		List<curso> curso = null;
		try {
			curso = negocioCurso.findAllNome(str);
			return ResponseEntity.status(HttpStatus.OK).body(curso);
		} catch (notFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(curso);		
		}
	}
	
	@GetMapping(value="/ordem")
	public ResponseEntity<List<curso>> findAllOrderNome() {
		
		List<curso> list = negocioCurso.findAllOrderNome();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/cod_disciplina/{id}")
	public ResponseEntity<List<curso>> FiltroPorDisciplina(@PathVariable Integer id){
		List<curso> cursos = null;
		try {
			cursos = negocioCurso.buscaPorDisciplina(id);
			return ResponseEntity.status(HttpStatus.OK).body(cursos);
		} catch (notFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cursos);		
		}
	}
	
	@GetMapping(value="/Id_aluno/{id}")
	public ResponseEntity<List<curso>> FiltroPorAluno(@PathVariable Integer id){
		List<curso> cursos = null;
		try {
			cursos = negocioCurso.buscaPorAluno(id);
			return ResponseEntity.status(HttpStatus.OK).body(cursos);
		} catch (notFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cursos);		
		}
	}
	
	@GetMapping("/datatable")
    public Page<curso> search(
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
		return negocioCurso.search(searchTerm, page, size, order, active);
		}
}
