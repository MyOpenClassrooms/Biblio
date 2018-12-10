package reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import fr.khady.wsBiblioClient.BibliothequeException;
import fr.khady.wsBiblioClient.Exemplaire;
import fr.khady.wsBiblioClient.ExemplaireService;
import fr.khady.wsBiblioClient.ExemplaireService_Service;
import fr.khady.wsBiblioClient.Pret;
import fr.khady.wsBiblioClient.PretService;
import fr.khady.wsBiblioClient.PretService_Service;
import fr.khady.wsBiblioClient.Reservation;
import fr.khady.wsBiblioClient.ReservationService;
import fr.khady.wsBiblioClient.ReservationService_Service;
import fr.khady.wsBiblioClient.Utilisateur;

public class ReservationItemReader implements ItemReader<Utilisateur> {

	private List<Utilisateur> emailList = new ArrayList<Utilisateur>();
	private int emailCount = 0;

	public Utilisateur read() throws Exception, UnexpectedInputException, ParseException {

		Utilisateur utilisateur = new Utilisateur();
		Exemplaire exemp = new Exemplaire();
		List<Pret> prets = new ArrayList<Pret>();

		ReservationService_Service serviceResa = new ReservationService_Service();
		ReservationService portResa = serviceResa.getReservationServicePort();

		PretService_Service servicePret = new PretService_Service();
		PretService portPret = servicePret.getPretServicePort();

		ExemplaireService_Service serviceExemp = new ExemplaireService_Service();
		ExemplaireService portExemp = serviceExemp.getExemplaireServicePort();

		List<Reservation> reservations = portResa.listerResevation();
		Double diffDate = null;

		Utilisateur nextEmail = new Utilisateur();
		if (!(reservations.isEmpty())) {
			for (Reservation reservation : reservations) {

				try {
					diffDate = portResa.verifDelai(reservation.getOuvrage().getIdOuvrage(),
							reservation.getUser().getIdUser());
					exemp = portExemp.trouverExemplaireParOuvrage(reservation.getOuvrage().getIdOuvrage());
					prets = portPret.trouverPretParUtilisateurExemp(utilisateur.getIdUser(), exemp.getIdExemp());
				} catch (BibliothequeException e) {
					e.printStackTrace();
				}

				if (reservation.getOuvrage().isDisponibilite() == true && reservation.getPosition() == 1) {
					if (diffDate == 2.0 && prets.isEmpty()) {
						portResa.annulerResa(reservation.getUser(), reservation.getOuvrage());
					} else if (diffDate > 0.0) {
						emailList.add(reservation.getUser());
					}
				}
			}

		}

		if (emailCount < emailList.size()) {

			nextEmail = emailList.get(emailCount);
			emailCount++;

		}
		return nextEmail;
	}
}