package com.batch.app.mail;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import fr.khady.wsBiblioClient.Pret;
import fr.khady.wsBiblioClient.Reservation;
import fr.khady.wsBiblioClient.Utilisateur;



@Component
public class ReservationMail {
	
	@Value("${batch.mail}") 
	private String mail;
	
	@Value("${batch.password}") 
	private String password;
	
	public void send(Utilisateur user, List<Reservation> listResa) {
		// Create the email message
		  HtmlEmail email = new HtmlEmail();
		  final Logger log = LoggerFactory.getLogger(ReservationMail.class);
		  email.setHostName("smtp.gmail.com");
		  email.setSmtpPort(587);
		  email.setAuthenticator(new DefaultAuthenticator(mail, password));
		  email.setSSLOnConnect(true);
		  try {
			  email.addTo(user.getEmail(), user.getNom());
			  email.setFrom(mail, "Moi");
			  email.setSubject("Ouvrage disponible");
			  log.info("Preparing message for: " + user.getEmail());
			  String message = "<html>Bonjour "+user.getNom()+ " " + user.getPrenom();
			  message+="<br>A compter ce jour vous avez 48h pour faire le pret et recupérer l'ouvrage ";
			  Iterator<Reservation> it = listResa.iterator();
	        	while(it.hasNext()) {
	        		Reservation resa = it.next();
	        		if (resa != null) {
	        		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	        	    fmt.setCalendar(resa.getDateRetourPlusProche().toGregorianCalendar());
	        	    String dateFormatted = fmt.format(resa.getDateRetourPlusProche().toGregorianCalendar().getTime());
	        		
	        		message+="\""+resa.getOuvrage().getTitre()+"\".";
	        		message+="<br>Passé ce délai votre réservation sera annulée.";
	        		// mettre date de retour + à la ligne
	        	}
	        	}
	       
	        		
			  message+="<br> Merci <br> Cordialement <br>";
			
			  email.setHtmlMsg(message);
			  
			  //tu dois corriger la taille des images
			  
			  
			  // set the alternative message
			  email.setTextMsg("Your email client does not support HTML messages");

			  // send the email
			  email.send();  
			  System.out.println("Mail has been sent successfully");
		  }
		 catch (EmailException e) {
			// TODO Auto-generated catch block
			 System.out.println("Unable to send an email" + e.getMessage());
		}
		
	}
	
	

}
