package fr.khady.webAppliBiblio.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import fr.khady.wsBiblioClient.Auteur;
import fr.khady.wsBiblioClient.BibliothequeException;
import fr.khady.wsBiblioClient.Exemplaire;
import fr.khady.wsBiblioClient.ExemplaireService;
import fr.khady.wsBiblioClient.ExemplaireService_Service;
import fr.khady.wsBiblioClient.Ouvrage;
import fr.khady.wsBiblioClient.OuvrageService;
import fr.khady.wsBiblioClient.OuvrageService_Service;
import fr.khady.wsBiblioClient.Pret;
import fr.khady.wsBiblioClient.PretService;
import fr.khady.wsBiblioClient.PretService_Service;
import fr.khady.wsBiblioClient.Reservation;
import fr.khady.wsBiblioClient.ReservationService;
import fr.khady.wsBiblioClient.ReservationService_Service;
import fr.khady.wsBiblioClient.Utilisateur;

public class GestionReservationAction extends ActionSupport implements SessionAware{

	ReservationService_Service service = new ReservationService_Service();
	ReservationService reservationPort = service.getReservationServicePort();

	ExemplaireService_Service serviceExemp = new ExemplaireService_Service();
	ExemplaireService exempPort = serviceExemp.getExemplaireServicePort();

	PretService_Service servicePret = new PretService_Service();
	PretService pretPort = servicePret.getPretServicePort();
	
	OuvrageService_Service serviceOuvrage = new OuvrageService_Service();
	OuvrageService ouvragePort = serviceOuvrage.getOuvrageServicePort();
	
	private Map<String, Object> session;
	public List<Ouvrage> ouvrages;
	public Ouvrage ouvrage = new Ouvrage();
	public Exemplaire exemp = new Exemplaire();
	public Pret pret = new Pret();
	public Utilisateur utilisateur = new Utilisateur();
	public Reservation reservation = new Reservation();
	public List<Reservation> reservations;
	public List<Pret> prets;
	public List<Reservation> reservationsUserOuvrage;

	private long idUser;
	private long idOuvrage;
	private long idResa;
	private XMLGregorianCalendar dateRetourPlusProche;
	private ArrayList<XMLGregorianCalendar> dates = new ArrayList<XMLGregorianCalendar>();

	@Override
	public void setSession(Map<String, Object> pSession) {
		 this.session = pSession;
		
	}
	
	public String reserver() {
		String vResult = ActionSupport.INPUT;
//		idUser= utilisateur.getIdUser();
//		idOuvrage = ouvrage.getIdOuvrage();
		try {
			exemp = exempPort.trouverExemplaireParOuvrage(ouvrage.getIdOuvrage());
			reservations = reservationPort.trouverReservationParOuvrage(ouvrage);
			reservationsUserOuvrage = reservationPort.trouverReservationParUtilisateurOuvrage(utilisateur, ouvrage);
			prets = pretPort.trouverPretParUtilisateurExemp(utilisateur.getIdUser(), exemp.getIdExemp());
			ouvrages = ouvragePort.listerOuvrage();
		} catch (BibliothequeException e) {
			e.printStackTrace();
		}

//			if (reservations.size() < (2*exemp.getNbrExemplaire()) && !(reservationsUserOuvrage.size()==1) && !(exemp == prets.get(0).getExemplaire())) {
//				reservationPort.creerReservation( ouvrage, utilisateur);
//				vResult = ActionSupport.SUCCESS;
//				this.addActionMessage("Reservation effectée avec succès");
//			}else {
//				this.addActionMessage("Impossible de faire une réservation, merci de réessayer plutart!");
//				
//			}
		if (!(reservations.size() < (2 * exemp.getNbrExemplaire()))) {
			addActionError("Impossible de faire une réservation, merci de réessayer plutart! resa max");

		} else if (reservationsUserOuvrage.size() == 1) {
			addActionError("Impossible de faire une réservation, une résa en encours por cet ouvrage");
		} else if (!(prets.isEmpty()) && (exemp.getIdExemp() == (prets.get(0).getExemplaire().getIdExemp()))) {
			addActionError("Impossible de faire une réservation, un pret en encours pour cet ouvrage");
		} else {
			reservationPort.creerReservation(ouvrage, utilisateur, null);
			vResult = ActionSupport.SUCCESS;
			this.addActionMessage("Reservation effectée avec succès");
		}

		listResa();
		return vResult;

	}

	public String listResa() {
		utilisateur = (Utilisateur) this.session.get("utilisateur");
		try {
			reservations = reservationPort.trouverReservationParUtilisateur(utilisateur);
		} catch (BibliothequeException e) {
			e.printStackTrace();
		}

		if (reservations.isEmpty()) {
			this.addActionMessage("Aucune réservation");
		}
		return ActionSupport.SUCCESS;
	}

	public String annuler() {
		int executeUpdate = 0;
		idResa = reservation.getIdResa();
		try {
			reservation = reservationPort.trouverReservationParId(idResa);
		} catch (BibliothequeException e) {
			e.printStackTrace();
		}
		utilisateur = reservation.getUser();
		ouvrage = reservation.getOuvrage();
		try {
			executeUpdate = reservationPort.annulerResa(utilisateur, ouvrage);
		} catch (BibliothequeException e) {
			e.printStackTrace();
		}
		if (executeUpdate != 0) {
			this.addActionMessage("Reservation annulée");
		}
		reservations = reservationPort.listerResevation();
		return SUCCESS;
	}

	public XMLGregorianCalendar getDateRetourPlusProche() {
		return dateRetourPlusProche;
	}

	public void setDateRetourPlusProche(XMLGregorianCalendar dateRetourPlusProche) {
		this.dateRetourPlusProche = dateRetourPlusProche;
	}

	public ArrayList<XMLGregorianCalendar> getDates() {
		return dates;
	}

	public void setDates(ArrayList<XMLGregorianCalendar> dates) {
		this.dates = dates;
	}

}
