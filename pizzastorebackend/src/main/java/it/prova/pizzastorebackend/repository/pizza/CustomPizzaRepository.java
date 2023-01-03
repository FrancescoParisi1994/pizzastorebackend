package it.prova.pizzastorebackend.repository.pizza;

import java.util.List;

import it.prova.pizzastorebackend.model.Pizza;

public interface CustomPizzaRepository {

	public List<Pizza> findByExample(Pizza input);
}
