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
import Spring.ctrl.excecao.notFoundException;
import Spring.model.entidades.aluno;

@Service
public class alunoNegocio {

	@Autowired
	private alunoRepositorio repositorio;
	
	public List<aluno> findAll(){
		return repositorio.findAll();
	}
	
	public aluno findById(Integer id) throws notFoundException {
		Optional<aluno> retorno = repositorio.findById(id);
		if(!retorno.isPresent()) {
			throw new notFoundException("Aluno não encontrado!");
		}
		return retorno.get();
	}
	
	public aluno insert(aluno aluno) throws notFoundException {
		return this.insertOrUpdate(aluno);
	}
	private aluno insertOrUpdate(aluno aluno ) throws notFoundException {
		
		if(aluno.getCpf() == 0) {
			throw new notFoundException("Campo CPF do aluno faltando!");
		}
		
		if(aluno.getNome().length() == 0) {
			throw new notFoundException("Campo Nome do aluno faltando!");
		}
		
		if(aluno.getEndereco().length() == 0) {
			throw new notFoundException("Campo Endereço do aluno faltando!");
		}
		return repositorio.save(aluno);
	}
	
	public aluno update(aluno aluno) throws notFoundException {
		Optional<aluno> alunoUpd = repositorio.findById(aluno.getId());
		if(!alunoUpd.isPresent()) {
			throw new notFoundException("Aluno não encontrado!");
		}else {
			aluno alUpd = alunoUpd.get();
			alUpd.setCpf(aluno.getCpf());
			alUpd.setNome(aluno.getNome());
			alUpd.setEndereco(aluno.getEndereco());
			return this.insertOrUpdate(alUpd);
		}
	}
	
	public void delete(Integer id) throws notFoundException {
		Optional<aluno> alunoDelete = repositorio.findById(id);
		if(alunoDelete.isPresent()) {
			repositorio.delete(alunoDelete.get());
		}else {
			throw new notFoundException("Aluno não encontrado!");
		}
	}
	
	public aluno findByNome(String nome) throws notFoundException {
		List<aluno> aluno = repositorio.findBynome(nome);
		if(aluno.size() > 0) {
			return aluno.get(0);
		}else {
			 throw new notFoundException("Aluno não encontrado!");
		}
	}
	
	public Page<aluno> search(String searchTerm, int page, int size, String order, String active) {
		PageRequest pageRequest = PageRequest.of(
        		page, 
        		size, 
        		(order.contentEquals("desc")) ? Sort.Direction.DESC : Sort.Direction.ASC, 
        		active);
        return repositorio.search(searchTerm.toLowerCase(), pageRequest);
	}
	
	public Page<aluno> findAllPages() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
        return new PageImpl<>(repositorio.findAll(), pageRequest, size);
    }
}
