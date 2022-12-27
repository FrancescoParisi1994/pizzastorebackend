package it.prova.pizzastorebackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ruolo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ruolo {
	
	public static final String ADMIN_ROLE = "ADMIN_ROLE";
	public static final String PIZZAIOLO_ROLE = "PIZZAIOLO_ROLE";
	public static final String PROPRIETARIO_ROLE = "PROPRIETARIO_ROLE";
	public static final String FATTORINO_ROLE = "FATTORINO_ROLE";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "codice")
	private String codice;

}
