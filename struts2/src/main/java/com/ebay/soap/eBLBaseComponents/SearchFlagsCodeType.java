
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchFlagsCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SearchFlagsCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Charity"/>
 *     &lt;enumeration value="SearchInDescription"/>
 *     &lt;enumeration value="PayPalBuyerPaymentOption"/>
 *     &lt;enumeration value="NowAndNew"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SearchFlagsCodeType")
@XmlEnum
public enum SearchFlagsCodeType {


    /**
     * 
     * 						Return only charity item listings.
     * 					
     * 
     */
    @XmlEnumValue("Charity")
    CHARITY("Charity"),

    /**
     * 
     * 						Include the description field of item listings in keyword search. Item
     * 						listings returned are those where specified search keywords appear in
     * 						the description, as well as the title and sub-title.
     * 					
     * 
     */
    @XmlEnumValue("SearchInDescription")
    SEARCH_IN_DESCRIPTION("SearchInDescription"),

    /**
     * 
     * 						Return only item listings where PayPal is a payment method offered by the
     * 						seller.
     * 					
     * 
     */
    @XmlEnumValue("PayPalBuyerPaymentOption")
    PAY_PAL_BUYER_PAYMENT_OPTION("PayPalBuyerPaymentOption"),

    /**
     * 
     * 						(in) Return only items that have been listed with Now and New. Applicable for
     * 						certain sites only. See the eBay Web Services Guide.
     * 					
     * 
     */
    @XmlEnumValue("NowAndNew")
    NOW_AND_NEW("NowAndNew"),

    /**
     * 
     * 						(out) Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    SearchFlagsCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SearchFlagsCodeType fromValue(String v) {
        for (SearchFlagsCodeType c: SearchFlagsCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
