
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BestOfferStatusCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BestOfferStatusCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Pending"/>
 *     &lt;enumeration value="Accepted"/>
 *     &lt;enumeration value="Declined"/>
 *     &lt;enumeration value="Expired"/>
 *     &lt;enumeration value="Retracted"/>
 *     &lt;enumeration value="AdminEnded"/>
 *     &lt;enumeration value="Active"/>
 *     &lt;enumeration value="Countered"/>
 *     &lt;enumeration value="All"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BestOfferStatusCodeType")
@XmlEnum
public enum BestOfferStatusCodeType {


    /**
     * 
     * 						The best offer is awaiting seller response or will naturally expire after 48 hours.
     * 					
     * 
     */
    @XmlEnumValue("Pending")
    PENDING("Pending"),

    /**
     * 
     * 						The best offer was accepted by the seller.
     * 					
     * 
     */
    @XmlEnumValue("Accepted")
    ACCEPTED("Accepted"),

    /**
     * 
     * 						The best offer was rejected by the seller.
     * 					
     * 
     */
    @XmlEnumValue("Declined")
    DECLINED("Declined"),

    /**
     * 
     * 						The best offer expired after 48 hours due to no action by the seller.
     * 					
     * 
     */
    @XmlEnumValue("Expired")
    EXPIRED("Expired"),

    /**
     * 
     * 						The best offer was retracted by the buyer.
     * 					
     * 
     */
    @XmlEnumValue("Retracted")
    RETRACTED("Retracted"),

    /**
     * 
     * 						The best offer was ended by an administrator.
     * 					
     * 
     */
    @XmlEnumValue("AdminEnded")
    ADMIN_ENDED("AdminEnded"),

    /**
     * 
     * 						Retrieve active best offers only.
     * 					
     * 
     */
    @XmlEnumValue("Active")
    ACTIVE("Active"),

    /**
     * 
     * 						Retrieve all counter best offers.
     * 					
     * 
     */
    @XmlEnumValue("Countered")
    COUNTERED("Countered"),

    /**
     * 
     * 						Retrieve all best offers (including declined offers, etc.).
     * 					
     * 
     */
    @XmlEnumValue("All")
    ALL("All"),

    /**
     * 
     * 						(out) Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    BestOfferStatusCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BestOfferStatusCodeType fromValue(String v) {
        for (BestOfferStatusCodeType c: BestOfferStatusCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
