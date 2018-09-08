//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.09.06 at 01:39:11 AM WEST 
//


package plataforma.modelointerno;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for timeSpan complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="timeSpan">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{Plataforma/ModeloInterno}languageString" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="begin" type="{Plataforma/ModeloInterno}dateTime" minOccurs="0"/>
 *         &lt;element name="end" type="{Plataforma/ModeloInterno}dateTime" minOccurs="0"/>
 *         &lt;element name="extraProperties" type="{Plataforma/ModeloInterno}extraProperty" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "timeSpan", propOrder = {
    "name",
    "begin",
    "end",
    "extraProperties"
})
public class TimeSpan {

    protected List<LanguageString> name;
    protected DateTime begin;
    protected DateTime end;
    protected List<ExtraProperty> extraProperties;

    /**
     * Gets the value of the name property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the name property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LanguageString }
     * 
     * 
     */
    public List<LanguageString> getName() {
        if (name == null) {
            name = new ArrayList<LanguageString>();
        }
        return this.name;
    }

    /**
     * Gets the value of the begin property.
     * 
     * @return
     *     possible object is
     *     {@link DateTime }
     *     
     */
    public DateTime getBegin() {
        return begin;
    }

    /**
     * Sets the value of the begin property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTime }
     *     
     */
    public void setBegin(DateTime value) {
        this.begin = value;
    }

    /**
     * Gets the value of the end property.
     * 
     * @return
     *     possible object is
     *     {@link DateTime }
     *     
     */
    public DateTime getEnd() {
        return end;
    }

    /**
     * Sets the value of the end property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTime }
     *     
     */
    public void setEnd(DateTime value) {
        this.end = value;
    }

    /**
     * Gets the value of the extraProperties property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extraProperties property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtraProperties().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtraProperty }
     * 
     * 
     */
    public List<ExtraProperty> getExtraProperties() {
        if (extraProperties == null) {
            extraProperties = new ArrayList<ExtraProperty>();
        }
        return this.extraProperties;
    }

}
