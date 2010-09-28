
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PictureManagerDetailLevelCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PictureManagerDetailLevelCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="ReturnAll"/>
 *     &lt;enumeration value="ReturnSubscription"/>
 *     &lt;enumeration value="ReturnPicture"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PictureManagerDetailLevelCodeType")
@XmlEnum
public enum PictureManagerDetailLevelCodeType {


    /**
     * 
     * 						(in) All pictures, folders, and account settings. Default value.
     * 					
     * 
     */
    @XmlEnumValue("ReturnAll")
    RETURN_ALL("ReturnAll"),

    /**
     * 
     * 						(in) Only data about the account subscription.
     * 					
     * 
     */
    @XmlEnumValue("ReturnSubscription")
    RETURN_SUBSCRIPTION("ReturnSubscription"),

    /**
     * 
     * 						(in) Return only data about pictures and folders in the authenticated user's account.
     * 					
     * 
     */
    @XmlEnumValue("ReturnPicture")
    RETURN_PICTURE("ReturnPicture"),

    /**
     * 
     * 						(out) Reserved for internal or future use
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    PictureManagerDetailLevelCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PictureManagerDetailLevelCodeType fromValue(String v) {
        for (PictureManagerDetailLevelCodeType c: PictureManagerDetailLevelCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
