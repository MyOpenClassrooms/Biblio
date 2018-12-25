package com.batch.app.mail;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import fr.khady.wsBiblioClient.Pret;
import fr.khady.wsBiblioClient.Reservation;
import fr.khady.wsBiblioClient.Utilisateur;



@Component
public class RappelFiveDaysMail {
	
	@Value("${batch.mail}") 
	private String mail;
	
	@Value("${batch.password}") 
	private String password;
	
	public void send(Utilisateur user, List<Pret> listPret) {
		// Create the email message
		  HtmlEmail email = new HtmlEmail();
		  
		  email.setHostName("smtp.gmail.com");
		  email.setSmtpPort(587);
		  email.setAuthenticator(new DefaultAuthenticator(mail, password));
		  email.setSSLOnConnect(true);
		  try {
			  email.addTo(user.getEmail(), user.getNom());
			  email.setFrom(mail, "Moi");
			  email.setSubject("Retour prêt proche");
			  
			  String message = "<html>Bonjour "+user.getNom()+ " " + user.getPrenom();
			  message+="<br>La date de retour de prêt des ouvrages suivants : <ul>";
			  Iterator<Pret> it = listPret.iterator();
	        	while(it.hasNext()) {
	        		Pret pret = it.next();
	        		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	        	    fmt.setCalendar(pret.getDateRetourPrevu().toGregorianCalendar());
	        	    String dateFormatted = fmt.format(pret.getDateRetourPrevu().toGregorianCalendar().getTime());
	        		
	        		message+="<li>\""+pret.getExemplaire().getOuvrage().getTitre()+"\" retour le "+dateFormatted+"</li> ";
	        	
	        
	        	}
	        	message+="</ul>";
	        	message+="<br> arrive bientôt à expiration.";
        		message+="<br>Merci d'en tenir compte.";
	        		
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
