package fr.khady.batchRelance;

import java.util.List;

import fr.khady.wsBiblioClient.UtilisateurService;
import fr.khady.wsBiblioClient.UtilisateurService_Service;

public class UtilisateurClient {
	public static void main(String[] args) {

		UtilisateurService_Service service = new UtilisateurService_Service();
		UtilisateurService port = service.getUtilisateurServicePort();

		List<String> emails = port.listerUtilisateurRelance();
		for (int i = 0; i < emails.size(); i++) {
			System.out.println(emails.get(i));

		}
	}

}
