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
 * Wrapper class of the GetUserPreferences call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>ShowBidderNoticePreferences</code> - If true, returns a seller's preferences for receiving bidder notices.
 * <br> <B>Input property:</B> <code>ShowCombinedPaymentPreferences</code> - DO NOT USE THIS FIELD.<br>
 * Use 'GetShippingDiscountProfiles' to access similar information.
 * <br><br>
 * If true, returns a seller's preferences for accepting payments
 * that combine item purchases into one order.
 * <br> <B>Input property:</B> <code>ShowCrossPromotionPreferences</code> - If true, returns a seller's cross-promotion preferences (such as
 * whether cross-promotions are enabled) and which sort filters they use.
 * <br> <B>Input property:</B> <code>ShowSellerPaymentPreferences</code> - If true, returns a seller's payment preferences, such as whether the seller
 * accepts PayPal, displays a Pay Now button, and so on.
 * <br> <B>Input property:</B> <code>ShowEndOfAuctionEmailPreferences</code> - If true, returns the seller's end-of-auction email preferences.
 * <br> <B>Input property:</B> <code>ShowSellerFavoriteItemPreferences</code> - If true, returns the seller's preferences for displaying items on a buyer's
 * favorite sellers page and in the favorite sellers email digest.
 * <br> <B>Input property:</B> <code>ShowProStoresPreferences</code> - If true, returns the seller's ProStores checkout preferences.
 * <br> <B>Input property:</B> <code>ShowEmailShipmentTrackingNumberPreference</code> - If true, returns the seller's preferences related to emailing shipment
 * tracking numbers.
 * <br> <B>Input property:</B> <code>ShowSellerExcludeShipToLocationPreference</code> - If true, returns a list of locations to where the seller will not ship
 * items. The returned list is the seller's default ExcludeShipToLocations
 * setting that is set in My eBay. Sellers can override these default
 * settings using ExcludeShipToLocation when they list an item.
 * <br> <B>Input property:</B> <code>ShowUnpaidItemAssistancePreference</code> - If true, returns the preference for the Unpaid Item Assistance mechanism.
 * <br> <B>Input property:</B> <code>ShowPurchaseReminderEmailPreferences</code> - If true, returns the preference for whether a purchase reminder email
 * is sent to buyers.
 * <br> <B>Output property:</B> <code>ReturnedBidderNoticePreferences</code> - Contains the seller's preferences for receiving bidder notices.<br />
 * Returned when ShowBidderNoticePreferences is set to true.
 * <br> <B>Output property:</B> <code>ReturnedCombinedPaymentPreferences</code> - DO NOT USE THIS FIELD. Use 'GetShippingDiscountProfiles' to retrieve
 * similar information.
 * <br><br>
 * Contains the seller's preferences for accepting combined payments.<br />
 * Returned when ShowCombinedPaymentPreferences is set to true.
 * <br> <B>Output property:</B> <code>ReturnedCrossPromotionPreferences</code> - Contains the seller's cross-promotion preferences.<br />
 * Returned when ShowCrossPromotionPreferences is set to true.
 * <br> <B>Output property:</B> <code>ReturnedSellerPaymentPreferences</code> - Contains the seller's preferences for accepting payments.<br />
 * Returned when ShowSellerPaymentPreferences is set to true.
 * <br> <B>Output property:</B> <code>ReturnedSellerFavoriteItemPreferences</code> - Contains the seller's preferences for displaying items on a buyer's Favorite
 * Sellers' Items page or Favorite Sellers' Items digest.<br />
 * Returned when ShowSellerFavoriteItemPreferences is set to true.
 * <br> <B>Output property:</B> <code>ReturnedEndOfAuctionEmailPreferences</code> - Contains the seller's end-of-auction email preferences.<br />
 * Returned when ShowEndOfAcutionEmailPreferences is set to true.
 * <br> <B>Output property:</B> <code>ReturnedEmailShipmentTrackingNumberPreference</code> - Contains the seller's preference for the email shipment tracking number.<br />
 * Returned when ShowEmailShipmentTrackingNumberPreference is set to true.
 * <br> <B>Output property:</B> <code>ReturnedProStoresPreference</code> - Contains the seller's preferences regarding ProStores, including the checkout.<br />
 * Returned when ShowProStoresPreference is set to true.
 * <br> <B>Output property:</B> <code>ReturnedUnpaidItemAssistancePreferences</code> - A seller's preferences for the automated Unpaid Item Assistance mechanism.
 * <br> <B>Output property:</B> <code>ReturnedSellerExcludeShipToLocationPreferences</code> - Details the seller's ExcludeShipToLocation preferences.<br />
 * Returned when ShowSellerExcludeShipToLocationPreference is set to true.
 * <br> <B>Output property:</B> <code>ReturnedPurchaseReminderEmailPreferences</code> - A seller's preference for whether a purchase reminder
 * email is sent to buyers.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class GetUserPreferencesCall extends com.ebay.sdk.ApiCall
{
  
  private Boolean showBidderNoticePreferences = null;
  private Boolean showCombinedPaymentPreferences = null;
  private Boolean showCrossPromotionPreferences = null;
  private Boolean showSellerPaymentPreferences = null;
  private Boolean showEndOfAuctionEmailPreferences = null;
  private Boolean showSellerFavoriteItemPreferences = null;
  private Boolean showProStoresPreferences = null;
  private Boolean showEmailShipmentTrackingNumberPreference = null;
  private Boolean showSellerExcludeShipToLocationPreference = null;
  private Boolean showUnpaidItemAssistancePreference = null;
  private Boolean showPurchaseReminderEmailPreferences = null;
  private BidderNoticePreferencesType returnedBidderNoticePreferences=null;
  private CombinedPaymentPreferencesType returnedCombinedPaymentPreferences=null;
  private CrossPromotionPreferencesType returnedCrossPromotionPreferences=null;
  private SellerPaymentPreferencesType returnedSellerPaymentPreferences=null;
  private SellerFavoriteItemPreferencesType returnedSellerFavoriteItemPreferences=null;
  private EndOfAuctionEmailPreferencesType returnedEndOfAuctionEmailPreferences=null;
  private Boolean returnedEmailShipmentTrackingNumberPreference=null;
  private ProStoresCheckoutPreferenceType returnedProStoresPreference=null;
  private UnpaidItemAssistancePreferencesType returnedUnpaidItemAssistancePreferences=null;
  private SellerExcludeShipToLocationPreferencesType returnedSellerExcludeShipToLocationPreferences=null;
  private PurchaseReminderEmailPreferencesType returnedPurchaseReminderEmailPreferences=null;


  /**
   * Constructor.
   */
  public GetUserPreferencesCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public GetUserPreferencesCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Retrieves the specified user preferences for the authenticated caller.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The void object.
   */
  public void getUserPreferences()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GetUserPreferencesRequestType req;
    req = new GetUserPreferencesRequestType();
    if (this.showBidderNoticePreferences != null)
      req.setShowBidderNoticePreferences(this.showBidderNoticePreferences.booleanValue());
    if (this.showCombinedPaymentPreferences != null)
      req.setShowCombinedPaymentPreferences(this.showCombinedPaymentPreferences.booleanValue());
    if (this.showCrossPromotionPreferences != null)
      req.setShowCrossPromotionPreferences(this.showCrossPromotionPreferences.booleanValue());
    if (this.showSellerPaymentPreferences != null)
      req.setShowSellerPaymentPreferences(this.showSellerPaymentPreferences.booleanValue());
    if (this.showEndOfAuctionEmailPreferences != null)
      req.setShowEndOfAuctionEmailPreferences(this.showEndOfAuctionEmailPreferences);
    if (this.showSellerFavoriteItemPreferences != null)
      req.setShowSellerFavoriteItemPreferences(this.showSellerFavoriteItemPreferences);
    if (this.showProStoresPreferences != null)
      req.setShowProStoresPreferences(this.showProStoresPreferences);
    if (this.showEmailShipmentTrackingNumberPreference != null)
      req.setShowEmailShipmentTrackingNumberPreference(this.showEmailShipmentTrackingNumberPreference);
    if (this.showSellerExcludeShipToLocationPreference != null)
      req.setShowSellerExcludeShipToLocationPreference(this.showSellerExcludeShipToLocationPreference);
    if (this.showUnpaidItemAssistancePreference != null)
      req.setShowUnpaidItemAssistancePreference(this.showUnpaidItemAssistancePreference);
    if (this.showPurchaseReminderEmailPreferences != null)
      req.setShowPurchaseReminderEmailPreferences(this.showPurchaseReminderEmailPreferences);

    GetUserPreferencesResponseType resp = (GetUserPreferencesResponseType) execute(req);

    this.returnedBidderNoticePreferences = resp.getBidderNoticePreferences();
    this.returnedCombinedPaymentPreferences = resp.getCombinedPaymentPreferences();
    this.returnedCrossPromotionPreferences = resp.getCrossPromotionPreferences();
    this.returnedSellerPaymentPreferences = resp.getSellerPaymentPreferences();
    this.returnedSellerFavoriteItemPreferences = resp.getSellerFavoriteItemPreferences();
    this.returnedEndOfAuctionEmailPreferences = resp.getEndOfAuctionEmailPreferences();
    this.returnedEmailShipmentTrackingNumberPreference = resp.isEmailShipmentTrackingNumberPreference();
    this.returnedProStoresPreference = resp.getProStoresPreference();
    this.returnedUnpaidItemAssistancePreferences = resp.getUnpaidItemAssistancePreferences();
    this.returnedSellerExcludeShipToLocationPreferences = resp.getSellerExcludeShipToLocationPreferences();
    this.returnedPurchaseReminderEmailPreferences = resp.getPurchaseReminderEmailPreferences();

  }

  /**
   * Gets the GetUserPreferencesRequestType.showBidderNoticePreferences.
   * @return Boolean
   */
  public Boolean getShowBidderNoticePreferences()
  {
    return this.showBidderNoticePreferences;
  }

  /**
   * Sets the GetUserPreferencesRequestType.showBidderNoticePreferences.
   * @param showBidderNoticePreferences Boolean
   */
  public void setShowBidderNoticePreferences(Boolean showBidderNoticePreferences)
  {
    this.showBidderNoticePreferences = showBidderNoticePreferences;
  }

  /**
   * Gets the GetUserPreferencesRequestType.showCombinedPaymentPreferences.
   * @return Boolean
   */
  public Boolean getShowCombinedPaymentPreferences()
  {
    return this.showCombinedPaymentPreferences;
  }

  /**
   * Sets the GetUserPreferencesRequestType.showCombinedPaymentPreferences.
   * @param showCombinedPaymentPreferences Boolean
   */
  public void setShowCombinedPaymentPreferences(Boolean showCombinedPaymentPreferences)
  {
    this.showCombinedPaymentPreferences = showCombinedPaymentPreferences;
  }

  /**
   * Gets the GetUserPreferencesRequestType.showCrossPromotionPreferences.
   * @return Boolean
   */
  public Boolean getShowCrossPromotionPreferences()
  {
    return this.showCrossPromotionPreferences;
  }

  /**
   * Sets the GetUserPreferencesRequestType.showCrossPromotionPreferences.
   * @param showCrossPromotionPreferences Boolean
   */
  public void setShowCrossPromotionPreferences(Boolean showCrossPromotionPreferences)
  {
    this.showCrossPromotionPreferences = showCrossPromotionPreferences;
  }

  /**
   * Gets the GetUserPreferencesRequestType.showEmailShipmentTrackingNumberPreference.
   * @return Boolean
   */
  public Boolean getShowEmailShipmentTrackingNumberPreference()
  {
    return this.showEmailShipmentTrackingNumberPreference;
  }

  /**
   * Sets the GetUserPreferencesRequestType.showEmailShipmentTrackingNumberPreference.
   * @param showEmailShipmentTrackingNumberPreference Boolean
   */
  public void setShowEmailShipmentTrackingNumberPreference(Boolean showEmailShipmentTrackingNumberPreference)
  {
    this.showEmailShipmentTrackingNumberPreference = showEmailShipmentTrackingNumberPreference;
  }

  /**
   * Gets the GetUserPreferencesRequestType.showEndOfAuctionEmailPreferences.
   * @return Boolean
   */
  public Boolean getShowEndOfAuctionEmailPreferences()
  {
    return this.showEndOfAuctionEmailPreferences;
  }

  /**
   * Sets the GetUserPreferencesRequestType.showEndOfAuctionEmailPreferences.
   * @param showEndOfAuctionEmailPreferences Boolean
   */
  public void setShowEndOfAuctionEmailPreferences(Boolean showEndOfAuctionEmailPreferences)
  {
    this.showEndOfAuctionEmailPreferences = showEndOfAuctionEmailPreferences;
  }

  /**
   * Gets the GetUserPreferencesRequestType.showProStoresPreferences.
   * @return Boolean
   */
  public Boolean getShowProStoresPreferences()
  {
    return this.showProStoresPreferences;
  }

  /**
   * Sets the GetUserPreferencesRequestType.showProStoresPreferences.
   * @param showProStoresPreferences Boolean
   */
  public void setShowProStoresPreferences(Boolean showProStoresPreferences)
  {
    this.showProStoresPreferences = showProStoresPreferences;
  }

  /**
   * Gets the GetUserPreferencesRequestType.showPurchaseReminderEmailPreferences.
   * @return Boolean
   */
  public Boolean getShowPurchaseReminderEmailPreferences()
  {
    return this.showPurchaseReminderEmailPreferences;
  }

  /**
   * Sets the GetUserPreferencesRequestType.showPurchaseReminderEmailPreferences.
   * @param showPurchaseReminderEmailPreferences Boolean
   */
  public void setShowPurchaseReminderEmailPreferences(Boolean showPurchaseReminderEmailPreferences)
  {
    this.showPurchaseReminderEmailPreferences = showPurchaseReminderEmailPreferences;
  }

  /**
   * Gets the GetUserPreferencesRequestType.showSellerExcludeShipToLocationPreference.
   * @return Boolean
   */
  public Boolean getShowSellerExcludeShipToLocationPreference()
  {
    return this.showSellerExcludeShipToLocationPreference;
  }

  /**
   * Sets the GetUserPreferencesRequestType.showSellerExcludeShipToLocationPreference.
   * @param showSellerExcludeShipToLocationPreference Boolean
   */
  public void setShowSellerExcludeShipToLocationPreference(Boolean showSellerExcludeShipToLocationPreference)
  {
    this.showSellerExcludeShipToLocationPreference = showSellerExcludeShipToLocationPreference;
  }

  /**
   * Gets the GetUserPreferencesRequestType.showSellerFavoriteItemPreferences.
   * @return Boolean
   */
  public Boolean getShowSellerFavoriteItemPreferences()
  {
    return this.showSellerFavoriteItemPreferences;
  }

  /**
   * Sets the GetUserPreferencesRequestType.showSellerFavoriteItemPreferences.
   * @param showSellerFavoriteItemPreferences Boolean
   */
  public void setShowSellerFavoriteItemPreferences(Boolean showSellerFavoriteItemPreferences)
  {
    this.showSellerFavoriteItemPreferences = showSellerFavoriteItemPreferences;
  }

  /**
   * Gets the GetUserPreferencesRequestType.showSellerPaymentPreferences.
   * @return Boolean
   */
  public Boolean getShowSellerPaymentPreferences()
  {
    return this.showSellerPaymentPreferences;
  }

  /**
   * Sets the GetUserPreferencesRequestType.showSellerPaymentPreferences.
   * @param showSellerPaymentPreferences Boolean
   */
  public void setShowSellerPaymentPreferences(Boolean showSellerPaymentPreferences)
  {
    this.showSellerPaymentPreferences = showSellerPaymentPreferences;
  }

  /**
   * Gets the GetUserPreferencesRequestType.showUnpaidItemAssistancePreference.
   * @return Boolean
   */
  public Boolean getShowUnpaidItemAssistancePreference()
  {
    return this.showUnpaidItemAssistancePreference;
  }

  /**
   * Sets the GetUserPreferencesRequestType.showUnpaidItemAssistancePreference.
   * @param showUnpaidItemAssistancePreference Boolean
   */
  public void setShowUnpaidItemAssistancePreference(Boolean showUnpaidItemAssistancePreference)
  {
    this.showUnpaidItemAssistancePreference = showUnpaidItemAssistancePreference;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetUserPreferencesResponseType.returnedBidderNoticePreferences.
   * 
   * @return BidderNoticePreferencesType
   */
  public BidderNoticePreferencesType getReturnedBidderNoticePreferences()
  {
    return this.returnedBidderNoticePreferences;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetUserPreferencesResponseType.returnedCombinedPaymentPreferences.
   * 
   * @return CombinedPaymentPreferencesType
   */
  public CombinedPaymentPreferencesType getReturnedCombinedPaymentPreferences()
  {
    return this.returnedCombinedPaymentPreferences;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetUserPreferencesResponseType.returnedCrossPromotionPreferences.
   * 
   * @return CrossPromotionPreferencesType
   */
  public CrossPromotionPreferencesType getReturnedCrossPromotionPreferences()
  {
    return this.returnedCrossPromotionPreferences;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetUserPreferencesResponseType.returnedEmailShipmentTrackingNumberPreference.
   * 
   * @return Boolean
   */
  public Boolean getReturnedEmailShipmentTrackingNumberPreference()
  {
    return this.returnedEmailShipmentTrackingNumberPreference;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetUserPreferencesResponseType.returnedEndOfAuctionEmailPreferences.
   * 
   * @return EndOfAuctionEmailPreferencesType
   */
  public EndOfAuctionEmailPreferencesType getReturnedEndOfAuctionEmailPreferences()
  {
    return this.returnedEndOfAuctionEmailPreferences;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetUserPreferencesResponseType.returnedProStoresPreference.
   * 
   * @return ProStoresCheckoutPreferenceType
   */
  public ProStoresCheckoutPreferenceType getReturnedProStoresPreference()
  {
    return this.returnedProStoresPreference;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetUserPreferencesResponseType.returnedPurchaseReminderEmailPreferences.
   * 
   * @return PurchaseReminderEmailPreferencesType
   */
  public PurchaseReminderEmailPreferencesType getReturnedPurchaseReminderEmailPreferences()
  {
    return this.returnedPurchaseReminderEmailPreferences;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetUserPreferencesResponseType.returnedSellerExcludeShipToLocationPreferences.
   * 
   * @return SellerExcludeShipToLocationPreferencesType
   */
  public SellerExcludeShipToLocationPreferencesType getReturnedSellerExcludeShipToLocationPreferences()
  {
    return this.returnedSellerExcludeShipToLocationPreferences;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetUserPreferencesResponseType.returnedSellerFavoriteItemPreferences.
   * 
   * @return SellerFavoriteItemPreferencesType
   */
  public SellerFavoriteItemPreferencesType getReturnedSellerFavoriteItemPreferences()
  {
    return this.returnedSellerFavoriteItemPreferences;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetUserPreferencesResponseType.returnedSellerPaymentPreferences.
   * 
   * @return SellerPaymentPreferencesType
   */
  public SellerPaymentPreferencesType getReturnedSellerPaymentPreferences()
  {
    return this.returnedSellerPaymentPreferences;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetUserPreferencesResponseType.returnedUnpaidItemAssistancePreferences.
   * 
   * @return UnpaidItemAssistancePreferencesType
   */
  public UnpaidItemAssistancePreferencesType getReturnedUnpaidItemAssistancePreferences()
  {
    return this.returnedUnpaidItemAssistancePreferences;
  }

}

