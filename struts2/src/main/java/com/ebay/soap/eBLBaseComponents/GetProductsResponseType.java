
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
 * 			Returns stock product information in eBay catalogs, such as
 * 			information about a particular DVD or camera. Optionally,
 * 			also returns product reviews, buying guides, and items that
 * 			match the product.
 * 			
 * 
 * <p>Java class for GetProductsResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetProductsResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractResponseType">
 *       &lt;sequence>
 *         &lt;element name="CharacteristicsSetProductHistogram" type="{urn:ebay:apis:eBLBaseComponents}CharacteristicsSetProductHistogramType" minOccurs="0"/>
 *         &lt;element name="PageNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ApproximatePages" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="HasMore" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="TotalProducts" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Product" type="{urn:ebay:apis:eBLBaseComponents}CatalogProductType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ItemArray" type="{urn:ebay:apis:eBLBaseComponents}ItemArrayType" minOccurs="0"/>
 *         &lt;element name="BuyingGuideDetails" type="{urn:ebay:apis:eBLBaseComponents}BuyingGuideDetailsType" minOccurs="0"/>
 *         &lt;element name="DuplicateItems" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetProductsResponseType", propOrder = {
    "characteristicsSetProductHistogram",
    "pageNumber",
    "approximatePages",
    "hasMore",
    "totalProducts",
    "product",
    "itemArray",
    "buyingGuideDetails",
    "duplicateItems"
})
public class GetProductsResponseType
    extends AbstractResponseType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "CharacteristicsSetProductHistogram")
    protected CharacteristicsSetProductHistogramType characteristicsSetProductHistogram;
    @XmlElement(name = "PageNumber")
    protected Integer pageNumber;
    @XmlElement(name = "ApproximatePages")
    protected Integer approximatePages;
    @XmlElement(name = "HasMore")
    protected Boolean hasMore;
    @XmlElement(name = "TotalProducts")
    protected Integer totalProducts;
    @XmlElement(name = "Product")
    protected List<CatalogProductType> product;
    @XmlElement(name = "ItemArray")
    protected ItemArrayType itemArray;
    @XmlElement(name = "BuyingGuideDetails")
    protected BuyingGuideDetailsType buyingGuideDetails;
    @XmlElement(name = "DuplicateItems")
    protected Boolean duplicateItems;

    /**
     * Gets the value of the characteristicsSetProductHistogram property.
     * 
     * @return
     *     possible object is
     *     {@link CharacteristicsSetProductHistogramType }
     *     
     */
    public CharacteristicsSetProductHistogramType getCharacteristicsSetProductHistogram() {
        return characteristicsSetProductHistogram;
    }

    /**
     * Sets the value of the characteristicsSetProductHistogram property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacteristicsSetProductHistogramType }
     *     
     */
    public void setCharacteristicsSetProductHistogram(CharacteristicsSetProductHistogramType value) {
        this.characteristicsSetProductHistogram = value;
    }

    /**
     * Gets the value of the pageNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * Sets the value of the pageNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPageNumber(Integer value) {
        this.pageNumber = value;
    }

    /**
     * Gets the value of the approximatePages property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getApproximatePages() {
        return approximatePages;
    }

    /**
     * Sets the value of the approximatePages property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setApproximatePages(Integer value) {
        this.approximatePages = value;
    }

    /**
     * Gets the value of the hasMore property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHasMore() {
        return hasMore;
    }

    /**
     * Sets the value of the hasMore property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasMore(Boolean value) {
        this.hasMore = value;
    }

    /**
     * Gets the value of the totalProducts property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalProducts() {
        return totalProducts;
    }

    /**
     * Sets the value of the totalProducts property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalProducts(Integer value) {
        this.totalProducts = value;
    }

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link CatalogProductType }
     *     
     */
    public CatalogProductType[] getProduct() {
        if (this.product == null) {
            return new CatalogProductType[ 0 ] ;
        }
        return ((CatalogProductType[]) this.product.toArray(new CatalogProductType[this.product.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link CatalogProductType }
     *     
     */
    public CatalogProductType getProduct(int idx) {
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
     *     {@link CatalogProductType }
     *     
     */
    public void setProduct(CatalogProductType[] values) {
        this._getProduct().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.product.add(values[i]);
        }
    }

    protected List<CatalogProductType> _getProduct() {
        if (product == null) {
            product = new ArrayList<CatalogProductType>();
        }
        return product;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogProductType }
     *     
     */
    public CatalogProductType setProduct(int idx, CatalogProductType value) {
        return this.product.set(idx, value);
    }

    /**
     * Gets the value of the itemArray property.
     * 
     * @return
     *     possible object is
     *     {@link ItemArrayType }
     *     
     */
    public ItemArrayType getItemArray() {
        return itemArray;
    }

    /**
     * Sets the value of the itemArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemArrayType }
     *     
     */
    public void setItemArray(ItemArrayType value) {
        this.itemArray = value;
    }

    /**
     * Gets the value of the buyingGuideDetails property.
     * 
     * @return
     *     possible object is
     *     {@link BuyingGuideDetailsType }
     *     
     */
    public BuyingGuideDetailsType getBuyingGuideDetails() {
        return buyingGuideDetails;
    }

    /**
     * Sets the value of the buyingGuideDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link BuyingGuideDetailsType }
     *     
     */
    public void setBuyingGuideDetails(BuyingGuideDetailsType value) {
        this.buyingGuideDetails = value;
    }

    /**
     * Gets the value of the duplicateItems property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDuplicateItems() {
        return duplicateItems;
    }

    /**
     * Sets the value of the duplicateItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDuplicateItems(Boolean value) {
        this.duplicateItems = value;
    }

}
