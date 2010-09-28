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
 * Wrapper class of the GetSellingManagerSaleRecord call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>ItemID</code> - The item ID associated with the sale record. If not specified, either
 * TransactionID or OrderID must be specified.
 * <br> <B>Input property:</B> <code>TransactionID</code> - The transaction ID associated with the sale record. If not specified, either
 * ItemID or OrderID must be specified.
 * <br> <B>Input property:</B> <code>OrderID</code> - The order ID associated with the sale record.
 * If specified, then the following, if specified in the same call, are ignored:
 * ItemID and TransactionID.
 * <br> <B>Output property:</B> <code>ReturnedSellingManagerSoldOrder</code> - Contains the data in a Selling Manager sale record.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class GetSellingManagerSaleRecordCall extends com.ebay.sdk.ApiCall
{
  
  private String itemID = null;
  private String transactionID = null;
  private String orderID = null;
  private SellingManagerSoldOrderType returnedSellingManagerSoldOrder=null;


  /**
   * Constructor.
   */
  public GetSellingManagerSaleRecordCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public GetSellingManagerSaleRecordCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Requests the data in a Selling Manager sale record.
   * <br><br>
   * This call is subject to change without notice; the
   * deprecation process is inapplicable to this call.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The SellingManagerSoldOrderType object.
   */
  public SellingManagerSoldOrderType getSellingManagerSaleRecord()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GetSellingManagerSaleRecordRequestType req;
    req = new GetSellingManagerSaleRecordRequestType();
    if (this.itemID != null)
      req.setItemID(this.itemID);
    if (this.transactionID != null)
      req.setTransactionID(this.transactionID);
    if (this.orderID != null)
      req.setOrderID(this.orderID);

    GetSellingManagerSaleRecordResponseType resp = (GetSellingManagerSaleRecordResponseType) execute(req);

    this.returnedSellingManagerSoldOrder = resp.getSellingManagerSoldOrder();
    return this.getReturnedSellingManagerSoldOrder();
  }

  /**
   * Gets the GetSellingManagerSaleRecordRequestType.itemID.
   * @return String
   */
  public String getItemID()
  {
    return this.itemID;
  }

  /**
   * Sets the GetSellingManagerSaleRecordRequestType.itemID.
   * @param itemID String
   */
  public void setItemID(String itemID)
  {
    this.itemID = itemID;
  }

  /**
   * Gets the GetSellingManagerSaleRecordRequestType.orderID.
   * @return String
   */
  public String getOrderID()
  {
    return this.orderID;
  }

  /**
   * Sets the GetSellingManagerSaleRecordRequestType.orderID.
   * @param orderID String
   */
  public void setOrderID(String orderID)
  {
    this.orderID = orderID;
  }

  /**
   * Gets the GetSellingManagerSaleRecordRequestType.transactionID.
   * @return String
   */
  public String getTransactionID()
  {
    return this.transactionID;
  }

  /**
   * Sets the GetSellingManagerSaleRecordRequestType.transactionID.
   * @param transactionID String
   */
  public void setTransactionID(String transactionID)
  {
    this.transactionID = transactionID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSellingManagerSaleRecordResponseType.returnedSellingManagerSoldOrder.
   * 
   * @return SellingManagerSoldOrderType
   */
  public SellingManagerSoldOrderType getReturnedSellingManagerSoldOrder()
  {
    return this.returnedSellingManagerSoldOrder;
  }

}

