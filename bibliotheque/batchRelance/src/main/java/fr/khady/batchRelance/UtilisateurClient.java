package fr.khady.batchRelance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.khady.wsBiblioClient.BibliothequeException;
import fr.khady.wsBiblioClient.Reservation;
import fr.khady.wsBiblioClient.ReservationService;
import fr.khady.wsBiblioClient.ReservationService_Service;
import fr.khady.wsBiblioClient.UtilisateurService;
import fr.khady.wsBiblioClient.UtilisateurService_Service;

public class UtilisateurClient {
	public static void main(String[] args) {

		UtilisateurService_Service service = new UtilisateurService_Service();
		UtilisateurService port = service.getUtilisateurServicePort();
		
		ReservationService_Service serviceResa = new ReservationService_Service();
		ReservationService portResa = serviceResa.getReservationServicePort();
		
	    ArrayList<String> userEmails = new ArrayList<String>();

		List<String> emails = port.listerUtilisateurRelance();
//		for (int i = 0; i < emails.size(); i++) {
//			System.out.println(emails.get(i));
//		}
		List<Reservation> reservations = portResa.listerResevation();
		for (Reservation reservation : reservations) {
			if((reservation.getDateRetourPlusProche().toGregorianCalendar().getTime()).compareTo(new Date()) > 0 && reservation.getPosition() == 1 && reservation.getOuvrage().isDisponibilite() == true) {
				try {
					portResa.annulerResa(reservation.getUser(), reservation.getOuvrage());
				} catch (BibliothequeException e) {
					e.printStackTrace();
				}
				
			}else if(reservation.getOuvrage().isDisponibilite() == true && reservation.getPosition() == 1 && (reservation.getDateRetourPlusProche().toGregorianCalendar().getTime().compareTo(new Date()) < 0) ) {
				userEmails.add(reservation.getUser().getEmail());
			}
		}
		System.out.println(userEmails);
		
	}

}
