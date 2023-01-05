package it.prova.pizzastorebackend.service.ordine;

import java.time.LocalDate;
import java.util.List;

import it.prova.pizzastorebackend.model.Ordine;

public interface OrdineService {

	public List<Ordine> listAll();

	public List<Ordine> findByExample(Ordine ordine);

	public Ordine findById(Long id);

	public Ordine insert(Ordine ordine);

	public Ordine update(Ordine ordine);

	public void delete(Long id);

	public List<Ordine> report(Ordine ordine);
	
	public List<Ordine> statistiche(LocalDate inizio,LocalDate fine);
}
