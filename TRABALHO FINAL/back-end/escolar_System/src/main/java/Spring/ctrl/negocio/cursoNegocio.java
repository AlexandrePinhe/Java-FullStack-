package Spring.ctrl.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Spring.model.repositorio.alunoRepositorio;
import Spring.model.repositorio.cursoRepositorio;
import Spring.model.repositorio.disciplinaRepositorio;
import Spring.ctrl.excecao.notFoundException;
import Spring.model.entidades.aluno;
import Spring.model.entidades.curso;
import Spring.model.entidades.disciplina;

@Service
public class cursoNegocio {

	@Autowired
	private cursoRepositorio repositorioCurso;
	
	@Autowired
	private disciplinaRepositorio repositorioDisciplina;
	
	@Autowired
	private alunoRepositorio repositorioAluno;
	
	public List<curso> findAll(){
		return repositorioCurso.findAll();
	}
	
	public curso findById(Integer id) throws notFoundException {
		Optional<curso> retorno = repositorioCurso.findById(id);
		if(!retorno.isPresent()) {
			throw new notFoundException("Curso não encontrado!");
		}
		return retorno.get();
	}
	
	public List<curso> buscaPorDisciplina(Integer id) throws notFoundException{
		Optional<disciplina> retorno = repositorioDisciplina.findById(id);
		if(!retorno.isPresent()) {
			throw new notFoundException("Disciplina com id "+id+" não encontrada!");
		}
		return repositorioCurso.findBydisciplina(retorno.get());
	}
	
	public List<curso> buscaPorAluno(Integer id) throws notFoundException{
		Optional<aluno> retorno = repositorioAluno.findById(id);
		if(!retorno.isPresent()) {
			throw new notFoundException("Aluno com id "+id+" não encontrado!");
		}
		return repositorioCurso.findByaluno(retorno.get());
	}
	
	public curso insert(curso cursoInsert) throws notFoundException {			
		return this.insertOrUpdate(cursoInsert);
	}
	
	private curso insertOrUpdate(curso curso ) throws notFoundException {
		if(curso.getNome().isEmpty() || curso.getCod_disciplina() == null
				|| curso.getId_aluno() == null) {
			throw new notFoundException("Campo(s) faltando");
		}
		return repositorioCurso.save(curso);
	}
	
	public void delete(Integer id) throws notFoundException {
		Optional<curso> CursoDelete = repositorioCurso.findById(id);
		if(CursoDelete.isPresent()) {
			repositorioCurso.delete(CursoDelete.get());
		}else {
			throw new notFoundException("Curso não encontrado!");
		}
	}
	
	public curso update(curso curso) throws notFoundException {
		Optional<curso> cursoUpd = repositorioCurso.findById(curso.getId());
		
		if(!cursoUpd.isPresent()) {
			throw new notFoundException("Curso não encontrado!");
		}
		curso cursUpd = cursoUpd.get();
		cursUpd.setNome(curso.getNome());
		cursUpd.setCod_disciplina(curso.getCod_disciplina());
		cursUpd.setData_matricula(curso.getData_matricula());
		cursUpd.setId_aluno(curso.getId_aluno());
		return this.insertOrUpdate(cursUpd);
	}
	
	public curso findByNome(String nome) throws notFoundException {
		List<curso> cursos = repositorioCurso.findBynome(nome);
		if(cursos.size() > 0) {
			return cursos.get(0);
		}else {
			 throw new notFoundException("Curso não encontrado!");
		}
	}
	
	public List<curso> findAllNome(String str) throws notFoundException {
		List<curso> list = repositorioCurso.findBynomeContains(str);
		if(list.size() > 0) {
			return list;
		}else {
			throw new notFoundException("Curso com a ocorrência '"+str+"' não encontrado");
		}
	}
	
	public List<curso> findAllOrderNome(){
		return repositorioCurso.findAllOrderNOme();
	}
	
	public Page<curso> search(String searchTerm, int page, int size, String order, String active) {
		PageRequest pageRequest = PageRequest.of(
        		page, 
        		size, 
        		(order.contentEquals("desc")) ? Sort.Direction.DESC : Sort.Direction.ASC, 
        		active);
        return repositorioCurso.search(searchTerm.toLowerCase(), pageRequest);
	}
	
	public Page<curso> findAllPages() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
        return new PageImpl<>(repositorioCurso.findAll(), pageRequest, size);
	}
}