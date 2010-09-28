
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
 *       	Retrieves information that describes how to present catalog
 * 				product information to a seller. Use this information to present
 * 				users with the equivalent of the Item Specifics portion of the
 * 				eBay Title and Description pages and to validate user-specified
 * 				values for eBay attributes on the client before including them in
 * 				an AddItem call or related calls. The data contains a list of all
 * 				the attributes that are applicable for one or more requested
 * 				products, along with related meta-data. The meta-data specifies
 * 				the pre-filled values of each attribute, the possible values of
 * 				attributes that are not pre-filled, the logic for presenting the
 * 				attributes to a user, and rules for validating the user's
 * 				selections. Use the results in combination with GetAttributesXSL
 * 				to render the Item Specifics in a graphical user interface. See
 * 				the eBay Web Services Guide for an overview of Pre-filled Item
 * 				Information, details about searching for catalog products, and
 * 				information about the ProductSellingPages content model.
 * 			
 * 
 * <p>Java class for GetProductSellingPagesRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetProductSellingPagesRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractRequestType">
 *       &lt;sequence>
 *         &lt;element name="UseCase" type="{urn:ebay:apis:eBLBaseComponents}ProductUseCaseCodeType" minOccurs="0"/>
 *         &lt;element name="Product" type="{urn:ebay:apis:eBLBaseComponents}ProductType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetProductSellingPagesRequestType", propOrder = {
    "useCase",
    "product"
})
public class GetProductSellingPagesRequestType
    extends AbstractRequestType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "UseCase")
    protected ProductUseCaseCodeType useCase;
    @XmlElement(name = "Product")
    protected List<ProductType> product;

    /**
     * Gets the value of the useCase property.
     * 
     * @return
     *     possible object is
     *     {@link ProductUseCaseCodeType }
     *     
     */
    public ProductUseCaseCodeType getUseCase() {
        return useCase;
    }

    /**
     * Sets the value of the useCase property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductUseCaseCodeType }
     *     
     */
    public void setUseCase(ProductUseCaseCodeType value) {
        this.useCase = value;
    }

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link ProductType }
     *     
     */
    public ProductType[] getProduct() {
        if (this.product == null) {
            return new ProductType[ 0 ] ;
        }
        return ((ProductType[]) this.product.toArray(new ProductType[this.product.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link ProductType }
     *     
     */
    public ProductType getProduct(int idx) {
        if (this.product == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.product.get(idx);
    }

    public int getProductLength() {
        if (this.product == null) {
            return  0;
        }
        return this.product.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link ProductType }
     *     
     */
    public void setProduct(ProductType[] values) {
        this._getProduct().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.product.add(values[i]);
        }
    }

    protected List<ProductType> _getProduct() {
        if (product == null) {
            product = new ArrayList<ProductType>();
        }
        return product;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link ProductType }
     *     
     */
    public ProductType setProduct(int idx, ProductType value) {
        return this.product.set(idx, value);
    }

}
