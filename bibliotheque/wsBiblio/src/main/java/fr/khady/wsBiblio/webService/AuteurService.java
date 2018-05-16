package fr.khady.wsBiblio.webService;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import fr.khady.wsBiblio.dao.AuteurDao;
import fr.khady.wsBiblio.entity.Auteur;
import fr.khady.wsBiblio.exception.BibliothequeException;
import fr.khady.wsBiblio.exception.BibliothequeFault;

@WebService(serviceName = "AuteurService")
public class AuteurService {
	

	@EJB
	private AuteurDao dao;

	@WebMethod
	public void creerAuteur(@WebParam(name = "nom") String nom, @WebParam(name = "prénom") String prenom,
			@WebParam(name = "dateDeNaissance") Date dateNaiss ){

		    dao.creerAuteur(nom, prenom, dateNaiss);

	}

	@WebMethod
	public List<Auteur> listerAuteur() {
		return dao.listerAuteur();
	}

	@WebMethod
	public void modifierAuteur(@WebParam(name = "nom") String nom, @WebParam(name = "prénom") String prenom,
			@WebParam(name = "dateDeNaissance") Date dateNaiss) {
		dao.modifierAuteur(nom, prenom, dateNaiss);

	}

	@WebMethod
	public Auteur trouverAuteurParId(@WebParam(name = "idAuteur") long idAut) throws BibliothequeException {
		
		BibliothequeFault fault = new BibliothequeFault();
		Auteur auteur = dao.trouverAuteurParId(idAut);
		if (auteur != null) {
			return auteur;
		} else
			throw new BibliothequeException("Aucun auteur trouvé pour l'id " + idAut, fault);
	}


}
