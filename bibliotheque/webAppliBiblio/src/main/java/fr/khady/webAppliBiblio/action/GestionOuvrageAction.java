package fr.khady.webAppliBiblio.action;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.struts2.interceptor.SessionAware;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.opensymphony.xwork2.ActionSupport;

import fr.khady.wsBiblioClient.Auteur;
import fr.khady.wsBiblioClient.BibliothequeException;
import fr.khady.wsBiblioClient.Exemplaire;
import fr.khady.wsBiblioClient.ExemplaireService;
import fr.khady.wsBiblioClient.ExemplaireService_Service;
import fr.khady.wsBiblioClient.Ouvrage;
import fr.khady.wsBiblioClient.OuvrageService;
import fr.khady.wsBiblioClient.OuvrageService_Service;
import fr.khady.wsBiblioClient.Reservation;
import fr.khady.wsBiblioClient.ReservationService;
import fr.khady.wsBiblioClient.ReservationService_Service;
import fr.khady.wsBiblioClient.Utilisateur;

public class GestionOuvrageAction extends ActionSupport {

	private static final long serialVersionUID = -4423447871500036097L;

	private Long idOuvrage;
	private String titre;
	private String isbn;
	private String nomAuteur;
	private Boolean dispo;
	public Ouvrage ouvrage = new Ouvrage();
	public Auteur auteur = new Auteur();
	public Exemplaire exemplaire = new Exemplaire();
	public Utilisateur utilisateur = new Utilisateur();
	public List<Ouvrage> ouvrages;
//	private Date dateRetourPrevu;
	XMLGregorianCalendar dateRetourPrevu;

	OuvrageService_Service service = new OuvrageService_Service();
	OuvrageService ouvragePort = service.getOuvrageServicePort();

	ExemplaireService_Service serviceEx = new ExemplaireService_Service();
	ExemplaireService exemplairePort = serviceEx.getExemplaireServicePort();

	ReservationService_Service serviceResa = new ReservationService_Service();
	ReservationService reservationPort = serviceResa.getReservationServicePort();
	public List<Reservation> reservations;

	public String index() {
		titre = ouvrage.getTitre();
		isbn = ouvrage.getIsbn();
		nomAuteur = auteur.getNom();
		dispo = ouvrage.isDisponibilite();

		System.out.println("dispo " + dispo);
		ouvrages = ouvragePort.listerOuvrage();
		if (titre == null && isbn == null && nomAuteur == null && dispo == null) {
			ouvrages = ouvragePort.listerOuvrage();
//			this.addActionMessage("AUCUN Result pour la recherche");
		} else {
			try {
				ouvrages = ouvragePort.trouverOuvrageParIsbnTitreAuteur(isbn, titre, nomAuteur, dispo);

			} catch (Exception pEx) {
				this.addActionMessage("AUCUN OUVRAGE");
			}
		}
		if (ouvrages.isEmpty()) {
			this.addActionMessage("Aucun ouvrage");
		}
		return SUCCESS;
	}

	public String doDetail() {
		idOuvrage = ouvrage.getIdOuvrage();

		try {
			ouvrage = ouvragePort.trouverOuvrageParId(idOuvrage);
			reservations = reservationPort.trouverReservationParOuvrage(ouvrage);
			exemplaire = exemplairePort.trouverExemplaireParOuvrage(idOuvrage);
			dateRetourPrevu = reservationPort.dateRetourPrevu(idOuvrage);
		} catch (BibliothequeException e) {
			e.printStackTrace();
		}
	
		return SUCCESS;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public XMLGregorianCalendar getDateRetourPrevu() {
		return dateRetourPrevu;
	}

	public void setDateRetourPrevu(XMLGregorianCalendar dateRetourPrevu) {
		this.dateRetourPrevu = dateRetourPrevu;
	}

	



//	public String listeParTitre() {
//		// String vResult = ActionSupport.INPUT;
//		titre = ouvrage.getTitre();
//		System.out.println("titre " + titre);
//		if (titre != null) {
//			try {
//				ouvrages = ouvragePort.trouverOuvrageParTitre(titre);
//
//			} catch (Exception pEx) {
//				this.addActionMessage("AUCUN OUVRAGE");
//			}
//
//		}
//		return SUCCESS;
//	}

}
