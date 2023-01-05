package it.prova.pizzastorebackend.dto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import it.prova.pizzastorebackend.model.Cliente;
import it.prova.pizzastorebackend.model.Ordine;
import it.prova.pizzastorebackend.model.Pizza;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@RequiredArgsConstructor
public class OrdineReportDTO {

	@NonNull
	@NotNull(message = "{notnull.ordine.data}")
	private LocalDate data;
	@NonNull
	@NotEmpty(message = "{notempty.ordine.pizze}")
	private Long[] pizzeId;
	@NonNull
	@NotNull(message = "{notnull.ordine.cliente}")
	private Long clienteId;

	public Ordine buildModelFromDTO() {
		Ordine ordine = Ordine.builder().data(getData()).build();

		ordine.setPizze(Arrays.asList(pizzeId).stream().map(id -> Pizza.builder().id(id).build()).collect(Collectors.toList()));
		
		ordine.setCliente(Cliente.builder().id(clienteId).build());

		return ordine;
	}

}
