package it.prova.pizzastorebackend.repository.pizza;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import it.prova.pizzastorebackend.model.Pizza;

public class CustomPizzaRepositoryImpl implements CustomPizzaRepository {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Pizza> findByExample(Pizza input) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select r from Pizza r where r.id = r.id ");

		if (StringUtils.isNotBlank(input.getDescrizione())) {
			whereClauses.add(" r.descrizione like :descrizione ");
			paramaterMap.put("descrizione", "%" + input.getDescrizione() + "%");
		}
		if (StringUtils.isNotBlank(input.getIngredienti())) {
			whereClauses.add(" r.ingredienti like :ingredienti ");
			paramaterMap.put("ingredienti", "%" + input.getIngredienti() + "%");
		}
		if (input.getPrezzo() != null) {
			whereClauses.add(" r.prezzo >= :prezzo ");
			paramaterMap.put("prezzo", input.getPrezzo());
		}
		
		whereClauses.add(" r.attivo = true");
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Pizza> typedQuery = entityManager.createQuery(queryBuilder.toString(), Pizza.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();

	}

}
