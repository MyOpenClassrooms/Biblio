package fr.khady.webService.service;

import java.util.ArrayList;


import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import fr.khady.webService.entity.Utilisateur;
import fr.khady.webService.metier.UtilisateurMetier;


@WebService(endpointInterface="fr.khady.webService.service.UtilisateurService")
public class UtilisateurServiceImpl implements UtilisateurService {
	
	
	private UtilisateurMetier metier;

	public void setMetier(UtilisateurMetier metier) {
		this.metier = metier;
	}
	
	@Override
	public void creer(Utilisateur utilisateur) {
		metier.creer(utilisateur);

	}

	@Override
	public ArrayList<Utilisateur> lister() {
		System.out.println(metier);
		return metier.lister();
	}

	@Override
	public void modifier(Utilisateur utilisateur) {
		metier.modifier(utilisateur);

	}

	@Override
	public Utilisateur trouverParId(long id_user) {
		return metier.trouverParId(id_user);
	}

}
