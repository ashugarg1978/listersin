
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SimpleItemSortCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SimpleItemSortCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="BestMatch"/>
 *     &lt;enumeration value="CustomCode"/>
 *     &lt;enumeration value="EndTime"/>
 *     &lt;enumeration value="BidCount"/>
 *     &lt;enumeration value="Country"/>
 *     &lt;enumeration value="CurrentBid"/>
 *     &lt;enumeration value="Distance"/>
 *     &lt;enumeration value="StartDate"/>
 *     &lt;enumeration value="BestMatchCategoryGroup"/>
 *     &lt;enumeration value="PricePlusShipping"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SimpleItemSortCodeType")
@XmlEnum
public enum SimpleItemSortCodeType {


    /**
     * 
     * 						Sorts items by Best Match, and no sort order applies. If specified, 
     * 						then Best Match sort also applies to CategoryHistogram.
     * 					
     * 
     */
    @XmlEnumValue("BestMatch")
    BEST_MATCH("BestMatch"),

    /**
     * 
     * 						Placeholder value. See
     * 						<a href="types/simpleTypes.html#token">token</a>.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode"),

    /**
     * 
     * 						Sorts items by end time in ascending or descending order.
     * 					
     * 
     */
    @XmlEnumValue("EndTime")
    END_TIME("EndTime"),

    /**
     * 
     * 						Sort by number of bids on the item in ascending or descending order.
     * 					
     * 
     */
    @XmlEnumValue("BidCount")
    BID_COUNT("BidCount"),

    /**
     * 
     * 						Sort by country; no sort order can be specified.
     * 					
     * 
     */
    @XmlEnumValue("Country")
    COUNTRY("Country"),

    /**
     * 
     * 						Sort by current bid; descending order only.
     * 					
     * 
     */
    @XmlEnumValue("CurrentBid")
    CURRENT_BID("CurrentBid"),

    /**
     * 
     * 						Sort by distance, ascending order only.
     * 					
     * 
     */
    @XmlEnumValue("Distance")
    DISTANCE("Distance"),

    /**
     * 
     * 						Sort by start date, recently-listed first.
     * 					
     * 
     */
    @XmlEnumValue("StartDate")
    START_DATE("StartDate"),

    /**
     * 
     * 						(in) Sort by BestMatchCategoryGroup, so results are grouped by Best Match within a category.
     * 					
     * 
     */
    @XmlEnumValue("BestMatchCategoryGroup")
    BEST_MATCH_CATEGORY_GROUP("BestMatchCategoryGroup"),

    /**
     * 
     * 						(in) This value is part of the Price Plus Shipping Sort feature, to be enabled
     * 						by the end of Sept. 2007 for the following sites:
     * 						US (site ID 0), Germany (77), Canada (2), and Australia (15).
     * 						The Price Plus Shipping Sort feature causes item sorting to consider shipping costs.
     * 						Specify PricePlusShippingAsc to sort items by lowest cost first, as follows:
     * 						Lowest-total-cost (for items where shipping was properly specified), 
     * 						then freight-shipping items, then items for which shipping was not specified (sorted by price).
     * 					
     * 
     */
    @XmlEnumValue("PricePlusShipping")
    PRICE_PLUS_SHIPPING("PricePlusShipping");
    private final String value;

    SimpleItemSortCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SimpleItemSortCodeType fromValue(String v) {
        for (SimpleItemSortCodeType c: SimpleItemSortCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
