package fr.khady.wsBiblio.exception;

import javax.xml.ws.WebFault;

@WebFault(name = "BibliothequeException")
public class BibliothequeException extends Exception {
	private static final long serialVersionUID = 1L;

	 /*
    on déclare une instance de LifeLeftFault qu'on créée précédemment
    afin de récupérer ensuite les messages et codes d'erreurs à renvoyer dans cette exception
     */
	private BibliothequeFault fault;

	 /*
    On crée un constructeur qui prend en paramètre un message d'erreur et un objet BibliothequeFault
    avec plus de détails sur l'erreur
    */
	public BibliothequeException(String message, BibliothequeFault fault) {
		super(message);
		this.fault = fault;
	}

	public BibliothequeException(String message, Throwable cause, BibliothequeFault fault) {
		super(message, cause);
		this.fault = fault;
	}

	public BibliothequeFault getFaultInfo() {
		return fault;
	}
}
