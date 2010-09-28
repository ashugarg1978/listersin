
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CategoryListingsOrderCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CategoryListingsOrderCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="NoFilter"/>
 *     &lt;enumeration value="ItemsBy24Hr"/>
 *     &lt;enumeration value="ItemsEndToday"/>
 *     &lt;enumeration value="ItemsEndIn5Hr"/>
 *     &lt;enumeration value="SortByPriceAsc"/>
 *     &lt;enumeration value="SortByPriceDesc"/>
 *     &lt;enumeration value="BestMatchSort"/>
 *     &lt;enumeration value="DistanceSort"/>
 *     &lt;enumeration value="CustomCode"/>
 *     &lt;enumeration value="BestMatchCategoryGroup"/>
 *     &lt;enumeration value="PricePlusShippingAsc"/>
 *     &lt;enumeration value="PricePlusShippingDesc"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CategoryListingsOrderCodeType")
@XmlEnum
public enum CategoryListingsOrderCodeType {


    /**
     * 
     * 								(in) Applies no filter.
     * 					
     * 
     */
    @XmlEnumValue("NoFilter")
    NO_FILTER("NoFilter"),

    /**
     * 
     * 								(in) Returns items that were listed in the past 24 hours.
     * 					
     * 
     */
    @XmlEnumValue("ItemsBy24Hr")
    ITEMS_BY_24_HR("ItemsBy24Hr"),

    /**
     * 
     * 								(in) Reserved for internal use.
     * 					
     * 
     */
    @XmlEnumValue("ItemsEndToday")
    ITEMS_END_TODAY("ItemsEndToday"),

    /**
     * 
     * 								(in) Reserved for internal use.
     * 					
     * 
     */
    @XmlEnumValue("ItemsEndIn5Hr")
    ITEMS_END_IN_5_HR("ItemsEndIn5Hr"),

    /**
     * 
     * 				(in) Sort items by price (lowest first)
     * 					
     * 
     */
    @XmlEnumValue("SortByPriceAsc")
    SORT_BY_PRICE_ASC("SortByPriceAsc"),

    /**
     * 
     * 				(in) Sort items by price (highest first)
     * 					
     * 
     */
    @XmlEnumValue("SortByPriceDesc")
    SORT_BY_PRICE_DESC("SortByPriceDesc"),

    /**
     * 
     * 				(in) Sorts items by Best Match, which is based on community buying activity and other relevance-based factors.
     * 					
     * 
     */
    @XmlEnumValue("BestMatchSort")
    BEST_MATCH_SORT("BestMatchSort"),

    /**
     * 
     * 				(in) Sort items by distance
     * 					
     * 
     */
    @XmlEnumValue("DistanceSort")
    DISTANCE_SORT("DistanceSort"),

    /**
     * 
     * 								(out) Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode"),

    /**
     * 
     * 						(in) Groups Best Match search results by category. 
     * 						See BestMatchSort. 
     * 						You can also set the number of groups and items per group within the Group
     * 						section of the call.
     * 						If you specify BestMatchCategoryGroup, 
     * 						and you want to specify a value for Pagination.EntriesPerPage,
     * 						the Pagination.EntriesPerPage value can be 50 or less. 
     * 					
     * 
     */
    @XmlEnumValue("BestMatchCategoryGroup")
    BEST_MATCH_CATEGORY_GROUP("BestMatchCategoryGroup"),

    /**
     * 
     * 						(in) This field is part of the Price Plus Shipping Sort feature, to be enabled
     * 						by the end of Sept. 2007 for the following sites:
     * 						US (site ID 0), Germany (77), Canada (2), and Australia (15).
     * 						The Price Plus Shipping Sort feature causes item sorting to consider shipping costs.
     * 						Specify PricePlusShippingAsc to sort items by lowest cost first, as follows:
     * 						Lowest-total-cost (for items where shipping was properly specified),
     * 						then freight-shipping items, then items for which shipping was not specified (sorted by price).
     * 					
     * 
     */
    @XmlEnumValue("PricePlusShippingAsc")
    PRICE_PLUS_SHIPPING_ASC("PricePlusShippingAsc"),

    /**
     * 
     * 						(in) This field is part of the Price Plus Shipping Sort feature, to be enabled
     * 						by the end of Sept. 2007 for the following sites:
     * 						US (site ID 0), Germany (77), Canada (2), and Australia (15).
     * 						The Price Plus Shipping Sort feature causes item sorting to consider shipping costs.
     * 						Specify PricePlusShippingDesc to sort items by highest cost first, as follows:
     * 						highest-total-cost (for items where shipping was properly specified),
     * 						then freight-shipping items, then items for which shipping was not specified (sorted by price).
     * 					
     * 
     */
    @XmlEnumValue("PricePlusShippingDesc")
    PRICE_PLUS_SHIPPING_DESC("PricePlusShippingDesc");
    private final String value;

    CategoryListingsOrderCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CategoryListingsOrderCodeType fromValue(String v) {
        for (CategoryListingsOrderCodeType c: CategoryListingsOrderCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
