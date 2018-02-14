package fr.khady.webService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import fr.khady.webService.entity.Utilisateur;
import fr.khady.webService.metier.UtilisateurMetier;

@Controller
public class HomeController {
	
	@Autowired
	private UtilisateurMetier metier;
	
	@RequestMapping(value="/")
	public String index(Model model){
	model.addAttribute("utilisateur", new Utilisateur());
	System.out.println(metier);
	model.addAttribute("utilisateurs", metier.lister());
	return "utilisateurs";
	}
	
	
}
