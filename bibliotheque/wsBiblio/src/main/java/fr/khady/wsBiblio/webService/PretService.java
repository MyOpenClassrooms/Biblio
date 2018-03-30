package fr.khady.wsBiblio.webService;

import java.sql.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import fr.khady.wsBiblio.dao.PretDao;
import fr.khady.wsBiblio.entity.Auteur;
import fr.khady.wsBiblio.entity.Exemplaire;
import fr.khady.wsBiblio.entity.Pret;
import fr.khady.wsBiblio.entity.Utilisateur;
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

	@WebMethod
	public void modifierPret(@WebParam(name = "dateSortie") Date dateSortie,
			@WebParam(name = "dateRetourPrevu") Date dateRetourPrevu, @WebParam(name = "exemplaire") long idExemp,
			@WebParam(name = "utilisateur") long idUser, @WebParam(name = "rendu") Boolean rendu) {
		dao.modifierPret(dateSortie, dateRetourPrevu, idExemp, idUser, rendu);

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
	public Pret trouverPretParDateRetourPrevu(@WebParam(name = "dateRetourPrevu") Date dateReour)
			throws BibliothequeException {

		BibliothequeFault fault = new BibliothequeFault();
		Pret pret = dao.trouverPretParDateRetourPrevu(dateReour);
		if (pret != null) {
			return pret;
		} else
			throw new BibliothequeException("Aucun pret trouvé pour la date " + dateReour, fault);
	}

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
	public Pret trouverPretParUtilisateur(@WebParam(name = "utilisateur") long IdUser) throws BibliothequeException {

		BibliothequeFault fault = new BibliothequeFault();
		Pret pret = dao.trouverPretParUtilisateur(IdUser);
		if (pret != null) {
			return pret;
		} else
			throw new BibliothequeException("Aucun pret trouvé pour l'utilisateur " + IdUser, fault);
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

}
