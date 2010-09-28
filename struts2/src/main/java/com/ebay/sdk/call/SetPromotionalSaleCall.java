/*
Copyright (c) 2009 eBay, Inc.

This program is licensed under the terms of the eBay Common Development and 
Distribution License (CDDL) Version 1.0 (the "License") and any subsequent 
version thereof released by eBay.  The then-current version of the License 
can be found at https://www.codebase.ebay.com/Licenses.html and in the 
eBaySDKLicense file that is under the eBay SDK install directory.
*/

package com.ebay.sdk.call;

import java.lang.Long;

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the SetPromotionalSale call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>Action</code> - Specifies adding, removing, or modifying promotional sales. 
 * <br> <B>Input property:</B> <code>PromotionalSaleDetails</code> - Specifies details about the promotional sale.
 * <br> <B>Output property:</B> <code>ReturnedStatus</code> - Contains the status of a promotional sale.
 * <br> <B>Output property:</B> <code>ReturnedPromotionalSaleID</code> - Contains the ID of a promotional sale.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class SetPromotionalSaleCall extends com.ebay.sdk.ApiCall
{
  
  private ModifyActionCodeType action = null;
  private PromotionalSaleType promotionalSaleDetails = null;
  private PromotionalSaleStatusCodeType returnedStatus=null;
  private Long returnedPromotionalSaleID=null;


  /**
   * Constructor.
   */
  public SetPromotionalSaleCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public SetPromotionalSaleCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Creates or modifies a promotional sale. Promotional sales enable sellers 
   * to apply discounts and/or free shipping across many listings. 
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The PromotionalSaleStatusCodeType object.
   */
  public PromotionalSaleStatusCodeType setPromotionalSale()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    SetPromotionalSaleRequestType req;
    req = new SetPromotionalSaleRequestType();
    if (this.action != null)
      req.setAction(this.action);
    if (this.promotionalSaleDetails != null)
      req.setPromotionalSaleDetails(this.promotionalSaleDetails);

    SetPromotionalSaleResponseType resp = (SetPromotionalSaleResponseType) execute(req);

    this.returnedStatus = resp.getStatus();
    this.returnedPromotionalSaleID = resp.getPromotionalSaleID();
    return this.getReturnedStatus();
  }

  /**
   * Gets the SetPromotionalSaleRequestType.action.
   * @return ModifyActionCodeType
   */
  public ModifyActionCodeType getAction()
  {
    return this.action;
  }

  /**
   * Sets the SetPromotionalSaleRequestType.action.
   * @param action ModifyActionCodeType
   */
  public void setAction(ModifyActionCodeType action)
  {
    this.action = action;
  }

  /**
   * Gets the SetPromotionalSaleRequestType.promotionalSaleDetails.
   * @return PromotionalSaleType
   */
  public PromotionalSaleType getPromotionalSaleDetails()
  {
    return this.promotionalSaleDetails;
  }

  /**
   * Sets the SetPromotionalSaleRequestType.promotionalSaleDetails.
   * @param promotionalSaleDetails PromotionalSaleType
   */
  public void setPromotionalSaleDetails(PromotionalSaleType promotionalSaleDetails)
  {
    this.promotionalSaleDetails = promotionalSaleDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned SetPromotionalSaleResponseType.returnedPromotionalSaleID.
   * 
   * @return Long
   */
  public Long getReturnedPromotionalSaleID()
  {
    return this.returnedPromotionalSaleID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned SetPromotionalSaleResponseType.returnedStatus.
   * 
   * @return PromotionalSaleStatusCodeType
   */
  public PromotionalSaleStatusCodeType getReturnedStatus()
  {
    return this.returnedStatus;
  }

}

