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
import java.lang.Integer;
import java.lang.String;
import java.util.Calendar;

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the GetItemTransactions call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>ItemID</code> - Unique item ID for a listing that has transactions
 * (purchases). When you use ItemID alone, eBay returns
 * all available transactions that are associated with the
 * listing. Also see other input filters (such as the
 * time filters) for ways to reduce the number of
 * transactions returned.<br>
 * <br>
 * If a listing has a Quantity greater than 1, and one
 * or more items were purchased separately, the call can
 * return multiple transactions. To uniquely
 * identify and retrieve a particular transaction, use
 * both ItemID and TransactionID together.<br>
 * <br>
 * <span class="tablenote"><b>Note:</b>
 * GetItemTransactions doesn't support SKU as an input because this
 * call requires an identifier that is unique across your active
 * and ended listings. Even when InventoryTrackingMethod is set to
 * SKU in a listing, the SKU is only unique across your active
 * listings (not your ended listings). To retrieve transactions
 * by SKU, use GetSellerTransactions instead.
 * </span>
 * <br> <B>Input property:</B> <code>ModifiedTimeFilter</code> - Helper wrapper to set GetItemTransactionsRequestType ModTimeFrom, ModTimeTo:
 * TimeFrom sets GetItemTransactionsRequestType.ModTimeFrom: 
 * Also see NumberOfDays, which (if used), takes precedence over ModTimeFrom and
 * ModTimeTo. If you prefer to use ModTimeFrom and ModTimeTo, specify the time range
 * within which retrieved transactions' statuses were modified. ModTimeFrom is the
 * earlier (older) date and ModTimeTo is the later (more recent) date. If you specify
 * ModTimeFrom, but do not specify the other end of the range, the time range is set
 * to 30 days. The time range between ModTimeFrom to ModTimeTo cannot be greater than
 * 30 days.
 * <br><br>
 * If you don't specify ModTimeFrom and ModTimeTo, NumberOfDays with a default of 30
 * days will be used.
 * TimeTo sets GetItemTransactionsRequestType.ModTimeTo: 
 * Also see NumberOfDays, which (if used), takes precedence over ModTimeFrom and
 * ModTimeTo. If you prefer to use ModTimeFrom and ModTimeTo, specify the time range
 * within which retrieved transactions' statuses were modified. ModTimeFrom is the
 * earlier (older) date and ModTimeTo is the later (more recent) date. If you specify
 * ModTimeTo, but do not specify the other end of the range, the time range is set to
 * 30 days. The time range between ModTimeFrom to ModTimeTo cannot be greater than 30
 * days.
 * <br><br>
 * If you don't specify ModTimeFrom and ModTimeTo, NumberOfDays with a default of 30
 * days will be used.
 * <br> <B>Input property:</B> <code>TransactionID</code> - Identifies one transaction for a listing. For Chinese auction listings
 * (single-item listings for which there can be only one transaction),
 * TransactionID is always 0, and for multi-quantity listings (for which
 * there can be multiple transactions), TransactionID has a non-0 value. If
 * TransactionID is provided, details are returned for the transaction,
 * regardless of any time range you might have specified (e.g. ModTimeFrom,
 * NumberOfDays). To determine the valid transaction IDs for a listing, you
 * typically need to have previously executed GetItemTransactionsCall or
 * GetSellerTransactionsCall and stored all the listing's transactions.
 * <br> <B>Input property:</B> <code>Pagination</code> - Child elements control pagination of the output. Use EntriesPerPage
 * property to control the number of transactions to return per call and
 * PageNumber property to specify the page of data to return.
 * <br> <B>Input property:</B> <code>IncludeFinalValueFee</code> - Indicates whether to include a Final Value Fee (FVF) in the response. For
 * most listing types, the Final Value Fee is returned in
 * Transaction.FinalValueFee. The final value fee is returned on a
 * transaction-by-transaction basis for FixedPriceItem and StoresFixedPrice
 * listing types. For all other listing types, the Final Value Fee is
 * returned when the listing status is Completed. This value is not returned
 * if the auction ended with Buy It Now.
 * <br><br>
 * For Dutch (multi-item) auctions that end with bids (not Buy It Now
 * purchases), the Final Value Fee is returned in
 * Item.SellingStatus.FinalValueFee. For Dutch Buy It Now listings, the Final
 * Value Fee is returned on a transaction-by-transaction basis.
 * <br><br>
 * <span class="tablenote"><strong>Note:</strong>
 * As of version 619, Dutch-style (multi-item) competitive-bid auctions are deprecated.
 * eBay throws an error if you submit a Dutch item listing with AddItem
 * or VerifyAddItem. If you use RelistItem to update a Dutch auction listing,
 * eBay generates a warning and resets the Quantity value to 1.
 * </span>
 * <br>
 * <br> <B>Input property:</B> <code>IncludeContainingOrder</code> - Whether to retrieve the order information. Default is false.
 * <br> <B>Input property:</B> <code>Platform</code> - Name of the eBay co-branded site upon which the transaction was made.
 * This will serve as a filter for the transactions to get emitted in the response.
 * <br> <B>Input property:</B> <code>NumberOfDays</code> - NumberOfDays enables you to specify the number of days' worth of new and modified
 * transactions that you want to retrieve. The call response contains the
 * transactions whose status was modified within the specified number of days since
 * the API call was made. NumberOfDays is often preferable to using the ModTimeFrom
 * and ModTimeTo filters because you only need to specify one value. If you use
 * NumberOfDays, then ModTimeFrom and ModTimeTo are ignored. For this field, one day
 * is defined as 24 hours.
 * <br><br>
 * If you don't specify ModTimeFrom and ModTimeTo, NumberOfDays with a default of 30
 * will be used.
 * <br> <B>Input property:</B> <code>IncludeVariations</code> - If true, all variations defined for the item are returned in the
 * Item-level details, including variations that have no
 * transactions. (If false, the applicable variations are still
 * returned in the Transaction-level details.) This information is
 * intended to help sellers to reconcile their local inventory with
 * eBay's records while processing transactions (without requiring
 * a separate call to GetItem).
 * <br> <B>Output property:</B> <code>PaginationResult</code> - Contains the total number of pages (TotalNumberOfPages) and the total number
 * of entries (TotalNumberOfEntries) that could be returned given repeated calls
 * that use the same selection criteria as the call that returned this response.
 * <br> <B>Output property:</B> <code>HasMoreTransactions</code> - Indicates whether there are additional transactions to retrieve.
 * That is, indicates whether more pages of data are available to be
 * returned, given the filters that were specified in the request.
 * Returns false for the last page of data.
 * <br> <B>Output property:</B> <code>ReturnedTransactionsPerPage</code> - Number of transactions returned per page (per call). May be a higher value
 * than ReturnedTransactionCountActual if the page returned is the last page
 * and more than one page of data exists.
 * <br> <B>Output property:</B> <code>ReturnedPageNumber</code> - Page number for the page of transactions the response returned.
 * <br> <B>Output property:</B> <code>ReturnedTransactionCountActual</code> - Number of transactions retrieved in the current page of results just returned.
 * May be a lower value than TransactionsPerPage if the page returned is the last
 * page and more than one page of data exists.
 * <br> <B>Output property:</B> <code>Item</code> - Item object that spawned the transaction. It is a purchase from this item's listing
 * that the transaction represents.
 * <br> <B>Output property:</B> <code>ReturnedTransactions</code> - List of Transaction objects representing the transactions resulting
 * from the listing. Each Transaction object contains the data for one purchase
 * (of one or more items in the same listing). The Transaction.Item field is not
 * returned because the Item object is returned at the root level of the response.
 * See the reference guide for more information about the fields that are returned.
 * <br> <B>Output property:</B> <code>PayPalPreferred</code> - Indicates whether the item's seller has the preference enabled that shows
 * that the seller prefers PayPal as the method of payment for an item. This
 * preference is indicated on an item's View Item page and is intended to
 * influence a buyer to use PayPal
 * to pay for the item.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class GetItemTransactionsCall extends com.ebay.sdk.ApiCall
{
  
  private String itemID = null;
  private TimeFilter modifiedTimeFilter = null;
  private String transactionID = null;
  private PaginationType pagination = null;
  private Boolean includeFinalValueFee = null;
  private Boolean includeContainingOrder = null;
  private TransactionPlatformCodeType platform = null;
  private Integer numberOfDays = null;
  private Boolean includeVariations = null;
  private PaginationResultType paginationResult=null;
  private boolean hasMoreTransactions=false;
  private Integer returnedTransactionsPerPage=null;
  private Integer returnedPageNumber=null;
  private int returnedTransactionCountActual=0;
  private ItemType item=null;
  private TransactionType[] returnedTransactions=null;
  private boolean payPalPreferred=false;


  /**
   * Constructor.
   */
  public GetItemTransactionsCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public GetItemTransactionsCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Retrieves transaction information for a specified itemID. The call returns zero, one,
   * or multiple transactions, depending on the number of items sold from the listing.
   * <br><br>
   * You can retrieve transaction data for a specific time range or number of days. If you don't specify a
   * a range or number of days, transaction data will be returned for the past 30 days.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The TransactionType[] object.
   */
  public TransactionType[] getItemTransactions()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GetItemTransactionsRequestType req;
    req = new GetItemTransactionsRequestType();

    if( this.itemID == null )
      throw new SdkException("ItemID property is not set.");
    if( this.modifiedTimeFilter == null )
      throw new SdkException("ModifiedTimeFilter property is not set.");

    req.setDetailLevel(this.getDetailLevel());
    if (this.itemID != null)
      req.setItemID(this.itemID);
    if (this.modifiedTimeFilter != null)
    {
      req.setModTimeFrom(this.modifiedTimeFilter.getTimeFrom());
      req.setModTimeTo(this.modifiedTimeFilter.getTimeTo());
    }
    if (this.transactionID != null)
      req.setTransactionID(this.transactionID);
    if (this.pagination != null)
      req.setPagination(this.pagination);
    if (this.includeFinalValueFee != null)
      req.setIncludeFinalValueFee(this.includeFinalValueFee);
    if (this.includeContainingOrder != null)
      req.setIncludeContainingOrder(this.includeContainingOrder);
    if (this.platform != null)
      req.setPlatform(this.platform);
    if (this.numberOfDays != null)
      req.setNumberOfDays(this.numberOfDays);
    if (this.includeVariations != null)
      req.setIncludeVariations(this.includeVariations);

    GetItemTransactionsResponseType resp = (GetItemTransactionsResponseType) execute(req);

    this.paginationResult = resp.getPaginationResult();
    this.hasMoreTransactions = (resp.isHasMoreTransactions() == null? false: resp.isHasMoreTransactions().booleanValue());
    this.returnedTransactionsPerPage = resp.getTransactionsPerPage();
    this.returnedPageNumber = resp.getPageNumber();
    this.returnedTransactionCountActual = (resp.getReturnedTransactionCountActual() == null? 0: resp.getReturnedTransactionCountActual().intValue());
    this.item = resp.getItem();
    this.returnedTransactions = (resp.getTransactionArray() == null? null: resp.getTransactionArray().getTransaction());
    this.payPalPreferred = (resp.isPayPalPreferred() == null? false: resp.isPayPalPreferred().booleanValue());
    return this.getReturnedTransactions();
  }

  /**
   * Gets the GetItemTransactionsRequestType.includeContainingOrder.
   * @return Boolean
   */
  public Boolean getIncludeContainingOrder()
  {
    return this.includeContainingOrder;
  }

  /**
   * Sets the GetItemTransactionsRequestType.includeContainingOrder.
   * @param includeContainingOrder Boolean
   */
  public void setIncludeContainingOrder(Boolean includeContainingOrder)
  {
    this.includeContainingOrder = includeContainingOrder;
  }

  /**
   * Gets the GetItemTransactionsRequestType.includeFinalValueFee.
   * @return Boolean
   */
  public Boolean getIncludeFinalValueFee()
  {
    return this.includeFinalValueFee;
  }

  /**
   * Sets the GetItemTransactionsRequestType.includeFinalValueFee.
   * @param includeFinalValueFee Boolean
   */
  public void setIncludeFinalValueFee(Boolean includeFinalValueFee)
  {
    this.includeFinalValueFee = includeFinalValueFee;
  }

  /**
   * Gets the GetItemTransactionsRequestType.includeVariations.
   * @return Boolean
   */
  public Boolean getIncludeVariations()
  {
    return this.includeVariations;
  }

  /**
   * Sets the GetItemTransactionsRequestType.includeVariations.
   * @param includeVariations Boolean
   */
  public void setIncludeVariations(Boolean includeVariations)
  {
    this.includeVariations = includeVariations;
  }

  /**
   * Gets the GetItemTransactionsRequestType.itemID.
   * @return String
   */
  public String getItemID()
  {
    return this.itemID;
  }

  /**
   * Sets the GetItemTransactionsRequestType.itemID.
   * @param itemID String
   */
  public void setItemID(String itemID)
  {
    this.itemID = itemID;
  }

  /**
   * Gets the GetItemTransactionsRequestType.modifiedTimeFilter.
   * @return TimeFilter
   */
  public TimeFilter getModifiedTimeFilter()
  {
    return this.modifiedTimeFilter;
  }

  /**
   * Sets the GetItemTransactionsRequestType.modifiedTimeFilter.
   * @param modifiedTimeFilter TimeFilter
   */
  public void setModifiedTimeFilter(TimeFilter modifiedTimeFilter)
  {
    this.modifiedTimeFilter = modifiedTimeFilter;
  }

  /**
   * Gets the GetItemTransactionsRequestType.numberOfDays.
   * @return Integer
   */
  public Integer getNumberOfDays()
  {
    return this.numberOfDays;
  }

  /**
   * Sets the GetItemTransactionsRequestType.numberOfDays.
   * @param numberOfDays Integer
   */
  public void setNumberOfDays(Integer numberOfDays)
  {
    this.numberOfDays = numberOfDays;
  }

  /**
   * Gets the GetItemTransactionsRequestType.pagination.
   * @return PaginationType
   */
  public PaginationType getPagination()
  {
    return this.pagination;
  }

  /**
   * Sets the GetItemTransactionsRequestType.pagination.
   * @param pagination PaginationType
   */
  public void setPagination(PaginationType pagination)
  {
    this.pagination = pagination;
  }

  /**
   * Gets the GetItemTransactionsRequestType.platform.
   * @return TransactionPlatformCodeType
   */
  public TransactionPlatformCodeType getPlatform()
  {
    return this.platform;
  }

  /**
   * Sets the GetItemTransactionsRequestType.platform.
   * @param platform TransactionPlatformCodeType
   */
  public void setPlatform(TransactionPlatformCodeType platform)
  {
    this.platform = platform;
  }

  /**
   * Gets the GetItemTransactionsRequestType.transactionID.
   * @return String
   */
  public String getTransactionID()
  {
    return this.transactionID;
  }

  /**
   * Sets the GetItemTransactionsRequestType.transactionID.
   * @param transactionID String
   */
  public void setTransactionID(String transactionID)
  {
    this.transactionID = transactionID;
  }
/**
   * Get all item transactions in specified date range.
   * It handles pagination internally and ignores the Pagination property.
   * GetItemTransactionsCall.ItemID and
   * GetItemTransactionsCall.ModifiedTimeFilter will be ignored.
   * @param modifiedTimeFilter TimeFilter
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return TransactionType[]
   */
  public TransactionType[] getEntireItemTransactions()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    if( this.itemID == null )
      throw new SdkException("ItemID is not set.");
    if( this.modifiedTimeFilter == null )
      throw new SdkException("ModifiedTimeFilter is not set.");
    return getEntireItemTransactions();
  }
/**
   * Execute the API call. GetItemTransactionsCall.ItemID and
   * GetItemTransactionsCall.ModifiedTimeFilter will be ignored.
   * @param itemID String Unique ID of item that you are retrieving transactions for.
   * @param modifiedTimeFilter TimeFilter Range of transactions that you are retrieving.
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return TransactionType[]
   */
  public TransactionType[] getItemTransactions(String itemID, TimeFilter modifiedTimeFilter)
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GetItemTransactionsRequestType req = new GetItemTransactionsRequestType();

    req.setItemID(itemID);
    req.setModTimeFrom(modifiedTimeFilter.getTimeFrom());
	req.setModTimeTo(modifiedTimeFilter.getTimeTo());
	if (null != includeContainingOrder)
		req.setIncludeContainingOrder(includeContainingOrder);
	if (null != includeFinalValueFee)
		req.setIncludeFinalValueFee(includeFinalValueFee);

    if( this.pagination != null )
      req.setPagination(this.pagination);

    if( this.transactionID != null && this.transactionID.length() > 0 )
      req.setTransactionID(this.transactionID);

    // Call eBay
    GetItemTransactionsResponseType resp = (GetItemTransactionsResponseType)this.execute(req);

    this.returnedTransactions = resp.getTransactionArray() == null ? null :
        resp.getTransactionArray().getTransaction();
    this.paginationResult = resp.getPaginationResult();
    this.item = resp.getItem();

    if( resp.isHasMoreTransactions() != null )
      this.hasMoreTransactions = resp.isHasMoreTransactions().booleanValue();
	if( resp.isPayPalPreferred() != null )
	  this.payPalPreferred = resp.isPayPalPreferred().booleanValue();
    if( resp.getReturnedTransactionCountActual() != null )
      this.returnedTransactionCountActual = resp.getReturnedTransactionCountActual().intValue();

    // Save EntriesPerPage and PageNumber back to the input PaginationType.
    if( this.pagination == null )
      this.pagination = new PaginationType();
    if( resp.getTransactionsPerPage() != null )
      this.pagination.setEntriesPerPage(resp.getTransactionsPerPage());
    if( resp.getPageNumber() != null )
      this.pagination.setPageNumber(resp.getPageNumber());

    return this.returnedTransactions;
  }


/**
   * Backward compatible function - do not use
   */
  public void setIncludeFinalValueFe(Boolean includeFinalValueFee) {
	this.includeFinalValueFee = includeFinalValueFee;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetItemTransactionsResponseType.hasMoreTransactions.
   * 
   * @return boolean
   */
  public boolean getHasMoreTransactions()
  {
    return this.hasMoreTransactions;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetItemTransactionsResponseType.item.
   * 
   * @return ItemType
   */
  public ItemType getItem()
  {
    return this.item;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetItemTransactionsResponseType.paginationResult.
   * 
   * @return PaginationResultType
   */
  public PaginationResultType getPaginationResult()
  {
    return this.paginationResult;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetItemTransactionsResponseType.payPalPreferred.
   * 
   * @return boolean
   */
  public boolean getPayPalPreferred()
  {
    return this.payPalPreferred;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetItemTransactionsResponseType.returnedPageNumber.
   * 
   * @return Integer
   */
  public Integer getReturnedPageNumber()
  {
    return this.returnedPageNumber;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetItemTransactionsResponseType.returnedTransactionCountActual.
   * 
   * @return int
   */
  public int getReturnedTransactionCountActual()
  {
    return this.returnedTransactionCountActual;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetItemTransactionsResponseType.returnedTransactions.
   * 
   * @return TransactionType[]
   */
  public TransactionType[] getReturnedTransactions()
  {
    return this.returnedTransactions;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetItemTransactionsResponseType.returnedTransactionsPerPage.
   * 
   * @return Integer
   */
  public Integer getReturnedTransactionsPerPage()
  {
    return this.returnedTransactionsPerPage;
  }

}

