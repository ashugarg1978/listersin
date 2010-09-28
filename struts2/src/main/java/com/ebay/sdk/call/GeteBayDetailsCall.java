/*
Copyright (c) 2009 eBay, Inc.

This program is licensed under the terms of the eBay Common Development and 
Distribution License (CDDL) Version 1.0 (the "License") and any subsequent 
version thereof released by eBay.  The then-current version of the License 
can be found at https://www.codebase.ebay.com/Licenses.html and in the 
eBaySDKLicense file that is under the eBay SDK install directory.
*/

package com.ebay.sdk.call;

import java.util.Calendar;

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the GeteBayDetails call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>DetailName</code> - A designation of what kind of information you wish returned for the
 * specified eBay site. If omitted, all details are returned. The
 * possible values for input (the enumeration values of
 * DetailNameCodeType) are the same name as fields returned by the
 * response. See the documentation for the GeteBayDetails response to
 * better understand the DetailName options.
 * <br> <B>Output property:</B> <code>ReturnedCountryDetails</code> - Lists the country code and associated name of the countries supported by
 * the eBay system, regardless of the site specified in the request.
 * <br> <B>Output property:</B> <code>ReturnedCurrencyDetails</code> - Lists the currencies supported by the eBay system, regardless of the site
 * specified in the request.
 * <br> <B>Output property:</B> <code>ReturnedDispatchTimeMaxDetails</code> - A dispatch time specifies the maximum number of business days a seller commits
 * to for shipping an item to domestic buyers after receiving a cleared payment.
 * Returns all dispatch times in the system, regardless of the site specified in
 * the request.
 * <br> <B>Output property:</B> <code>ReturnedPaymentOptionDetails</code> - The values returned in this field detail different payment options.
 * <br />
 * This feature is no longer in use. If you need valid payment option
 * information, use GetCategoryFeatures instead.
 * <br> <B>Output property:</B> <code>ReturnedRegionDetails</code> - No longer returned.
 * <br> <B>Output property:</B> <code>ReturnedShippingLocationDetails</code> - Lists the regions and locations supported by eBay's shipping services. Returns
 * all shipping locations supported by eBay, regardless of the site specified in
 * the request.
 * <br> <B>Output property:</B> <code>ReturnedShippingServiceDetails</code> - Lists the shipping services supported by the specified eBay site. Returns only
 * the shipping services that are applicable to the site specified in the
 * request.
 * <br> <B>Output property:</B> <code>ReturnedSiteDetails</code> - Lists all available eBay sites and their associated SiteID numbers.
 * <br> <B>Output property:</B> <code>ReturnedTaxJurisdiction</code> - Details the different tax jurisdictions or tax regions applicable to the
 * site specified in the request.
 * <br><br>
 * Related fields:<br>
 * TaxTable.TaxJurisdiction in SetTaxTable<br>
 * Item.UseTaxTable in Additem
 * <br> <B>Output property:</B> <code>ReturnedURLDetails</code> - Lists eBay URLs that are applicable to the site specified in the request.
 * <br> <B>Output property:</B> <code>ReturnedTimeZoneDetails</code> - Lists the details of the time zones supported by the eBay system.
 * <br> <B>Output property:</B> <code>ReturnedItemSpecificDetails</code> - The site's validation rules (e.g., string lengths) for custom Item Specifics.
 * <br> <B>Output property:</B> <code>ReturnedUnitOfMeasurementDetails</code> - Lists the suggested unit-of-measurement strings to use with Item Specifics
 * descriptions.
 * <br> <B>Output property:</B> <code>ReturnedRegionOfOriginDetails</code> - No longer returned.
 * <br> <B>Output property:</B> <code>ReturnedShippingPackageDetails</code> - Lists the various shipping packages supported by the specified site.
 * <br> <B>Output property:</B> <code>ReturnedShippingCarrierDetails</code> - Lists the shipping carriers supported by the specified site.
 * <br> <B>Output property:</B> <code>ReturnedReturnPolicyDetails</code> - Lists the return policies supported by the eBay site specified in the request.
 * <br> <B>Output property:</B> <code>ReturnedListingStartPriceDetails</code> - Lists the minimum starting prices for the supported types of eBay listings.
 * <br> <B>Output property:</B> <code>ReturnedBuyerRequirementDetails</code> - Details various eBay-buyer requirements.
 * <br> <B>Output property:</B> <code>ReturnedListingFeatureDetails</code> - Details the listing features available for the site specified in the request.
 * <br> <B>Output property:</B> <code>ReturnedVariationDetails</code> - Site-level validation rules for multi-variation listings (for example, the
 * maximum number of variations per listing). Use GetCategoryFeatures to
 * determine which categories on a site support variations. Use
 * GetCategorySpecifics for rules related to recommended or required variation
 * specifics.
 * <br> <B>Output property:</B> <code>ReturnedExcludeShippingLocationDetails</code> - Lists the locations supported by the ExcludeShipToLocation feature. These are
 * locations that a seller can list as areas where they will not ship an item.
 * <br />
 * The codes reflect the <a href=
 * "http://www.iso.org/iso/country_codes/iso_3166_code_lists/english_country_names_and_code_elements.htm"
 * >ISO 3166</a> location codes.
 * <br> <B>Output property:</B> <code>ReturnedUpdateTime</code> - Time in GMT of the most recent modification to any feature detail. If specific
 * feature details are passed in the request, gives most recent modification time
 * of the passed feature details.
 * <br> <B>Output property:</B> <code>ReturnedRecoupmentPolicyDetails</code> - Details the recoupment policies for the site specified in the request.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class GeteBayDetailsCall extends com.ebay.sdk.ApiCall
{
  
  private DetailNameCodeType[] detailName = null;
  private CountryDetailsType[] returnedCountryDetails=null;
  private CurrencyDetailsType[] returnedCurrencyDetails=null;
  private DispatchTimeMaxDetailsType[] returnedDispatchTimeMaxDetails=null;
  private PaymentOptionDetailsType[] returnedPaymentOptionDetails=null;
  private RegionDetailsType[] returnedRegionDetails=null;
  private ShippingLocationDetailsType[] returnedShippingLocationDetails=null;
  private ShippingServiceDetailsType[] returnedShippingServiceDetails=null;
  private SiteDetailsType[] returnedSiteDetails=null;
  private TaxJurisdictionType[] returnedTaxJurisdiction=null;
  private URLDetailsType[] returnedURLDetails=null;
  private TimeZoneDetailsType[] returnedTimeZoneDetails=null;
  private ItemSpecificDetailsType[] returnedItemSpecificDetails=null;
  private UnitOfMeasurementDetailsType[] returnedUnitOfMeasurementDetails=null;
  private RegionOfOriginDetailsType[] returnedRegionOfOriginDetails=null;
  private ShippingPackageDetailsType[] returnedShippingPackageDetails=null;
  private ShippingCarrierDetailsType[] returnedShippingCarrierDetails=null;
  private ReturnPolicyDetailsType returnedReturnPolicyDetails=null;
  private ListingStartPriceDetailsType[] returnedListingStartPriceDetails=null;
  private SiteBuyerRequirementDetailsType[] returnedBuyerRequirementDetails=null;
  private ListingFeatureDetailsType[] returnedListingFeatureDetails=null;
  private VariationDetailsType returnedVariationDetails=null;
  private ExcludeShippingLocationDetailsType[] returnedExcludeShippingLocationDetails=null;
  private Calendar returnedUpdateTime=null;
  private RecoupmentPolicyDetailsType[] returnedRecoupmentPolicyDetails=null;


  /**
   * Constructor.
   */
  public GeteBayDetailsCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public GeteBayDetailsCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Retrieves eBay IDs and codes (e.g., site IDs and shipping service
   * codes), enumerated data (e.g., payment methods), and other common eBay
   * meta-data. This call enables you to keep certain data up to date in your
   * applications without referring to the schema, the documentation, or the
   * eBay online help. Other data is returned for your reference, but you may
   * need to refer to the schema or the documentation for information about
   * valid values and usage.
   * <br><br>
   * In some cases, the data returned in the response will vary according to
   * the site that you use for the request.
   * <br><br>
   * If you use GeteBayDetails in preparation for listing in the US Motors Parts
   * and Accessories categories, use site ID 0 (which is the site ID of the US
   * site) when you call GeteBayDetails.
   * <br><br>
   * Sellers who engage in cross-border trade on sites that require a recoupment agreement, must agree to the
   * recoupment terms before adding items to the site. This agreement allows eBay to reimburse
   * a buyer during a dispute and then recoup the cost from the seller. Information about whether a site
   * is a recoupment site is returned in the GeteBayDetailsResponse.RecoupmentPolicyDetails container.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The void object.
   */
  public void geteBayDetails()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GeteBayDetailsRequestType req;
    req = new GeteBayDetailsRequestType();
    req.setDetailLevel(this.getDetailLevel());
    if (this.detailName != null)
      req.setDetailName(this.detailName);

    GeteBayDetailsResponseType resp = (GeteBayDetailsResponseType) execute(req);

    this.returnedCountryDetails = resp.getCountryDetails();
    this.returnedCurrencyDetails = resp.getCurrencyDetails();
    this.returnedDispatchTimeMaxDetails = resp.getDispatchTimeMaxDetails();
    this.returnedPaymentOptionDetails = resp.getPaymentOptionDetails();
    this.returnedRegionDetails = resp.getRegionDetails();
    this.returnedShippingLocationDetails = resp.getShippingLocationDetails();
    this.returnedShippingServiceDetails = resp.getShippingServiceDetails();
    this.returnedSiteDetails = resp.getSiteDetails();
    this.returnedTaxJurisdiction = resp.getTaxJurisdiction();
    this.returnedURLDetails = resp.getURLDetails();
    this.returnedTimeZoneDetails = resp.getTimeZoneDetails();
    this.returnedItemSpecificDetails = resp.getItemSpecificDetails();
    this.returnedUnitOfMeasurementDetails = resp.getUnitOfMeasurementDetails();
    this.returnedRegionOfOriginDetails = resp.getRegionOfOriginDetails();
    this.returnedShippingPackageDetails = resp.getShippingPackageDetails();
    this.returnedShippingCarrierDetails = resp.getShippingCarrierDetails();
    this.returnedReturnPolicyDetails = resp.getReturnPolicyDetails();
    this.returnedListingStartPriceDetails = resp.getListingStartPriceDetails();
    this.returnedBuyerRequirementDetails = resp.getBuyerRequirementDetails();
    this.returnedListingFeatureDetails = resp.getListingFeatureDetails();
    this.returnedVariationDetails = resp.getVariationDetails();
    this.returnedExcludeShippingLocationDetails = resp.getExcludeShippingLocationDetails();
    this.returnedUpdateTime = resp.getUpdateTime();
    this.returnedRecoupmentPolicyDetails = resp.getRecoupmentPolicyDetails();

  }

  /**
   * Gets the GeteBayDetailsRequestType.detailName.
   * @return DetailNameCodeType[]
   */
  public DetailNameCodeType[] getDetailName()
  {
    return this.detailName;
  }

  /**
   * Sets the GeteBayDetailsRequestType.detailName.
   * @param detailName DetailNameCodeType[]
   */
  public void setDetailName(DetailNameCodeType[] detailName)
  {
    this.detailName = detailName;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.
   * 
   * @return GeteBayDetailsResponseType
   */
  public GeteBayDetailsResponseType getReturnedeBayDetails()
  {
    return (GeteBayDetailsResponseType) this.getResponseObject();
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedBuyerRequirementDetails.
   * 
   * @return SiteBuyerRequirementDetailsType[]
   */
  public SiteBuyerRequirementDetailsType[] getReturnedBuyerRequirementDetails()
  {
    return this.returnedBuyerRequirementDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedCountryDetails.
   * 
   * @return CountryDetailsType[]
   */
  public CountryDetailsType[] getReturnedCountryDetails()
  {
    return this.returnedCountryDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedCurrencyDetails.
   * 
   * @return CurrencyDetailsType[]
   */
  public CurrencyDetailsType[] getReturnedCurrencyDetails()
  {
    return this.returnedCurrencyDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedDispatchTimeMaxDetails.
   * 
   * @return DispatchTimeMaxDetailsType[]
   */
  public DispatchTimeMaxDetailsType[] getReturnedDispatchTimeMaxDetails()
  {
    return this.returnedDispatchTimeMaxDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedExcludeShippingLocationDetails.
   * 
   * @return ExcludeShippingLocationDetailsType[]
   */
  public ExcludeShippingLocationDetailsType[] getReturnedExcludeShippingLocationDetails()
  {
    return this.returnedExcludeShippingLocationDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedItemSpecificDetails.
   * 
   * @return ItemSpecificDetailsType[]
   */
  public ItemSpecificDetailsType[] getReturnedItemSpecificDetails()
  {
    return this.returnedItemSpecificDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedListingFeatureDetails.
   * 
   * @return ListingFeatureDetailsType[]
   */
  public ListingFeatureDetailsType[] getReturnedListingFeatureDetails()
  {
    return this.returnedListingFeatureDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedListingStartPriceDetails.
   * 
   * @return ListingStartPriceDetailsType[]
   */
  public ListingStartPriceDetailsType[] getReturnedListingStartPriceDetails()
  {
    return this.returnedListingStartPriceDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedPaymentOptionDetails.
   * 
   * @return PaymentOptionDetailsType[]
   */
  public PaymentOptionDetailsType[] getReturnedPaymentOptionDetails()
  {
    return this.returnedPaymentOptionDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedRecoupmentPolicyDetails.
   * 
   * @return RecoupmentPolicyDetailsType[]
   */
  public RecoupmentPolicyDetailsType[] getReturnedRecoupmentPolicyDetails()
  {
    return this.returnedRecoupmentPolicyDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedRegionDetails.
   * 
   * @return RegionDetailsType[]
   */
  public RegionDetailsType[] getReturnedRegionDetails()
  {
    return this.returnedRegionDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedRegionOfOriginDetails.
   * 
   * @return RegionOfOriginDetailsType[]
   */
  public RegionOfOriginDetailsType[] getReturnedRegionOfOriginDetails()
  {
    return this.returnedRegionOfOriginDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedReturnPolicyDetails.
   * 
   * @return ReturnPolicyDetailsType
   */
  public ReturnPolicyDetailsType getReturnedReturnPolicyDetails()
  {
    return this.returnedReturnPolicyDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedShippingCarrierDetails.
   * 
   * @return ShippingCarrierDetailsType[]
   */
  public ShippingCarrierDetailsType[] getReturnedShippingCarrierDetails()
  {
    return this.returnedShippingCarrierDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedShippingLocationDetails.
   * 
   * @return ShippingLocationDetailsType[]
   */
  public ShippingLocationDetailsType[] getReturnedShippingLocationDetails()
  {
    return this.returnedShippingLocationDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedShippingPackageDetails.
   * 
   * @return ShippingPackageDetailsType[]
   */
  public ShippingPackageDetailsType[] getReturnedShippingPackageDetails()
  {
    return this.returnedShippingPackageDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedShippingServiceDetails.
   * 
   * @return ShippingServiceDetailsType[]
   */
  public ShippingServiceDetailsType[] getReturnedShippingServiceDetails()
  {
    return this.returnedShippingServiceDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedSiteDetails.
   * 
   * @return SiteDetailsType[]
   */
  public SiteDetailsType[] getReturnedSiteDetails()
  {
    return this.returnedSiteDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedTaxJurisdiction.
   * 
   * @return TaxJurisdictionType[]
   */
  public TaxJurisdictionType[] getReturnedTaxJurisdiction()
  {
    return this.returnedTaxJurisdiction;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedTimeZoneDetails.
   * 
   * @return TimeZoneDetailsType[]
   */
  public TimeZoneDetailsType[] getReturnedTimeZoneDetails()
  {
    return this.returnedTimeZoneDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedURLDetails.
   * 
   * @return URLDetailsType[]
   */
  public URLDetailsType[] getReturnedURLDetails()
  {
    return this.returnedURLDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedUnitOfMeasurementDetails.
   * 
   * @return UnitOfMeasurementDetailsType[]
   */
  public UnitOfMeasurementDetailsType[] getReturnedUnitOfMeasurementDetails()
  {
    return this.returnedUnitOfMeasurementDetails;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedUpdateTime.
   * 
   * @return Calendar
   */
  public Calendar getReturnedUpdateTime()
  {
    return this.returnedUpdateTime;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GeteBayDetailsResponseType.returnedVariationDetails.
   * 
   * @return VariationDetailsType
   */
  public VariationDetailsType getReturnedVariationDetails()
  {
    return this.returnedVariationDetails;
  }

}

