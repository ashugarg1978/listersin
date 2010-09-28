
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DescriptionReviseModeCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DescriptionReviseModeCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Replace"/>
 *     &lt;enumeration value="Prepend"/>
 *     &lt;enumeration value="Append"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DescriptionReviseModeCodeType")
@XmlEnum
public enum DescriptionReviseModeCodeType {


    /**
     * 
     * 						(in) Specifies that you want to replace the description text of an item.
     * 					
     * 
     */
    @XmlEnumValue("Replace")
    REPLACE("Replace"),

    /**
     * 
     * 						(in) Specifies that you want to prepend text to the description text of an item.
     * 					
     * 
     */
    @XmlEnumValue("Prepend")
    PREPEND("Prepend"),

    /**
     * 
     * 						(in) Specifies that you want to append text to the description text of an item.
     * 					
     * 
     */
    @XmlEnumValue("Append")
    APPEND("Append"),

    /**
     * 
     * 						Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    DescriptionReviseModeCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DescriptionReviseModeCodeType fromValue(String v) {
        for (DescriptionReviseModeCodeType c: DescriptionReviseModeCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
