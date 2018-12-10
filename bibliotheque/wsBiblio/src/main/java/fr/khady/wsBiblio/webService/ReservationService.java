package fr.khady.wsBiblio.webService;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import fr.khady.wsBiblio.dao.OuvrageDao;
import fr.khady.wsBiblio.dao.ReservationDao;
import fr.khady.wsBiblio.entity.Ouvrage;
import fr.khady.wsBiblio.entity.Pret;
import fr.khady.wsBiblio.entity.Reservation;
import fr.khady.wsBiblio.entity.Utilisateur;
import fr.khady.wsBiblio.exception.BibliothequeException;
import fr.khady.wsBiblio.exception.BibliothequeFault;

@WebService(serviceName = "ReservationService")
public class ReservationService {
	
	@EJB
	private ReservationDao dao;

	@WebMethod
	public void creerReservation(@WebParam(name = "ouvrage") Ouvrage ouvrage,
			@WebParam(name = "utilisateur") Utilisateur user) {

		dao.creerReservation(ouvrage, user);
	}
	
	@WebMethod
	public Reservation trouverReservationParId(@WebParam(name = "idResa") long idResa) throws BibliothequeException {

		BibliothequeFault fault = new BibliothequeFault();
		Reservation reservation = dao.trouverReservationParId(idResa);
		if (reservation != null) {
			return reservation;
		} else
			throw new BibliothequeException("Aucun réservation trouvée pour l'id " + idResa, fault);
	}
	
	@WebMethod
	public List<Reservation> listerResevation() {
		return dao.listerReservation();
	}
	
	@WebMethod
	public List<Reservation> trouverReservationParDateResa(@WebParam(name = "dateResa") Date dateResa) throws BibliothequeException {
		BibliothequeFault fault = new BibliothequeFault();
		List<Reservation> reservations = dao.trouverReservationParDateResa(dateResa);
		if (reservations != null) {
			return reservations;
		} else
			throw new BibliothequeException("Aucune réservation trouvée pour la date " + dateResa, fault);
	}
	
	@WebMethod
	public List<Reservation> trouverReservationParUtilisateur(@WebParam(name = "user") Utilisateur user) throws BibliothequeException {
		BibliothequeFault fault = new BibliothequeFault();
		List<Reservation> reservations = dao.trouverReservationParUser(user);
		if (reservations != null) {
			return reservations;
		} else
			throw new BibliothequeException("Aucune réservation trouvée pour l'utilisateur " + user, fault);
	}
	
	@WebMethod
	public int annulerResa(@WebParam(name = "user") Utilisateur user, @WebParam(name = "ouvrage") Ouvrage ouvrage) throws BibliothequeException {
		BibliothequeFault fault = new BibliothequeFault();
		int annulResa = dao.annulerReservation(ouvrage, user );
		if (annulResa != 0) {
			return annulResa;
		} else
			throw new BibliothequeException("Aucune annulation pour l'utilisateur " + user, fault);
	}
	
	@WebMethod
	public Date dateRetourPlusProche(@WebParam(name = "ouvrage") long idOuvrage) throws BibliothequeException {
		BibliothequeFault fault = new BibliothequeFault();
		Date dateRetourProche = dao.dateRetourPlusProche(idOuvrage);
		if (dateRetourProche != null) {
			return dateRetourProche;
		} else
			throw new BibliothequeException("Aucune date trouvée", fault);
	}
	
	@WebMethod
	public List<Reservation> trouverReservationParUtilisateurOuvrage(@WebParam(name = "user") Utilisateur user, @WebParam(name = "ouvrage") Ouvrage ouvrage) throws BibliothequeException {
		BibliothequeFault fault = new BibliothequeFault();
		List<Reservation> reservations = dao.trouverReservationParUserOuvrage(user, ouvrage);
		if (reservations != null) {
			return reservations;
		} else
			throw new BibliothequeException("Aucune réservation trouvée pour l'utilisateur " + user, fault);
	}
	
	@WebMethod
	public List<Reservation> trouverReservationParOuvrage(@WebParam(name = "ouvrage") Ouvrage ouvrage) throws BibliothequeException {
		BibliothequeFault fault = new BibliothequeFault();
		List<Reservation> reservations = dao.trouverReservationParOuvrage(ouvrage);
		if (reservations != null) {
			return reservations;
		} else
			throw new BibliothequeException("Aucune réservation trouvée pour l'utilisateur " + ouvrage, fault);
	}
	
	@WebMethod
	public Date dateRetourPrevu(@WebParam(name = "ouvrage") long idOuvrage) throws BibliothequeException {
		BibliothequeFault fault = new BibliothequeFault();
		Date dateRetourPrevue = dao.dateRetourPrevue(idOuvrage);
		if (dateRetourPrevue != null) {
			return dateRetourPrevue;
		} else
			throw new BibliothequeException("Aucune date trouvée", fault);
	}
	
	@WebMethod
	public Double verifDelai(@WebParam(name = "ouvrage") long idOuvrage, @WebParam(name = "user") long idUser) throws BibliothequeException {
		BibliothequeFault fault = new BibliothequeFault();
		Double diffDate = dao.verifDelai(idOuvrage, idUser);
		if (diffDate != null) {
			return diffDate;
		} else
			throw new BibliothequeException("Aucune date trouvée", fault);
	}
}
