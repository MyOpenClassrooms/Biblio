package fr.khady.wsBiblio.webService;

import java.sql.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import fr.khady.wsBiblio.dao.OuvrageDao;
import fr.khady.wsBiblio.entity.Auteur;
import fr.khady.wsBiblio.entity.Categorie;
import fr.khady.wsBiblio.entity.Ouvrage;
import fr.khady.wsBiblio.exception.BibliothequeException;
import fr.khady.wsBiblio.exception.BibliothequeFault;

@WebService(serviceName = "OuvrageService")
public class OuvrageService {

	@EJB
	private OuvrageDao dao;

	@WebMethod
	public void creerOuvrage(@WebParam(name = "isbn") String isbn, @WebParam(name = "titre") String titre,
			@WebParam(name = "resume") String resume, @WebParam(name = "nbrpage") int nbrpage,
			@WebParam(name = "dateParution") Date dateParution, @WebParam(name = "disponibilite") Boolean disponibilite,
			@WebParam(name = "photoCouverture") String photoCouverture,
			@WebParam(name = "categorie") long idCat, @WebParam(name = "auteur") long idAut) {

		dao.creerOuvrage(isbn, titre, resume, nbrpage, dateParution, disponibilite, photoCouverture, idCat, idAut);

	}

	@WebMethod
	public List<Ouvrage> listerOuvrage() {
		return dao.listerOuvrage();
	}

	@WebMethod
	public Ouvrage trouverOuvrageParId(@WebParam(name = "idOuvrage") long idOuvrage) throws BibliothequeException {

		BibliothequeFault fault = new BibliothequeFault();
		Ouvrage ouvrage = dao.trouverOuvrageParId(idOuvrage);
		if (ouvrage != null) {
			return ouvrage;
		} else
			throw new BibliothequeException("Aucun ouvrage trouvé pour l'id " + idOuvrage, fault);
	}

	@WebMethod
	public Ouvrage trouverOuvrageParIsbn(@WebParam(name = "isbn") String isbn) throws BibliothequeException {

		BibliothequeFault fault = new BibliothequeFault();
		Ouvrage ouvrage = dao.trouverOuvrageParIsbn(isbn);
		if (ouvrage != null) {
			return ouvrage;
		} else
			throw new BibliothequeException("Aucun ouvrage trouvé pour l'isbn " + isbn, fault);
	}
	
	@WebMethod
	public Ouvrage trouverOuvrageParTitre(@WebParam(name = "titre") String titre) throws BibliothequeException {

		BibliothequeFault fault = new BibliothequeFault();
		Ouvrage ouvrage = dao.trouverOuvrageParTitre(titre);
		if (ouvrage != null) {
			return ouvrage;
		} else
			throw new BibliothequeException("Aucun ouvrage trouvé pour le titre " + titre, fault);
	}
	
	@WebMethod
	public Ouvrage trouverOuvrageParAuteur(@WebParam(name = "auteur") long idAut) throws BibliothequeException {

		BibliothequeFault fault = new BibliothequeFault();
		Ouvrage ouvrage = dao.trouverOuvrageParAuteur(idAut);
		if (ouvrage != null) {
			return ouvrage;
		} else
			throw new BibliothequeException("Aucun ouvrage trouvé pour l'auteur " + idAut, fault);
	}
	
	@WebMethod
	public Ouvrage trouverOuvrageParCategorie(@WebParam(name = "categorie") long idCat) throws BibliothequeException {

		BibliothequeFault fault = new BibliothequeFault();
		Ouvrage ouvrage = dao.trouverOuvrageParCategorie(idCat);
		if (ouvrage != null) {
			return ouvrage;
		} else
			throw new BibliothequeException("Aucun ouvrage trouvé pour la catégorie " + idCat, fault);
	}
	
	@WebMethod
	public Ouvrage trouverOuvrageParDisponiblite(@WebParam(name = "disponiblite") Boolean disponibilite) throws BibliothequeException {

		BibliothequeFault fault = new BibliothequeFault();
		Ouvrage ouvrage = dao.trouverOuvrageParDisponiblite(disponibilite);
		if (ouvrage != null) {
			return ouvrage;
		} else
			throw new BibliothequeException("Aucun ouvrage trouvé pour la disponilité " + disponibilite, fault);
	}
	

}
