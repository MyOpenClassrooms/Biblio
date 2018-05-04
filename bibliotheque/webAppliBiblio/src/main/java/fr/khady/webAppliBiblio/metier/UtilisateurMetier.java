package fr.khady.webAppliBiblio.metier;

import java.util.List;


import fr.khady.wsBiblioClient.Utilisateur;
import fr.khady.wsBiblioClient.UtilisateurService;
import fr.khady.wsBiblioClient.UtilisateurService_Service;

public class UtilisateurMetier {

	
	UtilisateurService_Service service = new UtilisateurService_Service();
	UtilisateurService utilisateurPort = service.getUtilisateurServicePort();
	

	public List<Utilisateur> listerUtilisateur(){
		
		return utilisateurPort.listerUtilisateur();
		
	}
	
	
	
}
