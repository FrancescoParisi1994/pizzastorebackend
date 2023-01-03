package it.prova.pizzastorebackend;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.pizzastorebackend.model.Ruolo;
import it.prova.pizzastorebackend.model.Utente;
import it.prova.pizzastorebackend.service.ruolo.RuoloService;
import it.prova.pizzastorebackend.service.utente.UtenteService;

@SpringBootApplication
public class PizzastorebackendApplication implements CommandLineRunner {

	@Autowired
	private RuoloService ruoloServiceInstance;

	@Autowired
	private UtenteService utenteServiceInstance;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN) == null) {
			ruoloServiceInstance
					.inserisciNuovo(Ruolo.builder().descrizione("Administrator").codice(Ruolo.ROLE_ADMIN).build());
		}

		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = Utente.builder().username("admin").password("admin").nome("Mario").cognome("Rossi").build();
			Set<Ruolo> ruoli = new HashSet<Ruolo>();
			ruoli.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN));
			admin.setRuoli(ruoli);
			utenteServiceInstance.inserisciNuovo(admin);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", Ruolo.ROLE_PIZZAIOLO) == null) {
			ruoloServiceInstance
					.inserisciNuovo(Ruolo.builder().descrizione("Pizzaiolo").codice(Ruolo.ROLE_PIZZAIOLO).build());
		}

		if (utenteServiceInstance.findByUsername("pizzaiolo") == null) {
			Utente pizzaiolo = Utente.builder().username("pizzaiolo").password("pizzaiolo").nome("Mario")
					.cognome("Rossi").build();
			Set<Ruolo> ruoli = new HashSet<Ruolo>();
			ruoli.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", Ruolo.ROLE_PIZZAIOLO));
			pizzaiolo.setRuoli(ruoli);
			utenteServiceInstance.inserisciNuovo(pizzaiolo);
			// l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(pizzaiolo.getId());
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(PizzastorebackendApplication.class, args);
	}

}
