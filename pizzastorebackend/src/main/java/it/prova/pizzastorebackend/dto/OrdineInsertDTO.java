package it.prova.pizzastorebackend.dto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import it.prova.pizzastorebackend.model.Cliente;
import it.prova.pizzastorebackend.model.Ordine;
import it.prova.pizzastorebackend.model.Pizza;
import it.prova.pizzastorebackend.model.Utente;
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
public class OrdineInsertDTO {

	private Long id;
	@NonNull
	@NotBlank(message = "{notblanck.ordine.codice}")
	private String codice;
	@NonNull
	@NotNull(message = "{notnull.ordine.data}")
	private LocalDate data;
	private boolean attivo;
	@NonNull
	@NotEmpty(message = "{notempty.ordine.pizze}")
	private Long[] pizzeId;
	@NonNull
	@NotNull(message = "{notnull.ordine.cliente}")
	private Long clienteId;
	@NonNull
	@NotNull(message = "{notnull.ordine.fattorino}")
	private Long fattorinoId;

	public Ordine buildModelFromDTO() {
		Ordine ordine = Ordine.builder().id(getId()).codice(getCodice()).data(getData()).attivo(isAttivo()).build();

		ordine.setPizze(Arrays.asList(pizzeId).stream().map(id -> Pizza.builder().id(id).build()).collect(Collectors.toList()));

		ordine.setCliente(Cliente.builder().id(clienteId).build());
		
		ordine.setFattorino(Utente.builder().id(fattorinoId).build());
		
		return ordine;
	}

}
