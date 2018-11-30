package fr.khady.wsBiblio.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import fr.khady.wsBiblio.entity.Exemplaire;
import fr.khady.wsBiblio.entity.Ouvrage;
import fr.khady.wsBiblio.entity.Pret;
import fr.khady.wsBiblio.entity.Utilisateur;

@Stateless
public class PretDao {

	@PersistenceContext(unitName = "bdd_biblio_PU")
	private EntityManager entityManager;

	private static final String JPQL_UPDATE = "UPDATE pret  SET date_retour = date_retour + (28 || 'day')::interval "
			+ "WHERE date_retour=?1";
	private static final String JPQL_SELECT_PAR_UTILISATEUR = "SELECT p FROM Pret p WHERE p.user.idUser=:iduser";
	private static final String JPQL_SELECT_PAR_UTILISATEUR_EXEMP = "SELECT p FROM Pret p WHERE p.user.idUser=:iduser AND p.exemplaire.idExemp = :idExemp";
	private static final String PARAM_ID_USER = "iduser";
	private static final String PARAM_ID_EXEMP = "idExemp";
	private static final String JPQL_SELECT_PAR_DATE_RETOUR = "SELECT p FROM Pret p WHERE p.dateRetourPrevu=:dateRetour";
	private static final String PARAM_DATE_RETOUR = "dateRetour";
	
	public void creerPret(Date dateSortie, Date dateRetourPrevu, long idExemp, long idUser, Boolean rendu)
			throws DaoException {
		Pret pret = new Pret();
		pret.setDateSortie(dateSortie);
		pret.setDateRetourPrevu(dateRetourPrevu);

		Exemplaire exemplaire = new Exemplaire();
		exemplaire.setIdExemp(idExemp);
		pret.setExemplaire(exemplaire);

		Utilisateur user = new Utilisateur();
		user.setIdUser(idUser);
		pret.setUser(user);

		pret.setRendu(rendu);

		try {
			entityManager.persist(pret);
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}

	@SuppressWarnings("unchecked")
	public List<Pret> listerPret() throws DaoException {
		Query req = entityManager.createQuery("select p from Pret p");
		try {
			return req.getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}

	// public void modifierPret(Date dateSortie, Date dateRetourPrevu, long idExemp,
	// long idUser, Boolean rendu) throws DaoException {
	// Pret pret = new Pret();
	// pret.setDateSortie(dateSortie);
	// pret.setDateRetourPrevu(dateRetourPrevu);
	//
	// Exemplaire exemplaire = new Exemplaire();
	// exemplaire.setIdExemp(idExemp);
	// pret.setExemplaire(exemplaire);
	//
	// Utilisateur user = new Utilisateur();
	// user.setIdUser(idUser);
	// pret.setUser(user);
	//
	// pret.setRendu(rendu);
	// try {
	// entityManager.merge(pret);
	// } catch (Exception e) {
	// throw new DaoException("ko");
	// }
	//
	// }
	

	public int modifierPret(Date dateRetour) throws DaoException {
		Query requete = entityManager.createNativeQuery(JPQL_UPDATE);
		int executeUp = requete.setParameter(1, dateRetour).executeUpdate();
		System.out.println("executeupdate " + executeUp );
		listerPret();
		return executeUp;

	}

	public Pret trouverPretParId(long idPret) {
		Pret pret = entityManager.find(Pret.class, idPret);
		return pret;
	}

	
	  @SuppressWarnings("unchecked")
			public List<Pret> trouverPretParDateRetourPrevu(Date dateRetourPrevu) throws DaoException {
				Query req = entityManager.createQuery(JPQL_SELECT_PAR_DATE_RETOUR);
				req.setParameter(PARAM_DATE_RETOUR, dateRetourPrevu);
				try {
					return req.getResultList();
				} catch (Exception e) {
					throw new DaoException(e);
				}
		  }
	  

	public Pret trouverPretParDateSortie(Date dateSortie) {

		Pret pret = entityManager.find(Pret.class, dateSortie);
		return pret;
	}
	
	  @SuppressWarnings("unchecked")
			public List<Pret> trouverPretParUtilisateur(long idUser) throws DaoException {
				Query req = entityManager.createQuery(JPQL_SELECT_PAR_UTILISATEUR);
				req.setParameter(PARAM_ID_USER, idUser);
				try {
					return req.getResultList();
				} catch (Exception e) {
					throw new DaoException(e);
				}
		  }
	  
	  @SuppressWarnings("unchecked")
			public List<Pret> trouverPretParUtilisateurExemplaire(long idUser, long idExemp) throws DaoException {
				Query req = entityManager.createQuery(JPQL_SELECT_PAR_UTILISATEUR_EXEMP);
				req.setParameter(PARAM_ID_USER, idUser);
				req.setParameter(PARAM_ID_EXEMP, idExemp);
				try {
					return req.getResultList();
				} catch (Exception e) {
					throw new DaoException(e);
				}
		  }

//	public Pret trouverPretParUtilisateur(long idUser) {
//
//		Pret pret = entityManager.find(Pret.class, idUser);
//		return pret;
//	}

	public Pret trouverPretParExemplaire(long idExemp) {

		Pret pret = entityManager.find(Pret.class, idExemp);
		return pret;
	}

}
