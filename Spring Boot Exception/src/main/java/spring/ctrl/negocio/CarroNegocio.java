package spring.ctrl.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.ctrl.excecao.CarroException;
import spring.ctrl.excecao.NotFoundException;
import spring.model.entidades.Carro;
import spring.model.repositorio.CarroRepositorio;

@Service
public class CarroNegocio {
	
	@Autowired
	private CarroRepositorio repositorio;
	
	public List<Carro> findAll(){
		return repositorio.findAll();
	}
	
	public Carro findById(Integer id) throws NotFoundException {
		Optional<Carro> retorno = repositorio.findById(id);
		if(!retorno.isPresent()) {
			throw new NotFoundException("Carro não encontrado!");
		}
		return retorno.get();
	}
		
	public Carro insert(Carro carro) throws CarroException {
		return this.insertOrUpdate(carro) ;
	}
	
	private Carro insertOrUpdate(Carro carro) throws CarroException {
		if(carro.getFabricante() == null || carro.getTipo() == null || carro.getPlaca().isEmpty() || carro.getModelo() == null ) {
			throw new CarroException("Campo(s) faltando!");
		}
		return repositorio.save(carro);
	}
	public void delete(Integer id) throws NotFoundException {
		Optional<Carro> carUpd = repositorio.findById(id);
		if(carUpd.isPresent()) {
			repositorio.delete(carUpd.get());
		}else {
			throw new NotFoundException("Carro não encontrado!");
		}
	}
	
	public Carro update(Carro carro) throws NotFoundException, CarroException {
		Optional<Carro> carUpd = repositorio.findById(carro.getIdCarro());
		
		if(!carUpd.isPresent()) {
			throw new NotFoundException("Carro não encontrado!");
		}
		
		Carro carroUpd = carUpd.get();
		carroUpd.setFabricante(carro.getFabricante());
		carroUpd.setTipo(carro.getTipo());
		carroUpd.setPlaca(carro.getPlaca());
		carroUpd.setAno(carro.getAno());
		carroUpd.setCor(carro.getCor());
		carroUpd.setModelo(carro.getModelo());
		
		return this.insertOrUpdate(carroUpd);
	}	
}
