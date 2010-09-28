/*
Copyright (c) 2009 eBay, Inc.

This program is licensed under the terms of the eBay Common Development and 
Distribution License (CDDL) Version 1.0 (the "License") and any subsequent 
version thereof released by eBay.  The then-current version of the License 
can be found at https://www.codebase.ebay.com/Licenses.html and in the 
eBaySDKLicense file that is under the eBay SDK install directory.
*/

package com.ebay.sdk.call;

import java.lang.Boolean;
import java.lang.String;

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the ReviseCheckoutStatus call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>ItemID</code> - Unique ID for the item associated with the checkout. (See OrderID
 * if this item/transaction is part of an order.) A transaction is
 * uniquely identified by one of two means: (a) You provide a TransactionID
 * and ItemID. (This is the preferred approach.), OR, (b) You provide a
 * BuyerID and ItemID and eBay identifies only one transaction for that
 * combination (an error is returned if there are multiple transactions for
 * that combination). Note: If all three are provided (that is, BuyerID,
 * ItemID and TransactionID), BuyerID is ignored.
 * <br> <B>Input property:</B> <code>TransactionID</code> - Unique ID for the transaction associated with the checkout. (See OrderID
 * if this item/transaction is part of an order.) For Chinese
 * auctions, the value passed in the TransactionID argument must be 0 or the
 * call will fail with an error. For all other auction types, TransactionID
 * accepts the actual, nonzero transaction ID. Call GetItemTransactionsCall
 * or GetSellerTransactionsCall to determine the correct transaction ID. A
 * transaction is uniquely identified by one of two means: (a) You provide a
 * TransactionID and ItemID. (This is the preferred approach.), OR, (b) You
 * provide a BuyerID and ItemID and eBay identifies only one transaction for
 * that combination (an error is returned if there are multiple transactions
 * for that combination). Note: If all three are provided (that is, BuyerID,
 * ItemID and TransactionID), BuyerID is ignored.
 * <br> <B>Input property:</B> <code>OrderId</code> - Unique ID for a multi-item order. Never use this call to modify transactions
 * independent of an order. If specified, ItemID and
 * TransactionID are ignored if specified in the same call.
 * Changes to the checkout status are applied to the specified
 * order as a whole (and thus the child transactions
 * associated with the order).
 * <br> <B>Input property:</B> <code>AmountPaid</code> - The total amount paid by the buyer. For a US eBay Motors item,
 * AmountPaid is the total amount paid by the buyer for the deposit.
 * AmountPaid is optional if CheckoutStatus is Incomplete and required if it
 * is Complete.
 * <br> <B>Input property:</B> <code>PaymentMethodUsed</code> - Payment method used by the buyer.
 * Required if CheckoutStatus is Complete.
 * (Please note that only PayPal can set this value to PayPal.)
 * <br>
 * <span class="tablenote"><b>Note:</b>
 * Required or allowed payment methods vary by site and category. Refer to
 * <a href="http://developer.ebay.com/DevZone/XML/docs/WebHelp/wwhelp/wwhimpl/js/html/wwhelp.htm?context=eBay_XML_API&topic=PaymentMethodDifferences">
 * Payment Method Differences (PaymentMethod)</a> in the eBay
 * Trading API Guide for information to help you determine which payment
 * methods you are required or allowed to specify.
 * </span><br>
 * <br>
 * <br> <B>Input property:</B> <code>CheckoutStatus</code> - The current status of the checkout process for the transaction.
 * <br> <B>Input property:</B> <code>ShippingService</code> - The shipping service selected by the buyer from among the shipping
 * services offered by the seller (such as UPS Ground). For a list of valid
 * values that you can cache for future use, call GeteBayDetails with
 * DetailName set to ShippingServiceDetails.
 * <br> <B>Input property:</B> <code>ShippingIncludedInTax</code> - An indicator of whether shipping costs were included in the
 * taxable amount. For Third-Party Checkout applications.
 * <br> <B>Input property:</B> <code>CheckoutMethod</code> - Not supported.
 * <br> <B>Input property:</B> <code>InsuranceType</code> - The insurance option selected by the buyer.
 * <br> <B>Input property:</B> <code>PaymentStatus</code> - Marks the transaction status as Paid or awaiting payment
 * in My eBay. If you specify Paid, My eBay shows an icon
 * to indicate that the transaction status is Paid.
 * If Pending, it means the transaction is awaiting payment.
 * (Some applications may use Pending when the buyer has paid
 * but the funds have not yet been sent to the seller's financial
 * institution.)
 * <br> <B>Input property:</B> <code>AdjustmentAmount</code> - Discount or charge agreed to by the buyer and seller. A positive value
 * indicates that the amount is an extra charge being paid to the seller by
 * the buyer. A negative value indicates that the amount is a discount given
 * to the buyer by the seller.
 * <br> <B>Input property:</B> <code>ShippingAddress</code> - For internal use.
 * <br> <B>Input property:</B> <code>BuyerID</code> - eBay ID for the buyer in the transaction being revised. A transaction is
 * uniquely identified by one of two means: (a) You provide a TransactionID
 * and ItemID. (This is the preferred approach.), OR, (b) You provide a
 * BuyerID and ItemID and eBay identifies only one transaction for that
 * combination (an error is returned if there are multiple transactions for
 * that combination). Note: If all three are provided (that is, BuyerID,
 * ItemID and TransactionID), BuyerID is ignored.
 * <br> <B>Input property:</B> <code>ShippingInsuranceCost</code> - Amount of money paid for insurance. For Third Party Checkout
 * applications.
 * <br> <B>Input property:</B> <code>SalesTax</code> - Amount of money paid for sales tax. For Third-Party Checkout
 * applications.
 * <br> <B>Input property:</B> <code>ShippingCost</code> - Amount of money paid for shipping. For Third-party Checkout
 * applications.
 * <br> <B>Input property:</B> <code>EncryptedID</code> - Not supported.
 * <br> <B>Input property:</B> <code>ExternalTransaction</code> - <br> <B>Input property:</B> <code>MultipleSellerPaymentID</code> - Not supported.
 * <br> <B>Input property:</B> <code>CODCost</code> - Italy site (site ID 101) only.
 * Enables you to specify the cash-on-delivery (COD) cost, for COD shipping.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class ReviseCheckoutStatusCall extends com.ebay.sdk.ApiCall
{
  
  private String itemID = null;
  private String transactionID = null;
  private String orderId = null;
  private AmountType amountPaid = null;
  private BuyerPaymentMethodCodeType paymentMethodUsed = null;
  private CompleteStatusCodeType checkoutStatus = null;
  private String shippingService = null;
  private Boolean shippingIncludedInTax = null;
  private CheckoutMethodCodeType checkoutMethod = null;
  private InsuranceSelectedCodeType insuranceType = null;
  private RCSPaymentStatusCodeType paymentStatus = null;
  private AmountType adjustmentAmount = null;
  private AddressType shippingAddress = null;
  private String buyerID = null;
  private AmountType shippingInsuranceCost = null;
  private AmountType salesTax = null;
  private AmountType shippingCost = null;
  private String encryptedID = null;
  private ExternalTransactionType externalTransaction = null;
  private String multipleSellerPaymentID = null;
  private AmountType cODCost = null;


  /**
   * Constructor.
   */
  public ReviseCheckoutStatusCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public ReviseCheckoutStatusCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * A seller can use this call to update the payment details and status
   * of a transaction or order.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The ReviseCheckoutStatusResponseType object.
   */
  public ReviseCheckoutStatusResponseType reviseCheckoutStatus()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    ReviseCheckoutStatusRequestType req;
    req = new ReviseCheckoutStatusRequestType();
    if (this.itemID != null)
      req.setItemID(this.itemID);
    if (this.transactionID != null)
      req.setTransactionID(this.transactionID);
    if (this.orderId != null)
      req.setOrderID(this.orderId);
    if (this.amountPaid != null)
      req.setAmountPaid(this.amountPaid);
    if (this.paymentMethodUsed != null)
      req.setPaymentMethodUsed(this.paymentMethodUsed);
    if (this.checkoutStatus != null)
      req.setCheckoutStatus(this.checkoutStatus);
    if (this.shippingService != null)
      req.setShippingService(this.shippingService);
    if (this.shippingIncludedInTax != null)
      req.setShippingIncludedInTax(this.shippingIncludedInTax);
    if (this.checkoutMethod != null)
      req.setCheckoutMethod(this.checkoutMethod);
    if (this.insuranceType != null)
      req.setInsuranceType(this.insuranceType);
    if (this.paymentStatus != null)
      req.setPaymentStatus(this.paymentStatus);
    if (this.adjustmentAmount != null)
      req.setAdjustmentAmount(this.adjustmentAmount);
    if (this.shippingAddress != null)
      req.setShippingAddress(this.shippingAddress);
    if (this.buyerID != null)
      req.setBuyerID(this.buyerID);
    if (this.shippingInsuranceCost != null)
      req.setShippingInsuranceCost(this.shippingInsuranceCost);
    if (this.salesTax != null)
      req.setSalesTax(this.salesTax);
    if (this.shippingCost != null)
      req.setShippingCost(this.shippingCost);
    if (this.encryptedID != null)
      req.setEncryptedID(this.encryptedID);
    if (this.externalTransaction != null)
      req.setExternalTransaction(this.externalTransaction);
    if (this.multipleSellerPaymentID != null)
      req.setMultipleSellerPaymentID(this.multipleSellerPaymentID);
    if (this.cODCost != null)
      req.setCODCost(this.cODCost);

    ReviseCheckoutStatusResponseType resp = (ReviseCheckoutStatusResponseType) execute(req);

    return resp;
  }

  /**
   * Gets the ReviseCheckoutStatusRequestType.adjustmentAmount.
   * @return AmountType
   */
  public AmountType getAdjustmentAmount()
  {
    return this.adjustmentAmount;
  }

  /**
   * Sets the ReviseCheckoutStatusRequestType.adjustmentAmount.
   * @param adjustmentAmount AmountType
   */
  public void setAdjustmentAmount(AmountType adjustmentAmount)
  {
    this.adjustmentAmount = adjustmentAmount;
  }

  /**
   * Gets the ReviseCheckoutStatusRequestType.amountPaid.
   * @return AmountType
   */
  public AmountType getAmountPaid()
  {
    return this.amountPaid;
  }

  /**
   * Sets the ReviseCheckoutStatusRequestType.amountPaid.
   * @param amountPaid AmountType
   */
  public void setAmountPaid(AmountType amountPaid)
  {
    this.amountPaid = amountPaid;
  }

  /**
   * Gets the ReviseCheckoutStatusRequestType.buyerID.
   * @return String
   */
  public String getBuyerID()
  {
    return this.buyerID;
  }

  /**
   * Sets the ReviseCheckoutStatusRequestType.buyerID.
   * @param buyerID String
   */
  public void setBuyerID(String buyerID)
  {
    this.buyerID = buyerID;
  }

  /**
   * Gets the ReviseCheckoutStatusRequestType.cODCost.
   * @return AmountType
   */
  public AmountType getCODCost()
  {
    return this.cODCost;
  }

  /**
   * Sets the ReviseCheckoutStatusRequestType.cODCost.
   * @param cODCost AmountType
   */
  public void setCODCost(AmountType cODCost)
  {
    this.cODCost = cODCost;
  }

  /**
   * Gets the ReviseCheckoutStatusRequestType.checkoutMethod.
   * @return CheckoutMethodCodeType
   */
  public CheckoutMethodCodeType getCheckoutMethod()
  {
    return this.checkoutMethod;
  }

  /**
   * Sets the ReviseCheckoutStatusRequestType.checkoutMethod.
   * @param checkoutMethod CheckoutMethodCodeType
   */
  public void setCheckoutMethod(CheckoutMethodCodeType checkoutMethod)
  {
    this.checkoutMethod = checkoutMethod;
  }

  /**
   * Gets the ReviseCheckoutStatusRequestType.checkoutStatus.
   * @return CompleteStatusCodeType
   */
  public CompleteStatusCodeType getCheckoutStatus()
  {
    return this.checkoutStatus;
  }

  /**
   * Sets the ReviseCheckoutStatusRequestType.checkoutStatus.
   * @param checkoutStatus CompleteStatusCodeType
   */
  public void setCheckoutStatus(CompleteStatusCodeType checkoutStatus)
  {
    this.checkoutStatus = checkoutStatus;
  }

  /**
   * Gets the ReviseCheckoutStatusRequestType.encryptedID.
   * @return String
   */
  public String getEncryptedID()
  {
    return this.encryptedID;
  }

  /**
   * Sets the ReviseCheckoutStatusRequestType.encryptedID.
   * @param encryptedID String
   */
  public void setEncryptedID(String encryptedID)
  {
    this.encryptedID = encryptedID;
  }

  /**
   * Gets the ReviseCheckoutStatusRequestType.externalTransaction.
   * @return ExternalTransactionType
   */
  public ExternalTransactionType getExternalTransaction()
  {
    return this.externalTransaction;
  }

  /**
   * Sets the ReviseCheckoutStatusRequestType.externalTransaction.
   * @param externalTransaction ExternalTransactionType
   */
  public void setExternalTransaction(ExternalTransactionType externalTransaction)
  {
    this.externalTransaction = externalTransaction;
  }

  /**
   * Gets the ReviseCheckoutStatusRequestType.insuranceType.
   * @return InsuranceSelectedCodeType
   */
  public InsuranceSelectedCodeType getInsuranceType()
  {
    return this.insuranceType;
  }

  /**
   * Sets the ReviseCheckoutStatusRequestType.insuranceType.
   * @param insuranceType InsuranceSelectedCodeType
   */
  public void setInsuranceType(InsuranceSelectedCodeType insuranceType)
  {
    this.insuranceType = insuranceType;
  }

  /**
   * Gets the ReviseCheckoutStatusRequestType.itemID.
   * @return String
   */
  public String getItemID()
  {
    return this.itemID;
  }

  /**
   * Sets the ReviseCheckoutStatusRequestType.itemID.
   * @param itemID String
   */
  public void setItemID(String itemID)
  {
    this.itemID = itemID;
  }

  /**
   * Gets the ReviseCheckoutStatusRequestType.multipleSellerPaymentID.
   * @return String
   */
  public String getMultipleSellerPaymentID()
  {
    return this.multipleSellerPaymentID;
  }

  /**
   * Sets the ReviseCheckoutStatusRequestType.multipleSellerPaymentID.
   * @param multipleSellerPaymentID String
   */
  public void setMultipleSellerPaymentID(String multipleSellerPaymentID)
  {
    this.multipleSellerPaymentID = multipleSellerPaymentID;
  }

  /**
   * Gets the ReviseCheckoutStatusRequestType.orderId.
   * @return String
   */
  public String getOrderId()
  {
    return this.orderId;
  }

  /**
   * Sets the ReviseCheckoutStatusRequestType.orderId.
   * @param orderId String
   */
  public void setOrderId(String orderId)
  {
    this.orderId = orderId;
  }

  /**
   * Gets the ReviseCheckoutStatusRequestType.paymentMethodUsed.
   * @return BuyerPaymentMethodCodeType
   */
  public BuyerPaymentMethodCodeType getPaymentMethodUsed()
  {
    return this.paymentMethodUsed;
  }

  /**
   * Sets the ReviseCheckoutStatusRequestType.paymentMethodUsed.
   * @param paymentMethodUsed BuyerPaymentMethodCodeType
   */
  public void setPaymentMethodUsed(BuyerPaymentMethodCodeType paymentMethodUsed)
  {
    this.paymentMethodUsed = paymentMethodUsed;
  }

  /**
   * Gets the ReviseCheckoutStatusRequestType.paymentStatus.
   * @return RCSPaymentStatusCodeType
   */
  public RCSPaymentStatusCodeType getPaymentStatus()
  {
    return this.paymentStatus;
  }

  /**
   * Sets the ReviseCheckoutStatusRequestType.paymentStatus.
   * @param paymentStatus RCSPaymentStatusCodeType
   */
  public void setPaymentStatus(RCSPaymentStatusCodeType paymentStatus)
  {
    this.paymentStatus = paymentStatus;
  }

  /**
   * Gets the ReviseCheckoutStatusRequestType.salesTax.
   * @return AmountType
   */
  public AmountType getSalesTax()
  {
    return this.salesTax;
  }

  /**
   * Sets the ReviseCheckoutStatusRequestType.salesTax.
   * @param salesTax AmountType
   */
  public void setSalesTax(AmountType salesTax)
  {
    this.salesTax = salesTax;
  }

  /**
   * Gets the ReviseCheckoutStatusRequestType.shippingAddress.
   * @return AddressType
   */
  public AddressType getShippingAddress()
  {
    return this.shippingAddress;
  }

  /**
   * Sets the ReviseCheckoutStatusRequestType.shippingAddress.
   * @param shippingAddress AddressType
   */
  public void setShippingAddress(AddressType shippingAddress)
  {
    this.shippingAddress = shippingAddress;
  }

  /**
   * Gets the ReviseCheckoutStatusRequestType.shippingCost.
   * @return AmountType
   */
  public AmountType getShippingCost()
  {
    return this.shippingCost;
  }

  /**
   * Sets the ReviseCheckoutStatusRequestType.shippingCost.
   * @param shippingCost AmountType
   */
  public void setShippingCost(AmountType shippingCost)
  {
    this.shippingCost = shippingCost;
  }

  /**
   * Gets the ReviseCheckoutStatusRequestType.shippingIncludedInTax.
   * @return Boolean
   */
  public Boolean getShippingIncludedInTax()
  {
    return this.shippingIncludedInTax;
  }

  /**
   * Sets the ReviseCheckoutStatusRequestType.shippingIncludedInTax.
   * @param shippingIncludedInTax Boolean
   */
  public void setShippingIncludedInTax(Boolean shippingIncludedInTax)
  {
    this.shippingIncludedInTax = shippingIncludedInTax;
  }

  /**
   * Gets the ReviseCheckoutStatusRequestType.shippingInsuranceCost.
   * @return AmountType
   */
  public AmountType getShippingInsuranceCost()
  {
    return this.shippingInsuranceCost;
  }

  /**
   * Sets the ReviseCheckoutStatusRequestType.shippingInsuranceCost.
   * @param shippingInsuranceCost AmountType
   */
  public void setShippingInsuranceCost(AmountType shippingInsuranceCost)
  {
    this.shippingInsuranceCost = shippingInsuranceCost;
  }

  /**
   * Gets the ReviseCheckoutStatusRequestType.shippingService.
   * @return String
   */
  public String getShippingService()
  {
    return this.shippingService;
  }

  /**
   * Sets the ReviseCheckoutStatusRequestType.shippingService.
   * @param shippingService String
   */
  public void setShippingService(String shippingService)
  {
    this.shippingService = shippingService;
  }

  /**
   * Gets the ReviseCheckoutStatusRequestType.transactionID.
   * @return String
   */
  public String getTransactionID()
  {
    return this.transactionID;
  }

  /**
   * Sets the ReviseCheckoutStatusRequestType.transactionID.
   * @param transactionID String
   */
  public void setTransactionID(String transactionID)
  {
    this.transactionID = transactionID;
  }
/**
   * Backward compatible function - sets shipping service using a shipping service code type value.
   * @param shippingService ShippingServiceCodeType
   */
  public void setShippingService(com.ebay.soap.eBLBaseComponents.ShippingServiceCodeType shippingService) {
      this.shippingService = (shippingService == null? null: shippingService.value());
  }

}

