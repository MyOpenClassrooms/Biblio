package com.khady.webServices.Repotory;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.khady.webServices.entity.Utilisateur;



public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {
   
	Utilisateur touverUtilisateurParId(long userId);
    List<Utilisateur> trouverUtilisateurNom(String nom);
}
