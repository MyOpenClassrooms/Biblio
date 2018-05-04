package fr.khady.webAppliBiblio.action;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.opensymphony.xwork2.ActionSupport;

import fr.khady.wsBiblioClient.BibliothequeException;
import fr.khady.wsBiblioClient.Pret;
import fr.khady.wsBiblioClient.PretService;
import fr.khady.wsBiblioClient.PretService_Service;

public class GestionPretAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	public Pret pret = new Pret();
	public List<Pret> prets;
	public Date dateRetourPrevu;
	PretService_Service service = new PretService_Service();
	PretService pretPort = service.getPretServicePort();

	public String index() {

		prets = pretPort.listerPret();

		return SUCCESS;
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
//				prets = pretPort.trouverPretParDateRetourPrevu(dateConverti);
					System.out.println(" pret " + prets);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(" dateConverti " + dateConverti);
			    int executeUpdate =	pretPort.modifierPret(dateConverti);
			
			if(executeUpdate == 1) {
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
