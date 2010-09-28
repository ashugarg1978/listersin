
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
 * 				Suggested spelling of mispelled search terms, along with the number of matching items that would have been returned if the suggestions had been used.	
 * 		  
 * 
 * <p>Java class for SpellingSuggestionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SpellingSuggestionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MatchingItemCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Text" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "SpellingSuggestionType", propOrder = {
    "matchingItemCount",
    "text",
    "any"
})
public class SpellingSuggestionType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "MatchingItemCount")
    protected Integer matchingItemCount;
    @XmlElement(name = "Text")
    protected List<String> text;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the matchingItemCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMatchingItemCount() {
        return matchingItemCount;
    }

    /**
     * Sets the value of the matchingItemCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMatchingItemCount(Integer value) {
        this.matchingItemCount = value;
    }

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link String }
     *     
     */
    public String[] getText() {
        if (this.text == null) {
            return new String[ 0 ] ;
        }
        return ((String[]) this.text.toArray(new String[this.text.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link String }
     *     
     */
    public String getText(int idx) {
        if (this.text == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.text.get(idx);
    }

    public int getTextLength() {
        if (this.text == null) {
            return  0;
        }
        return this.text.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link String }
     *     
     */
    public void setText(String[] values) {
        this._getText().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.text.add(values[i]);
        }
    }

    protected List<String> _getText() {
        if (text == null) {
            text = new ArrayList<String>();
        }
        return text;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public String setText(int idx, String value) {
        return this.text.set(idx, value);
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
