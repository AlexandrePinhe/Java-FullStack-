package spring.model.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.model.entidades.Fabricante;

public interface FabricanteRepositorio extends JpaRepository<Fabricante, Integer> {
	
	public List<Fabricante> findByfabricanteNome(String str);
}
