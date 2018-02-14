package fr.khady.webService.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.khady.webService.entity.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public void creer(Utilisateur utilisateur) {
		entityManager.persist(utilisateur);

	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Utilisateur> lister() {
		Query req = entityManager.createQuery("select u from Utilisateur u");
		return (ArrayList<Utilisateur>) req.getResultList();
	}

	@Override
	public void modifier(Utilisateur utilisateur) {
		entityManager.merge(utilisateur);

	}

	@Override
	public Utilisateur trouverParId(long id_user) {
		Utilisateur utilisateur = entityManager.find(Utilisateur.class, id_user);
		return utilisateur;
	}

}
