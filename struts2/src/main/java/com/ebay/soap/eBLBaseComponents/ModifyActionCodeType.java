
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ModifyActionCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ModifyActionCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Add"/>
 *     &lt;enumeration value="Delete"/>
 *     &lt;enumeration value="Update"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ModifyActionCodeType")
@XmlEnum
public enum ModifyActionCodeType {


    /**
     * 
     * 						An addition.
     * 					
     * 
     */
    @XmlEnumValue("Add")
    ADD("Add"),

    /**
     * 
     * 						A removal.
     * 					
     * 
     */
    @XmlEnumValue("Delete")
    DELETE("Delete"),

    /**
     * 
     * 						A modification.
     * 					
     * 
     */
    @XmlEnumValue("Update")
    UPDATE("Update"),

    /**
     * 
     * 						Reserved for future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    ModifyActionCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ModifyActionCodeType fromValue(String v) {
        for (ModifyActionCodeType c: ModifyActionCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
