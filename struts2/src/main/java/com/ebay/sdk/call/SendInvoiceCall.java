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
 * Wrapper class of the SendInvoice call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>ItemID</code> - eBay's ID for the item from which the transaction was created.
 * If OrderID is not provided, both ItemID (or SKU) and
 * TransactionID must be provided to uniquely identify
 * a single transaction.
 * <br> <B>Input property:</B> <code>TransactionID</code> - ID for the transaction with which the item was sold.
 * If OrderID is not provided, both ItemID and
 * TransactionID must be provided.
 * For Chinese auctions, the value passed in the TransactionId
 * argument must be 0 or the call will fail with an error.
 * Call GetItemTransactions or GetSellerTransactions to
 * determine the correct transaction ID.
 * <br> <B>Input property:</B> <code>OrderID</code> - Unique ID for a multi-transaction order.
 * Either this OrderID must be specified or ItemID (or SKU) plus
 * TransactionID must be specified.
 * If OrderID is specified, ItemID, TransactionID, and SKU are
 * ignored if present in the same request. Changes to the
 * checkout status are applied to the specified order as a whole
 * (and thus to the child transactions associated with the order).
 * <br> <B>Input property:</B> <code>InternationalShippingServiceOptions</code> - If the buyer's shipping address is international, use this to offer up to
 * three shipping services, and omit all domestic ShippingServiceOptions. Any
 * shipping insurance cost specified should be the same for all services
 * offered.
 * <br><br>
 * Not applicable to invoices for digital listings.
 * <br> <B>Input property:</B> <code>ShippingServiceOptions</code> - If the buyer's shipping address is domestic, use this to offer up to
 * three shipping services, and omit all InternationalShippingServiceOptions. Any
 * shipping insurance cost specified should be the same for all services
 * offered.
 * <br><br>
 * Not applicable to invoices for digital listings.
 * <br> <B>Input property:</B> <code>SalesTax</code> - The details of the sales tax added to the invoice.
 * <br> <B>Input property:</B> <code>InsuranceOption</code> - Specifies whether insurance fee is required. An InsuranceOption value of
 * IncludedInShippingHandling cannot be used if the item will use calculated
 * shipping. Some shipping carriers automatically include shipping insurance
 * for qualifying items.<br>
 * <br>
 * Not applicable to invoices for digital listings.
 * <br> <B>Input property:</B> <code>InsuranceFee</code> - Insurance cost, as set by seller, if ShippingType = 1.
 * Specify if InsuranceOption is optional or required. Must
 * be greater than zero value if a value of Optional or Required is passed in
 * InsuranceOption. Value specified should be the total cost of insuring the
 * item.<br>
 * <br>
 * Not applicable to invoices for digital listings.
 * <br> <B>Input property:</B> <code>PaymentMethods</code> - Optional ability for the seller to add certain payment methods on
 * transaction if they were not originally specified on the item.  Valid
 * values are PayPal in the US, and MoneyXferAcceptedInCheckout (CIP+) in
 * Germany.
 * <br> <B>Input property:</B> <code>PayPalEmailAddress</code> - Provide PayPal email address if the payment method added is PayPal.
 * For digital listings, this must be an email address associated with
 * a verified PayPal Premier or Business account.
 * <br> <B>Input property:</B> <code>CheckoutInstructions</code> - Seller's Payment instructions/message to the buyer and return policy.
 * Default is null.
 * <br> <B>Input property:</B> <code>EmailCopyToSeller</code> - Specifies whether the seller wishes to be copied on the invoice email that
 * will be sent to the buyer. Default will be true.
 * <br> <B>Input property:</B> <code>CODCost</code> - Italy site (site ID 101) only.
 * Enables you to specify the cash-on-delivery (COD) cost, for COD shipping.
 * <br> <B>Input property:</B> <code>SKU</code> - The seller's unique identifier for an item that is being tracked
 * by this SKU. If OrderID is not provided, both SKU (or ItemID) and
 * TransactionID must be provided to uniquely identify
 * a single transaction.<br>
 * <br>
 * This field can only be used when you listed the item by using
 * AddFixedPriceItem or RelistFixedPriceItem,
 * and you set Item.InventoryTrackingMethod to SKU at
 * the time the item was listed. (These criteria are necessary to
 * uniquely identify the listing by a SKU.)<br>
 * <br>
 * <span class="tablenote"><b>Note:</b>
 * AddFixedPriceItem and RelistFixedPriceItem are defined in
 * the Merchant Data API (part of Large Merchant Services).
 * </span>
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class SendInvoiceCall extends com.ebay.sdk.ApiCall
{
  
  private String itemID = null;
  private String transactionID = null;
  private String orderID = null;
  private InternationalShippingServiceOptionsType[] internationalShippingServiceOptions = null;
  private ShippingServiceOptionsType[] shippingServiceOptions = null;
  private SalesTaxType salesTax = null;
  private InsuranceOptionCodeType insuranceOption = null;
  private AmountType insuranceFee = null;
  private BuyerPaymentMethodCodeType[] paymentMethods = null;
  private String payPalEmailAddress = null;
  private String checkoutInstructions = null;
  private Boolean emailCopyToSeller = null;
  private AmountType cODCost = null;
  private String sKU = null;
  private SendInvoiceRequestType sendInvoiceRequest=null;


  /**
   * Constructor.
   */
  public SendInvoiceCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public SendInvoiceCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Enables a seller to send an invoice to a buyer involved in a single transaction
   * or order.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The void object.
   */
  public void sendInvoice()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    SendInvoiceRequestType req;
    if (this.sendInvoiceRequest != null)
    {
      req = this.sendInvoiceRequest;
    } else {
      req = new SendInvoiceRequestType();
  
      if( this.sendInvoiceRequest == null )
        throw new SdkException("SendInvoiceRequest property is not set.");
  
      if (this.itemID != null)
        req.setItemID(this.itemID);
      if (this.transactionID != null)
        req.setTransactionID(this.transactionID);
      if (this.orderID != null)
        req.setOrderID(this.orderID);
      if (this.internationalShippingServiceOptions != null)
        req.setInternationalShippingServiceOptions(this.internationalShippingServiceOptions);
      if (this.shippingServiceOptions != null)
        req.setShippingServiceOptions(this.shippingServiceOptions);
      if (this.salesTax != null)
        req.setSalesTax(this.salesTax);
      if (this.insuranceOption != null)
        req.setInsuranceOption(this.insuranceOption);
      if (this.insuranceFee != null)
        req.setInsuranceFee(this.insuranceFee);
      if (this.paymentMethods != null)
        req.setPaymentMethods(this.paymentMethods);
      if (this.payPalEmailAddress != null)
        req.setPayPalEmailAddress(this.payPalEmailAddress);
      if (this.checkoutInstructions != null)
        req.setCheckoutInstructions(this.checkoutInstructions);
      if (this.emailCopyToSeller != null)
        req.setEmailCopyToSeller(this.emailCopyToSeller);
      if (this.cODCost != null)
        req.setCODCost(this.cODCost);
      if (this.sKU != null)
        req.setSKU(this.sKU);
  
    }

    SendInvoiceResponseType resp = (SendInvoiceResponseType) execute(req);


  }

  /**
   * Gets the SendInvoiceRequestType.sendInvoiceRequest.
   * @return SendInvoiceRequestType
   */
  public SendInvoiceRequestType getSendInvoiceRequest()
  {
    return this.sendInvoiceRequest;
  }


  /**
   * Sets the SendInvoiceRequestType.sendInvoiceRequest.
   * @param sendInvoiceRequest SendInvoiceRequestType
   */
  public void setSendInvoiceRequest(SendInvoiceRequestType sendInvoiceRequest)
  {
    this.sendInvoiceRequest = sendInvoiceRequest;
  }

  /**
   * Gets the SendInvoiceRequestType.cODCost.
   * @return AmountType
   */
  public AmountType getCODCost()
  {
    return this.cODCost;
  }

  /**
   * Sets the SendInvoiceRequestType.cODCost.
   * @param cODCost AmountType
   */
  public void setCODCost(AmountType cODCost)
  {
    this.cODCost = cODCost;
  }

  /**
   * Gets the SendInvoiceRequestType.checkoutInstructions.
   * @return String
   */
  public String getCheckoutInstructions()
  {
    return this.checkoutInstructions;
  }

  /**
   * Sets the SendInvoiceRequestType.checkoutInstructions.
   * @param checkoutInstructions String
   */
  public void setCheckoutInstructions(String checkoutInstructions)
  {
    this.checkoutInstructions = checkoutInstructions;
  }

  /**
   * Gets the SendInvoiceRequestType.emailCopyToSeller.
   * @return Boolean
   */
  public Boolean getEmailCopyToSeller()
  {
    return this.emailCopyToSeller;
  }

  /**
   * Sets the SendInvoiceRequestType.emailCopyToSeller.
   * @param emailCopyToSeller Boolean
   */
  public void setEmailCopyToSeller(Boolean emailCopyToSeller)
  {
    this.emailCopyToSeller = emailCopyToSeller;
  }

  /**
   * Gets the SendInvoiceRequestType.insuranceFee.
   * @return AmountType
   */
  public AmountType getInsuranceFee()
  {
    return this.insuranceFee;
  }

  /**
   * Sets the SendInvoiceRequestType.insuranceFee.
   * @param insuranceFee AmountType
   */
  public void setInsuranceFee(AmountType insuranceFee)
  {
    this.insuranceFee = insuranceFee;
  }

  /**
   * Gets the SendInvoiceRequestType.insuranceOption.
   * @return InsuranceOptionCodeType
   */
  public InsuranceOptionCodeType getInsuranceOption()
  {
    return this.insuranceOption;
  }

  /**
   * Sets the SendInvoiceRequestType.insuranceOption.
   * @param insuranceOption InsuranceOptionCodeType
   */
  public void setInsuranceOption(InsuranceOptionCodeType insuranceOption)
  {
    this.insuranceOption = insuranceOption;
  }

  /**
   * Gets the SendInvoiceRequestType.internationalShippingServiceOptions.
   * @return InternationalShippingServiceOptionsType[]
   */
  public InternationalShippingServiceOptionsType[] getInternationalShippingServiceOptions()
  {
    return this.internationalShippingServiceOptions;
  }

  /**
   * Sets the SendInvoiceRequestType.internationalShippingServiceOptions.
   * @param internationalShippingServiceOptions InternationalShippingServiceOptionsType[]
   */
  public void setInternationalShippingServiceOptions(InternationalShippingServiceOptionsType[] internationalShippingServiceOptions)
  {
    this.internationalShippingServiceOptions = internationalShippingServiceOptions;
  }

  /**
   * Gets the SendInvoiceRequestType.itemID.
   * @return String
   */
  public String getItemID()
  {
    return this.itemID;
  }

  /**
   * Sets the SendInvoiceRequestType.itemID.
   * @param itemID String
   */
  public void setItemID(String itemID)
  {
    this.itemID = itemID;
  }

  /**
   * Gets the SendInvoiceRequestType.orderID.
   * @return String
   */
  public String getOrderID()
  {
    return this.orderID;
  }

  /**
   * Sets the SendInvoiceRequestType.orderID.
   * @param orderID String
   */
  public void setOrderID(String orderID)
  {
    this.orderID = orderID;
  }

  /**
   * Gets the SendInvoiceRequestType.payPalEmailAddress.
   * @return String
   */
  public String getPayPalEmailAddress()
  {
    return this.payPalEmailAddress;
  }

  /**
   * Sets the SendInvoiceRequestType.payPalEmailAddress.
   * @param payPalEmailAddress String
   */
  public void setPayPalEmailAddress(String payPalEmailAddress)
  {
    this.payPalEmailAddress = payPalEmailAddress;
  }

  /**
   * Gets the SendInvoiceRequestType.paymentMethods.
   * @return BuyerPaymentMethodCodeType[]
   */
  public BuyerPaymentMethodCodeType[] getPaymentMethods()
  {
    return this.paymentMethods;
  }

  /**
   * Sets the SendInvoiceRequestType.paymentMethods.
   * @param paymentMethods BuyerPaymentMethodCodeType[]
   */
  public void setPaymentMethods(BuyerPaymentMethodCodeType[] paymentMethods)
  {
    this.paymentMethods = paymentMethods;
  }

  /**
   * Gets the SendInvoiceRequestType.sKU.
   * @return String
   */
  public String getSKU()
  {
    return this.sKU;
  }

  /**
   * Sets the SendInvoiceRequestType.sKU.
   * @param sKU String
   */
  public void setSKU(String sKU)
  {
    this.sKU = sKU;
  }

  /**
   * Gets the SendInvoiceRequestType.salesTax.
   * @return SalesTaxType
   */
  public SalesTaxType getSalesTax()
  {
    return this.salesTax;
  }

  /**
   * Sets the SendInvoiceRequestType.salesTax.
   * @param salesTax SalesTaxType
   */
  public void setSalesTax(SalesTaxType salesTax)
  {
    this.salesTax = salesTax;
  }

  /**
   * Gets the SendInvoiceRequestType.shippingServiceOptions.
   * @return ShippingServiceOptionsType[]
   */
  public ShippingServiceOptionsType[] getShippingServiceOptions()
  {
    return this.shippingServiceOptions;
  }

  /**
   * Sets the SendInvoiceRequestType.shippingServiceOptions.
   * @param shippingServiceOptions ShippingServiceOptionsType[]
   */
  public void setShippingServiceOptions(ShippingServiceOptionsType[] shippingServiceOptions)
  {
    this.shippingServiceOptions = shippingServiceOptions;
  }

  /**
   * Gets the SendInvoiceRequestType.transactionID.
   * @return String
   */
  public String getTransactionID()
  {
    return this.transactionID;
  }

  /**
   * Sets the SendInvoiceRequestType.transactionID.
   * @param transactionID String
   */
  public void setTransactionID(String transactionID)
  {
    this.transactionID = transactionID;
  }

}

