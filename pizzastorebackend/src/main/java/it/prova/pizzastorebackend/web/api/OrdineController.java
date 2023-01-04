package it.prova.pizzastorebackend.web.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import it.prova.pizzastorebackend.dto.OrdineDTO;
import it.prova.pizzastorebackend.dto.OrdineInsertDTO;
import it.prova.pizzastorebackend.exception.IdNotFoundException;
import it.prova.pizzastorebackend.service.ordine.OrdineService;

@RestController
@RequestMapping("/api/ordine")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class OrdineController {

	@Autowired
	private OrdineService service;

	@GetMapping
	public List<OrdineDTO> listAll() {
		return OrdineDTO.buildDTOListFromModelList(service.listAll());
	}

	@GetMapping("/search")
	public List<OrdineDTO> search(@RequestBody OrdineDTO dto) {
		return OrdineDTO.buildDTOListFromModelList(service.findByExample(dto.buildModelFromDTO()));
	}

	@GetMapping("/{id}")
	public OrdineDTO findById(@PathVariable(required = true) Long id) {
		return OrdineDTO.buildDTOFromModel(service.findById(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, Object> insert(@Valid @RequestBody OrdineInsertDTO dto) {
		Map<String, Object> resultMap = new HashMap<>();
		OrdineDTO ordine = OrdineDTO.buildDTOFromModel(service.insert(dto.buildModelFromDTO()));
		resultMap.put("Ordine", ordine);
		resultMap.put("Prezzo", ordine.getCostoTot());
		return resultMap;
	}

	@PutMapping
	public OrdineDTO update(@Valid @RequestBody OrdineInsertDTO dto) {
		if (dto.getId() == null) {
			throw new IdNotFoundException();
		}
		return OrdineDTO.buildDTOFromModel(service.update(dto.buildModelFromDTO()));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void delete(@PathVariable(required = true) Long id) {
		service.delete(id);
	}
}
