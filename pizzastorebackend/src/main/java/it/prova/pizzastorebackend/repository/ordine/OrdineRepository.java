package it.prova.pizzastorebackend.repository.ordine;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastorebackend.model.Ordine;

public interface OrdineRepository extends CrudRepository<Ordine, Long>, CustomOrdineRepository {

	public List<Ordine> findByAttivoTrue();

	public Optional<Ordine> findByIdAndAttivoTrue(Long id);

	@Query("select o from Ordine o join o.cliente c join o.pizze p where o.data >= :data and c.id = :idCliente and p.id = :idPizza")
	public List<Ordine> report(LocalDate data, Long idCliente, Long idPizza);
	
	@Query("select o from Ordine o join o.cliente c join o.pizze p where o.data between :inizio and :fine")
	public List<Ordine> statistiche(LocalDate inizio, LocalDate fine);
}
