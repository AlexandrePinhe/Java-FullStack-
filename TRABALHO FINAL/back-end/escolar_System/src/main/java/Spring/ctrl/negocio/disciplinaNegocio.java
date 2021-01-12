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
import Spring.model.entidades.disciplina;
import Spring.model.repositorio.disciplinaRepositorio;

@Service
public class disciplinaNegocio {

	@Autowired
	private disciplinaRepositorio repositorio;
	
	public List<disciplina> findAll(){
		return repositorio.findAll();
	}
	
	public disciplina findById(Integer id) throws notFoundException {
		Optional<disciplina> retorno = repositorio.findById(id);
		if(!retorno.isPresent()) {
			throw new notFoundException("Disciplina não encontrada!");
		}
		return retorno.get();
	}
	
	public disciplina insert(disciplina disciplina) throws notFoundException {
		return this.insertOrUpdate(disciplina);
	}
	
	private disciplina insertOrUpdate(disciplina disciplina ) throws notFoundException {
		if(disciplina.getNome().length() == 0) {
			throw new notFoundException("Campo Nome da Disciplina faltando!");
		}
		
		if(disciplina.getQtde_Alunos() == 0) {
			throw new notFoundException("Quantidade de alunos inválida!");
		}
		
		return repositorio.save(disciplina);
	}
	
	public disciplina update(disciplina disciplina) throws notFoundException {
		Optional<disciplina> disciplinaUpd = repositorio.findById(disciplina.getID());
		if(!disciplinaUpd.isPresent()) {
			throw new notFoundException("Disciplina não encontrada!");
		}else {
			disciplina discUpd = disciplinaUpd.get();
			discUpd.setNome(disciplina.getNome());
			discUpd.setQtde_Alunos(disciplina.getQtde_Alunos());
			return this.insertOrUpdate(discUpd);
		}
	}
	
	public void delete(Integer id) throws notFoundException {
		Optional<disciplina> disciplinaDelete = repositorio.findById(id);
		if(disciplinaDelete.isPresent()) {
			repositorio.delete(disciplinaDelete.get());
		}else {
			throw new notFoundException("Disciplina não encontrada!");
		}
	}
	
	public disciplina findByNome(String nome) throws notFoundException {
		List<disciplina> disciplina = repositorio.findBynome(nome);
		if(disciplina.size() > 0) {
			return disciplina.get(0);
		}else {
			 throw new notFoundException("Disciplina não encontrada!");
		}
	}
	
	public Page<disciplina> search(String searchTerm, int page, int size, String order, String active) {
		PageRequest pageRequest = PageRequest.of(
        		page, 
        		size, 
        		(order.contentEquals("desc")) ? Sort.Direction.DESC : Sort.Direction.ASC, 
        		active);
        return repositorio.search(searchTerm.toLowerCase(), pageRequest);
	}
	
	public Page<disciplina> findAllPages() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
        return new PageImpl<>(repositorio.findAll(), pageRequest, size);
    }
}
