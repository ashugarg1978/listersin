
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PaymentHoldStatusCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PaymentHoldStatusCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="PaymentReview"/>
 *     &lt;enumeration value="MerchantHold"/>
 *     &lt;enumeration value="Released"/>
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="NewSellerHold"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PaymentHoldStatusCodeType")
@XmlEnum
public enum PaymentHoldStatusCodeType {


    /**
     * 
     * 						Results from a possible issue with a buyer.
     * 						If this value is returned, then the following values, as a result, are returned:
     * 						In GetMyeBaySelling, NotPaid is returned in TransactionArray.Transaction.SellerPaidStatus.
     * 						In GetMyeBayBuying, PaidWithPayPal is returned in TransactionArray.Transaction.BuyerPaidStatus.
     * 					
     * 
     */
    @XmlEnumValue("PaymentReview")
    PAYMENT_REVIEW("PaymentReview"),

    /**
     * 
     * 						Results from a possible issue with a seller.
     * 						If this value is returned, then the following values, as a result, are returned:
     * 						In GetMyeBaySelling, PaidWithPayPal is returned in TransactionArray.Transaction.SellerPaidStatus.
     * 						In GetMyeBayBuying, PaidWithPayPal is returned in TransactionArray.Transaction.BuyerPaidStatus.
     * 					
     * 
     */
    @XmlEnumValue("MerchantHold")
    MERCHANT_HOLD("MerchantHold"),

    /**
     * 
     * 						Indicates that a payment hold was released.
     * 					
     * 
     */
    @XmlEnumValue("Released")
    RELEASED("Released"),

    /**
     * 
     * 						Indicates that there is no payment hold on the item.
     * 					
     * 
     */
    @XmlEnumValue("None")
    NONE("None"),

    /**
     * 
     * 						Results from a "new seller hold," in which
     * 						PayPal may hold the payments to a new seller for up to 21 days.
     * 					
     * 
     */
    @XmlEnumValue("NewSellerHold")
    NEW_SELLER_HOLD("NewSellerHold"),

    /**
     * 
     * 						Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    PaymentHoldStatusCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PaymentHoldStatusCodeType fromValue(String v) {
        for (PaymentHoldStatusCodeType c: PaymentHoldStatusCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
