
package fr.khady.wsbiblio.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour trouverUtilisateurParIdResponse complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="trouverUtilisateurParIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://webService.wsBiblio.khady.fr/}utilisateur" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trouverUtilisateurParIdResponse", propOrder = {
    "_return"
})
public class TrouverUtilisateurParIdResponse {

    @XmlElement(name = "return")
    protected Utilisateur _return;

    /**
     * Obtient la valeur de la propriété return.
     * 
     * @return
     *     possible object is
     *     {@link Utilisateur }
     *     
     */
    public Utilisateur getReturn() {
        return _return;
    }

    /**
     * Définit la valeur de la propriété return.
     * 
     * @param value
     *     allowed object is
     *     {@link Utilisateur }
     *     
     */
    public void setReturn(Utilisateur value) {
        this._return = value;
    }

}
