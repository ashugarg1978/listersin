
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AddressOwnerCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AddressOwnerCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="PayPal"/>
 *     &lt;enumeration value="eBay"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AddressOwnerCodeType")
@XmlEnum
public enum AddressOwnerCodeType {


    /**
     * 
     * 						PayPal owns the address.
     * 					
     * 
     */
    @XmlEnumValue("PayPal")
    PAY_PAL("PayPal"),

    /**
     * 
     * 						eBay owns the address.
     * 					
     * 
     */
    @XmlEnumValue("eBay")
    E_BAY("eBay"),

    /**
     * 
     * 						Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    AddressOwnerCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AddressOwnerCodeType fromValue(String v) {
        for (AddressOwnerCodeType c: AddressOwnerCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
