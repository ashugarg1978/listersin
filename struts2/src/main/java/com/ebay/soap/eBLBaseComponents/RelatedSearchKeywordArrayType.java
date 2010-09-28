
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
 * 				Container for a list of related keywords.
 * 			
 * 
 * <p>Java class for RelatedSearchKeywordArrayType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RelatedSearchKeywordArrayType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Keyword" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RelatedSearchKeywordArrayType", propOrder = {
    "keyword"
})
public class RelatedSearchKeywordArrayType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "Keyword")
    protected List<String> keyword;

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link String }
     *     
     */
    public String[] getKeyword() {
        if (this.keyword == null) {
            return new String[ 0 ] ;
        }
        return ((String[]) this.keyword.toArray(new String[this.keyword.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link String }
     *     
     */
    public String getKeyword(int idx) {
        if (this.keyword == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.keyword.get(idx);
    }

    public int getKeywordLength() {
        if (this.keyword == null) {
            return  0;
        }
        return this.keyword.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link String }
     *     
     */
    public void setKeyword(String[] values) {
        this._getKeyword().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.keyword.add(values[i]);
        }
    }

    protected List<String> _getKeyword() {
        if (keyword == null) {
            keyword = new ArrayList<String>();
        }
        return keyword;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public String setKeyword(int idx, String value) {
        return this.keyword.set(idx, value);
    }

}
