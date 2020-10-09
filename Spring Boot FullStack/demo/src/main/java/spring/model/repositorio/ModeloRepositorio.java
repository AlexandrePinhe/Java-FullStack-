package spring.model.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.model.entidades.Modelo;

public interface ModeloRepositorio extends JpaRepository<Modelo, Integer> {

}
