
package com.ebay.soap.eBLBaseComponents;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetCategoryListingsResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetCategoryListingsResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractResponseType">
 *       &lt;sequence>
 *         &lt;element name="ItemArray" type="{urn:ebay:apis:eBLBaseComponents}ItemArrayType" minOccurs="0"/>
 *         &lt;element name="Category" type="{urn:ebay:apis:eBLBaseComponents}CategoryType" minOccurs="0"/>
 *         &lt;element name="SubCategories" type="{urn:ebay:apis:eBLBaseComponents}CategoryArrayType" minOccurs="0"/>
 *         &lt;element name="ItemsPerPage" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PageNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="HasMoreItems" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="PaginationResult" type="{urn:ebay:apis:eBLBaseComponents}PaginationResultType" minOccurs="0"/>
 *         &lt;element name="BuyingGuideDetails" type="{urn:ebay:apis:eBLBaseComponents}BuyingGuideDetailsType" minOccurs="0"/>
 *         &lt;element name="RelatedSearchKeywordArray" type="{urn:ebay:apis:eBLBaseComponents}RelatedSearchKeywordArrayType" minOccurs="0"/>
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
@XmlType(name = "GetCategoryListingsResponseType", propOrder = {
    "itemArray",
    "category",
    "subCategories",
    "itemsPerPage",
    "pageNumber",
    "hasMoreItems",
    "paginationResult",
    "buyingGuideDetails",
    "relatedSearchKeywordArray",
    "duplicateItems"
})
public class GetCategoryListingsResponseType
    extends AbstractResponseType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "ItemArray")
    protected ItemArrayType itemArray;
    @XmlElement(name = "Category")
    protected CategoryType category;
    @XmlElement(name = "SubCategories")
    protected CategoryArrayType subCategories;
    @XmlElement(name = "ItemsPerPage")
    protected Integer itemsPerPage;
    @XmlElement(name = "PageNumber")
    protected Integer pageNumber;
    @XmlElement(name = "HasMoreItems")
    protected boolean hasMoreItems;
    @XmlElement(name = "PaginationResult")
    protected PaginationResultType paginationResult;
    @XmlElement(name = "BuyingGuideDetails")
    protected BuyingGuideDetailsType buyingGuideDetails;
    @XmlElement(name = "RelatedSearchKeywordArray")
    protected RelatedSearchKeywordArrayType relatedSearchKeywordArray;
    @XmlElement(name = "DuplicateItems")
    protected Boolean duplicateItems;

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
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link CategoryType }
     *     
     */
    public CategoryType getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoryType }
     *     
     */
    public void setCategory(CategoryType value) {
        this.category = value;
    }

    /**
     * Gets the value of the subCategories property.
     * 
     * @return
     *     possible object is
     *     {@link CategoryArrayType }
     *     
     */
    public CategoryArrayType getSubCategories() {
        return subCategories;
    }

    /**
     * Sets the value of the subCategories property.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoryArrayType }
     *     
     */
    public void setSubCategories(CategoryArrayType value) {
        this.subCategories = value;
    }

    /**
     * Gets the value of the itemsPerPage property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    /**
     * Sets the value of the itemsPerPage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setItemsPerPage(Integer value) {
        this.itemsPerPage = value;
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
     * Gets the value of the hasMoreItems property.
     * 
     */
    public boolean isHasMoreItems() {
        return hasMoreItems;
    }

    /**
     * Sets the value of the hasMoreItems property.
     * 
     */
    public void setHasMoreItems(boolean value) {
        this.hasMoreItems = value;
    }

    /**
     * Gets the value of the paginationResult property.
     * 
     * @return
     *     possible object is
     *     {@link PaginationResultType }
     *     
     */
    public PaginationResultType getPaginationResult() {
        return paginationResult;
    }

    /**
     * Sets the value of the paginationResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaginationResultType }
     *     
     */
    public void setPaginationResult(PaginationResultType value) {
        this.paginationResult = value;
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
     * Gets the value of the relatedSearchKeywordArray property.
     * 
     * @return
     *     possible object is
     *     {@link RelatedSearchKeywordArrayType }
     *     
     */
    public RelatedSearchKeywordArrayType getRelatedSearchKeywordArray() {
        return relatedSearchKeywordArray;
    }

    /**
     * Sets the value of the relatedSearchKeywordArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelatedSearchKeywordArrayType }
     *     
     */
    public void setRelatedSearchKeywordArray(RelatedSearchKeywordArrayType value) {
        this.relatedSearchKeywordArray = value;
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
