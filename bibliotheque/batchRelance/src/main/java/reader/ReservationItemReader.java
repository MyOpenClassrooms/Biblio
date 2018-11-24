package reader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import fr.khady.wsBiblioClient.Reservation;
import fr.khady.wsBiblioClient.ReservationService;
import fr.khady.wsBiblioClient.ReservationService_Service;
import fr.khady.wsBiblioClient.UtilisateurService;
import fr.khady.wsBiblioClient.UtilisateurService_Service;

public class ReservationItemReader implements ItemReader<String> {

	
	private List<String> emailList = new ArrayList<String>();
	private int emailCount = 0;

	public  String read() throws Exception, UnexpectedInputException, ParseException{

		ReservationService_Service serviceResa = new ReservationService_Service();
		ReservationService portResa = serviceResa.getReservationServicePort();
		
		List<Reservation> reservations = portResa.listerResevation();
		for (Reservation reservation : reservations) {
			
			if(reservation.getOuvrage().isDisponibilite() == true && reservation.getPosition() == 1 && (reservation.getDateRetourPlusProche().toGregorianCalendar().getTime().compareTo(new Date()) < 0) ) {
				emailList.add(reservation.getUser().getEmail());
			}
	
	}
		String nextEmail = null;
		if (emailCount < emailList.size()) {

			nextEmail =  emailList.get(emailCount);
			emailCount++;

		} 
		return  nextEmail;
	}
}