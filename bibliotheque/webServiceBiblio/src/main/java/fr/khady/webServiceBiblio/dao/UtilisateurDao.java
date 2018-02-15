package fr.khady.webServiceBiblio.dao;

import java.util.ArrayList;

import fr.khady.webServiceBiblio.entity.Utilisateur;


public interface UtilisateurDao {

	public void creer(Utilisateur utilisateur);

	public ArrayList<Utilisateur> lister();
	
	public void modifier(Utilisateur utilisateur);

	public Utilisateur trouverParId(long id_user);
	
	
}
