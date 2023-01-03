package it.prova.pizzastorebackend.service.ordine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastorebackend.exception.IdNotFoundException;
import it.prova.pizzastorebackend.exception.OrdineNotFoundException;
import it.prova.pizzastorebackend.model.Ordine;
import it.prova.pizzastorebackend.model.Pizza;
import it.prova.pizzastorebackend.repository.ordine.OrdineRepository;

@Service
@Transactional
public class OrdineServiceImpl implements OrdineService {

	@Autowired
	private OrdineRepository repository;

	@Transactional(readOnly = true)
	public List<Ordine> listAll() {
		return repository.findByAttivoTrue();
	}

	@Transactional(readOnly = true)
	public List<Ordine> findByExample(Ordine ordine) {
		return repository.findByExample(ordine);
	}

	@Transactional(readOnly = true)
	public Ordine findById(Long id) {
		if (id == null) {
			throw new IdNotFoundException();
		}
		Ordine ordine = repository.findByIdAndAttivoTrue(id).orElse(null);
		if (ordine == null) {
			throw new OrdineNotFoundException();
		}
		return ordine;
	}

	public Ordine insert(Ordine ordine) {
		ordine.setAttivo(true);
		return repository.save(ordine);
	}

	public Ordine update(Ordine ordine) {
		if (ordine.getId() == null) {
			throw new IdNotFoundException();
		}

		Ordine daModificare = findById(ordine.getId());
		if (!daModificare.getCodice().equals(ordine.getCodice())) {
			daModificare.setCodice(ordine.getCodice());
		}
		if (!(daModificare.getCostoTot() == ordine.getCostoTot())) {
			daModificare.setCostoTot(ordine.getCostoTot());
		}
		if (!daModificare.getData().equals(ordine.getData())) {
			daModificare.setData(ordine.getData());
		}
		if (!(daModificare.getCliente().getId() == ordine.getCliente().getId())) {
			daModificare.setCliente(ordine.getCliente());
		}
		if (!(daModificare.getFattorino().getId() == ordine.getFattorino().getId())) {
			daModificare.setFattorino(ordine.getFattorino());
		}
		daModificare.setPizze(ordine.getPizze());
		return repository.save(daModificare);
	}

	public void delete(Long id) {
		if (id == null) {
			throw new IdNotFoundException();
		}
		Ordine ordine = findById(id);
		ordine.setAttivo(false);
		repository.save(ordine);
	}
	
	public Integer calcolaPrezzoOrdine(Ordine ordine) {
		Integer tot = 0;
		for (Pizza pizzaItem : ordine.getPizze()) {
			tot += pizzaItem.getPrezzo();
		}
		return tot;
	}
}
