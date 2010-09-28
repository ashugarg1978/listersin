
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
 * 				One of the data filters used when searching for items using
 * 				GetSearchResults. Allows filtering based on the eBay user IDs of
 * 				sellers. May be used to limit the list of found items to just those
 * 				listed by one or more specified sellers. Or may be used to limit the
 * 				list to those items NOT listed by specified excluded sellers.
 * 			
 * 
 * <p>Java class for UserIdFilterType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserIdFilterType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ExcludeSellers" type="{urn:ebay:apis:eBLBaseComponents}UserIDType" maxOccurs="100" minOccurs="0"/>
 *         &lt;element name="IncludeSellers" type="{urn:ebay:apis:eBLBaseComponents}UserIDType" maxOccurs="100" minOccurs="0"/>
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
@XmlType(name = "UserIdFilterType", propOrder = {
    "excludeSellers",
    "includeSellers",
    "any"
})
public class UserIdFilterType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "ExcludeSellers")
    protected List<String> excludeSellers;
    @XmlElement(name = "IncludeSellers")
    protected List<String> includeSellers;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link String }
     *     
     */
    public String[] getExcludeSellers() {
        if (this.excludeSellers == null) {
            return new String[ 0 ] ;
        }
        return ((String[]) this.excludeSellers.toArray(new String[this.excludeSellers.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link String }
     *     
     */
    public String getExcludeSellers(int idx) {
        if (this.excludeSellers == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.excludeSellers.get(idx);
    }

    public int getExcludeSellersLength() {
        if (this.excludeSellers == null) {
            return  0;
        }
        return this.excludeSellers.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link String }
     *     
     */
    public void setExcludeSellers(String[] values) {
        this._getExcludeSellers().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.excludeSellers.add(values[i]);
        }
    }

    protected List<String> _getExcludeSellers() {
        if (excludeSellers == null) {
            excludeSellers = new ArrayList<String>();
        }
        return excludeSellers;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public String setExcludeSellers(int idx, String value) {
        return this.excludeSellers.set(idx, value);
    }

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link String }
     *     
     */
    public String[] getIncludeSellers() {
        if (this.includeSellers == null) {
            return new String[ 0 ] ;
        }
        return ((String[]) this.includeSellers.toArray(new String[this.includeSellers.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link String }
     *     
     */
    public String getIncludeSellers(int idx) {
        if (this.includeSellers == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.includeSellers.get(idx);
    }

    public int getIncludeSellersLength() {
        if (this.includeSellers == null) {
            return  0;
        }
        return this.includeSellers.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link String }
     *     
     */
    public void setIncludeSellers(String[] values) {
        this._getIncludeSellers().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.includeSellers.add(values[i]);
        }
    }

    protected List<String> _getIncludeSellers() {
        if (includeSellers == null) {
            includeSellers = new ArrayList<String>();
        }
        return includeSellers;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public String setIncludeSellers(int idx, String value) {
        return this.includeSellers.set(idx, value);
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
