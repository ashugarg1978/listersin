
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ListingDurationCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ListingDurationCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Days_1"/>
 *     &lt;enumeration value="Days_3"/>
 *     &lt;enumeration value="Days_5"/>
 *     &lt;enumeration value="Days_7"/>
 *     &lt;enumeration value="Days_10"/>
 *     &lt;enumeration value="Days_14"/>
 *     &lt;enumeration value="Days_21"/>
 *     &lt;enumeration value="Days_30"/>
 *     &lt;enumeration value="Days_60"/>
 *     &lt;enumeration value="Days_90"/>
 *     &lt;enumeration value="Days_120"/>
 *     &lt;enumeration value="GTC"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ListingDurationCodeType")
@XmlEnum
public enum ListingDurationCodeType {


    /**
     * 
     * 						1 Day - A seller must have a positive feedback rating of 10 or more, or must be
     * 						ID Verified to use the 1-day listing duration on the US site. Typically
     * 						applicable to competitive-bid (Chinese) auction formats (including Real Estate),
     * 						and Personal Offer (Second Chance Offer) actions. Call GetCategoryFeatures to
     * 						determine which listing formats support this duration for the site you are
     * 						using.
     * 						<br><br>
     * 						<span class="tablenote"><strong>Note:</strong>
     * 							As of version 619, Dutch-style (multi-item) competitive-bid auctions are deprecated.
     * 							eBay throws an error if you submit a Dutch item listing with AddItem
     * 							or VerifyAddItem. If you use RelistItem to update a Dutch auction listing,
     * 							eBay generates a warning and resets the Quantity value to 1.
     * 						</span>
     * 						<br>
     * 					
     * 
     */
    @XmlEnumValue("Days_1")
    DAYS_1("Days_1"),

    /**
     * 
     * 						3 Days - Typically applicable to competitive-bid (Chinese) auction formats
     * 						(including Real Estate), Basic Fixed-Price auction formats, and Personal Offer
     * 						(Second Chance Offer) actions. Call GetCategoryFeatures to determine which
     * 						listing formats support this duration for the site you are using.
     * 						<br><br>
     * 						<span class="tablenote"><strong>Note:</strong>
     * 							As of version 619, Dutch-style (multi-item) competitive-bid auctions are deprecated.
     * 							eBay throws an error if you submit a Dutch item listing with AddItem
     * 							or VerifyAddItem. If you use RelistItem to update a Dutch auction listing,
     * 							eBay generates a warning and resets the Quantity value to 1.
     * 						</span>
     * 						<br>
     * 					
     * 
     */
    @XmlEnumValue("Days_3")
    DAYS_3("Days_3"),

    /**
     * 
     * 						5 Days - Typically applicable to competitive-bid (Chinese) auction formats
     * 						(including Real Estate), Basic Fixed-Price auction formats, and Personal Offer
     * 						(Second Chance Offer) actions. Call GetCategoryFeatures to determine which
     * 						listing formats support this duration for the site you are using.
     * 						<br><br>
     * 						<span class="tablenote"><strong>Note:</strong>
     * 							As of version 619, Dutch-style (multi-item) competitive-bid auctions are deprecated.
     * 							eBay throws an error if you submit a Dutch item listing with AddItem
     * 							or VerifyAddItem. If you use RelistItem to update a Dutch auction listing,
     * 							eBay generates a warning and resets the Quantity value to 1.
     * 						</span>
     * 						<br>
     * 					
     * 
     */
    @XmlEnumValue("Days_5")
    DAYS_5("Days_5"),

    /**
     * 
     * 						7 Days - Typically applicable to competitive-bid (Chinese) auction formats
     * 						(including Real Estate), Basic Fixed-Price auction formats, and Personal Offer
     * 						(Second Chance Offer) actions. Call GetCategoryFeatures to determine which
     * 						listing formats support this duration for the site you are using.
     * 						<br><br>
     * 						<span class="tablenote"><strong>Note:</strong>
     * 							As of version 619, Dutch-style (multi-item) competitive-bid auctions are deprecated.
     * 							eBay throws an error if you submit a Dutch item listing with AddItem
     * 							or VerifyAddItem. If you use RelistItem to update a Dutch auction listing,
     * 							eBay generates a warning and resets the Quantity value to 1.
     * 						</span>
     * 						<br>
     * 					
     * 
     */
    @XmlEnumValue("Days_7")
    DAYS_7("Days_7"),

    /**
     * 
     * 						10 Days - Subject to an additional listing upgrade fee. Typically applicable to
     * 						competitive-bid (Chinese) auction formats (including Real Estate) and Basic
     * 						Fixed-Price auction formats. Call GetCategoryFeatures to determine which listing
     * 						formats support this duration for the site you are using.
     * 						<br><br>
     * 						<span class="tablenote"><strong>Note:</strong>
     * 							As of version 619, Dutch-style (multi-item) competitive-bid auctions are deprecated.
     * 							eBay throws an error if you submit a Dutch item listing with AddItem
     * 							or VerifyAddItem. If you use RelistItem to update a Dutch auction listing,
     * 							eBay generates a warning and resets the Quantity value to 1.
     * 						</span>
     * 						<br>
     * 					
     * 
     */
    @XmlEnumValue("Days_10")
    DAYS_10("Days_10"),

    /**
     * 
     * 						14 Days - Subject to an additional listing upgrade fee.
     * 						Call GetCategoryFeatures to determine which listing formats
     * 						support this duration for the site you are using.
     * 					
     * 
     */
    @XmlEnumValue("Days_14")
    DAYS_14("Days_14"),
    @XmlEnumValue("Days_21")
    DAYS_21("Days_21"),

    /**
     * 
     * 						30 Days - Typically applicable to Real Estate Ad format and Store Inventory
     * 						format. Call GetCategoryFeatures to determine which listing formats
     * 						support this duration for the site you are using.
     * 					
     * 
     */
    @XmlEnumValue("Days_30")
    DAYS_30("Days_30"),

    /**
     * 
     * 						Call GetCategoryFeatures to determine which listing formats
     * 						support this duration for the site you are using.
     * 					
     * 
     */
    @XmlEnumValue("Days_60")
    DAYS_60("Days_60"),

    /**
     * 
     * 						90 Days - Typically applicable to Real Estate Ad format.
     * 						Call GetCategoryFeatures to determine which listing formats
     * 						support this duration for the site you are using.
     * 					
     * 
     */
    @XmlEnumValue("Days_90")
    DAYS_90("Days_90"),

    /**
     * 
     * 						(in/out)	Call GetCategoryFeatures to determine which listing formats
     * 						support this duration for the site you are using.
     * 					
     * 
     */
    @XmlEnumValue("Days_120")
    DAYS_120("Days_120"),

    /**
     * 
     * 						GTC  - Relist automatically every 30 days until all the items sell or the seller
     * 						ends the listing. Typically applicable to Store Inventory format and Half.com
     * 						listings. This duration is not applicable to AdFormat listing type. Call
     * 						GetCategoryFeatures to determine which listing formats support this duration for
     * 						the site you are using.
     * 					
     * 
     */
    GTC("GTC"),

    /**
     * 
     * 						Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    ListingDurationCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ListingDurationCodeType fromValue(String v) {
        for (ListingDurationCodeType c: ListingDurationCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
