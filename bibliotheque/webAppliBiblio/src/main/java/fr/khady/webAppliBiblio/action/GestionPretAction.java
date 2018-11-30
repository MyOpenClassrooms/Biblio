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

		// date_retour = pret.getDateRetourPrevu();

		if (dateRetourPrevu != null) {
			System.out.println(" dateRetour " + dateRetourPrevu);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(dateRetourPrevu);
			XMLGregorianCalendar dateConverti;
			try {
				dateConverti = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
				try {
					// prets = pretPort.trouverPretParDateRetourPrevu(dateConverti);
					System.out.println(" pret " + prets);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(" dateConverti " + dateConverti);
				int executeUpdate = pretPort.modifierPret(dateConverti);

				if (executeUpdate == 1) {
					this.addActionMessage("Pret bien prolongé");
				}
				// pretPort.listerPret();

			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
		}

		prets = pretPort.listerPret();
		return SUCCESS;
	}

}
