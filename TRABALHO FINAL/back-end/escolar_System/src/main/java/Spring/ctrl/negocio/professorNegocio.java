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
import Spring.model.entidades.professor;
import Spring.model.repositorio.professorRepositorio;

@Service
public class professorNegocio {

	@Autowired
	private professorRepositorio repositorio;
	
	public List<professor> findAll(){
		return repositorio.findAll();
	}
	
	public professor findById(Integer id) throws notFoundException {
		Optional<professor> retorno = repositorio.findById(id);
		if(!retorno.isPresent()) {
			throw new notFoundException("Professor não encontrado!");
		}
		return retorno.get();
	}
	
	public professor insert(professor professor) throws notFoundException {
		return this.insertOrUpdate(professor);
	}
	private professor insertOrUpdate(professor professor ) throws notFoundException {
		if(professor.getNome().length() == 0) {
			throw new notFoundException("Campo Nome do professor faltando!");
		}
		
		return repositorio.save(professor);
	}
	
	public professor update(professor professor) throws notFoundException {
		Optional<professor> profUpd = repositorio.findById(professor.getMatricula());
		if(!profUpd.isPresent()) {
			throw new notFoundException("Professor não encontrado!");
		}else {
			professor professorUpd = profUpd.get();
			professorUpd.setNome(professor.getNome());
			return this.insertOrUpdate(professorUpd);
		}
	}
	
	public void delete(Integer id) throws notFoundException {
		Optional<professor> professorDelete = repositorio.findById(id);
		if(professorDelete.isPresent()) {
			repositorio.delete(professorDelete.get());
		}else {
			throw new notFoundException("Professor não encontrado!");
		}
	}
	
	public professor findByNome(String nome) throws notFoundException {
		List<professor> professor = repositorio.findBynome(nome);
		if(professor.size() > 0) {
			return professor.get(0);
		}else {
			 throw new notFoundException("Professor não encontrado!");
		}
	}
	
	public Page<professor> search(String searchTerm, int page, int size, String order, String active) {
		PageRequest pageRequest = PageRequest.of(
        		page, 
        		size, 
        		(order.contentEquals("desc")) ? Sort.Direction.DESC : Sort.Direction.ASC, 
        		active);
        return repositorio.search(searchTerm.toLowerCase(), pageRequest);
	}
	
	public Page<professor> findAllPages() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
        return new PageImpl<>(repositorio.findAll(), pageRequest, size);
    }

	
}
