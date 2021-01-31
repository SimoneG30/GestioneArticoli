package it.gestionearticolijspservletjpamaven.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.gestionearticolijspservletjpamaven.model.Articolo;

public class ArticoloDAOImpl implements ArticoloDAO {

	private EntityManager entityManager;

	@Override
	public List<Articolo> list() throws Exception {
		return entityManager.createQuery("from Articolo", Articolo.class).getResultList();
	}

	@Override
	public Articolo findOne(Long id) throws Exception {
		return entityManager.find(Articolo.class, id);
	}

	@Override
	public void update(Articolo input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Articolo input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Articolo input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Articolo> findByExample(Articolo input) throws Exception {

        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("select a from Articolo a where");
        stringBuilder.append("( :codice is null OR a.codice like ('%' || :codice || '%'))");
        stringBuilder.append("AND (:descrizione is null OR a.descrizione like ('%' || :descrizione || '%'))");
        stringBuilder.append("AND (:prezzo is null OR a.prezzo = :prezzo) ");
        stringBuilder.append("AND (:dataArrivo is null OR a.dataArrivo = :dataArrivo) ");
        TypedQuery<Articolo> typedQuery = entityManager.createQuery(stringBuilder.toString(), Articolo.class);
        typedQuery.setParameter("codice", input.getCodice());
        typedQuery.setParameter("descrizione", input.getDescrizione());
        typedQuery.setParameter("prezzo", input.getPrezzo());
        typedQuery.setParameter("dataArrivo", input.getDataArrivo());
        return typedQuery.getResultList();
	}
}
