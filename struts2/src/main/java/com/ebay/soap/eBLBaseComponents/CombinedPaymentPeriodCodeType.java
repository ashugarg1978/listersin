
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CombinedPaymentPeriodCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CombinedPaymentPeriodCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Days_3"/>
 *     &lt;enumeration value="Days_5"/>
 *     &lt;enumeration value="Days_7"/>
 *     &lt;enumeration value="Days_14"/>
 *     &lt;enumeration value="Days_30"/>
 *     &lt;enumeration value="Ineligible"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CombinedPaymentPeriodCodeType")
@XmlEnum
public enum CombinedPaymentPeriodCodeType {


    /**
     * 
     * 						3 days
     * 					
     * 
     */
    @XmlEnumValue("Days_3")
    DAYS_3("Days_3"),

    /**
     * 
     * 						5 days
     * 					
     * 
     */
    @XmlEnumValue("Days_5")
    DAYS_5("Days_5"),

    /**
     * 
     * 						7 days
     * 					
     * 
     */
    @XmlEnumValue("Days_7")
    DAYS_7("Days_7"),

    /**
     * 
     * 						14 days
     * 					
     * 
     */
    @XmlEnumValue("Days_14")
    DAYS_14("Days_14"),

    /**
     * 
     * 						30 days
     * 					
     * 
     */
    @XmlEnumValue("Days_30")
    DAYS_30("Days_30"),

    /**
     * 
     * 						No restriction period is being applied
     * 					
     * 
     */
    @XmlEnumValue("Ineligible")
    INELIGIBLE("Ineligible"),

    /**
     * 
     * 						Reserved for internal or future use
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    CombinedPaymentPeriodCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CombinedPaymentPeriodCodeType fromValue(String v) {
        for (CombinedPaymentPeriodCodeType c: CombinedPaymentPeriodCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
