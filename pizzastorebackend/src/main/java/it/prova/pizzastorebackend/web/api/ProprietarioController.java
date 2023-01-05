package it.prova.pizzastorebackend.web.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pizzastorebackend.dto.OrdineDTO;
import it.prova.pizzastorebackend.dto.OrdineReportDTO;
import it.prova.pizzastorebackend.model.Cliente;
import it.prova.pizzastorebackend.service.ordine.OrdineService;

@RestController
@RequestMapping("/api/proprietario")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ProprietarioController {

	@Autowired
	private OrdineService ordineService;

	@GetMapping("/report")
	public List<OrdineDTO> report(@Valid @RequestBody OrdineReportDTO dto) {
		return OrdineDTO.buildDTOListFromModelList(ordineService.report(dto.buildModelFromDTO()));
	}

	@GetMapping("/statistiche")
	public Map<String, Object> statisticheVarie(@RequestBody LocalDate inizio, @RequestBody LocalDate fine) {
//		Ricavi totali nell’intervallo selezionato: XXX
//		o Numero ordini totali nell’intervallo selezionato: XXX
//		o Numero pizze totali ordinate nell’intervallo selezionato: XXX
//		o Tabellina con lista clienti (virtuosi) che hanno effettuato almeno un ordine oltre i
//		100 euro nell’intervallo selezionato.

		List<OrdineDTO> elements = OrdineDTO.buildDTOListFromModelList(ordineService.statistiche(inizio, fine));
		Map<String, Object> result = new HashMap<>();
		Integer ricavi = 0;
		for (OrdineDTO ordineItem : elements) {
			ricavi += ordineItem.getCostoTot();
		}
		result.put("Ricavi", ricavi);
		Integer totOrdini = elements.size();
		result.put("OrdiniTot", totOrdini);
		Integer totPizze = 0;
		for (OrdineDTO ordineItem : elements) {
			totPizze += ordineItem.getPizze().size();
		}
		result.put("PizzeTot", totPizze);
		List<Cliente> clientiVirtuosi = new ArrayList<>();
		for (OrdineDTO ordineItem : elements) {
			if (ordineItem.getCostoTot() != null && ordineItem.getCostoTot() > 100) {
				clientiVirtuosi.add(ordineItem.getCliente());
			}
		}
		result.put("ClientiVirtuosi", clientiVirtuosi);
		return result;
	}
}
