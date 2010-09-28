
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchFlagCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SearchFlagCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Charity"/>
 *     &lt;enumeration value="Gift"/>
 *     &lt;enumeration value="NowAndNew"/>
 *     &lt;enumeration value="LocalSearch"/>
 *     &lt;enumeration value="FreeShipping"/>
 *     &lt;enumeration value="Gallery"/>
 *     &lt;enumeration value="Picture"/>
 *     &lt;enumeration value="GetItFast"/>
 *     &lt;enumeration value="Lot"/>
 *     &lt;enumeration value="GermanMotorsSearchable"/>
 *     &lt;enumeration value="WorldOfGood"/>
 *     &lt;enumeration value="DigitalDelivery"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SearchFlagCodeType")
@XmlEnum
public enum SearchFlagCodeType {


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
     * 						Return only gift items.
     * 					
     * 
     */
    @XmlEnumValue("Gift")
    GIFT("Gift"),

    /**
     * 
     * 						Return only items that have been listed with Now and New. Applicable for
     * 						certain sites only.
     * 					
     * 
     */
    @XmlEnumValue("NowAndNew")
    NOW_AND_NEW("NowAndNew"),

    /**
     * 
     * 						Perform a local search.
     * 					
     * 
     */
    @XmlEnumValue("LocalSearch")
    LOCAL_SEARCH("LocalSearch"),

    /**
     * 
     * 						If specified, only items with free shipping for the user's location are
     * 						returned. The user's location is determined from the site ID specified
     * 						in the request. If false, no filtering is done via this attribute. A
     * 						listing is not considered a free shipping listing if it requires
     * 						insurance or requires pick up or requires a shipping surcharge.
     * 					
     * 
     */
    @XmlEnumValue("FreeShipping")
    FREE_SHIPPING("FreeShipping"),

    /**
     * 
     * 						Return Gallery items only.
     * 					
     * 
     */
    @XmlEnumValue("Gallery")
    GALLERY("Gallery"),

    /**
     * 
     * 						Picture.
     * 					
     * 
     */
    @XmlEnumValue("Picture")
    PICTURE("Picture"),

    /**
     * 
     * 						Limits the results to Get It Fast listings.
     * 					
     * 
     */
    @XmlEnumValue("GetItFast")
    GET_IT_FAST("GetItFast"),

    /**
     * 
     * 						Limits the results to only those listings for which lot size is 2 or greater.
     * 					
     * 
     */
    @XmlEnumValue("Lot")
    LOT("Lot"),

    /**
     * 
     * 						Limits the results based on each item's eligibility to appear on the
     * 						mobile.de site. If
     * 						specified, queries for eligible items only. If not specified, the search
     * 						results are not affected. Only applicable for items listed on the eBay
     * 						Germany site (site ID 77) in subcategories of mobile.de search-enabled
     * 						categories.
     * 					
     * 
     */
    @XmlEnumValue("GermanMotorsSearchable")
    GERMAN_MOTORS_SEARCHABLE("GermanMotorsSearchable"),

    /**
     * 
     * 						Returns items that are also listed on the WorldOfGood.com website. (The Item IDs
     * 						are the same on both websites.)
     * 					
     * 
     */
    @XmlEnumValue("WorldOfGood")
    WORLD_OF_GOOD("WorldOfGood"),

    /**
     * 
     * 						The digital delivery feature has been disabled.
     * 						<br><br>
     * 						If specified, limits the results to digitally delivered good only.
     * 					
     * 
     */
    @XmlEnumValue("DigitalDelivery")
    DIGITAL_DELIVERY("DigitalDelivery"),

    /**
     * 
     * 						(out) Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    SearchFlagCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SearchFlagCodeType fromValue(String v) {
        for (SearchFlagCodeType c: SearchFlagCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
