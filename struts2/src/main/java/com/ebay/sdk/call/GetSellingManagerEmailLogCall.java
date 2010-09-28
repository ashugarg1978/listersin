/*
Copyright (c) 2009 eBay, Inc.

This program is licensed under the terms of the eBay Common Development and 
Distribution License (CDDL) Version 1.0 (the "License") and any subsequent 
version thereof released by eBay.  The then-current version of the License 
can be found at https://www.codebase.ebay.com/Licenses.html and in the 
eBaySDKLicense file that is under the eBay SDK install directory.
*/

package com.ebay.sdk.call;

import java.lang.Long;

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the GetSellingManagerEmailLog call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>ItemID</code> - Unique ID for the item associated with the email.
 * <br> <B>Input property:</B> <code>TransactionID</code> - Unique ID for the transaction associated with the email.
 * <br> <B>Input property:</B> <code>OrderID</code> - Unique ID for a multi-item order associated with the email. If specified,
 * ItemID and TransactionID are ignored if specified in the same call.
 * <br> <B>Input property:</B> <code>EmailDateRange</code> - Specifies the earliest (oldest) and latest (most recent) dates to use in a
 * date range filter based on email sent date. Each of the time ranges can be
 * up to 90 days.
 * <br> <B>Output property:</B> <code>ReturnedEmailLog</code> - Email logs associated with this order.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class GetSellingManagerEmailLogCall extends com.ebay.sdk.ApiCall
{
  
  private String itemID = null;
  private Long transactionID = null;
  private String orderID = null;
  private TimeRangeType emailDateRange = null;
  private SellingManagerEmailLogType[] returnedEmailLog=null;


  /**
   * Constructor.
   */
  public GetSellingManagerEmailLogCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public GetSellingManagerEmailLogCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Retrieves a log of emails sent, or scheduled to be sent, to buyers.
   * This call is subject to change without notice; the deprecation process is
   * inapplicable to this call.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The SellingManagerEmailLogType[] object.
   */
  public SellingManagerEmailLogType[] getSellingManagerEmailLog()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GetSellingManagerEmailLogRequestType req;
    req = new GetSellingManagerEmailLogRequestType();
    if (this.itemID != null)
      req.setItemID(this.itemID);
    if (this.transactionID != null)
      req.setTransactionID(this.transactionID);
    if (this.orderID != null)
      req.setOrderID(this.orderID);
    if (this.emailDateRange != null)
      req.setEmailDateRange(this.emailDateRange);

    GetSellingManagerEmailLogResponseType resp = (GetSellingManagerEmailLogResponseType) execute(req);

    this.returnedEmailLog = resp.getEmailLog();
    return this.getReturnedEmailLog();
  }

  /**
   * Gets the GetSellingManagerEmailLogRequestType.emailDateRange.
   * @return TimeRangeType
   */
  public TimeRangeType getEmailDateRange()
  {
    return this.emailDateRange;
  }

  /**
   * Sets the GetSellingManagerEmailLogRequestType.emailDateRange.
   * @param emailDateRange TimeRangeType
   */
  public void setEmailDateRange(TimeRangeType emailDateRange)
  {
    this.emailDateRange = emailDateRange;
  }

  /**
   * Gets the GetSellingManagerEmailLogRequestType.itemID.
   * @return String
   */
  public String getItemID()
  {
    return this.itemID;
  }

  /**
   * Sets the GetSellingManagerEmailLogRequestType.itemID.
   * @param itemID String
   */
  public void setItemID(String itemID)
  {
    this.itemID = itemID;
  }

  /**
   * Gets the GetSellingManagerEmailLogRequestType.orderID.
   * @return String
   */
  public String getOrderID()
  {
    return this.orderID;
  }

  /**
   * Sets the GetSellingManagerEmailLogRequestType.orderID.
   * @param orderID String
   */
  public void setOrderID(String orderID)
  {
    this.orderID = orderID;
  }

  /**
   * Gets the GetSellingManagerEmailLogRequestType.transactionID.
   * @return Long
   */
  public Long getTransactionID()
  {
    return this.transactionID;
  }

  /**
   * Sets the GetSellingManagerEmailLogRequestType.transactionID.
   * @param transactionID Long
   */
  public void setTransactionID(Long transactionID)
  {
    this.transactionID = transactionID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSellingManagerEmailLogResponseType.returnedEmailLog.
   * 
   * @return SellingManagerEmailLogType[]
   */
  public SellingManagerEmailLogType[] getReturnedEmailLog()
  {
    return this.returnedEmailLog;
  }

}

