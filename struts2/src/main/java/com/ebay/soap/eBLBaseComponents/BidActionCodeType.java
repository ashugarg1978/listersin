
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BidActionCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BidActionCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Unknown"/>
 *     &lt;enumeration value="Bid"/>
 *     &lt;enumeration value="NotUsed"/>
 *     &lt;enumeration value="Retraction"/>
 *     &lt;enumeration value="AutoRetraction"/>
 *     &lt;enumeration value="Cancelled"/>
 *     &lt;enumeration value="AutoCancel"/>
 *     &lt;enumeration value="Absentee"/>
 *     &lt;enumeration value="BuyItNow"/>
 *     &lt;enumeration value="Purchase"/>
 *     &lt;enumeration value="CustomCode"/>
 *     &lt;enumeration value="Offer"/>
 *     &lt;enumeration value="Counter"/>
 *     &lt;enumeration value="Accept"/>
 *     &lt;enumeration value="Decline"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BidActionCodeType")
@XmlEnum
public enum BidActionCodeType {


    /**
     * 
     * 						Unknown.
     * 					
     * 
     */
    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),

    /**
     * 
     * 						The item is being offered (or was placed) as a competitive-bid item listing.
     * 						This auction format if often referred to as a Chinese auction.
     * 					
     * 
     */
    @XmlEnumValue("Bid")
    BID("Bid"),

    /**
     * 
     * 						Not used.
     * 					
     * 
     */
    @XmlEnumValue("NotUsed")
    NOT_USED("NotUsed"),

    /**
     * 
     * 						The offer was retracted by the user who extended the
     * 						offer. (This can only be done on the eBay site, not via API.)
     * 					
     * 
     */
    @XmlEnumValue("Retraction")
    RETRACTION("Retraction"),

    /**
     * 
     * 						The offer was automatically retracted. (This can only be done on the eBay site, not
     * 						via API.)
     * 					
     * 
     */
    @XmlEnumValue("AutoRetraction")
    AUTO_RETRACTION("AutoRetraction"),

    /**
     * 
     * 						The offer was cancelled. (This can only be done on the eBay site, not via API.)
     * 					
     * 
     */
    @XmlEnumValue("Cancelled")
    CANCELLED("Cancelled"),

    /**
     * 
     * 						The offer was automatically cancelled. (This can only be done on the eBay site, not
     * 						via API.)
     * 					
     * 
     */
    @XmlEnumValue("AutoCancel")
    AUTO_CANCEL("AutoCancel"),

    /**
     * 
     * 						The offer placed was an absentee bid. (This can only be done on the eBay site,
     * 						not via API.)
     * 					
     * 
     */
    @XmlEnumValue("Absentee")
    ABSENTEE("Absentee"),

    /**
     * 
     * 						The offer resulted in the successful exercise of the Buy It Now option for a
     * 						competitive-bid auction.
     * 					
     * 
     */
    @XmlEnumValue("BuyItNow")
    BUY_IT_NOW("BuyItNow"),

    /**
     * 
     * 						The offer is being placed, or was placed, on a fixed-price listing.
     * 						This value is used for fixed-price listings
     * 						(Basic Fixed-Price and eBay Store Inventory listings) to purchase an item.
     * 						In PlaceOffer, for Chinese-auction items with the BIN option,
     * 						specify Purchase to buy the item.
     * 						In the case of fixed-price items listed with AutoPay, PlaceOffer
     * 						cannot be used for purchase.
     * 						But for fixed-price items listed with AutoPay that have the BestOffer option,
     * 						PlaceOffer can be used to make an offer (but not to purchase).
     * 					
     * 
     */
    @XmlEnumValue("Purchase")
    PURCHASE("Purchase"),

    /**
     * 
     * 						Reserved for future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode"),

    /**
     * 
     * 						If an item is best-offer enabled, use this value if a buyer is making a best offer on
     * 						the item. After a buyer makes a best offer (or counter-offer, etc.), the buyer can get
     * 						the status of the best offer (and of a possible seller-counter-offer, etc.) using the
     * 						GetBestOffers call. See the Trading Web Services Guide for information about
     * 						best-offer enabled items and about GetBestOffers.
     * 					
     * 
     */
    @XmlEnumValue("Offer")
    OFFER("Offer"),

    /**
     * 
     * 						If an item is best-offer enabled, use this value if a buyer is making a counteroffer
     * 						to a seller's counteroffer.
     * 					
     * 
     */
    @XmlEnumValue("Counter")
    COUNTER("Counter"),

    /**
     * 
     * 						If an item is best-offer enabled, use this value if a buyer is accepting a
     * 						counteroffer of a seller.
     * 					
     * 
     */
    @XmlEnumValue("Accept")
    ACCEPT("Accept"),

    /**
     * 
     * 						If an item is best-offer enabled, use this value if a buyer is declining a
     * 						counteroffer of a seller.
     * 					
     * 
     */
    @XmlEnumValue("Decline")
    DECLINE("Decline");
    private final String value;

    BidActionCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BidActionCodeType fromValue(String v) {
        for (BidActionCodeType c: BidActionCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
