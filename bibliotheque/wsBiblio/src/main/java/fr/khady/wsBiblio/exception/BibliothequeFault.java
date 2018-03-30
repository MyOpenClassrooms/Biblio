package fr.khady.wsBiblio.exception;

public class BibliothequeFault {


    // code d'erreur
    private String faultCode;

    //message d'erreur
    private String faultString;

    public String getFaultCode() {
        return faultCode;
    }

    public void setFaultCode(String faultCode) {
        this.faultCode = faultCode;
    }

    public String getFaultString() {
        return faultString;
    }

    public void setFaultString(String faultString) {
        this.faultString = faultString;
    }
}
