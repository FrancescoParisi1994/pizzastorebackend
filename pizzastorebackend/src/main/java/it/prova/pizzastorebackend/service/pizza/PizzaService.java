package it.prova.pizzastorebackend.service.pizza;

import java.util.List;

import it.prova.pizzastorebackend.model.Pizza;

public interface PizzaService {

	public List<Pizza> listAll();

	public List<Pizza> findByExample(Pizza pizza);

	public Pizza findById(Long id);

	public Pizza insert(Pizza pizza);

	public Pizza update(Pizza pizza);

	public void delete(Long id);

}
