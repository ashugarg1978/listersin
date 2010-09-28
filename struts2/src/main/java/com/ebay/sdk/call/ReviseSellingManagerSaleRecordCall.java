/*
Copyright (c) 2009 eBay, Inc.

This program is licensed under the terms of the eBay Common Development and 
Distribution License (CDDL) Version 1.0 (the "License") and any subsequent 
version thereof released by eBay.  The then-current version of the License 
can be found at https://www.codebase.ebay.com/Licenses.html and in the 
eBaySDKLicense file that is under the eBay SDK install directory.
*/

package com.ebay.sdk.call;

import java.lang.String;

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the ReviseSellingManagerSaleRecord call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>ItemID</code> - The item ID associated with the sale record.
 * <br> <B>Input property:</B> <code>TransactionID</code> - The transaction ID associated with the sale record.
 * <br> <B>Input property:</B> <code>OrderID</code> - The order ID associated with the sale record.
 * If specified, then the following, if specified in the same call, are ignored:
 * ItemID and TransactionID.
 * <br> <B>Input property:</B> <code>SellingManagerSoldOrder</code> - Details of a Selling Manager order.
 * Each order can have one or more transactions.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class ReviseSellingManagerSaleRecordCall extends com.ebay.sdk.ApiCall
{
  
  private String itemID = null;
  private String transactionID = null;
  private String orderID = null;
  private SellingManagerSoldOrderType sellingManagerSoldOrder = null;


  /**
   * Constructor.
   */
  public ReviseSellingManagerSaleRecordCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public ReviseSellingManagerSaleRecordCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Revises a Selling Manager sale record when you specify
   * one of the following: an ItemID, a TransactionID, or an OrderID.
   * This call is subject to change without notice; the
   * deprecation process is inapplicable to this call.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The void object.
   */
  public void reviseSellingManagerSaleRecord()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    ReviseSellingManagerSaleRecordRequestType req;
    req = new ReviseSellingManagerSaleRecordRequestType();
    if (this.itemID != null)
      req.setItemID(this.itemID);
    if (this.transactionID != null)
      req.setTransactionID(this.transactionID);
    if (this.orderID != null)
      req.setOrderID(this.orderID);
    if (this.sellingManagerSoldOrder != null)
      req.setSellingManagerSoldOrder(this.sellingManagerSoldOrder);

    ReviseSellingManagerSaleRecordResponseType resp = (ReviseSellingManagerSaleRecordResponseType) execute(req);


  }

  /**
   * Gets the ReviseSellingManagerSaleRecordRequestType.itemID.
   * @return String
   */
  public String getItemID()
  {
    return this.itemID;
  }

  /**
   * Sets the ReviseSellingManagerSaleRecordRequestType.itemID.
   * @param itemID String
   */
  public void setItemID(String itemID)
  {
    this.itemID = itemID;
  }

  /**
   * Gets the ReviseSellingManagerSaleRecordRequestType.orderID.
   * @return String
   */
  public String getOrderID()
  {
    return this.orderID;
  }

  /**
   * Sets the ReviseSellingManagerSaleRecordRequestType.orderID.
   * @param orderID String
   */
  public void setOrderID(String orderID)
  {
    this.orderID = orderID;
  }

  /**
   * Gets the ReviseSellingManagerSaleRecordRequestType.sellingManagerSoldOrder.
   * @return SellingManagerSoldOrderType
   */
  public SellingManagerSoldOrderType getSellingManagerSoldOrder()
  {
    return this.sellingManagerSoldOrder;
  }

  /**
   * Sets the ReviseSellingManagerSaleRecordRequestType.sellingManagerSoldOrder.
   * @param sellingManagerSoldOrder SellingManagerSoldOrderType
   */
  public void setSellingManagerSoldOrder(SellingManagerSoldOrderType sellingManagerSoldOrder)
  {
    this.sellingManagerSoldOrder = sellingManagerSoldOrder;
  }

  /**
   * Gets the ReviseSellingManagerSaleRecordRequestType.transactionID.
   * @return String
   */
  public String getTransactionID()
  {
    return this.transactionID;
  }

  /**
   * Sets the ReviseSellingManagerSaleRecordRequestType.transactionID.
   * @param transactionID String
   */
  public void setTransactionID(String transactionID)
  {
    this.transactionID = transactionID;
  }

}

