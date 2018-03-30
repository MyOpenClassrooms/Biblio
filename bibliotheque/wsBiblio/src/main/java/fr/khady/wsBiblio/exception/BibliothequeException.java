package fr.khady.wsBiblio.exception;

import javax.xml.ws.WebFault;

@WebFault(name = "BibliothequeException")
public class BibliothequeException extends Exception {
	private static final long serialVersionUID = 1L;

	 /*
    on d�clare une instance de LifeLeftFault qu'on cr��e pr�c�demment
    afin de r�cup�rer ensuite les messages et codes d'erreurs � renvoyer dans cette exception
     */
	private BibliothequeFault fault;

	 /*
    On cr�e un constructeur qui prend en param�tre un message d'erreur et un objet BibliothequeFault
    avec plus de d�tails sur l'erreur
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
