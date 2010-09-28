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
 * Wrapper class of the IssueRefund call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>ItemID</code> - ID of the Half.com item associated with the refund payment to the buyer.
 * To uniquely identify a transaction, you need to specify both ItemID
 * and TransactionID. Use GetSellerPayments to determine the item ID
 * and transaction ID associated with the original sale payment.
 * <br> <B>Input property:</B> <code>TransactionID</code> - ID of the Half.com transaction associated with the refund payment.
 * To uniquely identify a transaction, you need to specify both ItemID
 * and TransactionID. Use GetSellerPayments to determine the item ID
 * and transaction ID associated with the original sale payment.
 * <br> <B>Input property:</B> <code>RefundReason</code> - Explanation of the reason that the refund is being issued.
 * <br> <B>Input property:</B> <code>RefundType</code> - Explanation of the costs that the refund amount covers.
 * <br> <B>Input property:</B> <code>RefundAmount</code> - The amount the seller wants to refund to the buyer, in US Dollars (USD).
 * Must be greater than 0.00. Half.com allows a maximum of the original item
 * sale price (transaction price plus original shipping reimbursement) plus
 * return shipping costs (the amount the buyer paid to return the item).
 * Typically, the return shipping cost is based on the current cost of
 * shipping the individual item (not the discounted cost calculated during
 * the original checkout for a multi-item order). You can also issue a
 * partial refund for the amount you want the buyer to receive. If
 * RefundType=Full or RefundType=FullPlusShipping and you do not pass
 * RefundAmount in the request, Half.com will calculate the refund amount for
 * you. If you pass RefundAmount in the request, the amount you specify will
 * override Half.com's calculated value. Required if RefundType=
 * CustomOrPartial.
 * <br> <B>Input property:</B> <code>RefundMessage</code> - Note to the buyer. Cannot include HTML.
 * <br> <B>Output property:</B> <code>ReturnedRefundFromSeller</code> - Total amount that the seller asked Half.com to refund to 
 * a buyer for a Half.com transaction.
 * <br> <B>Output property:</B> <code>ReturnedTotalRefundToBuyer</code> - Total amount that Half.com refunded to the buyer (which could include the refund amount 
 * from the seller plus a refund amount from Half.com).
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class IssueRefundCall extends com.ebay.sdk.ApiCall
{
  
  private String itemID = null;
  private String transactionID = null;
  private RefundReasonCodeType refundReason = null;
  private RefundTypeCodeType refundType = null;
  private AmountType refundAmount = null;
  private String refundMessage = null;
  private AmountType returnedRefundFromSeller=null;
  private AmountType returnedTotalRefundToBuyer=null;


  /**
   * Constructor.
   */
  public IssueRefundCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public IssueRefundCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Issues a refund for a single Half.com transaction. This can only be
   * called by a seller. A refund may only be issued for a specific
   * transaction. Sellers do not have the ability to issue a general
   * refund (a refund not tied to a transaction) to a buyer.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The AmountType object.
   */
  public AmountType issueRefund()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    IssueRefundRequestType req;
    req = new IssueRefundRequestType();
    if (this.itemID != null)
      req.setItemID(this.itemID);
    if (this.transactionID != null)
      req.setTransactionID(this.transactionID);
    if (this.refundReason != null)
      req.setRefundReason(this.refundReason);
    if (this.refundType != null)
      req.setRefundType(this.refundType);
    if (this.refundAmount != null)
      req.setRefundAmount(this.refundAmount);
    if (this.refundMessage != null)
      req.setRefundMessage(this.refundMessage);

    IssueRefundResponseType resp = (IssueRefundResponseType) execute(req);

    this.returnedRefundFromSeller = resp.getRefundFromSeller();
    this.returnedTotalRefundToBuyer = resp.getTotalRefundToBuyer();
    return this.getReturnedRefundFromSeller();
  }

  /**
   * Gets the IssueRefundRequestType.itemID.
   * @return String
   */
  public String getItemID()
  {
    return this.itemID;
  }

  /**
   * Sets the IssueRefundRequestType.itemID.
   * @param itemID String
   */
  public void setItemID(String itemID)
  {
    this.itemID = itemID;
  }

  /**
   * Gets the IssueRefundRequestType.refundAmount.
   * @return AmountType
   */
  public AmountType getRefundAmount()
  {
    return this.refundAmount;
  }

  /**
   * Sets the IssueRefundRequestType.refundAmount.
   * @param refundAmount AmountType
   */
  public void setRefundAmount(AmountType refundAmount)
  {
    this.refundAmount = refundAmount;
  }

  /**
   * Gets the IssueRefundRequestType.refundMessage.
   * @return String
   */
  public String getRefundMessage()
  {
    return this.refundMessage;
  }

  /**
   * Sets the IssueRefundRequestType.refundMessage.
   * @param refundMessage String
   */
  public void setRefundMessage(String refundMessage)
  {
    this.refundMessage = refundMessage;
  }

  /**
   * Gets the IssueRefundRequestType.refundReason.
   * @return RefundReasonCodeType
   */
  public RefundReasonCodeType getRefundReason()
  {
    return this.refundReason;
  }

  /**
   * Sets the IssueRefundRequestType.refundReason.
   * @param refundReason RefundReasonCodeType
   */
  public void setRefundReason(RefundReasonCodeType refundReason)
  {
    this.refundReason = refundReason;
  }

  /**
   * Gets the IssueRefundRequestType.refundType.
   * @return RefundTypeCodeType
   */
  public RefundTypeCodeType getRefundType()
  {
    return this.refundType;
  }

  /**
   * Sets the IssueRefundRequestType.refundType.
   * @param refundType RefundTypeCodeType
   */
  public void setRefundType(RefundTypeCodeType refundType)
  {
    this.refundType = refundType;
  }

  /**
   * Gets the IssueRefundRequestType.transactionID.
   * @return String
   */
  public String getTransactionID()
  {
    return this.transactionID;
  }

  /**
   * Sets the IssueRefundRequestType.transactionID.
   * @param transactionID String
   */
  public void setTransactionID(String transactionID)
  {
    this.transactionID = transactionID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned IssueRefundResponseType.returnedRefundFromSeller.
   * 
   * @return AmountType
   */
  public AmountType getReturnedRefundFromSeller()
  {
    return this.returnedRefundFromSeller;
  }

  /**
   * Valid after executing the API.
   * Gets the returned IssueRefundResponseType.returnedTotalRefundToBuyer.
   * 
   * @return AmountType
   */
  public AmountType getReturnedTotalRefundToBuyer()
  {
    return this.returnedTotalRefundToBuyer;
  }

}

