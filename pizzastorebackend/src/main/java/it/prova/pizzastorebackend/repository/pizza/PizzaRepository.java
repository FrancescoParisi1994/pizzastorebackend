package it.prova.pizzastorebackend.repository.pizza;

import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastorebackend.model.Pizza;

public interface PizzaRepository extends CrudRepository<Pizza, Long>{

}
