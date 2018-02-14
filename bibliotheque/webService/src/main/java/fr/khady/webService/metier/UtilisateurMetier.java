package fr.khady.webService.metier;

import java.util.ArrayList;

import fr.khady.webService.entity.Utilisateur;

public interface UtilisateurMetier {

	public void creer(Utilisateur utilisateur);

	public ArrayList<Utilisateur> lister();

	public void modifier(Utilisateur utilisateur);

	public Utilisateur trouverParId(long id_user);
}
