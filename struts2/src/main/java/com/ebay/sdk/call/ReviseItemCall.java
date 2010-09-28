/*
Copyright (c) 2009 eBay, Inc.

This program is licensed under the terms of the eBay Common Development and 
Distribution License (CDDL) Version 1.0 (the "License") and any subsequent 
version thereof released by eBay.  The then-current version of the License 
can be found at https://www.codebase.ebay.com/Licenses.html and in the 
eBaySDKLicense file that is under the eBay SDK install directory.
*/

package com.ebay.sdk.call;

import com.ebay.sdk.pictureservice.*;
import com.ebay.sdk.pictureservice.eps.*;
import java.lang.Boolean;
import java.lang.String;
import java.util.Calendar;

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the ReviseItem call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>ItemToBeRevised</code> - Child elements hold the values for properties that are changing. The
 * Item.ItemID property must always be set to the ID of the item listing being
 * changed. Set values in the Item object only for those properties that are
 * changing. Use DeletedField to remove a property.
 * <br><br>
 * Applicable to Half.com.
 * <br> <B>Input property:</B> <code>DeletedField</code> - Specifies the name of a field to delete from a listing. See the eBay
 * Trading API guide for rules on deleting values when revising items. Also
 * see the relevant field descriptions to determine when to use DeletedField
 * (and potential consequences). The request can contain zero, one, or many
 * instances of DeletedField (one for each field to be deleted).
 * <br><br>
 * You cannot delete required fields from a listing.
 * <br><br>
 * Some fields are optional when you first list an item (e.g.,
 * SecondaryCategory), but once they are set they cannot be deleted when you
 * revise an item. Some optional fields cannot be deleted from auction
 * listings if the item has bids and/or ends within 12 hours. Some optional
 * fields cannot be deleted if other fields depend on them.
 * <br><br>
 * Some data (such as Variation nodes within Variations) can't be deleted by
 * using DeletedFields. See the relevant field descriptions for how to delete
 * such data. See the eBay Web Services guide for rules on removing values
 * when revising items. Also see the relevant field descriptions for details
 * on when to use DeletedField (and potential consequences).
 * <br><br>
 * DeletedField accepts the following path names, which delete the
 * corresponding nodes:
 * <br><br>
 * Item.ApplicationData<br>
 * Item.AttributeSetArray<br>
 * Item.BuyItNowPrice<br>
 * Item.ConditionID<br>
 * Item.ExtendedSellerContactDetails<br>
 * Item.ClassifiedAdContactByEmailEnabled<br>
 * Item.ItemSpecifics<br>
 * Item.ListingCheckoutRedirectPreference.ProStoresStoreName<br>
 * Item.ListingCheckoutRedirectPreference.SellerThirdPartyUsername<br>
 * Item.ListingDesigner.LayoutID<br>
 * Item.ListingDesigner.ThemeID<br>
 * Item.ListingDetails.MinimumBestOfferMessage<br>
 * Item.ListingDetails.MinimumBestOfferPrice<br>
 * Item.ListingEnhancement[Value]<br>
 * Item.PayPalEmailAddress<br>
 * Item.PictureDetails.GalleryURL<br>
 * Item.PictureDetails.PictureURL<br>
 * Item.PostalCode<br>
 * Item.ProductListingDetails<br>
 * Item.SellerContactDetails<br>
 * Item.SellerContactDetails.CompanyName<br>
 * Item.SellerContactDetails.County<br>
 * Item.SellerContactDetails.InternationalStreet<br>
 * Item.SellerContactDetails.Phone2AreaOrCityCode<br>
 * Item.SellerContactDetails.Phone2CountryCode<br>
 * Item.SellerContactDetails.Phone2CountryPrefix<br>
 * Item.SellerContactDetails.Phone2LocalNumber<br>
 * Item.SellerContactDetails.PhoneAreaOrCityCode<br>
 * Item.SellerContactDetails.PhoneCountryCode<br>
 * Item.SellerContactDetails.PhoneCountryPrefix<br>
 * Item.SellerContactDetails.PhoneLocalNumber<br>
 * Item.SellerContactDetails.Street<br>
 * Item.SellerContactDetails.Street2<br>
 * Item.ShippingDetails.PaymentInstructions<br>
 * Item.SKU (unless InventoryTrackingMethod is SKU)<br>
 * Item.SubTitle
 * <br><br>
 * These values are case-sensitive. Use values that match the case of the
 * schema element names (Item.PictureDetails.GalleryURL) or make the initial
 * letter of each field name lowercase (item.pictureDetails.galleryURL).
 * However, do not change the case of letters in the middle of a field name.
 * For example, item.picturedetails.galleryUrl is not allowed.
 * <br><br>
 * To delete a listing enhancement like BoldTitle, specify the value you are
 * deleting in square brackets ("[ ]"); for example,
 * Item.ListingEnhancement[BoldTitle].
 * <br> <B>Input property:</B> <code>VerifyOnly</code> - When the VerifyOnly boolean tag value is supplied as 'true', the response will include the calculated
 * listing price change if there is an increase in the BIN/Start price, but does not persist the values in DB.
 * This call is similar to VerifyAddItem in revise mode.
 * <br> <B>Output property:</B> <code>ReturnedItemID</code> - Item ID that uniquely identifies the item listing that was revised.
 * Provided for confirmation purposes. The value returned should be the
 * same as the item ID specified in the ItemID property of the Item object
 * specified as input for the call.
 * Also applicable to Half.com.
 * <br> <B>Output property:</B> <code>ReturnedStartTime</code> - Starting date and time for the revised listing.
 * Also returned for Half.com (for Half.com, the start time is
 * always the time the item was originally listed).
 * <br> <B>Output property:</B> <code>ReturnedEndTime</code> - Date and time when the new listing ends. This is the starting time
 * plus the listing duration.
 * Also returned for Half.com, but for Half.com the actual end time is GTC
 * (not the end time returned in the response).
 * <br> <B>Output property:</B> <code>ListingFees</code> - Child elements contain the estimated listing fees for the revised item
 * listing. The fees do not include the Final Value Fee (FVF), which cannot
 * be determined until an item is sold. Revising an item does not itself
 * incur a fee. However, certain item properties are fee-based and result
 * in the return of fees in the call's response.
 * Not applicable to Half.com.
 * <br> <B>Output property:</B> <code>ReturnedCategoryID</code> - ID of the primary category in which the item was listed.
 * Only returned if you set Item.CategoryMappingAllowed to true in the request
 * and the ID passed in Item.PrimaryCategory was mapped to a new ID by eBay.
 * If the primary category has not changed or it has expired with no replacement,
 * CategoryID does not return a value.
 * Not applicable to Half.com.
 * <br> <B>Output property:</B> <code>ReturnedCategory2ID</code> - ID of the secondary category in which the item was listed.
 * Only returned if you set Item.CategoryMappingAllowed to true in the request
 * and the ID passed in Item.SecondaryCategory was mapped to a new ID by eBay.
 * If the secondary category has not changed or it has expired with no replacement,
 * Category2ID does not return a value.
 * Not applicable to Half.com.
 * <br> <B>Output property:</B> <code>ReturnedVerifyOnly</code> - Supporting VerifyOnly for ReviseItem call as part of project 24083 (API - Critical enhancements).
 * When the VerifyOnly boolean tag value is supplied as 'true', the response will include the calculated
 * listing price change if there is an increase in the BIN/Start price, but does not persist the values in DB.
 * This call is simialr to VerifyAddItem in revise mode.
 * <br> <B>Output property:</B> <code>ReturnedDiscountReason</code> - The nature of the discount, if a discount applied.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class ReviseItemCall extends com.ebay.sdk.ApiCall
{
  
  private ItemType itemToBeRevised = null;
  private String[] deletedField = null;
  private Boolean verifyOnly = null;
  private String returnedItemID=null;
  private Calendar returnedStartTime=null;
  private Calendar returnedEndTime=null;
  private FeesType listingFees=null;
  private String returnedCategoryID=null;
  private String returnedCategory2ID=null;
  private Boolean returnedVerifyOnly=null;
  private DiscountReasonCodeType[] returnedDiscountReason=null;

  private TimeFilter listingDuration = null;
  private PictureService pictureService;


  /**
   * Constructor.
   */
  public ReviseItemCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public ReviseItemCall(ApiContext apiContext) {
    super(apiContext);
    
    this.pictureService = eBayPictureServiceFactory.getPictureService(apiContext);       
  }

  /**
   * Enables a seller to change the properties of a currently active listing.
   * <br>
   * <br>
   * After one item in a multi-quantity fixed-price listing has been sold, you can not
   * the values in the Title, Primary Category, Secondary Category, Listing Duration,
   * and Listing Type fields for that listing. However, all other fields in the
   * multi-quantity, fixed-price listing are editable.
   * <br>
   * <br>
   * Inputs are the Item ID of the listing you are revising, and the field or fields
   * that you are updating.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The FeesType object.
   */
  public FeesType reviseItem()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    ReviseItemRequestType req;
    req = new ReviseItemRequestType();

    if( this.itemToBeRevised == null )
      throw new SdkException("ItemToBeRevised property is not set.");
 
    if (this.itemToBeRevised != null)
      req.setItem(this.itemToBeRevised);
    if (this.deletedField != null)
      req.setDeletedField(this.deletedField);
    if (this.verifyOnly != null)
      req.setVerifyOnly(this.verifyOnly);

    ReviseItemResponseType resp = (ReviseItemResponseType) execute(req);

    this.returnedItemID = resp.getItemID();
    this.returnedStartTime = resp.getStartTime();
    this.returnedEndTime = resp.getEndTime();
    this.listingFees = resp.getFees();
    this.returnedCategoryID = resp.getCategoryID();
    this.returnedCategory2ID = resp.getCategory2ID();
    this.returnedVerifyOnly = resp.isVerifyOnly();
    this.returnedDiscountReason = resp.getDiscountReason();
    Calendar startTime = resp.getStartTime();
    if (null != startTime)
    {
    	listingDuration = new TimeFilter(startTime, resp.getEndTime());
    }
    return this.getListingFees();
  }

  /**
   * Gets the ReviseItemRequestType.deletedField.
   * @return String[]
   */
  public String[] getDeletedField()
  {
    return this.deletedField;
  }

  /**
   * Sets the ReviseItemRequestType.deletedField.
   * @param deletedField String[]
   */
  public void setDeletedField(String[] deletedField)
  {
    this.deletedField = deletedField;
  }

  /**
   * Gets the ReviseItemRequestType.itemToBeRevised.
   * @return ItemType
   */
  public ItemType getItemToBeRevised()
  {
    return this.itemToBeRevised;
  }

  /**
   * Sets the ReviseItemRequestType.itemToBeRevised.
   * @param itemToBeRevised ItemType
   */
  public void setItemToBeRevised(ItemType itemToBeRevised)
  {
    this.itemToBeRevised = itemToBeRevised;
  }

  /**
   * Gets the ReviseItemRequestType.verifyOnly.
   * @return Boolean
   */
  public Boolean getVerifyOnly()
  {
    return this.verifyOnly;
  }

  /**
   * Sets the ReviseItemRequestType.verifyOnly.
   * @param verifyOnly Boolean
   */
  public void setVerifyOnly(Boolean verifyOnly)
  {
    this.verifyOnly = verifyOnly;
  }
/**
   * 
   * @return the listing duration.
   */
  public TimeFilter getListingDuration() {
  	return listingDuration;
  }

  public void uploadPictures(String[] pictureFiles, PictureDetailsType pictureDetails) throws SdkException {
	  if(pictureFiles == null) {
		  return;
	  }
      PictureInfo[] pictureInfoArray = new PictureInfo[pictureFiles.length];
      for(int i = 0; i < pictureFiles.length; i ++) {
    	  pictureInfoArray[i] = new PictureInfo();
    	  pictureInfoArray[i].setPictureFilePath(pictureFiles[i]);
      }
      uploadPictures(pictureInfoArray, pictureDetails);
  }
  
  public void uploadPictures(PictureInfo[] pictureInfoArray, PictureDetailsType pictureDetails) throws SdkException  {
	    String epsUrl = getApiContext().getEpsServerUrl();
	    if(pictureInfoArray == null || epsUrl == null) {
	    	return;
	    }
	    if(pictureDetails == null) {
	    	pictureDetails = new PictureDetailsType();
	    }
	    if(pictureDetails.getPhotoDisplay() == null) {
	    	pictureDetails.setPhotoDisplay(PhotoDisplayCodeType.NONE);
	    }
	    if(pictureDetails.getGalleryType() == null) {
	    	pictureDetails.setGalleryType(GalleryTypeCodeType.NONE);
	    }
	    if(pictureDetails.getPictureSource() == null) {
	    	pictureDetails.setPictureSource(PictureSourceCodeType.VENDOR);
	    }
        itemToBeRevised.setPictureDetails(pictureDetails);

        int uploads = pictureService.uploadPictures(pictureDetails.getPhotoDisplay(), pictureInfoArray);
        if(uploads != pictureInfoArray.length ) {
          // Build error message.
          StringBuffer sb = new StringBuffer();
          for(int i = 0; i < pictureInfoArray.length; i ++ ) {
            if (pictureInfoArray[i].hasError())
              sb.append(pictureInfoArray[i].getErrorType() + " : " +
            		   pictureInfoArray[i].getErrorMessage() + "\n");
          }
          throw new SdkException(sb.toString());
        }

        // Save urls back to item object.
        String[] uris = new String[pictureInfoArray.length];
	for(int i = 0; i < pictureInfoArray.length; i ++) {
		uris[i] = pictureInfoArray[i].getURL();
	}
	itemToBeRevised.getPictureDetails().setPictureURL(uris);
  }


  /**
   * Valid after executing the API.
   * Gets the returned ReviseItemResponseType.listingFees.
   * 
   * @return FeesType
   */
  public FeesType getListingFees()
  {
    return this.listingFees;
  }

  /**
   * Valid after executing the API.
   * Gets the returned ReviseItemResponseType.returnedCategory2ID.
   * 
   * @return String
   */
  public String getReturnedCategory2ID()
  {
    return this.returnedCategory2ID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned ReviseItemResponseType.returnedCategoryID.
   * 
   * @return String
   */
  public String getReturnedCategoryID()
  {
    return this.returnedCategoryID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned ReviseItemResponseType.returnedDiscountReason.
   * 
   * @return DiscountReasonCodeType[]
   */
  public DiscountReasonCodeType[] getReturnedDiscountReason()
  {
    return this.returnedDiscountReason;
  }

  /**
   * Valid after executing the API.
   * Gets the returned ReviseItemResponseType.returnedEndTime.
   * 
   * @return Calendar
   */
  public Calendar getReturnedEndTime()
  {
    return this.returnedEndTime;
  }

  /**
   * Valid after executing the API.
   * Gets the returned ReviseItemResponseType.returnedItemID.
   * 
   * @return String
   */
  public String getReturnedItemID()
  {
    return this.returnedItemID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned ReviseItemResponseType.returnedStartTime.
   * 
   * @return Calendar
   */
  public Calendar getReturnedStartTime()
  {
    return this.returnedStartTime;
  }

  /**
   * Valid after executing the API.
   * Gets the returned ReviseItemResponseType.returnedVerifyOnly.
   * 
   * @return Boolean
   */
  public Boolean getReturnedVerifyOnly()
  {
    return this.returnedVerifyOnly;
  }

}

