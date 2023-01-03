package it.prova.pizzastorebackend.service.pizza;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastorebackend.exception.IdNotFoundException;
import it.prova.pizzastorebackend.exception.PizzaNotFoundException;
import it.prova.pizzastorebackend.model.Pizza;
import it.prova.pizzastorebackend.repository.pizza.PizzaRepository;

@Service
@Transactional
public class PizzaServiceImpl implements PizzaService {

	@Autowired
	private PizzaRepository repository;

	@Transactional(readOnly = true)
	public List<Pizza> listAll() {
		return repository.findByAttivoTrue();
	}

	@Transactional(readOnly = true)
	public List<Pizza> findByExample(Pizza pizza) {
		return repository.findByExample(pizza);
	}

	@Transactional(readOnly = true)
	public Pizza findById(Long id) {
		if (id == null) {
			throw new IdNotFoundException();
		}
		Pizza pizza = repository.findByIdAndAttivoTrue(id).orElse(null);
		if (pizza == null) {
			throw new PizzaNotFoundException();
		}
		return pizza;
	}

	public Pizza insert(Pizza pizza) {
		pizza.setId(null);
		pizza.setAttivo(true);
		return repository.save(pizza);
	}

	public Pizza update(Pizza pizza) {
		if (pizza.getId() == null) {
			throw new IdNotFoundException();
		}

		Pizza daModificare = findById(pizza.getId());
		if (!daModificare.getDescrizione().equals(pizza.getDescrizione())) {
			daModificare.setDescrizione(pizza.getDescrizione());
		}
		if (!daModificare.getIngredienti().equals(pizza.getIngredienti())) {
			daModificare.setIngredienti(pizza.getIngredienti());
		}
		if (!daModificare.getPrezzo().equals(pizza.getPrezzo())) {
			daModificare.setPrezzo(pizza.getPrezzo());
		}
		return repository.save(pizza);
	}

	public void delete(Long id) {
		if (id == null) {
			throw new IdNotFoundException();
		}
		Pizza pizza = findById(id);
		pizza.setAttivo(false);
		repository.save(pizza);
	}
}
