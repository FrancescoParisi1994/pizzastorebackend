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

import it.prova.pizzastorebackend.dto.ClienteDTO;
import it.prova.pizzastorebackend.dto.ClienteInsertDTO;
import it.prova.pizzastorebackend.exception.IdNotFoundException;
import it.prova.pizzastorebackend.service.cliente.ClienteService;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ClienteController {

	@Autowired
	private ClienteService service;
	
	@GetMapping
	public List<ClienteDTO> listAll(){
		return ClienteDTO.buildDTOListFromModelList(service.listAll());
	}
	
	@GetMapping("/search")
	public List<ClienteDTO> search(@RequestBody ClienteDTO dto){
		return ClienteDTO.buildDTOListFromModelList(service.findByExample(dto.buildModelFromDTO()));
	}
	
	@GetMapping("/{id}")
	public ClienteDTO findElement(@PathVariable(required = true)Long id){
		return ClienteDTO.buildDTOFromModel(service.findById(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDTO insert(@Valid @RequestBody ClienteInsertDTO dto) {
		return ClienteDTO.buildDTOFromModel(service.insert(dto.buildModelFromDTO()));
	}
	
	@PutMapping
	public ClienteDTO update(@Valid @RequestBody ClienteInsertDTO dto) {
		if (dto.getId()==null) {
			throw new IdNotFoundException();
		}
		return ClienteDTO.buildDTOFromModel(service.update(dto.buildModelFromDTO()));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true) Long id) {
		service.delete(id);
	}
}
