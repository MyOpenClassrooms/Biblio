
package fr.khady.wsbiblio.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour trouverUtilisateurParId complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="trouverUtilisateurParId">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id_user" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trouverUtilisateurParId", propOrder = {
    "idUser"
})
public class TrouverUtilisateurParId {

    @XmlElement(name = "id_user")
    protected long idUser;

    /**
     * Obtient la valeur de la propri�t� idUser.
     * 
     */
    public long getIdUser() {
        return idUser;
    }

    /**
     * D�finit la valeur de la propri�t� idUser.
     * 
     */
    public void setIdUser(long value) {
        this.idUser = value;
    }

}
