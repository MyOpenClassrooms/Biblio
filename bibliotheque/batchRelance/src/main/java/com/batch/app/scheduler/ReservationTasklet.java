package com.batch.app.scheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.batch.app.mail.ReservationMail;
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

@Component
public class ReservationTasklet implements Tasklet {
 
  @Value("${batch.message}") private String message;
 
  @Autowired
  private ReservationMail mail;
 
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
    System.out.println(message);
	Utilisateur utilisateur = new Utilisateur();
	Exemplaire exemp = new Exemplaire();
	List<Pret> prets = new ArrayList<Pret>();
    List<Utilisateur> emailList = new ArrayList<Utilisateur>();
	int emailCount = 0;

	ReservationService_Service serviceResa = new ReservationService_Service();
	ReservationService portResa = serviceResa.getReservationServicePort();

	PretService_Service servicePret = new PretService_Service();
	PretService portPret = servicePret.getPretServicePort();

	ExemplaireService_Service serviceExemp = new ExemplaireService_Service();
	ExemplaireService portExemp = serviceExemp.getExemplaireServicePort();

	List<Reservation> reservations = portResa.listerResevation();
	Double diffDate = null;

	GregorianCalendar c = new GregorianCalendar();
	Utilisateur nextEmail = new Utilisateur();
	if (!(reservations.isEmpty())) {
		for (Reservation reservation : reservations) {

			try {
				//renvoi la différence entre la date courante et la date d'envoi d'email
				diffDate = portResa.verifDelai(reservation.getOuvrage().getIdOuvrage(),
						reservation.getUser().getIdUser());
				exemp = portExemp.trouverExemplaireParOuvrage(reservation.getOuvrage().getIdOuvrage());
				//vérifie s'il y'a l'utilisateur a un fait un pret pour l'exemplaire
				prets = portPret.trouverPretParUtilisateurExemp(utilisateur.getIdUser(), exemp.getIdExemp());
			} catch (BibliothequeException e) {
				e.printStackTrace();
			}
			//vérifie si l'ouvrage  dans la réservation est disponible et si la position est 1 premiére dans la liste d'attente
			if (reservation.getOuvrage().isDisponibilite() == true && reservation.getPosition() == 1) {
				//vérifie si date_courante - date_envoi_emil = 2jours c'est à dire envoi mail depuis 48 et que l'utilisateur n'a pas fait de pret pour l'ouvrage
				if (diffDate != null && diffDate == 2.0 && prets.isEmpty() ) {
					//on annule la réservation en question
					portResa.annulerResa(reservation.getUser(), reservation.getOuvrage());
				} else if ( reservation.getDateEnvoiEmail() == null && diffDate == null) {//on vérifie si la date envoi mail est null
					c.setTime(new Date());
					XMLGregorianCalendar dateEnvoi = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
					//si c'est le cas on le set à la date du jour
					//reservation.setDateEnvoiEmail(dateEnvoi);
					portResa.modifierResa(dateEnvoi, reservation.getIdResa());
					emailList.add(reservation.getUser());
				}else if (diffDate < 2.0  && diffDate >= 0.0) { //sinan si  date_courante - date_envoi_emil < 2jours alors - 48h depuis l'envoi 
					
					//et on l'ajoute dans la liste des personnes à envoyer un mail
					emailList.add(reservation.getUser());
				}
			}
		}

	}

	if (emailCount < emailList.size()) {

		nextEmail = emailList.get(emailCount);
		emailCount++;

	}
	   Iterator<Utilisateur> it2 = emailList.iterator();
	   while(it2.hasNext()) {
		   Utilisateur user = it2.next();
		   List<Reservation> listResa = portResa.trouverReservationParUtilisateur(user);
		   if (user != null &&  listResa != null) {
		   mail.send(user,listResa);
		   }
	   }
	   
    return RepeatStatus.FINISHED;
  }


}
