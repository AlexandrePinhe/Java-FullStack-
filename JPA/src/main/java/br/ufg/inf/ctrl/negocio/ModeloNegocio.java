package br.ufg.inf.ctrl.negocio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.ufg.inf.ctrl.excecao.FabricanteException;
import br.ufg.inf.model.dao.ModeloDao;
import br.ufg.inf.model.entidade.Modelo;

public class ModeloNegocio {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("aulajpa");
	EntityManager em = emf.createEntityManager();
	ModeloDao dao = new ModeloDao(em);
	
	public List<Modelo> listar() throws FabricanteException{
		return dao.lista();
	}
	
	public Modelo buscaPorId(int id) throws FabricanteException {
		return dao.procurarPorId(id);
	}
	
	public Modelo inserir(Modelo modelo) throws FabricanteException {
		return dao.inserir(modelo);
	}
	
	public Modelo excluir(Modelo modelo) throws FabricanteException {
		modelo = dao.procurarPorId(modelo.getModeloId());
		return dao.excluir(modelo);
	}

	public void encerrarConexao() {
		emf.close();
		em.close();
	}
	
}
