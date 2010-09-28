/*
Copyright (c) 2009 eBay, Inc.

This program is licensed under the terms of the eBay Common Development and 
Distribution License (CDDL) Version 1.0 (the "License") and any subsequent 
version thereof released by eBay.  The then-current version of the License 
can be found at https://www.codebase.ebay.com/Licenses.html and in the 
eBaySDKLicense file that is under the eBay SDK install directory.
*/

package com.ebay.sdk.call;

import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.Calendar;

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the GetSearchResults call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>MotorsGermanySearchable</code> - Limits the results based on each item's eligibility to appear on the
 * mobile.de site. If false, excludes eligible items from search results. If
 * true, queries for eligible items only. If not specified, the search
 * results are not affected. Only applicable for items listed on the eBay
 * Germany site (site ID 77) in subcategories of mobile.de search-enabled
 * categories.
 * <br> <B>Input property:</B> <code>Query</code> - A query that specifies a search string. The search string consists of one or more
 * keywords to search for in the listing title and/or description.
 * The words "and" and "or" are treated like any other word. Only use "and",
 * "or", or "the" if you are searching for listings containing these words.
 * You can use AND or OR logic by including certain modifiers.
 * Wildcards (e.g., *) are also supported. Be careful when using spaces before
 * or after modifiers and wildcards (+, -, or *). See the
 * eBay Web Services Guide	for a list of valid modifiers and examples.
 * Query is not applicable in combination with ExternalProductID or ProductID.
 * <br> <B>Input property:</B> <code>CategoryID</code> - Limits the result set to items in the specified category.
 * If no category ID is specified, all categories
 * are searched by default.<br>
 * <br>
 * <span class="tablenote"><b>Note:</b>
 * CategoryID does not control whether you will see the
 * item's categories in the response. Set the value of DetailLevel to
 * ItemReturnCategories to retrieve each item's primary category and
 * secondary category (if any) in the response.</span><br>
 * <br>
 * If the specified category ID doesn't match an existing category
 * for the site, an invalid-category error message is returned.
 * Here are a few ways to determine valid categories:<br>
 * - Use the Categories input field to retrieve
 * matching categories, and then submit the request again with one of
 * those categories.<br>
 * - Find items in all categories but set DetailLevel to
 * ItemReturnCategories, determine the primary (or secondary)
 * category ID for a similar item in the results, and then
 * submit the request again with that category ID.<br>
 * - Use another call like GetCategories or GetSuggestedCategories to
 * find a valid category ID.<br>
 * <br>
 * You must pass ProductID, Query, ExternalProductID, or CategoryID
 * in the request. CategoryID can be used in combination with Query.
 * It is not allowed with ExternalProductID or ProductID.
 * If you pass CategoryID without Query, it
 * must specify a leaf category ID. That is, it cannot be a
 * meta-category ID (e.g., 267 for "Books").
 * <br> <B>Input property:</B> <code>SearchFlags</code> - Secondary search criterion that checks item descriptions for keywords that
 * match the query, limits the search results to only charity items, limits
 * the result set to those items with PayPal as a payment method, and/or
 * provides other criteria to refine the search.
 * <br> <B>Input property:</B> <code>PriceRangeFilter</code> - Limits the result set to just those items where the price is within the
 * specified range. The PriceRangeFilterType includes a minimum and a maximum
 * price.
 * <br> <B>Input property:</B> <code>ProximitySearch</code> - Limits the result set to just those items whose location is within a
 * specified distance of a postal code. The ProximitySearchType includes
 * a maximum distance and a postal code.
 * <br> <B>Input property:</B> <code>ItemTypeFilter</code> - Filters items based on the ListingType set for the items.
 * If ItemTypeFilter is not
 * specified (or if the AllItemTypes value of ItemTypeFilter is specified),
 * all listing types can be returned unless another relevant filter is
 * specified.
 * <br> <B>Input property:</B> <code>SearchType</code> - Limits the listings in the result set based on whether they are in the
 * Gallery. The choices are items in the Gallery or Gallery and non-Gallery
 * items.
 * <br> <B>Input property:</B> <code>UserIdFilter</code> - Limits the the result set to just those items listed by one or more
 * specified sellers or those items not listed by the one or more specified
 * sellers.
 * <br> <B>Input property:</B> <code>SearchLocationFilter</code> - Limits the result set to just those items that meet location criteria:
 * listed in a specified eBay site, location where the seller has the item,
 * location from which the user is searching, and/or items listed with a
 * specified currency.
 * <br> <B>Input property:</B> <code>StoreSearchFilter</code> - Limits the result set to just those items that meet criteria related to
 * eBay Stores sellers and eBay Stores. Use this to retrieve items listed in
 * a particular seller's eBay Store or in all store sellers' eBay Stores.
 * This filter always causes item description text to be searched with the
 * string specified in the Query field. That is, StoreSearchFilter
 * forces the type of search that would have occurred if you had specified
 * SearchInDescription in the SearchFlags field.
 * <br> <B>Input property:</B> <code>SortOrder</code> - Specifies the order in which listings are returned in a result set.
 * Listings may be sorted by end time, start time, and in other ways listed
 * in the SearchSortOrderCodeType. Controls the way the listings are
 * organized in the response (not the details to return for each listing).
 * For most sites, the default sort order is by items ending soonest. Store
 * Inventory listings are usually returned after other listing types,
 * regardless of the sort order.
 * <br> <B>Input property:</B> <code>Pagination</code> - Controls the pagination of the result set. Child elements specify the
 * maximum number of item listings to return per call and which page of data
 * to return. Controls the way the listings are organized in the response
 * (not the details to return for each listing).
 * <br> <B>Input property:</B> <code>SearchRequest</code> - A query consisting of a set of attributes (Item Specifics). Use this kind
 * of query to search against the Item Specifics in listings (e.g., to search
 * for a particular shoe size). If the query includes multiple attributes,
 * the search engine will apply "AND" logic to the query and narrow the
 * results. Use GetProductFinder to determine the list of valid attributes
 * and how many are permitted for the specified characteristic set. Retrieves
 * items along with any buying guide details that are associated with the
 * specified product finder. Applicable in combination with the Query
 * argument. Cannot be used in combination with ProductID or
 * ExternalProductID.<br>
 * <br>
 * If you are searching for tickets, see TicketFinder instead.
 * <br> <B>Input property:</B> <code>ProductID</code> - An exclusive query to retrieve items that were listed with the specified
 * eBay catalog product. You must pass ProductID, Query, ExternalProductID,
 * or CategoryID in the request. If you use ProductID, do not use Query,
 * ExternalProductID, or CategoryID.
 * <br><br>
 * Some sites (such as eBay US, Germany, Austria, and Switzerland) are updating,
 * replacing, deleting, or merging some products (as a result of migrating from one
 * catalog data provider to another). If you specify one of these products, the call may
 * return a warning, or it may return an error if the product has been deleted.
 * <br> <B>Input property:</B> <code>ExternalProductID</code> - An exclusive query to only retrieve items that were listed with the
 * specified ISBN or UPC. Only applicable for items that were listed with
 * Pre-filled Item Information in media categories (Books, Music, DVDs and
 * Movies, and Video Games). You must pass ProductID, Query,
 * ExternalProductID, or CategoryID in the request. If you use
 * ExternalProductID, do not use Query, ProductID, or CategoryID.
 * <br> <B>Input property:</B> <code>Categories</code> - Retrieves statistical (histogram) information about categories that contain items
 * that match the query. Can also cause the result to include information
 * about buying guides that are associated with the matching categories.
 * Does not control the set of listings to return or the details to return
 * for each listing.
 * <br> <B>Input property:</B> <code>TotalOnly</code> - Retrieves the total quantity of matching items, without returning the item
 * data. See PaginationResult.TotalNumberOfEntries in the response. If
 * TotalOnly and Categories.CategoriesOnly are both specified in the request
 * and their values are inconsistent with each other, TotalOnly overrides
 * Categories.CategoriesOnly. That is, if TotalOnly is true and
 * Categories.CategoriesOnly is false, the results include matching
 * categories but no item data or buying guides. If TotalOnly is false and
 * Categories.CategoriesOnly is true, the results include matching
 * categories, item data, and buying guides. If TotalOnly is not specified,
 * it has no logical effect.
 * <br> <B>Input property:</B> <code>EndTimeFrom</code> - Limits the results to items ending within a time range. EndTimeFrom
 * specifies the beginning of the time range. Specify a time in the future.
 * If you specify a time in the past, the current time is used. If specified,
 * EndTimeTo must also be specified (with a value equal to or later than
 * EndTimeFrom). Specify the time in GMT. Cannot be used with the ModTimeFrom
 * filter.
 * <br> <B>Input property:</B> <code>EndTimeTo</code> - Limits the results to items ending within a time range. EndTimeTo specifies
 * the end of the time range. If specified, EndTimeFrom must also be specified
 * (with a value equal to or earlier than EndTimeTo). Specify the time in GMT.
 * Cannot be used with the ModTimeFrom filter.
 * <br> <B>Input property:</B> <code>ModTimeFrom</code> - Limits the results to active items whose status has changed
 * since the specified time. Specify a time in the past.
 * Time must be in GMT. Cannot be used with the EndTime filters.
 * <br> <B>Input property:</B> <code>IncludeGetItFastItems</code> - When passed with a value of true, limits the results to Get It Fast listings.
 * <br> <B>Input property:</B> <code>PaymentMethod</code> - Limits the results to items that accept a specific payment method or methods.
 * <br> <B>Input property:</B> <code>GranularityLevel</code> - Optional tag that currently accepts only one value for this call: Coarse.
 * Other values return an error. If you specify Coarse, the call
 * returns the fields shown in the
 * <a href="#GranularityLevel">GranularityLevel table</a>
 * plus any tags resulting from the detail level you specify.
 * Controls the fields to return for each listing (not the set of
 * listings that match the query).
 * <br> <B>Input property:</B> <code>ExpandSearch</code> - Expands search results when a small result set is returned. For example,
 * on the US site (site ID 0), if a search would normally result in fewer
 * than 10 items, then if you specify true for this tag, the search results
 * are expanded. Specifically, the search returns items (if there are
 * matches) in one or more of the following containers:
 * InternationalExpansionArray (for items available from international
 * sellers), FilterRemovedExpansionArray (items that would be returned if
 * filters such as PriceRangeFilter are removed), StoreExpansionArray (for
 * items listed in the Store Inventory Format), and
 * AllCategoriesExpansionArray (for items available if category filters are
 * removed). Maximum number of items returned in each expansion container is
 * 6 to 10.
 * <br> <B>Input property:</B> <code>Lot</code> - Limits the results to only those listings for which Item.LotSize is 2 or greater.
 * <br> <B>Input property:</B> <code>AdFormat</code> - Restricts listings to return only items that have the Ad Format feature.
 * If true, the values of ItemTypeFilter and
 * StoreSearchFilter are ignored (if they are specified). That is, "AND"
 * logic is not applied.
 * <br> <B>Input property:</B> <code>FreeShipping</code> - If true, only items with free shipping for the user's location are
 * returned. The user's location is determined from the site ID specified
 * in the request. If false, no filtering is done via this attribute. A
 * listing is not considered a free shipping listing if it requires
 * insurance or requires pick up or requires a shipping surcharge.
 * <br> <B>Input property:</B> <code>Quantity</code> - Limits the results to listings that offer a certain number of items
 * matching the query. If Lot is also specified, then Quantity is the number
 * of lots multiplied by the number of objects in each lot. Use
 * QuantityOperator to specify that you are seeking listings with quantities
 * greater than, equal to, or less than Quantity.
 * <br> <B>Input property:</B> <code>QuantityOperator</code> - Limits the results to listings with quantities greater than, equal to, or
 * less than Quantity. Controls the set of listings to return (not the
 * details to return for each listing).
 * <br> <B>Input property:</B> <code>SellerBusinessType</code> - Limits the results to those of a particular seller business type such as
 * commercial or private. Applies only to the following sites: UK, France, and Germany.
 * <br> <B>Input property:</B> <code>IncludeCondition</code> - If true, each item in the result set can also
 * include the item condition (e.g., New or Used) in the
 * ItemSpecific property of the response. An item only includes
 * the condition in the response if the seller filled in the
 * Item Condition in the Item Specifics section of the listing.
 * (That is, the condition is not returned if the seller
 * only put the word "New" in the listing's title.) <br>
 * <br>
 * Controls the details to return for each listing (not the set of
 * listings that match the query). <br>
 * <br>To control whether to retrieve only new or used items,
 * see ItemCondition (or SearchRequest).
 * <br> <B>Input property:</B> <code>IncludeFeedback</code> - If true, each item in the result set also includes information about the
 * seller's feedback. Controls the details to return for each listing (not
 * the set of listings that match the query).
 * <br>
 * For GetSearchResults, if set to true will also return the seller's User ID.
 * <br> <B>Input property:</B> <code>CharityID</code> - Restricts listings to return only items that support the specified
 * nonprofit charity organization. Retrieve CharityID values with
 * GetCharities.
 * <br> <B>Input property:</B> <code>LocalSearchPostalCode</code> - Include local items in returning results near this postal code. This
 * postal code is the basis for local search.
 * <br> <B>Input property:</B> <code>MaxRelatedSearchKeywords</code> - The maximum number of related keywords to be retrieved.
 * Use this field if you want the search results to include
 * recommended keywords (that is, keywords matching one or more of the
 * original keywords) in a RelatedSearchKeywordArray container.
 * A value of 0 (the default) means no related search information is processed.
 * <br> <B>Input property:</B> <code>AffiliateTrackingDetails</code> - Container for affiliate tags.
 * If you use affiliate tags, it is possible to get affiliate commissions
 * based on calls made by your application.
 * (See the <a href="https://www.ebaypartnernetwork.com/" target="_blank">eBay Partner Network</a>
 * for information about commissions.)
 * Affiliate tags enable the tracking of user activity.
 * You can use child tags of AffiliateTrackingDetails if you want
 * call output to include a string that includes
 * affiliate tracking information.
 * <br> <B>Input property:</B> <code>BidRange</code> - Limits the results to items with a minimum or maximum number
 * of bids. You also can specify a bid range by specifying
 * both a minimum and maximum number of bids in one call.
 * <br> <B>Input property:</B> <code>ItemCondition</code> - Limits the results to new or used items, plus items that have no
 * condition specified.<br>
 * <br>
 * Matches the new or used condition that the seller specified
 * in the Item Specifics section of the listing.
 * (That is, this won't specifically match on items where the seller
 * only put the word "New" in the listing's title.)<br>
 * <br>
 * Only applicable to sites and categories that support a
 * sitewide (global) item condition. For example, the US site
 * does not currently support this. See GetCategory2CS.
 * To search for the item condition on the US site,
 * use a product finder instead (see SearchRequest).
 * <br> <B>Input property:</B> <code>TicketFinder</code> - Searches for event ticket listings only. If specified, this cannot be empty.
 * For example, to search for all tickets (with no event, date, city, or quantity constraints),
 * specify EventType with a value of Any.
 * If specified, Query is optional. Query is useful when the user wants to search
 * for a particular event name (like "eric clapton") or a venue that might be
 * included in the listing title.
 * If TicketFinder and SearchRequest are both specified in the same request,
 * SearchRequest is ignored.
 * <br> <B>Input property:</B> <code>Group</code> - You can group Best Match search results by category by specifying BestMatchCategoryGroup
 * in the Order field. When you specify BestMatchCategoryGroup
 * in the Order field, you can also specify Group.MaxEntriesPerGroup and/or Group.MaxGroups.
 * When you specify BestMatchCategoryGroup
 * in the Order field, there will be fewer results returned because Best Matches
 * in lower-level (leaf) categories and higher-level categories are taken into account.
 * There is not a direct correlation between the number of items returned in a 
 * regular sort (or in a BestMatch sort) and the number of items that are returned 
 * when you specify BestMatchCategoryGroup in the Order field.
 * When you specify BestMatchCategoryGroup
 * in the Order field, not more than 2 pages of results are returned.
 * See also the GroupCategoryID element in ItemType.
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
 * <br> <B>Output property:</B> <code>ReturnedItems</code> - Contains the returned item listings, if any. The data for each listing is
 * returned in an ItemType object.
 * <br> <B>Output property:</B> <code>ReturnedItemsPerPage</code> - Indicates the maximum number of items that can be returned in a
 * SearchResultItemArray for any given call.
 * <br> <B>Output property:</B> <code>ReturnedPageNumber</code> - Indicates the page of data returned by the current call. For instance,
 * for the first set of items can be returned, this field has a value of
 * one.
 * <br> <B>Output property:</B> <code>HasMoreItems</code> - Indicates whether there are more items, not yet returned, that match the
 * search criteria. 
 * <br> <B>Output property:</B> <code>ResultPagination</code> - Indicates the results of the pagination, including the total number of
 * pages of data that can be returned and the total number of items that can
 * be returned.
 * <br> <B>Output property:</B> <code>ReturnedCategoryArray</code> - Shows the distribution of items across each category. 
 * Consists of statistical information about
 * categories that contain items that match
 * the query, if any. Returns empty if no matches are found or when the
 * Categories filter was not passed in the request. (For categories
 * associated with specific items, see items returned in each search
 * result.) 
 * You must use fields within the Categories input container
 * if you want the CategoryArray response container to be returned.
 * <br> <B>Output property:</B> <code>ReturnedBuyingGuideDetails</code> - Contains URLs and other information about relevant buying guides (if
 * any), as well as the site's buying guide hub. Unavailable in the Sandbox
 * environment. Buying guides are useful to buyers who do not have a
 * specific product in mind. For example, a digital camera buying guide
 * could help a buyer determine what kind of digital camera is right for
 * them. Only returned for product finder searches (using
 * SearchRequest.SearchAttributes) and for searches that retrieve matching
 * categories along with item data (using Categories.CategoriesOnly=false).
 * <br> <B>Output property:</B> <code>ReturnedStoreExpansionArray</code> - Contains items listed in the Store Inventory Format,
 * if the request specifies that ExpandSearch is true.
 * <br> <B>Output property:</B> <code>ReturnedInternationalExpansionArray</code> - Can be returned if the request specifies that ExpandSearch is true.
 * Provides additional search results when a small result set would have
 * been returned with the original search. For example, on the US site (site
 * ID 0), if a search would normally result in fewer than 10 items, and
 * ExpandSearch is true, the search results are expanded to include (if
 * matching the query) the InternationalExpansionArray container for items,
 * as well as other containers. The InternationalExpansionArray container
 * contains items available from international sellers.
 * <br> <B>Output property:</B> <code>ReturnedFilterRemovedExpansionArray</code> - Can be returned if the request specifies that ExpandSearch is true.
 * Provides additional search results when a small result set would have
 * been returned with the original search. For example, on the US site (site
 * ID 0), if a search would normally result in fewer than 10 items, and
 * ExpandSearch is true, the search results are expanded to include (if
 * matching the query) the FilterRemovedExpansionArray container for items,
 * as well as other containers. The FilterRemovedExpansionArray container
 * contains items that would be returned if filters such as PriceRangeFilter
 * are removed.
 * <br> <B>Output property:</B> <code>ReturnedAllCategoriesExpansionArray</code> - Can be returned if the request specifies that ExpandSearch is true.
 * Provides additional search results when a small result set would have
 * been returned with the original search. For example, on the US site (site
 * ID 0), if a search would normally result in fewer than 10 items, and
 * ExpandSearch is true, the search results are expanded to include (if
 * matching the query) the AllCategoriesExpansionArray container for items,
 * as well as other containers. The AllCategoriesExpansionArray container
 * contains items available if category filters are removed.
 * <br> <B>Output property:</B> <code>ReturnedSpellingSuggestion</code> - Suggestion for a different spelling of the search term or terms, along
 * with the number of matching items that would have been returned if the
 * suggestions had been used. The suggestions are given in Text tags and the
 * suggestion for the first word is given before the suggestion for
 * subsequent words. Suggestions are based on correctly-spelled terms in
 * items, so suggestions vary over time and depend on whether a word or word
 * combination is in one or more items.
 * <br> <B>Output property:</B> <code>ReturnedRelatedSearchKeywordArray</code> - Container for keywords related to the original keywords in the request.
 * Can be returned if the request specified more than zero in the MaxRelatedSearchKeywords field.
 * <br> <B>Output property:</B> <code>ReturnedDuplicateItems</code> - Indicates whether there are duplicated items not returned by this 
 * response when HideDuplicateItems is true in the request. 
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class GetSearchResultsCall extends com.ebay.sdk.ApiCall
{
  
  private Boolean motorsGermanySearchable = null;
  private String query = null;
  private String categoryID = null;
  private SearchFlagsCodeType[] searchFlags = null;
  private PriceRangeFilterType priceRangeFilter = null;
  private ProximitySearchType proximitySearch = null;
  private ItemTypeFilterCodeType itemTypeFilter = null;
  private SearchTypeCodeType searchType = null;
  private UserIdFilterType userIdFilter = null;
  private SearchLocationFilterType searchLocationFilter = null;
  private SearchStoreFilterType storeSearchFilter = null;
  private SearchSortOrderCodeType sortOrder = null;
  private PaginationType pagination = null;
  private SearchRequestType searchRequest = null;
  private String productID = null;
  private ExternalProductIDType externalProductID = null;
  private RequestCategoriesType categories = null;
  private Boolean totalOnly = null;
  private Calendar endTimeFrom = null;
  private Calendar endTimeTo = null;
  private Calendar modTimeFrom = null;
  private Boolean includeGetItFastItems = null;
  private PaymentMethodSearchCodeType paymentMethod = null;
  private GranularityLevelCodeType granularityLevel = null;
  private Boolean expandSearch = null;
  private Boolean lot = null;
  private Boolean adFormat = null;
  private Boolean freeShipping = null;
  private Integer quantity = null;
  private QuantityOperatorCodeType quantityOperator = null;
  private SellerBusinessCodeType sellerBusinessType = null;
  private Boolean includeCondition = null;
  private Boolean includeFeedback = null;
  private Integer charityID = null;
  private String localSearchPostalCode = null;
  private Integer maxRelatedSearchKeywords = null;
  private AffiliateTrackingDetailsType affiliateTrackingDetails = null;
  private BidRangeType bidRange = null;
  private ItemConditionCodeType itemCondition = null;
  private TicketDetailsType ticketFinder = null;
  private GroupType group = null;
  private Boolean hideDuplicateItems = null;
  private GetSearchResultsRequestType overrideGetSearchResultsRequest=null;
  private SearchResultItemType[] returnedItems=null;
  private Integer returnedItemsPerPage=null;
  private Integer returnedPageNumber=null;
  private boolean hasMoreItems=false;
  private PaginationResultType resultPagination=null;
  private CategoryArrayType returnedCategoryArray=null;
  private BuyingGuideDetailsType returnedBuyingGuideDetails=null;
  private ExpansionArrayType returnedStoreExpansionArray=null;
  private ExpansionArrayType returnedInternationalExpansionArray=null;
  private ExpansionArrayType returnedFilterRemovedExpansionArray=null;
  private ExpansionArrayType returnedAllCategoriesExpansionArray=null;
  private SpellingSuggestionType returnedSpellingSuggestion=null;
  private RelatedSearchKeywordArrayType returnedRelatedSearchKeywordArray=null;
  private Boolean returnedDuplicateItems=null;


  /**
   * Constructor.
   */
  public GetSearchResultsCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public GetSearchResultsCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Retrieves item listings based on keywords you specify. The keywords can
   * include wildcards.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The SearchResultItemType[] object.
   */
  public SearchResultItemType[] getSearchResults()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GetSearchResultsRequestType req;
    if (this.overrideGetSearchResultsRequest != null)
    {
      req = this.overrideGetSearchResultsRequest;
    } else {
      req = new GetSearchResultsRequestType();
      req.setDetailLevel(this.getDetailLevel());
      if (this.motorsGermanySearchable != null)
        req.setMotorsGermanySearchable(this.motorsGermanySearchable);
      if (this.query != null)
        req.setQuery(this.query);
      if (this.categoryID != null)
        req.setCategoryID(this.categoryID);
      if (this.searchFlags != null)
        req.setSearchFlags(this.searchFlags);
      if (this.priceRangeFilter != null)
        req.setPriceRangeFilter(this.priceRangeFilter);
      if (this.proximitySearch != null)
        req.setProximitySearch(this.proximitySearch);
      if (this.itemTypeFilter != null)
        req.setItemTypeFilter(this.itemTypeFilter);
      if (this.searchType != null)
        req.setSearchType(this.searchType);
      if (this.userIdFilter != null)
        req.setUserIdFilter(this.userIdFilter);
      if (this.searchLocationFilter != null)
        req.setSearchLocationFilter(this.searchLocationFilter);
      if (this.storeSearchFilter != null)
        req.setStoreSearchFilter(this.storeSearchFilter);
      if (this.sortOrder != null)
        req.setOrder(this.sortOrder);
      if (this.pagination != null)
        req.setPagination(this.pagination);
      if (this.searchRequest != null)
        req.setSearchRequest(this.searchRequest);
      if (this.productID != null)
        req.setProductID(this.productID);
      if (this.externalProductID != null)
        req.setExternalProductID(this.externalProductID);
      if (this.categories != null)
        req.setCategories(this.categories);
      if (this.totalOnly != null)
        req.setTotalOnly(this.totalOnly);
      if (this.endTimeFrom != null)
        req.setEndTimeFrom(this.endTimeFrom);
      if (this.endTimeTo != null)
        req.setEndTimeTo(this.endTimeTo);
      if (this.modTimeFrom != null)
        req.setModTimeFrom(this.modTimeFrom);
      if (this.includeGetItFastItems != null)
        req.setIncludeGetItFastItems(this.includeGetItFastItems);
      if (this.paymentMethod != null)
        req.setPaymentMethod(this.paymentMethod);
      if (this.granularityLevel != null)
        req.setGranularityLevel(this.granularityLevel);
      if (this.expandSearch != null)
        req.setExpandSearch(this.expandSearch);
      if (this.lot != null)
        req.setLot(this.lot);
      if (this.adFormat != null)
        req.setAdFormat(this.adFormat);
      if (this.freeShipping != null)
        req.setFreeShipping(this.freeShipping);
      if (this.quantity != null)
        req.setQuantity(this.quantity);
      if (this.quantityOperator != null)
        req.setQuantityOperator(this.quantityOperator);
      if (this.sellerBusinessType != null)
        req.setSellerBusinessType(this.sellerBusinessType);
      if (this.includeCondition != null)
        req.setIncludeCondition(this.includeCondition);
      if (this.includeFeedback != null)
        req.setIncludeFeedback(this.includeFeedback);
      if (this.charityID != null)
        req.setCharityID(this.charityID);
      if (this.localSearchPostalCode != null)
        req.setLocalSearchPostalCode(this.localSearchPostalCode);
      if (this.maxRelatedSearchKeywords != null)
        req.setMaxRelatedSearchKeywords(this.maxRelatedSearchKeywords);
      if (this.affiliateTrackingDetails != null)
        req.setAffiliateTrackingDetails(this.affiliateTrackingDetails);
      if (this.bidRange != null)
        req.setBidRange(this.bidRange);
      if (this.itemCondition != null)
        req.setItemCondition(this.itemCondition);
      if (this.ticketFinder != null)
        req.setTicketFinder(this.ticketFinder);
      if (this.group != null)
        req.setGroup(this.group);
      if (this.hideDuplicateItems != null)
        req.setHideDuplicateItems(this.hideDuplicateItems);
  
    }

    GetSearchResultsResponseType resp = (GetSearchResultsResponseType) execute(req);

    this.returnedItems = (resp.getSearchResultItemArray() == null? null: resp.getSearchResultItemArray().getSearchResultItem());
    this.returnedItemsPerPage = resp.getItemsPerPage();
    this.returnedPageNumber = resp.getPageNumber();
    this.hasMoreItems = resp.isHasMoreItems();
    this.resultPagination = resp.getPaginationResult();
    this.returnedCategoryArray = resp.getCategoryArray();
    this.returnedBuyingGuideDetails = resp.getBuyingGuideDetails();
    this.returnedStoreExpansionArray = resp.getStoreExpansionArray();
    this.returnedInternationalExpansionArray = resp.getInternationalExpansionArray();
    this.returnedFilterRemovedExpansionArray = resp.getFilterRemovedExpansionArray();
    this.returnedAllCategoriesExpansionArray = resp.getAllCategoriesExpansionArray();
    this.returnedSpellingSuggestion = resp.getSpellingSuggestion();
    this.returnedRelatedSearchKeywordArray = resp.getRelatedSearchKeywordArray();
    this.returnedDuplicateItems = resp.isDuplicateItems();
    return this.getReturnedItems();
  }

  /**
   * Gets the GetSearchResultsRequestType.overrideGetSearchResultsRequest.
   * @return GetSearchResultsRequestType
   */
  public GetSearchResultsRequestType getOverrideGetSearchResultsRequest()
  {
    return this.overrideGetSearchResultsRequest;
  }


  /**
   * Sets the GetSearchResultsRequestType.overrideGetSearchResultsRequest.
   * @param overrideGetSearchResultsRequest GetSearchResultsRequestType
   */
  public void setOverrideGetSearchResultsRequest(GetSearchResultsRequestType overrideGetSearchResultsRequest)
  {
    this.overrideGetSearchResultsRequest = overrideGetSearchResultsRequest;
  }

  /**
   * Gets the GetSearchResultsRequestType.adFormat.
   * @return Boolean
   */
  public Boolean getAdFormat()
  {
    return this.adFormat;
  }

  /**
   * Sets the GetSearchResultsRequestType.adFormat.
   * @param adFormat Boolean
   */
  public void setAdFormat(Boolean adFormat)
  {
    this.adFormat = adFormat;
  }

  /**
   * Gets the GetSearchResultsRequestType.affiliateTrackingDetails.
   * @return AffiliateTrackingDetailsType
   */
  public AffiliateTrackingDetailsType getAffiliateTrackingDetails()
  {
    return this.affiliateTrackingDetails;
  }

  /**
   * Sets the GetSearchResultsRequestType.affiliateTrackingDetails.
   * @param affiliateTrackingDetails AffiliateTrackingDetailsType
   */
  public void setAffiliateTrackingDetails(AffiliateTrackingDetailsType affiliateTrackingDetails)
  {
    this.affiliateTrackingDetails = affiliateTrackingDetails;
  }

  /**
   * Gets the GetSearchResultsRequestType.bidRange.
   * @return BidRangeType
   */
  public BidRangeType getBidRange()
  {
    return this.bidRange;
  }

  /**
   * Sets the GetSearchResultsRequestType.bidRange.
   * @param bidRange BidRangeType
   */
  public void setBidRange(BidRangeType bidRange)
  {
    this.bidRange = bidRange;
  }

  /**
   * Gets the GetSearchResultsRequestType.categories.
   * @return RequestCategoriesType
   */
  public RequestCategoriesType getCategories()
  {
    return this.categories;
  }

  /**
   * Sets the GetSearchResultsRequestType.categories.
   * @param categories RequestCategoriesType
   */
  public void setCategories(RequestCategoriesType categories)
  {
    this.categories = categories;
  }

  /**
   * Gets the GetSearchResultsRequestType.categoryID.
   * @return String
   */
  public String getCategoryID()
  {
    return this.categoryID;
  }

  /**
   * Sets the GetSearchResultsRequestType.categoryID.
   * @param categoryID String
   */
  public void setCategoryID(String categoryID)
  {
    this.categoryID = categoryID;
  }

  /**
   * Gets the GetSearchResultsRequestType.charityID.
   * @return Integer
   */
  public Integer getCharityID()
  {
    return this.charityID;
  }

  /**
   * Sets the GetSearchResultsRequestType.charityID.
   * @param charityID Integer
   */
  public void setCharityID(Integer charityID)
  {
    this.charityID = charityID;
  }

  /**
   * Gets the GetSearchResultsRequestType.endTimeFrom.
   * @return Calendar
   */
  public Calendar getEndTimeFrom()
  {
    return this.endTimeFrom;
  }

  /**
   * Sets the GetSearchResultsRequestType.endTimeFrom.
   * @param endTimeFrom Calendar
   */
  public void setEndTimeFrom(Calendar endTimeFrom)
  {
    this.endTimeFrom = endTimeFrom;
  }

  /**
   * Gets the GetSearchResultsRequestType.endTimeTo.
   * @return Calendar
   */
  public Calendar getEndTimeTo()
  {
    return this.endTimeTo;
  }

  /**
   * Sets the GetSearchResultsRequestType.endTimeTo.
   * @param endTimeTo Calendar
   */
  public void setEndTimeTo(Calendar endTimeTo)
  {
    this.endTimeTo = endTimeTo;
  }

  /**
   * Gets the GetSearchResultsRequestType.expandSearch.
   * @return Boolean
   */
  public Boolean getExpandSearch()
  {
    return this.expandSearch;
  }

  /**
   * Sets the GetSearchResultsRequestType.expandSearch.
   * @param expandSearch Boolean
   */
  public void setExpandSearch(Boolean expandSearch)
  {
    this.expandSearch = expandSearch;
  }

  /**
   * Gets the GetSearchResultsRequestType.externalProductID.
   * @return ExternalProductIDType
   */
  public ExternalProductIDType getExternalProductID()
  {
    return this.externalProductID;
  }

  /**
   * Sets the GetSearchResultsRequestType.externalProductID.
   * @param externalProductID ExternalProductIDType
   */
  public void setExternalProductID(ExternalProductIDType externalProductID)
  {
    this.externalProductID = externalProductID;
  }

  /**
   * Gets the GetSearchResultsRequestType.freeShipping.
   * @return Boolean
   */
  public Boolean getFreeShipping()
  {
    return this.freeShipping;
  }

  /**
   * Sets the GetSearchResultsRequestType.freeShipping.
   * @param freeShipping Boolean
   */
  public void setFreeShipping(Boolean freeShipping)
  {
    this.freeShipping = freeShipping;
  }

  /**
   * Gets the GetSearchResultsRequestType.granularityLevel.
   * @return GranularityLevelCodeType
   */
  public GranularityLevelCodeType getGranularityLevel()
  {
    return this.granularityLevel;
  }

  /**
   * Sets the GetSearchResultsRequestType.granularityLevel.
   * @param granularityLevel GranularityLevelCodeType
   */
  public void setGranularityLevel(GranularityLevelCodeType granularityLevel)
  {
    this.granularityLevel = granularityLevel;
  }

  /**
   * Gets the GetSearchResultsRequestType.group.
   * @return GroupType
   */
  public GroupType getGroup()
  {
    return this.group;
  }

  /**
   * Sets the GetSearchResultsRequestType.group.
   * @param group GroupType
   */
  public void setGroup(GroupType group)
  {
    this.group = group;
  }

  /**
   * Gets the GetSearchResultsRequestType.hideDuplicateItems.
   * @return Boolean
   */
  public Boolean getHideDuplicateItems()
  {
    return this.hideDuplicateItems;
  }

  /**
   * Sets the GetSearchResultsRequestType.hideDuplicateItems.
   * @param hideDuplicateItems Boolean
   */
  public void setHideDuplicateItems(Boolean hideDuplicateItems)
  {
    this.hideDuplicateItems = hideDuplicateItems;
  }

  /**
   * Gets the GetSearchResultsRequestType.includeCondition.
   * @return Boolean
   */
  public Boolean getIncludeCondition()
  {
    return this.includeCondition;
  }

  /**
   * Sets the GetSearchResultsRequestType.includeCondition.
   * @param includeCondition Boolean
   */
  public void setIncludeCondition(Boolean includeCondition)
  {
    this.includeCondition = includeCondition;
  }

  /**
   * Gets the GetSearchResultsRequestType.includeFeedback.
   * @return Boolean
   */
  public Boolean getIncludeFeedback()
  {
    return this.includeFeedback;
  }

  /**
   * Sets the GetSearchResultsRequestType.includeFeedback.
   * @param includeFeedback Boolean
   */
  public void setIncludeFeedback(Boolean includeFeedback)
  {
    this.includeFeedback = includeFeedback;
  }

  /**
   * Gets the GetSearchResultsRequestType.includeGetItFastItems.
   * @return Boolean
   */
  public Boolean getIncludeGetItFastItems()
  {
    return this.includeGetItFastItems;
  }

  /**
   * Sets the GetSearchResultsRequestType.includeGetItFastItems.
   * @param includeGetItFastItems Boolean
   */
  public void setIncludeGetItFastItems(Boolean includeGetItFastItems)
  {
    this.includeGetItFastItems = includeGetItFastItems;
  }

  /**
   * Gets the GetSearchResultsRequestType.itemCondition.
   * @return ItemConditionCodeType
   */
  public ItemConditionCodeType getItemCondition()
  {
    return this.itemCondition;
  }

  /**
   * Sets the GetSearchResultsRequestType.itemCondition.
   * @param itemCondition ItemConditionCodeType
   */
  public void setItemCondition(ItemConditionCodeType itemCondition)
  {
    this.itemCondition = itemCondition;
  }

  /**
   * Gets the GetSearchResultsRequestType.itemTypeFilter.
   * @return ItemTypeFilterCodeType
   */
  public ItemTypeFilterCodeType getItemTypeFilter()
  {
    return this.itemTypeFilter;
  }

  /**
   * Sets the GetSearchResultsRequestType.itemTypeFilter.
   * @param itemTypeFilter ItemTypeFilterCodeType
   */
  public void setItemTypeFilter(ItemTypeFilterCodeType itemTypeFilter)
  {
    this.itemTypeFilter = itemTypeFilter;
  }

  /**
   * Gets the GetSearchResultsRequestType.localSearchPostalCode.
   * @return String
   */
  public String getLocalSearchPostalCode()
  {
    return this.localSearchPostalCode;
  }

  /**
   * Sets the GetSearchResultsRequestType.localSearchPostalCode.
   * @param localSearchPostalCode String
   */
  public void setLocalSearchPostalCode(String localSearchPostalCode)
  {
    this.localSearchPostalCode = localSearchPostalCode;
  }

  /**
   * Gets the GetSearchResultsRequestType.lot.
   * @return Boolean
   */
  public Boolean getLot()
  {
    return this.lot;
  }

  /**
   * Sets the GetSearchResultsRequestType.lot.
   * @param lot Boolean
   */
  public void setLot(Boolean lot)
  {
    this.lot = lot;
  }

  /**
   * Gets the GetSearchResultsRequestType.maxRelatedSearchKeywords.
   * @return Integer
   */
  public Integer getMaxRelatedSearchKeywords()
  {
    return this.maxRelatedSearchKeywords;
  }

  /**
   * Sets the GetSearchResultsRequestType.maxRelatedSearchKeywords.
   * @param maxRelatedSearchKeywords Integer
   */
  public void setMaxRelatedSearchKeywords(Integer maxRelatedSearchKeywords)
  {
    this.maxRelatedSearchKeywords = maxRelatedSearchKeywords;
  }

  /**
   * Gets the GetSearchResultsRequestType.modTimeFrom.
   * @return Calendar
   */
  public Calendar getModTimeFrom()
  {
    return this.modTimeFrom;
  }

  /**
   * Sets the GetSearchResultsRequestType.modTimeFrom.
   * @param modTimeFrom Calendar
   */
  public void setModTimeFrom(Calendar modTimeFrom)
  {
    this.modTimeFrom = modTimeFrom;
  }

  /**
   * Gets the GetSearchResultsRequestType.motorsGermanySearchable.
   * @return Boolean
   */
  public Boolean getMotorsGermanySearchable()
  {
    return this.motorsGermanySearchable;
  }

  /**
   * Sets the GetSearchResultsRequestType.motorsGermanySearchable.
   * @param motorsGermanySearchable Boolean
   */
  public void setMotorsGermanySearchable(Boolean motorsGermanySearchable)
  {
    this.motorsGermanySearchable = motorsGermanySearchable;
  }

  /**
   * Gets the GetSearchResultsRequestType.pagination.
   * @return PaginationType
   */
  public PaginationType getPagination()
  {
    return this.pagination;
  }

  /**
   * Sets the GetSearchResultsRequestType.pagination.
   * @param pagination PaginationType
   */
  public void setPagination(PaginationType pagination)
  {
    this.pagination = pagination;
  }

  /**
   * Gets the GetSearchResultsRequestType.paymentMethod.
   * @return PaymentMethodSearchCodeType
   */
  public PaymentMethodSearchCodeType getPaymentMethod()
  {
    return this.paymentMethod;
  }

  /**
   * Sets the GetSearchResultsRequestType.paymentMethod.
   * @param paymentMethod PaymentMethodSearchCodeType
   */
  public void setPaymentMethod(PaymentMethodSearchCodeType paymentMethod)
  {
    this.paymentMethod = paymentMethod;
  }

  /**
   * Gets the GetSearchResultsRequestType.priceRangeFilter.
   * @return PriceRangeFilterType
   */
  public PriceRangeFilterType getPriceRangeFilter()
  {
    return this.priceRangeFilter;
  }

  /**
   * Sets the GetSearchResultsRequestType.priceRangeFilter.
   * @param priceRangeFilter PriceRangeFilterType
   */
  public void setPriceRangeFilter(PriceRangeFilterType priceRangeFilter)
  {
    this.priceRangeFilter = priceRangeFilter;
  }

  /**
   * Gets the GetSearchResultsRequestType.productID.
   * @return String
   */
  public String getProductID()
  {
    return this.productID;
  }

  /**
   * Sets the GetSearchResultsRequestType.productID.
   * @param productID String
   */
  public void setProductID(String productID)
  {
    this.productID = productID;
  }

  /**
   * Gets the GetSearchResultsRequestType.proximitySearch.
   * @return ProximitySearchType
   */
  public ProximitySearchType getProximitySearch()
  {
    return this.proximitySearch;
  }

  /**
   * Sets the GetSearchResultsRequestType.proximitySearch.
   * @param proximitySearch ProximitySearchType
   */
  public void setProximitySearch(ProximitySearchType proximitySearch)
  {
    this.proximitySearch = proximitySearch;
  }

  /**
   * Gets the GetSearchResultsRequestType.quantity.
   * @return Integer
   */
  public Integer getQuantity()
  {
    return this.quantity;
  }

  /**
   * Sets the GetSearchResultsRequestType.quantity.
   * @param quantity Integer
   */
  public void setQuantity(Integer quantity)
  {
    this.quantity = quantity;
  }

  /**
   * Gets the GetSearchResultsRequestType.quantityOperator.
   * @return QuantityOperatorCodeType
   */
  public QuantityOperatorCodeType getQuantityOperator()
  {
    return this.quantityOperator;
  }

  /**
   * Sets the GetSearchResultsRequestType.quantityOperator.
   * @param quantityOperator QuantityOperatorCodeType
   */
  public void setQuantityOperator(QuantityOperatorCodeType quantityOperator)
  {
    this.quantityOperator = quantityOperator;
  }

  /**
   * Gets the GetSearchResultsRequestType.query.
   * @return String
   */
  public String getQuery()
  {
    return this.query;
  }

  /**
   * Sets the GetSearchResultsRequestType.query.
   * @param query String
   */
  public void setQuery(String query)
  {
    this.query = query;
  }

  /**
   * Gets the GetSearchResultsRequestType.searchFlags.
   * @return SearchFlagsCodeType[]
   */
  public SearchFlagsCodeType[] getSearchFlags()
  {
    return this.searchFlags;
  }

  /**
   * Sets the GetSearchResultsRequestType.searchFlags.
   * @param searchFlags SearchFlagsCodeType[]
   */
  public void setSearchFlags(SearchFlagsCodeType[] searchFlags)
  {
    this.searchFlags = searchFlags;
  }

  /**
   * Gets the GetSearchResultsRequestType.searchLocationFilter.
   * @return SearchLocationFilterType
   */
  public SearchLocationFilterType getSearchLocationFilter()
  {
    return this.searchLocationFilter;
  }

  /**
   * Sets the GetSearchResultsRequestType.searchLocationFilter.
   * @param searchLocationFilter SearchLocationFilterType
   */
  public void setSearchLocationFilter(SearchLocationFilterType searchLocationFilter)
  {
    this.searchLocationFilter = searchLocationFilter;
  }

  /**
   * Gets the GetSearchResultsRequestType.searchRequest.
   * @return SearchRequestType
   */
  public SearchRequestType getSearchRequest()
  {
    return this.searchRequest;
  }

  /**
   * Sets the GetSearchResultsRequestType.searchRequest.
   * @param searchRequest SearchRequestType
   */
  public void setSearchRequest(SearchRequestType searchRequest)
  {
    this.searchRequest = searchRequest;
  }

  /**
   * Gets the GetSearchResultsRequestType.searchType.
   * @return SearchTypeCodeType
   */
  public SearchTypeCodeType getSearchType()
  {
    return this.searchType;
  }

  /**
   * Sets the GetSearchResultsRequestType.searchType.
   * @param searchType SearchTypeCodeType
   */
  public void setSearchType(SearchTypeCodeType searchType)
  {
    this.searchType = searchType;
  }

  /**
   * Gets the GetSearchResultsRequestType.sellerBusinessType.
   * @return SellerBusinessCodeType
   */
  public SellerBusinessCodeType getSellerBusinessType()
  {
    return this.sellerBusinessType;
  }

  /**
   * Sets the GetSearchResultsRequestType.sellerBusinessType.
   * @param sellerBusinessType SellerBusinessCodeType
   */
  public void setSellerBusinessType(SellerBusinessCodeType sellerBusinessType)
  {
    this.sellerBusinessType = sellerBusinessType;
  }

  /**
   * Gets the GetSearchResultsRequestType.sortOrder.
   * @return SearchSortOrderCodeType
   */
  public SearchSortOrderCodeType getSortOrder()
  {
    return this.sortOrder;
  }

  /**
   * Sets the GetSearchResultsRequestType.sortOrder.
   * @param sortOrder SearchSortOrderCodeType
   */
  public void setSortOrder(SearchSortOrderCodeType sortOrder)
  {
    this.sortOrder = sortOrder;
  }

  /**
   * Gets the GetSearchResultsRequestType.storeSearchFilter.
   * @return SearchStoreFilterType
   */
  public SearchStoreFilterType getStoreSearchFilter()
  {
    return this.storeSearchFilter;
  }

  /**
   * Sets the GetSearchResultsRequestType.storeSearchFilter.
   * @param storeSearchFilter SearchStoreFilterType
   */
  public void setStoreSearchFilter(SearchStoreFilterType storeSearchFilter)
  {
    this.storeSearchFilter = storeSearchFilter;
  }

  /**
   * Gets the GetSearchResultsRequestType.ticketFinder.
   * @return TicketDetailsType
   */
  public TicketDetailsType getTicketFinder()
  {
    return this.ticketFinder;
  }

  /**
   * Sets the GetSearchResultsRequestType.ticketFinder.
   * @param ticketFinder TicketDetailsType
   */
  public void setTicketFinder(TicketDetailsType ticketFinder)
  {
    this.ticketFinder = ticketFinder;
  }

  /**
   * Gets the GetSearchResultsRequestType.totalOnly.
   * @return Boolean
   */
  public Boolean getTotalOnly()
  {
    return this.totalOnly;
  }

  /**
   * Sets the GetSearchResultsRequestType.totalOnly.
   * @param totalOnly Boolean
   */
  public void setTotalOnly(Boolean totalOnly)
  {
    this.totalOnly = totalOnly;
  }

  /**
   * Gets the GetSearchResultsRequestType.userIdFilter.
   * @return UserIdFilterType
   */
  public UserIdFilterType getUserIdFilter()
  {
    return this.userIdFilter;
  }

  /**
   * Sets the GetSearchResultsRequestType.userIdFilter.
   * @param userIdFilter UserIdFilterType
   */
  public void setUserIdFilter(UserIdFilterType userIdFilter)
  {
    this.userIdFilter = userIdFilter;
  }
/**
	 * After executing the API, indicates whether there are more items matching
	 * the search criteria than were returned by the current call to GetSearchResults.
	 * @return boolean
	 */
	public boolean isHasMoreItems() {
		return hasMoreItems;
	}

  /**
   * Valid after executing the API.
   * Gets the returned GetSearchResultsResponseType.hasMoreItems.
   * 
   * @return boolean
   */
  public boolean getHasMoreItems()
  {
    return this.hasMoreItems;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSearchResultsResponseType.resultPagination.
   * 
   * @return PaginationResultType
   */
  public PaginationResultType getResultPagination()
  {
    return this.resultPagination;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSearchResultsResponseType.returnedAllCategoriesExpansionArray.
   * 
   * @return ExpansionArrayType
   */
  public ExpansionArrayType getReturnedAllCategoriesExpansionArray()
  {
    return this.returnedAllCategoriesExpansionArray;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSearchResultsResponseType.returnedBuyingGuideDetails.
   * 
   * @return BuyingGuideDetailsType
   */
  public BuyingGuideDetailsType getReturnedBuyingGuideDetails()
  {
    return this.returnedBuyingGuideDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSearchResultsResponseType.returnedCategoryArray.
   * 
   * @return CategoryArrayType
   */
  public CategoryArrayType getReturnedCategoryArray()
  {
    return this.returnedCategoryArray;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSearchResultsResponseType.returnedDuplicateItems.
   * 
   * @return Boolean
   */
  public Boolean getReturnedDuplicateItems()
  {
    return this.returnedDuplicateItems;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSearchResultsResponseType.returnedFilterRemovedExpansionArray.
   * 
   * @return ExpansionArrayType
   */
  public ExpansionArrayType getReturnedFilterRemovedExpansionArray()
  {
    return this.returnedFilterRemovedExpansionArray;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSearchResultsResponseType.returnedInternationalExpansionArray.
   * 
   * @return ExpansionArrayType
   */
  public ExpansionArrayType getReturnedInternationalExpansionArray()
  {
    return this.returnedInternationalExpansionArray;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSearchResultsResponseType.returnedItems.
   * 
   * @return SearchResultItemType[]
   */
  public SearchResultItemType[] getReturnedItems()
  {
    return this.returnedItems;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSearchResultsResponseType.returnedItemsPerPage.
   * 
   * @return Integer
   */
  public Integer getReturnedItemsPerPage()
  {
    return this.returnedItemsPerPage;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSearchResultsResponseType.returnedPageNumber.
   * 
   * @return Integer
   */
  public Integer getReturnedPageNumber()
  {
    return this.returnedPageNumber;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSearchResultsResponseType.returnedRelatedSearchKeywordArray.
   * 
   * @return RelatedSearchKeywordArrayType
   */
  public RelatedSearchKeywordArrayType getReturnedRelatedSearchKeywordArray()
  {
    return this.returnedRelatedSearchKeywordArray;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSearchResultsResponseType.returnedSpellingSuggestion.
   * 
   * @return SpellingSuggestionType
   */
  public SpellingSuggestionType getReturnedSpellingSuggestion()
  {
    return this.returnedSpellingSuggestion;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSearchResultsResponseType.returnedStoreExpansionArray.
   * 
   * @return ExpansionArrayType
   */
  public ExpansionArrayType getReturnedStoreExpansionArray()
  {
    return this.returnedStoreExpansionArray;
  }

}

