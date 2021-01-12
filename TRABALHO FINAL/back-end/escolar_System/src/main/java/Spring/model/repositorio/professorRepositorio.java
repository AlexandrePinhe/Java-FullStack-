package Spring.model.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Spring.model.entidades.professor;

public interface professorRepositorio extends JpaRepository<professor, Integer> {

	public List<professor> findBynome(String str);
	
	@Query("FROM professor c " +
	           "WHERE LOWER(c.nome) like %:searchTerm% " +
	           "OR LOWER(c.matricula) like %:searchTerm%")
	public Page<professor> search(
	            @Param("searchTerm") String searchTerm, 
	            Pageable pageable);
}
