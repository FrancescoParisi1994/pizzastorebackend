package it.prova.pizzastorebackend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.prova.pizzastorebackend.model.Pizza;
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
public class PizzaInsertDTO {

	private Long id;
	@NonNull
	@NotBlank(message = "{notblanck.pizza.descrizione}")
	private String descrizione;
	@NonNull
	@NotBlank(message = "{notblanck.pizza.ingredienti}")
	private String ingredienti;
	@NonNull
	@NotNull(message = "{notnull.pizza.prezzo}")
	private Integer prezzo;
	private boolean attivo;

	public Pizza buildModelFromDTO() {
		return new Pizza(getId(), getDescrizione(), getIngredienti(), getPrezzo(), isAttivo());
	}

}
