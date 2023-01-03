package it.prova.pizzastorebackend.repository.ordine;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastorebackend.model.Ordine;

public interface OrdineRepository extends CrudRepository<Ordine, Long>, CustomOrdineRepository {

	public List<Ordine> findByAttivoTrue();

	public Optional<Ordine> findByIdAndAttivoTrue(Long id);
}
