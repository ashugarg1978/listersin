
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ClassifiedAdBestOfferEnabledCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ClassifiedAdBestOfferEnabledCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Disabled"/>
 *     &lt;enumeration value="Enabled"/>
 *     &lt;enumeration value="Required"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ClassifiedAdBestOfferEnabledCodeType")
@XmlEnum
public enum ClassifiedAdBestOfferEnabledCodeType {


    /**
     * 
     * 						The Best Offer feature is disabled for the category.
     * 					
     * 
     */
    @XmlEnumValue("Disabled")
    DISABLED("Disabled"),

    /**
     * 
     * 						The Best Offer feature is enabled for the category.
     * 					
     * 
     */
    @XmlEnumValue("Enabled")
    ENABLED("Enabled"),

    /**
     * 
     * 						The category supports the Best Offer feature only.
     * 					
     * 
     */
    @XmlEnumValue("Required")
    REQUIRED("Required"),

    /**
     * 
     * 						(out) Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    ClassifiedAdBestOfferEnabledCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ClassifiedAdBestOfferEnabledCodeType fromValue(String v) {
        for (ClassifiedAdBestOfferEnabledCodeType c: ClassifiedAdBestOfferEnabledCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
