package processor;

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

import fr.khady.wsBiblioClient.Utilisateur;
import fr.khady.wsBiblioClient.UtilisateurService;
import fr.khady.wsBiblioClient.UtilisateurService_Service;

public class UtilisateurItemProcessor implements ItemProcessor<String, MimeMessage> {

	private static final Logger log = LoggerFactory.getLogger(UtilisateurItemProcessor.class);

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private VelocityEngine engine;
	private String sender;

	
	public UtilisateurItemProcessor(String sender) {
		this.sender = sender;
	}

	@SuppressWarnings("deprecation")
	@Override
	public MimeMessage process(String string) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		helper.setFrom(sender);
		
		Utilisateur utilisateur = new Utilisateur();
		UtilisateurService_Service service = new UtilisateurService_Service();
		UtilisateurService port = service.getUtilisateurServicePort();

		List<String> emails = port.listerUtilisateurRelance();
		for (int i = 0; i < emails.size(); i++) {
			utilisateur.setEmail(emails.get(i));
			helper.setTo(utilisateur.getEmail());
		
		
//		helper.setCc(sender);
		helper.setSubject(VelocityEngineUtils.mergeTemplateIntoString(engine, "email-subject.vm", "UTF-8", null));
		helper.setText(VelocityEngineUtils.mergeTemplateIntoString(engine, "email-body.vm", "UTF-8", null));
		
		
		log.info("Preparing message for: " + utilisateur.getEmail());
		
		}
		return message;
	}


	
}
