package spring.ctrl.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import spring.ctrl.excecao.FabricanteException;
import spring.ctrl.excecao.NotFoundException;
import spring.model.entidades.Fabricante;
import spring.model.repositorio.FabricanteRepositorio;
import org.springframework.data.domain.Sort;

@Service
public class FabricanteNegocio {

	@Autowired
	private FabricanteRepositorio repositorio;
	
	public List<Fabricante> findAll(){
		return repositorio.findAll();
	}
	
	public Fabricante findById(Integer id) throws NotFoundException {
		Optional<Fabricante> retorno = repositorio.findById(id);
		if(!retorno.isPresent()) {
			throw new NotFoundException("fabricante não encontrado!");
		}
		return retorno.get();
	}
	
	public Fabricante insert(Fabricante fabricante) throws FabricanteException {
		return this.insertOrUpdate(fabricante);
	}
	
	private Fabricante insertOrUpdate(Fabricante fabricante ) throws FabricanteException {
		if(fabricante.getFabricanteNome().length() == 0) {
			throw new FabricanteException("Campo Nome do fabricante faltando!");
		}
		return repositorio.save(fabricante);
	}
	
	public Fabricante update(Fabricante fabricante) throws NotFoundException, FabricanteException {
		Optional<Fabricante> fbUpd = repositorio.findById(fabricante.getFabricanteId());
		if(!fbUpd.isPresent()) {
			throw new NotFoundException("fabricante não encontrado!");
		}else {
			Fabricante fabricanteUpd = fbUpd.get();
			fabricanteUpd.setFabricanteNome(fabricante.getFabricanteNome());
			return this.insertOrUpdate(fabricanteUpd);
		}
	}
	
	public void delete(Integer id) throws NotFoundException {
		Optional<Fabricante> fbUpd = repositorio.findById(id);
		if(fbUpd.isPresent()) {
			repositorio.delete(fbUpd.get());
		}else {
			throw new NotFoundException("fabricante não encontrado!");
		}
	}
	
	public Fabricante findByNome(String nome) throws FabricanteException {
		List<Fabricante> fabs = repositorio.findByfabricanteNome(nome);
		if(fabs.size() > 0) {
			return fabs.get(0);
		}else {
			 throw new FabricanteException("Fabricante não encontrado!");
		}
	}

	public Page<Fabricante> search(String searchTerm, int page, int size, String order, String active) {
		PageRequest pageRequest = PageRequest.of(
        		page, 
        		size, 
        		(order.contentEquals("desc")) ? Sort.Direction.DESC : Sort.Direction.ASC, 
        		active);
        return repositorio.search(searchTerm.toLowerCase(), pageRequest);
	}
	
	public Page<Fabricante> findAllPages() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "fabricanteNome");
        return new PageImpl<>(repositorio.findAll(), pageRequest, size);
    }
}
