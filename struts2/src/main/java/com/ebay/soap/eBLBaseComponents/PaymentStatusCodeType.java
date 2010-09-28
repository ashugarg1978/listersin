
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PaymentStatusCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PaymentStatusCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="NoPaymentFailure"/>
 *     &lt;enumeration value="BuyerECheckBounced"/>
 *     &lt;enumeration value="BuyerCreditCardFailed"/>
 *     &lt;enumeration value="BuyerFailedPaymentReportedBySeller"/>
 *     &lt;enumeration value="PayPalPaymentInProcess"/>
 *     &lt;enumeration value="PaymentInProcess"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PaymentStatusCodeType")
@XmlEnum
public enum PaymentStatusCodeType {


    /**
     * 
     * 						(out) No payment failure.
     * 					
     * 
     */
    @XmlEnumValue("NoPaymentFailure")
    NO_PAYMENT_FAILURE("NoPaymentFailure"),

    /**
     * 
     * 						(out) The buyer's eCheck bounced.
     * 					
     * 
     */
    @XmlEnumValue("BuyerECheckBounced")
    BUYER_E_CHECK_BOUNCED("BuyerECheckBounced"),

    /**
     * 
     * 						(out) The buyer's credit card failed.
     * 					
     * 
     */
    @XmlEnumValue("BuyerCreditCardFailed")
    BUYER_CREDIT_CARD_FAILED("BuyerCreditCardFailed"),

    /**
     * 
     * 						(out) The seller reports that the buyer's payment failed.
     * 					
     * 
     */
    @XmlEnumValue("BuyerFailedPaymentReportedBySeller")
    BUYER_FAILED_PAYMENT_REPORTED_BY_SELLER("BuyerFailedPaymentReportedBySeller"),

    /**
     * 
     * 						(out) The payment from buyer to seller is in PayPal process, but has not
     * 						yet been completed.
     * 					
     * 
     */
    @XmlEnumValue("PayPalPaymentInProcess")
    PAY_PAL_PAYMENT_IN_PROCESS("PayPalPaymentInProcess"),

    /**
     * 
     * 						(out) Currently for eBay Germany only.
     * 					
     * 
     */
    @XmlEnumValue("PaymentInProcess")
    PAYMENT_IN_PROCESS("PaymentInProcess"),

    /**
     * 
     * 						(out) Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    PaymentStatusCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PaymentStatusCodeType fromValue(String v) {
        for (PaymentStatusCodeType c: PaymentStatusCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
