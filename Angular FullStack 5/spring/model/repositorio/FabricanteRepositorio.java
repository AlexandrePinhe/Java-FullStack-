package spring.model.repositorio;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.model.entidades.Fabricante;

public interface FabricanteRepositorio extends JpaRepository<Fabricante, Integer> {
	
	public List<Fabricante> findByfabricanteNome(String str);

	@Query("FROM Fabricante c " +
	           "WHERE LOWER(c.fabricanteNome) like %:searchTerm% " +
	           "OR LOWER(c.fabricanteId) like %:searchTerm%")
	public Page<Fabricante> search(
	            @Param("searchTerm") String searchTerm, 
	            Pageable pageable);
}
