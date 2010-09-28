
package com.ebay.soap.eBLBaseComponents;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Returns items in a specified category, with the ability to filter the items
 * 				in various ways.
 * 			
 * 
 * <p>Java class for GetCategoryListingsRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetCategoryListingsRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractRequestType">
 *       &lt;sequence>
 *         &lt;element name="MotorsGermanySearchable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="CategoryID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AdFormat" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="FreeShipping" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Currency" type="{urn:ebay:apis:eBLBaseComponents}CurrencyCodeType" minOccurs="0"/>
 *         &lt;element name="ItemTypeFilter" type="{urn:ebay:apis:eBLBaseComponents}ItemTypeFilterCodeType" minOccurs="0"/>
 *         &lt;element name="SearchType" type="{urn:ebay:apis:eBLBaseComponents}CategoryListingsSearchCodeType" minOccurs="0"/>
 *         &lt;element name="OrderBy" type="{urn:ebay:apis:eBLBaseComponents}CategoryListingsOrderCodeType" minOccurs="0"/>
 *         &lt;element name="Pagination" type="{urn:ebay:apis:eBLBaseComponents}PaginationType" minOccurs="0"/>
 *         &lt;element name="SearchLocation" type="{urn:ebay:apis:eBLBaseComponents}SearchLocationType" minOccurs="0"/>
 *         &lt;element name="ProximitySearch" type="{urn:ebay:apis:eBLBaseComponents}ProximitySearchType" minOccurs="0"/>
 *         &lt;element name="IncludeGetItFastItems" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="PaymentMethod" type="{urn:ebay:apis:eBLBaseComponents}PaymentMethodSearchCodeType" minOccurs="0"/>
 *         &lt;element name="IncludeCondition" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IncludeFeedback" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="LocalSearchPostalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MaxRelatedSearchKeywords" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Group" type="{urn:ebay:apis:eBLBaseComponents}GroupType" minOccurs="0"/>
 *         &lt;element name="HideDuplicateItems" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetCategoryListingsRequestType", propOrder = {
    "motorsGermanySearchable",
    "categoryID",
    "adFormat",
    "freeShipping",
    "currency",
    "itemTypeFilter",
    "searchType",
    "orderBy",
    "pagination",
    "searchLocation",
    "proximitySearch",
    "includeGetItFastItems",
    "paymentMethod",
    "includeCondition",
    "includeFeedback",
    "localSearchPostalCode",
    "maxRelatedSearchKeywords",
    "group",
    "hideDuplicateItems"
})
public class GetCategoryListingsRequestType
    extends AbstractRequestType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "MotorsGermanySearchable")
    protected Boolean motorsGermanySearchable;
    @XmlElement(name = "CategoryID")
    protected String categoryID;
    @XmlElement(name = "AdFormat")
    protected Boolean adFormat;
    @XmlElement(name = "FreeShipping")
    protected Boolean freeShipping;
    @XmlElement(name = "Currency")
    protected CurrencyCodeType currency;
    @XmlElement(name = "ItemTypeFilter")
    protected ItemTypeFilterCodeType itemTypeFilter;
    @XmlElement(name = "SearchType")
    protected CategoryListingsSearchCodeType searchType;
    @XmlElement(name = "OrderBy")
    protected CategoryListingsOrderCodeType orderBy;
    @XmlElement(name = "Pagination")
    protected PaginationType pagination;
    @XmlElement(name = "SearchLocation")
    protected SearchLocationType searchLocation;
    @XmlElement(name = "ProximitySearch")
    protected ProximitySearchType proximitySearch;
    @XmlElement(name = "IncludeGetItFastItems")
    protected Boolean includeGetItFastItems;
    @XmlElement(name = "PaymentMethod")
    protected PaymentMethodSearchCodeType paymentMethod;
    @XmlElement(name = "IncludeCondition")
    protected Boolean includeCondition;
    @XmlElement(name = "IncludeFeedback")
    protected Boolean includeFeedback;
    @XmlElement(name = "LocalSearchPostalCode")
    protected String localSearchPostalCode;
    @XmlElement(name = "MaxRelatedSearchKeywords")
    protected Integer maxRelatedSearchKeywords;
    @XmlElement(name = "Group")
    protected GroupType group;
    @XmlElement(name = "HideDuplicateItems")
    protected Boolean hideDuplicateItems;

    /**
     * Gets the value of the motorsGermanySearchable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMotorsGermanySearchable() {
        return motorsGermanySearchable;
    }

    /**
     * Sets the value of the motorsGermanySearchable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMotorsGermanySearchable(Boolean value) {
        this.motorsGermanySearchable = value;
    }

    /**
     * Gets the value of the categoryID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryID() {
        return categoryID;
    }

    /**
     * Sets the value of the categoryID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryID(String value) {
        this.categoryID = value;
    }

    /**
     * Gets the value of the adFormat property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAdFormat() {
        return adFormat;
    }

    /**
     * Sets the value of the adFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAdFormat(Boolean value) {
        this.adFormat = value;
    }

    /**
     * Gets the value of the freeShipping property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFreeShipping() {
        return freeShipping;
    }

    /**
     * Sets the value of the freeShipping property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFreeShipping(Boolean value) {
        this.freeShipping = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link CurrencyCodeType }
     *     
     */
    public CurrencyCodeType getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrencyCodeType }
     *     
     */
    public void setCurrency(CurrencyCodeType value) {
        this.currency = value;
    }

    /**
     * Gets the value of the itemTypeFilter property.
     * 
     * @return
     *     possible object is
     *     {@link ItemTypeFilterCodeType }
     *     
     */
    public ItemTypeFilterCodeType getItemTypeFilter() {
        return itemTypeFilter;
    }

    /**
     * Sets the value of the itemTypeFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemTypeFilterCodeType }
     *     
     */
    public void setItemTypeFilter(ItemTypeFilterCodeType value) {
        this.itemTypeFilter = value;
    }

    /**
     * Gets the value of the searchType property.
     * 
     * @return
     *     possible object is
     *     {@link CategoryListingsSearchCodeType }
     *     
     */
    public CategoryListingsSearchCodeType getSearchType() {
        return searchType;
    }

    /**
     * Sets the value of the searchType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoryListingsSearchCodeType }
     *     
     */
    public void setSearchType(CategoryListingsSearchCodeType value) {
        this.searchType = value;
    }

    /**
     * Gets the value of the orderBy property.
     * 
     * @return
     *     possible object is
     *     {@link CategoryListingsOrderCodeType }
     *     
     */
    public CategoryListingsOrderCodeType getOrderBy() {
        return orderBy;
    }

    /**
     * Sets the value of the orderBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoryListingsOrderCodeType }
     *     
     */
    public void setOrderBy(CategoryListingsOrderCodeType value) {
        this.orderBy = value;
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

    /**
     * Gets the value of the searchLocation property.
     * 
     * @return
     *     possible object is
     *     {@link SearchLocationType }
     *     
     */
    public SearchLocationType getSearchLocation() {
        return searchLocation;
    }

    /**
     * Sets the value of the searchLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchLocationType }
     *     
     */
    public void setSearchLocation(SearchLocationType value) {
        this.searchLocation = value;
    }

    /**
     * Gets the value of the proximitySearch property.
     * 
     * @return
     *     possible object is
     *     {@link ProximitySearchType }
     *     
     */
    public ProximitySearchType getProximitySearch() {
        return proximitySearch;
    }

    /**
     * Sets the value of the proximitySearch property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProximitySearchType }
     *     
     */
    public void setProximitySearch(ProximitySearchType value) {
        this.proximitySearch = value;
    }

    /**
     * Gets the value of the includeGetItFastItems property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeGetItFastItems() {
        return includeGetItFastItems;
    }

    /**
     * Sets the value of the includeGetItFastItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeGetItFastItems(Boolean value) {
        this.includeGetItFastItems = value;
    }

    /**
     * Gets the value of the paymentMethod property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentMethodSearchCodeType }
     *     
     */
    public PaymentMethodSearchCodeType getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets the value of the paymentMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentMethodSearchCodeType }
     *     
     */
    public void setPaymentMethod(PaymentMethodSearchCodeType value) {
        this.paymentMethod = value;
    }

    /**
     * Gets the value of the includeCondition property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeCondition() {
        return includeCondition;
    }

    /**
     * Sets the value of the includeCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeCondition(Boolean value) {
        this.includeCondition = value;
    }

    /**
     * Gets the value of the includeFeedback property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeFeedback() {
        return includeFeedback;
    }

    /**
     * Sets the value of the includeFeedback property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeFeedback(Boolean value) {
        this.includeFeedback = value;
    }

    /**
     * Gets the value of the localSearchPostalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalSearchPostalCode() {
        return localSearchPostalCode;
    }

    /**
     * Sets the value of the localSearchPostalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalSearchPostalCode(String value) {
        this.localSearchPostalCode = value;
    }

    /**
     * Gets the value of the maxRelatedSearchKeywords property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxRelatedSearchKeywords() {
        return maxRelatedSearchKeywords;
    }

    /**
     * Sets the value of the maxRelatedSearchKeywords property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxRelatedSearchKeywords(Integer value) {
        this.maxRelatedSearchKeywords = value;
    }

    /**
     * Gets the value of the group property.
     * 
     * @return
     *     possible object is
     *     {@link GroupType }
     *     
     */
    public GroupType getGroup() {
        return group;
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupType }
     *     
     */
    public void setGroup(GroupType value) {
        this.group = value;
    }

    /**
     * Gets the value of the hideDuplicateItems property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHideDuplicateItems() {
        return hideDuplicateItems;
    }

    /**
     * Sets the value of the hideDuplicateItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHideDuplicateItems(Boolean value) {
        this.hideDuplicateItems = value;
    }

}
