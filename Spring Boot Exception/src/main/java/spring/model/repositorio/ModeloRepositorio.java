package spring.model.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.model.entidades.Modelo;

public interface ModeloRepositorio extends JpaRepository<Modelo, Integer> {
	
	public List<Modelo> findBynomeModelo(String nome);
}
