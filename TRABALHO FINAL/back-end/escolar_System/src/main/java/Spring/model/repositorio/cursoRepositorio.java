package Spring.model.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Spring.model.entidades.aluno;
import Spring.model.entidades.curso;
import Spring.model.entidades.disciplina;

public interface cursoRepositorio extends JpaRepository<curso, Integer> {

	public List<curso> findBynome(String nome);
	
    public List<curso> findBynomeContains(String nome);
	
	public List<curso> findBydisciplina(disciplina disciplina);
	
	public List<curso> findByaluno(aluno aluno);
	
	@Query(value = "SELECT b FROM curso b ORDER BY b.nome")
	public List<curso> findAllOrderNOme();
	
	@Query("FROM curso c " +
	           "WHERE LOWER(c.nome) like %:searchTerm% " +
	           "OR LOWER(c.id) like %:searchTerm%")
	public Page<curso> search(
	            @Param("searchTerm") String searchTerm, 
	            Pageable pageable);
}