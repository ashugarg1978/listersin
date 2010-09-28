
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CharacteristicsSearchCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CharacteristicsSearchCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Single"/>
 *     &lt;enumeration value="Multi"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CharacteristicsSearchCodeType")
@XmlEnum
public enum CharacteristicsSearchCodeType {


    /**
     * 
     * 						(out) The search criteria can consist of one attribute.
     * 					
     * 
     */
    @XmlEnumValue("Single")
    SINGLE("Single"),

    /**
     * 
     * 						(out) Not used. (For multi-attribute search criteria,
     * 						call GetProductFinder instead.)
     * 					
     * 
     */
    @XmlEnumValue("Multi")
    MULTI("Multi"),

    /**
     * 
     * 						(out) Reserved for internal or future use
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    CharacteristicsSearchCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CharacteristicsSearchCodeType fromValue(String v) {
        for (CharacteristicsSearchCodeType c: CharacteristicsSearchCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
