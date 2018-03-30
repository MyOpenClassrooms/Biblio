package fr.khady.wsBiblio.dao;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.khady.wsBiblio.entity.Auteur;
import fr.khady.wsBiblio.entity.Categorie;
import fr.khady.wsBiblio.entity.Ouvrage;

@Stateless
public class OuvrageDao {

	@PersistenceContext(unitName = "bdd_biblio_PU")
	private EntityManager entityManager;

	private static final String JPQL_SELECT_PAR_TITRE = "SELECT o FROM Ouvrage o WHERE o.titre=:titre";
	private static final String JPQL_SELECT_PAR_ISBN = "SELECT o FROM Ouvrage o WHERE o.isbn=:isbn";
	private static final String JPQL_SELECT_PAR_DISPONIBLITE = "SELECT o FROM Ouvrage o WHERE o.disponibilite=:disponibilite";
	private static final String PARAM_TITRE = "titre";
	private static final String PARAM_ISBN = "isbn";
	private static final String PARAM_DISPONIBLITE = "disponibilite";

	public void creerOuvrage(String isbn, String titre, String resume, int nbrpage, Date dateParution,
			Boolean disponibilite, String photoCouverture, long idCat, long idAut) throws DaoException {
		Ouvrage ouvrage = new Ouvrage();
		Categorie categorie = new Categorie();
		Auteur auteur = new Auteur();

		ouvrage.setIsbn(isbn);
		ouvrage.setTitre(titre);
		ouvrage.setResume(resume);
		ouvrage.setNbrpage(nbrpage);
		ouvrage.setDateParution(dateParution);
		ouvrage.setDisponibilite(disponibilite);
		ouvrage.setPhotoCouverture(photoCouverture);

		categorie.setIdCat(idCat);
		ouvrage.setCategorie(categorie);

		auteur.setIdAuteur(idAut);
		ouvrage.setAuteur(auteur);

		try {
			entityManager.persist(ouvrage);
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}

	@SuppressWarnings("unchecked")
	public List<Ouvrage> listerOuvrage() throws DaoException {
		Query req = entityManager.createQuery("select o from Ouvrage o");
		try {
			return req.getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}

	public Ouvrage trouverOuvrageParId(long idExemp) {

		Ouvrage ouvrage = entityManager.find(Ouvrage.class, idExemp);
		return ouvrage;
	}

	public Ouvrage trouverOuvrageParIsbn(String isbn) {

		// Ouvrage ouvrage = entityManager.find(Ouvrage.class, isbn);

		Ouvrage ouvrage = null;
		Query requete = entityManager.createQuery(JPQL_SELECT_PAR_ISBN);
		requete.setParameter(PARAM_ISBN, isbn);
		try {
			ouvrage = (Ouvrage) requete.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}

		return ouvrage;
	}

	public Ouvrage trouverOuvrageParTitre(String titre) {
		Ouvrage ouvrage = null;
		Query requete = entityManager.createQuery(JPQL_SELECT_PAR_TITRE);
		requete.setParameter(PARAM_TITRE, titre);
		try {
			ouvrage = (Ouvrage) requete.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}

		// Ouvrage ouvrage = entityManager.find(Ouvrage.class, titre);

		return ouvrage;
	}

	public Ouvrage trouverOuvrageParAuteur(long idAut) {

		Ouvrage ouvrage = entityManager.find(Ouvrage.class, idAut);
		return ouvrage;
	}

	public Ouvrage trouverOuvrageParCategorie(long idCat) {

		Ouvrage ouvrage = entityManager.find(Ouvrage.class, idCat);
		return ouvrage;
	}

	public Ouvrage trouverOuvrageParDisponiblite(Boolean disponibilite) {

		// Ouvrage ouvrage = entityManager.find(Ouvrage.class, disponibilite);

		Ouvrage ouvrage = null;
		Query requete = entityManager.createQuery(JPQL_SELECT_PAR_DISPONIBLITE);
		requete.setParameter(PARAM_DISPONIBLITE, disponibilite);
		try {
			ouvrage = (Ouvrage) requete.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return ouvrage;
	}
}
