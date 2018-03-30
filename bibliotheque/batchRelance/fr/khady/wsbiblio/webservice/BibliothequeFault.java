
package fr.khady.wsbiblio.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour bibliothequeFault complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="bibliothequeFault">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="faultCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="faultString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bibliothequeFault", propOrder = {
    "faultCode",
    "faultString"
})
public class BibliothequeFault {

    protected String faultCode;
    protected String faultString;

    /**
     * Obtient la valeur de la propriété faultCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaultCode() {
        return faultCode;
    }

    /**
     * Définit la valeur de la propriété faultCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaultCode(String value) {
        this.faultCode = value;
    }

    /**
     * Obtient la valeur de la propriété faultString.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaultString() {
        return faultString;
    }

    /**
     * Définit la valeur de la propriété faultString.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaultString(String value) {
        this.faultString = value;
    }

}
