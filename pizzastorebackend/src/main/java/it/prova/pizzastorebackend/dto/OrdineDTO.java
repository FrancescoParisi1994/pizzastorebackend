package it.prova.pizzastorebackend.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import it.prova.pizzastorebackend.model.Cliente;
import it.prova.pizzastorebackend.model.Ordine;
import it.prova.pizzastorebackend.model.Pizza;
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
public class OrdineDTO {

	private Long id;
	private String codice;
	private Integer costoTot;
	private LocalDate data;
	private boolean attivo;
	private List<Pizza> pizze;
	private Cliente cliente;
	private UtenteDTO fattorino;

	public Ordine buildModelFromDTO() {
		Ordine ordine= Ordine.builder().id(getId()).codice(getCodice()).costoTot(getCostoTot()).data(getData()).attivo(isAttivo()).pizze(getPizze()).cliente(getCliente()).build();
		ordine.setFattorino(getFattorino().buildModelFromDTO());
		return ordine;
	}

	public static OrdineDTO buildDTOFromModel(Ordine ordine) {
		OrdineDTO dto = OrdineDTO.builder().id(ordine.getId()).codice(ordine.getCodice()).costoTot(ordine.getCostoTot()).data(ordine.getData()).attivo(ordine.isAttivo()).pizze(ordine.getPizze()).cliente(ordine.getCliente()).build();
		dto.setFattorino(UtenteDTO.buildDTOFromModel(ordine.getFattorino()));
		return dto;
	}

	public static List<OrdineDTO> buildDTOListFromModelList(List<Ordine> ordini) {
		return ordini.stream().map(Element -> {
			return buildDTOFromModel(Element);
		}).collect(Collectors.toList());
	}

}
