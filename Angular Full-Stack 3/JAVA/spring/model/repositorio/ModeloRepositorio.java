package spring.model.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.model.entidades.Fabricante;
import spring.model.entidades.Modelo;

public interface ModeloRepositorio extends JpaRepository<Modelo, Integer>{
	
	public List<Modelo> findBynomeModelo(String nome);
	public List<Modelo> findBynomeModeloContains(String nome);
	
	
	public List<Modelo> findByfabricante(Fabricante fabricante);
	
	@Query(value = "SELECT b FROM Modelo b ORDER BY b.nomeModelo")
	public List<Modelo> findAllOrderNOme();
	
	@Query("FROM Modelo c " +
	           "WHERE LOWER(c.nomeModelo) like %:searchTerm% " +
	           "OR LOWER(c.modeloId) like %:searchTerm%")
	public Page<Modelo> search(
	            @Param("searchTerm") String searchTerm, 
	            Pageable pageable);
}
