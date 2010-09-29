
package com.ebay.soap.eBLBaseComponents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * 
 * 				Contains information about a nonprofit charity organization.
 * 			
 * 
 * <p>Java class for CharityInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CharityInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Mission" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LogoURL" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="Status" type="{urn:ebay:apis:eBLBaseComponents}CharityStatusCodeType" minOccurs="0"/>
 *         &lt;element name="SearchableString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CharityRegion" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="CharityDomain" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="3" minOccurs="0"/>
 *         &lt;element name="CharityID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LogoURLSelling" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="DisplayLogoSelling" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="DisplayNameInCheckout" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShowMultipleDonationAmountInCheckout" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;any/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CharityInfoType", propOrder = {
    "name",
    "mission",
    "logoURL",
    "status",
    "searchableString",
    "charityRegion",
    "charityDomain",
    "charityID",
    "logoURLSelling",
    "displayLogoSelling",
    "displayNameInCheckout",
    "description",
    "showMultipleDonationAmountInCheckout",
    "any"
})
public class CharityInfoType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Mission")
    protected String mission;
    @XmlElement(name = "LogoURL")
    @XmlSchemaType(name = "anyURI")
    protected String logoURL;
    @XmlElement(name = "Status")
    protected CharityStatusCodeType status;
    @XmlElement(name = "SearchableString")
    protected String searchableString;
    @XmlElement(name = "CharityRegion")
    protected Integer charityRegion;
    @XmlElement(name = "CharityDomain", type = Integer.class)
    protected List<Integer> charityDomain;
    @XmlElement(name = "CharityID")
    protected String charityID;
    @XmlElement(name = "LogoURLSelling")
    @XmlSchemaType(name = "anyURI")
    protected String logoURLSelling;
    @XmlElement(name = "DisplayLogoSelling")
    protected Boolean displayLogoSelling;
    @XmlElement(name = "DisplayNameInCheckout")
    protected Boolean displayNameInCheckout;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "ShowMultipleDonationAmountInCheckout")
    protected Boolean showMultipleDonationAmountInCheckout;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute
    protected String id;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the mission property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMission() {
        return mission;
    }

    /**
     * Sets the value of the mission property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMission(String value) {
        this.mission = value;
    }

    /**
     * Gets the value of the logoURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogoURL() {
        return logoURL;
    }

    /**
     * Sets the value of the logoURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogoURL(String value) {
        this.logoURL = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link CharityStatusCodeType }
     *     
     */
    public CharityStatusCodeType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharityStatusCodeType }
     *     
     */
    public void setStatus(CharityStatusCodeType value) {
        this.status = value;
    }

    /**
     * Gets the value of the searchableString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchableString() {
        return searchableString;
    }

    /**
     * Sets the value of the searchableString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchableString(String value) {
        this.searchableString = value;
    }

    /**
     * Gets the value of the charityRegion property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCharityRegion() {
        return charityRegion;
    }

    /**
     * Sets the value of the charityRegion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCharityRegion(Integer value) {
        this.charityRegion = value;
    }

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link Integer }
     *     
     */
    public int[] getCharityDomain() {
        if (this.charityDomain == null) {
            return new int[ 0 ] ;
        }
        int[] r = new int[this.charityDomain.size()] ;
        for (int __i = 0; (__i<r.length); __i ++) {
            r[__i] = this.charityDomain.get(__i).intValue();
        }
        return r;
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link Integer }
     *     
     */
    public int getCharityDomain(int idx) {
        if (this.charityDomain == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.charityDomain.get(idx).intValue();
    }

    public int getCharityDomainLength() {
        if (this.charityDomain == null) {
            return  0;
        }
        return this.charityDomain.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link Integer }
     *     
     */
    public void setCharityDomain(int[] values) {
        this._getCharityDomain().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.charityDomain.add(new Integer(values[i]));
        }
    }

    protected List<Integer> _getCharityDomain() {
        if (charityDomain == null) {
            charityDomain = new ArrayList<Integer>();
        }
        return charityDomain;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public int setCharityDomain(int idx, int value) {
        return this.charityDomain.set(idx, new Integer(value)).intValue();
    }

    /**
     * Gets the value of the charityID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharityID() {
        return charityID;
    }

    /**
     * Sets the value of the charityID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharityID(String value) {
        this.charityID = value;
    }

    /**
     * Gets the value of the logoURLSelling property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogoURLSelling() {
        return logoURLSelling;
    }

    /**
     * Sets the value of the logoURLSelling property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogoURLSelling(String value) {
        this.logoURLSelling = value;
    }

    /**
     * Gets the value of the displayLogoSelling property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDisplayLogoSelling() {
        return displayLogoSelling;
    }

    /**
     * Sets the value of the displayLogoSelling property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDisplayLogoSelling(Boolean value) {
        this.displayLogoSelling = value;
    }

    /**
     * Gets the value of the displayNameInCheckout property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDisplayNameInCheckout() {
        return displayNameInCheckout;
    }

    /**
     * Sets the value of the displayNameInCheckout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDisplayNameInCheckout(Boolean value) {
        this.displayNameInCheckout = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the showMultipleDonationAmountInCheckout property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowMultipleDonationAmountInCheckout() {
        return showMultipleDonationAmountInCheckout;
    }

    /**
     * Sets the value of the showMultipleDonationAmountInCheckout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowMultipleDonationAmountInCheckout(Boolean value) {
        this.showMultipleDonationAmountInCheckout = value;
    }

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link Element }
     *     {@link Object }
     *     
     */
    public Object[] getAny() {
        if (this.any == null) {
            return new Object[ 0 ] ;
        }
        return ((Object[]) this.any.toArray(new Object[this.any.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link Element }
     *     {@link Object }
     *     
     */
    public Object getAny(int idx) {
        if (this.any == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.any.get(idx);
    }

    public int getAnyLength() {
        if (this.any == null) {
            return  0;
        }
        return this.any.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link Element }
     *     {@link Object }
     *     
     */
    public void setAny(Object[] values) {
        this._getAny().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.any.add(values[i]);
        }
    }

    protected List<Object> _getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return any;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link Element }
     *     {@link Object }
     *     
     */
    public Object setAny(int idx, Object value) {
        return this.any.set(idx, value);
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
