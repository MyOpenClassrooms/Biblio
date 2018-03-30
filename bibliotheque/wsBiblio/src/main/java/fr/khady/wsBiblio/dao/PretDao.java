package fr.khady.wsBiblio.dao;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.khady.wsBiblio.entity.Auteur;
import fr.khady.wsBiblio.entity.Categorie;
import fr.khady.wsBiblio.entity.Exemplaire;
import fr.khady.wsBiblio.entity.Ouvrage;
import fr.khady.wsBiblio.entity.Pret;
import fr.khady.wsBiblio.entity.Utilisateur;

@Stateless
public class PretDao {

	@PersistenceContext(unitName = "bdd_biblio_PU")
	private EntityManager entityManager;

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
	
	public void modifierPret(Date dateSortie, Date dateRetourPrevu, long idExemp, long idUser, Boolean rendu) throws DaoException {
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
			entityManager.merge(pret);
		} catch (Exception e) {
			throw new DaoException("ko");
		}

	}

	public Pret trouverPretParId(long idPret) {

		Pret pret = entityManager.find(Pret.class, idPret);
		return pret;
	}

	public Pret trouverPretParDateRetourPrevu(Date dateRetourPrevu) {

		Pret pret = entityManager.find(Pret.class, dateRetourPrevu);
		return pret;
	}

	public Pret trouverPretParDateSortie(Date dateSortie) {

		Pret pret = entityManager.find(Pret.class, dateSortie);
		return pret;
	}

	public Pret trouverPretParUtilisateur(long idUser) {

		Pret pret = entityManager.find(Pret.class, idUser);
		return pret;
	}

	public Pret trouverPretParExemplaire(long idExemp) {

		Pret pret = entityManager.find(Pret.class, idExemp);
		return pret;
	}

}
