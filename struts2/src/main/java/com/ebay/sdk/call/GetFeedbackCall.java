/*
Copyright (c) 2009 eBay, Inc.

This program is licensed under the terms of the eBay Common Development and 
Distribution License (CDDL) Version 1.0 (the "License") and any subsequent 
version thereof released by eBay.  The then-current version of the License 
can be found at https://www.codebase.ebay.com/Licenses.html and in the 
eBaySDKLicense file that is under the eBay SDK install directory.
*/

package com.ebay.sdk.call;

import java.lang.Integer;
import java.lang.String;

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the GetFeedback call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>UserID</code> - Specifies the user whose feedback data is to be returned. If not specified,
 * then the feedback returned is for the requesting user.
 * <br> <B>Input property:</B> <code>FeedbackID</code> - An ID that uniquely identifies a feedback record to be retrieved.
 * Used only by the Feedback notification.
 * <br> <B>Input property:</B> <code>ItemID</code> - The ID of the item that you want feedback data about. If not specified,
 * feedback for all items is returned. Note that when you specify this ItemID
 * field, the response is also changed as follows: any FeedbackType filter you
 * specify is ignored; the maximum number of feedback records returned is 100; and
 * the Pagination input fields are ignored.
 * <br> <B>Input property:</B> <code>TransactionID</code> - Transaction ID whose feedback record you want to inspect. If not specified,
 * then the feedback for all transactions are returned. When a Transaction ID
 * is specified, since only one record is returned, pagination values are ignored.
 * <br> <B>Input property:</B> <code>CommentType</code> - Returns feedback of a specified type (positive, negative, or neutral) in a
 * FeedbackDetailArray. You can include two CommentTypes in your call if you want
 * to exclude the third type from your results. If no CommentType is specified,
 * all of the feedback types are returned.
 * <br> <B>Input property:</B> <code>FeedbackType</code> - Returns feedback that you received as a buyer or seller, or feedback you left
 * for others (as either a buyer or a seller). You can include only one
 * FeedbackType in your call. If no FeedbackType is specified, all of the
 * available feedback is returned.
 * <br> <B>Input property:</B> <code>Pagination</code> - Controls the pagination of the result set. Child elements, EntriesPerPage and
 * PageNumber, specify the maximum number of individual feedback records to return
 * per call and which page of data to return. Only applicable if DetailLevel is
 * set to ReturnAll and the call is returning feedback for a User ID. Feedback
 * summary data is not paginated, but when pagination is used, it is returned
 * after the last feedback detail entry.
 * <br><br>
 * Valid Pagination.EntriesPerPage input for GetFeedback is limited to 25 (the
 * default), 50, 100, and 200. If you specify a value of zero or less, or a value
 * greater than 200, the call fails with an error. If you specify a value between
 * one and twenty-four, the value is rounded up to 25. Values between 26 and 199
 * that are not one of the accepted values are rounded down to the nearest
 * accepted value.
 * <br> <B>Output property:</B> <code>ReturnedFeedbackDetails</code> - Contains the individual feedback records for the user, transaction, or
 * item is specified in the request. There is one FeedbackDetailType
 * object for each feedback record. Only populated with data when a detail level of ReturnAll is specified in the request. Not returned if you specify FeedbackID in the request.
 * <br> <B>Output property:</B> <code>GrandTotal</code> - Indicates the number of FeedbackDetailType objects returned in the
 * FeedbackDetailArray property. Only applicable if feedback details are
 * returned.
 * <br> <B>Output property:</B> <code>FeedbackSummary</code> - Summary feedback data for the user. Contains counts of positive, neutral,
 * and negative feedback for predefined time periods. Only applicable if feedback details are returned.
 * <br> <B>Output property:</B> <code>FeedbackScore</code> - Indicates the total feedback score for the user.
 * <br> <B>Output property:</B> <code>ReturnedPaginationResult</code> - Contains information regarding the pagination of data (if pagination is
 * used), including total number of pages and total number of entries. This
 * is only applicable when a User ID or no ID (requester option) is specified.
 * <br> <B>Output property:</B> <code>ReturnedEntriesPerPage</code> - Indicates the number of entries (feedback detail) that are being 
 * returned per page of data (i.e., per call).  
 * Only returned if entries are returned.
 * <br> <B>Output property:</B> <code>ReturnedPageNumber</code> - Indicates which page of data was just returned. Will be the same as the
 * value specified in Pagination.PageNumber. (If the input is
 * higher than the total number of pages, the call fails with an error.)
 * Only returned if items are returned.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class GetFeedbackCall extends com.ebay.sdk.ApiCall
{
  
  private String userID = null;
  private String feedbackID = null;
  private String itemID = null;
  private String transactionID = null;
  private CommentTypeCodeType[] commentType = null;
  private FeedbackTypeCodeType feedbackType = null;
  private PaginationType pagination = null;
  private FeedbackDetailType[] returnedFeedbackDetails=null;
  private int grandTotal=0;
  private FeedbackSummaryType feedbackSummary=null;
  private int feedbackScore=0;
  private PaginationResultType returnedPaginationResult=null;
  private Integer returnedEntriesPerPage=null;
  private Integer returnedPageNumber=null;


  /**
   * Constructor.
   */
  public GetFeedbackCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public GetFeedbackCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Retrieves the accumulated feedback left for a specified user, or the summary
   * feedback data for a specific transaction or item.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The FeedbackDetailType[] object.
   */
  public FeedbackDetailType[] getFeedback()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GetFeedbackRequestType req;
    req = new GetFeedbackRequestType();

    if( this.userID == null )
      throw new SdkException("UserID property is not set.");

    req.setDetailLevel(this.getDetailLevel());
    if (this.userID != null)
      req.setUserID(this.userID);
    if (this.feedbackID != null)
      req.setFeedbackID(this.feedbackID);
    if (this.itemID != null)
      req.setItemID(this.itemID);
    if (this.transactionID != null)
      req.setTransactionID(this.transactionID);
    if (this.commentType != null)
      req.setCommentType(this.commentType);
    if (this.feedbackType != null)
      req.setFeedbackType(this.feedbackType);
    if (this.pagination != null)
      req.setPagination(this.pagination);

    GetFeedbackResponseType resp = (GetFeedbackResponseType) execute(req);

    this.returnedFeedbackDetails = (resp.getFeedbackDetailArray() == null? null: resp.getFeedbackDetailArray().getFeedbackDetail());
    this.grandTotal = (resp.getFeedbackDetailItemTotal() == null? 0: resp.getFeedbackDetailItemTotal().intValue());
    this.feedbackSummary = resp.getFeedbackSummary();
    this.feedbackScore = (resp.getFeedbackScore() == null? 0: resp.getFeedbackScore().intValue());
    this.returnedPaginationResult = resp.getPaginationResult();
    this.returnedEntriesPerPage = resp.getEntriesPerPage();
    this.returnedPageNumber = resp.getPageNumber();
    return this.getReturnedFeedbackDetails();
  }

  /**
   * Gets the GetFeedbackRequestType.commentType.
   * @return CommentTypeCodeType[]
   */
  public CommentTypeCodeType[] getCommentType()
  {
    return this.commentType;
  }

  /**
   * Sets the GetFeedbackRequestType.commentType.
   * @param commentType CommentTypeCodeType[]
   */
  public void setCommentType(CommentTypeCodeType[] commentType)
  {
    this.commentType = commentType;
  }

  /**
   * Gets the GetFeedbackRequestType.feedbackID.
   * @return String
   */
  public String getFeedbackID()
  {
    return this.feedbackID;
  }

  /**
   * Sets the GetFeedbackRequestType.feedbackID.
   * @param feedbackID String
   */
  public void setFeedbackID(String feedbackID)
  {
    this.feedbackID = feedbackID;
  }

  /**
   * Gets the GetFeedbackRequestType.feedbackType.
   * @return FeedbackTypeCodeType
   */
  public FeedbackTypeCodeType getFeedbackType()
  {
    return this.feedbackType;
  }

  /**
   * Sets the GetFeedbackRequestType.feedbackType.
   * @param feedbackType FeedbackTypeCodeType
   */
  public void setFeedbackType(FeedbackTypeCodeType feedbackType)
  {
    this.feedbackType = feedbackType;
  }

  /**
   * Gets the GetFeedbackRequestType.itemID.
   * @return String
   */
  public String getItemID()
  {
    return this.itemID;
  }

  /**
   * Sets the GetFeedbackRequestType.itemID.
   * @param itemID String
   */
  public void setItemID(String itemID)
  {
    this.itemID = itemID;
  }

  /**
   * Gets the GetFeedbackRequestType.pagination.
   * @return PaginationType
   */
  public PaginationType getPagination()
  {
    return this.pagination;
  }

  /**
   * Sets the GetFeedbackRequestType.pagination.
   * @param pagination PaginationType
   */
  public void setPagination(PaginationType pagination)
  {
    this.pagination = pagination;
  }

  /**
   * Gets the GetFeedbackRequestType.transactionID.
   * @return String
   */
  public String getTransactionID()
  {
    return this.transactionID;
  }

  /**
   * Sets the GetFeedbackRequestType.transactionID.
   * @param transactionID String
   */
  public void setTransactionID(String transactionID)
  {
    this.transactionID = transactionID;
  }

  /**
   * Gets the GetFeedbackRequestType.userID.
   * @return String
   */
  public String getUserID()
  {
    return this.userID;
  }

  /**
   * Sets the GetFeedbackRequestType.userID.
   * @param userID String
   */
  public void setUserID(String userID)
  {
    this.userID = userID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetFeedbackResponseType.feedbackScore.
   * 
   * @return int
   */
  public int getFeedbackScore()
  {
    return this.feedbackScore;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetFeedbackResponseType.feedbackSummary.
   * 
   * @return FeedbackSummaryType
   */
  public FeedbackSummaryType getFeedbackSummary()
  {
    return this.feedbackSummary;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetFeedbackResponseType.grandTotal.
   * 
   * @return int
   */
  public int getGrandTotal()
  {
    return this.grandTotal;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetFeedbackResponseType.returnedEntriesPerPage.
   * 
   * @return Integer
   */
  public Integer getReturnedEntriesPerPage()
  {
    return this.returnedEntriesPerPage;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetFeedbackResponseType.returnedFeedbackDetails.
   * 
   * @return FeedbackDetailType[]
   */
  public FeedbackDetailType[] getReturnedFeedbackDetails()
  {
    return this.returnedFeedbackDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetFeedbackResponseType.returnedPageNumber.
   * 
   * @return Integer
   */
  public Integer getReturnedPageNumber()
  {
    return this.returnedPageNumber;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetFeedbackResponseType.returnedPaginationResult.
   * 
   * @return PaginationResultType
   */
  public PaginationResultType getReturnedPaginationResult()
  {
    return this.returnedPaginationResult;
  }

}

