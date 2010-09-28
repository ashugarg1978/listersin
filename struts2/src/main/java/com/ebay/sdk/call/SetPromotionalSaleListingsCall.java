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
import java.lang.Long;

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the SetPromotionalSaleListings call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>PromotionalSaleID</code> - The ID of the promotional sale that you want to add listings to or
 * delete listings from.
 * <br> <B>Input property:</B> <code>Action</code> - You must specify either Add or Delete.
 * <br><br>
 * This field determines whether you are adding listings to, or deleting listings from, the
 * promotional sale you specify in the PromotionalSaleID field.
 * <br><br>
 * If you specify Delete, you must specify PromotionalSaleItemIDArray. Delete is applicable
 * only in cases where you specify PromotionalSaleItemIDArray. Auction or auction/BIN format
 * listings cannot be added to or deleted from a promotional sale if the item has bids.
 * <br> <B>Input property:</B> <code>PromotionalSaleItemIDArray</code> - The IDs of the item listings to be affected
 * by the action you specify in the Action field.
 * <br> <B>Input property:</B> <code>StoreCategoryID</code> - Adds to the promotional sale all the seller's item listings with
 * the StoreCategoryID specified in this field.
 * Requires that you specify Add in the Action field.
 * <br> <B>Input property:</B> <code>CategoryID</code> - Adds to the promotional sale all the seller's item listings with
 * the CategoryID specified in this field.
 * Requires that you specify Add in the Action field.
 * <br> <B>Input property:</B> <code>AllFixedPriceItems</code> - Adds to the promotional sale all the seller's item listings
 * that are fixed price items.
 * Requires that you specify Add in the Action field.
 * <br> <B>Input property:</B> <code>AllStoreInventoryItems</code> - Adds to the promotional sale all the seller's item listings
 * that are store inventory items.
 * Requires that you specify Add in the Action field.
 * <br> <B>Input property:</B> <code>AllAuctionItems</code> - Adds to the promotional sale all the seller's item listings
 * that are auction items. Auction and auction/BIN format listings
 * can be added to free shipping sales only.
 * Requires that you specify Add in the Action field.
 * <br> <B>Output property:</B> <code>ReturnedStatus</code> - Contains the status of a promotional sale.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class SetPromotionalSaleListingsCall extends com.ebay.sdk.ApiCall
{
  
  private Long promotionalSaleID = null;
  private ModifyActionCodeType action = null;
  private ItemIDArrayType promotionalSaleItemIDArray = null;
  private Long storeCategoryID = null;
  private Long categoryID = null;
  private Boolean allFixedPriceItems = null;
  private Boolean allStoreInventoryItems = null;
  private Boolean allAuctionItems = null;
  private PromotionalSaleStatusCodeType returnedStatus=null;


  /**
   * Constructor.
   */
  public SetPromotionalSaleListingsCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public SetPromotionalSaleListingsCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Changes which item listings are affected by a promotional sale.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The PromotionalSaleStatusCodeType object.
   */
  public PromotionalSaleStatusCodeType setPromotionalSaleListings()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    SetPromotionalSaleListingsRequestType req;
    req = new SetPromotionalSaleListingsRequestType();
    if (this.promotionalSaleID != null)
      req.setPromotionalSaleID(this.promotionalSaleID);
    if (this.action != null)
      req.setAction(this.action);
    if (this.promotionalSaleItemIDArray != null)
      req.setPromotionalSaleItemIDArray(this.promotionalSaleItemIDArray);
    if (this.storeCategoryID != null)
      req.setStoreCategoryID(this.storeCategoryID);
    if (this.categoryID != null)
      req.setCategoryID(this.categoryID);
    if (this.allFixedPriceItems != null)
      req.setAllFixedPriceItems(this.allFixedPriceItems);
    if (this.allStoreInventoryItems != null)
      req.setAllStoreInventoryItems(this.allStoreInventoryItems);
    if (this.allAuctionItems != null)
      req.setAllAuctionItems(this.allAuctionItems);

    SetPromotionalSaleListingsResponseType resp = (SetPromotionalSaleListingsResponseType) execute(req);

    this.returnedStatus = resp.getStatus();
    return this.getReturnedStatus();
  }

  /**
   * Gets the SetPromotionalSaleListingsRequestType.action.
   * @return ModifyActionCodeType
   */
  public ModifyActionCodeType getAction()
  {
    return this.action;
  }

  /**
   * Sets the SetPromotionalSaleListingsRequestType.action.
   * @param action ModifyActionCodeType
   */
  public void setAction(ModifyActionCodeType action)
  {
    this.action = action;
  }

  /**
   * Gets the SetPromotionalSaleListingsRequestType.allAuctionItems.
   * @return Boolean
   */
  public Boolean getAllAuctionItems()
  {
    return this.allAuctionItems;
  }

  /**
   * Sets the SetPromotionalSaleListingsRequestType.allAuctionItems.
   * @param allAuctionItems Boolean
   */
  public void setAllAuctionItems(Boolean allAuctionItems)
  {
    this.allAuctionItems = allAuctionItems;
  }

  /**
   * Gets the SetPromotionalSaleListingsRequestType.allFixedPriceItems.
   * @return Boolean
   */
  public Boolean getAllFixedPriceItems()
  {
    return this.allFixedPriceItems;
  }

  /**
   * Sets the SetPromotionalSaleListingsRequestType.allFixedPriceItems.
   * @param allFixedPriceItems Boolean
   */
  public void setAllFixedPriceItems(Boolean allFixedPriceItems)
  {
    this.allFixedPriceItems = allFixedPriceItems;
  }

  /**
   * Gets the SetPromotionalSaleListingsRequestType.allStoreInventoryItems.
   * @return Boolean
   */
  public Boolean getAllStoreInventoryItems()
  {
    return this.allStoreInventoryItems;
  }

  /**
   * Sets the SetPromotionalSaleListingsRequestType.allStoreInventoryItems.
   * @param allStoreInventoryItems Boolean
   */
  public void setAllStoreInventoryItems(Boolean allStoreInventoryItems)
  {
    this.allStoreInventoryItems = allStoreInventoryItems;
  }

  /**
   * Gets the SetPromotionalSaleListingsRequestType.categoryID.
   * @return Long
   */
  public Long getCategoryID()
  {
    return this.categoryID;
  }

  /**
   * Sets the SetPromotionalSaleListingsRequestType.categoryID.
   * @param categoryID Long
   */
  public void setCategoryID(Long categoryID)
  {
    this.categoryID = categoryID;
  }

  /**
   * Gets the SetPromotionalSaleListingsRequestType.promotionalSaleID.
   * @return Long
   */
  public Long getPromotionalSaleID()
  {
    return this.promotionalSaleID;
  }

  /**
   * Sets the SetPromotionalSaleListingsRequestType.promotionalSaleID.
   * @param promotionalSaleID Long
   */
  public void setPromotionalSaleID(Long promotionalSaleID)
  {
    this.promotionalSaleID = promotionalSaleID;
  }

  /**
   * Gets the SetPromotionalSaleListingsRequestType.promotionalSaleItemIDArray.
   * @return ItemIDArrayType
   */
  public ItemIDArrayType getPromotionalSaleItemIDArray()
  {
    return this.promotionalSaleItemIDArray;
  }

  /**
   * Sets the SetPromotionalSaleListingsRequestType.promotionalSaleItemIDArray.
   * @param promotionalSaleItemIDArray ItemIDArrayType
   */
  public void setPromotionalSaleItemIDArray(ItemIDArrayType promotionalSaleItemIDArray)
  {
    this.promotionalSaleItemIDArray = promotionalSaleItemIDArray;
  }

  /**
   * Gets the SetPromotionalSaleListingsRequestType.storeCategoryID.
   * @return Long
   */
  public Long getStoreCategoryID()
  {
    return this.storeCategoryID;
  }

  /**
   * Sets the SetPromotionalSaleListingsRequestType.storeCategoryID.
   * @param storeCategoryID Long
   */
  public void setStoreCategoryID(Long storeCategoryID)
  {
    this.storeCategoryID = storeCategoryID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned SetPromotionalSaleListingsResponseType.returnedStatus.
   * 
   * @return PromotionalSaleStatusCodeType
   */
  public PromotionalSaleStatusCodeType getReturnedStatus()
  {
    return this.returnedStatus;
  }

}

