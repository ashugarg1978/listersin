
package com.ebay.soap.eBLBaseComponents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 * 				Retrieves item listings based on keywords you specify. The keywords can
 * 				include wildcards.
 * 			
 * 
 * <p>Java class for GetSearchResultsRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetSearchResultsRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractRequestType">
 *       &lt;sequence>
 *         &lt;element name="MotorsGermanySearchable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Query" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CategoryID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SearchFlags" type="{urn:ebay:apis:eBLBaseComponents}SearchFlagsCodeType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PriceRangeFilter" type="{urn:ebay:apis:eBLBaseComponents}PriceRangeFilterType" minOccurs="0"/>
 *         &lt;element name="ProximitySearch" type="{urn:ebay:apis:eBLBaseComponents}ProximitySearchType" minOccurs="0"/>
 *         &lt;element name="ItemTypeFilter" type="{urn:ebay:apis:eBLBaseComponents}ItemTypeFilterCodeType" minOccurs="0"/>
 *         &lt;element name="SearchType" type="{urn:ebay:apis:eBLBaseComponents}SearchTypeCodeType" minOccurs="0"/>
 *         &lt;element name="UserIdFilter" type="{urn:ebay:apis:eBLBaseComponents}UserIdFilterType" minOccurs="0"/>
 *         &lt;element name="SearchLocationFilter" type="{urn:ebay:apis:eBLBaseComponents}SearchLocationFilterType" minOccurs="0"/>
 *         &lt;element name="StoreSearchFilter" type="{urn:ebay:apis:eBLBaseComponents}SearchStoreFilterType" minOccurs="0"/>
 *         &lt;element name="Order" type="{urn:ebay:apis:eBLBaseComponents}SearchSortOrderCodeType" minOccurs="0"/>
 *         &lt;element name="Pagination" type="{urn:ebay:apis:eBLBaseComponents}PaginationType" minOccurs="0"/>
 *         &lt;element name="SearchRequest" type="{urn:ebay:apis:eBLBaseComponents}SearchRequestType" minOccurs="0"/>
 *         &lt;element name="ProductID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExternalProductID" type="{urn:ebay:apis:eBLBaseComponents}ExternalProductIDType" minOccurs="0"/>
 *         &lt;element name="Categories" type="{urn:ebay:apis:eBLBaseComponents}RequestCategoriesType" minOccurs="0"/>
 *         &lt;element name="TotalOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="EndTimeFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="EndTimeTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ModTimeFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="IncludeGetItFastItems" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="PaymentMethod" type="{urn:ebay:apis:eBLBaseComponents}PaymentMethodSearchCodeType" minOccurs="0"/>
 *         &lt;element name="GranularityLevel" type="{urn:ebay:apis:eBLBaseComponents}GranularityLevelCodeType" minOccurs="0"/>
 *         &lt;element name="ExpandSearch" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Lot" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AdFormat" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="FreeShipping" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Quantity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="QuantityOperator" type="{urn:ebay:apis:eBLBaseComponents}QuantityOperatorCodeType" minOccurs="0"/>
 *         &lt;element name="SellerBusinessType" type="{urn:ebay:apis:eBLBaseComponents}SellerBusinessCodeType" minOccurs="0"/>
 *         &lt;element name="IncludeCondition" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IncludeFeedback" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="CharityID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="LocalSearchPostalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MaxRelatedSearchKeywords" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="AffiliateTrackingDetails" type="{urn:ebay:apis:eBLBaseComponents}AffiliateTrackingDetailsType" minOccurs="0"/>
 *         &lt;element name="BidRange" type="{urn:ebay:apis:eBLBaseComponents}BidRangeType" minOccurs="0"/>
 *         &lt;element name="ItemCondition" type="{urn:ebay:apis:eBLBaseComponents}ItemConditionCodeType" minOccurs="0"/>
 *         &lt;element name="TicketFinder" type="{urn:ebay:apis:eBLBaseComponents}TicketDetailsType" minOccurs="0"/>
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
@XmlType(name = "GetSearchResultsRequestType", propOrder = {
    "motorsGermanySearchable",
    "query",
    "categoryID",
    "searchFlags",
    "priceRangeFilter",
    "proximitySearch",
    "itemTypeFilter",
    "searchType",
    "userIdFilter",
    "searchLocationFilter",
    "storeSearchFilter",
    "order",
    "pagination",
    "searchRequest",
    "productID",
    "externalProductID",
    "categories",
    "totalOnly",
    "endTimeFrom",
    "endTimeTo",
    "modTimeFrom",
    "includeGetItFastItems",
    "paymentMethod",
    "granularityLevel",
    "expandSearch",
    "lot",
    "adFormat",
    "freeShipping",
    "quantity",
    "quantityOperator",
    "sellerBusinessType",
    "includeCondition",
    "includeFeedback",
    "charityID",
    "localSearchPostalCode",
    "maxRelatedSearchKeywords",
    "affiliateTrackingDetails",
    "bidRange",
    "itemCondition",
    "ticketFinder",
    "group",
    "hideDuplicateItems"
})
public class GetSearchResultsRequestType
    extends AbstractRequestType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "MotorsGermanySearchable")
    protected Boolean motorsGermanySearchable;
    @XmlElement(name = "Query")
    protected String query;
    @XmlElement(name = "CategoryID")
    protected String categoryID;
    @XmlElement(name = "SearchFlags")
    protected List<SearchFlagsCodeType> searchFlags;
    @XmlElement(name = "PriceRangeFilter")
    protected PriceRangeFilterType priceRangeFilter;
    @XmlElement(name = "ProximitySearch")
    protected ProximitySearchType proximitySearch;
    @XmlElement(name = "ItemTypeFilter")
    protected ItemTypeFilterCodeType itemTypeFilter;
    @XmlElement(name = "SearchType")
    protected SearchTypeCodeType searchType;
    @XmlElement(name = "UserIdFilter")
    protected UserIdFilterType userIdFilter;
    @XmlElement(name = "SearchLocationFilter")
    protected SearchLocationFilterType searchLocationFilter;
    @XmlElement(name = "StoreSearchFilter")
    protected SearchStoreFilterType storeSearchFilter;
    @XmlElement(name = "Order")
    protected SearchSortOrderCodeType order;
    @XmlElement(name = "Pagination")
    protected PaginationType pagination;
    @XmlElement(name = "SearchRequest")
    protected SearchRequestType searchRequest;
    @XmlElement(name = "ProductID")
    protected String productID;
    @XmlElement(name = "ExternalProductID")
    protected ExternalProductIDType externalProductID;
    @XmlElement(name = "Categories")
    protected RequestCategoriesType categories;
    @XmlElement(name = "TotalOnly")
    protected Boolean totalOnly;
    @XmlElement(name = "EndTimeFrom", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar endTimeFrom;
    @XmlElement(name = "EndTimeTo", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar endTimeTo;
    @XmlElement(name = "ModTimeFrom", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar modTimeFrom;
    @XmlElement(name = "IncludeGetItFastItems")
    protected Boolean includeGetItFastItems;
    @XmlElement(name = "PaymentMethod")
    protected PaymentMethodSearchCodeType paymentMethod;
    @XmlElement(name = "GranularityLevel")
    protected GranularityLevelCodeType granularityLevel;
    @XmlElement(name = "ExpandSearch")
    protected Boolean expandSearch;
    @XmlElement(name = "Lot")
    protected Boolean lot;
    @XmlElement(name = "AdFormat")
    protected Boolean adFormat;
    @XmlElement(name = "FreeShipping")
    protected Boolean freeShipping;
    @XmlElement(name = "Quantity")
    protected Integer quantity;
    @XmlElement(name = "QuantityOperator")
    protected QuantityOperatorCodeType quantityOperator;
    @XmlElement(name = "SellerBusinessType")
    protected SellerBusinessCodeType sellerBusinessType;
    @XmlElement(name = "IncludeCondition")
    protected Boolean includeCondition;
    @XmlElement(name = "IncludeFeedback")
    protected Boolean includeFeedback;
    @XmlElement(name = "CharityID")
    protected Integer charityID;
    @XmlElement(name = "LocalSearchPostalCode")
    protected String localSearchPostalCode;
    @XmlElement(name = "MaxRelatedSearchKeywords")
    protected Integer maxRelatedSearchKeywords;
    @XmlElement(name = "AffiliateTrackingDetails")
    protected AffiliateTrackingDetailsType affiliateTrackingDetails;
    @XmlElement(name = "BidRange")
    protected BidRangeType bidRange;
    @XmlElement(name = "ItemCondition")
    protected ItemConditionCodeType itemCondition;
    @XmlElement(name = "TicketFinder")
    protected TicketDetailsType ticketFinder;
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
     * Gets the value of the query property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuery() {
        return query;
    }

    /**
     * Sets the value of the query property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuery(String value) {
        this.query = value;
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
     * 
     * 
     * @return
     *     array of
     *     {@link SearchFlagsCodeType }
     *     
     */
    public SearchFlagsCodeType[] getSearchFlags() {
        if (this.searchFlags == null) {
            return new SearchFlagsCodeType[ 0 ] ;
        }
        return ((SearchFlagsCodeType[]) this.searchFlags.toArray(new SearchFlagsCodeType[this.searchFlags.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link SearchFlagsCodeType }
     *     
     */
    public SearchFlagsCodeType getSearchFlags(int idx) {
        if (this.searchFlags == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.searchFlags.get(idx);
    }

    public int getSearchFlagsLength() {
        if (this.searchFlags == null) {
            return  0;
        }
        return this.searchFlags.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link SearchFlagsCodeType }
     *     
     */
    public void setSearchFlags(SearchFlagsCodeType[] values) {
        this._getSearchFlags().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.searchFlags.add(values[i]);
        }
    }

    protected List<SearchFlagsCodeType> _getSearchFlags() {
        if (searchFlags == null) {
            searchFlags = new ArrayList<SearchFlagsCodeType>();
        }
        return searchFlags;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link SearchFlagsCodeType }
     *     
     */
    public SearchFlagsCodeType setSearchFlags(int idx, SearchFlagsCodeType value) {
        return this.searchFlags.set(idx, value);
    }

    /**
     * Gets the value of the priceRangeFilter property.
     * 
     * @return
     *     possible object is
     *     {@link PriceRangeFilterType }
     *     
     */
    public PriceRangeFilterType getPriceRangeFilter() {
        return priceRangeFilter;
    }

    /**
     * Sets the value of the priceRangeFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link PriceRangeFilterType }
     *     
     */
    public void setPriceRangeFilter(PriceRangeFilterType value) {
        this.priceRangeFilter = value;
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
     *     {@link SearchTypeCodeType }
     *     
     */
    public SearchTypeCodeType getSearchType() {
        return searchType;
    }

    /**
     * Sets the value of the searchType property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchTypeCodeType }
     *     
     */
    public void setSearchType(SearchTypeCodeType value) {
        this.searchType = value;
    }

    /**
     * Gets the value of the userIdFilter property.
     * 
     * @return
     *     possible object is
     *     {@link UserIdFilterType }
     *     
     */
    public UserIdFilterType getUserIdFilter() {
        return userIdFilter;
    }

    /**
     * Sets the value of the userIdFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserIdFilterType }
     *     
     */
    public void setUserIdFilter(UserIdFilterType value) {
        this.userIdFilter = value;
    }

    /**
     * Gets the value of the searchLocationFilter property.
     * 
     * @return
     *     possible object is
     *     {@link SearchLocationFilterType }
     *     
     */
    public SearchLocationFilterType getSearchLocationFilter() {
        return searchLocationFilter;
    }

    /**
     * Sets the value of the searchLocationFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchLocationFilterType }
     *     
     */
    public void setSearchLocationFilter(SearchLocationFilterType value) {
        this.searchLocationFilter = value;
    }

    /**
     * Gets the value of the storeSearchFilter property.
     * 
     * @return
     *     possible object is
     *     {@link SearchStoreFilterType }
     *     
     */
    public SearchStoreFilterType getStoreSearchFilter() {
        return storeSearchFilter;
    }

    /**
     * Sets the value of the storeSearchFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchStoreFilterType }
     *     
     */
    public void setStoreSearchFilter(SearchStoreFilterType value) {
        this.storeSearchFilter = value;
    }

    /**
     * Gets the value of the order property.
     * 
     * @return
     *     possible object is
     *     {@link SearchSortOrderCodeType }
     *     
     */
    public SearchSortOrderCodeType getOrder() {
        return order;
    }

    /**
     * Sets the value of the order property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchSortOrderCodeType }
     *     
     */
    public void setOrder(SearchSortOrderCodeType value) {
        this.order = value;
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
     * Gets the value of the searchRequest property.
     * 
     * @return
     *     possible object is
     *     {@link SearchRequestType }
     *     
     */
    public SearchRequestType getSearchRequest() {
        return searchRequest;
    }

    /**
     * Sets the value of the searchRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchRequestType }
     *     
     */
    public void setSearchRequest(SearchRequestType value) {
        this.searchRequest = value;
    }

    /**
     * Gets the value of the productID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductID() {
        return productID;
    }

    /**
     * Sets the value of the productID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductID(String value) {
        this.productID = value;
    }

    /**
     * Gets the value of the externalProductID property.
     * 
     * @return
     *     possible object is
     *     {@link ExternalProductIDType }
     *     
     */
    public ExternalProductIDType getExternalProductID() {
        return externalProductID;
    }

    /**
     * Sets the value of the externalProductID property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExternalProductIDType }
     *     
     */
    public void setExternalProductID(ExternalProductIDType value) {
        this.externalProductID = value;
    }

    /**
     * Gets the value of the categories property.
     * 
     * @return
     *     possible object is
     *     {@link RequestCategoriesType }
     *     
     */
    public RequestCategoriesType getCategories() {
        return categories;
    }

    /**
     * Sets the value of the categories property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestCategoriesType }
     *     
     */
    public void setCategories(RequestCategoriesType value) {
        this.categories = value;
    }

    /**
     * Gets the value of the totalOnly property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTotalOnly() {
        return totalOnly;
    }

    /**
     * Sets the value of the totalOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTotalOnly(Boolean value) {
        this.totalOnly = value;
    }

    /**
     * Gets the value of the endTimeFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getEndTimeFrom() {
        return endTimeFrom;
    }

    /**
     * Sets the value of the endTimeFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndTimeFrom(Calendar value) {
        this.endTimeFrom = value;
    }

    /**
     * Gets the value of the endTimeTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getEndTimeTo() {
        return endTimeTo;
    }

    /**
     * Sets the value of the endTimeTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndTimeTo(Calendar value) {
        this.endTimeTo = value;
    }

    /**
     * Gets the value of the modTimeFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getModTimeFrom() {
        return modTimeFrom;
    }

    /**
     * Sets the value of the modTimeFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModTimeFrom(Calendar value) {
        this.modTimeFrom = value;
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
     * Gets the value of the granularityLevel property.
     * 
     * @return
     *     possible object is
     *     {@link GranularityLevelCodeType }
     *     
     */
    public GranularityLevelCodeType getGranularityLevel() {
        return granularityLevel;
    }

    /**
     * Sets the value of the granularityLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link GranularityLevelCodeType }
     *     
     */
    public void setGranularityLevel(GranularityLevelCodeType value) {
        this.granularityLevel = value;
    }

    /**
     * Gets the value of the expandSearch property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExpandSearch() {
        return expandSearch;
    }

    /**
     * Sets the value of the expandSearch property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExpandSearch(Boolean value) {
        this.expandSearch = value;
    }

    /**
     * Gets the value of the lot property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLot() {
        return lot;
    }

    /**
     * Sets the value of the lot property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLot(Boolean value) {
        this.lot = value;
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
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQuantity(Integer value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the quantityOperator property.
     * 
     * @return
     *     possible object is
     *     {@link QuantityOperatorCodeType }
     *     
     */
    public QuantityOperatorCodeType getQuantityOperator() {
        return quantityOperator;
    }

    /**
     * Sets the value of the quantityOperator property.
     * 
     * @param value
     *     allowed object is
     *     {@link QuantityOperatorCodeType }
     *     
     */
    public void setQuantityOperator(QuantityOperatorCodeType value) {
        this.quantityOperator = value;
    }

    /**
     * Gets the value of the sellerBusinessType property.
     * 
     * @return
     *     possible object is
     *     {@link SellerBusinessCodeType }
     *     
     */
    public SellerBusinessCodeType getSellerBusinessType() {
        return sellerBusinessType;
    }

    /**
     * Sets the value of the sellerBusinessType property.
     * 
     * @param value
     *     allowed object is
     *     {@link SellerBusinessCodeType }
     *     
     */
    public void setSellerBusinessType(SellerBusinessCodeType value) {
        this.sellerBusinessType = value;
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
     * Gets the value of the charityID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCharityID() {
        return charityID;
    }

    /**
     * Sets the value of the charityID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCharityID(Integer value) {
        this.charityID = value;
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
     * Gets the value of the affiliateTrackingDetails property.
     * 
     * @return
     *     possible object is
     *     {@link AffiliateTrackingDetailsType }
     *     
     */
    public AffiliateTrackingDetailsType getAffiliateTrackingDetails() {
        return affiliateTrackingDetails;
    }

    /**
     * Sets the value of the affiliateTrackingDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link AffiliateTrackingDetailsType }
     *     
     */
    public void setAffiliateTrackingDetails(AffiliateTrackingDetailsType value) {
        this.affiliateTrackingDetails = value;
    }

    /**
     * Gets the value of the bidRange property.
     * 
     * @return
     *     possible object is
     *     {@link BidRangeType }
     *     
     */
    public BidRangeType getBidRange() {
        return bidRange;
    }

    /**
     * Sets the value of the bidRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link BidRangeType }
     *     
     */
    public void setBidRange(BidRangeType value) {
        this.bidRange = value;
    }

    /**
     * Gets the value of the itemCondition property.
     * 
     * @return
     *     possible object is
     *     {@link ItemConditionCodeType }
     *     
     */
    public ItemConditionCodeType getItemCondition() {
        return itemCondition;
    }

    /**
     * Sets the value of the itemCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemConditionCodeType }
     *     
     */
    public void setItemCondition(ItemConditionCodeType value) {
        this.itemCondition = value;
    }

    /**
     * Gets the value of the ticketFinder property.
     * 
     * @return
     *     possible object is
     *     {@link TicketDetailsType }
     *     
     */
    public TicketDetailsType getTicketFinder() {
        return ticketFinder;
    }

    /**
     * Sets the value of the ticketFinder property.
     * 
     * @param value
     *     allowed object is
     *     {@link TicketDetailsType }
     *     
     */
    public void setTicketFinder(TicketDetailsType value) {
        this.ticketFinder = value;
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
