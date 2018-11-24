package processor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

import fr.khady.wsBiblioClient.Reservation;
import fr.khady.wsBiblioClient.ReservationService;
import fr.khady.wsBiblioClient.ReservationService_Service;
import fr.khady.wsBiblioClient.Utilisateur;
import fr.khady.wsBiblioClient.UtilisateurService;
import fr.khady.wsBiblioClient.UtilisateurService_Service;

public class ReservationItemProcessor implements ItemProcessor<String, MimeMessage> {

	private static final Logger log = LoggerFactory.getLogger(ReservationItemProcessor.class);

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private VelocityEngine engine;
	private String sender;

	
	public ReservationItemProcessor(String sender) {
		this.sender = sender;
	}

	@SuppressWarnings("deprecation")
	@Override
	public MimeMessage process(String string) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		helper.setFrom(sender);
		
		Utilisateur utilisateur = new Utilisateur();
		ReservationService_Service serviceResa = new ReservationService_Service();
		ReservationService portResa = serviceResa.getReservationServicePort();

		List<Reservation> reservations = portResa.listerResevation();
		for (Reservation reservation : reservations) {
			if(reservation.getOuvrage().isDisponibilite() == true && reservation.getPosition() == 1 && (reservation.getDateRetourPlusProche().toGregorianCalendar().getTime().compareTo(new Date()) < 0) ) {
				utilisateur = reservation.getUser();
			}
		
			helper.setTo(utilisateur.getEmail());
		
		
//		helper.setCc(sender);
		helper.setSubject(VelocityEngineUtils.mergeTemplateIntoString(engine, "email-subject.vm", "UTF-8", null));
		helper.setText(VelocityEngineUtils.mergeTemplateIntoString(engine, "email-body.vm", "UTF-8", null));
		
		
		log.info("Preparing message for: " + utilisateur.getEmail());
		
		}
		return message;
	}


	
}
