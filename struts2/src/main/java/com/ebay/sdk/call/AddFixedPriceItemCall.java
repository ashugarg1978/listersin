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
 * Wrapper class of the AddFixedPriceItem call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>Item</code> - Child fields hold the values that define the new
 * fixed-price listing.
 * <br> <B>Output property:</B> <code>ReturnedItemID</code> - Unique item ID for the new fixed-price listing.
 * <br> <B>Output property:</B> <code>ReturnedSKU</code> - Item-level SKU for the new listing, if the seller specified
 * Item.SKU in the request. Variation SKUs are not returned
 * (see GetItem instead).
 * <br> <B>Output property:</B> <code>ReturnedStartTime</code> - Starting date and time for the new listing. This value is based
 * on the time the listing was received and processed, or the
 * time the item will be listed if the seller specified
 * Item.ScheduleTime in the request.
 * <br> <B>Output property:</B> <code>ReturnedEndTime</code> - Date and time when the new listing ends. This is the starting time
 * plus the listing duration.
 * <br> <B>Output property:</B> <code>ReturnedFees</code> - Child elements contain the estimated listing fees for the new item listing.
 * The fees do not include the Final Value Fee (FVF), which cannot be determined
 * until an item is sold.
 * <br> <B>Output property:</B> <code>ReturnedCategoryID</code> - ID of the primary category in which the item was listed.
 * Only returned if you set Item.CategoryMappingAllowed to true in the request
 * and the ID you passed in PrimaryCategory was mapped to a new ID by eBay.
 * If the primary category has not changed or if it has expired with no replacement,
 * CategoryID does not return a value.
 * <br> <B>Output property:</B> <code>ReturnedCategory2ID</code> - ID of the secondary category in which the item was listed.
 * Only returned if you set Item.CategoryMappingAllowed to true in the request
 * and the ID you passed in SecondaryCategory was mapped to a new ID by eBay.
 * If the secondary category has not changed or it has expired with no replacement,
 * Category2ID does not return a value.
 * <br> <B>Output property:</B> <code>ReturnedDiscountReason</code> - The nature of the discount, if a discount applied.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class AddFixedPriceItemCall extends com.ebay.sdk.ApiCall
{
  
  private ItemType item = null;
  private String returnedItemID=null;
  private String returnedSKU=null;
  private Calendar returnedStartTime=null;
  private Calendar returnedEndTime=null;
  private FeesType returnedFees=null;
  private String returnedCategoryID=null;
  private String returnedCategory2ID=null;
  private DiscountReasonCodeType[] returnedDiscountReason=null;

  private PictureService pictureService;
  private String[] pictureFiles = null;
  private boolean autoSetItemUUID = false;
  

  /**
   * Constructor.
   */
  public AddFixedPriceItemCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public AddFixedPriceItemCall(ApiContext apiContext) {
    super(apiContext);
    
    pictureService = eBayPictureServiceFactory.getPictureService(apiContext);

  }

  /**
   * Defines and lists a new fixed-price item. A fixed-price listing
   * can include multiple identical items.
   * <br><br>
   * For the US, Canada (CA), French Canadian (CAFR), and US Motors sites, the FixedPriceItem listing format
   * will be replacing the StoresFixedPrice listing format, and the StoresFixedPrice format will be deprecated
   * in early 2011. As of March 30, 2010, we will start a migration phase where AddItem and AddFixedPriceItem
   * will accept either FixedPriceItem or StoresFixedPrice as listing formats, but the item will be displayed
   * as FixedPriceItem on the site and in search results. GetItem and other 'Get' calls will return the format you originally
   * used in the request. Therefore, the preferred format will be FixedPriceItem.
   * <br><br>
   * As part of the merge of the StoresFixedPrice and FixedPriceItem formats, the start price of all new
   * FixedPriceItems must be 99 cents or greater. This change will also go into effect on March 30, 2010.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The FeesType object.
   */
  public FeesType addFixedPriceItem()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    AddFixedPriceItemRequestType req;
    req = new AddFixedPriceItemRequestType();

    if(autoSetItemUUID && item.getUUID() == null)
      resetItemUUID(item);

    // Call picture service to upload picture files.
    String epsUrl = getApiContext().getEpsServerUrl();
    if(pictureFiles != null && epsUrl != null ) {
	  		PictureDetailsType pictureDetails = item.getPictureDetails();
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
	  		item.setPictureDetails(pictureDetails);
	  		PhotoDisplayCodeType photoDisplay = item.getPictureDetails().getPhotoDisplay();

	  		PictureInfo[] piList = new PictureInfo[pictureFiles.length];
	  		for(int i = 0; i < pictureFiles.length; i ++) {
	  			piList[i] = new PictureInfo();
	  			piList[i].setPictureFilePath(pictureFiles[i]);
	  		}
	  		int uploads = pictureService.uploadPictures(photoDisplay, piList);
	  		if( uploads != piList.length ) {
	  			// Build error message.
	  			StringBuffer sb = new StringBuffer();
	  			for(int i = 0; i < piList.length; i ++ ) {
	  				if (piList[i].hasError()) {
	  					sb.append(piList[i].getErrorType() + " : " + piList[i].getErrorMessage() + "\n");
	  				}
	  			}
	  			throw new SdkException(sb.toString());
	  		}

	  		// Save urls back to item object.
	  		String[] uris = new String[piList.length];
	  		for(int i = 0; i < piList.length; i ++) {
	  			uris[i] = piList[i].getURL();
	  		}
	  		item.getPictureDetails().setPictureURL(uris);
    }

    if (this.item != null)
      req.setItem(this.item);

    AddFixedPriceItemResponseType resp = (AddFixedPriceItemResponseType) execute(req);

    this.returnedItemID = resp.getItemID();
    this.returnedSKU = resp.getSKU();
    this.returnedStartTime = resp.getStartTime();
    this.returnedEndTime = resp.getEndTime();
    this.returnedFees = resp.getFees();
    this.returnedCategoryID = resp.getCategoryID();
    this.returnedCategory2ID = resp.getCategory2ID();
    this.returnedDiscountReason = resp.getDiscountReason();
    item.setItemID(resp.getItemID());
    if( item.getListingDetails() == null )
      item.setListingDetails(new ListingDetailsType());
    if( resp.getStartTime() != null )
      item.getListingDetails().setStartTime(resp.getStartTime());
    if( resp.getEndTime() != null )
      item.getListingDetails().setEndTime(resp.getEndTime());
    return this.getReturnedFees();
  }

  /**
   * Gets the AddFixedPriceItemRequestType.item.
   * @return ItemType
   */
  public ItemType getItem()
  {
    return this.item;
  }

  /**
   * Sets the AddFixedPriceItemRequestType.item.
   * @param item ItemType
   */
  public void setItem(ItemType item)
  {
    this.item = item;
  }
/**
   * Returns AutoSetItemUUID property.
   * @return true means if UUID property of item (item.getUUID()) is null,
   * the call object will automatically generate an UUID and
   * fills it to the item object (item.setUUID()).
   */
  public boolean getAutoSetItemUUID() {
    return autoSetItemUUID;
  }
/**
   * Gets list of picture files for the item.
   * @return String[]
   */
  public String[] getPictureFiles() {
    return pictureFiles;
  }
/**
   * Gets the PictureService object that will be used to upload picture files
   * specified in setPictureFiles().
   * @return The PictureService instance.
   */
  public PictureService getPictureService() {
    return pictureService;
  }
/**
   * Generates a UUID.
   * @return The UUID String object.
   */
  public static String newUUID() {
  	String uuid = java.util.UUID.randomUUID().toString();
    
    	StringBuffer goodUuid = new StringBuffer();
    	for(int i = 0; i < uuid.length(); i ++) {
      		char c = uuid.charAt(i);
      		if( c != '-' ) {
        		goodUuid.append(c);
        	}
	}

    return goodUuid.toString();
  }


/**
   * Reset the UUID property of eBay item object.
   * @param item The ItemType object whose UUID property will be reset.
   */
  public static void resetItemUUID(ItemType item) {
    item.setUUID(newUUID());
  }
/**
   * Sets AutoSetItemUUID property. true means if UUID property of item is null,
   * the call object will automatically generate an UUID and fills it
   * to the item object (item.setUUID()).
   * @param autoSetItemUUID boolean
   */
  public void setAutoSetItemUUID(boolean autoSetItemUUID) {
    this.autoSetItemUUID = autoSetItemUUID;
  }
/**
   * Sets list of picture files for the item. Before listing
   * the item, the attached picture service object (setPictureService())
   * to upload these picture files then set the picture property of the
   * item object.
   * @param pictureFiles String[]
   */
  public void setPictureFiles(String[] pictureFiles) {
    this.pictureFiles = pictureFiles;
  }
/**
   * Set the PictureService object that will be used to upload picture files
   * specified in setPictureFiles().
   * @param pictureService the PictureService object.
   */
  public void setPictureService(PictureService pictureService) {
    this.pictureService = pictureService;
  }

  /**
   * Valid after executing the API.
   * Gets the returned AddFixedPriceItemResponseType.returnedCategory2ID.
   * 
   * @return String
   */
  public String getReturnedCategory2ID()
  {
    return this.returnedCategory2ID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned AddFixedPriceItemResponseType.returnedCategoryID.
   * 
   * @return String
   */
  public String getReturnedCategoryID()
  {
    return this.returnedCategoryID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned AddFixedPriceItemResponseType.returnedDiscountReason.
   * 
   * @return DiscountReasonCodeType[]
   */
  public DiscountReasonCodeType[] getReturnedDiscountReason()
  {
    return this.returnedDiscountReason;
  }

  /**
   * Valid after executing the API.
   * Gets the returned AddFixedPriceItemResponseType.returnedEndTime.
   * 
   * @return Calendar
   */
  public Calendar getReturnedEndTime()
  {
    return this.returnedEndTime;
  }

  /**
   * Valid after executing the API.
   * Gets the returned AddFixedPriceItemResponseType.returnedFees.
   * 
   * @return FeesType
   */
  public FeesType getReturnedFees()
  {
    return this.returnedFees;
  }

  /**
   * Valid after executing the API.
   * Gets the returned AddFixedPriceItemResponseType.returnedItemID.
   * 
   * @return String
   */
  public String getReturnedItemID()
  {
    return this.returnedItemID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned AddFixedPriceItemResponseType.returnedSKU.
   * 
   * @return String
   */
  public String getReturnedSKU()
  {
    return this.returnedSKU;
  }

  /**
   * Valid after executing the API.
   * Gets the returned AddFixedPriceItemResponseType.returnedStartTime.
   * 
   * @return Calendar
   */
  public Calendar getReturnedStartTime()
  {
    return this.returnedStartTime;
  }

}

