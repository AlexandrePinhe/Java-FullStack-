package Spring.model.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Spring.model.entidades.disciplina;

public interface disciplinaRepositorio  extends JpaRepository<disciplina, Integer>  {

public List<disciplina> findBynome(String str);
	
	@Query("FROM disciplina c " +
	           "WHERE LOWER(c.nome) like %:searchTerm% " +
	           "OR LOWER(c.ID) like %:searchTerm%")
	public Page<disciplina> search(
	            @Param("searchTerm") String searchTerm, 
	            Pageable pageable);
}
