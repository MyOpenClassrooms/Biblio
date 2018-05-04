package fr.khady.webAppliBiblio.action;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionSupport;

import fr.khady.wsBiblioClient.UtilisateurService;
import fr.khady.wsBiblioClient.UtilisateurService_Service;

public class LoginAction extends ActionSupport {

	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void validate() {
		if(StringUtils.isEmpty(getEmail())) {
			addFieldError("email", "email pas null");
		}
	}
	
	public String execute() {
		
		UtilisateurService_Service service = new UtilisateurService_Service();
		UtilisateurService utilisateurPort = service.getUtilisateurServicePort();
		
		
		
		return SUCCESS;
		
	}
	
}
