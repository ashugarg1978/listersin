
package com.ebay.soap.eBLBaseComponents;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Retrieves an XML string that describes how to present catalog product information
 * 				to a seller. Use this information to present users with the equivalent of the Item
 * 				Specifics portion of the eBay Title and Description pages and to validate user-
 * 				specified values for eBay attributes on the client before including them in an
 * 				AddItem call or related calls. Use the results in combination with
 * 				GetAttributesXSL to render the Item Specifics in a graphical user interface. See
 * 				the Developer's Guide for an overview of Pre-filled Item Information and details
 * 				about searching for catalog products.
 * 			
 * 
 * <p>Java class for GetProductSellingPagesResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetProductSellingPagesResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractResponseType">
 *       &lt;sequence>
 *         &lt;element name="ProductSellingPagesData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetProductSellingPagesResponseType", propOrder = {
    "productSellingPagesData"
})
public class GetProductSellingPagesResponseType
    extends AbstractResponseType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "ProductSellingPagesData")
    protected String productSellingPagesData;

    /**
     * Gets the value of the productSellingPagesData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductSellingPagesData() {
        return productSellingPagesData;
    }

    /**
     * Sets the value of the productSellingPagesData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductSellingPagesData(String value) {
        this.productSellingPagesData = value;
    }

}
