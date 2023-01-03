package it.prova.pizzastorebackend.repository.pizza;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastorebackend.model.Pizza;

public interface PizzaRepository extends CrudRepository<Pizza, Long>,CustomPizzaRepository{

	public List<Pizza> findByAttivoTrue();
	
	public Optional<Pizza> findByIdAndAttivoTrue(Long id);
}
