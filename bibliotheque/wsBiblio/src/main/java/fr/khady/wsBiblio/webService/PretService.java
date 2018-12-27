package fr.khady.wsBiblio.webService;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import fr.khady.wsBiblio.dao.PretDao;
import fr.khady.wsBiblio.entity.Pret;
import fr.khady.wsBiblio.exception.BibliothequeException;
import fr.khady.wsBiblio.exception.BibliothequeFault;

@WebService(serviceName = "PretService")
public class PretService {

	@EJB
	private PretDao dao;

	@WebMethod
	public void creerPret(@WebParam(name = "dateSortie") Date dateSortie,
			@WebParam(name = "dateRetourPrevu") Date dateRetourPrevu, @WebParam(name = "exemplaire") long idExemp,
			@WebParam(name = "utilisateur") long idUser, @WebParam(name = "rendu") Boolean rendu) {

		dao.creerPret(dateSortie, dateRetourPrevu, idExemp, idUser, rendu);

	}

	@WebMethod
	public List<Pret> listerPret() {
		return dao.listerPret();
	}

//	@WebMethod
//	public void modifierPret(@WebParam(name = "dateSortie") Date dateSortie,
//			@WebParam(name = "dateRetourPrevu") Date dateRetourPrevu, @WebParam(name = "exemplaire") long idExemp,
//			@WebParam(name = "utilisateur") long idUser, @WebParam(name = "rendu") Boolean rendu) {
//		dao.modifierPret(dateSortie, dateRetourPrevu, idExemp, idUser, rendu);
//
//	}
	
	@WebMethod
	public int modifierPret(@WebParam(name = "dateRetourPrevu") Date dateRetourPrevu) {
	return 	dao.modifierPret(dateRetourPrevu);

	}
	
	@WebMethod
	public Pret trouverPretParId(@WebParam(name = "idPret") long idPret) throws BibliothequeException {

		BibliothequeFault fault = new BibliothequeFault();
		Pret pret = dao.trouverPretParId(idPret);
		if (pret != null) {
			return pret;
		} else
			throw new BibliothequeException("Aucun pret trouvé pour l'id " + idPret, fault);
	}

	@WebMethod
	public List<Pret> trouverPretParDateRetourPrevu(@WebParam(name = "dateRetourPrevu") Date dateReour) throws BibliothequeException {

		BibliothequeFault fault = new BibliothequeFault();
		List<Pret> prets = dao.trouverPretParDateRetourPrevu(dateReour);
		if (prets != null) {
			return prets;
		} else
			throw new BibliothequeException("Aucun ouvrage trouvé pour le titre " + dateReour, fault);
	}
	
	
//	@WebMethod
//	public Pret trouverPretParDateRetourPrevu(@WebParam(name = "dateRetourPrevu") Date dateReour)
//			throws BibliothequeException {
//
//		BibliothequeFault fault = new BibliothequeFault();
//		Pret pret = dao.trouverPretParDateRetourPrevu(dateReour);
//		if (pret != null) {
//			return pret;
//		} else
//			throw new BibliothequeException("Aucun pret trouvé pour la date " + dateReour, fault);
//	}

	@WebMethod
	public Pret trouverPretParDateSortie(@WebParam(name = "dateSortie") Date dateSortie) throws BibliothequeException {

		BibliothequeFault fault = new BibliothequeFault();
		Pret pret = dao.trouverPretParDateSortie(dateSortie);
		if (pret != null) {
			return pret;
		} else
			throw new BibliothequeException("Aucun pret trouvé pour la date " + dateSortie, fault);
	}

	
	@WebMethod
	public List<Pret> trouverPretParUtilisateur(@WebParam(name = "utilisateur") long idUser) throws BibliothequeException {

		BibliothequeFault fault = new BibliothequeFault();
		List<Pret> prets = dao.trouverPretParUtilisateur(idUser);
		if (prets != null) {
			return prets;
		} else
			throw new BibliothequeException("Aucun ouvrage trouvé pour le titre " + idUser, fault);
	}
	
	@WebMethod
	public List<Pret> trouverPretParUtilisateurExemp(@WebParam(name = "utilisateur") long idUser, @WebParam(name = "exemplaire") long idExemp) throws BibliothequeException {

		BibliothequeFault fault = new BibliothequeFault();
		List<Pret> prets = dao.trouverPretParUtilisateurExemplaire(idUser, idExemp);
		if (prets != null) {
			return prets;
		} else
			throw new BibliothequeException("Aucun pret trouvé pour l'utilisateur " + idUser, fault);
	}

	@WebMethod
	public Pret trouverPretParExemplaire(@WebParam(name = "ouvrage") long idExemp) throws BibliothequeException {

		BibliothequeFault fault = new BibliothequeFault();
		Pret pret = dao.trouverPretParExemplaire(idExemp);
		if (pret != null) {
			return pret;
		} else
			throw new BibliothequeException("Aucun pret trouvé pour l'ouvrage " + idExemp, fault);
	}
	
	@WebMethod
	public List verifDelaiFiveDays( @WebParam(name = "user") long idUser) throws BibliothequeException {
		BibliothequeFault fault = new BibliothequeFault();
		List diffDate = dao.verifDelaiFiveDays(idUser);
		if (diffDate != null) {
			return diffDate;
		} else
			throw new BibliothequeException("Aucune date trouvée", fault);
	}

}
