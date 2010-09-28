
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SkypeContactOptionCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SkypeContactOptionCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Chat"/>
 *     &lt;enumeration value="Voice"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SkypeContactOptionCodeType")
@XmlEnum
public enum SkypeContactOptionCodeType {


    /**
     * 
     * 						The Skype Chat contact option.
     * 					
     * 
     */
    @XmlEnumValue("Chat")
    CHAT("Chat"),

    /**
     * 
     * 						The Skype Voice contact option.
     * 					
     * 
     */
    @XmlEnumValue("Voice")
    VOICE("Voice"),
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    SkypeContactOptionCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SkypeContactOptionCodeType fromValue(String v) {
        for (SkypeContactOptionCodeType c: SkypeContactOptionCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
