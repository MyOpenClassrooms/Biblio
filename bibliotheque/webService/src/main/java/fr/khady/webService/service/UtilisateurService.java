package fr.khady.webService.service;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.springframework.stereotype.Service;

import fr.khady.webService.entity.Utilisateur;


@WebService(name="UtilisateurService")
@SOAPBinding(style = Style.RPC)
public interface UtilisateurService {
	
	@WebMethod
	public void creer(Utilisateur utilisateur);

	@WebMethod
	public ArrayList<Utilisateur> lister();

	@WebMethod
	public void modifier(Utilisateur utilisateur);

	@WebMethod
	public Utilisateur trouverParId(@WebParam(name = "id_user")long id_user);

}
