package spring.ctrl.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.model.entidades.Carro;
import spring.model.repositorio.CarroRepositorio;

@Service
public class CarroNegocio {
	
	@Autowired
	private CarroRepositorio repositorio;
	
	public List<Carro> findAll(){
		return repositorio.findAll();
	}
	
	public Carro findById(Integer id) {
		Optional<Carro> retorno = repositorio.findById(id);
		return retorno.get();
	}
		
	public Carro insert(Carro carro) {
		return repositorio.save(carro);
	}
	
	public void delete(Integer id) {
		repositorio.deleteById(id);
	}
	
	public Carro update(Carro carro) {
		Carro carroUpd = repositorio.getOne(carro.getIdCarro());
		
		carroUpd.setFabricante(carro.getFabricante());
		carroUpd.setTipo(carro.getTipo());
		carroUpd.setPlaca(carro.getPlaca());
		carroUpd.setAno(carro.getAno());
		carroUpd.setCor(carro.getCor());
		carroUpd.setModelo(carro.getModelo());
		
		return repositorio.save(carroUpd);
	}	
}
