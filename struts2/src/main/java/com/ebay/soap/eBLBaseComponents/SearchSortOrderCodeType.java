
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchSortOrderCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SearchSortOrderCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="SortByEndDate"/>
 *     &lt;enumeration value="SortByStartDate"/>
 *     &lt;enumeration value="SortByCurrentBid"/>
 *     &lt;enumeration value="SortByListingDate"/>
 *     &lt;enumeration value="SortByCurrentBidAsc"/>
 *     &lt;enumeration value="SortByCurrentBidDesc"/>
 *     &lt;enumeration value="SortByPayPalAsc"/>
 *     &lt;enumeration value="SortByPayPalDesc"/>
 *     &lt;enumeration value="SortByEscrowAsc"/>
 *     &lt;enumeration value="SortByEscrowDesc"/>
 *     &lt;enumeration value="SortByCountryAsc"/>
 *     &lt;enumeration value="SortByCountryDesc"/>
 *     &lt;enumeration value="SortByDistanceAsc"/>
 *     &lt;enumeration value="SortByBidCountAsc"/>
 *     &lt;enumeration value="SortByBidCountDesc"/>
 *     &lt;enumeration value="BestMatchSort"/>
 *     &lt;enumeration value="CustomCode"/>
 *     &lt;enumeration value="BestMatchCategoryGroup"/>
 *     &lt;enumeration value="PricePlusShippingAsc"/>
 *     &lt;enumeration value="PricePlusShippingDesc"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SearchSortOrderCodeType")
@XmlEnum
public enum SearchSortOrderCodeType {


    /**
     * 
     * 						(in) Sorts items by the end date (items ending soonest). This is the default for most sites,
     * 						except where noted for other values.
     * 					
     * 
     */
    @XmlEnumValue("SortByEndDate")
    SORT_BY_END_DATE("SortByEndDate"),

    /**
     * 
     * 						(in) Sorts items by the start date (most recently listed first).
     * 					
     * 
     */
    @XmlEnumValue("SortByStartDate")
    SORT_BY_START_DATE("SortByStartDate"),

    /**
     * 
     * 						(in) Sorts items by current price (lowest bid first).
     * 						Note that if an item was listed in a different currency from the
     * 						site's currency, that item is sorted is based on its ConvertedCurrentPrice value.
     * 					
     * 
     */
    @XmlEnumValue("SortByCurrentBid")
    SORT_BY_CURRENT_BID("SortByCurrentBid"),

    /**
     * 
     * 						(in) Sorts items by the listing date in descending order.
     * 					
     * 
     */
    @XmlEnumValue("SortByListingDate")
    SORT_BY_LISTING_DATE("SortByListingDate"),

    /**
     * 
     * 						(in)Due to the new Price Plus Shipping Sort feature, SortByCurrentBidAsc
     * 						is no longer functional.
     * 						The Price Plus Shipping Sort feature causes item sorting to consider shipping costs. For more information, please see the PricePlusShippingAsc value.
     * 						Formerly, SortByCurrentBidAsc sorted items by the current bid price in
     * 						ascending order (lowest bid first).
     * 						(If an item was listed in a different currency from the
     * 						site currency, the item was sorted based on its ConvertedCurrentPrice value.)
     * 					
     * 
     */
    @XmlEnumValue("SortByCurrentBidAsc")
    SORT_BY_CURRENT_BID_ASC("SortByCurrentBidAsc"),

    /**
     * 
     * 						(in) Sorts items by the current bid price in descending order (highest bid first).
     * 						Note that if an item was listed in a different currency from the
     * 						site's currency, the item is sorted based on its ConvertedCurrentPrice value.
     * 					
     * 
     */
    @XmlEnumValue("SortByCurrentBidDesc")
    SORT_BY_CURRENT_BID_DESC("SortByCurrentBidDesc"),

    /**
     * 
     * 						(in) This input value for sorting is no longer functional.
     * 						Formerly, this input value sorted items in ascending order
     * 						based on whether PayPal was accepted (accepted followed by unaccepted).
     * 					
     * 
     */
    @XmlEnumValue("SortByPayPalAsc")
    SORT_BY_PAY_PAL_ASC("SortByPayPalAsc"),

    /**
     * 
     * 						(in) This input value for sorting is no longer functional.
     * 						Formerly, this input value sorted items in descending order
     * 						based on whether PayPal was accepted (unaccepted followed by accepted).
     * 					
     * 
     */
    @XmlEnumValue("SortByPayPalDesc")
    SORT_BY_PAY_PAL_DESC("SortByPayPalDesc"),

    /**
     * 
     * 						(in) Sorts items in ascending order based on whether Escrow is applicable (applicable followed by inapplicable).
     * 					
     * 
     */
    @XmlEnumValue("SortByEscrowAsc")
    SORT_BY_ESCROW_ASC("SortByEscrowAsc"),

    /**
     * 
     * 						(in) Sorts items in descending order based on whether Escrow is applicable (inapplicable followed by applicable).
     * 					
     * 
     */
    @XmlEnumValue("SortByEscrowDesc")
    SORT_BY_ESCROW_DESC("SortByEscrowDesc"),

    /**
     * 
     * 						(in) Sorts items in ascending order by the country in which the items are located.
     * 					
     * 
     */
    @XmlEnumValue("SortByCountryAsc")
    SORT_BY_COUNTRY_ASC("SortByCountryAsc"),

    /**
     * 
     * 						(in) Sorts items in descending order by the country in which the items are located.
     * 					
     * 
     */
    @XmlEnumValue("SortByCountryDesc")
    SORT_BY_COUNTRY_DESC("SortByCountryDesc"),

    /**
     * 
     * 						(in) Sorts items based on distance from the buyer, with the nearest items returned first.
     * 					
     * 
     */
    @XmlEnumValue("SortByDistanceAsc")
    SORT_BY_DISTANCE_ASC("SortByDistanceAsc"),

    /**
     * 
     * 					(in) Sorts items based on the number of bids, fewest bids first.
     * 					
     * 
     */
    @XmlEnumValue("SortByBidCountAsc")
    SORT_BY_BID_COUNT_ASC("SortByBidCountAsc"),

    /**
     * 
     * 					(in) Sorts items based on the number of bids, most bids first.
     * 					
     * 
     */
    @XmlEnumValue("SortByBidCountDesc")
    SORT_BY_BID_COUNT_DESC("SortByBidCountDesc"),

    /**
     * 
     * 						(in) Sorts items by Best Match, which is based on community buying activity and other relevance-based factors.
     * 					
     * 
     */
    @XmlEnumValue("BestMatchSort")
    BEST_MATCH_SORT("BestMatchSort"),

    /**
     * 
     * 						(out) Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode"),

    /**
     * 
     * 						(in) Groups BestMatch results. BestMatch results are based on 
     * 						community buying activity and other relevance-based factors. 
     * 						You can also set the number of groups to be returned and you 
     * 						can set the number of items to be returned in each group.
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
    @XmlEnumValue("PricePlusShippingAsc")
    PRICE_PLUS_SHIPPING_ASC("PricePlusShippingAsc"),

    /**
     * 
     * 						(in) This value is part of the Price Plus Shipping Sort feature, to be enabled
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

    SearchSortOrderCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SearchSortOrderCodeType fromValue(String v) {
        for (SearchSortOrderCodeType c: SearchSortOrderCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
