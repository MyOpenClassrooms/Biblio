package fr.khady.wsBiblio.webService;

import java.sql.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import fr.khady.wsBiblio.dao.CategorieDao;
import fr.khady.wsBiblio.entity.Categorie;
import fr.khady.wsBiblio.exception.BibliothequeException;
import fr.khady.wsBiblio.exception.BibliothequeFault;

@WebService(serviceName = "CategorieService")
public class CategorieService {

	@EJB
	private CategorieDao dao;

	@WebMethod
	public void creerCategorie(@WebParam(name = "libelle") String libelle) {

		dao.creerCategorie(libelle);

	}

	@WebMethod
	public List<Categorie> listerCategorie() {
		return dao.listerCategorie();
	}

	@WebMethod
	public Categorie trouverCategorieParId(@WebParam(name = "idCategorie") long idCat) throws BibliothequeException {

		BibliothequeFault fault = new BibliothequeFault();
		Categorie categorie = dao.trouverCategorieParId(idCat);
		if (categorie != null) {
			return categorie;
		} else
			throw new BibliothequeException("Aucune catégorie trouvée pour l'id " + idCat, fault);
	}

}
