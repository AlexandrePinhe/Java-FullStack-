package br.ufg.inf.ctrl.negocio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.ufg.inf.ctrl.excecao.FabricanteException;
import br.ufg.inf.model.dao.FabricanteDao;
import br.ufg.inf.model.entidade.Fabricante;

public class FabricanteNegocio {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("aulajpa");
	EntityManager em = emf.createEntityManager();
	FabricanteDao dao = new FabricanteDao(em);
	
	public List<Fabricante> listar() throws FabricanteException{
		return dao.lista();
	}
	
	public Fabricante buscaPorId(int id) throws FabricanteException {
		return dao.procurarPorId(id);
	}
	
	public Fabricante inserir(Fabricante fabricante) throws FabricanteException {
		return dao.inserir(fabricante);
	}
	
	public Fabricante excluir(Fabricante fabricante) throws FabricanteException {
		fabricante = dao.procurarPorId(fabricante.getFabricanteId());
		return dao.excluir(fabricante);
	}

	public Fabricante alterar(Fabricante fabricante) throws FabricanteException {
		return dao.alterar(fabricante);
	}
	public List<Fabricante> buscarPorNome(String str){
		return dao.buscarNome(str);
	}
	
	public void encerrarConexao() {
		emf.close();
		em.close();
	}
}
