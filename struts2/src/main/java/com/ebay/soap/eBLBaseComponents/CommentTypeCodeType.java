
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CommentTypeCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CommentTypeCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Positive"/>
 *     &lt;enumeration value="Neutral"/>
 *     &lt;enumeration value="Negative"/>
 *     &lt;enumeration value="Withdrawn"/>
 *     &lt;enumeration value="IndependentlyWithdrawn"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CommentTypeCodeType")
@XmlEnum
public enum CommentTypeCodeType {


    /**
     * 
     * 								Positive feedback. Increases total feedback score.
     * 					
     * 
     */
    @XmlEnumValue("Positive")
    POSITIVE("Positive"),

    /**
     * 
     * 								Neutral feedback. No effect on total feedback score.
     * 					
     * 
     */
    @XmlEnumValue("Neutral")
    NEUTRAL("Neutral"),

    /**
     * 
     * 								Negative feedback. Decreases total feedback score.
     * 					
     * 
     */
    @XmlEnumValue("Negative")
    NEGATIVE("Negative"),

    /**
     * 
     * 								Withdrawn feedback. Removes the effect of the original
     * 								feedback on total feedback score. Comments from withdrawn feedback
     * 								are still visible.
     * 					
     * 
     */
    @XmlEnumValue("Withdrawn")
    WITHDRAWN("Withdrawn"),

    /**
     * 
     * 								Applies to the eBay Motors site only. Feedback is withdrawn based on
     * 								the decision of a third party.
     * 					
     * 
     */
    @XmlEnumValue("IndependentlyWithdrawn")
    INDEPENDENTLY_WITHDRAWN("IndependentlyWithdrawn"),

    /**
     * 
     * 								Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    CommentTypeCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CommentTypeCodeType fromValue(String v) {
        for (CommentTypeCodeType c: CommentTypeCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
