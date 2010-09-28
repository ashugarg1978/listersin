
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AddressRecordTypeCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AddressRecordTypeCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Residential"/>
 *     &lt;enumeration value="Business"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AddressRecordTypeCodeType")
@XmlEnum
public enum AddressRecordTypeCodeType {

    @XmlEnumValue("Residential")
    RESIDENTIAL("Residential"),
    @XmlEnumValue("Business")
    BUSINESS("Business"),

    /**
     * 
     * 						(out) Reserved for internal or future use
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    AddressRecordTypeCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AddressRecordTypeCodeType fromValue(String v) {
        for (AddressRecordTypeCodeType c: AddressRecordTypeCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
