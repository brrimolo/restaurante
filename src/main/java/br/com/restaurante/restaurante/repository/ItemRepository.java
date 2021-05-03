package br.com.restaurante.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.restaurante.restaurante.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    
}
