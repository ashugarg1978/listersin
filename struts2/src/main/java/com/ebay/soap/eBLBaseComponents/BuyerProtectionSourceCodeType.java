
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BuyerProtectionSourceCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BuyerProtectionSourceCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="eBay"/>
 *     &lt;enumeration value="PayPal"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BuyerProtectionSourceCodeType")
@XmlEnum
public enum BuyerProtectionSourceCodeType {


    /**
     * 
     * 						Buyer protection is covered by the eBay Standard Purchase Protection Program.
     * 					
     * 
     */
    @XmlEnumValue("eBay")
    E_BAY("eBay"),

    /**
     * 
     * 						Buyer protection is covered by the PayPal Protection Program.
     * 					
     * 
     */
    @XmlEnumValue("PayPal")
    PAY_PAL("PayPal"),

    /**
     * 
     * 						 Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    BuyerProtectionSourceCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BuyerProtectionSourceCodeType fromValue(String v) {
        for (BuyerProtectionSourceCodeType c: BuyerProtectionSourceCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
