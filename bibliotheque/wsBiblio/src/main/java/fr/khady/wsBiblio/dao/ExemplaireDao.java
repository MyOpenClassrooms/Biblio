package fr.khady.wsBiblio.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.khady.wsBiblio.entity.Exemplaire;
import fr.khady.wsBiblio.entity.Ouvrage;

@Stateless
public class ExemplaireDao {

	@PersistenceContext(unitName = "bdd_biblio_PU")
	private EntityManager entityManager;

	public void creerExemplaire(int nbrExemp, int numRayon, long  idOuvrage) throws DaoException {
		Exemplaire exemplaire = new Exemplaire();
		Ouvrage ouvrage = new Ouvrage();
		
		exemplaire.setNbrExemplaire(nbrExemp);
		exemplaire.setNumRayon(numRayon);
		ouvrage.setIdOuvrage(idOuvrage);
		exemplaire.setOuvrage(ouvrage);

		try {
			entityManager.persist(exemplaire);
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}

	@SuppressWarnings("unchecked")
	public List<Exemplaire> listerExemplaire() throws DaoException {
		Query req = entityManager.createQuery("select e from Exemplaire e");
		try {
			return req.getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}

	public Exemplaire trouverExemplaireParId(long idExemp) {

		Exemplaire exemplaire = entityManager.find(Exemplaire.class, idExemp);
		return exemplaire;
	}

}
