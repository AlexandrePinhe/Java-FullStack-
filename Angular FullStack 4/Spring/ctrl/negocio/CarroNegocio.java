package spring.ctrl.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import spring.ctrl.excecao.CarroException;
import spring.ctrl.excecao.ModeloException;
import spring.ctrl.excecao.NotFoundException;
import spring.model.entidades.Carro;
import spring.model.entidades.Fabricante;
import spring.model.entidades.Modelo;
import spring.model.repositorio.CarroRepositorio;
import spring.model.repositorio.FabricanteRepositorio;
import spring.model.repositorio.ModeloRepositorio;

@Service
public class CarroNegocio {
	
	@Autowired
	private CarroRepositorio repositorio;
	
	@Autowired
	private ModeloRepositorio modeloRepositorio;
	
	@Autowired
	private FabricanteRepositorio fabricanteRepositorio;
	
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
	
	public Carro findByPlaca(String placa) throws ModeloException {
		List<Carro> carros = repositorio.findByPlaca(placa);
		if(carros.size() > 0) {
			return carros.get(0);
		}else {
			 throw new ModeloException("Carro não encontrado!");
		}
	}
	
	public List<Carro> findAllPlaca(String str) throws NotFoundException {
		List<Carro> list = repositorio.findByPlacaContains(str);
		if(list.size() > 0) {
			return list;
		}else {
			throw new NotFoundException("Carro com a ocorrência '"+str+"' não encontrado");
		}
	}
	
	public List<Carro> findAllOrderNome(){
		return repositorio.findAllOrderNOme();
	}
	
	public List<Carro> buscaPorFabricante(Integer id) throws NotFoundException{
		Optional<Fabricante> retorno = fabricanteRepositorio.findById(id);
		if(!retorno.isPresent()) {
			throw new NotFoundException("Carro com id "+id+" não encontrado");
		}
		return repositorio.findByfabricante(retorno.get());
	}
	
	public List<Carro> buscaPorModelo(Integer id) throws NotFoundException{
		Optional<Modelo> retorno = modeloRepositorio.findById(id);
		if(!retorno.isPresent()) {
			throw new NotFoundException("Modelo com id "+id+" não encontrado");
		}
		return repositorio.findBymodelo(retorno.get());
	}
	
	public Page<Carro> search(String searchTerm, int page, int size, String order, String active) {
		PageRequest pageRequest = PageRequest.of(
        		page, 
        		size, 
        		(order.contentEquals("desc")) ? Sort.Direction.DESC : Sort.Direction.ASC, 
        		active);
        return repositorio.search(searchTerm.toLowerCase(), pageRequest);
	}
}
