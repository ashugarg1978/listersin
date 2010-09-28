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
 * Wrapper class of the VerifyRelistItem call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>Item</code> - Child elements hold the values for item properties that change for the
 * item re-list. Item is a required input. At a minimum, the Item.ItemID
 * property must be set to the ID of the listing being re-listed (a
 * listing that ended in the past 90 days). By default, the new listing's
 * Item object properties are the same as those of the original (ended)
 * listing. By setting a new value in the Item object, the new listing
 * uses the new value rather than the corresponding value from the old
 * listing.
 * <br> <B>Input property:</B> <code>DeletedField</code> - Specifies the name of the field to remove from a listing.
 * See the Developer's Guide for rules on removing values when relisting
 * items. DeletedField accepts the same values as 
 * DeletedField in RelistItem.
 * DeletedField is case sensitive. The request can contain zero, one, or many
 * instances of DeletedField.
 * <br> <B>Output property:</B> <code>ReturnedItemID</code> - Unique item ID for the new listing. As VerifyRelistItem does not
 * actually re-list an item, returns 0 instead of a normal item ID.
 * <br> <B>Output property:</B> <code>ReturnedFees</code> - Child elements contain the estimated listing fees for the new item
 * listing. The fees do not include the Final Value Fee (FVF), which cannot
 * be determined until an item is sold.
 * <br> <B>Output property:</B> <code>ReturnedStartTime</code> - Date and time the new listing became active on the eBay site.
 * <br> <B>Output property:</B> <code>ReturnedEndTime</code> - Date and time when the new listing ends. This is the starting time plus
 * the listing duration.
 * <br> <B>Output property:</B> <code>ReturnedDiscountReason</code> - The nature of the discount, if a discount would have applied
 * had this actually been listed at this time.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class VerifyRelistItemCall extends com.ebay.sdk.ApiCall
{
  
  private ItemType item = null;
  private String[] deletedField = null;
  private String returnedItemID=null;
  private FeesType returnedFees=null;
  private Calendar returnedStartTime=null;
  private Calendar returnedEndTime=null;
  private DiscountReasonCodeType[] returnedDiscountReason=null;


  /**
   * Constructor.
   */
  public VerifyRelistItemCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public VerifyRelistItemCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Checks the arguments that you plan to use for a RelistItem call, looking for errors
   * and listing fees, without actually relisting the item.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The String object.
   */
  public String verifyRelistItem()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    VerifyRelistItemRequestType req;
    req = new VerifyRelistItemRequestType();
    if (this.item != null)
      req.setItem(this.item);
    if (this.deletedField != null)
      req.setDeletedField(this.deletedField);

    VerifyRelistItemResponseType resp = (VerifyRelistItemResponseType) execute(req);

    this.returnedItemID = resp.getItemID();
    this.returnedFees = resp.getFees();
    this.returnedStartTime = resp.getStartTime();
    this.returnedEndTime = resp.getEndTime();
    this.returnedDiscountReason = resp.getDiscountReason();
    return this.getReturnedItemID();
  }

  /**
   * Gets the VerifyRelistItemRequestType.deletedField.
   * @return String[]
   */
  public String[] getDeletedField()
  {
    return this.deletedField;
  }

  /**
   * Sets the VerifyRelistItemRequestType.deletedField.
   * @param deletedField String[]
   */
  public void setDeletedField(String[] deletedField)
  {
    this.deletedField = deletedField;
  }

  /**
   * Gets the VerifyRelistItemRequestType.item.
   * @return ItemType
   */
  public ItemType getItem()
  {
    return this.item;
  }

  /**
   * Sets the VerifyRelistItemRequestType.item.
   * @param item ItemType
   */
  public void setItem(ItemType item)
  {
    this.item = item;
  }

  /**
   * Valid after executing the API.
   * Gets the returned VerifyRelistItemResponseType.returnedDiscountReason.
   * 
   * @return DiscountReasonCodeType[]
   */
  public DiscountReasonCodeType[] getReturnedDiscountReason()
  {
    return this.returnedDiscountReason;
  }

  /**
   * Valid after executing the API.
   * Gets the returned VerifyRelistItemResponseType.returnedEndTime.
   * 
   * @return Calendar
   */
  public Calendar getReturnedEndTime()
  {
    return this.returnedEndTime;
  }

  /**
   * Valid after executing the API.
   * Gets the returned VerifyRelistItemResponseType.returnedFees.
   * 
   * @return FeesType
   */
  public FeesType getReturnedFees()
  {
    return this.returnedFees;
  }

  /**
   * Valid after executing the API.
   * Gets the returned VerifyRelistItemResponseType.returnedItemID.
   * 
   * @return String
   */
  public String getReturnedItemID()
  {
    return this.returnedItemID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned VerifyRelistItemResponseType.returnedStartTime.
   * 
   * @return Calendar
   */
  public Calendar getReturnedStartTime()
  {
    return this.returnedStartTime;
  }

}

