package it.prova.pizzastorebackend.service.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastorebackend.exception.ClienteNotFoundException;
import it.prova.pizzastorebackend.exception.IdNotFoundException;
import it.prova.pizzastorebackend.model.Cliente;
import it.prova.pizzastorebackend.repository.cliente.ClienteRepository;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository repository;

	@Transactional(readOnly = true)
	public List<Cliente> listAll(){
		return repository.findByAttivoTrue();
	}

	@Transactional(readOnly = true)
	public List<Cliente> findByExample(Cliente cliente){
		return repository.findByExample(cliente);
	}
	
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		if (id==null) {
			throw new IdNotFoundException();
		}
		Cliente cliente=repository.findByIdAndAttivoTrue(id).orElse(null);
		if (cliente==null) {
			throw new ClienteNotFoundException();
		}
		return cliente;
	}
	
	public Cliente insert(Cliente cliente) {
		cliente.setAttivo(true);
		return repository.save(cliente);
	}
	
	public Cliente update(Cliente cliente) {
		Cliente daModificare = findById(cliente.getId());
		if (!daModificare.getNome().equals(cliente.getNome())) {
			daModificare.setNome(cliente.getNome());
		}
		if (!daModificare.getCognome().equals(cliente.getCognome())) {
			daModificare.setCognome(cliente.getCognome());
		}
		if (!daModificare.getIndirizzo().equals(cliente.getIndirizzo())) {
			daModificare.setIndirizzo(cliente.getIndirizzo());
		}
		return repository.save(daModificare);
	}
	
	public void delete(Long id) {
		if (id==null) {
			throw new IdNotFoundException();
		}
		Cliente cliente=findById(id);
		cliente.setAttivo(false);
		repository.save(cliente);
	}
}
