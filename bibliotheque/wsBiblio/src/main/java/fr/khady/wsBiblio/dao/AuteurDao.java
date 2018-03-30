package fr.khady.wsBiblio.dao;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.khady.wsBiblio.entity.Auteur;

@Stateless
public class AuteurDao {

	@PersistenceContext(unitName = "bdd_biblio_PU")
	private EntityManager entityManager;

	public void creerAuteur(String nom, String prenom, Date dateNaiss) throws DaoException {
		Auteur auteur = new Auteur();
		auteur.setNom(nom);
		auteur.setPrenom(prenom);
		auteur.setDateNaiss(dateNaiss);

		try {
			entityManager.persist(auteur);
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}

	@SuppressWarnings("unchecked")
	public List<Auteur> listerAuteur() throws DaoException {
		Query req = entityManager.createQuery("select a from Auteur a");
		try {
			return req.getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}

	public void modifierAuteur(String nom, String prenom, Date dateNaiss) throws DaoException {
		Auteur auteur = new Auteur();
		auteur.setNom(nom);
		auteur.setPrenom(prenom);
		auteur.setDateNaiss(dateNaiss);
		try {
			entityManager.merge(auteur);
		} catch (Exception e) {
			throw new DaoException("ko");
		}

	}

	public Auteur trouverAuteurParId(long idAut) {

		Auteur auteur = entityManager.find(Auteur.class, idAut);
		return auteur;
	}

}
