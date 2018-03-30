
package fr.khady.wsBiblioClient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.khady.wsBiblioClient package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ListerUtilisateur_QNAME = new QName("http://webService.wsBiblio.khady.fr/", "listerUtilisateur");
    private final static QName _ModifierUtilisateur_QNAME = new QName("http://webService.wsBiblio.khady.fr/", "modifierUtilisateur");
    private final static QName _ListerUtilisateurResponse_QNAME = new QName("http://webService.wsBiblio.khady.fr/", "listerUtilisateurResponse");
    private final static QName _BibliothequeException_QNAME = new QName("http://webService.wsBiblio.khady.fr/", "BibliothequeException");
    private final static QName _CreerUtilisateur_QNAME = new QName("http://webService.wsBiblio.khady.fr/", "creerUtilisateur");
    private final static QName _CreerUtilisateurResponse_QNAME = new QName("http://webService.wsBiblio.khady.fr/", "creerUtilisateurResponse");
    private final static QName _ListerUtilisateurRelance_QNAME = new QName("http://webService.wsBiblio.khady.fr/", "listerUtilisateurRelance");
    private final static QName _TrouverUtilisateurParId_QNAME = new QName("http://webService.wsBiblio.khady.fr/", "trouverUtilisateurParId");
    private final static QName _ModifierUtilisateurResponse_QNAME = new QName("http://webService.wsBiblio.khady.fr/", "modifierUtilisateurResponse");
    private final static QName _TrouverUtilisateurParIdResponse_QNAME = new QName("http://webService.wsBiblio.khady.fr/", "trouverUtilisateurParIdResponse");
    private final static QName _ListerUtilisateurRelanceResponse_QNAME = new QName("http://webService.wsBiblio.khady.fr/", "listerUtilisateurRelanceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.khady.wsBiblioClient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ModifierUtilisateur }
     * 
     */
    public ModifierUtilisateur createModifierUtilisateur() {
        return new ModifierUtilisateur();
    }

    /**
     * Create an instance of {@link ListerUtilisateur }
     * 
     */
    public ListerUtilisateur createListerUtilisateur() {
        return new ListerUtilisateur();
    }

    /**
     * Create an instance of {@link ListerUtilisateurResponse }
     * 
     */
    public ListerUtilisateurResponse createListerUtilisateurResponse() {
        return new ListerUtilisateurResponse();
    }

    /**
     * Create an instance of {@link ModifierUtilisateurResponse }
     * 
     */
    public ModifierUtilisateurResponse createModifierUtilisateurResponse() {
        return new ModifierUtilisateurResponse();
    }

    /**
     * Create an instance of {@link TrouverUtilisateurParIdResponse }
     * 
     */
    public TrouverUtilisateurParIdResponse createTrouverUtilisateurParIdResponse() {
        return new TrouverUtilisateurParIdResponse();
    }

    /**
     * Create an instance of {@link TrouverUtilisateurParId }
     * 
     */
    public TrouverUtilisateurParId createTrouverUtilisateurParId() {
        return new TrouverUtilisateurParId();
    }

    /**
     * Create an instance of {@link BibliothequeFault }
     * 
     */
    public BibliothequeFault createBibliothequeFault() {
        return new BibliothequeFault();
    }

    /**
     * Create an instance of {@link CreerUtilisateur }
     * 
     */
    public CreerUtilisateur createCreerUtilisateur() {
        return new CreerUtilisateur();
    }

    /**
     * Create an instance of {@link CreerUtilisateurResponse }
     * 
     */
    public CreerUtilisateurResponse createCreerUtilisateurResponse() {
        return new CreerUtilisateurResponse();
    }

    /**
     * Create an instance of {@link ListerUtilisateurRelance }
     * 
     */
    public ListerUtilisateurRelance createListerUtilisateurRelance() {
        return new ListerUtilisateurRelance();
    }

    /**
     * Create an instance of {@link ListerUtilisateurRelanceResponse }
     * 
     */
    public ListerUtilisateurRelanceResponse createListerUtilisateurRelanceResponse() {
        return new ListerUtilisateurRelanceResponse();
    }

    /**
     * Create an instance of {@link Utilisateur }
     * 
     */
    public Utilisateur createUtilisateur() {
        return new Utilisateur();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListerUtilisateur }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.wsBiblio.khady.fr/", name = "listerUtilisateur")
    public JAXBElement<ListerUtilisateur> createListerUtilisateur(ListerUtilisateur value) {
        return new JAXBElement<ListerUtilisateur>(_ListerUtilisateur_QNAME, ListerUtilisateur.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifierUtilisateur }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.wsBiblio.khady.fr/", name = "modifierUtilisateur")
    public JAXBElement<ModifierUtilisateur> createModifierUtilisateur(ModifierUtilisateur value) {
        return new JAXBElement<ModifierUtilisateur>(_ModifierUtilisateur_QNAME, ModifierUtilisateur.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListerUtilisateurResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.wsBiblio.khady.fr/", name = "listerUtilisateurResponse")
    public JAXBElement<ListerUtilisateurResponse> createListerUtilisateurResponse(ListerUtilisateurResponse value) {
        return new JAXBElement<ListerUtilisateurResponse>(_ListerUtilisateurResponse_QNAME, ListerUtilisateurResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BibliothequeFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.wsBiblio.khady.fr/", name = "BibliothequeException")
    public JAXBElement<BibliothequeFault> createBibliothequeException(BibliothequeFault value) {
        return new JAXBElement<BibliothequeFault>(_BibliothequeException_QNAME, BibliothequeFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreerUtilisateur }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.wsBiblio.khady.fr/", name = "creerUtilisateur")
    public JAXBElement<CreerUtilisateur> createCreerUtilisateur(CreerUtilisateur value) {
        return new JAXBElement<CreerUtilisateur>(_CreerUtilisateur_QNAME, CreerUtilisateur.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreerUtilisateurResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.wsBiblio.khady.fr/", name = "creerUtilisateurResponse")
    public JAXBElement<CreerUtilisateurResponse> createCreerUtilisateurResponse(CreerUtilisateurResponse value) {
        return new JAXBElement<CreerUtilisateurResponse>(_CreerUtilisateurResponse_QNAME, CreerUtilisateurResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListerUtilisateurRelance }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.wsBiblio.khady.fr/", name = "listerUtilisateurRelance")
    public JAXBElement<ListerUtilisateurRelance> createListerUtilisateurRelance(ListerUtilisateurRelance value) {
        return new JAXBElement<ListerUtilisateurRelance>(_ListerUtilisateurRelance_QNAME, ListerUtilisateurRelance.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrouverUtilisateurParId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.wsBiblio.khady.fr/", name = "trouverUtilisateurParId")
    public JAXBElement<TrouverUtilisateurParId> createTrouverUtilisateurParId(TrouverUtilisateurParId value) {
        return new JAXBElement<TrouverUtilisateurParId>(_TrouverUtilisateurParId_QNAME, TrouverUtilisateurParId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifierUtilisateurResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.wsBiblio.khady.fr/", name = "modifierUtilisateurResponse")
    public JAXBElement<ModifierUtilisateurResponse> createModifierUtilisateurResponse(ModifierUtilisateurResponse value) {
        return new JAXBElement<ModifierUtilisateurResponse>(_ModifierUtilisateurResponse_QNAME, ModifierUtilisateurResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrouverUtilisateurParIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.wsBiblio.khady.fr/", name = "trouverUtilisateurParIdResponse")
    public JAXBElement<TrouverUtilisateurParIdResponse> createTrouverUtilisateurParIdResponse(TrouverUtilisateurParIdResponse value) {
        return new JAXBElement<TrouverUtilisateurParIdResponse>(_TrouverUtilisateurParIdResponse_QNAME, TrouverUtilisateurParIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListerUtilisateurRelanceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.wsBiblio.khady.fr/", name = "listerUtilisateurRelanceResponse")
    public JAXBElement<ListerUtilisateurRelanceResponse> createListerUtilisateurRelanceResponse(ListerUtilisateurRelanceResponse value) {
        return new JAXBElement<ListerUtilisateurRelanceResponse>(_ListerUtilisateurRelanceResponse_QNAME, ListerUtilisateurRelanceResponse.class, null, value);
    }

}
