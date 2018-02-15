package com.khady.webServices.service;

import java.util.List;

import com.khady.webServices.entity.Utilisateur;
public interface UtilisateurService {

	List<Utilisateur> lister();

	Utilisateur trouverUtilisateurParId(long userId);

	boolean ajouterUtilisateur(Utilisateur utilisateur);

	void modifierUtilisateur(Utilisateur utilisateur);
}
