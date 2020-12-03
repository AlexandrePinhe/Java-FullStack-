package spring.model.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.model.entidades.Carro;
import spring.model.entidades.Fabricante;
import spring.model.entidades.Modelo;

public interface CarroRepositorio extends JpaRepository<Carro, Integer> {

	public List<Carro> findByPlaca(String placa);
	public List<Carro> findByPlacaContains(String placa);
	
    public List<Carro> findByfabricante(Fabricante fabricante);
    public List<Carro> findBymodelo(Modelo modelo);
    
	@Query(value = "SELECT c FROM Carro c ORDER BY c.placa")
	public List<Carro> findAllOrderNOme();
	
	@Query("FROM Carro c " +
	           "WHERE LOWER(c.placa) like %:searchTerm% " +
	           "OR LOWER(c.idCarro) like %:searchTerm%")
	public Page<Carro> search(
	            @Param("searchTerm") String searchTerm, 
	            Pageable pageable);
}
