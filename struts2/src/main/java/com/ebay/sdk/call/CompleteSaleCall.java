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
import java.lang.String;

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the CompleteSale call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>ItemID</code> - ID of the listing associated with the transaction that the seller is
 * completing.
 * <br> <B>Input property:</B> <code>TransactionID</code> - Unique identifier for a transaction from the listing indicated by ItemID.
 * Call GetItemTransactions or GetSellerTransactions to determine the
 * appropriate transaction ID. Required for all listing types (pass 0 for
 * Chinese auctions).
 * <br> <B>Input property:</B> <code>FeedbackInfo</code> - Specifies feedback the seller is leaving for the buyer. The seller can leave
 * feedback once for a given transaction, and no further modifications can be
 * made to that feedback entry. If feedback has already been left, FeedbackInfo
 * is not allowed. Call GetFeedback to determine whether feedback has already
 * been left.
 * <br> <B>Input property:</B> <code>Shipped</code> - If true, the transaction is marked as shipped in My eBay.
 * <br>
 * If false, the transaction is marked as not shipped in My eBay.
 * <br>
 * If not specified, the shipped status in My eBay is not modified.
 * <br> <B>Input property:</B> <code>Paid</code> - If true, the transaction is marked as paid in My eBay.
 * <br>
 * If false, the transaction is marked as not paid in My eBay.
 * <br>
 * If not specified, the paid status in My eBay is not modified.
 * <br> <B>Input property:</B> <code>ListingType</code> - If included in the request, and with a value of ListingType = Half,
 * indicates that the given ItemID and TransactionID values are for Half.com.
 * <br><br>
 * Required for Half.com items.
 * <br> <B>Input property:</B> <code>Shipment</code> - Details about the shipment. Setting the tracking number and carrier
 * automatically marks the item as shipped (sets Shipped to true).
 * <br> <B>Input property:</B> <code>OrderID</code> - Unique ID for a multi-item order. ItemID and TransactionID are ignored if a
 * call includes OrderID. CompleteSale applies to the specified order as a
 * whole (and thus the child transactions associated with the order).
 * <br><br>
 * Not applicable to Half.com.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class CompleteSaleCall extends com.ebay.sdk.ApiCall
{
  
  private String itemID = null;
  private String transactionID = null;
  private FeedbackInfoType feedbackInfo = null;
  private Boolean shipped = null;
  private Boolean paid = null;
  private ListingTypeCodeType listingType = null;
  private ShipmentType shipment = null;
  private String orderID = null;


  /**
   * Constructor.
   */
  public CompleteSaleCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public CompleteSaleCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Enables a seller to do various tasks after an item transaction or multiple-item
   * order has been created. Task examples include leaving feedback for the buyer,
   * changing the paid status, and setting shipment tracking information.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The void object.
   */
  public void completeSale()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    CompleteSaleRequestType req;
    req = new CompleteSaleRequestType();
    if (this.itemID != null)
      req.setItemID(this.itemID);
    if (this.transactionID != null)
      req.setTransactionID(this.transactionID);
    if (this.feedbackInfo != null)
      req.setFeedbackInfo(this.feedbackInfo);
    if (this.shipped != null)
      req.setShipped(this.shipped);
    if (this.paid != null)
      req.setPaid(this.paid);
    if (this.listingType != null)
      req.setListingType(this.listingType);
    if (this.shipment != null)
      req.setShipment(this.shipment);
    if (this.orderID != null)
      req.setOrderID(this.orderID);

    CompleteSaleResponseType resp = (CompleteSaleResponseType) execute(req);


  }

  /**
   * Gets the CompleteSaleRequestType.feedbackInfo.
   * @return FeedbackInfoType
   */
  public FeedbackInfoType getFeedbackInfo()
  {
    return this.feedbackInfo;
  }

  /**
   * Sets the CompleteSaleRequestType.feedbackInfo.
   * @param feedbackInfo FeedbackInfoType
   */
  public void setFeedbackInfo(FeedbackInfoType feedbackInfo)
  {
    this.feedbackInfo = feedbackInfo;
  }

  /**
   * Gets the CompleteSaleRequestType.itemID.
   * @return String
   */
  public String getItemID()
  {
    return this.itemID;
  }

  /**
   * Sets the CompleteSaleRequestType.itemID.
   * @param itemID String
   */
  public void setItemID(String itemID)
  {
    this.itemID = itemID;
  }

  /**
   * Gets the CompleteSaleRequestType.listingType.
   * @return ListingTypeCodeType
   */
  public ListingTypeCodeType getListingType()
  {
    return this.listingType;
  }

  /**
   * Sets the CompleteSaleRequestType.listingType.
   * @param listingType ListingTypeCodeType
   */
  public void setListingType(ListingTypeCodeType listingType)
  {
    this.listingType = listingType;
  }

  /**
   * Gets the CompleteSaleRequestType.orderID.
   * @return String
   */
  public String getOrderID()
  {
    return this.orderID;
  }

  /**
   * Sets the CompleteSaleRequestType.orderID.
   * @param orderID String
   */
  public void setOrderID(String orderID)
  {
    this.orderID = orderID;
  }

  /**
   * Gets the CompleteSaleRequestType.paid.
   * @return Boolean
   */
  public Boolean getPaid()
  {
    return this.paid;
  }

  /**
   * Sets the CompleteSaleRequestType.paid.
   * @param paid Boolean
   */
  public void setPaid(Boolean paid)
  {
    this.paid = paid;
  }

  /**
   * Gets the CompleteSaleRequestType.shipment.
   * @return ShipmentType
   */
  public ShipmentType getShipment()
  {
    return this.shipment;
  }

  /**
   * Sets the CompleteSaleRequestType.shipment.
   * @param shipment ShipmentType
   */
  public void setShipment(ShipmentType shipment)
  {
    this.shipment = shipment;
  }

  /**
   * Gets the CompleteSaleRequestType.shipped.
   * @return Boolean
   */
  public Boolean getShipped()
  {
    return this.shipped;
  }

  /**
   * Sets the CompleteSaleRequestType.shipped.
   * @param shipped Boolean
   */
  public void setShipped(Boolean shipped)
  {
    this.shipped = shipped;
  }

  /**
   * Gets the CompleteSaleRequestType.transactionID.
   * @return String
   */
  public String getTransactionID()
  {
    return this.transactionID;
  }

  /**
   * Sets the CompleteSaleRequestType.transactionID.
   * @param transactionID String
   */
  public void setTransactionID(String transactionID)
  {
    this.transactionID = transactionID;
  }

}

