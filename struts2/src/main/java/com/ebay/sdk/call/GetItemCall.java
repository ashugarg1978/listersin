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
import java.lang.String;

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the GetItem call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>ItemID</code> - Specifies the item ID that uniquely identifies the item listing for which
 * to retrieve the data.
 * <br><br>
 * ItemID is a required input in most cases. SKU can be used instead in certain
 * cases (see the description of SKU). If both ItemID and SKU are specified for
 * items where the inventory tracking method is ItemID, ItemID takes precedence.
 * <br> <B>Input property:</B> <code>IncludeWatchCount</code> - Indicates if the caller wants to include watch count for that item in the
 * response. You must be the seller of the item to retrieve the watch count.
 * <br> <B>Input property:</B> <code>IncludeCrossPromotion</code> - Specifies whether or not to include cross-promotion information for
 * the item in the call response.
 * <br><br>
 * With a request version of 485 or higher, the default is false (do not
 * include cross-promotion details). Set to true to retrieve cross-promotion
 * information for the item. Cross-promotion information is returned in
 * Item.CrossPromotion.PromotedItem.PromotionDetails.
 * A promoted item will now contain multiple PromotionDetails containers.
 * <br><br>
 * With a request version lower than 485, the default is true (include
 * cross-promotions). Set the value to false if you do not want to retrieve
 * cross-promotion information. Cross-promotion information, PromotedPrice
 * and PromotedPriceType, are returned in Item.CrossPromotion.PromotedItem.
 * If a promoted item has multiple PromotedPriceType and PromotedPrice value
 * pairs, only the last pair is returned.
 * <br> <B>Input property:</B> <code>IncludeItemSpecifics</code> - If true, the response returns the ItemSpecifics node
 * (if the listing has custom Item Specifics).<br>
 * <br>
 * Item Specifics are well-known aspects of items in a given
 * category. For example, items in a washer and dryer category
 * might have an aspect like Type=Top-Loading; whereas
 * items in a jewelry category might have an aspect like
 * Gemstone=Amber.<br>
 * <br>
 * (This does not cause the response to include ID-based
 * attributes. To also retrieve ID-based attributes,
 * pass DetailLevel in the request with the value
 * ItemReturnAttributes or ReturnAll.)
 * <br> <B>Input property:</B> <code>IncludeTaxTable</code> - If true, an associated tax table is returned in the response.
 * If no tax table is associated with the item, then no
 * tax table is returned, even if IncludeTaxTable is set to true.
 * <br> <B>Input property:</B> <code>SKU</code> - Retrieves an item that was listed by the user identified
 * in AuthToken and that is being tracked by this SKU.<br>
 * <br>
 * A SKU (stock keeping unit) is an identifier defined by a seller.
 * Some sellers use SKUs to track complex flows of products
 * and information on the client side.
 * eBay preserves the SKU on the item, enabling you
 * to obtain it before and after a transaction is created.
 * (SKU is recommended as an alternative to
 * ApplicationData.)<br>
 * <br>
 * In GetItem, SKU can only be used to retrieve one of your
 * own items, where you listed the item by using AddFixedPriceItem
 * or RelistFixedPriceItem,
 * and you set Item.InventoryTrackingMethod to SKU at
 * the time the item was listed. (These criteria are necessary to
 * uniquely identify the listing by a SKU.)<br>
 * <br>
 * Either ItemID or SKU is required in the request.
 * If both are passed, they must refer to the same item,
 * and that item must have InventoryTrackingMethod set to SKU.
 * <br> <B>Input property:</B> <code>VariationSKU</code> - Variation-level SKU that uniquely identifes a Variation within
 * the listing identified by ItemID. Only applicable when the
 * seller listed the item with Variation-level SKU (Variation.SKU)
 * values. Retrieves all the usual Item fields, but limits the
 * Variations content to the specified Variation.
 * If not specified, the response includes all Variations.
 * <br> <B>Input property:</B> <code>VariationSpecifics</code> - Name-value pairs that identify one or more Variations within the
 * listing identified by ItemID. Only applicable when the seller
 * listed the item with Variations. Retrieves all the usual Item
 * fields, but limits the Variations content to the specified
 * Variation(s). If the specified pairs do not match any Variation,
 * eBay returns all Variations.<br>
 * <br>
 * To retrieve only one variation, specify the full set of
 * name/value pairs that match all the name-value pairs of one
 * Variation. <br>
 * <br>
 * To retrieve multiple variations (using a wildcard),
 * specify one or more name/value pairs that partially match the
 * desired variations. For example, if the listing contains
 * Variations for shirts in different colors and sizes, specify
 * Color as Red (and no other name/value pairs) to retrieve
 * all the red shirts in all sizes (but no other colors).
 * <br> <B>Input property:</B> <code>TransactionID</code> - Identifies a single transaction for a listing. A transaction begins when
 * a winning bidder or buyer is determined, and ends when the buyer finishes
 * the checkout process.
 * <br><br>
 * Since you can change active multiple-quantity fixed price listings
 * even after one of the items has been purchased, the transaction ID is associated
 * with a snapshot of the item data at the time of the purchase.
 * <br><br>
 * After one item in a multi-quantity listing has been sold, sellers can not change
 * the values in the Title, Primary Category, Secondary Category, Listing Duration,
 * and Listing Type fields. However, all other fields are editable.
 * <br><br>
 * Specifying a TransactionID in the GetItemRequest allows you to retrieve
 * a snapshot of the listing as it was when the transaction was created.
 * <br> <B>Input property:</B> <code>IncludeItemCompatibilityList</code> - This field is used to specify whether or not to retrieve Parts
 * Compatiblity information. If true, any compatible applications associated
 * with the item will be returned in the response (<b class="con">
 * Item.ItemCompatibilityList</b>). If no compatible applications have
 * been specified for the item, no item compatibilities will be returned.
 * <br><br>
 * If false or not specified, the response will return a compatibility count
 * (<b class="con">ItemCompatibilityCoun</b>t) when parts 
 * compatibilities have been specified for the item.
 * <br><br> 
 * Parts Compatibility is supported in limited Parts & Accessories
 * categories for the eBay Motors (US) site (site ID 100) only.
 * <br> <B>Output property:</B> <code>ReturnedItem</code> - ItemType object that contains the data for the specified item.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class GetItemCall extends com.ebay.sdk.ApiCall
{
  
  private String itemID = null;
  private Boolean includeWatchCount = null;
  private Boolean includeCrossPromotion = null;
  private Boolean includeItemSpecifics = null;
  private Boolean includeTaxTable = null;
  private String sKU = null;
  private String variationSKU = null;
  private NameValueListArrayType variationSpecifics = null;
  private String transactionID = null;
  private Boolean includeItemCompatibilityList = null;
  private ItemType returnedItem=null;


  /**
   * Constructor.
   */
  public GetItemCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public GetItemCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Requests data for a specific item identified by item ID. Use no
   * DetailLevel to return all item fields without Item.Description.
   * Use a DetailLevel of ReturnAll to return all item fields. See
   * GetItem in the eBay Web Services Guide for more information.
   * If a listing ended more than 90 days ago, its title,
   * price, and other item information are not returned. 
   * <p>Applicable to the <a href="http://developer.ebay.com/developercenter/rest/" target="_blank">REST API</a>.</p>
   * 
   * <br>
   * @throws ApiException
   * @return The ItemType object.
   */
  public ItemType getItem()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GetItemRequestType req;
    req = new GetItemRequestType();
    req.setDetailLevel(this.getDetailLevel());
    if (this.itemID != null)
      req.setItemID(this.itemID);
    if (this.includeWatchCount != null)
      req.setIncludeWatchCount(this.includeWatchCount);
    if (this.includeCrossPromotion != null)
      req.setIncludeCrossPromotion(this.includeCrossPromotion);
    if (this.includeItemSpecifics != null)
      req.setIncludeItemSpecifics(this.includeItemSpecifics);
    if (this.includeTaxTable != null)
      req.setIncludeTaxTable(this.includeTaxTable);

    GetItemResponseType resp = (GetItemResponseType) execute(req);

    this.returnedItem = resp.getItem();
    return this.getReturnedItem();
  }

  /**
   * Gets the GetItemRequestType.includeCrossPromotion.
   * @return Boolean
   */
  public Boolean getIncludeCrossPromotion()
  {
    return this.includeCrossPromotion;
  }

  /**
   * Sets the GetItemRequestType.includeCrossPromotion.
   * @param includeCrossPromotion Boolean
   */
  public void setIncludeCrossPromotion(Boolean includeCrossPromotion)
  {
    this.includeCrossPromotion = includeCrossPromotion;
  }

  /**
   * Gets the GetItemRequestType.includeItemCompatibilityList.
   * @return Boolean
   */
  public Boolean getIncludeItemCompatibilityList()
  {
    return this.includeItemCompatibilityList;
  }

  /**
   * Sets the GetItemRequestType.includeItemCompatibilityList.
   * @param includeItemCompatibilityList Boolean
   */
  public void setIncludeItemCompatibilityList(Boolean includeItemCompatibilityList)
  {
    this.includeItemCompatibilityList = includeItemCompatibilityList;
  }

  /**
   * Gets the GetItemRequestType.includeItemSpecifics.
   * @return Boolean
   */
  public Boolean getIncludeItemSpecifics()
  {
    return this.includeItemSpecifics;
  }

  /**
   * Sets the GetItemRequestType.includeItemSpecifics.
   * @param includeItemSpecifics Boolean
   */
  public void setIncludeItemSpecifics(Boolean includeItemSpecifics)
  {
    this.includeItemSpecifics = includeItemSpecifics;
  }

  /**
   * Gets the GetItemRequestType.includeTaxTable.
   * @return Boolean
   */
  public Boolean getIncludeTaxTable()
  {
    return this.includeTaxTable;
  }

  /**
   * Sets the GetItemRequestType.includeTaxTable.
   * @param includeTaxTable Boolean
   */
  public void setIncludeTaxTable(Boolean includeTaxTable)
  {
    this.includeTaxTable = includeTaxTable;
  }

  /**
   * Gets the GetItemRequestType.includeWatchCount.
   * @return Boolean
   */
  public Boolean getIncludeWatchCount()
  {
    return this.includeWatchCount;
  }

  /**
   * Sets the GetItemRequestType.includeWatchCount.
   * @param includeWatchCount Boolean
   */
  public void setIncludeWatchCount(Boolean includeWatchCount)
  {
    this.includeWatchCount = includeWatchCount;
  }

  /**
   * Gets the GetItemRequestType.itemID.
   * @return String
   */
  public String getItemID()
  {
    return this.itemID;
  }

  /**
   * Sets the GetItemRequestType.itemID.
   * @param itemID String
   */
  public void setItemID(String itemID)
  {
    this.itemID = itemID;
  }

  /**
   * Gets the GetItemRequestType.sKU.
   * @return String
   */
  public String getSKU()
  {
    return this.sKU;
  }

  /**
   * Sets the GetItemRequestType.sKU.
   * @param sKU String
   */
  public void setSKU(String sKU)
  {
    this.sKU = sKU;
  }

  /**
   * Gets the GetItemRequestType.transactionID.
   * @return String
   */
  public String getTransactionID()
  {
    return this.transactionID;
  }

  /**
   * Sets the GetItemRequestType.transactionID.
   * @param transactionID String
   */
  public void setTransactionID(String transactionID)
  {
    this.transactionID = transactionID;
  }

  /**
   * Gets the GetItemRequestType.variationSKU.
   * @return String
   */
  public String getVariationSKU()
  {
    return this.variationSKU;
  }

  /**
   * Sets the GetItemRequestType.variationSKU.
   * @param variationSKU String
   */
  public void setVariationSKU(String variationSKU)
  {
    this.variationSKU = variationSKU;
  }

  /**
   * Gets the GetItemRequestType.variationSpecifics.
   * @return NameValueListArrayType
   */
  public NameValueListArrayType getVariationSpecifics()
  {
    return this.variationSpecifics;
  }

  /**
   * Sets the GetItemRequestType.variationSpecifics.
   * @param variationSpecifics NameValueListArrayType
   */
  public void setVariationSpecifics(NameValueListArrayType variationSpecifics)
  {
    this.variationSpecifics = variationSpecifics;
  }
/**
   * Executing the API call.
   * @param itemID eBay itemID that uniquely identifies the item.
   * @throws ApiException
   * @return The ItemType object if the call succeeded.
   */
  public ItemType getItem(String itemID)
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {

    GetItemRequestType request = new GetItemRequestType();
    request.setItemID(itemID);
    if (this.includeWatchCount != null)
    	request.setIncludeWatchCount(this.includeWatchCount);
    if (this.includeCrossPromotion != null)
    	request.setIncludeCrossPromotion(this.includeCrossPromotion);
    if (this.includeItemSpecifics != null)
    	request.setIncludeItemSpecifics(this.includeItemSpecifics);
    if (this.includeTaxTable != null)
    	request.setIncludeTaxTable(this.includeTaxTable);

    GetItemResponseType resp = (GetItemResponseType)execute(request);

    this.returnedItem = resp.getItem();
    return this.getReturnedItem();
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetItemResponseType.returnedItem.
   * 
   * @return ItemType
   */
  public ItemType getReturnedItem()
  {
    return this.returnedItem;
  }

}

