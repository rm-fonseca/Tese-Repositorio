//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.09.13 at 01:52:56 PM BST 
//


package plataforma.modelointerno;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for relationLocations complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="relationLocations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idResult" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="thisResultIdLocation" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="targetResultIdLocation" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "relationLocations", propOrder = {
    "idResult",
    "thisResultIdLocation",
    "targetResultIdLocation"
})
public class RelationLocations {

    @XmlElement(required = true)
    protected String idResult;
    protected int thisResultIdLocation;
    protected int targetResultIdLocation;

    /**
     * Gets the value of the idResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdResult() {
        return idResult;
    }

    /**
     * Sets the value of the idResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdResult(String value) {
        this.idResult = value;
    }

    /**
     * Gets the value of the thisResultIdLocation property.
     * 
     */
    public int getThisResultIdLocation() {
        return thisResultIdLocation;
    }

    /**
     * Sets the value of the thisResultIdLocation property.
     * 
     */
    public void setThisResultIdLocation(int value) {
        this.thisResultIdLocation = value;
    }

    /**
     * Gets the value of the targetResultIdLocation property.
     * 
     */
    public int getTargetResultIdLocation() {
        return targetResultIdLocation;
    }

    /**
     * Sets the value of the targetResultIdLocation property.
     * 
     */
    public void setTargetResultIdLocation(int value) {
        this.targetResultIdLocation = value;
    }

}
