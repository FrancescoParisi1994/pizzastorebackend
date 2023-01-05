package it.prova.pizzastorebackend.dto;

import javax.validation.constraints.NotBlank;

import it.prova.pizzastorebackend.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class ClienteInsertDTO {

	private Long id;
	@NonNull
	@NotBlank(message = "{notblanck.cliente.nome}")
	private String nome;
	@NonNull
	@NotBlank(message = "{notblanck.cliente.cognome}")
	private String cognome;
	@NonNull
	@NotBlank(message = "{notblanck.cliente.indirizzo}")
	private String indirizzo;
	private boolean attivo;
	
	public Cliente buildModelFromDTO() {
		return new Cliente(getId(), getNome(), getCognome(), getIndirizzo(), isAttivo());
	}
	
}
