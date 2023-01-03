package it.prova.pizzastorebackend.repository.ordine;

import java.util.List;

import it.prova.pizzastorebackend.model.Ordine;

public interface CustomOrdineRepository {

	public List<Ordine> findByExample(Ordine input);
}
