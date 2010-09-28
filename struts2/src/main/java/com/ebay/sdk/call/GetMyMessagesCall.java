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
import java.util.Calendar;

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the GetMyMessages call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>AlertIDs</code> - This field will be deprecated in an upcoming release.
 * This field formerly contained a list
 * of up to 10 AlertID values.
 * <br> <B>Input property:</B> <code>MessageIDs</code> - Contains a list of up to 10 message ID values.
 * <br> <B>Input property:</B> <code>FolderID</code> - An ID that uniquely identifies the
 * My Messages folder from which to retrieve messages.
 * <br> <B>Input property:</B> <code>StartTime</code> - The beginning of the date-range filter.
 * Filtering takes into account the entire timestamp of when messages were sent.
 * Messages expire after one year.
 * <br> <B>Input property:</B> <code>EndTime</code> - The end of the date-range filter. See StartTime
 * (which is the beginning of the date-range filter).
 * <br> <B>Input property:</B> <code>ExternalMessageIDs</code> - This field is currently available on the US site. A container for IDs that
 * uniquely identify messages for a given user. If provided at the time of message
 * creation, this ID can be used to retrieve messages and will take precedence
 * over message ID.
 * <br> <B>Output property:</B> <code>ReturnedSummary</code> - Summary data for a given user's
 * messages. This includes the numbers of
 * new messages, flagged messages, and total messages.
 * The amount and type of data returned is the same
 * whether or not the request includes specific
 * MessageIDs.
 * Always/Conditionally returned logic assumes a
 * detail level of ReturnMessages.
 * <br> <B>Output property:</B> <code>ReturnedAlerts</code> - This field will be deprecated in an upcoming release.
 * This field formerly contained the alert information for each alert
 * specified in AlertIDs.
 * <br> <B>Output property:</B> <code>ReturnedMyMessages</code> - Contains the message information for each
 * message specified in MessageIDs. The amount and
 * type of information returned varies based on the
 * requested detail level. Contains one
 * MyMessagesMessageType object per message.
 * Returned as an empty node if user has no
 * messages.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class GetMyMessagesCall extends com.ebay.sdk.ApiCall
{
  
  private String[] alertIDs = null;
  private String[] messageIDs = null;
  private long folderID = 0;
  private Calendar startTime = null;
  private Calendar endTime = null;
  private MyMessagesExternalMessageIDArrayType externalMessageIDs = null;
  private MyMessagesSummaryType returnedSummary=null;
  private MyMessagesAlertType[] returnedAlerts=null;
  private MyMessagesMessageType[] returnedMyMessages=null;


  /**
   * Constructor.
   */
  public GetMyMessagesCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public GetMyMessagesCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Retrieves information about the messages sent to a given user.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The void object.
   */
  public void getMyMessages()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GetMyMessagesRequestType req;
    req = new GetMyMessagesRequestType();
    if (this.alertIDs != null)
    {
      MyMessagesAlertIDArrayType ary = new MyMessagesAlertIDArrayType();
      ary.setAlertID(this.alertIDs);
      req.setAlertIDs(ary);
    }
    if (this.messageIDs != null)
    {
      MyMessagesMessageIDArrayType ary = new MyMessagesMessageIDArrayType();
      ary.setMessageID(this.messageIDs);
      req.setMessageIDs(ary);
    }
    if (this.folderID != 0)
      req.setFolderID(new Long(this.folderID));
    if (this.startTime != null)
      req.setStartTime(this.startTime);
    if (this.endTime != null)
      req.setEndTime(this.endTime);
    if (this.externalMessageIDs != null)
      req.setExternalMessageIDs(this.externalMessageIDs);

    GetMyMessagesResponseType resp = (GetMyMessagesResponseType) execute(req);

    this.returnedSummary = resp.getSummary();
    this.returnedAlerts = (resp.getAlerts() == null? null: resp.getAlerts().getAlert());
    this.returnedMyMessages = (resp.getMessages() == null? null: resp.getMessages().getMessage());

  }

  /**
   * Gets the GetMyMessagesRequestType.alertIDs.
   * @return String[]
   */
  public String[] getAlertIDs()
  {
    return this.alertIDs;
  }

  /**
   * Sets the GetMyMessagesRequestType.alertIDs.
   * @param alertIDs String[]
   */
  public void setAlertIDs(String[] alertIDs)
  {
    this.alertIDs = alertIDs;
  }

  /**
   * Gets the GetMyMessagesRequestType.endTime.
   * @return Calendar
   */
  public Calendar getEndTime()
  {
    return this.endTime;
  }

  /**
   * Sets the GetMyMessagesRequestType.endTime.
   * @param endTime Calendar
   */
  public void setEndTime(Calendar endTime)
  {
    this.endTime = endTime;
  }

  /**
   * Gets the GetMyMessagesRequestType.externalMessageIDs.
   * @return MyMessagesExternalMessageIDArrayType
   */
  public MyMessagesExternalMessageIDArrayType getExternalMessageIDs()
  {
    return this.externalMessageIDs;
  }

  /**
   * Sets the GetMyMessagesRequestType.externalMessageIDs.
   * @param externalMessageIDs MyMessagesExternalMessageIDArrayType
   */
  public void setExternalMessageIDs(MyMessagesExternalMessageIDArrayType externalMessageIDs)
  {
    this.externalMessageIDs = externalMessageIDs;
  }

  /**
   * Gets the GetMyMessagesRequestType.folderID.
   * @return long
   */
  public long getFolderID()
  {
    return this.folderID;
  }

  /**
   * Sets the GetMyMessagesRequestType.folderID.
   * @param folderID long
   */
  public void setFolderID(long folderID)
  {
    this.folderID = folderID;
  }

  /**
   * Gets the GetMyMessagesRequestType.messageIDs.
   * @return String[]
   */
  public String[] getMessageIDs()
  {
    return this.messageIDs;
  }

  /**
   * Sets the GetMyMessagesRequestType.messageIDs.
   * @param messageIDs String[]
   */
  public void setMessageIDs(String[] messageIDs)
  {
    this.messageIDs = messageIDs;
  }

  /**
   * Gets the GetMyMessagesRequestType.startTime.
   * @return Calendar
   */
  public Calendar getStartTime()
  {
    return this.startTime;
  }

  /**
   * Sets the GetMyMessagesRequestType.startTime.
   * @param startTime Calendar
   */
  public void setStartTime(Calendar startTime)
  {
    this.startTime = startTime;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetMyMessagesResponseType.returnedAlerts.
   * 
   * @return MyMessagesAlertType[]
   */
  public MyMessagesAlertType[] getReturnedAlerts()
  {
    return this.returnedAlerts;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetMyMessagesResponseType.returnedMyMessages.
   * 
   * @return MyMessagesMessageType[]
   */
  public MyMessagesMessageType[] getReturnedMyMessages()
  {
    return this.returnedMyMessages;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetMyMessagesResponseType.returnedSummary.
   * 
   * @return MyMessagesSummaryType
   */
  public MyMessagesSummaryType getReturnedSummary()
  {
    return this.returnedSummary;
  }

}

