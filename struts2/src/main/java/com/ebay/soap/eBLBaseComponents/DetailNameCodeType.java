
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DetailNameCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DetailNameCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="CountryDetails"/>
 *     &lt;enumeration value="CurrencyDetails"/>
 *     &lt;enumeration value="PaymentOptionDetails"/>
 *     &lt;enumeration value="RegionDetails"/>
 *     &lt;enumeration value="ShippingLocationDetails"/>
 *     &lt;enumeration value="ShippingServiceDetails"/>
 *     &lt;enumeration value="SiteDetails"/>
 *     &lt;enumeration value="TaxJurisdiction"/>
 *     &lt;enumeration value="URLDetails"/>
 *     &lt;enumeration value="TimeZoneDetails"/>
 *     &lt;enumeration value="RegionOfOriginDetails"/>
 *     &lt;enumeration value="DispatchTimeMaxDetails"/>
 *     &lt;enumeration value="ItemSpecificDetails"/>
 *     &lt;enumeration value="UnitOfMeasurementDetails"/>
 *     &lt;enumeration value="ShippingPackageDetails"/>
 *     &lt;enumeration value="CustomCode"/>
 *     &lt;enumeration value="ShippingCarrierDetails"/>
 *     &lt;enumeration value="ListingStartPriceDetails"/>
 *     &lt;enumeration value="ReturnPolicyDetails"/>
 *     &lt;enumeration value="BuyerRequirementDetails"/>
 *     &lt;enumeration value="ListingFeatureDetails"/>
 *     &lt;enumeration value="VariationDetails"/>
 *     &lt;enumeration value="ExcludeShippingLocationDetails"/>
 *     &lt;enumeration value="RecoupmentPolicyDetails"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DetailNameCodeType")
@XmlEnum
public enum DetailNameCodeType {


    /**
     * 
     * 						Lists the country code and associated name of the countries supported by
     * 						the eBay system.
     * 					
     * 
     */
    @XmlEnumValue("CountryDetails")
    COUNTRY_DETAILS("CountryDetails"),

    /**
     * 
     * 						Lists the currencies supported by the eBay system.
     * 					
     * 
     */
    @XmlEnumValue("CurrencyDetails")
    CURRENCY_DETAILS("CurrencyDetails"),

    /**
     * 
     * 						Not functional. Do not use this value.
     * 						<br />
     * 						Formerly, this value was used to get details about specific payment options.
     * 					
     * 
     */
    @XmlEnumValue("PaymentOptionDetails")
    PAYMENT_OPTION_DETAILS("PaymentOptionDetails"),

    /**
     * 
     * 						Not functional. Do not use this value.
     * 					
     * 
     */
    @XmlEnumValue("RegionDetails")
    REGION_DETAILS("RegionDetails"),

    /**
     * 
     * 						Lists the regions and locations supported by eBays shipping services.
     * 					
     * 
     */
    @XmlEnumValue("ShippingLocationDetails")
    SHIPPING_LOCATION_DETAILS("ShippingLocationDetails"),

    /**
     * 
     * 						Lists the shipping services supported by the specified eBay site.
     * 					
     * 
     */
    @XmlEnumValue("ShippingServiceDetails")
    SHIPPING_SERVICE_DETAILS("ShippingServiceDetails"),

    /**
     * 
     * 						Lists the available eBay sites and their associated SiteID numbers.
     * 					
     * 
     */
    @XmlEnumValue("SiteDetails")
    SITE_DETAILS("SiteDetails"),

    /**
     * 
     * 						Details the different tax jurisdictions supported by the specified eBay site.
     * 					
     * 
     */
    @XmlEnumValue("TaxJurisdiction")
    TAX_JURISDICTION("TaxJurisdiction"),

    /**
     * 
     * 						Lists the different eBay URLs associated with the specified eBay site.
     * 					
     * 
     */
    @XmlEnumValue("URLDetails")
    URL_DETAILS("URLDetails"),

    /**
     * 
     * 						Lists the details of the time zones supported by the eBay system.
     * 					
     * 
     */
    @XmlEnumValue("TimeZoneDetails")
    TIME_ZONE_DETAILS("TimeZoneDetails"),

    /**
     * 
     * 						Not functional. Do not use this value.<br />
     * 						Details about the region of origin of a listing.
     * 					
     * 
     */
    @XmlEnumValue("RegionOfOriginDetails")
    REGION_OF_ORIGIN_DETAILS("RegionOfOriginDetails"),

    /**
     * 
     * 						Details about maximum dispatch times.
     * 					
     * 
     */
    @XmlEnumValue("DispatchTimeMaxDetails")
    DISPATCH_TIME_MAX_DETAILS("DispatchTimeMaxDetails"),

    /**
     * 
     * 						Details about Item Specifics rules for the specified site.
     * 					
     * 
     */
    @XmlEnumValue("ItemSpecificDetails")
    ITEM_SPECIFIC_DETAILS("ItemSpecificDetails"),

    /**
     * 
     * 					Lists the suggested unit-of-measurement strings to use with Item Specifics
     * 					descriptions.
     * 				
     * 
     */
    @XmlEnumValue("UnitOfMeasurementDetails")
    UNIT_OF_MEASUREMENT_DETAILS("UnitOfMeasurementDetails"),

    /**
     * 
     * 						Lists the various shipping packages supported by the specified site.
     * 					
     * 
     */
    @XmlEnumValue("ShippingPackageDetails")
    SHIPPING_PACKAGE_DETAILS("ShippingPackageDetails"),

    /**
     * 
     * 						Reserved for future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode"),

    /**
     * 
     * 						Lists the shipping carriers supported by the specified site.
     * 					
     * 
     */
    @XmlEnumValue("ShippingCarrierDetails")
    SHIPPING_CARRIER_DETAILS("ShippingCarrierDetails"),

    /**
     * 
     * 						Lists the minimum starting prices for the supported types of eBay listings.
     * 					
     * 
     */
    @XmlEnumValue("ListingStartPriceDetails")
    LISTING_START_PRICE_DETAILS("ListingStartPriceDetails"),

    /**
     * 
     * 						Lists the return policies supported by the specified eBay site.
     * 					
     * 
     */
    @XmlEnumValue("ReturnPolicyDetails")
    RETURN_POLICY_DETAILS("ReturnPolicyDetails"),

    /**
     * 
     * 						Details various eBay-buyer requirements.
     * 					
     * 
     */
    @XmlEnumValue("BuyerRequirementDetails")
    BUYER_REQUIREMENT_DETAILS("BuyerRequirementDetails"),

    /**
     * 
     * 						Details the listing features available for the specified site.
     * 					
     * 
     */
    @XmlEnumValue("ListingFeatureDetails")
    LISTING_FEATURE_DETAILS("ListingFeatureDetails"),

    /**
     * 
     * 						Details the multi-variation listing rules for the site.
     * 					
     * 
     */
    @XmlEnumValue("VariationDetails")
    VARIATION_DETAILS("VariationDetails"),

    /**
     * 
     * 						Lists the locations supported by the ExcludeShipToLocation feature.
     * 						<br />
     * 						The codes reflect the <a href=
     * 						"http://www.iso.org/iso/country_codes/iso_3166_code_lists/english_country_names_and_code_elements.htm"
     * 						>ISO 3166</a> location codes.
     * 					
     * 
     */
    @XmlEnumValue("ExcludeShippingLocationDetails")
    EXCLUDE_SHIPPING_LOCATION_DETAILS("ExcludeShippingLocationDetails"),

    /**
     * 
     * 						Details the recoupment policies of the site.
     * 					
     * 
     */
    @XmlEnumValue("RecoupmentPolicyDetails")
    RECOUPMENT_POLICY_DETAILS("RecoupmentPolicyDetails");
    private final String value;

    DetailNameCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DetailNameCodeType fromValue(String v) {
        for (DetailNameCodeType c: DetailNameCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
