/*
Copyright (c) 2009 eBay, Inc.

This program is licensed under the terms of the eBay Common Development and 
Distribution License (CDDL) Version 1.0 (the "License") and any subsequent 
version thereof released by eBay.  The then-current version of the License 
can be found at https://www.codebase.ebay.com/Licenses.html and in the 
eBaySDKLicense file that is under the eBay SDK install directory.
*/

package com.ebay.sdk.call;

import java.lang.Boolean;

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the SetUserPreferences call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>BidderNoticePreferences</code> - The user's bidder notice preferences to be set.
 * <br> <B>Input property:</B> <code>CombinedPaymentPreferences</code> - DO NOT USE THIS FIELD.
 * <br>
 * Instead, use SetShippingDiscountProfiles to set the shipping preferences.
 * <br><br>
 * The user's combined payment preferences to be set. When you change these
 * preferences, it can take up to 7 days for the change to have any logical or
 * functional effect on eBay.
 * <br> <B>Input property:</B> <code>CrossPromotionPreferences</code> - Sets the user's cross promotion preferences.
 * <br> <B>Input property:</B> <code>SellerPaymentPreferences</code> - Sets the user's seller payment preferences.
 * <br> <B>Input property:</B> <code>SellerFavoriteItemPreferences</code> - Ssets the seller's favorite item preferences.
 * <br> <B>Input property:</B> <code>EndOfAuctionEmailPreferences</code> - Sets the seller's end of auction email preferences.
 * <br> <B>Input property:</B> <code>EmailShipmentTrackingNumberPreference</code> - Sets the preference for the email shipment tracking number.
 * <br> <B>Input property:</B> <code>UnpaidItemAssistancePreferences</code> - Sets the preference for the Unpaid Item Assistance mechanism.
 * <br> <B>Input property:</B> <code>PurchaseReminderEmailPreferences</code> - Sets the preference for whether a purchase reminder email is sent to buyers.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class SetUserPreferencesCall extends com.ebay.sdk.ApiCall
{
  
  private BidderNoticePreferencesType bidderNoticePreferences = null;
  private CombinedPaymentPreferencesType combinedPaymentPreferences = null;
  private CrossPromotionPreferencesType crossPromotionPreferences = null;
  private SellerPaymentPreferencesType sellerPaymentPreferences = null;
  private SellerFavoriteItemPreferencesType sellerFavoriteItemPreferences = null;
  private EndOfAuctionEmailPreferencesType endOfAuctionEmailPreferences = null;
  private Boolean emailShipmentTrackingNumberPreference = null;
  private UnpaidItemAssistancePreferencesType unpaidItemAssistancePreferences = null;
  private PurchaseReminderEmailPreferencesType purchaseReminderEmailPreferences = null;


  /**
   * Constructor.
   */
  public SetUserPreferencesCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public SetUserPreferencesCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Sets the authenticated user's preferences to those specified in the request.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The void object.
   */
  public void setUserPreferences()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    SetUserPreferencesRequestType req;
    req = new SetUserPreferencesRequestType();
    if (this.bidderNoticePreferences != null)
      req.setBidderNoticePreferences(this.bidderNoticePreferences);
    if (this.combinedPaymentPreferences != null)
      req.setCombinedPaymentPreferences(this.combinedPaymentPreferences);
    if (this.crossPromotionPreferences != null)
      req.setCrossPromotionPreferences(this.crossPromotionPreferences);
    if (this.sellerPaymentPreferences != null)
      req.setSellerPaymentPreferences(this.sellerPaymentPreferences);
    if (this.sellerFavoriteItemPreferences != null)
      req.setSellerFavoriteItemPreferences(this.sellerFavoriteItemPreferences);
    if (this.endOfAuctionEmailPreferences != null)
      req.setEndOfAuctionEmailPreferences(this.endOfAuctionEmailPreferences);
    if (this.emailShipmentTrackingNumberPreference != null)
      req.setEmailShipmentTrackingNumberPreference(this.emailShipmentTrackingNumberPreference);
    if (this.unpaidItemAssistancePreferences != null)
      req.setUnpaidItemAssistancePreferences(this.unpaidItemAssistancePreferences);
    if (this.purchaseReminderEmailPreferences != null)
      req.setPurchaseReminderEmailPreferences(this.purchaseReminderEmailPreferences);

    SetUserPreferencesResponseType resp = (SetUserPreferencesResponseType) execute(req);


  }

  /**
   * Gets the SetUserPreferencesRequestType.bidderNoticePreferences.
   * @return BidderNoticePreferencesType
   */
  public BidderNoticePreferencesType getBidderNoticePreferences()
  {
    return this.bidderNoticePreferences;
  }

  /**
   * Sets the SetUserPreferencesRequestType.bidderNoticePreferences.
   * @param bidderNoticePreferences BidderNoticePreferencesType
   */
  public void setBidderNoticePreferences(BidderNoticePreferencesType bidderNoticePreferences)
  {
    this.bidderNoticePreferences = bidderNoticePreferences;
  }

  /**
   * Gets the SetUserPreferencesRequestType.combinedPaymentPreferences.
   * @return CombinedPaymentPreferencesType
   */
  public CombinedPaymentPreferencesType getCombinedPaymentPreferences()
  {
    return this.combinedPaymentPreferences;
  }

  /**
   * Sets the SetUserPreferencesRequestType.combinedPaymentPreferences.
   * @param combinedPaymentPreferences CombinedPaymentPreferencesType
   */
  public void setCombinedPaymentPreferences(CombinedPaymentPreferencesType combinedPaymentPreferences)
  {
    this.combinedPaymentPreferences = combinedPaymentPreferences;
  }

  /**
   * Gets the SetUserPreferencesRequestType.crossPromotionPreferences.
   * @return CrossPromotionPreferencesType
   */
  public CrossPromotionPreferencesType getCrossPromotionPreferences()
  {
    return this.crossPromotionPreferences;
  }

  /**
   * Sets the SetUserPreferencesRequestType.crossPromotionPreferences.
   * @param crossPromotionPreferences CrossPromotionPreferencesType
   */
  public void setCrossPromotionPreferences(CrossPromotionPreferencesType crossPromotionPreferences)
  {
    this.crossPromotionPreferences = crossPromotionPreferences;
  }

  /**
   * Gets the SetUserPreferencesRequestType.emailShipmentTrackingNumberPreference.
   * @return Boolean
   */
  public Boolean getEmailShipmentTrackingNumberPreference()
  {
    return this.emailShipmentTrackingNumberPreference;
  }

  /**
   * Sets the SetUserPreferencesRequestType.emailShipmentTrackingNumberPreference.
   * @param emailShipmentTrackingNumberPreference Boolean
   */
  public void setEmailShipmentTrackingNumberPreference(Boolean emailShipmentTrackingNumberPreference)
  {
    this.emailShipmentTrackingNumberPreference = emailShipmentTrackingNumberPreference;
  }

  /**
   * Gets the SetUserPreferencesRequestType.endOfAuctionEmailPreferences.
   * @return EndOfAuctionEmailPreferencesType
   */
  public EndOfAuctionEmailPreferencesType getEndOfAuctionEmailPreferences()
  {
    return this.endOfAuctionEmailPreferences;
  }

  /**
   * Sets the SetUserPreferencesRequestType.endOfAuctionEmailPreferences.
   * @param endOfAuctionEmailPreferences EndOfAuctionEmailPreferencesType
   */
  public void setEndOfAuctionEmailPreferences(EndOfAuctionEmailPreferencesType endOfAuctionEmailPreferences)
  {
    this.endOfAuctionEmailPreferences = endOfAuctionEmailPreferences;
  }

  /**
   * Gets the SetUserPreferencesRequestType.purchaseReminderEmailPreferences.
   * @return PurchaseReminderEmailPreferencesType
   */
  public PurchaseReminderEmailPreferencesType getPurchaseReminderEmailPreferences()
  {
    return this.purchaseReminderEmailPreferences;
  }

  /**
   * Sets the SetUserPreferencesRequestType.purchaseReminderEmailPreferences.
   * @param purchaseReminderEmailPreferences PurchaseReminderEmailPreferencesType
   */
  public void setPurchaseReminderEmailPreferences(PurchaseReminderEmailPreferencesType purchaseReminderEmailPreferences)
  {
    this.purchaseReminderEmailPreferences = purchaseReminderEmailPreferences;
  }

  /**
   * Gets the SetUserPreferencesRequestType.sellerFavoriteItemPreferences.
   * @return SellerFavoriteItemPreferencesType
   */
  public SellerFavoriteItemPreferencesType getSellerFavoriteItemPreferences()
  {
    return this.sellerFavoriteItemPreferences;
  }

  /**
   * Sets the SetUserPreferencesRequestType.sellerFavoriteItemPreferences.
   * @param sellerFavoriteItemPreferences SellerFavoriteItemPreferencesType
   */
  public void setSellerFavoriteItemPreferences(SellerFavoriteItemPreferencesType sellerFavoriteItemPreferences)
  {
    this.sellerFavoriteItemPreferences = sellerFavoriteItemPreferences;
  }

  /**
   * Gets the SetUserPreferencesRequestType.sellerPaymentPreferences.
   * @return SellerPaymentPreferencesType
   */
  public SellerPaymentPreferencesType getSellerPaymentPreferences()
  {
    return this.sellerPaymentPreferences;
  }

  /**
   * Sets the SetUserPreferencesRequestType.sellerPaymentPreferences.
   * @param sellerPaymentPreferences SellerPaymentPreferencesType
   */
  public void setSellerPaymentPreferences(SellerPaymentPreferencesType sellerPaymentPreferences)
  {
    this.sellerPaymentPreferences = sellerPaymentPreferences;
  }

  /**
   * Gets the SetUserPreferencesRequestType.unpaidItemAssistancePreferences.
   * @return UnpaidItemAssistancePreferencesType
   */
  public UnpaidItemAssistancePreferencesType getUnpaidItemAssistancePreferences()
  {
    return this.unpaidItemAssistancePreferences;
  }

  /**
   * Sets the SetUserPreferencesRequestType.unpaidItemAssistancePreferences.
   * @param unpaidItemAssistancePreferences UnpaidItemAssistancePreferencesType
   */
  public void setUnpaidItemAssistancePreferences(UnpaidItemAssistancePreferencesType unpaidItemAssistancePreferences)
  {
    this.unpaidItemAssistancePreferences = unpaidItemAssistancePreferences;
  }

}

