package it.prova.pizzastorebackend.repository.cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import it.prova.pizzastorebackend.model.Cliente;

public class CustomClienteRepositoryImpl implements CustomClienteRepository {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Cliente> findByExample(Cliente input) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select r from Cliente r where r.id = r.id ");

		if (StringUtils.isNotBlank(input.getNome())) {
			whereClauses.add(" r.nome like :nome ");
			paramaterMap.put("nome", "%" + input.getNome() + "%");
		}
		if (StringUtils.isNotBlank(input.getCognome())) {
			whereClauses.add(" r.cognome like :cognome ");
			paramaterMap.put("cognome", "%" + input.getCognome() + "%");
		}
		if (StringUtils.isNotBlank(input.getIndirizzo())) {
			whereClauses.add(" r.indirizzo like :indirizzo ");
			paramaterMap.put("indirizzo", "%" + input.getIndirizzo() + "%");
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Cliente> typedQuery = entityManager.createQuery(queryBuilder.toString(), Cliente.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();

	}
	
}
