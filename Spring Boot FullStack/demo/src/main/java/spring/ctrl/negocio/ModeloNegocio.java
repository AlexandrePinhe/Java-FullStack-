package spring.ctrl.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.model.entidades.Modelo;
import spring.model.repositorio.ModeloRepositorio;

@Service
public class ModeloNegocio {

	@Autowired
	private ModeloRepositorio repositorio;
	
	public List<Modelo> findAll(){
		return repositorio.findAll();
	}
	
	public Modelo findById(Integer id) {
		Optional<Modelo> retorno = repositorio.findById(id);
		return retorno.get();
	}
	
	public Modelo insert(Modelo modelo) {	
		return repositorio.save(modelo);
	}
	
	public void delete(Integer id) {
		repositorio.deleteById(id);
	}
	
	public Modelo update(Modelo modelo) {
		Modelo modeloUpd = repositorio.getOne(modelo.getModeloId());
		
		modeloUpd.setNomeModelo(modelo.getNomeModelo());
		modeloUpd.setFabricante(modelo.getFabricante());
		
		return repositorio.save(modeloUpd);
	}
}
