package spring.model.repositorio;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.model.entidades.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

	@Query("FROM Usuario u " +
	           "WHERE LOWER(u.nomeUsuario) like %:searchTerm% " +
	           "OR LOWER(u.login) like %:searchTerm%")
	 Page<Usuario> search(
	            @Param("searchTerm") String searchTerm, 
	            Pageable pageable);
}
