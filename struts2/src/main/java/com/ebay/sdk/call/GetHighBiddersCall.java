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
 * Wrapper class of the GetHighBidders call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>ItemID</code> - Unique item ID that identifies the Dutch auction listing for which to
 * retrieve a list of the high bidders.
 * <br><br>
 * <span class="tablenote"><strong>Note:</strong>
 * As of version 619, Dutch-style (multi-item) competitive-bid auctions are deprecated.
 * eBay throws an error if you submit a Dutch item listing with AddItem
 * or VerifyAddItem. If you use RelistItem to update a Dutch auction listing,
 * eBay generates a warning and resets the Quantity value to 1.
 * </span>
 * <br>
 * <br> <B>Output property:</B> <code>ReturnedOffers</code> - Contains a list of zero, one, or multiple OfferType objects. Each
 * OfferType object represents the data for one high bidder. See the schema
 * documentation for OfferType for details on its properties and their
 * meanings.
 * <br> <B>Output property:</B> <code>ReturnedListingStatus</code> - Specifies an active or ended listing's status in eBay's processing workflow.
 * If a listing ends with a sale (or sales), eBay needs to update the sale
 * details (e.g., winning bidder) and other information. This processing can take
 * several minutes. If you retrieve a sold item, use this listing status
 * information to determine whether eBay has finished processing the listing so
 * that you can be sure the winning bidder and other details are correct and
 * complete.
 * <br><br>
 * <span class="tablenote"><strong>Note:</strong>
 * As of version 619, Dutch-style (multi-item) competitive-bid auctions are deprecated.
 * eBay throws an error if you submit a Dutch item listing with AddItem
 * or VerifyAddItem. If you use RelistItem to update a Dutch auction listing,
 * eBay generates a warning and resets the Quantity value to 1.
 * </span>
 * <br>
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class GetHighBiddersCall extends com.ebay.sdk.ApiCall
{
  
  private String itemID = null;
  private OfferType[] returnedOffers=null;
  private ListingStatusCodeType returnedListingStatus=null;


  /**
   * Constructor.
   */
  public GetHighBiddersCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public GetHighBiddersCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Retrieves a list of high bidders for the Dutch auction specified in the ItemId
   * property of the request. A seller specifies a unique item ID in this call to
   * determine which buyers are winning bidders and how many items each buyer can
   * purchase. Note that bidder information is anonymous to everyone except the bidder and
   * the seller during an active auction.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The OfferType[] object.
   */
  public OfferType[] getHighBidders()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GetHighBiddersRequestType req;
    req = new GetHighBiddersRequestType();

    if( this.itemID == null )
      throw new SdkException("ItemID property is not set.");

    req.setDetailLevel(this.getDetailLevel());
    if (this.itemID != null)
      req.setItemID(this.itemID);

    GetHighBiddersResponseType resp = (GetHighBiddersResponseType) execute(req);

    this.returnedOffers = (resp.getBidArray() == null? null: resp.getBidArray().getOffer());
    this.returnedListingStatus = resp.getListingStatus();
    return this.getReturnedOffers();
  }

  /**
   * Gets the GetHighBiddersRequestType.itemID.
   * @return String
   */
  public String getItemID()
  {
    return this.itemID;
  }

  /**
   * Sets the GetHighBiddersRequestType.itemID.
   * @param itemID String
   */
  public void setItemID(String itemID)
  {
    this.itemID = itemID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetHighBiddersResponseType.returnedListingStatus.
   * 
   * @return ListingStatusCodeType
   */
  public ListingStatusCodeType getReturnedListingStatus()
  {
    return this.returnedListingStatus;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetHighBiddersResponseType.returnedOffers.
   * 
   * @return OfferType[]
   */
  public OfferType[] getReturnedOffers()
  {
    return this.returnedOffers;
  }

}

