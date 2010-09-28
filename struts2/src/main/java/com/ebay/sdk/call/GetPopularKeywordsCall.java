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

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the GetPopularKeywords call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>CategoryID</code> - A category ID for which you want keywords returned.
 * When IncludeChildCategories is true, one and only one
 * CategoryID is allowed. Otherwise, up
 * to 100 CategoryIds are allowed.
 * <br><br>
 * To retrieve the keywords for root category, set one of
 * the CategoryIDs to -1 or submit no CategoryIDs at all.
 * <br> <B>Input property:</B> <code>IncludeChildCategories</code> - If true, only one CategoryID can be specified, and keywords
 * are returned for that category and its subcategories.
 * If false, keywords are returned only for the categories
 * identified by CategoryID. Default is false.
 * <br> <B>Input property:</B> <code>MaxKeywordsRetrieved</code> - The maximum number of keywords to be retrieved per category
 * for this call.
 * <br> <B>Input property:</B> <code>Pagination</code> - Details about how many categories to return per
 * page and which page to view.
 * <br> <B>Output property:</B> <code>ReturnedPaginationResult</code> - Contains information regarding the pagination of data (if pagination is used),
 * including total number of pages and total number of entries.
 * <br> <B>Output property:</B> <code>ReturnedPopularKeywords</code> - Organization of keywords by category. For each
 * category, its ID and its parent's ID and keywords
 * are listed.
 * <br> <B>Output property:</B> <code>ReturnedHasMore</code> - Indicates whether there are more categories beyond the
 * subset returned in the last
 * call to GetPopularKeywords. Returned only when
 * IncludeChildCategories is true.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class GetPopularKeywordsCall extends com.ebay.sdk.ApiCall
{
  
  private String[] categoryID = null;
  private Boolean includeChildCategories = null;
  private Integer maxKeywordsRetrieved = null;
  private PaginationType pagination = null;
  private PaginationResultType returnedPaginationResult=null;
  private CategoryType[] returnedPopularKeywords=null;
  private Boolean returnedHasMore=null;


  /**
   * Constructor.
   */
  public GetPopularKeywordsCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public GetPopularKeywordsCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Retrieves the words that are most frequently submitted by eBay users when
   * searching for listings.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The void object.
   */
  public void getPopularKeywords()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GetPopularKeywordsRequestType req;
    req = new GetPopularKeywordsRequestType();
    if (this.categoryID != null)
      req.setCategoryID(this.categoryID);
    if (this.includeChildCategories != null)
      req.setIncludeChildCategories(this.includeChildCategories);
    if (this.maxKeywordsRetrieved != null)
      req.setMaxKeywordsRetrieved(this.maxKeywordsRetrieved);
    if (this.pagination != null)
      req.setPagination(this.pagination);

    GetPopularKeywordsResponseType resp = (GetPopularKeywordsResponseType) execute(req);

    this.returnedPaginationResult = resp.getPaginationResult();
    this.returnedPopularKeywords = (resp.getCategoryArray() == null? null: resp.getCategoryArray().getCategory());
    this.returnedHasMore = resp.isHasMore();

  }

  /**
   * Gets the GetPopularKeywordsRequestType.categoryID.
   * @return String[]
   */
  public String[] getCategoryID()
  {
    return this.categoryID;
  }

  /**
   * Sets the GetPopularKeywordsRequestType.categoryID.
   * @param categoryID String[]
   */
  public void setCategoryID(String[] categoryID)
  {
    this.categoryID = categoryID;
  }

  /**
   * Gets the GetPopularKeywordsRequestType.includeChildCategories.
   * @return Boolean
   */
  public Boolean getIncludeChildCategories()
  {
    return this.includeChildCategories;
  }

  /**
   * Sets the GetPopularKeywordsRequestType.includeChildCategories.
   * @param includeChildCategories Boolean
   */
  public void setIncludeChildCategories(Boolean includeChildCategories)
  {
    this.includeChildCategories = includeChildCategories;
  }

  /**
   * Gets the GetPopularKeywordsRequestType.maxKeywordsRetrieved.
   * @return Integer
   */
  public Integer getMaxKeywordsRetrieved()
  {
    return this.maxKeywordsRetrieved;
  }

  /**
   * Sets the GetPopularKeywordsRequestType.maxKeywordsRetrieved.
   * @param maxKeywordsRetrieved Integer
   */
  public void setMaxKeywordsRetrieved(Integer maxKeywordsRetrieved)
  {
    this.maxKeywordsRetrieved = maxKeywordsRetrieved;
  }

  /**
   * Gets the GetPopularKeywordsRequestType.pagination.
   * @return PaginationType
   */
  public PaginationType getPagination()
  {
    return this.pagination;
  }

  /**
   * Sets the GetPopularKeywordsRequestType.pagination.
   * @param pagination PaginationType
   */
  public void setPagination(PaginationType pagination)
  {
    this.pagination = pagination;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetPopularKeywordsResponseType.returnedHasMore.
   * 
   * @return Boolean
   */
  public Boolean getReturnedHasMore()
  {
    return this.returnedHasMore;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetPopularKeywordsResponseType.returnedPaginationResult.
   * 
   * @return PaginationResultType
   */
  public PaginationResultType getReturnedPaginationResult()
  {
    return this.returnedPaginationResult;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetPopularKeywordsResponseType.returnedPopularKeywords.
   * 
   * @return CategoryType[]
   */
  public CategoryType[] getReturnedPopularKeywords()
  {
    return this.returnedPopularKeywords;
  }

}

