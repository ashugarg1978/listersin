
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DisputeFilterTypeCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DisputeFilterTypeCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="AllInvolvedDisputes"/>
 *     &lt;enumeration value="DisputesAwaitingMyResponse"/>
 *     &lt;enumeration value="DisputesAwaitingOtherPartyResponse"/>
 *     &lt;enumeration value="AllInvolvedClosedDisputes"/>
 *     &lt;enumeration value="EligibleForCredit"/>
 *     &lt;enumeration value="UnpaidItemDisputes"/>
 *     &lt;enumeration value="ItemNotReceivedDisputes"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DisputeFilterTypeCodeType")
@XmlEnum
public enum DisputeFilterTypeCodeType {


    /**
     * 
     * 						All disputes that involve me as buyer or seller.
     * 					
     * 
     */
    @XmlEnumValue("AllInvolvedDisputes")
    ALL_INVOLVED_DISPUTES("AllInvolvedDisputes"),

    /**
     * 
     * 						All disputes that involve me as buyer or seller and
     * 						are awaiting my response. Default value.
     * 					
     * 
     */
    @XmlEnumValue("DisputesAwaitingMyResponse")
    DISPUTES_AWAITING_MY_RESPONSE("DisputesAwaitingMyResponse"),

    /**
     * 
     * 						All disputes that involve me as buyer or seller and
     * 						are awaiting the other party's response.
     * 					
     * 
     */
    @XmlEnumValue("DisputesAwaitingOtherPartyResponse")
    DISPUTES_AWAITING_OTHER_PARTY_RESPONSE("DisputesAwaitingOtherPartyResponse"),

    /**
     * 
     * 						All disputes that involve me as buyer or seller and
     * 						are closed.
     * 					
     * 
     */
    @XmlEnumValue("AllInvolvedClosedDisputes")
    ALL_INVOLVED_CLOSED_DISPUTES("AllInvolvedClosedDisputes"),

    /**
     * 
     * 						All disputes that involve me as buyer or seller and
     * 						are eligible for a Final Value Fee credit, whether or not
     * 						the credit has been granted.
     * 					
     * 
     */
    @XmlEnumValue("EligibleForCredit")
    ELIGIBLE_FOR_CREDIT("EligibleForCredit"),

    /**
     * 
     * 						All Unpaid item disputes.
     * 					
     * 
     */
    @XmlEnumValue("UnpaidItemDisputes")
    UNPAID_ITEM_DISPUTES("UnpaidItemDisputes"),

    /**
     * 
     * 						All Item Not Received disputes.
     * 					
     * 
     */
    @XmlEnumValue("ItemNotReceivedDisputes")
    ITEM_NOT_RECEIVED_DISPUTES("ItemNotReceivedDisputes"),

    /**
     * 
     * 						Reserved for future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    DisputeFilterTypeCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DisputeFilterTypeCodeType fromValue(String v) {
        for (DisputeFilterTypeCodeType c: DisputeFilterTypeCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
