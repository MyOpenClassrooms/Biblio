package fr.khady.wsBiblio.dao;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.khady.wsBiblio.entity.Utilisateur;

@Stateless
public class UtilisateurDao {

	@PersistenceContext(unitName = "bdd_biblio_PU")
	private EntityManager entityManager;
	
	private static final String JPQL_SELECT_PAR_DATE_RETOUR = "SELECT u.email FROM utilisateur u,pret p WHERE u.id_user=p.id_user " + 
			"AND p.rendu = false AND DATE_PART('day', NOW()::timestamp - '2018-03-28'::timestamp) > 0";
	private static final String JPQL_UPDATE_USER = "UPDATE utilisateur SET rappel = ?1 WHERE id_user = ?2";
    
	 private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM Utilisateur u WHERE u.email=:email AND u.password=:password";
	    private static final String PARAM_EMAIL  = "email";
	    private static final String PARAM_PASSWORD = "password";

	public void creerUtilisateur(String nom, String prenom, String adress, String email, String login, String password,
			String photo) throws DaoException {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setAdress(adress);
		utilisateur.setEmail(email);
		utilisateur.setLogin(login);
		utilisateur.setPassword(password);
		utilisateur.setPhoto(photo);
		utilisateur.setRappel(true);

		try {
			entityManager.persist(utilisateur);
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}

	@SuppressWarnings("unchecked")
	public List<Utilisateur> listerUtilisateur() throws DaoException {
		Query req = entityManager.createQuery("select u from Utilisateur u");
		try {
			return req.getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}

	public void modifierUtilisateur(Utilisateur utilisateur) throws DaoException {

		try {
			entityManager.merge(utilisateur);
		} catch (Exception e) {
			throw new DaoException("ko");
		}

	}

	public Utilisateur trouverUtilisateurParId(Long id_user) {

		Utilisateur utilisateur = entityManager.find(Utilisateur.class, id_user);
		return utilisateur;
	}

	public Utilisateur trouverUtilisateurParEmail(String email, String password)  throws DaoException {
		
		  Utilisateur utilisateur = null;
	        Query requete = entityManager.createQuery( JPQL_SELECT_PAR_EMAIL );
	        requete.setParameter( PARAM_EMAIL, email );
	        requete.setParameter(PARAM_PASSWORD, password);
	        try {
	            utilisateur = (Utilisateur) requete.getSingleResult();
	        } catch ( NoResultException e ) {
	            return null;
	        } catch ( Exception e ) {
	            throw new DaoException( e );
	        }
	        return utilisateur;
	    }
	

	@SuppressWarnings("unchecked")
	public List<String> listerUtilisateurRelance() throws DaoException {
		Query req = entityManager.createNativeQuery( JPQL_SELECT_PAR_DATE_RETOUR );
		try {
			return req.getResultList();
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}

	public int updateRappel(long user, Boolean rappel) throws DaoException {
		Query req = entityManager.createNativeQuery( JPQL_UPDATE_USER);
		req.setParameter(1, rappel);
		req.setParameter(2, user);
		try {
			return req.executeUpdate();
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}
}
