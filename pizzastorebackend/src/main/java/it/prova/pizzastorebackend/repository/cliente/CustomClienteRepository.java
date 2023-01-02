package it.prova.pizzastorebackend.repository.cliente;

import java.util.List;

import it.prova.pizzastorebackend.model.Cliente;

public interface CustomClienteRepository {

	public List<Cliente> findByExample(Cliente input);
}
