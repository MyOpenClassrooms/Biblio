package fr.khady.webServiceBiblio.dao;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.cxf.service.invoker.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.khady.webServiceBiblio.entity.Utilisateur;

@Repository("utilisateurDao")
public class UtilisateurDaoImpl implements UtilisateurDao {

//	
//	@PersistenceContext
//	private EntityManager entityManager;
//	
	
	 @Autowired
	    private SessionFactory sessionFactory;
	
	@Override
	public void creer(Utilisateur utilisateur) {
		sessionFactory.create(e)
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
