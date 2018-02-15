package com.khady.webServices.endpoints;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.khady.soap.AjouterUtilisateurRequest;
import com.khady.soap.AjouterUtilisateurResponse;
import com.khady.soap.ListerUtilisateurResponse;
import com.khady.soap.ModifierUtilisateurRequest;
import com.khady.soap.ModifierUtilisateurResponse;
import com.khady.soap.ServiceStatus;
import com.khady.soap.TrouverUtilisateurParIdRequest;
import com.khady.soap.TrouverUtilisateurParIdResponse;
import com.khady.soap.UtilisateurInfo;
import com.khady.webServices.entity.Utilisateur;
import com.khady.webServices.service.UtilisateurService;

@Endpoint
public class UtilisateurEnpoint {

	private static final String NAMESPACE_URI = "http://khady.com/soap";

	@Autowired
	private UtilisateurService utilisateurService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "trouverUtilisateurParIdRequest")
	@ResponsePayload
	public TrouverUtilisateurParIdResponse trouverUtilisateurParId(
			@RequestPayload TrouverUtilisateurParIdRequest request) {
		TrouverUtilisateurParIdResponse response = new TrouverUtilisateurParIdResponse();
		UtilisateurInfo utilisateurInfo = new UtilisateurInfo();
		BeanUtils.copyProperties(utilisateurService.trouverUtilisateurParId(request.getUserId()), utilisateurInfo);
		response.setUtilisateurInfo(utilisateurInfo);
		return response;

	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "listerUtilisateurRequest")
	@ResponsePayload
	public ListerUtilisateurResponse listerUtilisateur() {
		ListerUtilisateurResponse response = new ListerUtilisateurResponse();
		List<UtilisateurInfo> utilisateurInfoList = new ArrayList<>();
		List<Utilisateur> utilisateurList = utilisateurService.lister();
		for (int i = 0; i < utilisateurList.size(); i++) {
			UtilisateurInfo ob = new UtilisateurInfo();
			BeanUtils.copyProperties(utilisateurList.get(i), ob);
			utilisateurInfoList.add(ob);
		}
		response.getUtilisateurInfo().addAll(utilisateurInfoList);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "ajouterUtilisateurRequest")
	@ResponsePayload
	public AjouterUtilisateurResponse addArticle(@RequestPayload AjouterUtilisateurRequest request) {
		AjouterUtilisateurResponse response = new AjouterUtilisateurResponse();
		ServiceStatus serviceStatus = new ServiceStatus();
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNom(request.getNom());
		utilisateur.setPrenom(request.getPrenom());
		utilisateur.setAdress(request.getAdress());
		utilisateur.setEmail(request.getEmail());
		utilisateur.setLogin(request.getLogin());
		utilisateur.setPassword(request.getPassword());
		utilisateur.setPhoto(request.getPhoto());
		boolean flag = utilisateurService.ajouterUtilisateur(utilisateur);
		if (flag == false) {
			serviceStatus.setStatusCode("CONFLIT");
			serviceStatus.setMessage("Contenu déjà disponible");
			response.setServiceStatus(serviceStatus);
		} else {
			UtilisateurInfo utilisateurInfo = new UtilisateurInfo();
			BeanUtils.copyProperties(utilisateur, utilisateurInfo);
			response.setUtilisateurInfo(utilisateurInfo);
			serviceStatus.setStatusCode("SUCCES");
			serviceStatus.setMessage("Contenu ajouté avec succès");
			response.setServiceStatus(serviceStatus);
		}
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "modifierUtilisateurRequest")
	@ResponsePayload
	public ModifierUtilisateurResponse updateArticle(@RequestPayload ModifierUtilisateurRequest request) {
		Utilisateur utilisateur = new Utilisateur();
		BeanUtils.copyProperties(request.getUtilisateurInfo(), utilisateur);
		utilisateurService.modifierUtilisateur(utilisateur);
    	        ServiceStatus serviceStatus = new ServiceStatus();
    	        serviceStatus.setStatusCode("SUCCES");
    	        serviceStatus.setMessage("Contenu modifié avec succès");
    	        ModifierUtilisateurResponse response = new ModifierUtilisateurResponse();
    	        response.setServiceStatus(serviceStatus);
    	        return response;
	}
}