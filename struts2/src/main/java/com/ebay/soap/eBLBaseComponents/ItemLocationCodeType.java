
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ItemLocationCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ItemLocationCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="ItemAvailableIn"/>
 *     &lt;enumeration value="ItemLocatedIn"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ItemLocationCodeType")
@XmlEnum
public enum ItemLocationCodeType {


    /**
     * 
     * 						Items available to the specified country.
     * 					
     * 
     */
    @XmlEnumValue("ItemAvailableIn")
    ITEM_AVAILABLE_IN("ItemAvailableIn"),

    /**
     * 
     * 						Items located in the specified country.
     * 					
     * 
     */
    @XmlEnumValue("ItemLocatedIn")
    ITEM_LOCATED_IN("ItemLocatedIn"),

    /**
     * 
     * 						(out) Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    ItemLocationCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ItemLocationCodeType fromValue(String v) {
        for (ItemLocationCodeType c: ItemLocationCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
