
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ItemTypeFilterCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ItemTypeFilterCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="AuctionItemsOnly"/>
 *     &lt;enumeration value="FixedPricedItem"/>
 *     &lt;enumeration value="AllItems"/>
 *     &lt;enumeration value="StoreInventoryOnly"/>
 *     &lt;enumeration value="FixedPriceExcludeStoreInventory"/>
 *     &lt;enumeration value="ExcludeStoreInventory"/>
 *     &lt;enumeration value="AllItemTypes"/>
 *     &lt;enumeration value="AllFixedPriceItemTypes"/>
 *     &lt;enumeration value="CustomCode"/>
 *     &lt;enumeration value="ClassifiedItemsOnly"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ItemTypeFilterCodeType")
@XmlEnum
public enum ItemTypeFilterCodeType {


    /**
     * 
     * 						(in) Retrieve only listings eligible for competitive bidding at auction.
     * 						That is, only retrieve listings for which ListingType is Chinese
     * 						(single-item auction), regardless of the BuyItNowEnabled value.
     * 						Items with any of the following listing types are not retrieved:
     * 						StoresFixedPrice, FixedPriceItem, and AdType.
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
    @XmlEnumValue("AuctionItemsOnly")
    AUCTION_ITEMS_ONLY("AuctionItemsOnly"),

    /**
     * 
     * 						(in) Retrieves only listings that can be purchased at a fixed price. That is,
     * 						only retrieves listings for which ListingType is StoresFixedPrice or
     * 						FixedPriceItem. Whether StoresFixedPrice items are retrieved depends on the site
     * 						default. If StoresFixedPrice items are retrieved, they are returned after the
     * 						other retrieved items. Also retrieves competitive-bid auction listings for which
     * 						BuyItNowEnabled is true. Does not retrieve listings for which ListingType is
     * 						AdType, and does not retrieve auction listings for which BuyItNowEnabled is
     * 						false.
     * 					
     * 
     */
    @XmlEnumValue("FixedPricedItem")
    FIXED_PRICED_ITEM("FixedPricedItem"),

    /**
     * 
     * 						(in) It is recommended that you use AllItemTypes instead of AllItems.
     * 						Return all listing types (the default for GetSearchResults).
     * 						Whether StoresFixedPrice items are retrieved depends on the site default.
     * 					
     * 
     */
    @XmlEnumValue("AllItems")
    ALL_ITEMS("AllItems"),

    /**
     * 
     * 						(in) Only retrieve listings for which ListingType is StoresFixedPrice.
     * 					
     * 
     */
    @XmlEnumValue("StoreInventoryOnly")
    STORE_INVENTORY_ONLY("StoreInventoryOnly"),

    /**
     * 
     * 						(in) Exclude listings that have ListingType set to StoresFixedPrice.
     * 						Exclude listings that have ListingType set to AdType.
     * 						Exclude auction listings in which BuyItNowEnabled is false.
     * 					
     * 
     */
    @XmlEnumValue("FixedPriceExcludeStoreInventory")
    FIXED_PRICE_EXCLUDE_STORE_INVENTORY("FixedPriceExcludeStoreInventory"),

    /**
     * 
     * 						(in) Exclude listings that have ListingType set to StoresFixedPrice.
     * 					
     * 
     */
    @XmlEnumValue("ExcludeStoreInventory")
    EXCLUDE_STORE_INVENTORY("ExcludeStoreInventory"),

    /**
     * 
     * 						(in) Retrieve listings whether or not ListingType is set to StoresFixedPrice; include
     * 						auction items.
     * 					
     * 
     */
    @XmlEnumValue("AllItemTypes")
    ALL_ITEM_TYPES("AllItemTypes"),

    /**
     * 
     * 						(in) Retrieves fixed-price items.
     * 						Whether StoresFixedPrice items are retrieved does not depend on the site default.
     * 						The StoresFixedPrice items are retrieved after the basic fixed price items.
     * 						Items are retrieved whether or not ListingType is set to StoresFixedPrice.
     * 						Does not retrieve items for which ListingType is AdType.
     * 						Does not retrieve auction items for which BuyItNowEnabled is false.
     * 					
     * 
     */
    @XmlEnumValue("AllFixedPriceItemTypes")
    ALL_FIXED_PRICE_ITEM_TYPES("AllFixedPriceItemTypes"),

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
     * 						(in) Only retrieve Classified Ad format listings.
     * 					
     * 
     */
    @XmlEnumValue("ClassifiedItemsOnly")
    CLASSIFIED_ITEMS_ONLY("ClassifiedItemsOnly");
    private final String value;

    ItemTypeFilterCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ItemTypeFilterCodeType fromValue(String v) {
        for (ItemTypeFilterCodeType c: ItemTypeFilterCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
