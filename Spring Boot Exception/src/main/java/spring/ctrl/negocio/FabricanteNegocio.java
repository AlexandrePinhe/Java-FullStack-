package spring.ctrl.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.ctrl.excecao.FabricanteException;
import spring.ctrl.excecao.NotFoundException;
import spring.model.entidades.Fabricante;
import spring.model.repositorio.FabricanteRepositorio;

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
}
