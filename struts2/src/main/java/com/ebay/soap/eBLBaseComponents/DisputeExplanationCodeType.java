
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DisputeExplanationCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DisputeExplanationCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="BuyerHasNotResponded"/>
 *     &lt;enumeration value="BuyerRefusedToPay"/>
 *     &lt;enumeration value="BuyerNotClearedToPay"/>
 *     &lt;enumeration value="BuyerReturnedItemForRefund"/>
 *     &lt;enumeration value="UnableToResolveTerms"/>
 *     &lt;enumeration value="BuyerNoLongerWantsItem"/>
 *     &lt;enumeration value="BuyerPurchasingMistake"/>
 *     &lt;enumeration value="ShipCountryNotSupported"/>
 *     &lt;enumeration value="ShippingAddressNotConfirmed"/>
 *     &lt;enumeration value="PaymentMethodNotSupported"/>
 *     &lt;enumeration value="BuyerNoLongerRegistered"/>
 *     &lt;enumeration value="OtherExplanation"/>
 *     &lt;enumeration value="Unspecified"/>
 *     &lt;enumeration value="UPIAssistance"/>
 *     &lt;enumeration value="BuyerPaymentNotReceivedOrCleared"/>
 *     &lt;enumeration value="SellerDoesntShipToCountry"/>
 *     &lt;enumeration value="BuyerNotPaid"/>
 *     &lt;enumeration value="UPIAssistanceDisabled"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DisputeExplanationCodeType")
@XmlEnum
public enum DisputeExplanationCodeType {


    /**
     * 
     * 						The buyer has not responded within the 7-day waiting period.
     * 						Allowed when DisputeReason is BuyerHasNotPaid.
     * 					
     * 
     */
    @XmlEnumValue("BuyerHasNotResponded")
    BUYER_HAS_NOT_RESPONDED("BuyerHasNotResponded"),

    /**
     * 
     * 						The buyer has refused to pay. Allowed when DisputeReason is BuyerHasNotPaid.
     * 					
     * 
     */
    @XmlEnumValue("BuyerRefusedToPay")
    BUYER_REFUSED_TO_PAY("BuyerRefusedToPay"),

    /**
     * 
     * 						The buyer is not cleared to pay. Allowed when DisputeReason is BuyerHasNotPaid.
     * 					
     * 
     */
    @XmlEnumValue("BuyerNotClearedToPay")
    BUYER_NOT_CLEARED_TO_PAY("BuyerNotClearedToPay"),

    /**
     * 
     * 						The buyer returned the item for a refund. Allowed when DisputeReason
     * 						is TransactionMutuallyCanceled.
     * 					
     * 
     */
    @XmlEnumValue("BuyerReturnedItemForRefund")
    BUYER_RETURNED_ITEM_FOR_REFUND("BuyerReturnedItemForRefund"),

    /**
     * 
     * 						The buyer and seller were unable to resolve a disagreement over
     * 						terms. Allowed when DisputeReason is TransactionMutuallyCanceled.
     * 					
     * 
     */
    @XmlEnumValue("UnableToResolveTerms")
    UNABLE_TO_RESOLVE_TERMS("UnableToResolveTerms"),

    /**
     * 
     * 						The buyer no longer wants the item. Allowed when DisputeReason is
     * 						TransactionMutuallyCanceled.
     * 					
     * 
     */
    @XmlEnumValue("BuyerNoLongerWantsItem")
    BUYER_NO_LONGER_WANTS_ITEM("BuyerNoLongerWantsItem"),

    /**
     * 
     * 						The buyer made a mistake. Allowed when DisputeReason is
     * 						TransactionMutuallyCanceled.
     * 					
     * 
     */
    @XmlEnumValue("BuyerPurchasingMistake")
    BUYER_PURCHASING_MISTAKE("BuyerPurchasingMistake"),

    /**
     * 
     * 						The buyer requests shipment to a country that the seller
     * 						does not ship to. Allowed when DisputeReason is BuyerHasNotPaid.
     * 						Deprecated.
     * 					
     * 
     */
    @XmlEnumValue("ShipCountryNotSupported")
    SHIP_COUNTRY_NOT_SUPPORTED("ShipCountryNotSupported"),

    /**
     * 
     * 						The buyer requests shipment to an unconfirmed address.
     * 						Allowed when DisputeReason is BuyerHasNotPaid or TransactionMutuallyCanceled.
     * 					
     * 
     */
    @XmlEnumValue("ShippingAddressNotConfirmed")
    SHIPPING_ADDRESS_NOT_CONFIRMED("ShippingAddressNotConfirmed"),

    /**
     * 
     * 						The buyer requests a payment method that the seller does not accept.
     * 						Allowed when DisputeReason is BuyerHasNotPaid or TransactionMutuallyCanceled.
     * 						Deprecated.
     * 					
     * 
     */
    @XmlEnumValue("PaymentMethodNotSupported")
    PAYMENT_METHOD_NOT_SUPPORTED("PaymentMethodNotSupported"),

    /**
     * 
     * 						The buyer is no longer a registered user.
     * 						Allowed when DisputeReason is BuyerHasNotPaid.
     * 					
     * 
     */
    @XmlEnumValue("BuyerNoLongerRegistered")
    BUYER_NO_LONGER_REGISTERED("BuyerNoLongerRegistered"),

    /**
     * 
     * 						Some other reason not specified in this code list.
     * 						Allowed when DisputeReason is either BuyerHasNotPaid OR
     * 						TransactionMutuallyCanceled.
     * 					
     * 
     */
    @XmlEnumValue("OtherExplanation")
    OTHER_EXPLANATION("OtherExplanation"),

    /**
     * 
     * 						Used when DisputeReason is ItemNotReceived or SignificantlyNotAsDescribed.
     * 					
     * 
     */
    @XmlEnumValue("Unspecified")
    UNSPECIFIED("Unspecified"),

    /**
     * 
     * 						The dispute was opened by the Unpaid Item Assistance mechanism. This can only
     * 						be set by eBay.
     * 					
     * 
     */
    @XmlEnumValue("UPIAssistance")
    UPI_ASSISTANCE("UPIAssistance"),

    /**
     * 
     * 						Payment was not received or didn't clear.
     * 					
     * 
     */
    @XmlEnumValue("BuyerPaymentNotReceivedOrCleared")
    BUYER_PAYMENT_NOT_RECEIVED_OR_CLEARED("BuyerPaymentNotReceivedOrCleared"),

    /**
     * 
     * 						Seller doesn't ship to the country requested by buyer.
     * 					
     * 
     */
    @XmlEnumValue("SellerDoesntShipToCountry")
    SELLER_DOESNT_SHIP_TO_COUNTRY("SellerDoesntShipToCountry"),

    /**
     * 
     * 						Buyer has not paid for the item.
     * 					
     * 
     */
    @XmlEnumValue("BuyerNotPaid")
    BUYER_NOT_PAID("BuyerNotPaid"),

    /**
     * 
     * 						The dispute was opened by the Unpaid Item Assistance mechanism and was
     * 						subsequently converted to a manual dispute, either by the seller or
     * 						by eBay.  This can only be set by eBay.
     * 					
     * 
     */
    @XmlEnumValue("UPIAssistanceDisabled")
    UPI_ASSISTANCE_DISABLED("UPIAssistanceDisabled"),

    /**
     * 
     * 						Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    DisputeExplanationCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DisputeExplanationCodeType fromValue(String v) {
        for (DisputeExplanationCodeType c: DisputeExplanationCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
