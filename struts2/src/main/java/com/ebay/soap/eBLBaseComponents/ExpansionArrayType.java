
package com.ebay.soap.eBLBaseComponents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 *  
 * 				Container for items returned with an expanded search.
 * 			
 * 
 * <p>Java class for ExpansionArrayType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExpansionArrayType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ExpansionItem" type="{urn:ebay:apis:eBLBaseComponents}SearchResultItemType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="TotalAvailable" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExpansionArrayType", propOrder = {
    "expansionItem",
    "totalAvailable"
})
public class ExpansionArrayType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "ExpansionItem")
    protected List<SearchResultItemType> expansionItem;
    @XmlElement(name = "TotalAvailable")
    protected Integer totalAvailable;

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link SearchResultItemType }
     *     
     */
    public SearchResultItemType[] getExpansionItem() {
        if (this.expansionItem == null) {
            return new SearchResultItemType[ 0 ] ;
        }
        return ((SearchResultItemType[]) this.expansionItem.toArray(new SearchResultItemType[this.expansionItem.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link SearchResultItemType }
     *     
     */
    public SearchResultItemType getExpansionItem(int idx) {
        if (this.expansionItem == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.expansionItem.get(idx);
    }

    public int getExpansionItemLength() {
        if (this.expansionItem == null) {
            return  0;
        }
        return this.expansionItem.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link SearchResultItemType }
     *     
     */
    public void setExpansionItem(SearchResultItemType[] values) {
        this._getExpansionItem().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.expansionItem.add(values[i]);
        }
    }

    protected List<SearchResultItemType> _getExpansionItem() {
        if (expansionItem == null) {
            expansionItem = new ArrayList<SearchResultItemType>();
        }
        return expansionItem;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link SearchResultItemType }
     *     
     */
    public SearchResultItemType setExpansionItem(int idx, SearchResultItemType value) {
        return this.expansionItem.set(idx, value);
    }

    /**
     * Gets the value of the totalAvailable property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalAvailable() {
        return totalAvailable;
    }

    /**
     * Sets the value of the totalAvailable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalAvailable(Integer value) {
        this.totalAvailable = value;
    }

}
