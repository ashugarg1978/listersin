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
import java.util.Calendar;

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the GetSellerEvents call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>UserID</code> - eBay user ID for the seller whose events are to be returned.
 * If not specified, retrieves events for the user identified by
 * the authentication token passed in the request. Note that since user information is anonymous to everyone except the bidder and the seller (during an active auction), only sellers looking for information about
 * their own listings and bidders who know the user IDs of their sellers
 * will be able to make this API call successfully. See <a href="http://developer.ebay.com/DevZone/XML/docs/WebHelp/index.htm?context=eBay_XML_API&topic=AnonymousUserInfo">Working with Anonymous User Information</a>in the eBay Web Services Guide for more information.
 * <br> <B>Input property:</B> <code>StartTimeFilter</code> - Helper wrapper to set GetSellerEventsRequestType StartTimeFrom, StartTimeTo:
 * TimeFrom sets GetSellerEventsRequestType.StartTimeFrom: 
 * Describes the earliest (oldest) date to use in a date range filter based
 * on item start time. Must be specified if StartTimeTo is specified. Either
 * the StartTimeFrom, EndTimeFrom, or ModTimeFrom filter must be specified.
 * If you do not specify the correspoding To filter,
 * it is set to the time you make the call.
 * For better results, the time period you use should be less than 48 hours.
 * TimeTo sets GetSellerEventsRequestType.StartTimeTo: 
 * Describes the latest (most recent) date to use in a date range filter
 * based on item start time. If you specify the corresponding From filter,
 * but you do not include StartTimeTo, the StartTimeTo is set to
 * the time you make the call.
 * <br> <B>Input property:</B> <code>EndTimeFilter</code> - Helper wrapper to set GetSellerEventsRequestType EndTimeFrom, EndTimeTo:
 * TimeFrom sets GetSellerEventsRequestType.EndTimeFrom: 
 * Describes the earliest (oldest) date to use in a date range filter based
 * on item end time. Must be specified if EndTimeTo is specified. Either
 * the StartTimeFrom, EndTimeFrom, or ModTimeFrom filter must be specified.
 * If you do not specify the correspoding To filter,
 * it is set to the time you make the call.
 * For better results, the time period you use should be less than 48 hours.
 * TimeTo sets GetSellerEventsRequestType.EndTimeTo: 
 * Describes the latest (most recent) date to use in a date range filter
 * based on item end time. If you specify the corresponding From filter,
 * but you do not include EndTimeTo, then EndTimeTo is set
 * to the time you make the call.
 * <br> <B>Input property:</B> <code>ModTimeFilter</code> - Helper wrapper to set GetSellerEventsRequestType ModTimeFrom, ModTimeTo:
 * TimeFrom sets GetSellerEventsRequestType.ModTimeFrom: 
 * Describes the earliest (oldest) date to use in a date range filter based
 * on item modification time. Must be specified if ModTimeTo is specified. Either
 * the StartTimeFrom, EndTimeFrom, or ModTimeFrom filter must be specified.
 * If you do not specify the correspoding To filter,
 * it is set to the time you make the call.
 * <br><br>
 * For better results, the time period you use should be less than 48 hours.
 * <br><br>
 * If an unexpected item is returned (including an old item
 * or an unchanged active item), please ignore the item.
 * Although a maintenance process may have triggered a change in the modification time,
 * item characteristics are unchanged.
 * TimeTo sets GetSellerEventsRequestType.ModTimeTo: 
 * Describes the latest (most recent) date to use in a date range filter
 * based on the time an item's record was modified. If you specify
 * the corresponding From filter, but you do not include ModTimeTo,
 * then ModTimeTo is set to the time you make the call.
 * <br> <B>Input property:</B> <code>IncludeNewItem</code> - Default is true. If true, response includes only items that have been modified
 * within the ModTime window. If false, response includes all items.
 * <br> <B>Input property:</B> <code>IncludeWatchCount</code> - Specifies whether to include WatchCount in Item nodes returned. WatchCount
 * is the number of watches buyers have placed on the item from their My eBay
 * accounts.
 * <br> <B>Input property:</B> <code>IncludeVariationSpecifics</code> - Specifies whether to force the response to include
 * variation specifics for multi-variation listings. <br>
 * <br>
 * If false (or not specified), eBay keeps the response as small as 
 * possible by only returning the Variation.SKU (along with the 
 * variation price, quantity, and quantity sold).
 * If the variation has no SKU, then Variation.VariationSpecifics is returned instead.<br>
 * <br>
 * If true, Variation.VariationSpecifics is always returned.
 * (Variation.SKU is also returned, if the variation has a SKU.)
 * This may be useful for applications that don't track variations
 * by SKU.<br>
 * <br>
 * Ignored when HideVariations=true.
 * <br> <B>Input property:</B> <code>HideVariations</code> - Specifies whether to force the response to hide
 * variation details for multi-variation listings.<br>
 * <br>
 * If false (or not specified), eBay returns variation details (if 
 * any). In this case, the amount of detail can be controlled by 
 * using IncludeVariationSpecifics.<br>
 * <br>
 * If true, variation details are not returned (and 
 * IncludeVariationSpecifics has no effect). This may be useful for
 * applications that use other calls, notifications, alerts, 
 * or reports to track price and quantity details.
 * <br> <B>Output property:</B> <code>TimeTo</code> - Indicates the latest (most recent) date for any date-based filtering specified as
 * input. Specifically, this field contains the value you specified in the StartTimeTo, EndTimeTo, or ModTimeTo filter, if you used a time filter in the request. If no time filter was specified, TimeTo returns the current time.
 * <br> <B>Output property:</B> <code>ReturnedSellerEvents</code> - Collection of Item objects, each of which represents an item listing
 * that incurred the type of seller event change specified in the call's
 * inputs. Returns empty if the seller has no item events within the
 * time window indicated in the request.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class GetSellerEventsCall extends com.ebay.sdk.ApiCall
{
  
  private String userID = null;
  private TimeFilter startTimeFilter = null;
  private TimeFilter endTimeFilter = null;
  private TimeFilter modTimeFilter = null;
  private boolean includeNewItem = false;
  private Boolean includeWatchCount = null;
  private Boolean includeVariationSpecifics = null;
  private Boolean hideVariations = null;
  private Calendar timeTo=null;
  private ItemType[] returnedSellerEvents=null;


  /**
   * Constructor.
   */
  public GetSellerEventsCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public GetSellerEventsCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Retrieves price changes, item revisions, description revisions,
   * and other changes that have occurred within the last 48 hours
   * related to a seller's eBay listings.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The ItemType[] object.
   */
  public ItemType[] getSellerEvents()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GetSellerEventsRequestType req;
    req = new GetSellerEventsRequestType();
    req.setDetailLevel(this.getDetailLevel());
    if (this.userID != null)
      req.setUserID(this.userID);
    if (this.startTimeFilter != null)
    {
      req.setStartTimeFrom(this.startTimeFilter.getTimeFrom());
      req.setStartTimeTo(this.startTimeFilter.getTimeTo());
    }
    if (this.endTimeFilter != null)
    {
      req.setEndTimeFrom(this.endTimeFilter.getTimeFrom());
      req.setEndTimeTo(this.endTimeFilter.getTimeTo());
    }
    if (this.modTimeFilter != null)
    {
      req.setModTimeFrom(this.modTimeFilter.getTimeFrom());
      req.setModTimeTo(this.modTimeFilter.getTimeTo());
    }
    if (this.includeNewItem != false)
      req.setNewItemFilter(new Boolean(this.includeNewItem));
    if (this.includeWatchCount != null)
      req.setIncludeWatchCount(this.includeWatchCount);
    if (this.includeVariationSpecifics != null)
      req.setIncludeVariationSpecifics(this.includeVariationSpecifics);
    if (this.hideVariations != null)
      req.setHideVariations(this.hideVariations);

    GetSellerEventsResponseType resp = (GetSellerEventsResponseType) execute(req);

    this.timeTo = resp.getTimeTo();
    this.returnedSellerEvents = (resp.getItemArray() == null? null: resp.getItemArray().getItem());
    return this.getReturnedSellerEvents();
  }

  /**
   * Gets the GetSellerEventsRequestType.endTimeFilter.
   * @return TimeFilter
   */
  public TimeFilter getEndTimeFilter()
  {
    return this.endTimeFilter;
  }

  /**
   * Sets the GetSellerEventsRequestType.endTimeFilter.
   * @param endTimeFilter TimeFilter
   */
  public void setEndTimeFilter(TimeFilter endTimeFilter)
  {
    this.endTimeFilter = endTimeFilter;
  }

  /**
   * Gets the GetSellerEventsRequestType.hideVariations.
   * @return Boolean
   */
  public Boolean getHideVariations()
  {
    return this.hideVariations;
  }

  /**
   * Sets the GetSellerEventsRequestType.hideVariations.
   * @param hideVariations Boolean
   */
  public void setHideVariations(Boolean hideVariations)
  {
    this.hideVariations = hideVariations;
  }

  /**
   * Gets the GetSellerEventsRequestType.includeNewItem.
   * @return boolean
   */
  public boolean getIncludeNewItem()
  {
    return this.includeNewItem;
  }

  /**
   * Sets the GetSellerEventsRequestType.includeNewItem.
   * @param includeNewItem boolean
   */
  public void setIncludeNewItem(boolean includeNewItem)
  {
    this.includeNewItem = includeNewItem;
  }

  /**
   * Gets the GetSellerEventsRequestType.includeVariationSpecifics.
   * @return Boolean
   */
  public Boolean getIncludeVariationSpecifics()
  {
    return this.includeVariationSpecifics;
  }

  /**
   * Sets the GetSellerEventsRequestType.includeVariationSpecifics.
   * @param includeVariationSpecifics Boolean
   */
  public void setIncludeVariationSpecifics(Boolean includeVariationSpecifics)
  {
    this.includeVariationSpecifics = includeVariationSpecifics;
  }

  /**
   * Gets the GetSellerEventsRequestType.includeWatchCount.
   * @return Boolean
   */
  public Boolean getIncludeWatchCount()
  {
    return this.includeWatchCount;
  }

  /**
   * Sets the GetSellerEventsRequestType.includeWatchCount.
   * @param includeWatchCount Boolean
   */
  public void setIncludeWatchCount(Boolean includeWatchCount)
  {
    this.includeWatchCount = includeWatchCount;
  }

  /**
   * Gets the GetSellerEventsRequestType.modTimeFilter.
   * @return TimeFilter
   */
  public TimeFilter getModTimeFilter()
  {
    return this.modTimeFilter;
  }

  /**
   * Sets the GetSellerEventsRequestType.modTimeFilter.
   * @param modTimeFilter TimeFilter
   */
  public void setModTimeFilter(TimeFilter modTimeFilter)
  {
    this.modTimeFilter = modTimeFilter;
  }

  /**
   * Gets the GetSellerEventsRequestType.startTimeFilter.
   * @return TimeFilter
   */
  public TimeFilter getStartTimeFilter()
  {
    return this.startTimeFilter;
  }

  /**
   * Sets the GetSellerEventsRequestType.startTimeFilter.
   * @param startTimeFilter TimeFilter
   */
  public void setStartTimeFilter(TimeFilter startTimeFilter)
  {
    this.startTimeFilter = startTimeFilter;
  }

  /**
   * Gets the GetSellerEventsRequestType.userID.
   * @return String
   */
  public String getUserID()
  {
    return this.userID;
  }

  /**
   * Sets the GetSellerEventsRequestType.userID.
   * @param userID String
   */
  public void setUserID(String userID)
  {
    this.userID = userID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSellerEventsResponseType.returnedSellerEvents.
   * 
   * @return ItemType[]
   */
  public ItemType[] getReturnedSellerEvents()
  {
    return this.returnedSellerEvents;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSellerEventsResponseType.timeTo.
   * 
   * @return Calendar
   */
  public Calendar getTimeTo()
  {
    return this.timeTo;
  }

}

