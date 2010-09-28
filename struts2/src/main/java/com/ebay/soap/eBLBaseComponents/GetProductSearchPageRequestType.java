
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
 * 				Retrieves the attributes a seller can use to form a query when
 * 				searching for Pre-filled Item Information to use in a listing for
 * 				a category that is catalog-enabled. This call is applicable for
 * 				use cases related to listing items with Pre-filled Item
 * 				Information. Specifically, it retrieves data
 * 				that you use to construct valid "single-attribute" queries. The
 * 				attributes describe search criteria (e.g., Author) and sorting
 * 				criteria (e.g., Publication Year), as appropriate for the
 * 				category. GetProductSearchPage does not conduct the actual
 * 				product search. It only returns data about what you can search
 * 				on. Use the data as input to GetProductSearchResults to conduct
 * 				the actual search for product information. To retrieve Product
 * 				Finder search criteria (querying against multiple attributes),
 * 				use GetProductFinder instead. See the eBay Web Services guide for
 * 				an overview of Pre-filled Item Information and details about
 * 				searching for catalog products.
 * 			
 * 
 * <p>Java class for GetProductSearchPageRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetProductSearchPageRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractRequestType">
 *       &lt;sequence>
 *         &lt;element name="AttributeSystemVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AttributeSetID" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetProductSearchPageRequestType", propOrder = {
    "attributeSystemVersion",
    "attributeSetID"
})
public class GetProductSearchPageRequestType
    extends AbstractRequestType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "AttributeSystemVersion")
    protected String attributeSystemVersion;
    @XmlElement(name = "AttributeSetID", type = Integer.class)
    protected List<Integer> attributeSetID;

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

}
