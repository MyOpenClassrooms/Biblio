package reader;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import fr.khady.wsBiblioClient.UtilisateurService;
import fr.khady.wsBiblioClient.UtilisateurService_Service;

public class UtilisateurItemReader implements ItemReader<String> {

	
	private List<String> emailList;
	private int emailCount = 0;

	public String read() throws Exception, UnexpectedInputException, ParseException{

		UtilisateurService_Service service = new UtilisateurService_Service();
		UtilisateurService port = service.getUtilisateurServicePort();
		emailList = port.listerUtilisateurRelance();
		String nextEmail = null;
		if (emailCount < emailList.size()) {

			nextEmail =  port.listerUtilisateurRelance().get(emailCount);
			emailCount++;

		} 
		return  nextEmail;
	}
}