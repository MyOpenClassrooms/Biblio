package com.batch.app.scheduler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.batch.app.mail.RappelFiveDaysMail;
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
public class RappelFiveDaysTasklet implements Tasklet {

	@Value("${batch.message}")
	private String message;

	@Autowired
	private RappelFiveDaysMail mail;

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

		prets = portPret.listerPret();
		List<Object> diffDate = null;
		Utilisateur nextEmail = new Utilisateur();
		if (!(prets.isEmpty())) {
			for (Pret pret : prets) {

				// renvoie la différence entre la date courante et la date retour pret
				diffDate = portPret.verifDelaiFiveDays(pret.getUser().getIdUser());
				for (Object double1 : diffDate) {
				
				if (double1 != null) {
				// vérifie si l'option rappel est à true pour l'utilisateur et et que la diff
				// est inf ou égal a 5jours
				if (pret.getUser().isRappel() == true  && new Double(double1.toString()) >= -5.0 && new Double(double1.toString()) <= 0.0) {
					// dans ce cas on ajoute l'utilisateur à la liste
					emailList.add(pret.getUser());
				}
				}
				
				}
			}

		}

		if (emailCount < emailList.size()) {

			nextEmail = emailList.get(emailCount);
			emailCount++;

		}
		Iterator<Utilisateur> it2 = emailList.iterator();
		while (it2.hasNext()) {
			Utilisateur user = it2.next();
			List<Pret> listPret = portPret.trouverPretParUtilisateur(user.getIdUser());
			mail.send(user, listPret);

		}

		return RepeatStatus.FINISHED;
	}

}
