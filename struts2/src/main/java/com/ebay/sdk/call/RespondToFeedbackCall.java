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
 * Wrapper class of the RespondToFeedback call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>FeedbackID</code> - An ID that uniquely identifies the feedback. Retrieve FeedbackIDs with
 * GetFeedback. Required if ItemID is not specified.
 * <br> <B>Input property:</B> <code>ItemID</code> - Unique identifier of an item to which the feedback is attached. Required if
 * FeedbackID is not provided.
 * <br> <B>Input property:</B> <code>TransactionID</code> - Unique identifier for a purchase from an
 * eBay Stores Inventory or basic fixed price
 * listing. If TransactionID is specified, ItemID
 * must also be specified.
 * <br> <B>Input property:</B> <code>TargetUserID</code> - User who left the feedback that is being replied to or followed up on.
 * <br> <B>Input property:</B> <code>ResponseType</code> - Specifies whether the response is a reply or a follow-up.
 * <br> <B>Input property:</B> <code>ResponseText</code> - Textual comment that the user who is subject of feedback may leave in
 * response or rebuttal to the feedback. Alternatively, when the  ResponseType
 * is FollowUp, this value contains the text of the follow-up comment.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class RespondToFeedbackCall extends com.ebay.sdk.ApiCall
{
  
  private String feedbackID = null;
  private String itemID = null;
  private String transactionID = null;
  private String targetUserID = null;
  private FeedbackResponseCodeType responseType = null;
  private String responseText = null;


  /**
   * Constructor.
   */
  public RespondToFeedbackCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public RespondToFeedbackCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Replies to feedback that has been left for a user, or posts a
   * follow-up comment to a feedback comment a user has left for someone else.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The void object.
   */
  public void respondToFeedback()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    RespondToFeedbackRequestType req;
    req = new RespondToFeedbackRequestType();

    if( this.targetUserID == null )
      throw new SdkException("TargetUserID property is not set.");
    if( this.responseType == null )
      throw new SdkException("ResponseType property is not set.");
    if( this.responseText == null )
      throw new SdkException("ResponseText property is not set.");

    if (this.feedbackID != null)
      req.setFeedbackID(this.feedbackID);
    if (this.itemID != null)
      req.setItemID(this.itemID);
    if (this.transactionID != null)
      req.setTransactionID(this.transactionID);
    if (this.targetUserID != null)
      req.setTargetUserID(this.targetUserID);
    if (this.responseType != null)
      req.setResponseType(this.responseType);
    if (this.responseText != null)
      req.setResponseText(this.responseText);

    RespondToFeedbackResponseType resp = (RespondToFeedbackResponseType) execute(req);


  }

  /**
   * Gets the RespondToFeedbackRequestType.feedbackID.
   * @return String
   */
  public String getFeedbackID()
  {
    return this.feedbackID;
  }

  /**
   * Sets the RespondToFeedbackRequestType.feedbackID.
   * @param feedbackID String
   */
  public void setFeedbackID(String feedbackID)
  {
    this.feedbackID = feedbackID;
  }

  /**
   * Gets the RespondToFeedbackRequestType.itemID.
   * @return String
   */
  public String getItemID()
  {
    return this.itemID;
  }

  /**
   * Sets the RespondToFeedbackRequestType.itemID.
   * @param itemID String
   */
  public void setItemID(String itemID)
  {
    this.itemID = itemID;
  }

  /**
   * Gets the RespondToFeedbackRequestType.responseText.
   * @return String
   */
  public String getResponseText()
  {
    return this.responseText;
  }

  /**
   * Sets the RespondToFeedbackRequestType.responseText.
   * @param responseText String
   */
  public void setResponseText(String responseText)
  {
    this.responseText = responseText;
  }

  /**
   * Gets the RespondToFeedbackRequestType.responseType.
   * @return FeedbackResponseCodeType
   */
  public FeedbackResponseCodeType getResponseType()
  {
    return this.responseType;
  }

  /**
   * Sets the RespondToFeedbackRequestType.responseType.
   * @param responseType FeedbackResponseCodeType
   */
  public void setResponseType(FeedbackResponseCodeType responseType)
  {
    this.responseType = responseType;
  }

  /**
   * Gets the RespondToFeedbackRequestType.targetUserID.
   * @return String
   */
  public String getTargetUserID()
  {
    return this.targetUserID;
  }

  /**
   * Sets the RespondToFeedbackRequestType.targetUserID.
   * @param targetUserID String
   */
  public void setTargetUserID(String targetUserID)
  {
    this.targetUserID = targetUserID;
  }

  /**
   * Gets the RespondToFeedbackRequestType.transactionID.
   * @return String
   */
  public String getTransactionID()
  {
    return this.transactionID;
  }

  /**
   * Sets the RespondToFeedbackRequestType.transactionID.
   * @param transactionID String
   */
  public void setTransactionID(String transactionID)
  {
    this.transactionID = transactionID;
  }

}

