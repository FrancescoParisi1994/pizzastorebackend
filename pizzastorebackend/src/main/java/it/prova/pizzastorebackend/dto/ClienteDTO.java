package it.prova.pizzastorebackend.dto;

import java.util.List;
import java.util.stream.Collectors;

import it.prova.pizzastorebackend.model.Cliente;
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
public class ClienteDTO {

	private Long id;
	private String nome;
	private String cognome;
	private String indirizzo;
	private boolean attivo;

	public Cliente buildModelFromDTO() {
		return new Cliente(getId(), getNome(), getCognome(), getIndirizzo(), isAttivo());
	}

	public static ClienteDTO buildDTOFromModel(Cliente cliente) {
		return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getCognome(), cliente.getIndirizzo(),
				cliente.isAttivo());
	}

	public static List<ClienteDTO> buildDTOListFromModelList(List<Cliente> clienti) {
		return clienti.stream().map(Element -> {
			return buildDTOFromModel(Element);
		}).collect(Collectors.toList());
	}
}
