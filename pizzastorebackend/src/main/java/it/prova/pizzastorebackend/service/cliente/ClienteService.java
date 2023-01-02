package it.prova.pizzastorebackend.service.cliente;

import java.util.List;

import it.prova.pizzastorebackend.model.Cliente;

public interface ClienteService {
	
	public List<Cliente> listAll();
	
	public Cliente findById(Long id);
	
	public Cliente insert(Cliente cliente);
	
	public Cliente update(Cliente cliente);
	
	public void delete(Long id);
}
