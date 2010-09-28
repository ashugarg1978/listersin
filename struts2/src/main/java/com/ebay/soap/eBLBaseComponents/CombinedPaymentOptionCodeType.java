
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CombinedPaymentOptionCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CombinedPaymentOptionCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="NoCombinedPayment"/>
 *     &lt;enumeration value="DiscountSpecified"/>
 *     &lt;enumeration value="SpecifyDiscountLater"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CombinedPaymentOptionCodeType")
@XmlEnum
public enum CombinedPaymentOptionCodeType {


    /**
     * 
     * 					No Combined Purchase
     * 				
     * 
     */
    @XmlEnumValue("NoCombinedPayment")
    NO_COMBINED_PAYMENT("NoCombinedPayment"),

    /**
     * 
     * 					Yes Specify Discount Now
     * 				
     * 
     */
    @XmlEnumValue("DiscountSpecified")
    DISCOUNT_SPECIFIED("DiscountSpecified"),

    /**
     * 
     * 					Yes Specify Discount Later
     * 				
     * 
     */
    @XmlEnumValue("SpecifyDiscountLater")
    SPECIFY_DISCOUNT_LATER("SpecifyDiscountLater"),

    /**
     * 
     * 					Reserved for internal or future use
     * 				
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    CombinedPaymentOptionCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CombinedPaymentOptionCodeType fromValue(String v) {
        for (CombinedPaymentOptionCodeType c: CombinedPaymentOptionCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
