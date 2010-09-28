
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
 * 				Retrieves the words that are most frequently submitted by eBay users when
 * 				searching for listings.
 * 			
 * 
 * <p>Java class for GetPopularKeywordsRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetPopularKeywordsRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractRequestType">
 *       &lt;sequence>
 *         &lt;element name="CategoryID" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="IncludeChildCategories" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="MaxKeywordsRetrieved" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Pagination" type="{urn:ebay:apis:eBLBaseComponents}PaginationType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetPopularKeywordsRequestType", propOrder = {
    "categoryID",
    "includeChildCategories",
    "maxKeywordsRetrieved",
    "pagination"
})
public class GetPopularKeywordsRequestType
    extends AbstractRequestType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "CategoryID")
    protected List<String> categoryID;
    @XmlElement(name = "IncludeChildCategories")
    protected Boolean includeChildCategories;
    @XmlElement(name = "MaxKeywordsRetrieved")
    protected Integer maxKeywordsRetrieved;
    @XmlElement(name = "Pagination")
    protected PaginationType pagination;

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link String }
     *     
     */
    public String[] getCategoryID() {
        if (this.categoryID == null) {
            return new String[ 0 ] ;
        }
        return ((String[]) this.categoryID.toArray(new String[this.categoryID.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link String }
     *     
     */
    public String getCategoryID(int idx) {
        if (this.categoryID == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.categoryID.get(idx);
    }

    public int getCategoryIDLength() {
        if (this.categoryID == null) {
            return  0;
        }
        return this.categoryID.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link String }
     *     
     */
    public void setCategoryID(String[] values) {
        this._getCategoryID().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.categoryID.add(values[i]);
        }
    }

    protected List<String> _getCategoryID() {
        if (categoryID == null) {
            categoryID = new ArrayList<String>();
        }
        return categoryID;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public String setCategoryID(int idx, String value) {
        return this.categoryID.set(idx, value);
    }

    /**
     * Gets the value of the includeChildCategories property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeChildCategories() {
        return includeChildCategories;
    }

    /**
     * Sets the value of the includeChildCategories property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeChildCategories(Boolean value) {
        this.includeChildCategories = value;
    }

    /**
     * Gets the value of the maxKeywordsRetrieved property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxKeywordsRetrieved() {
        return maxKeywordsRetrieved;
    }

    /**
     * Sets the value of the maxKeywordsRetrieved property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxKeywordsRetrieved(Integer value) {
        this.maxKeywordsRetrieved = value;
    }

    /**
     * Gets the value of the pagination property.
     * 
     * @return
     *     possible object is
     *     {@link PaginationType }
     *     
     */
    public PaginationType getPagination() {
        return pagination;
    }

    /**
     * Sets the value of the pagination property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaginationType }
     *     
     */
    public void setPagination(PaginationType value) {
        this.pagination = value;
    }

}
