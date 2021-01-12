package Spring.ctrl.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Spring.ctrl.excecao.notFoundException;
import Spring.model.entidades.curso;
import Spring.model.entidades.departamento;
import Spring.model.entidades.professor;
import Spring.model.repositorio.cursoRepositorio;
import Spring.model.repositorio.departamentoRepositorio;
import Spring.model.repositorio.professorRepositorio;

@Service
public class departamentoNegocio {

	@Autowired
	private departamentoRepositorio repositorioDepartamento;
	
	@Autowired
	private professorRepositorio repositorioProfessor;
	
	@Autowired
	private cursoRepositorio repositorioCurso;
	
	public List<departamento> findAll(){
		return repositorioDepartamento.findAll();
	}
	public departamento findById(Integer id) throws notFoundException {
		Optional<departamento> retorno = repositorioDepartamento.findById(id);
		if(!retorno.isPresent()) {
			throw new notFoundException("Departamento não encontrado!");
		}
		return retorno.get();
	}
	
	public List<departamento> buscaPorProfessor(Integer id) throws notFoundException{
		Optional<professor> retorno = repositorioProfessor.findById(id);
		if(!retorno.isPresent()) {
			throw new notFoundException("professor com id "+id+" não encontrada!");
		}
		return repositorioDepartamento.findByprofessor(retorno.get());
	}
	
	public List<departamento> buscarPorCurso(Integer id) throws notFoundException{
		Optional<curso> retorno = repositorioCurso.findById(id);
		if(!retorno.isPresent()) {
			throw new notFoundException("Curso com id "+id+" não encontrada!");
		}
		return repositorioDepartamento.findBycurso(retorno.get());
	}
	
	public departamento insert(departamento departamentoInsert) throws notFoundException {			
		return this.insertOrUpdate(departamentoInsert);
	}
	
	private departamento insertOrUpdate(departamento departamento ) throws notFoundException {
		if(departamento.getNome().isEmpty() || departamento.getMatricula_prof() == null
				|| departamento.getCod_curso() == null) {
			throw new notFoundException("Campo(s) faltando");
		}
		return repositorioDepartamento.save(departamento);
	}
	
	public void delete(Integer id) throws notFoundException {
		Optional<departamento> departamentoDelete = repositorioDepartamento.findById(id);
		if(departamentoDelete.isPresent()) {
			repositorioDepartamento.delete(departamentoDelete.get());
		}else {
			throw new notFoundException("Departamento não encontrado!");
		}
	}
	
	public departamento update(departamento departamento) throws notFoundException {
		Optional<departamento> departamentoUpd = repositorioDepartamento.findById(departamento.getId());
		
		if(!departamentoUpd.isPresent()) {
			throw new notFoundException("Departamento não encontrado!");
		}
		departamento deptUpd = departamentoUpd.get();
		deptUpd.setNome(departamento.getNome());
		deptUpd.setData_contratacao(departamento.getData_contratacao());
		deptUpd.setMatricula_prof(departamento.getMatricula_prof());
		deptUpd.setCod_curso(departamento.getCod_curso());
		deptUpd.setTipo_departamento(departamento.getTipo_departamento());
		deptUpd.setDepartamento_ativo(departamento.getDepartamento_ativo());
		return this.insertOrUpdate(deptUpd);
	}
	
	public departamento findByNome(String nome) throws notFoundException {
		List<departamento> departamentos = repositorioDepartamento.findBynome(nome);
		if(departamentos.size() > 0) {
			return departamentos.get(0);
		}else {
			 throw new notFoundException("Departamento não encontrado!");
		}
	}
	
	public List<departamento> findAllNome(String str) throws notFoundException {
		List<departamento> list = repositorioDepartamento.findBynomeContains(str);
		if(list.size() > 0) {
			return list;
		}else {
			throw new notFoundException("Departamento com a ocorrência '"+str+"' não encontrado");
		}
	}
	
	public List<departamento> findAllOrderNome(){
		return repositorioDepartamento.findAllOrderNOme();
	}
	
	public Page<departamento> search(String searchTerm, int page, int size, String order, String active) {
		PageRequest pageRequest = PageRequest.of(
        		page, 
        		size, 
        		(order.contentEquals("desc")) ? Sort.Direction.DESC : Sort.Direction.ASC, 
        		active);
        return repositorioDepartamento.search(searchTerm.toLowerCase(), pageRequest);
	}
	
	public Page<departamento> findAllPages() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
        return new PageImpl<>(repositorioDepartamento.findAll(), pageRequest, size);
	}
}
