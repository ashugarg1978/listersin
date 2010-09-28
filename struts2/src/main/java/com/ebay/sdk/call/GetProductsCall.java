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

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the GetProducts call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>ProductSearch</code> - Contains the fields that form the search query. You can query
 * against keywords, an eBay product reference ID (not to be confused
 * with an eBay product ID), or external product ID (like an ISBN).
 * <br> <B>Input property:</B> <code>ProductSort</code> - Sorts the list of products returned. This is mostly only useful
 * with QueryKeywords. (When you use ExternalProductID or
 * ProductReferenceID, eBay usually only returns one product.)
 * <br> <B>Input property:</B> <code>IncludeItemArray</code> - If true, the response includes items (if any) that match the
 * product specified in ExternalProductID or ProductReferenceID.
 * Not applicable with QueryKeywords.
 * <br> <B>Input property:</B> <code>IncludeReviewDetails</code> - If true, the response includes up to 20 reviews (if any)
 * for the product specified in ExternalProductID or
 * ProductReferenceID.
 * The reviews are sorted by most helpful (most votes) first.
 * When you include review details, please note that
 * response times may be longer than 60 seconds.
 * Not applicable with QueryKeywords.
 * <br> <B>Input property:</B> <code>IncludeBuyingGuideDetails</code> - If true, the response includes up to 5 buying guides (if any)
 * for the product specified in ExternalProductID or
 * ProductReferenceID.
 * Not applicable with QueryKeywords.
 * <br> <B>Input property:</B> <code>IncludeHistogram</code> - If true, the response includes a histogram that lists the
 * number of matching products found and the domains in which
 * they were found. (A domain is like a high-level category.)
 * Including the histogram can affect the call's performance.
 * You may see significantly slower response times when many
 * matching products are found.
 * <br> <B>Input property:</B> <code>AffiliateTrackingDetails</code> - See the
 * <a href="https://www.ebaypartnernetwork.com/" target="_blank">eBay Partner Network</a>.
 * eBay uses the values in AffiliateTrackingDetails to build a View Item URL
 * string, in order to include that string in the response.
 * When a user clicks through the URL to eBay,
 * you may get a commission (see the URL above).
 * Only applicable when IncludeItemArray is specified
 * (because the View Item URL is only returned in item information,
 * not in product information).
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
 * For GetProducts, this filter only works when IncludeItemArray is set to
 * true.
 * <br> <B>Output property:</B> <code>ReturnedCharacteristicsSetProductHistogram</code> - A histogram that lists the number of matching products found
 * and the domains in which they were found.
 * A <i>domain</i> is essentially
 * a set of categories that share certain common features
 * (as determined by eBay). Each domain has its own name and ID.
 * <br> <B>Output property:</B> <code>ReturnedPageNumber</code> - The number of the page of data returned. If many products
 * are found and multiple pages of results are available, use
 * this in combination with ApproximatePages and HasMore to decide
 * which page to retrieve next. As GetProducts only returns
 * up to 2000 products, the maximum possible value is theoretically
 * 2000 (if you were to set EntriesPerPage to 1 in the request).
 * <br> <B>Output property:</B> <code>ReturnedApproximatePages</code> - The total number of pages that can be returned, given the same query and
 * filters in the request. As GetProducts only returns up to 2000 products,
 * the maximum possible value is theoretically 2000 (if you were to set
 * EntriesPerPage to 1 in the request).
 * <br> <B>Output property:</B> <code>ReturnedHasMore</code> - If true, more pages of results are available.
 * That is, PageNumber is less than ApproximatePages.
 * <br> <B>Output property:</B> <code>ReturnedTotalProducts</code> - The total number of matching products found.
 * (If more than 2000 products are found, the call fails
 * with an error.)
 * <br> <B>Output property:</B> <code>ReturnedProduct</code> - An eBay catalog product. This contains stock information about a
 * particular DVD, camera, set of golf clubs, or other product.
 * When you use QueryKeywords in the request, GetProducts returns a
 * maximum of 20 products per page.
 * When you use ExternalProductID or ProductReferenceID in the
 * request, GetProducts usually only returns 1 product.
 * (If more than one product matches the same ExternalProductID,
 * GetProducts will return all of those products. As of the time
 * of this writing, we expect this to be a rare case.)
 * <br> <B>Output property:</B> <code>ReturnedItemArray</code> - A list of items (if any) that match the product identified in
 * ExternalProductID or ProductReferenceID in the request.
 * Only returned when you set IncludeItemArray to true.
 * The items are sorted by end time (ending soonest), and this order
 * is not configurable in the request.
 * Up to 200 items can be returned. All items are returned on
 * the first page (page 1). Not returned when you use
 * QueryKeywords.<br>
 * <br>
 * If ItemCount is greater than 200, use GetSearchResults if you
 * want to retrieve more matching items.<br>
 * <br>
 * <span class="tablenote"><b>Note:</b>
 * GetSearchResults doesn't currently support ProductReferenceID
 * queries. So, you can use ExternalProductID (if present) as input
 * to GetSearchResults. Otherwise, see Product.DetailsURL for
 * a workaround to determine a ProductID value to use with
 * GetSearchResults.</span>
 * <br> <B>Output property:</B> <code>ReturnedBuyingGuideDetails</code> - Contains information for up to 5 buying guides that match the
 * query. Only returned when you set IncludeBuyingGuideDetails to
 * true. Buying guides provide useful information about shopping
 * in a particular domain, like Digital Cameras.
 * For example, a digital camera buying guide
 * could help a buyer determine what kind of digital camera
 * is right for them.
 * If no buying guides are associated with the product, this only
 * returns a link to the buying guide hub (the main Guides page).
 * <br> <B>Output property:</B> <code>ReturnedDuplicateItems</code> - Indicates whether there are duplicated items not returned by this 
 * response when HideDuplicateItems is true in the request. 
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class GetProductsCall extends com.ebay.sdk.ApiCall
{
  
  private ProductSearchType productSearch = null;
  private ProductSortCodeType productSort = null;
  private Boolean includeItemArray = null;
  private Boolean includeReviewDetails = null;
  private Boolean includeBuyingGuideDetails = null;
  private Boolean includeHistogram = null;
  private AffiliateTrackingDetailsType affiliateTrackingDetails = null;
  private Boolean hideDuplicateItems = null;
  private CharacteristicsSetProductHistogramType returnedCharacteristicsSetProductHistogram=null;
  private Integer returnedPageNumber=null;
  private Integer returnedApproximatePages=null;
  private Boolean returnedHasMore=null;
  private Integer returnedTotalProducts=null;
  private CatalogProductType[] returnedProduct=null;
  private ItemArrayType returnedItemArray=null;
  private BuyingGuideDetailsType returnedBuyingGuideDetails=null;
  private Boolean returnedDuplicateItems=null;


  /**
   * Constructor.
   */
  public GetProductsCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public GetProductsCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Searches for stock information and reviews for certain kinds of products,
   * such as a particular digital camera model.
   * <p>
   * GetProducts is designed to be useful to applications that support
   * shopping comparison, product reviews, or basic supply and
   * demand data.
   * </p>
   * <p>
   * GetProducts also supports tracking so that members of the
   * eBay Affiliates Program can get commissions for driving traffic to eBay.
   * </p>
   * <p class="tablenote"><b>Note:</b>
   * For selling use cases, use GetProductSearchResults and
   * GetProductSellingPages instead.
   * </p>
   * <p>
   * To use this call, you typically pass in keywords, and GetProducts finds
   * products with matching words in the product title, description, and/or
   * Item Specifics.
   * <p>
   * For each product of interest, you call GetProducts again to retrieve
   * additional details that would be useful to buyers:
   * </p>
   * <ul>
   * <li>Top reviews of the product by eBay members,
   * including part of the review text, plus links to the full text on the
   * eBay Web site.</li>
   * <li>Relevant buying guides (shopping advice) written by
   * eBay members and by eBay staff, including part of the guide text,
   * plus links to the full text
   * on the eBay Web site.</li>
   * <li>Up to 200 matching items on eBay (if any). (To find more matching
   * items, use GetSearchResults.)</li>
   * </ul>
   * <p>
   * <span class="tablenote"><b>Note:</b>
   * As catalog queries can take longer than item queries,
   * GetProducts can be slower than GetSearchResults.
   * Also, due to the way product data is cached, you may get a faster response
   * when you run the same query a second time.</span>
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The CharacteristicsSetProductHistogramType object.
   */
  public CharacteristicsSetProductHistogramType getProducts()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GetProductsRequestType req;
    req = new GetProductsRequestType();
    if (this.productSearch != null)
      req.setProductSearch(this.productSearch);
    if (this.productSort != null)
      req.setProductSort(this.productSort);
    if (this.includeItemArray != null)
      req.setIncludeItemArray(this.includeItemArray);
    if (this.includeReviewDetails != null)
      req.setIncludeReviewDetails(this.includeReviewDetails);
    if (this.includeBuyingGuideDetails != null)
      req.setIncludeBuyingGuideDetails(this.includeBuyingGuideDetails);
    if (this.includeHistogram != null)
      req.setIncludeHistogram(this.includeHistogram);
    if (this.affiliateTrackingDetails != null)
      req.setAffiliateTrackingDetails(this.affiliateTrackingDetails);
    if (this.hideDuplicateItems != null)
      req.setHideDuplicateItems(this.hideDuplicateItems);

    GetProductsResponseType resp = (GetProductsResponseType) execute(req);

    this.returnedCharacteristicsSetProductHistogram = resp.getCharacteristicsSetProductHistogram();
    this.returnedPageNumber = resp.getPageNumber();
    this.returnedApproximatePages = resp.getApproximatePages();
    this.returnedHasMore = resp.isHasMore();
    this.returnedTotalProducts = resp.getTotalProducts();
    this.returnedProduct = resp.getProduct();
    this.returnedItemArray = resp.getItemArray();
    this.returnedBuyingGuideDetails = resp.getBuyingGuideDetails();
    this.returnedDuplicateItems = resp.isDuplicateItems();
    return this.getReturnedCharacteristicsSetProductHistogram();
  }

  /**
   * Gets the GetProductsRequestType.affiliateTrackingDetails.
   * @return AffiliateTrackingDetailsType
   */
  public AffiliateTrackingDetailsType getAffiliateTrackingDetails()
  {
    return this.affiliateTrackingDetails;
  }

  /**
   * Sets the GetProductsRequestType.affiliateTrackingDetails.
   * @param affiliateTrackingDetails AffiliateTrackingDetailsType
   */
  public void setAffiliateTrackingDetails(AffiliateTrackingDetailsType affiliateTrackingDetails)
  {
    this.affiliateTrackingDetails = affiliateTrackingDetails;
  }

  /**
   * Gets the GetProductsRequestType.hideDuplicateItems.
   * @return Boolean
   */
  public Boolean getHideDuplicateItems()
  {
    return this.hideDuplicateItems;
  }

  /**
   * Sets the GetProductsRequestType.hideDuplicateItems.
   * @param hideDuplicateItems Boolean
   */
  public void setHideDuplicateItems(Boolean hideDuplicateItems)
  {
    this.hideDuplicateItems = hideDuplicateItems;
  }

  /**
   * Gets the GetProductsRequestType.includeBuyingGuideDetails.
   * @return Boolean
   */
  public Boolean getIncludeBuyingGuideDetails()
  {
    return this.includeBuyingGuideDetails;
  }

  /**
   * Sets the GetProductsRequestType.includeBuyingGuideDetails.
   * @param includeBuyingGuideDetails Boolean
   */
  public void setIncludeBuyingGuideDetails(Boolean includeBuyingGuideDetails)
  {
    this.includeBuyingGuideDetails = includeBuyingGuideDetails;
  }

  /**
   * Gets the GetProductsRequestType.includeHistogram.
   * @return Boolean
   */
  public Boolean getIncludeHistogram()
  {
    return this.includeHistogram;
  }

  /**
   * Sets the GetProductsRequestType.includeHistogram.
   * @param includeHistogram Boolean
   */
  public void setIncludeHistogram(Boolean includeHistogram)
  {
    this.includeHistogram = includeHistogram;
  }

  /**
   * Gets the GetProductsRequestType.includeItemArray.
   * @return Boolean
   */
  public Boolean getIncludeItemArray()
  {
    return this.includeItemArray;
  }

  /**
   * Sets the GetProductsRequestType.includeItemArray.
   * @param includeItemArray Boolean
   */
  public void setIncludeItemArray(Boolean includeItemArray)
  {
    this.includeItemArray = includeItemArray;
  }

  /**
   * Gets the GetProductsRequestType.includeReviewDetails.
   * @return Boolean
   */
  public Boolean getIncludeReviewDetails()
  {
    return this.includeReviewDetails;
  }

  /**
   * Sets the GetProductsRequestType.includeReviewDetails.
   * @param includeReviewDetails Boolean
   */
  public void setIncludeReviewDetails(Boolean includeReviewDetails)
  {
    this.includeReviewDetails = includeReviewDetails;
  }

  /**
   * Gets the GetProductsRequestType.productSearch.
   * @return ProductSearchType
   */
  public ProductSearchType getProductSearch()
  {
    return this.productSearch;
  }

  /**
   * Sets the GetProductsRequestType.productSearch.
   * @param productSearch ProductSearchType
   */
  public void setProductSearch(ProductSearchType productSearch)
  {
    this.productSearch = productSearch;
  }

  /**
   * Gets the GetProductsRequestType.productSort.
   * @return ProductSortCodeType
   */
  public ProductSortCodeType getProductSort()
  {
    return this.productSort;
  }

  /**
   * Sets the GetProductsRequestType.productSort.
   * @param productSort ProductSortCodeType
   */
  public void setProductSort(ProductSortCodeType productSort)
  {
    this.productSort = productSort;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetProductsResponseType.returnedApproximatePages.
   * 
   * @return Integer
   */
  public Integer getReturnedApproximatePages()
  {
    return this.returnedApproximatePages;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetProductsResponseType.returnedBuyingGuideDetails.
   * 
   * @return BuyingGuideDetailsType
   */
  public BuyingGuideDetailsType getReturnedBuyingGuideDetails()
  {
    return this.returnedBuyingGuideDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetProductsResponseType.returnedCharacteristicsSetProductHistogram.
   * 
   * @return CharacteristicsSetProductHistogramType
   */
  public CharacteristicsSetProductHistogramType getReturnedCharacteristicsSetProductHistogram()
  {
    return this.returnedCharacteristicsSetProductHistogram;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetProductsResponseType.returnedDuplicateItems.
   * 
   * @return Boolean
   */
  public Boolean getReturnedDuplicateItems()
  {
    return this.returnedDuplicateItems;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetProductsResponseType.returnedHasMore.
   * 
   * @return Boolean
   */
  public Boolean getReturnedHasMore()
  {
    return this.returnedHasMore;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetProductsResponseType.returnedItemArray.
   * 
   * @return ItemArrayType
   */
  public ItemArrayType getReturnedItemArray()
  {
    return this.returnedItemArray;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetProductsResponseType.returnedPageNumber.
   * 
   * @return Integer
   */
  public Integer getReturnedPageNumber()
  {
    return this.returnedPageNumber;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetProductsResponseType.returnedProduct.
   * 
   * @return CatalogProductType[]
   */
  public CatalogProductType[] getReturnedProduct()
  {
    return this.returnedProduct;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetProductsResponseType.returnedTotalProducts.
   * 
   * @return Integer
   */
  public Integer getReturnedTotalProducts()
  {
    return this.returnedTotalProducts;
  }

}

