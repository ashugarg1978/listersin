
package com.ebay.soap.eBLBaseComponents;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				For a category that supports product finders, GetProductFinder returns an XML string
 * 				that decribes the attributes a seller can use to form a query when searching for
 * 				Pre-filled Item Information, or attributes that can be used to search for listed items.
 * 				Specifically, it retrieves data that you use to construct valid "product finder" queries
 * 				(queries against multiple attributes).
 * 				Use the results in combination with GetProductFinderXSL to render the Product Finder
 * 				in a graphical user interface.
 * 				See the Developer's Guide for an overview of Pre-filled Item Information and details about
 * 				searching for catalog products and for information about searching for listed items.
 * 			
 * 
 * <p>Java class for GetProductFinderResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetProductFinderResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractResponseType">
 *       &lt;sequence>
 *         &lt;element name="AttributeSystemVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProductFinderData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetProductFinderResponseType", propOrder = {
    "attributeSystemVersion",
    "productFinderData"
})
public class GetProductFinderResponseType
    extends AbstractResponseType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "AttributeSystemVersion")
    protected String attributeSystemVersion;
    @XmlElement(name = "ProductFinderData")
    protected String productFinderData;

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
     * Gets the value of the productFinderData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductFinderData() {
        return productFinderData;
    }

    /**
     * Sets the value of the productFinderData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductFinderData(String value) {
        this.productFinderData = value;
    }

}
