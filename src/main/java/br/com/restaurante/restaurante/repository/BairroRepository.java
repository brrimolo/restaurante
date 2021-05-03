package br.com.restaurante.restaurante.repository;

import br.com.restaurante.restaurante.model.Bairro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BairroRepository extends JpaRepository<Bairro,Long> {
    
}
