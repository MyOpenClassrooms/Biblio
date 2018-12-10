package fr.khady.webAppliBiblio.action;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import fr.khady.wsBiblioClient.BibliothequeException;
import fr.khady.wsBiblioClient.Pret;
import fr.khady.wsBiblioClient.PretService;
import fr.khady.wsBiblioClient.PretService_Service;
import fr.khady.wsBiblioClient.Utilisateur;

public class GestionPretAction extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 1L;

	public Pret pret = new Pret();
	public List<Pret> prets;
	public Date dateRetourPrevu;
	Utilisateur utilisateur = new Utilisateur();
	private Map<String, Object> session;
	 
	PretService_Service service = new PretService_Service();
	PretService pretPort = service.getPretServicePort();

	public String index() {
		utilisateur = (Utilisateur) this.session.get("utilisateur");
		try {
			prets = pretPort.trouverPretParUtilisateur(utilisateur.getIdUser());
		} catch (BibliothequeException e) {
			e.printStackTrace();
		}
		if (prets.isEmpty()) {
			this.addActionMessage("Aucun pret");
		}
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> pSession) {
		 this.session = pSession;
		
	}
	
	public String prolonger() {
		utilisateur = (Utilisateur) this.session.get("utilisateur");
		// date_retour = pret.getDateRetourPrevu();
		int executeUpdate = 0;
		if (dateRetourPrevu != null && dateRetourPrevu.compareTo(new Date()) > 0) {
			System.out.println(" dateRetour " + dateRetourPrevu);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(dateRetourPrevu);
			XMLGregorianCalendar dateConverti = null;
			try {
				dateConverti = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
				
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
				
				executeUpdate = pretPort.modifierPret(dateConverti);
				if (executeUpdate == 1) {
				this.addActionMessage("Pret bien prolongé");
			}
		}else {
			this.addActionError("Le pret ne peut pas être prolongé: la date butoire dépassée");
		}
		
		try {
		prets = pretPort.trouverPretParUtilisateur(utilisateur.getIdUser());
	} catch (BibliothequeException e) {
		e.printStackTrace();
	}
		return SUCCESS;
	}

}
