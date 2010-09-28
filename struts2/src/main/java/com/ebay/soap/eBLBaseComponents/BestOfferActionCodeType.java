
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BestOfferActionCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BestOfferActionCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Accept"/>
 *     &lt;enumeration value="Decline"/>
 *     &lt;enumeration value="Counter"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BestOfferActionCodeType")
@XmlEnum
public enum BestOfferActionCodeType {


    /**
     * 
     * 						(in) To accept something.
     * 					
     * 
     */
    @XmlEnumValue("Accept")
    ACCEPT("Accept"),

    /**
     * 
     * 						(in) To decline something.
     * 					
     * 
     */
    @XmlEnumValue("Decline")
    DECLINE("Decline"),

    /**
     * 
     * 						(in) To counter offer.
     * 					
     * 
     */
    @XmlEnumValue("Counter")
    COUNTER("Counter"),

    /**
     * 
     * 						Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    BestOfferActionCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BestOfferActionCodeType fromValue(String v) {
        for (BestOfferActionCodeType c: BestOfferActionCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
