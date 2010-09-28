
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReturnsWithinOptionsCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ReturnsWithinOptionsCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Days_3"/>
 *     &lt;enumeration value="Days_7"/>
 *     &lt;enumeration value="Days_10"/>
 *     &lt;enumeration value="Days_14"/>
 *     &lt;enumeration value="Days_30"/>
 *     &lt;enumeration value="Days_60"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ReturnsWithinOptionsCodeType")
@XmlEnum
public enum ReturnsWithinOptionsCodeType {


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
     * 						7 days
     * 					
     * 
     */
    @XmlEnumValue("Days_7")
    DAYS_7("Days_7"),

    /**
     * 
     * 						10 days
     * 					
     * 
     */
    @XmlEnumValue("Days_10")
    DAYS_10("Days_10"),

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
     * 						60 days
     * 					
     * 
     */
    @XmlEnumValue("Days_60")
    DAYS_60("Days_60"),

    /**
     * 
     * 						(out) Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    ReturnsWithinOptionsCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ReturnsWithinOptionsCodeType fromValue(String v) {
        for (ReturnsWithinOptionsCodeType c: ReturnsWithinOptionsCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
