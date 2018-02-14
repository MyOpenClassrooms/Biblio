package fr.khady.webService.metier;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.khady.webService.dao.UtilisateurDao;
import fr.khady.webService.entity.Utilisateur;
import fr.khady.webService.service.UtilisateurServiceImpl;

public class UtilisateurMetierImpl implements UtilisateurMetier {

	private UtilisateurDao dao;
	
	public void setDao(UtilisateurDao dao) {
		this.dao = dao;
	}
	
	@Override
	@Transactional
	public void creer(Utilisateur utilisateur) {
		dao.creer(utilisateur);

	}

	@Override
	@Transactional
	public ArrayList<Utilisateur> lister() {
		return dao.lister();
	}

	@Override
	@Transactional
	public void modifier(Utilisateur utilisateur) {
		dao.modifier(utilisateur);

	}

	@Override
	@Transactional
	public Utilisateur trouverParId(long id_user) {
		return dao.trouverParId(id_user);
	}

}
