
package com.ebay.soap.eBLBaseComponents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3c.dom.Element;


/**
 * 
 * 				Contains the transaction status, e.g. the buyer's online
 * 				payment and whether the checkout process for the transaction is complete.
 * 			
 * 
 * <p>Java class for TransactionStatusType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransactionStatusType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="eBayPaymentStatus" type="{urn:ebay:apis:eBLBaseComponents}PaymentStatusCodeType" minOccurs="0"/>
 *         &lt;element name="CheckoutStatus" type="{urn:ebay:apis:eBLBaseComponents}CheckoutStatusCodeType" minOccurs="0"/>
 *         &lt;element name="LastTimeModified" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="PaymentMethodUsed" type="{urn:ebay:apis:eBLBaseComponents}BuyerPaymentMethodCodeType" minOccurs="0"/>
 *         &lt;element name="CompleteStatus" type="{urn:ebay:apis:eBLBaseComponents}CompleteStatusCodeType" minOccurs="0"/>
 *         &lt;element name="BuyerSelectedShipping" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="PaymentHoldStatus" type="{urn:ebay:apis:eBLBaseComponents}PaymentHoldStatusCodeType" minOccurs="0"/>
 *         &lt;element name="IntegratedMerchantCreditCardEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;any/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransactionStatusType", propOrder = {
    "eBayPaymentStatus",
    "checkoutStatus",
    "lastTimeModified",
    "paymentMethodUsed",
    "completeStatus",
    "buyerSelectedShipping",
    "paymentHoldStatus",
    "integratedMerchantCreditCardEnabled",
    "any"
})
public class TransactionStatusType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    protected PaymentStatusCodeType eBayPaymentStatus;
    @XmlElement(name = "CheckoutStatus")
    protected CheckoutStatusCodeType checkoutStatus;
    @XmlElement(name = "LastTimeModified", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar lastTimeModified;
    @XmlElement(name = "PaymentMethodUsed")
    protected BuyerPaymentMethodCodeType paymentMethodUsed;
    @XmlElement(name = "CompleteStatus")
    protected CompleteStatusCodeType completeStatus;
    @XmlElement(name = "BuyerSelectedShipping")
    protected Boolean buyerSelectedShipping;
    @XmlElement(name = "PaymentHoldStatus")
    protected PaymentHoldStatusCodeType paymentHoldStatus;
    @XmlElement(name = "IntegratedMerchantCreditCardEnabled")
    protected Boolean integratedMerchantCreditCardEnabled;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the eBayPaymentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentStatusCodeType }
     *     
     */
    public PaymentStatusCodeType getEBayPaymentStatus() {
        return eBayPaymentStatus;
    }

    /**
     * Sets the value of the eBayPaymentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentStatusCodeType }
     *     
     */
    public void setEBayPaymentStatus(PaymentStatusCodeType value) {
        this.eBayPaymentStatus = value;
    }

    /**
     * Gets the value of the checkoutStatus property.
     * 
     * @return
     *     possible object is
     *     {@link CheckoutStatusCodeType }
     *     
     */
    public CheckoutStatusCodeType getCheckoutStatus() {
        return checkoutStatus;
    }

    /**
     * Sets the value of the checkoutStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckoutStatusCodeType }
     *     
     */
    public void setCheckoutStatus(CheckoutStatusCodeType value) {
        this.checkoutStatus = value;
    }

    /**
     * Gets the value of the lastTimeModified property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getLastTimeModified() {
        return lastTimeModified;
    }

    /**
     * Sets the value of the lastTimeModified property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastTimeModified(Calendar value) {
        this.lastTimeModified = value;
    }

    /**
     * Gets the value of the paymentMethodUsed property.
     * 
     * @return
     *     possible object is
     *     {@link BuyerPaymentMethodCodeType }
     *     
     */
    public BuyerPaymentMethodCodeType getPaymentMethodUsed() {
        return paymentMethodUsed;
    }

    /**
     * Sets the value of the paymentMethodUsed property.
     * 
     * @param value
     *     allowed object is
     *     {@link BuyerPaymentMethodCodeType }
     *     
     */
    public void setPaymentMethodUsed(BuyerPaymentMethodCodeType value) {
        this.paymentMethodUsed = value;
    }

    /**
     * Gets the value of the completeStatus property.
     * 
     * @return
     *     possible object is
     *     {@link CompleteStatusCodeType }
     *     
     */
    public CompleteStatusCodeType getCompleteStatus() {
        return completeStatus;
    }

    /**
     * Sets the value of the completeStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link CompleteStatusCodeType }
     *     
     */
    public void setCompleteStatus(CompleteStatusCodeType value) {
        this.completeStatus = value;
    }

    /**
     * Gets the value of the buyerSelectedShipping property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBuyerSelectedShipping() {
        return buyerSelectedShipping;
    }

    /**
     * Sets the value of the buyerSelectedShipping property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBuyerSelectedShipping(Boolean value) {
        this.buyerSelectedShipping = value;
    }

    /**
     * Gets the value of the paymentHoldStatus property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentHoldStatusCodeType }
     *     
     */
    public PaymentHoldStatusCodeType getPaymentHoldStatus() {
        return paymentHoldStatus;
    }

    /**
     * Sets the value of the paymentHoldStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentHoldStatusCodeType }
     *     
     */
    public void setPaymentHoldStatus(PaymentHoldStatusCodeType value) {
        this.paymentHoldStatus = value;
    }

    /**
     * Gets the value of the integratedMerchantCreditCardEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIntegratedMerchantCreditCardEnabled() {
        return integratedMerchantCreditCardEnabled;
    }

    /**
     * Sets the value of the integratedMerchantCreditCardEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIntegratedMerchantCreditCardEnabled(Boolean value) {
        this.integratedMerchantCreditCardEnabled = value;
    }

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link Element }
     *     {@link Object }
     *     
     */
    public Object[] getAny() {
        if (this.any == null) {
            return new Object[ 0 ] ;
        }
        return ((Object[]) this.any.toArray(new Object[this.any.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link Element }
     *     {@link Object }
     *     
     */
    public Object getAny(int idx) {
        if (this.any == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.any.get(idx);
    }

    public int getAnyLength() {
        if (this.any == null) {
            return  0;
        }
        return this.any.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link Element }
     *     {@link Object }
     *     
     */
    public void setAny(Object[] values) {
        this._getAny().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.any.add(values[i]);
        }
    }

    protected List<Object> _getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return any;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link Element }
     *     {@link Object }
     *     
     */
    public Object setAny(int idx, Object value) {
        return this.any.set(idx, value);
    }

}
