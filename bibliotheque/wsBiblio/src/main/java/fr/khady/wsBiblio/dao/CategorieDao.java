package fr.khady.wsBiblio.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.khady.wsBiblio.entity.Categorie;

@Stateless
public class CategorieDao {
	
	@PersistenceContext(unitName = "bdd_biblio_PU")
	private EntityManager entityManager;

	public void creerCategorie(String libelle) throws DaoException {
		Categorie categorie = new Categorie();
		categorie.setLibelle(libelle);

		try {
			entityManager.persist(categorie);
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}

	@SuppressWarnings("unchecked")
	public List<Categorie> listerCategorie() throws DaoException {
		Query req = entityManager.createQuery("select c from Categorie c");
		try {
			return req.getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}

	public Categorie trouverCategorieParId(long idCat) {

		Categorie categorie = entityManager.find(Categorie.class, idCat);
		return categorie;
	}

}
