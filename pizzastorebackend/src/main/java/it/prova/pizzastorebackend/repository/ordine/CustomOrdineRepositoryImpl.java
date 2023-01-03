package it.prova.pizzastorebackend.repository.ordine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import it.prova.pizzastorebackend.model.Ordine;

public class CustomOrdineRepositoryImpl implements CustomOrdineRepository {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Ordine> findByExample(Ordine input) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		String joinPizza = ""; 
		String joinCliente = "";
		String joinFattorino = "";
		StringBuilder queryBuilder = new StringBuilder("select r from Ordine "+joinPizza+" "+joinCliente+" "+joinFattorino+" r where r.id = r.id ");

		if (StringUtils.isNotBlank(input.getCodice())) {
			whereClauses.add(" r.codice like :codice ");
			paramaterMap.put("codice", "%" + input.getCodice() + "%");
		}
		if (input.getCostoTot() != null) {
			whereClauses.add(" r.costoTot >= :costoTot ");
			paramaterMap.put("costoTot", input.getCostoTot());
		}
		if (input.getData() != null) {
			whereClauses.add(" r.data >= :data ");
			paramaterMap.put("data", input.getData());
		}
		
		if (!input.getPizze().isEmpty()) {
			joinPizza = "join r.pizze p";
			for (int i = 0; i < input.getPizze().size(); i++) {
				whereClauses.add(" p.id = :descrizione"+i+" ");
				paramaterMap.put("descrizione"+i, input.getPizze().get(i).getId());
			}
		}
		if (input.getCliente() != null || input.getCliente().getId() != null) {
			joinCliente = "join r.cliente c";
			whereClauses.add(" c.id = :idCliente ");
			paramaterMap.put("idCliente", input.getCliente().getId());
		}
		if (input.getFattorino() != null || input.getFattorino().getId() != null) {
			joinCliente = "join r.fattorino f";
			whereClauses.add(" f.id = :idFattorino ");
			paramaterMap.put("idFattorino", input.getFattorino().getId());
		}
		
		whereClauses.add(" r.attivo = true");
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Ordine> typedQuery = entityManager.createQuery(queryBuilder.toString(), Ordine.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();

	}

}
