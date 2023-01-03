package it.prova.pizzastorebackend.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pizzastorebackend.dto.PizzaDTO;
import it.prova.pizzastorebackend.dto.PizzaInsertDTO;
import it.prova.pizzastorebackend.exception.IdNotFoundException;
import it.prova.pizzastorebackend.service.pizza.PizzaService;

@RestController
@RequestMapping("/api/pizza")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;

	@GetMapping
	public List<PizzaDTO> listAll() {
		return PizzaDTO.buildDTOListFromModelList(pizzaService.listAll());
	}

	@GetMapping("/search")
	public List<PizzaDTO> search(@RequestBody PizzaDTO dto) {
		return PizzaDTO.buildDTOListFromModelList(pizzaService.findByExample(dto.buildModelFromDTO()));
	}

	@GetMapping("/{id}")
	public PizzaDTO findById(@PathVariable(required = true) Long id) {
		return PizzaDTO.buildDTOFromModel(pizzaService.findById(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PizzaDTO insert(@Valid @RequestBody PizzaInsertDTO dto) {
		return PizzaDTO.buildDTOFromModel(pizzaService.insert(dto.buildModelFromDTO()));
	}

	@PutMapping
	public PizzaDTO update(@Valid @RequestBody PizzaInsertDTO dto) {
		if (dto.getId() == null) {
			throw new IdNotFoundException();
		}
		return PizzaDTO.buildDTOFromModel(pizzaService.update(dto.buildModelFromDTO()));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true) Long id) {
		pizzaService.delete(id);
	}
}
