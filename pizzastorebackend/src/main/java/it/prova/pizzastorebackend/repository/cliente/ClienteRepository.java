package it.prova.pizzastorebackend.repository.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastorebackend.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>,CustomClienteRepository{

	public List<Cliente> findByAttivoTrue();
	
	public Optional<Cliente> findByIdAndAttivoTrue(Long id);
}
