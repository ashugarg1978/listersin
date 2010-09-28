
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StoreSearchCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="StoreSearchCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="AllItemsInTheStore"/>
 *     &lt;enumeration value="AuctionItemsInTheStore"/>
 *     &lt;enumeration value="BuyItNowItemsInTheStore"/>
 *     &lt;enumeration value="BuyItNowItemsInAllStores"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StoreSearchCodeType")
@XmlEnum
public enum StoreSearchCodeType {


    /**
     * 
     * 						(in) Within a single store for all items (specify store in
     * 						SearchStoreFilterType.StoreName).
     * 					
     * 
     */
    @XmlEnumValue("AllItemsInTheStore")
    ALL_ITEMS_IN_THE_STORE("AllItemsInTheStore"),

    /**
     * 
     * 						(in) Within a single store for auction items (specify store in
     * 						SearchStoreFilterType.StoreName).
     * 					
     * 
     */
    @XmlEnumValue("AuctionItemsInTheStore")
    AUCTION_ITEMS_IN_THE_STORE("AuctionItemsInTheStore"),

    /**
     * 
     * 						(in) Within a single store for basic fixed price items, Store Inventory format items,
     * 						and auction items with Buy It Now. (Specify store in SearchStoreFilterType.StoreName).
     * 					
     * 
     */
    @XmlEnumValue("BuyItNowItemsInTheStore")
    BUY_IT_NOW_ITEMS_IN_THE_STORE("BuyItNowItemsInTheStore"),

    /**
     * 
     * 						(in) Across all stores for basic fixed price items, Store Inventory format items,
     * 						and auction items with Buy It Now.
     * 					
     * 
     */
    @XmlEnumValue("BuyItNowItemsInAllStores")
    BUY_IT_NOW_ITEMS_IN_ALL_STORES("BuyItNowItemsInAllStores"),

    /**
     * 
     * 						(out) Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    StoreSearchCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StoreSearchCodeType fromValue(String v) {
        for (StoreSearchCodeType c: StoreSearchCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
