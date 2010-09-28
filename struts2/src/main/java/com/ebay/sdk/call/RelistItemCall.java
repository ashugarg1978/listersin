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
import java.lang.String;
import java.util.Calendar;

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the RelistItem call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>ItemToBeRelisted</code> - Child elements hold the values for item properties that change for the
 * item re-list. Item is a required input. At a minimum, the Item.ItemID
 * property must be set to the ID of the listing being re-listed (a
 * listing that ended in the past 90 days). By default, the new listing's
 * Item object properties are the same as those of the original (ended)
 * listing. By setting a new value in the Item object, the new listing
 * uses the new value rather than the corresponding value from the old
 * listing.
 * <br> <B>Input property:</B> <code>DeletedField</code> - Specifies the name of the field to delete from a listing.
 * See the eBay Web Services guide for rules on deleting values when relisting items.
 * Also see the relevant field descriptions to determine when to use DeletedField (and potential consequences).
 * The request can contain zero, one, or many instances of DeletedField (one for each field to be deleted).
 * DeletedField accepts the following path names, which delete the corresponding nodes:<br><br>
 * Item.ApplicationData<br>
 * Item.AttributeSetArray<br>
 * Item.BuyItNowPrice<br>
 * Item.Charity<br>
 * Item.ConditionID<br>
 * Item.ItemSpecifics<br>
 * Item.ListingCheckoutRedirectPreference.ProStoresStoreName<br>
 * Item.ListingCheckoutRedirectPreference.SellerThirdPartyUsername<br>
 * Item.ListingDesigner.LayoutID<br>
 * Item.ListingDesigner.ThemeID<br>
 * Item.ListingDetails.LocalListingDistance<br>
 * Item.ListingDetails.MinimumBestOfferMessage<br>
 * Item.ListingDetails.MinimumBestOfferPrice<br>
 * Item.ListingEnhancement[Value]<br>
 * Item.PayPalEmailAddress<br>
 * Item.PictureDetails.GalleryURL<br>
 * Item.PictureDetails.PictureURL<br>
 * Item.PostalCode<br>
 * Item.ProductListingDetails<br>
 * Item.SecondaryCategory<br>
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
 * Item.SKU<br>
 * Item.SubTitle<br>
 * These values are case-sensitive. Use values that match the case of the schema element names
 * (Item.PictureDetails.GalleryURL) or make the initial letter of each field name lowercase (item.pictureDetails.galleryURL).
 * However, do not change the case of letters in the middle of a field name.
 * For example, item.picturedetails.galleryUrl is not allowed.<br><br>
 * To delete a listing enhancement like Featured, specify the value you are deleting;
 * for example, Item.ListingEnhancement[Featured].
 * <br> <B>Output property:</B> <code>ReturnedItemID</code> - Unique item ID for the new listing.
 * <br> <B>Output property:</B> <code>ListingFees</code> - Child elements contain the estimated listing fees for the new item
 * listing. The fees do not include the Final Value Fee (FVF), which cannot
 * be determined until an item is sold.
 * <br> <B>Output property:</B> <code>ReturnedStartTime</code> - Date and time the new listing became active on the eBay site.
 * <br> <B>Output property:</B> <code>ReturnedEndTime</code> - Date and time when the new listing ends. This is the starting time plus
 * the listing duration.
 * <br> <B>Output property:</B> <code>ReturnedCategoryID</code> - ID of the primary category in which the item was listed.
 * Only returned if you set Item.CategoryMappingAllowed to true in the request
 * and the ID passed in Item.PrimaryCategory was mapped to a new ID by eBay.
 * If the primary category has not changed or it has expired with no replacement,
 * CategoryID does not return a value.
 * <br> <B>Output property:</B> <code>ReturnedCategory2ID</code> - ID of the secondary category in which the item was listed.
 * Only returned if you set Item.CategoryMappingAllowed to true in the request
 * and the ID passed in Item.SecondaryCategory was mapped to a new ID by eBay.
 * If the secondary category has not changed or it has expired with no replacement,
 * Category2ID does not return a value.
 * <br> <B>Output property:</B> <code>ReturnedDiscountReason</code> - The nature of the discount, if a discount applied.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class RelistItemCall extends com.ebay.sdk.ApiCall
{
  
  private ItemType itemToBeRelisted = null;
  private String[] deletedField = null;
  private String returnedItemID=null;
  private FeesType listingFees=null;
  private Calendar returnedStartTime=null;
  private Calendar returnedEndTime=null;
  private String returnedCategoryID=null;
  private String returnedCategory2ID=null;
  private DiscountReasonCodeType[] returnedDiscountReason=null;

  private PictureService pictureService;


  /**
   * Constructor.
   */
  public RelistItemCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public RelistItemCall(ApiContext apiContext) {
    super(apiContext);
    
    this.pictureService = eBayPictureServiceFactory.getPictureService(apiContext);       

  }

  /**
   * Enables a seller to take a single item (or a single multi-item
   * listing) and re-list it on a specified eBay site.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The FeesType object.
   */
  public FeesType relistItem()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    RelistItemRequestType req;
    req = new RelistItemRequestType();

    if( this.itemToBeRelisted == null )
      throw new SdkException("ItemToBeRelisted property is not set.");
    String origItemID = this.itemToBeRelisted.getItemID();
    req.setItem(this.itemToBeRelisted);
    if (this.itemToBeRelisted != null)
      req.setItem(this.itemToBeRelisted);
    if (this.deletedField != null)
      req.setDeletedField(this.deletedField);

    RelistItemResponseType resp = (RelistItemResponseType) execute(req);

    this.returnedItemID = resp.getItemID();
    this.listingFees = resp.getFees();
    this.returnedStartTime = resp.getStartTime();
    this.returnedEndTime = resp.getEndTime();
    this.returnedCategoryID = resp.getCategoryID();
    this.returnedCategory2ID = resp.getCategory2ID();
    this.returnedDiscountReason = resp.getDiscountReason();
    if( this.itemToBeRelisted.getListingDetails() == null )
      this.itemToBeRelisted.setListingDetails(new ListingDetailsType());
    this.itemToBeRelisted.getListingDetails().setRelistedItemID(origItemID);

    this.itemToBeRelisted.setItemID(resp.getItemID());
    return this.getListingFees();
  }

  /**
   * Gets the RelistItemRequestType.deletedField.
   * @return String[]
   */
  public String[] getDeletedField()
  {
    return this.deletedField;
  }

  /**
   * Sets the RelistItemRequestType.deletedField.
   * @param deletedField String[]
   */
  public void setDeletedField(String[] deletedField)
  {
    this.deletedField = deletedField;
  }

  /**
   * Gets the RelistItemRequestType.itemToBeRelisted.
   * @return ItemType
   */
  public ItemType getItemToBeRelisted()
  {
    return this.itemToBeRelisted;
  }

  /**
   * Sets the RelistItemRequestType.itemToBeRelisted.
   * @param itemToBeRelisted ItemType
   */
  public void setItemToBeRelisted(ItemType itemToBeRelisted)
  {
    this.itemToBeRelisted = itemToBeRelisted;
  }
 
  public void uploadPictures(String[] pictureFiles, PictureDetailsType pictureDetails) throws SdkException  {
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
	    itemToBeRelisted.setPictureDetails(pictureDetails);

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
	itemToBeRelisted.getPictureDetails().setPictureURL(uris);
  }


  /**
   * Valid after executing the API.
   * Gets the returned RelistItemResponseType.listingFees.
   * 
   * @return FeesType
   */
  public FeesType getListingFees()
  {
    return this.listingFees;
  }

  /**
   * Valid after executing the API.
   * Gets the returned RelistItemResponseType.returnedCategory2ID.
   * 
   * @return String
   */
  public String getReturnedCategory2ID()
  {
    return this.returnedCategory2ID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned RelistItemResponseType.returnedCategoryID.
   * 
   * @return String
   */
  public String getReturnedCategoryID()
  {
    return this.returnedCategoryID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned RelistItemResponseType.returnedDiscountReason.
   * 
   * @return DiscountReasonCodeType[]
   */
  public DiscountReasonCodeType[] getReturnedDiscountReason()
  {
    return this.returnedDiscountReason;
  }

  /**
   * Valid after executing the API.
   * Gets the returned RelistItemResponseType.returnedEndTime.
   * 
   * @return Calendar
   */
  public Calendar getReturnedEndTime()
  {
    return this.returnedEndTime;
  }

  /**
   * Valid after executing the API.
   * Gets the returned RelistItemResponseType.returnedItemID.
   * 
   * @return String
   */
  public String getReturnedItemID()
  {
    return this.returnedItemID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned RelistItemResponseType.returnedStartTime.
   * 
   * @return Calendar
   */
  public Calendar getReturnedStartTime()
  {
    return this.returnedStartTime;
  }

}

