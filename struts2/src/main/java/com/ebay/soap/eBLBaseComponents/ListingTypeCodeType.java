
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ListingTypeCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ListingTypeCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Unknown"/>
 *     &lt;enumeration value="Chinese"/>
 *     &lt;enumeration value="Dutch"/>
 *     &lt;enumeration value="Live"/>
 *     &lt;enumeration value="Auction"/>
 *     &lt;enumeration value="AdType"/>
 *     &lt;enumeration value="StoresFixedPrice"/>
 *     &lt;enumeration value="PersonalOffer"/>
 *     &lt;enumeration value="FixedPriceItem"/>
 *     &lt;enumeration value="Half"/>
 *     &lt;enumeration value="LeadGeneration"/>
 *     &lt;enumeration value="Express"/>
 *     &lt;enumeration value="Shopping"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ListingTypeCodeType")
@XmlEnum
public enum ListingTypeCodeType {


    /**
     * 
     * 						Unknown or undefined auction type. Applicable to
     * 						user preferences and other informational use cases.
     * 					
     * 
     */
    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),

    /**
     * 
     * 						Single-quantity online auction format.
     * 						A Chinese auction has a Quantity of 1. Buyers engage in competitive bidding,
     * 						although Buy It Now may be offered as long as no bids have been placed.
     * 						Online auctions are listed on eBay.com, and they are also listed in
     * 						the seller's eBay Store if the seller is a Store owner.
     * 					
     * 
     */
    @XmlEnumValue("Chinese")
    CHINESE("Chinese"),

    /**
     * 
     * 						Multiple-quantity online auction format. A Dutch auction has a Quantity greater than 1.
     * 						Buyers engage in competitive bidding. Some sites also offer Buy It Now for Dutch
     * 						auctions. Online auctions are listed on eBay.com, and they are also listed in the
     * 						seller's eBay Store if the seller is a Store owner.
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
    @XmlEnumValue("Dutch")
    DUTCH("Dutch"),

    /**
     * 
     * 						Live auction, on-site auction that can include non-eBay bidders.
     * 						(Use AddLiveAuctionItem to list live auctions.)
     * 						Live auctions are listed on the eBay Live Auctions site, in live auction categories.
     * 						They can also appear on eBay if the seller lists the lot in a secondary category
     * 						that is an eBay category.
     * 					
     * 
     */
    @XmlEnumValue("Live")
    LIVE("Live"),

    /**
     * 
     * 						An optional input parameter used with GetMyeBaySelling. When used in
     * 						the request, returns items of competitive-bid auctions (Chinese).
     * 					
     * 
     */
    @XmlEnumValue("Auction")
    AUCTION("Auction"),

    /**
     * 
     * 						Advertisement to solicit inquiries on listings such as real estate. Permits
     * 						no bidding on that item, service, or property. To express interest, a buyer
     * 						fills out a contact form that eBay forwards to the the seller as a lead. This
     * 						format does not enable buyers and sellers to transact online through eBay,
     * 						and eBay Feedback is not available for ad format listings.
     * 					
     * 
     */
    @XmlEnumValue("AdType")
    AD_TYPE("AdType"),

    /**
     * 
     *             A fixed-price format for eBay Store sellers.
     *             <br><br>
     *             As of March 30, 2010, the FixedPriceItem listing type will be replacing the
     *             StoresFixedPrice listing type on all eBay sites. The StoresFixedPrice listing type
     *             will be deprecated in early 2011. We have started a migration phase where AddItem
     *             and AddFixedPriceItem will accept either FixedPriceItem or StoresFixedPrice as listing
     *             types, but the item will be displayed as FixedPriceItem on the site and in search
     *             results. GetItem and other 'Get' calls will return the listing type you originally
     *             used in the request. Therefore, the preferred format is FixedPriceItem.
     *             <br><br>
     * 						In additon to the changes mentioned above, items added as StoresFixedPrice listing
     * 						type will be treated as FixedPriceItem listing type on the site. Items will immediately
     * 						get the higher FixedPriceItem exposure, and will renew or relist at the FixedPriceItem
     * 						price.
     *             <br><br>
     * 						Any listing enhancements (e.g., bold, subtitle, etc.) will also renew or relist at
     * 						FixedPriceItem (higher) rates.
     *             <br><br>
     *             To list a product for a store, you can simply substitute FixedPriceItem as the listing
     *             type where the StoresFixedPrice listing type used to be within your AddItem call. Like
     *             StoresFixedPrice, the FixedPriceItem listing type has permitted durations of 30 days
     *             and GTC for store and non-store subscribers (in addition to the previously permitted
     *             durations of 3, 5, 7, and 10 days).
     *           
     * 
     */
    @XmlEnumValue("StoresFixedPrice")
    STORES_FIXED_PRICE("StoresFixedPrice"),

    /**
     * 
     * 						Second chance offer made to a non-winning bidder on an ended listing.
     * 						A seller can make an offer to a non-winning bidder when either the winning bidder
     * 						has failed to pay for an item or the seller has a duplicate of the item.
     * 						A seller can create a Second Chance Offer immediately after a listing ends and up to
     * 						60 days after the end of the listing. eBay does not charge an Insertion Fee,
     * 						but if the bidder accepts the offer, the regular Final Value Fee is charged.
     * 						In the case of an Unpaid Item, the seller should ensure that everything has
     * 						been done to resolve the issue with the winning bidder before sending a
     * 						Second Chance Offer to another bidder. See the Unpaid Items Process for details.
     * 						Make sure you're aware of other rules and restrictions surrounding Second Chance Offers.
     * 						Use AddSecondChanceItem to submit Second Chance Offers.
     * 						Listed on eBay, but does not appear when browsing or searching listings.
     * 					
     * 
     */
    @XmlEnumValue("PersonalOffer")
    PERSONAL_OFFER("PersonalOffer"),

    /**
     * 
     *             A basic fixed-price item format. Bids do not occur.
     *             The quantity of items is one or more.
     *             <br><br>
     *             Also known as Buy It Now Only on some sites (not to be confused with the BuyItNow option that
     *             is available for Chinese auctions).
     *             <br><br>
     *             Sellers must meet certain feedback requirements and/or be ID Verified to use this format.
     *             See Minimum Feedback Requirements for Various Features in the Trading Web Services guide.
     *             <br><br>
     *             Fixed-price listings are listed on eBay.com, and they are listed in
     *             the seller's eBay Store if the seller is a Store owner.
     *             Stores fixed price items will be treated as basic
     *             fixed-price items. Permitted durations of 30 days
     *             and GTC are now available for store and non-store subscribers (in addition
     *             to the existing durations of 3, 5, 7, and 10 days).
     *           
     * 
     */
    @XmlEnumValue("FixedPriceItem")
    FIXED_PRICE_ITEM("FixedPriceItem"),

    /**
     * 
     * 					Half.com listing (item is listed on Half.com, not on eBay).
     * 					You must be a registered Half.com seller to use this format.
     * 					
     * 
     */
    @XmlEnumValue("Half")
    HALF("Half"),

    /**
     * 
     * 						Lead Generation format (advertisement-style listing to solicit
     * 						inquiries or offers, no bidding or fixed price, listed on eBay).
     * 					
     * 
     */
    @XmlEnumValue("LeadGeneration")
    LEAD_GENERATION("LeadGeneration"),

    /**
     * 
     * 						For Germany Express site only, which is no longer in service.
     * 					
     * 
     */
    @XmlEnumValue("Express")
    EXPRESS("Express"),

    /**
     * 
     * 						Reserved for internal or future use. You can ignore Shopping.com items in your results.
     * 					
     * 
     */
    @XmlEnumValue("Shopping")
    SHOPPING("Shopping"),

    /**
     * 
     * 						Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    ListingTypeCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ListingTypeCodeType fromValue(String v) {
        for (ListingTypeCodeType c: ListingTypeCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
