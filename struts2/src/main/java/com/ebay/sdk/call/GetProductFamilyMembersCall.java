/*
Copyright (c) 2009 eBay, Inc.

This program is licensed under the terms of the eBay Common Development and 
Distribution License (CDDL) Version 1.0 (the "License") and any subsequent 
version thereof released by eBay.  The then-current version of the License 
can be found at https://www.codebase.ebay.com/Licenses.html and in the 
eBaySDKLicense file that is under the eBay SDK install directory.
*/

package com.ebay.sdk.call;


import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the GetProductFamilyMembers call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>ProductSearch</code> - Specifies the ID of a product in the family to be retrieved,
 * along with pagination and sorting instructions.
 * ProductSearch is a required input.
 * <br> <B>Output property:</B> <code>ReturnedDataElementSets</code> - Container for one or more DataElement fields containing supplemental helpful data.
 * A DataElement field is an HTML snippet that specifies hints for the user, help links,
 * help graphics, and other supplemental information that varies per characteristics set.
 * Usage of this information is optional and may require developers to inspect the information
 * to determine how it can be applied in an application.
 * <br> <B>Output property:</B> <code>ReturnedProductSearchResults</code> - Contains the attributes and summary product details for all products that match
 * the product ID (or IDs) passed in the request.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class GetProductFamilyMembersCall extends com.ebay.sdk.ApiCall
{
  
  private ProductSearchType[] productSearch = null;
  private DataElementSetType[] returnedDataElementSets=null;
  private ProductSearchResultType[] returnedProductSearchResults=null;


  /**
   * Constructor.
   */
  public GetProductFamilyMembersCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public GetProductFamilyMembersCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   *    * GetProductFamilyMembers is intended to be used combination with GetProductSearchResults.
   * If a search result returned from GetProductSearchResults does not return all
   * product versions in a family, and if the user wants to see more versions (editions)
   * of the product, you can use GetProductFamilyMembers to retrieve all versions of the product.
   * That is, if GetProductSearchResultsonly returns the product family header (ParentProduct),
   * use this call to zoom in on a particular family of product versions.
   * (This situation usually occurs when you call GetProductSearchResults and more more
   * matches are found than the MaxChildrenPerFamily value you specified.)<br>
   * <br>
   * The structure of tGetProductFamilyMembers is similar to that of GetProductSearchResults.
   * Instead of passing in a query, you pass in a product ID. This product ID is used to
   * find all the members of the product family that the specified product is a member of.
   * The results are compatible with the results from GetProductSearchResults,
   * so similar application logic can be used to handle both requests and responses.
   * This call supports batch requests. This means you can retrieve products in
   * multiple families by using a single request. To perform a batch request,
   * pass an array of ProductSearch objects in the call.<br>
   * <br>
   * For each ProductSearch object, GetProductFamilyMembers returns a list of all the
   * products in the specified product family. Each product is represented as a list of
   * attributes (Item Specifics) plus other identifying information, such as a product ID
   * and a stock photo.<br>
   * <br>
   * Once the user selects a product from the results, pass its ID in a GetProductSellingPages
   * request to retrieve more detailed information about the product, including a set of
   * optional Item Specifics that the seller can use in addition to the
   * pre-filled Item Specifics (see GetProductSellingPages). <br>
   * <br>
   * To use this data in a listing, pass the product ID and the optional Item Specifics
   * in the seller's listing request (AddItem).
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The ProductSearchResultType[] object.
   */
  public ProductSearchResultType[] getProductFamilyMembers()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GetProductFamilyMembersRequestType req;
    req = new GetProductFamilyMembersRequestType();

    if( this.productSearch == null )
      throw new SdkException("ProductSearch property is not set.");

    req.setDetailLevel(this.getDetailLevel());
    if (this.productSearch != null)
      req.setProductSearch(this.productSearch);

    GetProductFamilyMembersResponseType resp = (GetProductFamilyMembersResponseType) execute(req);

    this.returnedDataElementSets = resp.getDataElementSets();
    this.returnedProductSearchResults = resp.getProductSearchResult();
    return this.getReturnedProductSearchResults();
  }

  /**
   * Gets the GetProductFamilyMembersRequestType.productSearch.
   * @return ProductSearchType[]
   */
  public ProductSearchType[] getProductSearch()
  {
    return this.productSearch;
  }

  /**
   * Sets the GetProductFamilyMembersRequestType.productSearch.
   * @param productSearch ProductSearchType[]
   */
  public void setProductSearch(ProductSearchType[] productSearch)
  {
    this.productSearch = productSearch;
  }
/**
   * Executes the SOAP API. GetProductFamilyMembersCall.ProductSearch will
   * be ignored.
   * @param productSearch ProductSearchType[]
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return ProductSearchResultType[]
   */
  public ProductSearchResultType[] getProductFamilyMembers(ProductSearchType[] productSearch)
    throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GetProductFamilyMembersRequestType req = new GetProductFamilyMembersRequestType();
    req.setProductSearch(productSearch);

    GetProductFamilyMembersResponseType resp = (GetProductFamilyMembersResponseType)this.execute(req);
    this.returnedProductSearchResults = resp.getProductSearchResult();
    this.returnedDataElementSets = resp.getDataElementSets();
    return this.returnedProductSearchResults;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetProductFamilyMembersResponseType.returnedDataElementSets.
   * 
   * @return DataElementSetType[]
   */
  public DataElementSetType[] getReturnedDataElementSets()
  {
    return this.returnedDataElementSets;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetProductFamilyMembersResponseType.returnedProductSearchResults.
   * 
   * @return ProductSearchResultType[]
   */
  public ProductSearchResultType[] getReturnedProductSearchResults()
  {
    return this.returnedProductSearchResults;
  }

}

