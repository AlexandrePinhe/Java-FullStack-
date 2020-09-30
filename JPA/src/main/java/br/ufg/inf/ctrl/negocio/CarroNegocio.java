package br.ufg.inf.ctrl.negocio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.ufg.inf.ctrl.excecao.CarroException;
import br.ufg.inf.model.dao.CarroDao;
import br.ufg.inf.model.entidade.Carro;

public class CarroNegocio {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("aulajpa");
	EntityManager em = emf.createEntityManager();
	CarroDao dao = new CarroDao(em);
		
	public List<Carro> listar() throws CarroException{
		return dao.lista();
	}
	
	public Carro procurarPorPlaca(String placa) throws CarroException {
		
		if(placa.length() != 8) {
			throw new CarroException("Placa de ter 8 caracteres.");
		}
		
		return (Carro) dao.procurarPorPlaca(placa);
	}
	
	public Carro inserir(Carro carro) throws CarroException {
		if(carro.getPlaca().length() != 8) {
			throw new CarroException("Placa de ter 8 caracteres.");
		}
		return dao.inserir(carro);
	}
	
	public Carro excluir(Carro carro) {
		return dao.excluir(carro);
	}

	public Carro alterar(Carro carro) throws CarroException {
		if(carro.getPlaca().length() != 8) {
			throw new CarroException("Placa de ter 8 caracteres.");
		}
		return dao.alterar(carro);
	}
	
	public void encerrarConexao() {
		emf.close();
		em.close();
	}
}
