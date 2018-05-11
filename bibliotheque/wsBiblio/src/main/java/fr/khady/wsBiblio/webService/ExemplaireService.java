package fr.khady.wsBiblio.webService;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import fr.khady.wsBiblio.dao.ExemplaireDao;
import fr.khady.wsBiblio.entity.Exemplaire;
import fr.khady.wsBiblio.exception.BibliothequeException;
import fr.khady.wsBiblio.exception.BibliothequeFault;

@WebService(serviceName = "ExemplaireService")
public class ExemplaireService {
	
	@EJB
	private ExemplaireDao dao;

	@WebMethod
	public void creerExemplaire(@WebParam(name = "nbreExemp") int nbrExemp, @WebParam(name = "numRayon") int numRayon,
			@WebParam(name = "ouvrage") long idOuvrage ){

		    dao.creerExemplaire(nbrExemp, numRayon, idOuvrage);

	}

	@WebMethod
	public List<Exemplaire> listerExemplaire() {
		return dao.listerExemplaire();
	}


	@WebMethod
	public Exemplaire trouverExemplaireParId(@WebParam(name = "idExemp") long idExemp) throws BibliothequeException {
		
		BibliothequeFault fault = new BibliothequeFault();
		Exemplaire exemplaire = dao.trouverExemplaireParId(idExemp);
		if (exemplaire != null) {
			return exemplaire;
		} else
			throw new BibliothequeException("Aucun exemplaire trouvé pour l'id " + idExemp, fault);
	}

	@WebMethod
	public Exemplaire trouverExemplaireParOuvrage(@WebParam(name = "idOuvrage") long idOuvrage) throws BibliothequeException {
		
		BibliothequeFault fault = new BibliothequeFault();
		Exemplaire exemplaire = dao.trouverExemplaireParOuvrage(idOuvrage);
		if (exemplaire != null) {
			return exemplaire;
		} else
			throw new BibliothequeException("Aucun exemplaire trouvé pour l'ouvrage " + idOuvrage, fault);
	}

}
