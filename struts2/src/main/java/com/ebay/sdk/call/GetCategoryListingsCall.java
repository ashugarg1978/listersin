/*
Copyright (c) 2009 eBay, Inc.

This program is licensed under the terms of the eBay Common Development and 
Distribution License (CDDL) Version 1.0 (the "License") and any subsequent 
version thereof released by eBay.  The then-current version of the License 
can be found at https://www.codebase.ebay.com/Licenses.html and in the 
eBaySDKLicense file that is under the eBay SDK install directory.
*/

package com.ebay.sdk.call;

import com.ebay.sdk.model.*;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the GetCategoryListings call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>MotorsGermanySearchable</code> - Filters the response based on each item's eligibility to appear on the
 * mobile.de site. If false, excludes eligible items from search results. If
 * true, queries for eligible items only. If not specified, the search
 * results are not affected. Only applicable for items listed on the eBay
 * Germany site (site ID 77) in subcategories of mobile.de search-enabled
 * categories.
 * <br> <B>Input property:</B> <code>CategoryID</code> - Specifies the category for which to retrieve item listings.
 * <br> <B>Input property:</B> <code>AdFormat</code> - Restricts listings to return only items that have the Ad Format feature.
 * If true, the values of ItemTypeFilter and
 * StoreSearchFilter are ignored (if they are specified). That is, "AND"
 * logic is not applied.
 * <br> <B>Input property:</B> <code>FreeShipping</code> - If true, only items with free shipping for the user's location are
 * returned. The user's location is determined from the site ID specified
 * in the request. If false, no filtering is done via this attribute. A
 * listing is not considered a free shipping listing if it requires
 * insurance or requires pick up or requires a shipping surcharge.
 * <br> <B>Input property:</B> <code>Currency</code> - A currency value. Limits the result set to just those items listed
 * using a specified currency. Not applicable to US eBay Motors searches.
 * <br> <B>Input property:</B> <code>ItemTypeFilter</code> - Filters items based on the ListingType set for the items.
 * If ItemTypeFilter is not specified (or if the
 * AllItemTypes value of ItemTypeFilter is specified), all listing types can
 * be returned unless another relevant filter is specified.
 * <br> <B>Input property:</B> <code>SearchType</code> - Specifies whether to limit the item listings to just those that are
 * category featured or super featured or all items.
 * <br> <B>Input property:</B> <code>OrderBy</code> - Specifies the order in which the item listings returned will be sorted.
 * Store Inventory listings are usually returned after other listing types,
 * regardless of the sort order.
 * <br> <B>Input property:</B> <code>Pagination</code> - Controls the pagination of the result set. Child elements specify the
 * maximum number of item listings to return per call and which page of data
 * to return.
 * <br> <B>Input property:</B> <code>SearchLocation</code> - Limits the result set to just those items that meet location criteria:
 * listed in a specified eBay site, location where the seller has the item,
 * location from which the user is searching, and/or items listed with a
 * specified currency.
 * <br> <B>Input property:</B> <code>ProximitySearch</code> - Limits the result set to just those items that meet proximity search
 * criteria: postal code and max distance.
 * <br> <B>Input property:</B> <code>IncludeGetItFastItems</code> - When passed with a value of true, limits the results to Get It Fast listings.
 * <br> <B>Input property:</B> <code>PaymentMethod</code> - Specifies items that accept a specific payment method or methods.
 * <br> <B>Input property:</B> <code>IncludeCondition</code> - If true, each item in the result set can also include the item
 * condition (whether the item is new or used).
 * The item's condition is returned in Item.AttributeSetArray.
 * An item only includes condition attribute if the item's seller
 * filled in the Item Condition in the Item Specifics section of the
 * listing. (That is, the condition is not returned if the seller
 * only put the word "New" in the listing's title.)
 * <br> <B>Input property:</B> <code>IncludeFeedback</code> - If true, each item in the result set also includes information about the
 * seller's feedback.
 * <br> <B>Input property:</B> <code>LocalSearchPostalCode</code> - Include local items in returning results near this postal code. This
 * postal code is the basis for local search.
 * <br> <B>Input property:</B> <code>MaxRelatedSearchKeywords</code> - The maximum number of related keywords to be retrieved.
 * Use this field if you want the results to include
 * recommended keywords (that is, keywords matching the category ID)
 * in a RelatedSearchKeywordArray container.
 * A value of 0 (the default) means no related search information is processed.
 * <br> <B>Input property:</B> <code>Group</code> - You can group Best Match search results by category. To group
 * by category, put the BestMatchCategoryGroup value
 * in the OrderBy field.
 * When you use the BestMatchCategoryGroup value,
 * you can include group
 * parameters in your call. Note
 * that there will be significanty fewer results returned with a BestMatchCategoryGroup sort because the results account
 * for Best Matches in lower-level
 * (leaf) as well as higher-level categories.
 * There is not a direct correlation between the number of results returned in a regular sort or
 * the number of results returned with a BestMatch sort, and the results that are returned by
 * the BestMatchCategoryGroup sort. You should not receive more
 * than 2 pages of results with
 * this type of sort. See also
 * the new GroupCategoryID element
 * in ItemType.
 * <br> <B>Input property:</B> <code>HideDuplicateItems</code> - Specifies whether or not to remove duplicate items from search results.
 * When set to true, and there are duplicate items for an item in the
 * search results, the subsequent duplicates will not appear in the
 * results.
 * Item listings are considered duplicates in the following
 * conditions: <br>
 * <ul>
 * <li>Items are listed by the same seller</li>
 * <li>Items have exactly the same item title</li>
 * <li>Items have similar listing formats</li>
 * <ul>
 * <li>Auctions: Auction Items, Auction BIN items, Multi-Quantity
 * Auctions, and Multi-Quantity Auctions BIN items</li>
 * <li>Fixed Price: Fixed Price, Multi-quantity Fixed Price, Fixed
 * Price with Best Offer, and Store Inventory Format items</li>
 * <li>Classified Ads</li>
 * </ul>
 * </ul><br>
 * For Auctions, items must also have the same price and number of bids to
 * be considered duplicates.
 * <br>
 * Filtering of duplicate item listings is not supported on all sites.
 * <br> <B>Output property:</B> <code>ReturnedItems</code> - Contains the item listings for the specified category and which meet the
 * input filtering criteria (if any is specified). Consists of one ItemType
 * object for each returned item listing.
 * <br> <B>Output property:</B> <code>ReturnedCategoryType</code> - Indicates the category from which the listings were drawn.
 * <br> <B>Output property:</B> <code>ReturnedSubCategories</code> - Collection of the sub-categories that are child to the category indicated
 * in Category. Data for each sub-category is conveyed in a CategoryType
 * object.
 * <br> <B>Output property:</B> <code>ReturnedItemsPerPage</code> - Indicates the maximum number of item listings that will be returned per
 * call.
 * <br> <B>Output property:</B> <code>ReturnedPageNumber</code> - Indicates the page of data returned in the current call.
 * <br> <B>Output property:</B> <code>HasMoreItems</code> - Indicates whether there are more item listings that can be returned
 * (items listed in the specified category and that meet any input filtering
 * criteria).
 * <br> <B>Output property:</B> <code>PaginationResult</code> - Indicates the results of the pagination, including the total number of
 * pages of data there are to be returned and the total number of items
 * there are to be returned.
 * <br> <B>Output property:</B> <code>ReturnedBuyingGuideDetails</code> - Contains information about relevant buying guides (if any) and the
 * site's buying guide hub. Buying guides are useful to buyers who do not
 * have a specific product in mind. For example, a digital camera buying
 * guide could help a buyer determine what kind of digital camera is right
 * for them.
 * <br> <B>Output property:</B> <code>ReturnedRelatedSearchKeywordArray</code> - Container for keywords related to the category ID in the request.
 * Can be returned if the request specified more than zero in
 * the MaxRelatedSearchKeywords field.
 * <br> <B>Output property:</B> <code>ReturnedDuplicateItems</code> - Indicates whether there are duplicated items not returned by this 
 * response when HideDuplicateItems is true in the request. 
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class GetCategoryListingsCall extends com.ebay.sdk.ApiCall
{
  
  private Boolean motorsGermanySearchable = null;
  private String categoryID = null;
  private Boolean adFormat = null;
  private Boolean freeShipping = null;
  private CurrencyCodeType currency = null;
  private ItemTypeFilterCodeType itemTypeFilter = null;
  private CategoryListingsSearchCodeType searchType = null;
  private CategoryListingsOrderCodeType orderBy = null;
  private PaginationType pagination = null;
  private SearchLocationType searchLocation = null;
  private ProximitySearchType proximitySearch = null;
  private Boolean includeGetItFastItems = null;
  private PaymentMethodSearchCodeType paymentMethod = null;
  private Boolean includeCondition = null;
  private Boolean includeFeedback = null;
  private String localSearchPostalCode = null;
  private Integer maxRelatedSearchKeywords = null;
  private GroupType group = null;
  private Boolean hideDuplicateItems = null;
  private ItemType[] returnedItems=null;
  private CategoryType returnedCategoryType=null;
  private CategoryType[] returnedSubCategories=null;
  private Integer returnedItemsPerPage=null;
  private Integer returnedPageNumber=null;
  private boolean hasMoreItems=false;
  private PaginationResultType paginationResult=null;
  private BuyingGuideDetailsType returnedBuyingGuideDetails=null;
  private RelatedSearchKeywordArrayType returnedRelatedSearchKeywordArray=null;
  private Boolean returnedDuplicateItems=null;


  /**
   * Constructor.
   */
  public GetCategoryListingsCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public GetCategoryListingsCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Returns items in a specified category, with the ability to filter the items
   * in various ways.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The ItemType[] object.
   */
  public ItemType[] getCategoryListings()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GetCategoryListingsRequestType req;
    req = new GetCategoryListingsRequestType();

    if( this.categoryID == null )
      throw new SdkException("CategoryID property is not set.");

    req.setDetailLevel(this.getDetailLevel());
    if (this.motorsGermanySearchable != null)
      req.setMotorsGermanySearchable(this.motorsGermanySearchable);
    if (this.categoryID != null)
      req.setCategoryID(this.categoryID);
    if (this.adFormat != null)
      req.setAdFormat(this.adFormat);
    if (this.freeShipping != null)
      req.setFreeShipping(this.freeShipping);
    if (this.currency != null)
      req.setCurrency(this.currency);
    if (this.itemTypeFilter != null)
      req.setItemTypeFilter(this.itemTypeFilter);
    if (this.searchType != null)
      req.setSearchType(this.searchType);
    if (this.orderBy != null)
      req.setOrderBy(this.orderBy);
    if (this.pagination != null)
      req.setPagination(this.pagination);
    if (this.searchLocation != null)
      req.setSearchLocation(this.searchLocation);
    if (this.proximitySearch != null)
      req.setProximitySearch(this.proximitySearch);
    if (this.includeGetItFastItems != null)
      req.setIncludeGetItFastItems(this.includeGetItFastItems);
    if (this.paymentMethod != null)
      req.setPaymentMethod(this.paymentMethod);
    if (this.includeCondition != null)
      req.setIncludeCondition(this.includeCondition);
    if (this.includeFeedback != null)
      req.setIncludeFeedback(this.includeFeedback);
    if (this.localSearchPostalCode != null)
      req.setLocalSearchPostalCode(this.localSearchPostalCode);
    if (this.maxRelatedSearchKeywords != null)
      req.setMaxRelatedSearchKeywords(this.maxRelatedSearchKeywords);
    if (this.group != null)
      req.setGroup(this.group);
    if (this.hideDuplicateItems != null)
      req.setHideDuplicateItems(this.hideDuplicateItems);

    GetCategoryListingsResponseType resp = (GetCategoryListingsResponseType) execute(req);

    this.returnedItems = (resp.getItemArray() == null? null: resp.getItemArray().getItem());
    this.returnedCategoryType = resp.getCategory();
    this.returnedSubCategories = (resp.getSubCategories() == null? null: resp.getSubCategories().getCategory());
    this.returnedItemsPerPage = resp.getItemsPerPage();
    this.returnedPageNumber = resp.getPageNumber();
    this.hasMoreItems = resp.isHasMoreItems();
    this.paginationResult = resp.getPaginationResult();
    this.returnedBuyingGuideDetails = resp.getBuyingGuideDetails();
    this.returnedRelatedSearchKeywordArray = resp.getRelatedSearchKeywordArray();
    this.returnedDuplicateItems = resp.isDuplicateItems();
    // Save EntriesPerPage and PageNumber back to the input PaginationType.
    if( this.pagination == null )
      this.pagination = new PaginationType();
    if( resp.getItemsPerPage() != null )
      this.pagination.setEntriesPerPage(resp.getItemsPerPage());
    if( resp.getPageNumber() != null )
      this.pagination.setPageNumber(resp.getPageNumber());
    return this.getReturnedItems();
  }

  /**
   * Gets the GetCategoryListingsRequestType.adFormat.
   * @return Boolean
   */
  public Boolean getAdFormat()
  {
    return this.adFormat;
  }

  /**
   * Sets the GetCategoryListingsRequestType.adFormat.
   * @param adFormat Boolean
   */
  public void setAdFormat(Boolean adFormat)
  {
    this.adFormat = adFormat;
  }

  /**
   * Gets the GetCategoryListingsRequestType.categoryID.
   * @return String
   */
  public String getCategoryID()
  {
    return this.categoryID;
  }

  /**
   * Sets the GetCategoryListingsRequestType.categoryID.
   * @param categoryID String
   */
  public void setCategoryID(String categoryID)
  {
    this.categoryID = categoryID;
  }

  /**
   * Gets the GetCategoryListingsRequestType.currency.
   * @return CurrencyCodeType
   */
  public CurrencyCodeType getCurrency()
  {
    return this.currency;
  }

  /**
   * Sets the GetCategoryListingsRequestType.currency.
   * @param currency CurrencyCodeType
   */
  public void setCurrency(CurrencyCodeType currency)
  {
    this.currency = currency;
  }

  /**
   * Gets the GetCategoryListingsRequestType.freeShipping.
   * @return Boolean
   */
  public Boolean getFreeShipping()
  {
    return this.freeShipping;
  }

  /**
   * Sets the GetCategoryListingsRequestType.freeShipping.
   * @param freeShipping Boolean
   */
  public void setFreeShipping(Boolean freeShipping)
  {
    this.freeShipping = freeShipping;
  }

  /**
   * Gets the GetCategoryListingsRequestType.group.
   * @return GroupType
   */
  public GroupType getGroup()
  {
    return this.group;
  }

  /**
   * Sets the GetCategoryListingsRequestType.group.
   * @param group GroupType
   */
  public void setGroup(GroupType group)
  {
    this.group = group;
  }

  /**
   * Gets the GetCategoryListingsRequestType.hideDuplicateItems.
   * @return Boolean
   */
  public Boolean getHideDuplicateItems()
  {
    return this.hideDuplicateItems;
  }

  /**
   * Sets the GetCategoryListingsRequestType.hideDuplicateItems.
   * @param hideDuplicateItems Boolean
   */
  public void setHideDuplicateItems(Boolean hideDuplicateItems)
  {
    this.hideDuplicateItems = hideDuplicateItems;
  }

  /**
   * Gets the GetCategoryListingsRequestType.includeCondition.
   * @return Boolean
   */
  public Boolean getIncludeCondition()
  {
    return this.includeCondition;
  }

  /**
   * Sets the GetCategoryListingsRequestType.includeCondition.
   * @param includeCondition Boolean
   */
  public void setIncludeCondition(Boolean includeCondition)
  {
    this.includeCondition = includeCondition;
  }

  /**
   * Gets the GetCategoryListingsRequestType.includeFeedback.
   * @return Boolean
   */
  public Boolean getIncludeFeedback()
  {
    return this.includeFeedback;
  }

  /**
   * Sets the GetCategoryListingsRequestType.includeFeedback.
   * @param includeFeedback Boolean
   */
  public void setIncludeFeedback(Boolean includeFeedback)
  {
    this.includeFeedback = includeFeedback;
  }

  /**
   * Gets the GetCategoryListingsRequestType.includeGetItFastItems.
   * @return Boolean
   */
  public Boolean getIncludeGetItFastItems()
  {
    return this.includeGetItFastItems;
  }

  /**
   * Sets the GetCategoryListingsRequestType.includeGetItFastItems.
   * @param includeGetItFastItems Boolean
   */
  public void setIncludeGetItFastItems(Boolean includeGetItFastItems)
  {
    this.includeGetItFastItems = includeGetItFastItems;
  }

  /**
   * Gets the GetCategoryListingsRequestType.itemTypeFilter.
   * @return ItemTypeFilterCodeType
   */
  public ItemTypeFilterCodeType getItemTypeFilter()
  {
    return this.itemTypeFilter;
  }

  /**
   * Sets the GetCategoryListingsRequestType.itemTypeFilter.
   * @param itemTypeFilter ItemTypeFilterCodeType
   */
  public void setItemTypeFilter(ItemTypeFilterCodeType itemTypeFilter)
  {
    this.itemTypeFilter = itemTypeFilter;
  }

  /**
   * Gets the GetCategoryListingsRequestType.localSearchPostalCode.
   * @return String
   */
  public String getLocalSearchPostalCode()
  {
    return this.localSearchPostalCode;
  }

  /**
   * Sets the GetCategoryListingsRequestType.localSearchPostalCode.
   * @param localSearchPostalCode String
   */
  public void setLocalSearchPostalCode(String localSearchPostalCode)
  {
    this.localSearchPostalCode = localSearchPostalCode;
  }

  /**
   * Gets the GetCategoryListingsRequestType.maxRelatedSearchKeywords.
   * @return Integer
   */
  public Integer getMaxRelatedSearchKeywords()
  {
    return this.maxRelatedSearchKeywords;
  }

  /**
   * Sets the GetCategoryListingsRequestType.maxRelatedSearchKeywords.
   * @param maxRelatedSearchKeywords Integer
   */
  public void setMaxRelatedSearchKeywords(Integer maxRelatedSearchKeywords)
  {
    this.maxRelatedSearchKeywords = maxRelatedSearchKeywords;
  }

  /**
   * Gets the GetCategoryListingsRequestType.motorsGermanySearchable.
   * @return Boolean
   */
  public Boolean getMotorsGermanySearchable()
  {
    return this.motorsGermanySearchable;
  }

  /**
   * Sets the GetCategoryListingsRequestType.motorsGermanySearchable.
   * @param motorsGermanySearchable Boolean
   */
  public void setMotorsGermanySearchable(Boolean motorsGermanySearchable)
  {
    this.motorsGermanySearchable = motorsGermanySearchable;
  }

  /**
   * Gets the GetCategoryListingsRequestType.orderBy.
   * @return CategoryListingsOrderCodeType
   */
  public CategoryListingsOrderCodeType getOrderBy()
  {
    return this.orderBy;
  }

  /**
   * Sets the GetCategoryListingsRequestType.orderBy.
   * @param orderBy CategoryListingsOrderCodeType
   */
  public void setOrderBy(CategoryListingsOrderCodeType orderBy)
  {
    this.orderBy = orderBy;
  }

  /**
   * Gets the GetCategoryListingsRequestType.pagination.
   * @return PaginationType
   */
  public PaginationType getPagination()
  {
    return this.pagination;
  }

  /**
   * Sets the GetCategoryListingsRequestType.pagination.
   * @param pagination PaginationType
   */
  public void setPagination(PaginationType pagination)
  {
    this.pagination = pagination;
  }

  /**
   * Gets the GetCategoryListingsRequestType.paymentMethod.
   * @return PaymentMethodSearchCodeType
   */
  public PaymentMethodSearchCodeType getPaymentMethod()
  {
    return this.paymentMethod;
  }

  /**
   * Sets the GetCategoryListingsRequestType.paymentMethod.
   * @param paymentMethod PaymentMethodSearchCodeType
   */
  public void setPaymentMethod(PaymentMethodSearchCodeType paymentMethod)
  {
    this.paymentMethod = paymentMethod;
  }

  /**
   * Gets the GetCategoryListingsRequestType.proximitySearch.
   * @return ProximitySearchType
   */
  public ProximitySearchType getProximitySearch()
  {
    return this.proximitySearch;
  }

  /**
   * Sets the GetCategoryListingsRequestType.proximitySearch.
   * @param proximitySearch ProximitySearchType
   */
  public void setProximitySearch(ProximitySearchType proximitySearch)
  {
    this.proximitySearch = proximitySearch;
  }

  /**
   * Gets the GetCategoryListingsRequestType.searchLocation.
   * @return SearchLocationType
   */
  public SearchLocationType getSearchLocation()
  {
    return this.searchLocation;
  }

  /**
   * Sets the GetCategoryListingsRequestType.searchLocation.
   * @param searchLocation SearchLocationType
   */
  public void setSearchLocation(SearchLocationType searchLocation)
  {
    this.searchLocation = searchLocation;
  }

  /**
   * Gets the GetCategoryListingsRequestType.searchType.
   * @return CategoryListingsSearchCodeType
   */
  public CategoryListingsSearchCodeType getSearchType()
  {
    return this.searchType;
  }

  /**
   * Sets the GetCategoryListingsRequestType.searchType.
   * @param searchType CategoryListingsSearchCodeType
   */
  public void setSearchType(CategoryListingsSearchCodeType searchType)
  {
    this.searchType = searchType;
  }
/**
   * For backward compatibility.  Use getReturnedCategoryType instead.
   * After executing the API, returns category object. Category.getSubCategories()
   * will contain list of sub categories.
   * @return Category
   */
  public Category getReturnedCategory() {
    if( this.returnedCategoryType != null )
    {
      Category returnedCategory = new Category(this.returnedCategoryType);
      if( this.returnedSubCategories != null )
      {
        for(int i = 0; i < this.returnedSubCategories.length; i++ )
          returnedCategory.addSubCategory(new Category(this.returnedSubCategories[i]));
      }
      return returnedCategory;
    }
    else
      return null;
  }


  /**
   * Valid after executing the API.
   * Gets the returned GetCategoryListingsResponseType.hasMoreItems.
   * 
   * @return boolean
   */
  public boolean getHasMoreItems()
  {
    return this.hasMoreItems;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetCategoryListingsResponseType.paginationResult.
   * 
   * @return PaginationResultType
   */
  public PaginationResultType getPaginationResult()
  {
    return this.paginationResult;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetCategoryListingsResponseType.returnedBuyingGuideDetails.
   * 
   * @return BuyingGuideDetailsType
   */
  public BuyingGuideDetailsType getReturnedBuyingGuideDetails()
  {
    return this.returnedBuyingGuideDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetCategoryListingsResponseType.returnedCategoryType.
   * 
   * @return CategoryType
   */
  public CategoryType getReturnedCategoryType()
  {
    return this.returnedCategoryType;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetCategoryListingsResponseType.returnedDuplicateItems.
   * 
   * @return Boolean
   */
  public Boolean getReturnedDuplicateItems()
  {
    return this.returnedDuplicateItems;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetCategoryListingsResponseType.returnedItems.
   * 
   * @return ItemType[]
   */
  public ItemType[] getReturnedItems()
  {
    return this.returnedItems;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetCategoryListingsResponseType.returnedItemsPerPage.
   * 
   * @return Integer
   */
  public Integer getReturnedItemsPerPage()
  {
    return this.returnedItemsPerPage;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetCategoryListingsResponseType.returnedPageNumber.
   * 
   * @return Integer
   */
  public Integer getReturnedPageNumber()
  {
    return this.returnedPageNumber;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetCategoryListingsResponseType.returnedRelatedSearchKeywordArray.
   * 
   * @return RelatedSearchKeywordArrayType
   */
  public RelatedSearchKeywordArrayType getReturnedRelatedSearchKeywordArray()
  {
    return this.returnedRelatedSearchKeywordArray;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetCategoryListingsResponseType.returnedSubCategories.
   * 
   * @return CategoryType[]
   */
  public CategoryType[] getReturnedSubCategories()
  {
    return this.returnedSubCategories;
  }

}

