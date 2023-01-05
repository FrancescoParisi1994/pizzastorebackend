package it.prova.pizzastorebackend.dto;

import java.util.Set;

import it.prova.pizzastorebackend.model.Ruolo;
import it.prova.pizzastorebackend.model.Utente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtenteDTO {

	private String username;
	private String nome;
	private String cognome;
	private Set<Ruolo> ruoli;
	
	public Utente buildModelFromDTO() {
		return Utente.builder().username(getUsername()).nome(getNome()).cognome(getCognome()).ruoli(getRuoli()).build();
	}

	public static UtenteDTO buildDTOFromModel(Utente utente) {
		return new UtenteDTO(utente.getUsername(), utente.getNome(), utente.getCognome(), utente.getRuoli());
	}
}
