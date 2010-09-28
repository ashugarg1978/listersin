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
import java.util.Calendar;

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the AddDisputeResponse call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>DisputeID</code> - The unique identifier of the dispute,
 * returned when the dispute was created.
 * <br> <B>Input property:</B> <code>MessageText</code> - The text of a comment or response being posted to the dispute. Required
 * when DisputeActivity is SellerAddInformation, SellerComment, or
 * SellerPaymentNotReceived; otherwise, optional.
 * <br> <B>Input property:</B> <code>DisputeActivity</code> - The type of activity the seller is taking on the dispute.
 * The allowed value is determined by the current value of
 * DisputeState, returned by GetDispute or GetUserDisputes.
 * Some values are for Unpaid Item disputes and some are for Item
 * Not Received disputes.
 * <br> <B>Input property:</B> <code>ShippingCarrierUsed</code> - The shipping carrier used for the item in dispute. Required if DisputeActivity
 * is SellerShippedItem; otherwise, optional.
 * <br> <B>Input property:</B> <code>ShipmentTrackNumber</code> - The shipper's tracking number for the item being shipped. Required
 * if DisputeActivity is SellerShippedItem; otherwise, optional.
 * <br> <B>Input property:</B> <code>ShippingTime</code> - The date the item under dispute was shipped. Required if DisputeActivity
 * is SellerShippedItem; otherwise, optional.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class AddDisputeResponseCall extends com.ebay.sdk.ApiCall
{
  
  private String disputeID = null;
  private String messageText = null;
  private DisputeActivityCodeType disputeActivity = null;
  private String shippingCarrierUsed = null;
  private String shipmentTrackNumber = null;
  private Calendar shippingTime = null;


  /**
   * Constructor.
   */
  public AddDisputeResponseCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public AddDisputeResponseCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Allows a seller to respond to or close an Item Not Received dispute.
   * This can be used to add a comment to an Unpaid Item Dispute
   * only if the request version was lower than 637 when the
   * dispute was created.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The AddDisputeResponseResponseType object.
   */
  public AddDisputeResponseResponseType addDisputeResponse()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    AddDisputeResponseRequestType req;
    req = new AddDisputeResponseRequestType();
    if (this.disputeID != null)
      req.setDisputeID(this.disputeID);
    if (this.messageText != null)
      req.setMessageText(this.messageText);
    if (this.disputeActivity != null)
      req.setDisputeActivity(this.disputeActivity);
    if (this.shippingCarrierUsed != null)
      req.setShippingCarrierUsed(this.shippingCarrierUsed);
    if (this.shipmentTrackNumber != null)
      req.setShipmentTrackNumber(this.shipmentTrackNumber);
    if (this.shippingTime != null)
      req.setShippingTime(this.shippingTime);

    AddDisputeResponseResponseType resp = (AddDisputeResponseResponseType) execute(req);

    return resp;
  }

  /**
   * Gets the AddDisputeResponseRequestType.disputeActivity.
   * @return DisputeActivityCodeType
   */
  public DisputeActivityCodeType getDisputeActivity()
  {
    return this.disputeActivity;
  }

  /**
   * Sets the AddDisputeResponseRequestType.disputeActivity.
   * @param disputeActivity DisputeActivityCodeType
   */
  public void setDisputeActivity(DisputeActivityCodeType disputeActivity)
  {
    this.disputeActivity = disputeActivity;
  }

  /**
   * Gets the AddDisputeResponseRequestType.disputeID.
   * @return String
   */
  public String getDisputeID()
  {
    return this.disputeID;
  }

  /**
   * Sets the AddDisputeResponseRequestType.disputeID.
   * @param disputeID String
   */
  public void setDisputeID(String disputeID)
  {
    this.disputeID = disputeID;
  }

  /**
   * Gets the AddDisputeResponseRequestType.messageText.
   * @return String
   */
  public String getMessageText()
  {
    return this.messageText;
  }

  /**
   * Sets the AddDisputeResponseRequestType.messageText.
   * @param messageText String
   */
  public void setMessageText(String messageText)
  {
    this.messageText = messageText;
  }

  /**
   * Gets the AddDisputeResponseRequestType.shipmentTrackNumber.
   * @return String
   */
  public String getShipmentTrackNumber()
  {
    return this.shipmentTrackNumber;
  }

  /**
   * Sets the AddDisputeResponseRequestType.shipmentTrackNumber.
   * @param shipmentTrackNumber String
   */
  public void setShipmentTrackNumber(String shipmentTrackNumber)
  {
    this.shipmentTrackNumber = shipmentTrackNumber;
  }

  /**
   * Gets the AddDisputeResponseRequestType.shippingCarrierUsed.
   * @return String
   */
  public String getShippingCarrierUsed()
  {
    return this.shippingCarrierUsed;
  }

  /**
   * Sets the AddDisputeResponseRequestType.shippingCarrierUsed.
   * @param shippingCarrierUsed String
   */
  public void setShippingCarrierUsed(String shippingCarrierUsed)
  {
    this.shippingCarrierUsed = shippingCarrierUsed;
  }

  /**
   * Gets the AddDisputeResponseRequestType.shippingTime.
   * @return Calendar
   */
  public Calendar getShippingTime()
  {
    return this.shippingTime;
  }

  /**
   * Sets the AddDisputeResponseRequestType.shippingTime.
   * @param shippingTime Calendar
   */
  public void setShippingTime(Calendar shippingTime)
  {
    this.shippingTime = shippingTime;
  }

}

