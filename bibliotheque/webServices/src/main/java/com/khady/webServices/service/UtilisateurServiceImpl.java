package com.khady.webServices.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.khady.webServices.Repotory.UtilisateurRepository;
import com.khady.webServices.entity.Utilisateur;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	private UtilisateurRepository utilisateurRepository;
	
	@Override
	public List<Utilisateur> lister() {
		List<Utilisateur> utilisateurs = new ArrayList<>();
		utilisateurRepository.findAll().forEach(e -> utilisateurs.add(e));
		return utilisateurs;
	}

	@Override
	public Utilisateur trouverUtilisateurParId(long userId) {
		Utilisateur utilisateur = utilisateurRepository.touverUtilisateurParId(userId);
		return utilisateur;
	}

	@Override
	public boolean ajouterUtilisateur(Utilisateur utilisateur) {
		List<Utilisateur> utilisateurs = utilisateurRepository.trouverUtilisateurNom(utilisateur.getNom());
		if(utilisateurs.size() > 0) {
			return false;
		}else {
			
			utilisateur = utilisateurRepository.save(utilisateur);
			return true;
		}
		
	}

	@Override
	public void modifierUtilisateur(Utilisateur utilisateur) {
		utilisateurRepository.save(utilisateur);
	}

}
