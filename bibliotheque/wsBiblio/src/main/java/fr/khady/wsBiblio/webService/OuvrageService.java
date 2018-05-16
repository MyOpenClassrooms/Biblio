package fr.khady.wsBiblio.webService;

import java.util.Date;
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

	
	public List<Ouvrage> trouverOuvrageParIsbn(String isbn) throws BibliothequeException {

		BibliothequeFault fault = new BibliothequeFault();
		List<Ouvrage> ouvrages = dao.trouverOuvrageParIsbn(isbn);
		if (ouvrages != null) {
			return ouvrages;
		} else
			throw new BibliothequeException("Aucun ouvrage trouvé pour l'isbn " + isbn, fault);
	}
	
	@WebMethod
	public List<Ouvrage> trouverOuvrageParIsbnTitreAuteur(@WebParam(name = "isbn") String isbn,@WebParam(name = "titre") String titre,@WebParam(name = "auteur") String nom, @WebParam(name = "disponibilité") Boolean dispo) throws BibliothequeException {

		BibliothequeFault fault = new BibliothequeFault();
		List<Ouvrage> ouvrages = dao.trouverOuvrageParIsbnTitreAuteur(isbn, titre, nom, dispo);
		if (ouvrages != null) {
			return ouvrages;
		} else
			throw new BibliothequeException("Aucun ouvrage trouvé pour l'isbn " + isbn, fault);
	}
	
	
	
	public List<Ouvrage> trouverOuvrageParTitre(String titre) throws BibliothequeException {

		BibliothequeFault fault = new BibliothequeFault();
		List<Ouvrage> ouvrages = dao.trouverOuvrageParTitre(titre);
		if (ouvrages != null) {
			return ouvrages;
		} else
			throw new BibliothequeException("Aucun ouvrage trouvé pour le titre " + titre, fault);
	}
	
	
	public List<Ouvrage> trouverOuvrageParAuteur(long idAut) throws BibliothequeException {

		BibliothequeFault fault = new BibliothequeFault();
		List<Ouvrage> ouvrages = dao.trouverOuvrageParAuteur(idAut);
		if (ouvrages != null) {
			return ouvrages;
		} else
			throw new BibliothequeException("Aucun ouvrage trouvé pour l'auteur " + idAut, fault);
	}
	
	
	public Ouvrage trouverOuvrageParCategorie(long idCat) throws BibliothequeException {

		BibliothequeFault fault = new BibliothequeFault();
		Ouvrage ouvrage = dao.trouverOuvrageParCategorie(idCat);
		if (ouvrage != null) {
			return ouvrage;
		} else
			throw new BibliothequeException("Aucun ouvrage trouvé pour la catégorie " + idCat, fault);
	}
	
	
	public List<Ouvrage>trouverOuvrageParDisponiblite(Boolean disponibilite) throws BibliothequeException {

		BibliothequeFault fault = new BibliothequeFault();
		List<Ouvrage> ouvrages = dao.trouverOuvrageParDisponiblite(disponibilite);
		if (ouvrages != null) {
			return ouvrages;
		} else
			throw new BibliothequeException("Aucun ouvrage trouvé pour la disponilité " + disponibilite, fault);
	}
	

}
