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
 * Wrapper class of the AddDispute call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>DisputeExplanation</code> - An explanation of the dispute that supplements the
 * DisputeReason. The allowed value depends on the value of
 * DisputeReason.
 * <br> <B>Input property:</B> <code>DisputeReason</code> - The top-level reason for the Unpaid Item Dispute.
 * DisputeReasonCodeType has several possible values. However, only
 * BuyerHasNotPaid and TransactionMutuallyCanceled apply to
 * AddDispute--you can only use AddDisputeCall to create Unpaid
 * Item disputes.
 * <br> <B>Input property:</B> <code>ItemID</code> - The eBay ID of the item in dispute, an item which has been
 * sold but not yet paid for.
 * <br> <B>Input property:</B> <code>TransactionID</code> - The eBay ID of a transaction, created when the buyer committed
 * to purchasing the item. A transaction ID is unique to the item
 * but not across the entire eBay site. The transaction ID must be
 * combined with an item ID to uniquely identify an item.
 * <br> <B>Output property:</B> <code>ReturnedDisputeID</code> - The unique identifier of the Unpaid Item dispute.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class AddDisputeCall extends com.ebay.sdk.ApiCall
{
  
  private DisputeExplanationCodeType disputeExplanation = null;
  private DisputeReasonCodeType disputeReason = null;
  private String itemID = null;
  private String transactionID = null;
  private String returnedDisputeID=null;


  /**
   * Constructor.
   */
  public AddDisputeCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public AddDisputeCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Enables a seller to create a new Unpaid Item dispute. (Item Not Received
   * disputes can only be created via the eBay web site.)
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The String object.
   */
  public String addDispute()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    AddDisputeRequestType req;
    req = new AddDisputeRequestType();
    if (this.disputeExplanation != null)
      req.setDisputeExplanation(this.disputeExplanation);
    if (this.disputeReason != null)
      req.setDisputeReason(this.disputeReason);
    if (this.itemID != null)
      req.setItemID(this.itemID);
    if (this.transactionID != null)
      req.setTransactionID(this.transactionID);

    AddDisputeResponseType resp = (AddDisputeResponseType) execute(req);

    this.returnedDisputeID = resp.getDisputeID();
    return this.getReturnedDisputeID();
  }

  /**
   * Gets the AddDisputeRequestType.disputeExplanation.
   * @return DisputeExplanationCodeType
   */
  public DisputeExplanationCodeType getDisputeExplanation()
  {
    return this.disputeExplanation;
  }

  /**
   * Sets the AddDisputeRequestType.disputeExplanation.
   * @param disputeExplanation DisputeExplanationCodeType
   */
  public void setDisputeExplanation(DisputeExplanationCodeType disputeExplanation)
  {
    this.disputeExplanation = disputeExplanation;
  }

  /**
   * Gets the AddDisputeRequestType.disputeReason.
   * @return DisputeReasonCodeType
   */
  public DisputeReasonCodeType getDisputeReason()
  {
    return this.disputeReason;
  }

  /**
   * Sets the AddDisputeRequestType.disputeReason.
   * @param disputeReason DisputeReasonCodeType
   */
  public void setDisputeReason(DisputeReasonCodeType disputeReason)
  {
    this.disputeReason = disputeReason;
  }

  /**
   * Gets the AddDisputeRequestType.itemID.
   * @return String
   */
  public String getItemID()
  {
    return this.itemID;
  }

  /**
   * Sets the AddDisputeRequestType.itemID.
   * @param itemID String
   */
  public void setItemID(String itemID)
  {
    this.itemID = itemID;
  }

  /**
   * Gets the AddDisputeRequestType.transactionID.
   * @return String
   */
  public String getTransactionID()
  {
    return this.transactionID;
  }

  /**
   * Sets the AddDisputeRequestType.transactionID.
   * @param transactionID String
   */
  public void setTransactionID(String transactionID)
  {
    this.transactionID = transactionID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned AddDisputeResponseType.returnedDisputeID.
   * 
   * @return String
   */
  public String getReturnedDisputeID()
  {
    return this.returnedDisputeID;
  }

}

