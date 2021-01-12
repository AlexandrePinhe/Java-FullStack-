package Spring.model.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Spring.model.entidades.aluno;


public interface alunoRepositorio  extends JpaRepository<aluno, Integer> {

	public List<aluno> findBynome(String str);
	
	@Query("FROM aluno c " +
	           "WHERE LOWER(c.nome) like %:searchTerm% " +
	           "OR LOWER(c.id) like %:searchTerm%")
	public Page<aluno> search(
	            @Param("searchTerm") String searchTerm, 
	            Pageable pageable);
}
