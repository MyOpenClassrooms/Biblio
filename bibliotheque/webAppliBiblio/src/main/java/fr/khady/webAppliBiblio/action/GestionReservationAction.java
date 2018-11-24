package fr.khady.webAppliBiblio.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.opensymphony.xwork2.ActionSupport;

import fr.khady.wsBiblioClient.Auteur;
import fr.khady.wsBiblioClient.BibliothequeException;
import fr.khady.wsBiblioClient.Exemplaire;
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

public class GestionReservationAction extends ActionSupport{

	ReservationService_Service service = new ReservationService_Service();
	ReservationService reservationPort = service.getReservationServicePort();
	
	public Ouvrage ouvrage = new Ouvrage();
	public Utilisateur utilisateur = new Utilisateur();
	public Reservation reservation = new Reservation();
	public List<Reservation> reservations;
	
	private long idUser;
	private long idOuvrage;
	private long idResa;
	private  XMLGregorianCalendar dateRetourPlusProche;
	private ArrayList<XMLGregorianCalendar> dates = new ArrayList<XMLGregorianCalendar>();
	public String reserver() {
		String vResult = ActionSupport.INPUT;
//		idUser= utilisateur.getIdUser();
//		idOuvrage = ouvrage.getIdOuvrage();
			if (!this.hasErrors()) {
				reservationPort.creerReservation( ouvrage, utilisateur);
				vResult = ActionSupport.SUCCESS;
				this.addActionMessage("Reservation effectée avec succès");
			}
		listResa();
		return vResult;
	}
	
	public String listResa() {
		reservations = reservationPort.listerResevation();
//		for (Reservation reservation : reservations) {
			
//			try {
//				dateRetourPlusProche = reservationPort.dateRetourPlusProche(reservation.getOuvrage().getIdOuvrage());
//			} catch (BibliothequeException e) {
//				e.printStackTrace();
//			}
//			 dates.add(dateRetourPlusProche);
//			 setDates(dates);
//			 setDateRetourPlusProche(dateRetourPlusProche);
//		}
		
//	      dateRetourPlusProcheConvertie = dateRetourPlusProche.toGregorianCalendar().getTime();
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
