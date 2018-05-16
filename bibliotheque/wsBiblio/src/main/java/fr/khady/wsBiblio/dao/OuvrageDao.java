package fr.khady.wsBiblio.dao;

import java.util.Date;
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
	private static final String JPQL_SELECT_PAR_ISBN_TITRE_AUTEUR = "SELECT o FROM Ouvrage o WHERE o.isbn=:isbn"
			+ " OR o.titre=:titre OR o.auteur.nom=:nom OR o.disponibilite=:dispo";
	private static final String JPQL_SELECT_PAR_AUTEUR = "SELECT o FROM Ouvrage o WHERE o.auteur.idAuteur=:idAut";
	private static final String JPQL_SELECT_PAR_DISPONIBLITE = "SELECT o FROM Ouvrage o WHERE o.disponibilite=:disponibilite";
	private static final String PARAM_TITRE = "titre";
	private static final String PARAM_ISBN = "isbn";
	private static final String PARAM_AUTEUR = "nom";
	private static final String PARAM_DISPONIBLITE = "dispo";

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


	  @SuppressWarnings("unchecked")
		public List<Ouvrage> trouverOuvrageParIsbnTitreAuteur(String isbn, String titre, String nom, Boolean dispo) throws DaoException {
			Query req = entityManager.createQuery(JPQL_SELECT_PAR_ISBN_TITRE_AUTEUR);
			req.setParameter(PARAM_ISBN, isbn);
			req.setParameter(PARAM_TITRE, titre);
			req.setParameter(PARAM_AUTEUR, nom);
			req.setParameter(PARAM_DISPONIBLITE, dispo);
			try {
				return req.getResultList();
			} catch (Exception e) {
				throw new DaoException(e);
			}
	  }

	  @SuppressWarnings("unchecked")
		public List<Ouvrage> trouverOuvrageParIsbn(String isbn) throws DaoException {
			Query req = entityManager.createQuery(JPQL_SELECT_PAR_ISBN);
			req.setParameter(PARAM_ISBN, isbn);
			try {
				return req.getResultList();
			} catch (Exception e) {
				throw new DaoException(e);
			}
	  }
	  
	@SuppressWarnings("unchecked")
	public List<Ouvrage> trouverOuvrageParTitre(String titre) throws DaoException {
		Query req = entityManager.createQuery(JPQL_SELECT_PAR_TITRE);
		req.setParameter(PARAM_TITRE, titre);
		try {
			return req.getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}
	
	
	@SuppressWarnings("unchecked")
	public List<Ouvrage> trouverOuvrageParAuteur(long idAut) throws DaoException {
		Query req = entityManager.createQuery(JPQL_SELECT_PAR_AUTEUR);
		req.setParameter(PARAM_AUTEUR, idAut);
		try {
			return req.getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}
	

	public Ouvrage trouverOuvrageParCategorie(long idCat) {

		Ouvrage ouvrage = entityManager.find(Ouvrage.class, idCat);
		return ouvrage;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Ouvrage> trouverOuvrageParDisponiblite(Boolean disponibilite) throws DaoException {
		Query req = entityManager.createQuery(JPQL_SELECT_PAR_DISPONIBLITE);
		req.setParameter(PARAM_DISPONIBLITE, disponibilite);
		try {
			return req.getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}
	

}
