
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConditionEnabledCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ConditionEnabledCodeType">
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
@XmlType(name = "ConditionEnabledCodeType")
@XmlEnum
public enum ConditionEnabledCodeType {


    /**
     * 
     * 						Item condition is not supported for the category. 
     * 						Do not pass ConditionID in AddItem and related calls.
     * 					
     * 
     */
    @XmlEnumValue("Disabled")
    DISABLED("Disabled"),

    /**
     * 
     * 						Item condition is supported for the category. 
     * 						You can pass ConditionID to specify the condition 
     * 						in AddItem and related calls.
     * 					
     * 
     */
    @XmlEnumValue("Enabled")
    ENABLED("Enabled"),

    /**
     * 
     * 						Item condition is required for the category. 
     * 						You must use ConditionID to specify the condition 
     * 						in AddItem and related calls.
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

    ConditionEnabledCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ConditionEnabledCodeType fromValue(String v) {
        for (ConditionEnabledCodeType c: ConditionEnabledCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
