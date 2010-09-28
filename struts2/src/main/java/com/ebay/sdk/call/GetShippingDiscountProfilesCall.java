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
 * Wrapper class of the GetShippingDiscountProfiles call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Output property:</B> <code>ReturnedCurrencyID</code> - The ID of the currency to be used for shipping cost discounts and
 * insurance for combined payment. A discount profile can only be associated
 * with a listing if the currency ID of the profile matches the currency ID
 * of the listing.
 * <br> <B>Output property:</B> <code>ReturnedFlatShippingDiscount</code> - Details of an individual discount profile defined by the
 * user for flat rate shipping--one for each profile defined by the user.
 * Empty if no shipping discount profiles were defined.
 * <br> <B>Output property:</B> <code>ReturnedCalculatedShippingDiscount</code> - Details of an individual discount profile defined by the
 * user for calculated shipping--one for each profile defined by the user.
 * Empty if no shipping discount profiles were defined.
 * <br> <B>Output property:</B> <code>ReturnedPromotionalShippingDiscount</code> - Indicates whether the user defined a promotional discount (the discount is active
 * as soon as it exists).
 * <br> <B>Output property:</B> <code>ReturnedCalculatedHandlingDiscount</code> - The data for the specific packaging/handling details for combined payment.
 * Returned only if it has been defined.
 * <br> <B>Output property:</B> <code>ReturnedPromotionalShippingDiscountDetails</code> - The data for the specific promotional shipping discount.
 * Returned only if it has been defined.
 * <br> <B>Output property:</B> <code>ReturnedShippingInsurance</code> - The data for the domestic insurance for combined payment.
 * Returned only if it has been defined.
 * <br> <B>Output property:</B> <code>ReturnedInternationalShippingInsurance</code> - The data for the international insurance for combined payment.
 * Returned only if it has been defined.
 * <br> <B>Output property:</B> <code>ReturnedCombinedDuration</code> - Only those items purchased within CombinedDuration days of each other can
 * be combined into an order and benefit from any shipping discounts.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class GetShippingDiscountProfilesCall extends com.ebay.sdk.ApiCall
{
  
  private CurrencyCodeType returnedCurrencyID=null;
  private FlatShippingDiscountType returnedFlatShippingDiscount=null;
  private CalculatedShippingDiscountType returnedCalculatedShippingDiscount=null;
  private Boolean returnedPromotionalShippingDiscount=null;
  private CalculatedHandlingDiscountType returnedCalculatedHandlingDiscount=null;
  private PromotionalShippingDiscountDetailsType returnedPromotionalShippingDiscountDetails=null;
  private ShippingInsuranceType returnedShippingInsurance=null;
  private ShippingInsuranceType returnedInternationalShippingInsurance=null;
  private CombinedPaymentPeriodCodeType returnedCombinedDuration=null;


  /**
   * Constructor.
   */
  public GetShippingDiscountProfilesCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public GetShippingDiscountProfilesCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Returns the shipping discount profiles defined by the user, along with other combined
   * payment-related details such as packaging and handling costs.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The CurrencyCodeType object.
   */
  public CurrencyCodeType getShippingDiscountProfiles()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GetShippingDiscountProfilesRequestType req;
    req = new GetShippingDiscountProfilesRequestType();

    GetShippingDiscountProfilesResponseType resp = (GetShippingDiscountProfilesResponseType) execute(req);

    this.returnedCurrencyID = resp.getCurrencyID();
    this.returnedFlatShippingDiscount = resp.getFlatShippingDiscount();
    this.returnedCalculatedShippingDiscount = resp.getCalculatedShippingDiscount();
    this.returnedPromotionalShippingDiscount = resp.isPromotionalShippingDiscount();
    this.returnedCalculatedHandlingDiscount = resp.getCalculatedHandlingDiscount();
    this.returnedPromotionalShippingDiscountDetails = resp.getPromotionalShippingDiscountDetails();
    this.returnedShippingInsurance = resp.getShippingInsurance();
    this.returnedInternationalShippingInsurance = resp.getInternationalShippingInsurance();
    this.returnedCombinedDuration = resp.getCombinedDuration();
    return this.getReturnedCurrencyID();
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetShippingDiscountProfilesResponseType.returnedCalculatedHandlingDiscount.
   * 
   * @return CalculatedHandlingDiscountType
   */
  public CalculatedHandlingDiscountType getReturnedCalculatedHandlingDiscount()
  {
    return this.returnedCalculatedHandlingDiscount;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetShippingDiscountProfilesResponseType.returnedCalculatedShippingDiscount.
   * 
   * @return CalculatedShippingDiscountType
   */
  public CalculatedShippingDiscountType getReturnedCalculatedShippingDiscount()
  {
    return this.returnedCalculatedShippingDiscount;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetShippingDiscountProfilesResponseType.returnedCombinedDuration.
   * 
   * @return CombinedPaymentPeriodCodeType
   */
  public CombinedPaymentPeriodCodeType getReturnedCombinedDuration()
  {
    return this.returnedCombinedDuration;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetShippingDiscountProfilesResponseType.returnedCurrencyID.
   * 
   * @return CurrencyCodeType
   */
  public CurrencyCodeType getReturnedCurrencyID()
  {
    return this.returnedCurrencyID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetShippingDiscountProfilesResponseType.returnedFlatShippingDiscount.
   * 
   * @return FlatShippingDiscountType
   */
  public FlatShippingDiscountType getReturnedFlatShippingDiscount()
  {
    return this.returnedFlatShippingDiscount;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetShippingDiscountProfilesResponseType.returnedInternationalShippingInsurance.
   * 
   * @return ShippingInsuranceType
   */
  public ShippingInsuranceType getReturnedInternationalShippingInsurance()
  {
    return this.returnedInternationalShippingInsurance;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetShippingDiscountProfilesResponseType.returnedPromotionalShippingDiscount.
   * 
   * @return Boolean
   */
  public Boolean getReturnedPromotionalShippingDiscount()
  {
    return this.returnedPromotionalShippingDiscount;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetShippingDiscountProfilesResponseType.returnedPromotionalShippingDiscountDetails.
   * 
   * @return PromotionalShippingDiscountDetailsType
   */
  public PromotionalShippingDiscountDetailsType getReturnedPromotionalShippingDiscountDetails()
  {
    return this.returnedPromotionalShippingDiscountDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetShippingDiscountProfilesResponseType.returnedShippingInsurance.
   * 
   * @return ShippingInsuranceType
   */
  public ShippingInsuranceType getReturnedShippingInsurance()
  {
    return this.returnedShippingInsurance;
  }

}

