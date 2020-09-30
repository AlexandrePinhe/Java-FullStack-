package br.ufg.inf.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import br.ufg.inf.model.entidade.Modelo;

public class ModeloDao {
	
    EntityManager em;
	
	public ModeloDao(EntityManager em) {
		this.em = em;
	}
	
	
	public List<Modelo> lista(){
		return em.createQuery("from Modelo", Modelo.class).getResultList();
	}
	
	public Modelo procurarPorId(int id) {
		return em.find(Modelo.class, id);
	}
	
	public Modelo inserir(Modelo modelo) {
		em.getTransaction().begin();
		em.persist(modelo);
		em.getTransaction().commit();
		return modelo;
	}

	public Modelo excluir(Modelo modelo) {
		em.getTransaction().begin();
		em.remove(modelo);
		em.getTransaction().commit();	
		return modelo;
	}
	
	

}