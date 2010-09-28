
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
 * 				Retrieves an XML string that describes how to present Item
 * 				Specifics to a seller who is creating a new listing.
 * 				Use this information to present users with
 * 				the equivalent of the Item Specifics portion of the eBay Title
 * 				and Description pages and to validate selected eBay attributes on
 * 				the client before including them in an AddItem call or related
 * 				calls. The XML string contains a list of all the attributes that
 * 				are applicable for one or more requested categories, along with
 * 				related meta-data. The meta-data specifies all the possible
 * 				values of each attribute, the logic for presenting the attributes
 * 				to a user, and rules for validating the user's selections. <br>
 * 				<br>
 * 				See the eBay Web Services Guide for an overview of Item Specifics and
 * 				details about the eBay attribute model.<br>
 * 				<br>
 * 				Item Specifics are optional when listing in most categories;
 * 				however, some categories (such as Real Estate, US Tickets, and
 * 				US eBay Motors) require Item Specifics.
 * 			
 * 
 * <p>Java class for GetAttributesCSRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetAttributesCSRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractRequestType">
 *       &lt;sequence>
 *         &lt;element name="AttributeSystemVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AttributeSetID" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="IncludeCategoryMappingDetails" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAttributesCSRequestType", propOrder = {
    "attributeSystemVersion",
    "attributeSetID",
    "includeCategoryMappingDetails"
})
public class GetAttributesCSRequestType
    extends AbstractRequestType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "AttributeSystemVersion")
    protected String attributeSystemVersion;
    @XmlElement(name = "AttributeSetID", type = Integer.class)
    protected List<Integer> attributeSetID;
    @XmlElement(name = "IncludeCategoryMappingDetails", defaultValue = "false")
    protected Boolean includeCategoryMappingDetails;

    /**
     * Gets the value of the attributeSystemVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributeSystemVersion() {
        return attributeSystemVersion;
    }

    /**
     * Sets the value of the attributeSystemVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributeSystemVersion(String value) {
        this.attributeSystemVersion = value;
    }

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link Integer }
     *     
     */
    public int[] getAttributeSetID() {
        if (this.attributeSetID == null) {
            return new int[ 0 ] ;
        }
        int[] r = new int[this.attributeSetID.size()] ;
        for (int __i = 0; (__i<r.length); __i ++) {
            r[__i] = this.attributeSetID.get(__i).intValue();
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
    public int getAttributeSetID(int idx) {
        if (this.attributeSetID == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.attributeSetID.get(idx).intValue();
    }

    public int getAttributeSetIDLength() {
        if (this.attributeSetID == null) {
            return  0;
        }
        return this.attributeSetID.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link Integer }
     *     
     */
    public void setAttributeSetID(int[] values) {
        this._getAttributeSetID().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.attributeSetID.add(new Integer(values[i]));
        }
    }

    protected List<Integer> _getAttributeSetID() {
        if (attributeSetID == null) {
            attributeSetID = new ArrayList<Integer>();
        }
        return attributeSetID;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public int setAttributeSetID(int idx, int value) {
        return this.attributeSetID.set(idx, new Integer(value)).intValue();
    }

    /**
     * Gets the value of the includeCategoryMappingDetails property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeCategoryMappingDetails() {
        return includeCategoryMappingDetails;
    }

    /**
     * Sets the value of the includeCategoryMappingDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeCategoryMappingDetails(Boolean value) {
        this.includeCategoryMappingDetails = value;
    }

}
