
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
 * 				Retrieves data that you use to construct valid "product finder" queries
 * 				(queries against multiple attributes) against catalog products or (in some cases) listed items.
 * 				The attributes describe search criteria (e.g., Manufacturer), as appropriate for the category.
 * 				Use the results in combination with GetProductFinderXSL to render the Product Finder
 * 				in a graphical user interface.<br>
 * 				<br>
 * 				GetProductFinder does not conduct the actual product or listing search.
 * 				It only returns data about what you can search on. Use the data as input to
 * 				GetProductSearchResults to conduct the actual search for product information
 * 				or as input to GetSearchResults to conduct the search for listed items.
 * 				(Please note that this call may not return valid product finder IDs for some
 * 				GetSearchResults use cases. See the Knowledge Base article referenced below for details.)<br>
 * 				<br>
 * 				To retrieve single-attribute search criteria (querying against a single attribute, like UPC),
 * 				use GetProductSearchPage instead (only applicable for catalog searches).<br>
 * 				<br>
 * 				See the eBay Web Services Guide for an overview of Pre-filled Item Information and details about
 * 				searching for catalog products and for information about searching for listed items.
 * 			
 * 
 * <p>Java class for GetProductFinderRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetProductFinderRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractRequestType">
 *       &lt;sequence>
 *         &lt;element name="AttributeSystemVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProductFinderID" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetProductFinderRequestType", propOrder = {
    "attributeSystemVersion",
    "productFinderID"
})
public class GetProductFinderRequestType
    extends AbstractRequestType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "AttributeSystemVersion")
    protected String attributeSystemVersion;
    @XmlElement(name = "ProductFinderID", type = Integer.class)
    protected List<Integer> productFinderID;

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
    public int[] getProductFinderID() {
        if (this.productFinderID == null) {
            return new int[ 0 ] ;
        }
        int[] r = new int[this.productFinderID.size()] ;
        for (int __i = 0; (__i<r.length); __i ++) {
            r[__i] = this.productFinderID.get(__i).intValue();
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
    public int getProductFinderID(int idx) {
        if (this.productFinderID == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.productFinderID.get(idx).intValue();
    }

    public int getProductFinderIDLength() {
        if (this.productFinderID == null) {
            return  0;
        }
        return this.productFinderID.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link Integer }
     *     
     */
    public void setProductFinderID(int[] values) {
        this._getProductFinderID().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.productFinderID.add(new Integer(values[i]));
        }
    }

    protected List<Integer> _getProductFinderID() {
        if (productFinderID == null) {
            productFinderID = new ArrayList<Integer>();
        }
        return productFinderID;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public int setProductFinderID(int idx, int value) {
        return this.productFinderID.set(idx, new Integer(value)).intValue();
    }

}
