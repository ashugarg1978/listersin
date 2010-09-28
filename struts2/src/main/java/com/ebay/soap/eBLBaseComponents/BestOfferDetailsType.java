
package com.ebay.soap.eBLBaseComponents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * 
 * 				Container for BestOffer properties associated with the item.
 * 			
 * 
 * <p>Java class for BestOfferDetailsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BestOfferDetailsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BestOfferCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BestOfferEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="BestOffer" type="{urn:ebay:apis:eBLBaseComponents}AmountType" minOccurs="0"/>
 *         &lt;element name="BestOfferStatus" type="{urn:ebay:apis:eBLBaseComponents}BestOfferStatusCodeType" minOccurs="0"/>
 *         &lt;element name="BestOfferType" type="{urn:ebay:apis:eBLBaseComponents}BestOfferTypeCodeType" minOccurs="0"/>
 *         &lt;any/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BestOfferDetailsType", propOrder = {
    "bestOfferCount",
    "bestOfferEnabled",
    "bestOffer",
    "bestOfferStatus",
    "bestOfferType",
    "any"
})
public class BestOfferDetailsType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "BestOfferCount")
    protected Integer bestOfferCount;
    @XmlElement(name = "BestOfferEnabled")
    protected Boolean bestOfferEnabled;
    @XmlElement(name = "BestOffer")
    protected AmountType bestOffer;
    @XmlElement(name = "BestOfferStatus")
    protected BestOfferStatusCodeType bestOfferStatus;
    @XmlElement(name = "BestOfferType")
    protected BestOfferTypeCodeType bestOfferType;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the bestOfferCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBestOfferCount() {
        return bestOfferCount;
    }

    /**
     * Sets the value of the bestOfferCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBestOfferCount(Integer value) {
        this.bestOfferCount = value;
    }

    /**
     * Gets the value of the bestOfferEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBestOfferEnabled() {
        return bestOfferEnabled;
    }

    /**
     * Sets the value of the bestOfferEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBestOfferEnabled(Boolean value) {
        this.bestOfferEnabled = value;
    }

    /**
     * Gets the value of the bestOffer property.
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getBestOffer() {
        return bestOffer;
    }

    /**
     * Sets the value of the bestOffer property.
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setBestOffer(AmountType value) {
        this.bestOffer = value;
    }

    /**
     * Gets the value of the bestOfferStatus property.
     * 
     * @return
     *     possible object is
     *     {@link BestOfferStatusCodeType }
     *     
     */
    public BestOfferStatusCodeType getBestOfferStatus() {
        return bestOfferStatus;
    }

    /**
     * Sets the value of the bestOfferStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link BestOfferStatusCodeType }
     *     
     */
    public void setBestOfferStatus(BestOfferStatusCodeType value) {
        this.bestOfferStatus = value;
    }

    /**
     * Gets the value of the bestOfferType property.
     * 
     * @return
     *     possible object is
     *     {@link BestOfferTypeCodeType }
     *     
     */
    public BestOfferTypeCodeType getBestOfferType() {
        return bestOfferType;
    }

    /**
     * Sets the value of the bestOfferType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BestOfferTypeCodeType }
     *     
     */
    public void setBestOfferType(BestOfferTypeCodeType value) {
        this.bestOfferType = value;
    }

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link Object }
     *     {@link Element }
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
     *     {@link Object }
     *     {@link Element }
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
     *     {@link Object }
     *     {@link Element }
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
     *     {@link Object }
     *     {@link Element }
     *     
     */
    public Object setAny(int idx, Object value) {
        return this.any.set(idx, value);
    }

}
