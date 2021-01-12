package Spring.model.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Spring.model.entidades.curso;
import Spring.model.entidades.departamento;
import Spring.model.entidades.professor;

public interface departamentoRepositorio extends JpaRepository<departamento, Integer> {

	public List<departamento> findBynome(String nome);	
	public List<departamento> findBynomeContains(String nome);
	
	public List<departamento> findByprofessor(professor professor);
	public List<departamento> findBycurso(curso curso);
	
	@Query(value = "SELECT b FROM departamento b ORDER BY b.nome")
	public List<departamento> findAllOrderNOme();
	
	@Query("FROM departamento c " +
	           "WHERE LOWER(c.nome) like %:searchTerm% " +
	           "OR LOWER(c.id) like %:searchTerm%")
	public Page<departamento> search(
	            @Param("searchTerm") String searchTerm, 
	            Pageable pageable);
}
