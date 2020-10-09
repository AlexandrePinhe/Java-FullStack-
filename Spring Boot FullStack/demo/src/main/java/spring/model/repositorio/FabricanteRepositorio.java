package spring.model.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;

import spring.model.entidades.Fabricante;

public interface FabricanteRepositorio extends JpaRepository<Fabricante, Integer> {
    
}
