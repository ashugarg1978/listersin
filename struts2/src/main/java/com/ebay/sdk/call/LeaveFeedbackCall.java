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
 * Wrapper class of the LeaveFeedback call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>FeedbackDetail</code> - Helper wrapper to set LeaveFeedbackRequestType CommentText, CommentType, ItemID:
 * CommentText sets LeaveFeedbackRequestType.CommentText: 
 * Textual comment that explains, clarifies, or justifies the feedback
 * score specified in CommentType.
 * CommentType sets LeaveFeedbackRequestType.CommentType: 
 * Score for the feedback being left. May be Positive, Neutral, or Negative.
 * <br><br>
 * <span class="tablenote"><b>Note:</b>
 * Sellers can not leave neutral or negative feedback for buyers. In addition,
 * buyers can not leave neutral or negative feedback within 7 days from the end of
 * the transaction for active PowerSellers who have been on eBay for 12 months.
 * </span> ;<br>
 * ItemID sets LeaveFeedbackRequestType.ItemID: 
 * The ID of an item. Specify the ID for the item of the transaction of the
 * users. The transaction must not have been created more than 60 days before
 * your attempt to leave feedback.
 * <br> <B>Input property:</B> <code>TransactionID</code> - The item purchase transaction from the listing specified in ItemID for
 * which the feedback is being left. Required if the listing identified in
 * ItemID is a multi-item fixed-price listing.
 * <span class="tablenote"><strong>Note:</strong>
 * As of version 619, Dutch-style (multi-item) competitive-bid auctions are
 * deprecated. eBay throws an error if you submit a Dutch item listing with
 * AddItem or VerifyAddItem. If you use RelistItem to update a Dutch auction
 * listing, eBay generates a warning and resets the Quantity value to 1.
 * </span>
 * <br>
 * <br> <B>Input property:</B> <code>TargetUser</code> - Specifies the recipient user about whom the feedback is being left.
 * <br> <B>Input property:</B> <code>SellerItemRatingDetailArray</code> - Container for detailed seller ratings (DSRs). If a buyer is providing DSRs,
 * they are specified in this container. Sellers have access to the number of
 * ratings they've received, as well as to the averages of the DSRs they've
 * received in each DSR area (i.e., to the average of ratings in the
 * item-description area, etc.).
 * <br> <B>Output property:</B> <code>ReturnedFeedbackID</code> - The ID of the feedback that has been left.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class LeaveFeedbackCall extends com.ebay.sdk.ApiCall
{
  
  private FeedbackDetailType feedbackDetail = null;
  private String transactionID = null;
  private String targetUser = null;
  private ItemRatingDetailArrayType sellerItemRatingDetailArray = null;
  private String returnedFeedbackID=null;


  /**
   * Constructor.
   */
  public LeaveFeedbackCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public LeaveFeedbackCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Enables a user to leave feedback about another user at the conclusion of a successful
   * sales transaction (item sold).
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The void object.
   */
  public void leaveFeedback()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    LeaveFeedbackRequestType req;
    req = new LeaveFeedbackRequestType();

    if( this.targetUser == null )
      throw new SdkException("TargetUser property is not set.");
    if( this.feedbackDetail == null )
      throw new SdkException("FeedbackDetail property is not set.");
    if( this.feedbackDetail.getItemID() == null )
      throw new SdkException("FeedbackDetail.getItemID() property is not set.");
    if( this.feedbackDetail.getCommentText() == null )
      throw new SdkException("FeedbackDetail.getCommentText() property is not set.");
    if( this.feedbackDetail.getCommentType() == null )
      throw new SdkException("FeedbackDetail.getCommentType() property is not set.");

    req.setDetailLevel(this.getDetailLevel());
    if (this.feedbackDetail != null)
    {
      req.setCommentText(this.feedbackDetail.getCommentText());
      req.setCommentType(this.feedbackDetail.getCommentType());
      req.setItemID(this.feedbackDetail.getItemID());
    }
    if (this.transactionID != null)
      req.setTransactionID(this.transactionID);
    if (this.targetUser != null)
      req.setTargetUser(this.targetUser);
    if (this.sellerItemRatingDetailArray != null)
      req.setSellerItemRatingDetailArray(this.sellerItemRatingDetailArray);

    LeaveFeedbackResponseType resp = (LeaveFeedbackResponseType) execute(req);

    this.returnedFeedbackID = resp.getFeedbackID();

  }

  /**
   * Gets the LeaveFeedbackRequestType.feedbackDetail.
   * @return FeedbackDetailType
   */
  public FeedbackDetailType getFeedbackDetail()
  {
    return this.feedbackDetail;
  }

  /**
   * Sets the LeaveFeedbackRequestType.feedbackDetail.
   * @param feedbackDetail FeedbackDetailType
   */
  public void setFeedbackDetail(FeedbackDetailType feedbackDetail)
  {
    this.feedbackDetail = feedbackDetail;
  }

  /**
   * Gets the LeaveFeedbackRequestType.sellerItemRatingDetailArray.
   * @return ItemRatingDetailArrayType
   */
  public ItemRatingDetailArrayType getSellerItemRatingDetailArray()
  {
    return this.sellerItemRatingDetailArray;
  }

  /**
   * Sets the LeaveFeedbackRequestType.sellerItemRatingDetailArray.
   * @param sellerItemRatingDetailArray ItemRatingDetailArrayType
   */
  public void setSellerItemRatingDetailArray(ItemRatingDetailArrayType sellerItemRatingDetailArray)
  {
    this.sellerItemRatingDetailArray = sellerItemRatingDetailArray;
  }

  /**
   * Gets the LeaveFeedbackRequestType.targetUser.
   * @return String
   */
  public String getTargetUser()
  {
    return this.targetUser;
  }

  /**
   * Sets the LeaveFeedbackRequestType.targetUser.
   * @param targetUser String
   */
  public void setTargetUser(String targetUser)
  {
    this.targetUser = targetUser;
  }

  /**
   * Gets the LeaveFeedbackRequestType.transactionID.
   * @return String
   */
  public String getTransactionID()
  {
    return this.transactionID;
  }

  /**
   * Sets the LeaveFeedbackRequestType.transactionID.
   * @param transactionID String
   */
  public void setTransactionID(String transactionID)
  {
    this.transactionID = transactionID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned LeaveFeedbackResponseType.returnedFeedbackID.
   * 
   * @return String
   */
  public String getReturnedFeedbackID()
  {
    return this.returnedFeedbackID;
  }

}

