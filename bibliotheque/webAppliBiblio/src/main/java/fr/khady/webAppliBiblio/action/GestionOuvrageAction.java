package fr.khady.webAppliBiblio.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import fr.khady.wsBiblioClient.Auteur;
import fr.khady.wsBiblioClient.BibliothequeException;
import fr.khady.wsBiblioClient.Exemplaire;
import fr.khady.wsBiblioClient.ExemplaireService;
import fr.khady.wsBiblioClient.ExemplaireService_Service;
import fr.khady.wsBiblioClient.Ouvrage;
import fr.khady.wsBiblioClient.OuvrageService;
import fr.khady.wsBiblioClient.OuvrageService_Service;

public class GestionOuvrageAction extends ActionSupport {

	private static final long serialVersionUID = -4423447871500036097L;

	private Long idOuvrage;
	private String titre;
	private String isbn;
	private String nomAuteur;
	private Boolean dispo;
	public Ouvrage ouvrage = new Ouvrage();
	public Auteur auteur = new Auteur();
	public Exemplaire exemplaire = new Exemplaire();
	public List<Ouvrage> ouvrages;
	OuvrageService_Service service = new OuvrageService_Service();
	OuvrageService ouvragePort = service.getOuvrageServicePort();
	
	ExemplaireService_Service serviceEx = new ExemplaireService_Service();
	ExemplaireService exemplairePort = serviceEx.getExemplaireServicePort();

	public String index() {
		titre = ouvrage.getTitre();
		isbn = ouvrage.getIsbn();
		nomAuteur = auteur.getNom();
//		dispo = ouvrage.isDisponibilite();
		
		System.out.println("dispo " + dispo);
		ouvrages = ouvragePort.listerOuvrage();
		if (titre == null && isbn == null && nomAuteur == null && dispo == null ) {
			ouvrages = ouvragePort.listerOuvrage();
//			this.addActionMessage("AUCUN Result pour la recherche");
		} else{
			try {
				ouvrages = ouvragePort.trouverOuvrageParIsbnTitreAuteur(isbn, titre, nomAuteur, dispo);

			} catch (Exception pEx) {
				this.addActionMessage("AUCUN OUVRAGE");
			}
		}
		
			

		return SUCCESS;
	}

	public String doDetail() {
		idOuvrage = ouvrage.getIdOuvrage();
		try {
			ouvrage = ouvragePort.trouverOuvrageParId(idOuvrage);
		} catch (BibliothequeException e) {
			e.printStackTrace();
		}
		
		try {
			exemplaire = exemplairePort.trouverExemplaireParOuvrage(idOuvrage);
			System.out.println("exem " + exemplaire);
		} catch (BibliothequeException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

//	public String listeParTitre() {
//		// String vResult = ActionSupport.INPUT;
//		titre = ouvrage.getTitre();
//		System.out.println("titre " + titre);
//		if (titre != null) {
//			try {
//				ouvrages = ouvragePort.trouverOuvrageParTitre(titre);
//
//			} catch (Exception pEx) {
//				this.addActionMessage("AUCUN OUVRAGE");
//			}
//
//		}
//		return SUCCESS;
//	}
}
