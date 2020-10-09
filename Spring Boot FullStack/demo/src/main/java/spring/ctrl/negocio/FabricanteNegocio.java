package spring.ctrl.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.model.entidades.Fabricante;
import spring.model.repositorio.FabricanteRepositorio;

@Service
public class FabricanteNegocio {

	@Autowired
	private FabricanteRepositorio repositorio;
	
	public List<Fabricante> findAll(){
		return repositorio.findAll();
	}
	
	public Fabricante findById(Integer id) {
		Optional<Fabricante> retorno = repositorio.findById(id);
		return retorno.get();
	}
	
	public Fabricante insert(Fabricante fabricante) {
		return repositorio.save(fabricante);
	}
	
	public void delete(Integer id) {
		repositorio.deleteById(id);
	}
	
	public Fabricante update(Fabricante fabricante) {
		Fabricante fabricanteUpd = repositorio.getOne(fabricante.getFabricanteId());
		
		fabricanteUpd.setFabricanteNome(fabricante.getFabricanteNome());
		
		return repositorio.save(fabricanteUpd);
	}
}
