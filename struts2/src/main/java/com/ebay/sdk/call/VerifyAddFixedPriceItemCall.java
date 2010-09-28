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
 * Wrapper class of the VerifyAddFixedPriceItem call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>Item</code> - Child fields hold the values that define the new
 * fixed-price listing.
 * <br> <B>Output property:</B> <code>ReturnedItemID</code> - Represents the item ID for the new fixed-price listing. VerifyAddFixedPriceItem does not
 * actually list an item, so 0 is returned instead of a normal item ID.
 * <br> <B>Output property:</B> <code>ReturnedSKU</code> - Item-level SKU for the new listing, if the seller specified
 * tem.SKU in the request. Variation SKUs are not returned
 * (see GetItem instead).
 * <br> <B>Output property:</B> <code>ReturnedFees</code> - Child elements contain the estimated listing fees for the new item listing.
 * The fees do not include the Final Value Fee (FVF), which cannot be determined
 * until an item is sold.
 * <br> <B>Output property:</B> <code>ReturnedExpressListing</code> - Indicates whether the item would be listed on eBay Express.
 * See ExpressItemRequirements for hints about why this
 * value is true or false for a given item.
 * <br> <B>Output property:</B> <code>ReturnedExpressItemRequirements</code> - Contains details about why an item does or doesn't
 * qualify as an eBay Express listing. Only returned when
 * IncludeExpressRequirements is true the request.<br>
 * <br>
 * The item requirements are assessed in this order:<br>
 * - SellerExpressEligible<br>
 * - ExpressOptOut<br>
 * - ExpressApproved<br>
 * - All other settings
 * <br> <B>Output property:</B> <code>ReturnedCategoryID</code> - ID of the primary category in which the item would be listed.
 * Only returned if you set Item.CategoryMappingAllowed to true in the request
 * and the ID you passed in PrimaryCategory was mapped to a new ID by eBay.
 * If the primary category has not changed or it has expired with no replacement,
 * CategoryID does not return a value.
 * <br> <B>Output property:</B> <code>ReturnedCategory2ID</code> - ID of the secondary category in which the item would be listed.
 * Only returned if you set Item.CategoryMappingAllowed to true in the request
 * and the ID you passed in SecondaryCategory was mapped to a new ID by eBay.
 * If the secondary category has not changed or it has expired with no replacement,
 * Category2ID does not return a value.
 * <br> <B>Output property:</B> <code>ReturnedDiscountReason</code> - The nature of the discount, if a discount would have applied
 * had this actually been listed at this time.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class VerifyAddFixedPriceItemCall extends com.ebay.sdk.ApiCall
{
  
  private ItemType item = null;
  private String returnedItemID=null;
  private String returnedSKU=null;
  private FeesType returnedFees=null;
  private Boolean returnedExpressListing=null;
  private ExpressItemRequirementsType returnedExpressItemRequirements=null;
  private String returnedCategoryID=null;
  private String returnedCategory2ID=null;
  private DiscountReasonCodeType[] returnedDiscountReason=null;

  private final String IMG_URL_FORMAT = "http://i0.ebayimg.ebay.com/i0/00/";
  private final String JPG_EXT = ".jpg";

  private String[] pictureFiles = null;


  /**
   * Constructor.
   */
  public VerifyAddFixedPriceItemCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public VerifyAddFixedPriceItemCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Enables a seller to test the definition of a new fixed-price listing by
   * submitting the definition to eBay without creating a actual listing.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The FeesType object.
   */
  public FeesType verifyAddFixedPriceItem()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    VerifyAddFixedPriceItemRequestType req;
    req = new VerifyAddFixedPriceItemRequestType();

   if( this.pictureFiles != null )
    {
      if( item.getPictureDetails() == null )
      {
    	  PictureDetailsType pictureDetails = new PictureDetailsType();
    	  pictureDetails.setPhotoDisplay(PhotoDisplayCodeType.NONE);
          item.setPictureDetails(pictureDetails);
      }
      
      // Save urls back to item object.
      String[] uris = new String[pictureFiles.length];
      for(int i = 0; i < pictureFiles.length; i ++) {
           uris[i] = IMG_URL_FORMAT + (new Integer(i)).toString() + JPG_EXT;
      }
      item.getPictureDetails().setPictureURL(uris);
    }
    if (this.item != null)
      req.setItem(this.item);

    VerifyAddFixedPriceItemResponseType resp = (VerifyAddFixedPriceItemResponseType) execute(req);

    this.returnedItemID = resp.getItemID();
    this.returnedSKU = resp.getSKU();
    this.returnedFees = resp.getFees();
    this.returnedExpressListing = resp.isExpressListing();
    this.returnedExpressItemRequirements = resp.getExpressItemRequirements();
    this.returnedCategoryID = resp.getCategoryID();
    this.returnedCategory2ID = resp.getCategory2ID();
    this.returnedDiscountReason = resp.getDiscountReason();
    return this.getReturnedFees();
  }

  /**
   * Gets the VerifyAddFixedPriceItemRequestType.item.
   * @return ItemType
   */
  public ItemType getItem()
  {
    return this.item;
  }

  /**
   * Sets the VerifyAddFixedPriceItemRequestType.item.
   * @param item ItemType
   */
  public void setItem(ItemType item)
  {
    this.item = item;
  }
/**
   * Gets list of picture files for the item.
   * @return String[]
   */
  public String[] getPictureFiles() {
    return this.pictureFiles;
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
   * Executes the API call. The picture files that you provided will not be
   * uploaded and some dummy urls will be used instead.
   * @param item ItemType
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return FeesType The estimated fees information.
   */
  public FeesType verifyAddFixedPriceItem(ItemType item)
    throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    VerifyAddFixedPriceItemRequestType request = new VerifyAddFixedPriceItemRequestType();
    request.setItem(item);

   if( this.pictureFiles != null )
    {
      if( item.getPictureDetails() == null )
      {
    	  PictureDetailsType pictureDetails = new PictureDetailsType();
    	  pictureDetails.setPhotoDisplay(PhotoDisplayCodeType.NONE);
          item.setPictureDetails(pictureDetails);
      }
      
      // Save urls back to item object.
      String[] uris = new String[pictureFiles.length];
      for(int i = 0; i < pictureFiles.length; i ++) {
           uris[i] = IMG_URL_FORMAT + (new Integer(i)).toString() + JPG_EXT;
      }
      item.getPictureDetails().setPictureURL(uris);
    }
    
    VerifyAddFixedPriceItemResponseType resp = (VerifyAddFixedPriceItemResponseType)execute(request);
    return resp.getFees();
  }

  /**
   * Valid after executing the API.
   * Gets the returned VerifyAddFixedPriceItemResponseType.returnedCategory2ID.
   * 
   * @return String
   */
  public String getReturnedCategory2ID()
  {
    return this.returnedCategory2ID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned VerifyAddFixedPriceItemResponseType.returnedCategoryID.
   * 
   * @return String
   */
  public String getReturnedCategoryID()
  {
    return this.returnedCategoryID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned VerifyAddFixedPriceItemResponseType.returnedDiscountReason.
   * 
   * @return DiscountReasonCodeType[]
   */
  public DiscountReasonCodeType[] getReturnedDiscountReason()
  {
    return this.returnedDiscountReason;
  }

  /**
   * Valid after executing the API.
   * Gets the returned VerifyAddFixedPriceItemResponseType.returnedExpressItemRequirements.
   * 
   * @return ExpressItemRequirementsType
   */
  public ExpressItemRequirementsType getReturnedExpressItemRequirements()
  {
    return this.returnedExpressItemRequirements;
  }

  /**
   * Valid after executing the API.
   * Gets the returned VerifyAddFixedPriceItemResponseType.returnedExpressListing.
   * 
   * @return Boolean
   */
  public Boolean getReturnedExpressListing()
  {
    return this.returnedExpressListing;
  }

  /**
   * Valid after executing the API.
   * Gets the returned VerifyAddFixedPriceItemResponseType.returnedFees.
   * 
   * @return FeesType
   */
  public FeesType getReturnedFees()
  {
    return this.returnedFees;
  }

  /**
   * Valid after executing the API.
   * Gets the returned VerifyAddFixedPriceItemResponseType.returnedItemID.
   * 
   * @return String
   */
  public String getReturnedItemID()
  {
    return this.returnedItemID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned VerifyAddFixedPriceItemResponseType.returnedSKU.
   * 
   * @return String
   */
  public String getReturnedSKU()
  {
    return this.returnedSKU;
  }

}

