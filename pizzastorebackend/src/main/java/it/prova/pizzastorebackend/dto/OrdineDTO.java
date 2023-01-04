package it.prova.pizzastorebackend.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import it.prova.pizzastorebackend.model.Cliente;
import it.prova.pizzastorebackend.model.Ordine;
import it.prova.pizzastorebackend.model.Pizza;
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
public class OrdineDTO {

	private Long id;
	private String codice;
	private Integer costoTot;
	private Date data;
	private boolean attivo;
	private List<Pizza> pizze;
	private Cliente cliente;
	private Utente fattorino;

	public Ordine buildModelFromDTO() {
		return new Ordine(getId(), getCodice(), getCostoTot(), getData(), isAttivo(), getPizze(), getCliente(),
				getFattorino());
	}

	public static OrdineDTO buildDTOFromModel(Ordine ordine) {
		return new OrdineDTO(ordine.getId(), ordine.getCodice(), ordine.getCostoTot(), ordine.getData(),
				ordine.isAttivo(), ordine.getPizze(), ordine.getCliente(), ordine.getFattorino());
	}

	public static List<OrdineDTO> buildDTOListFromModelList(List<Ordine> ordini) {
		return ordini.stream().map(Element -> {
			return buildDTOFromModel(Element);
		}).collect(Collectors.toList());
	}

}
