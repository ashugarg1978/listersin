
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
 * 				Contains the data for one item listing found by a search (such as by
 * 				GetSearchResults).
 * 			
 * 
 * <p>Java class for SearchResultItemType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchResultItemType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Item" type="{urn:ebay:apis:eBLBaseComponents}ItemType" minOccurs="0"/>
 *         &lt;element name="ItemSpecific" type="{urn:ebay:apis:eBLBaseComponents}NameValueListArrayType" minOccurs="0"/>
 *         &lt;element name="SearchResultValues" type="{urn:ebay:apis:eBLBaseComponents}SearchResultValuesCodeType" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "SearchResultItemType", propOrder = {
    "item",
    "itemSpecific",
    "searchResultValues",
    "any"
})
public class SearchResultItemType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "Item")
    protected ItemType item;
    @XmlElement(name = "ItemSpecific")
    protected NameValueListArrayType itemSpecific;
    @XmlElement(name = "SearchResultValues")
    protected List<SearchResultValuesCodeType> searchResultValues;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the item property.
     * 
     * @return
     *     possible object is
     *     {@link ItemType }
     *     
     */
    public ItemType getItem() {
        return item;
    }

    /**
     * Sets the value of the item property.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemType }
     *     
     */
    public void setItem(ItemType value) {
        this.item = value;
    }

    /**
     * Gets the value of the itemSpecific property.
     * 
     * @return
     *     possible object is
     *     {@link NameValueListArrayType }
     *     
     */
    public NameValueListArrayType getItemSpecific() {
        return itemSpecific;
    }

    /**
     * Sets the value of the itemSpecific property.
     * 
     * @param value
     *     allowed object is
     *     {@link NameValueListArrayType }
     *     
     */
    public void setItemSpecific(NameValueListArrayType value) {
        this.itemSpecific = value;
    }

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link SearchResultValuesCodeType }
     *     
     */
    public SearchResultValuesCodeType[] getSearchResultValues() {
        if (this.searchResultValues == null) {
            return new SearchResultValuesCodeType[ 0 ] ;
        }
        return ((SearchResultValuesCodeType[]) this.searchResultValues.toArray(new SearchResultValuesCodeType[this.searchResultValues.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link SearchResultValuesCodeType }
     *     
     */
    public SearchResultValuesCodeType getSearchResultValues(int idx) {
        if (this.searchResultValues == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.searchResultValues.get(idx);
    }

    public int getSearchResultValuesLength() {
        if (this.searchResultValues == null) {
            return  0;
        }
        return this.searchResultValues.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link SearchResultValuesCodeType }
     *     
     */
    public void setSearchResultValues(SearchResultValuesCodeType[] values) {
        this._getSearchResultValues().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.searchResultValues.add(values[i]);
        }
    }

    protected List<SearchResultValuesCodeType> _getSearchResultValues() {
        if (searchResultValues == null) {
            searchResultValues = new ArrayList<SearchResultValuesCodeType>();
        }
        return searchResultValues;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link SearchResultValuesCodeType }
     *     
     */
    public SearchResultValuesCodeType setSearchResultValues(int idx, SearchResultValuesCodeType value) {
        return this.searchResultValues.set(idx, value);
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

}
