
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ListingSubtypeCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ListingSubtypeCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="ClassifiedAd"/>
 *     &lt;enumeration value="LocalMarketBestOfferOnly"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ListingSubtypeCodeType")
@XmlEnum
public enum ListingSubtypeCodeType {


    /**
     * 
     * 					General classified ad listing subtype.
     * 				
     * 
     */
    @XmlEnumValue("ClassifiedAd")
    CLASSIFIED_AD("ClassifiedAd"),

    /**
     * 
     * 					General LocalMarketBestOfferOnly listing subtype.
     * 				
     * 
     */
    @XmlEnumValue("LocalMarketBestOfferOnly")
    LOCAL_MARKET_BEST_OFFER_ONLY("LocalMarketBestOfferOnly"),

    /**
     * 
     * 					Reserved for internal or future use
     * 				
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    ListingSubtypeCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ListingSubtypeCodeType fromValue(String v) {
        for (ListingSubtypeCodeType c: ListingSubtypeCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
