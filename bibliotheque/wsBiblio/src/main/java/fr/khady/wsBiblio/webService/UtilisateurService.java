package fr.khady.wsBiblio.webService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import fr.khady.wsBiblio.dao.UtilisateurDao;
import fr.khady.wsBiblio.entity.Utilisateur;
import fr.khady.wsBiblio.exception.BibliothequeException;
import fr.khady.wsBiblio.exception.BibliothequeFault;

@WebService(serviceName = "UtilisateurService")
public class UtilisateurService {

	@EJB
	private UtilisateurDao dao;

	@WebMethod
	public void creerUtilisateur(@WebParam(name = "nom") String nom, @WebParam(name = "prénom") String prenom,
			@WebParam(name = "adresse") String adress, @WebParam(name = "email") String email,
			@WebParam(name = "login") String login, @WebParam(name = "motDePasse") String password,
			@WebParam(name = "photo") String photo) {
		    
		    dao.creerUtilisateur(nom, prenom, adress, email, login, password, photo);
		    

	}

	@WebMethod
	public List<Utilisateur> listerUtilisateur() {
		return dao.listerUtilisateur();
	}

	@WebMethod
	public void modifierUtilisateur(@WebParam(name = "nom") String nom, @WebParam(name = "prénom") String prenom,
			@WebParam(name = "adresse") String adress, @WebParam(name = "email") String email,
			@WebParam(name = "login") String login, @WebParam(name = "motDePasse") String password,
			@WebParam(name = "photo") String photo) {
		dao.modifierUtilisateur(nom, prenom, adress, email, login, password, photo);

	}

	@WebMethod
	public Utilisateur trouverUtilisateurParId(@WebParam(name = "id_user") long id_user) throws BibliothequeException {
		
		BibliothequeFault fault = new BibliothequeFault();
		Utilisateur utilisateur = dao.trouverUtilisateurParId(id_user);
		if (utilisateur != null) {
			return utilisateur;
		} else
			throw new BibliothequeException("Aucun utilisateur trouvé pour l'id " + id_user, fault);
	}

	@WebMethod
	public List<Utilisateur> listerUtilisateurRelance() throws BibliothequeException{
		BibliothequeFault fault = new BibliothequeFault();
		List<Utilisateur> utilisateurs =  dao.listerUtilisateurRelance();
		if (utilisateurs != null) {
			return utilisateurs;
		} else
			throw new BibliothequeException("Aucun utilisateur trouvé pour la relance " , fault);
	}

}
