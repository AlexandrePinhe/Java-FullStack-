package spring.model.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.model.entidades.Carro;

public interface CarroRepositorio extends JpaRepository<Carro, Integer> {

}
