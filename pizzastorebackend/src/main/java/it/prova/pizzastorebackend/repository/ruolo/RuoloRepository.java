package it.prova.pizzastorebackend.repository.ruolo;

import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastorebackend.model.Ruolo;

public interface RuoloRepository extends CrudRepository<Ruolo, Long> {
	Ruolo findByDescrizioneAndCodice(String descrizione, String codice);
}
