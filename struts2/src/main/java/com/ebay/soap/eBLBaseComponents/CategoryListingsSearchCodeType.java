
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CategoryListingsSearchCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CategoryListingsSearchCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Featured"/>
 *     &lt;enumeration value="SuperFeatured"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CategoryListingsSearchCodeType")
@XmlEnum
public enum CategoryListingsSearchCodeType {


    /**
     * 
     * 						(in) Return only featured item listings.
     * 					
     * 
     */
    @XmlEnumValue("Featured")
    FEATURED("Featured"),

    /**
     * 
     * 						(in) Return only super-featured item listings.
     * 					
     * 
     */
    @XmlEnumValue("SuperFeatured")
    SUPER_FEATURED("SuperFeatured"),

    /**
     * 
     * 						(out) Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    CategoryListingsSearchCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CategoryListingsSearchCodeType fromValue(String v) {
        for (CategoryListingsSearchCodeType c: CategoryListingsSearchCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
