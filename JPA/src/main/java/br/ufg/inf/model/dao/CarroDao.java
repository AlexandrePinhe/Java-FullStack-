package br.ufg.inf.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.ufg.inf.model.entidade.Carro;

public class CarroDao {
	
	EntityManager em;
	
	public CarroDao(EntityManager em) {
		this.em = em;
	}
	

	public List<Carro> lista(){
		return em.createQuery("from Carro", Carro.class).getResultList();
	}
	
	public List<Carro>  procurarPorPlaca(String placa) {
		TypedQuery<Carro> query = em.createQuery("from Carro b WHERE b.placa LIKE :n", Carro.class);
		query.setParameter("n", "%"+placa+"%");
		return query.getResultList();
	}
	
	public Carro inserir(Carro carro) {
		em.getTransaction().begin();
		em.persist(carro);
		em.getTransaction().commit();
		return carro;
	}

	public Carro excluir(Carro carro) {
		em.getTransaction().begin();
		em.remove(carro);
		em.getTransaction().commit();		
		return carro;
	}
	
	public Carro alterar(Carro carro) {
		em.getTransaction().begin();
		em.persist(carro);
		em.getTransaction().commit();
		return carro;
	}

}
