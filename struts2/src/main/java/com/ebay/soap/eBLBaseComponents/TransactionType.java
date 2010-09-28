
package com.ebay.soap.eBLBaseComponents;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * 				Contains information about a single transaction. A transaction contains
 * 				information about the sale one or multiple items from a listing to a single
 * 				buyer. The eBay system creates a transaction when a buyer has made a purchase
 * 				(fixed-price listings) or is the winning bidder (BIN and competitive-bid item
 * 				listings). A listing can spawn one or more transactions in these cases:
 * 				multi-item fixed-price listings and Dutch listings. Single-item fixed-price
 * 				listings and Chinese listings can spawn only a single transaction.
 * 				<br><br>
 * 				<span class="tablenote"><strong>Note:</strong>
 * 					As of version 619, Dutch-style (multi-item) competitive-bid auctions are deprecated.
 * 					eBay throws an error if you submit a Dutch item listing with AddItem
 * 					or VerifyAddItem. If you use RelistItem to update a Dutch auction listing,
 * 					eBay generates a warning and resets the Quantity value to 1.
 * 				</span>
 * 				<br>
 * 			
 * 
 * <p>Java class for TransactionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransactionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AmountPaid" type="{urn:ebay:apis:eBLBaseComponents}AmountType" minOccurs="0"/>
 *         &lt;element name="AdjustmentAmount" type="{urn:ebay:apis:eBLBaseComponents}AmountType" minOccurs="0"/>
 *         &lt;element name="ConvertedAdjustmentAmount" type="{urn:ebay:apis:eBLBaseComponents}AmountType" minOccurs="0"/>
 *         &lt;element name="Buyer" type="{urn:ebay:apis:eBLBaseComponents}UserType" minOccurs="0"/>
 *         &lt;element name="ShippingDetails" type="{urn:ebay:apis:eBLBaseComponents}ShippingDetailsType" minOccurs="0"/>
 *         &lt;element name="ConvertedAmountPaid" type="{urn:ebay:apis:eBLBaseComponents}AmountType" minOccurs="0"/>
 *         &lt;element name="ConvertedTransactionPrice" type="{urn:ebay:apis:eBLBaseComponents}AmountType" minOccurs="0"/>
 *         &lt;element name="CreatedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="DepositType" type="{urn:ebay:apis:eBLBaseComponents}DepositTypeCodeType" minOccurs="0"/>
 *         &lt;element name="Item" type="{urn:ebay:apis:eBLBaseComponents}ItemType" minOccurs="0"/>
 *         &lt;element name="QuantityPurchased" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Status" type="{urn:ebay:apis:eBLBaseComponents}TransactionStatusType" minOccurs="0"/>
 *         &lt;element name="TransactionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransactionPrice" type="{urn:ebay:apis:eBLBaseComponents}AmountType" minOccurs="0"/>
 *         &lt;element name="BestOfferSale" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="VATPercent" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="ExternalTransaction" type="{urn:ebay:apis:eBLBaseComponents}ExternalTransactionType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SellingManagerProductDetails" type="{urn:ebay:apis:eBLBaseComponents}SellingManagerProductDetailsType" minOccurs="0"/>
 *         &lt;element name="ShippingServiceSelected" type="{urn:ebay:apis:eBLBaseComponents}ShippingServiceOptionsType" minOccurs="0"/>
 *         &lt;element name="BuyerMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DutchAuctionBid" type="{urn:ebay:apis:eBLBaseComponents}AmountType" minOccurs="0"/>
 *         &lt;element name="BuyerPaidStatus" type="{urn:ebay:apis:eBLBaseComponents}PaidStatusCodeType" minOccurs="0"/>
 *         &lt;element name="SellerPaidStatus" type="{urn:ebay:apis:eBLBaseComponents}PaidStatusCodeType" minOccurs="0"/>
 *         &lt;element name="PaidTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ShippedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="TotalPrice" type="{urn:ebay:apis:eBLBaseComponents}AmountType" minOccurs="0"/>
 *         &lt;element name="FeedbackLeft" type="{urn:ebay:apis:eBLBaseComponents}FeedbackInfoType" minOccurs="0"/>
 *         &lt;element name="FeedbackReceived" type="{urn:ebay:apis:eBLBaseComponents}FeedbackInfoType" minOccurs="0"/>
 *         &lt;element name="ContainingOrder" type="{urn:ebay:apis:eBLBaseComponents}OrderType" minOccurs="0"/>
 *         &lt;element name="FinalValueFee" type="{urn:ebay:apis:eBLBaseComponents}AmountType" minOccurs="0"/>
 *         &lt;element name="ListingCheckoutRedirectPreference" type="{urn:ebay:apis:eBLBaseComponents}ListingCheckoutRedirectPreferenceType" minOccurs="0"/>
 *         &lt;element name="RefundArray" type="{urn:ebay:apis:eBLBaseComponents}RefundArrayType" minOccurs="0"/>
 *         &lt;element name="TransactionSiteID" type="{urn:ebay:apis:eBLBaseComponents}SiteCodeType" minOccurs="0"/>
 *         &lt;element name="Platform" type="{urn:ebay:apis:eBLBaseComponents}TransactionPlatformCodeType" minOccurs="0"/>
 *         &lt;element name="CartID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SellerContactBuyerByEmail" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="PayPalEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PaisaPayID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BuyerGuaranteePrice" type="{urn:ebay:apis:eBLBaseComponents}AmountType" minOccurs="0"/>
 *         &lt;element name="Variation" type="{urn:ebay:apis:eBLBaseComponents}VariationType" minOccurs="0"/>
 *         &lt;element name="BuyerCheckoutMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "TransactionType", propOrder = {
    "amountPaid",
    "adjustmentAmount",
    "convertedAdjustmentAmount",
    "buyer",
    "shippingDetails",
    "convertedAmountPaid",
    "convertedTransactionPrice",
    "createdDate",
    "depositType",
    "item",
    "quantityPurchased",
    "status",
    "transactionID",
    "transactionPrice",
    "bestOfferSale",
    "vatPercent",
    "externalTransaction",
    "sellingManagerProductDetails",
    "shippingServiceSelected",
    "buyerMessage",
    "dutchAuctionBid",
    "buyerPaidStatus",
    "sellerPaidStatus",
    "paidTime",
    "shippedTime",
    "totalPrice",
    "feedbackLeft",
    "feedbackReceived",
    "containingOrder",
    "finalValueFee",
    "listingCheckoutRedirectPreference",
    "refundArray",
    "transactionSiteID",
    "platform",
    "cartID",
    "sellerContactBuyerByEmail",
    "payPalEmailAddress",
    "paisaPayID",
    "buyerGuaranteePrice",
    "variation",
    "buyerCheckoutMessage",
    "any"
})
public class TransactionType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "AmountPaid")
    protected AmountType amountPaid;
    @XmlElement(name = "AdjustmentAmount")
    protected AmountType adjustmentAmount;
    @XmlElement(name = "ConvertedAdjustmentAmount")
    protected AmountType convertedAdjustmentAmount;
    @XmlElement(name = "Buyer")
    protected UserType buyer;
    @XmlElement(name = "ShippingDetails")
    protected ShippingDetailsType shippingDetails;
    @XmlElement(name = "ConvertedAmountPaid")
    protected AmountType convertedAmountPaid;
    @XmlElement(name = "ConvertedTransactionPrice")
    protected AmountType convertedTransactionPrice;
    @XmlElement(name = "CreatedDate", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar createdDate;
    @XmlElement(name = "DepositType")
    protected DepositTypeCodeType depositType;
    @XmlElement(name = "Item")
    protected ItemType item;
    @XmlElement(name = "QuantityPurchased")
    protected Integer quantityPurchased;
    @XmlElement(name = "Status")
    protected TransactionStatusType status;
    @XmlElement(name = "TransactionID")
    protected String transactionID;
    @XmlElement(name = "TransactionPrice")
    protected AmountType transactionPrice;
    @XmlElement(name = "BestOfferSale")
    protected Boolean bestOfferSale;
    @XmlElement(name = "VATPercent")
    protected BigDecimal vatPercent;
    @XmlElement(name = "ExternalTransaction")
    protected List<ExternalTransactionType> externalTransaction;
    @XmlElement(name = "SellingManagerProductDetails")
    protected SellingManagerProductDetailsType sellingManagerProductDetails;
    @XmlElement(name = "ShippingServiceSelected")
    protected ShippingServiceOptionsType shippingServiceSelected;
    @XmlElement(name = "BuyerMessage")
    protected String buyerMessage;
    @XmlElement(name = "DutchAuctionBid")
    protected AmountType dutchAuctionBid;
    @XmlElement(name = "BuyerPaidStatus")
    protected PaidStatusCodeType buyerPaidStatus;
    @XmlElement(name = "SellerPaidStatus")
    protected PaidStatusCodeType sellerPaidStatus;
    @XmlElement(name = "PaidTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar paidTime;
    @XmlElement(name = "ShippedTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar shippedTime;
    @XmlElement(name = "TotalPrice")
    protected AmountType totalPrice;
    @XmlElement(name = "FeedbackLeft")
    protected FeedbackInfoType feedbackLeft;
    @XmlElement(name = "FeedbackReceived")
    protected FeedbackInfoType feedbackReceived;
    @XmlElement(name = "ContainingOrder")
    protected OrderType containingOrder;
    @XmlElement(name = "FinalValueFee")
    protected AmountType finalValueFee;
    @XmlElement(name = "ListingCheckoutRedirectPreference")
    protected ListingCheckoutRedirectPreferenceType listingCheckoutRedirectPreference;
    @XmlElement(name = "RefundArray")
    protected RefundArrayType refundArray;
    @XmlElement(name = "TransactionSiteID")
    protected SiteCodeType transactionSiteID;
    @XmlElement(name = "Platform")
    protected TransactionPlatformCodeType platform;
    @XmlElement(name = "CartID")
    protected String cartID;
    @XmlElement(name = "SellerContactBuyerByEmail")
    protected Boolean sellerContactBuyerByEmail;
    @XmlElement(name = "PayPalEmailAddress")
    protected String payPalEmailAddress;
    @XmlElement(name = "PaisaPayID")
    protected String paisaPayID;
    @XmlElement(name = "BuyerGuaranteePrice")
    protected AmountType buyerGuaranteePrice;
    @XmlElement(name = "Variation")
    protected VariationType variation;
    @XmlElement(name = "BuyerCheckoutMessage")
    protected String buyerCheckoutMessage;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the amountPaid property.
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getAmountPaid() {
        return amountPaid;
    }

    /**
     * Sets the value of the amountPaid property.
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setAmountPaid(AmountType value) {
        this.amountPaid = value;
    }

    /**
     * Gets the value of the adjustmentAmount property.
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getAdjustmentAmount() {
        return adjustmentAmount;
    }

    /**
     * Sets the value of the adjustmentAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setAdjustmentAmount(AmountType value) {
        this.adjustmentAmount = value;
    }

    /**
     * Gets the value of the convertedAdjustmentAmount property.
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getConvertedAdjustmentAmount() {
        return convertedAdjustmentAmount;
    }

    /**
     * Sets the value of the convertedAdjustmentAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setConvertedAdjustmentAmount(AmountType value) {
        this.convertedAdjustmentAmount = value;
    }

    /**
     * Gets the value of the buyer property.
     * 
     * @return
     *     possible object is
     *     {@link UserType }
     *     
     */
    public UserType getBuyer() {
        return buyer;
    }

    /**
     * Sets the value of the buyer property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserType }
     *     
     */
    public void setBuyer(UserType value) {
        this.buyer = value;
    }

    /**
     * Gets the value of the shippingDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ShippingDetailsType }
     *     
     */
    public ShippingDetailsType getShippingDetails() {
        return shippingDetails;
    }

    /**
     * Sets the value of the shippingDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShippingDetailsType }
     *     
     */
    public void setShippingDetails(ShippingDetailsType value) {
        this.shippingDetails = value;
    }

    /**
     * Gets the value of the convertedAmountPaid property.
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getConvertedAmountPaid() {
        return convertedAmountPaid;
    }

    /**
     * Sets the value of the convertedAmountPaid property.
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setConvertedAmountPaid(AmountType value) {
        this.convertedAmountPaid = value;
    }

    /**
     * Gets the value of the convertedTransactionPrice property.
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getConvertedTransactionPrice() {
        return convertedTransactionPrice;
    }

    /**
     * Sets the value of the convertedTransactionPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setConvertedTransactionPrice(AmountType value) {
        this.convertedTransactionPrice = value;
    }

    /**
     * Gets the value of the createdDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the value of the createdDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedDate(Calendar value) {
        this.createdDate = value;
    }

    /**
     * Gets the value of the depositType property.
     * 
     * @return
     *     possible object is
     *     {@link DepositTypeCodeType }
     *     
     */
    public DepositTypeCodeType getDepositType() {
        return depositType;
    }

    /**
     * Sets the value of the depositType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DepositTypeCodeType }
     *     
     */
    public void setDepositType(DepositTypeCodeType value) {
        this.depositType = value;
    }

    /**
     * Gets the value of the item property.
     * 
     * @return
     *     possible object is
     *     {@link ItemType }
     *     
     */
    public ItemType getItem() {
        return item;
    }

    /**
     * Sets the value of the item property.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemType }
     *     
     */
    public void setItem(ItemType value) {
        this.item = value;
    }

    /**
     * Gets the value of the quantityPurchased property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQuantityPurchased() {
        return quantityPurchased;
    }

    /**
     * Sets the value of the quantityPurchased property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQuantityPurchased(Integer value) {
        this.quantityPurchased = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionStatusType }
     *     
     */
    public TransactionStatusType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionStatusType }
     *     
     */
    public void setStatus(TransactionStatusType value) {
        this.status = value;
    }

    /**
     * Gets the value of the transactionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * Sets the value of the transactionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionID(String value) {
        this.transactionID = value;
    }

    /**
     * Gets the value of the transactionPrice property.
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getTransactionPrice() {
        return transactionPrice;
    }

    /**
     * Sets the value of the transactionPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setTransactionPrice(AmountType value) {
        this.transactionPrice = value;
    }

    /**
     * Gets the value of the bestOfferSale property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBestOfferSale() {
        return bestOfferSale;
    }

    /**
     * Sets the value of the bestOfferSale property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBestOfferSale(Boolean value) {
        this.bestOfferSale = value;
    }

    /**
     * Gets the value of the vatPercent property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVATPercent() {
        return vatPercent;
    }

    /**
     * Sets the value of the vatPercent property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVATPercent(BigDecimal value) {
        this.vatPercent = value;
    }

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link ExternalTransactionType }
     *     
     */
    public ExternalTransactionType[] getExternalTransaction() {
        if (this.externalTransaction == null) {
            return new ExternalTransactionType[ 0 ] ;
        }
        return ((ExternalTransactionType[]) this.externalTransaction.toArray(new ExternalTransactionType[this.externalTransaction.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link ExternalTransactionType }
     *     
     */
    public ExternalTransactionType getExternalTransaction(int idx) {
        if (this.externalTransaction == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.externalTransaction.get(idx);
    }

    public int getExternalTransactionLength() {
        if (this.externalTransaction == null) {
            return  0;
        }
        return this.externalTransaction.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link ExternalTransactionType }
     *     
     */
    public void setExternalTransaction(ExternalTransactionType[] values) {
        this._getExternalTransaction().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.externalTransaction.add(values[i]);
        }
    }

    protected List<ExternalTransactionType> _getExternalTransaction() {
        if (externalTransaction == null) {
            externalTransaction = new ArrayList<ExternalTransactionType>();
        }
        return externalTransaction;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link ExternalTransactionType }
     *     
     */
    public ExternalTransactionType setExternalTransaction(int idx, ExternalTransactionType value) {
        return this.externalTransaction.set(idx, value);
    }

    /**
     * Gets the value of the sellingManagerProductDetails property.
     * 
     * @return
     *     possible object is
     *     {@link SellingManagerProductDetailsType }
     *     
     */
    public SellingManagerProductDetailsType getSellingManagerProductDetails() {
        return sellingManagerProductDetails;
    }

    /**
     * Sets the value of the sellingManagerProductDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link SellingManagerProductDetailsType }
     *     
     */
    public void setSellingManagerProductDetails(SellingManagerProductDetailsType value) {
        this.sellingManagerProductDetails = value;
    }

    /**
     * Gets the value of the shippingServiceSelected property.
     * 
     * @return
     *     possible object is
     *     {@link ShippingServiceOptionsType }
     *     
     */
    public ShippingServiceOptionsType getShippingServiceSelected() {
        return shippingServiceSelected;
    }

    /**
     * Sets the value of the shippingServiceSelected property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShippingServiceOptionsType }
     *     
     */
    public void setShippingServiceSelected(ShippingServiceOptionsType value) {
        this.shippingServiceSelected = value;
    }

    /**
     * Gets the value of the buyerMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuyerMessage() {
        return buyerMessage;
    }

    /**
     * Sets the value of the buyerMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuyerMessage(String value) {
        this.buyerMessage = value;
    }

    /**
     * Gets the value of the dutchAuctionBid property.
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getDutchAuctionBid() {
        return dutchAuctionBid;
    }

    /**
     * Sets the value of the dutchAuctionBid property.
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setDutchAuctionBid(AmountType value) {
        this.dutchAuctionBid = value;
    }

    /**
     * Gets the value of the buyerPaidStatus property.
     * 
     * @return
     *     possible object is
     *     {@link PaidStatusCodeType }
     *     
     */
    public PaidStatusCodeType getBuyerPaidStatus() {
        return buyerPaidStatus;
    }

    /**
     * Sets the value of the buyerPaidStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaidStatusCodeType }
     *     
     */
    public void setBuyerPaidStatus(PaidStatusCodeType value) {
        this.buyerPaidStatus = value;
    }

    /**
     * Gets the value of the sellerPaidStatus property.
     * 
     * @return
     *     possible object is
     *     {@link PaidStatusCodeType }
     *     
     */
    public PaidStatusCodeType getSellerPaidStatus() {
        return sellerPaidStatus;
    }

    /**
     * Sets the value of the sellerPaidStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaidStatusCodeType }
     *     
     */
    public void setSellerPaidStatus(PaidStatusCodeType value) {
        this.sellerPaidStatus = value;
    }

    /**
     * Gets the value of the paidTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getPaidTime() {
        return paidTime;
    }

    /**
     * Sets the value of the paidTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaidTime(Calendar value) {
        this.paidTime = value;
    }

    /**
     * Gets the value of the shippedTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getShippedTime() {
        return shippedTime;
    }

    /**
     * Sets the value of the shippedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShippedTime(Calendar value) {
        this.shippedTime = value;
    }

    /**
     * Gets the value of the totalPrice property.
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the value of the totalPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setTotalPrice(AmountType value) {
        this.totalPrice = value;
    }

    /**
     * Gets the value of the feedbackLeft property.
     * 
     * @return
     *     possible object is
     *     {@link FeedbackInfoType }
     *     
     */
    public FeedbackInfoType getFeedbackLeft() {
        return feedbackLeft;
    }

    /**
     * Sets the value of the feedbackLeft property.
     * 
     * @param value
     *     allowed object is
     *     {@link FeedbackInfoType }
     *     
     */
    public void setFeedbackLeft(FeedbackInfoType value) {
        this.feedbackLeft = value;
    }

    /**
     * Gets the value of the feedbackReceived property.
     * 
     * @return
     *     possible object is
     *     {@link FeedbackInfoType }
     *     
     */
    public FeedbackInfoType getFeedbackReceived() {
        return feedbackReceived;
    }

    /**
     * Sets the value of the feedbackReceived property.
     * 
     * @param value
     *     allowed object is
     *     {@link FeedbackInfoType }
     *     
     */
    public void setFeedbackReceived(FeedbackInfoType value) {
        this.feedbackReceived = value;
    }

    /**
     * Gets the value of the containingOrder property.
     * 
     * @return
     *     possible object is
     *     {@link OrderType }
     *     
     */
    public OrderType getContainingOrder() {
        return containingOrder;
    }

    /**
     * Sets the value of the containingOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderType }
     *     
     */
    public void setContainingOrder(OrderType value) {
        this.containingOrder = value;
    }

    /**
     * Gets the value of the finalValueFee property.
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getFinalValueFee() {
        return finalValueFee;
    }

    /**
     * Sets the value of the finalValueFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setFinalValueFee(AmountType value) {
        this.finalValueFee = value;
    }

    /**
     * Gets the value of the listingCheckoutRedirectPreference property.
     * 
     * @return
     *     possible object is
     *     {@link ListingCheckoutRedirectPreferenceType }
     *     
     */
    public ListingCheckoutRedirectPreferenceType getListingCheckoutRedirectPreference() {
        return listingCheckoutRedirectPreference;
    }

    /**
     * Sets the value of the listingCheckoutRedirectPreference property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListingCheckoutRedirectPreferenceType }
     *     
     */
    public void setListingCheckoutRedirectPreference(ListingCheckoutRedirectPreferenceType value) {
        this.listingCheckoutRedirectPreference = value;
    }

    /**
     * Gets the value of the refundArray property.
     * 
     * @return
     *     possible object is
     *     {@link RefundArrayType }
     *     
     */
    public RefundArrayType getRefundArray() {
        return refundArray;
    }

    /**
     * Sets the value of the refundArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link RefundArrayType }
     *     
     */
    public void setRefundArray(RefundArrayType value) {
        this.refundArray = value;
    }

    /**
     * Gets the value of the transactionSiteID property.
     * 
     * @return
     *     possible object is
     *     {@link SiteCodeType }
     *     
     */
    public SiteCodeType getTransactionSiteID() {
        return transactionSiteID;
    }

    /**
     * Sets the value of the transactionSiteID property.
     * 
     * @param value
     *     allowed object is
     *     {@link SiteCodeType }
     *     
     */
    public void setTransactionSiteID(SiteCodeType value) {
        this.transactionSiteID = value;
    }

    /**
     * Gets the value of the platform property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionPlatformCodeType }
     *     
     */
    public TransactionPlatformCodeType getPlatform() {
        return platform;
    }

    /**
     * Sets the value of the platform property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionPlatformCodeType }
     *     
     */
    public void setPlatform(TransactionPlatformCodeType value) {
        this.platform = value;
    }

    /**
     * Gets the value of the cartID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCartID() {
        return cartID;
    }

    /**
     * Sets the value of the cartID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCartID(String value) {
        this.cartID = value;
    }

    /**
     * Gets the value of the sellerContactBuyerByEmail property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSellerContactBuyerByEmail() {
        return sellerContactBuyerByEmail;
    }

    /**
     * Sets the value of the sellerContactBuyerByEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSellerContactBuyerByEmail(Boolean value) {
        this.sellerContactBuyerByEmail = value;
    }

    /**
     * Gets the value of the payPalEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayPalEmailAddress() {
        return payPalEmailAddress;
    }

    /**
     * Sets the value of the payPalEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayPalEmailAddress(String value) {
        this.payPalEmailAddress = value;
    }

    /**
     * Gets the value of the paisaPayID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaisaPayID() {
        return paisaPayID;
    }

    /**
     * Sets the value of the paisaPayID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaisaPayID(String value) {
        this.paisaPayID = value;
    }

    /**
     * Gets the value of the buyerGuaranteePrice property.
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getBuyerGuaranteePrice() {
        return buyerGuaranteePrice;
    }

    /**
     * Sets the value of the buyerGuaranteePrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setBuyerGuaranteePrice(AmountType value) {
        this.buyerGuaranteePrice = value;
    }

    /**
     * Gets the value of the variation property.
     * 
     * @return
     *     possible object is
     *     {@link VariationType }
     *     
     */
    public VariationType getVariation() {
        return variation;
    }

    /**
     * Sets the value of the variation property.
     * 
     * @param value
     *     allowed object is
     *     {@link VariationType }
     *     
     */
    public void setVariation(VariationType value) {
        this.variation = value;
    }

    /**
     * Gets the value of the buyerCheckoutMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuyerCheckoutMessage() {
        return buyerCheckoutMessage;
    }

    /**
     * Sets the value of the buyerCheckoutMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuyerCheckoutMessage(String value) {
        this.buyerCheckoutMessage = value;
    }

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link Object }
     *     {@link Element }
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
     *     {@link Object }
     *     {@link Element }
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
     *     {@link Object }
     *     {@link Element }
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
     *     {@link Object }
     *     {@link Element }
     *     
     */
    public Object setAny(int idx, Object value) {
        return this.any.set(idx, value);
    }

}
