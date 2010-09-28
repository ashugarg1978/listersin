
package com.ebay.soap.eBLBaseComponents;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Response contains the item listings that have the specified keyword(s) in the
 * 				title, sub-title, and (optionally) the description. If the request uses any of
 * 				the optional filtering properties, the item listings returned are those
 * 				containing the keyword(s) and meeting the filter criteria. <br><br>
 * 				Not applicable to Half.com.
 * 			
 * 
 * <p>Java class for GetSearchResultsResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetSearchResultsResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractResponseType">
 *       &lt;sequence>
 *         &lt;element name="SearchResultItemArray" type="{urn:ebay:apis:eBLBaseComponents}SearchResultItemArrayType" minOccurs="0"/>
 *         &lt;element name="ItemsPerPage" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PageNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="HasMoreItems" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="PaginationResult" type="{urn:ebay:apis:eBLBaseComponents}PaginationResultType" minOccurs="0"/>
 *         &lt;element name="CategoryArray" type="{urn:ebay:apis:eBLBaseComponents}CategoryArrayType" minOccurs="0"/>
 *         &lt;element name="BuyingGuideDetails" type="{urn:ebay:apis:eBLBaseComponents}BuyingGuideDetailsType" minOccurs="0"/>
 *         &lt;element name="StoreExpansionArray" type="{urn:ebay:apis:eBLBaseComponents}ExpansionArrayType" minOccurs="0"/>
 *         &lt;element name="InternationalExpansionArray" type="{urn:ebay:apis:eBLBaseComponents}ExpansionArrayType" minOccurs="0"/>
 *         &lt;element name="FilterRemovedExpansionArray" type="{urn:ebay:apis:eBLBaseComponents}ExpansionArrayType" minOccurs="0"/>
 *         &lt;element name="AllCategoriesExpansionArray" type="{urn:ebay:apis:eBLBaseComponents}ExpansionArrayType" minOccurs="0"/>
 *         &lt;element name="SpellingSuggestion" type="{urn:ebay:apis:eBLBaseComponents}SpellingSuggestionType" minOccurs="0"/>
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
@XmlType(name = "GetSearchResultsResponseType", propOrder = {
    "searchResultItemArray",
    "itemsPerPage",
    "pageNumber",
    "hasMoreItems",
    "paginationResult",
    "categoryArray",
    "buyingGuideDetails",
    "storeExpansionArray",
    "internationalExpansionArray",
    "filterRemovedExpansionArray",
    "allCategoriesExpansionArray",
    "spellingSuggestion",
    "relatedSearchKeywordArray",
    "duplicateItems"
})
public class GetSearchResultsResponseType
    extends AbstractResponseType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "SearchResultItemArray")
    protected SearchResultItemArrayType searchResultItemArray;
    @XmlElement(name = "ItemsPerPage")
    protected Integer itemsPerPage;
    @XmlElement(name = "PageNumber")
    protected Integer pageNumber;
    @XmlElement(name = "HasMoreItems")
    protected boolean hasMoreItems;
    @XmlElement(name = "PaginationResult")
    protected PaginationResultType paginationResult;
    @XmlElement(name = "CategoryArray")
    protected CategoryArrayType categoryArray;
    @XmlElement(name = "BuyingGuideDetails")
    protected BuyingGuideDetailsType buyingGuideDetails;
    @XmlElement(name = "StoreExpansionArray")
    protected ExpansionArrayType storeExpansionArray;
    @XmlElement(name = "InternationalExpansionArray")
    protected ExpansionArrayType internationalExpansionArray;
    @XmlElement(name = "FilterRemovedExpansionArray")
    protected ExpansionArrayType filterRemovedExpansionArray;
    @XmlElement(name = "AllCategoriesExpansionArray")
    protected ExpansionArrayType allCategoriesExpansionArray;
    @XmlElement(name = "SpellingSuggestion")
    protected SpellingSuggestionType spellingSuggestion;
    @XmlElement(name = "RelatedSearchKeywordArray")
    protected RelatedSearchKeywordArrayType relatedSearchKeywordArray;
    @XmlElement(name = "DuplicateItems")
    protected Boolean duplicateItems;

    /**
     * Gets the value of the searchResultItemArray property.
     * 
     * @return
     *     possible object is
     *     {@link SearchResultItemArrayType }
     *     
     */
    public SearchResultItemArrayType getSearchResultItemArray() {
        return searchResultItemArray;
    }

    /**
     * Sets the value of the searchResultItemArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchResultItemArrayType }
     *     
     */
    public void setSearchResultItemArray(SearchResultItemArrayType value) {
        this.searchResultItemArray = value;
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
     * Gets the value of the categoryArray property.
     * 
     * @return
     *     possible object is
     *     {@link CategoryArrayType }
     *     
     */
    public CategoryArrayType getCategoryArray() {
        return categoryArray;
    }

    /**
     * Sets the value of the categoryArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoryArrayType }
     *     
     */
    public void setCategoryArray(CategoryArrayType value) {
        this.categoryArray = value;
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
     * Gets the value of the storeExpansionArray property.
     * 
     * @return
     *     possible object is
     *     {@link ExpansionArrayType }
     *     
     */
    public ExpansionArrayType getStoreExpansionArray() {
        return storeExpansionArray;
    }

    /**
     * Sets the value of the storeExpansionArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExpansionArrayType }
     *     
     */
    public void setStoreExpansionArray(ExpansionArrayType value) {
        this.storeExpansionArray = value;
    }

    /**
     * Gets the value of the internationalExpansionArray property.
     * 
     * @return
     *     possible object is
     *     {@link ExpansionArrayType }
     *     
     */
    public ExpansionArrayType getInternationalExpansionArray() {
        return internationalExpansionArray;
    }

    /**
     * Sets the value of the internationalExpansionArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExpansionArrayType }
     *     
     */
    public void setInternationalExpansionArray(ExpansionArrayType value) {
        this.internationalExpansionArray = value;
    }

    /**
     * Gets the value of the filterRemovedExpansionArray property.
     * 
     * @return
     *     possible object is
     *     {@link ExpansionArrayType }
     *     
     */
    public ExpansionArrayType getFilterRemovedExpansionArray() {
        return filterRemovedExpansionArray;
    }

    /**
     * Sets the value of the filterRemovedExpansionArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExpansionArrayType }
     *     
     */
    public void setFilterRemovedExpansionArray(ExpansionArrayType value) {
        this.filterRemovedExpansionArray = value;
    }

    /**
     * Gets the value of the allCategoriesExpansionArray property.
     * 
     * @return
     *     possible object is
     *     {@link ExpansionArrayType }
     *     
     */
    public ExpansionArrayType getAllCategoriesExpansionArray() {
        return allCategoriesExpansionArray;
    }

    /**
     * Sets the value of the allCategoriesExpansionArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExpansionArrayType }
     *     
     */
    public void setAllCategoriesExpansionArray(ExpansionArrayType value) {
        this.allCategoriesExpansionArray = value;
    }

    /**
     * Gets the value of the spellingSuggestion property.
     * 
     * @return
     *     possible object is
     *     {@link SpellingSuggestionType }
     *     
     */
    public SpellingSuggestionType getSpellingSuggestion() {
        return spellingSuggestion;
    }

    /**
     * Sets the value of the spellingSuggestion property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpellingSuggestionType }
     *     
     */
    public void setSpellingSuggestion(SpellingSuggestionType value) {
        this.spellingSuggestion = value;
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
