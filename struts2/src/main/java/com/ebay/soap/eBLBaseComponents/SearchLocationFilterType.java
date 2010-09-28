
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
 * 				One of the data filters used when searching for items using GetSearchResults. 
 * 				Allows filtering based on an item's location (where the seller would be shipping 
 * 				the item from) or an item's availability (which eBay sites the item can be 
 * 				purchased from).
 * 			
 * 
 * <p>Java class for SearchLocationFilterType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchLocationFilterType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CountryCode" type="{urn:ebay:apis:eBLBaseComponents}CountryCodeType" minOccurs="0"/>
 *         &lt;element name="ItemLocation" type="{urn:ebay:apis:eBLBaseComponents}ItemLocationCodeType" minOccurs="0"/>
 *         &lt;element name="SearchLocation" type="{urn:ebay:apis:eBLBaseComponents}SearchLocationType" minOccurs="0"/>
 *         &lt;element name="Currency" type="{urn:ebay:apis:eBLBaseComponents}CurrencyCodeType" minOccurs="0"/>
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
@XmlType(name = "SearchLocationFilterType", propOrder = {
    "countryCode",
    "itemLocation",
    "searchLocation",
    "currency",
    "any"
})
public class SearchLocationFilterType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "CountryCode")
    protected CountryCodeType countryCode;
    @XmlElement(name = "ItemLocation")
    protected ItemLocationCodeType itemLocation;
    @XmlElement(name = "SearchLocation")
    protected SearchLocationType searchLocation;
    @XmlElement(name = "Currency")
    protected CurrencyCodeType currency;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the countryCode property.
     * 
     * @return
     *     possible object is
     *     {@link CountryCodeType }
     *     
     */
    public CountryCodeType getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CountryCodeType }
     *     
     */
    public void setCountryCode(CountryCodeType value) {
        this.countryCode = value;
    }

    /**
     * Gets the value of the itemLocation property.
     * 
     * @return
     *     possible object is
     *     {@link ItemLocationCodeType }
     *     
     */
    public ItemLocationCodeType getItemLocation() {
        return itemLocation;
    }

    /**
     * Sets the value of the itemLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemLocationCodeType }
     *     
     */
    public void setItemLocation(ItemLocationCodeType value) {
        this.itemLocation = value;
    }

    /**
     * Gets the value of the searchLocation property.
     * 
     * @return
     *     possible object is
     *     {@link SearchLocationType }
     *     
     */
    public SearchLocationType getSearchLocation() {
        return searchLocation;
    }

    /**
     * Sets the value of the searchLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchLocationType }
     *     
     */
    public void setSearchLocation(SearchLocationType value) {
        this.searchLocation = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link CurrencyCodeType }
     *     
     */
    public CurrencyCodeType getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrencyCodeType }
     *     
     */
    public void setCurrency(CurrencyCodeType value) {
        this.currency = value;
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
