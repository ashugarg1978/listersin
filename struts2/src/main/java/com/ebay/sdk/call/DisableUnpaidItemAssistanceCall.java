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
 * Wrapper class of the DisableUnpaidItemAssistance call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>ItemID</code> - The eBay ID of the item to exclude from the automated Unpaid Item
 * Assistance mechanism.  If ItemID is provided, TransactionID
 * must be provided. The ItemID/TransactionID pairing of tags is
 * mutually exclusive with DisputeID: provide the pair or
 * provide DisputeID, but not both.
 * <br> <B>Input property:</B> <code>TransactionID</code> - The eBay ID of a transaction, created when the buyer committed
 * to purchasing the item. A transaction ID is unique to the item
 * but not across the entire eBay site. The transaction ID must be
 * combined with an item ID to uniquely identify an item.
 * The ItemID/TransactionID pairing of tags is
 * mutually exclusive with DisputeID: provide the pair or
 * provide DisputeID, but not both.
 * <br> <B>Input property:</B> <code>DisputeID</code> - The ID for a dispute created by the Unpaid Item Assistance Mechanism.
 * Mutually exclusive with the ItemID/TransactionID pair.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class DisableUnpaidItemAssistanceCall extends com.ebay.sdk.ApiCall
{
  
  private String itemID = null;
  private String transactionID = null;
  private String disputeID = null;


  /**
   * Constructor.
   */
  public DisableUnpaidItemAssistanceCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public DisableUnpaidItemAssistanceCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Enables a seller who has opted into the automated Unpaid Item Assistance mechanism
   * to disable that Assistance for an item. The item is identified either by its item
   * ID / transaction ID pair (regardless of whether a dispute ID yet exists for that
   * item) or, if a dispute has been created by the Assistance	mechanism, by the
   * DisputeID. If a dispute had already been created by the Assistance mechanism, it
   * is converted to a "manual" dispute for the seller to manage like any other
   * manually-created dispute. disputes.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The void object.
   */
  public void disableUnpaidItemAssistance()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    DisableUnpaidItemAssistanceRequestType req;
    req = new DisableUnpaidItemAssistanceRequestType();
    if (this.itemID != null)
      req.setItemID(this.itemID);
    if (this.transactionID != null)
      req.setTransactionID(this.transactionID);
    if (this.disputeID != null)
      req.setDisputeID(this.disputeID);

    DisableUnpaidItemAssistanceResponseType resp = (DisableUnpaidItemAssistanceResponseType) execute(req);


  }

  /**
   * Gets the DisableUnpaidItemAssistanceRequestType.disputeID.
   * @return String
   */
  public String getDisputeID()
  {
    return this.disputeID;
  }

  /**
   * Sets the DisableUnpaidItemAssistanceRequestType.disputeID.
   * @param disputeID String
   */
  public void setDisputeID(String disputeID)
  {
    this.disputeID = disputeID;
  }

  /**
   * Gets the DisableUnpaidItemAssistanceRequestType.itemID.
   * @return String
   */
  public String getItemID()
  {
    return this.itemID;
  }

  /**
   * Sets the DisableUnpaidItemAssistanceRequestType.itemID.
   * @param itemID String
   */
  public void setItemID(String itemID)
  {
    this.itemID = itemID;
  }

  /**
   * Gets the DisableUnpaidItemAssistanceRequestType.transactionID.
   * @return String
   */
  public String getTransactionID()
  {
    return this.transactionID;
  }

  /**
   * Sets the DisableUnpaidItemAssistanceRequestType.transactionID.
   * @param transactionID String
   */
  public void setTransactionID(String transactionID)
  {
    this.transactionID = transactionID;
  }

}

