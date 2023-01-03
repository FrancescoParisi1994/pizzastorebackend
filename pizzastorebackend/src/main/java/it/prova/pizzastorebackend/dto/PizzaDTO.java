package it.prova.pizzastorebackend.dto;

import java.util.List;
import java.util.stream.Collectors;

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
public class PizzaDTO {

	private Long id;
	private String descrizione;
	private String ingredienti;
	private Integer prezzo;
	private boolean attivo;

	public Pizza buildModelFromDTO() {
		return new Pizza(getId(), getDescrizione(), getIngredienti(), getPrezzo(), isAttivo());
	}

	public static PizzaDTO buildDTOFromModel(Pizza pizza) {
		return new PizzaDTO(pizza.getId(), pizza.getDescrizione(), pizza.getIngredienti(), pizza.getPrezzo(),
				pizza.isAttivo());
	}

	public static List<PizzaDTO> buildDTOListFromModelList(List<Pizza> pizze) {
		return pizze.stream().map(Element -> {
			return buildDTOFromModel(Element);
		}).collect(Collectors.toList());
	}
}
