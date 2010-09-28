
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AutomatedLeaveFeedbackEventCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AutomatedLeaveFeedbackEventCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="PositiveFeedbackReceived"/>
 *     &lt;enumeration value="PaymentReceived"/>
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AutomatedLeaveFeedbackEventCodeType")
@XmlEnum
public enum AutomatedLeaveFeedbackEventCodeType {


    /**
     * 
     * 						Leave feedback automatically when you receive positive feedback. 
     * 					
     * 
     */
    @XmlEnumValue("PositiveFeedbackReceived")
    POSITIVE_FEEDBACK_RECEIVED("PositiveFeedbackReceived"),

    /**
     * 
     * 						Leave feedback automatically when you receive payment. 
     * 					
     * 
     */
    @XmlEnumValue("PaymentReceived")
    PAYMENT_RECEIVED("PaymentReceived"),

    /**
     * 
     * 						Do not leave feedback automatically. 
     * 					
     * 
     */
    @XmlEnumValue("None")
    NONE("None"),

    /**
     * 
     * 						(out) Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    AutomatedLeaveFeedbackEventCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AutomatedLeaveFeedbackEventCodeType fromValue(String v) {
        for (AutomatedLeaveFeedbackEventCodeType c: AutomatedLeaveFeedbackEventCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
