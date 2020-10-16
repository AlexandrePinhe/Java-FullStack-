package spring.ctrl.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.ctrl.excecao.ModeloException;
import spring.ctrl.excecao.NotFoundException;
import spring.model.entidades.Modelo;
import spring.model.repositorio.ModeloRepositorio;

@Service
public class ModeloNegocio {

	@Autowired
	private ModeloRepositorio repositorio;
	
	public List<Modelo> findAll(){
		return repositorio.findAll();
	}
	
	public Modelo findById(Integer id) throws NotFoundException {
		Optional<Modelo> retorno = repositorio.findById(id);
		if(!retorno.isPresent()) {
			throw new NotFoundException("Modelo não encontrado!");
		}
		return retorno.get();
	}
	
	public Modelo insert(Modelo modelo) throws ModeloException {			
		return this.insertOrUpdate(modelo);
	}
	
	private Modelo insertOrUpdate(Modelo modelo ) throws ModeloException {
		if(modelo.getNomeModelo().isEmpty() || modelo.getFabricante() == null) {
			throw new ModeloException("Campo(s) faltando");
		}
		return repositorio.save(modelo);
	}
	
	public void delete(Integer id) throws NotFoundException {
		Optional<Modelo> modUpd = repositorio.findById(id);
		if(modUpd.isPresent()) {
			repositorio.delete(modUpd.get());
		}else {
			throw new NotFoundException("Modelo não encontrado!");
		}
	}
	
	public Modelo update(Modelo modelo) throws NotFoundException, ModeloException {
		Optional<Modelo> modUpd = repositorio.findById(modelo.getModeloId());
		
		if(!modUpd.isPresent()) {
			throw new NotFoundException("Modelo não encontrado!");
		}
		Modelo modeloUpd = modUpd.get();
		modeloUpd.setNomeModelo(modelo.getNomeModelo());
		modeloUpd.setFabricante(modelo.getFabricante());
		
		return this.insertOrUpdate(modeloUpd);
	}
	
	public Modelo findByNome(String nome) throws ModeloException {
		List<Modelo> modelos = repositorio.findBynomeModelo(nome);
		if(modelos.size() > 0) {
			return modelos.get(0);
		}else {
			 throw new ModeloException("Modelo não encontrado!");
		}
	}
}
