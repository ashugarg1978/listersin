/*
Copyright (c) 2009 eBay, Inc.

This program is licensed under the terms of the eBay Common Development and 
Distribution License (CDDL) Version 1.0 (the "License") and any subsequent 
version thereof released by eBay.  The then-current version of the License 
can be found at https://www.codebase.ebay.com/Licenses.html and in the 
eBaySDKLicense file that is under the eBay SDK install directory.
*/

package com.ebay.sdk.call;

import java.lang.Integer;
import java.lang.String;

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the GetItemShipping call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>ItemID</code> - The item ID that uniquely identifies the item listing for which
 * to retrieve the data. Required input.
 * <br> <B>Input property:</B> <code>QuantitySold</code> - Number of items sold to a single buyer and to be shipped together.
 * <br> <B>Input property:</B> <code>DestinationPostalCode</code> - Destination country postal code (or zipcode, for US). Ignored if no
 * country code is provided. Optional tag for some countries. More likely to
 * be required for large countries.
 * <br> <B>Input property:</B> <code>DestinationCountryCode</code> - Destination country code. If DestinationCountryCode is US,
 * a postal code is required and it represents the US zip code.
 * <br> <B>Output property:</B> <code>ReturnedShippingDetails</code> - Shipping-related details for the specified item. Any error about shipping services
 * (returned by a vendor of eBay's who calculates shipping costs) is returned in
 * ShippingRateErrorMessage. Errors from a shipping service are likely to be related to
 * issues with shipping specifications, such as package size and the selected shipping
 * method not supported by a particular shipping service.<br>
 * <br>It is possible for a seller to offer a shipping service which turns out
 * to be a mismatch in some way with the item(s) being shipped. For example, if a
 * buyer purchases more than one of an item, the weight might be too great for First
 * Class mail. In such a case, GetItemShipping will omit that shipping service from
 * the response.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class GetItemShippingCall extends com.ebay.sdk.ApiCall
{
  
  private String itemID = null;
  private Integer quantitySold = null;
  private String destinationPostalCode = null;
  private CountryCodeType destinationCountryCode = null;
  private ShippingDetailsType returnedShippingDetails=null;


  /**
   * Constructor.
   */
  public GetItemShippingCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public GetItemShippingCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Returns shipping cost estimates for an item for every calculated shipping service
   * that the seller has offered with the listing. This is analogous to the Shipping
   * Calculator seen in both the buyer and seller web pages.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The ShippingDetailsType object.
   */
  public ShippingDetailsType getItemShipping()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GetItemShippingRequestType req;
    req = new GetItemShippingRequestType();
    req.setDetailLevel(this.getDetailLevel());
    if (this.itemID != null)
      req.setItemID(this.itemID);
    if (this.quantitySold != null)
      req.setQuantitySold(this.quantitySold);
    if (this.destinationPostalCode != null)
      req.setDestinationPostalCode(this.destinationPostalCode);
    if (this.destinationCountryCode != null)
      req.setDestinationCountryCode(this.destinationCountryCode);

    GetItemShippingResponseType resp = (GetItemShippingResponseType) execute(req);

    this.returnedShippingDetails = resp.getShippingDetails();
    return this.getReturnedShippingDetails();
  }

  /**
   * Gets the GetItemShippingRequestType.destinationCountryCode.
   * @return CountryCodeType
   */
  public CountryCodeType getDestinationCountryCode()
  {
    return this.destinationCountryCode;
  }

  /**
   * Sets the GetItemShippingRequestType.destinationCountryCode.
   * @param destinationCountryCode CountryCodeType
   */
  public void setDestinationCountryCode(CountryCodeType destinationCountryCode)
  {
    this.destinationCountryCode = destinationCountryCode;
  }

  /**
   * Gets the GetItemShippingRequestType.destinationPostalCode.
   * @return String
   */
  public String getDestinationPostalCode()
  {
    return this.destinationPostalCode;
  }

  /**
   * Sets the GetItemShippingRequestType.destinationPostalCode.
   * @param destinationPostalCode String
   */
  public void setDestinationPostalCode(String destinationPostalCode)
  {
    this.destinationPostalCode = destinationPostalCode;
  }

  /**
   * Gets the GetItemShippingRequestType.itemID.
   * @return String
   */
  public String getItemID()
  {
    return this.itemID;
  }

  /**
   * Sets the GetItemShippingRequestType.itemID.
   * @param itemID String
   */
  public void setItemID(String itemID)
  {
    this.itemID = itemID;
  }

  /**
   * Gets the GetItemShippingRequestType.quantitySold.
   * @return Integer
   */
  public Integer getQuantitySold()
  {
    return this.quantitySold;
  }

  /**
   * Sets the GetItemShippingRequestType.quantitySold.
   * @param quantitySold Integer
   */
  public void setQuantitySold(Integer quantitySold)
  {
    this.quantitySold = quantitySold;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetItemShippingResponseType.returnedShippingDetails.
   * 
   * @return ShippingDetailsType
   */
  public ShippingDetailsType getReturnedShippingDetails()
  {
    return this.returnedShippingDetails;
  }

}

