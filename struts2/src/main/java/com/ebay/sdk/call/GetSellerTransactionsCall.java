/*
Copyright (c) 2009 eBay, Inc.

This program is licensed under the terms of the eBay Common Development and 
Distribution License (CDDL) Version 1.0 (the "License") and any subsequent 
version thereof released by eBay.  The then-current version of the License 
can be found at https://www.codebase.ebay.com/Licenses.html and in the 
eBaySDKLicense file that is under the eBay SDK install directory.
*/

package com.ebay.sdk.call;

import java.util.List;
import java.util.ArrayList;


import java.lang.Boolean;
import java.lang.Integer;
import java.util.Calendar;

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the GetSellerTransactions call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>ModifiedTimeFilter</code> - Helper wrapper to set GetSellerTransactionsRequestType ModTimeFrom, ModTimeTo:
 * TimeFrom sets GetSellerTransactionsRequestType.ModTimeFrom: 
 * Also see NumberOfDays, which (if used), takes precedence over ModTimeFrom and
 * ModTimeTo. If you prefer to use ModTimeFrom and ModTimeTo, specify the time range
 * within which retrieved transactions' statuses were modified. ModTimeFrom is the
 * earlier (older) date and ModTimeTo is the later (more recent) date. If you specify
 * ModTimeFrom, but do not specify the other end of the range, the time range is set
 * to 30 days. The time range between ModTimeFrom to ModTimeTo cannot be greater than
 * 30 days.
 * TimeTo sets GetSellerTransactionsRequestType.ModTimeTo: 
 * Also see NumberOfDays, which (if used), takes precedence over ModTimeFrom and
 * ModTimeTo. If you prefer to use ModTimeFrom and ModTimeTo, specify the time range
 * within which retrieved transactions' statuses were modified. ModTimeFrom is the
 * earlier (older) date and ModTimeTo is the later (more recent) date. If you specify
 * ModTimeTo, but do not specify the other end of the range, the time range is set to
 * 30 days. The time range between ModTimeFrom to ModTimeTo cannot be greater than 30
 * days.
 * <br> <B>Input property:</B> <code>Pagination</code> - Child elements control pagination of the output. Use EntriesPerPage property to
 * control the number of transactions to return per call and PageNumber property to
 * specify the page of data to return.
 * <br> <B>Input property:</B> <code>IncludeFinalValueFee</code> - Indicates whether to include Final Value Fee (FVF) in the response. For most
 * listing types, the Final Value Fee is returned in Transaction.FinalValueFee.
 * The Final Value Fee is returned on a transaction-by-transaction basis for
 * FixedPriceItem and StoresFixedPrice listing types. For all other listing
 * types, the Final Value Fee is returned when the listing status is Completed.
 * This value is not returned if the auction ended with Buy It Now.
 * <br><br>
 * For Dutch (multi-item) auctions that end with bids (not Buy It Now
 * purchases), the Final Value Fee is returned in
 * Item.SellingStatus.FinalValueFee. For Dutch Buy It Now listings, the Final
 * Value Fee is returned on a transaction-by-transaction basis.
 * <br><br>
 * <span class="tablenote"><strong>Note:</strong>
 * As of version 619, Dutch-style (multi-item) competitive-bid auctions are
 * deprecated. eBay throws an error if you submit a Dutch item listing with AddItem
 * or VerifyAddItem. If you use RelistItem to update a Dutch auction listing, eBay
 * generates a warning and resets the Quantity value to 1.
 * </span>
 * <br>
 * <br> <B>Input property:</B> <code>IncludeContainingOrder</code> - Whether to retrieve the order information. Default is false.
 * <br> <B>Input property:</B> <code>SKUArray</code> - Container for a set of SKUs.
 * Filters (reduces) the response to only include transactions
 * for listings that include any of the specified SKUs.
 * If multiple listings include the same SKU, transactions for
 * all of them are returned (assuming they also match the other
 * criteria in the GetSellerTransactions request).<br>
 * <br>
 * You can combine SKUArray with InventoryTrackingMethod.
 * For example, if you also pass in InventoryTrackingMethod=SKU,
 * the response only includes transactions for listings that
 * include InventoryTrackingMethod=SKU and one of the
 * requested SKUs.
 * <br> <B>Input property:</B> <code>Platform</code> - Name of the eBay co-branded site upon which the transaction was made.
 * This will serve as a filter for the transactions to get emitted in the response.
 * <br> <B>Input property:</B> <code>NumberOfDays</code> - NumberOfDays enables you to specify the number of days' worth of new and modified
 * transactions that you want to retrieve. The call response contains the
 * transactions whose status was modified within the specified number of days since
 * the API call was made. NumberOfDays is often preferable to using the ModTimeFrom
 * and ModTimeTo filters because you only need to specify one value. If you use
 * NumberOfDays, then ModTimeFrom and ModTimeTo are ignored. For this field, one day
 * is defined as 24 hours.
 * <br> <B>Input property:</B> <code>InventoryTrackingMethod</code> - Filters the response to only include transactions for listings
 * that match this InventoryTrackingMethod setting. <br>
 * <br>
 * For example, if you set this to SKU, the call returns
 * transactions for your listings that are tracked by SKU.
 * If you set this to ItemID, the call omits transactions
 * for your listings that are tracked by SKU.
 * If you don't pass this in, the call returns all transactions,
 * regardless of whether they are tracked by SKU or ItemID.<br>
 * <br>
 * <span class="tablenote"><b>Note:</b>
 * To specify InventoryTrackingMethod when you create a listing,
 * use AddFixedPriceItem or RelistFixedPriceItem.
 * AddFixedPriceItem and RelistFixedPriceItem are defined in
 * the Merchant Data API (part of Large Merchant Services).
 * </span>
 * <br>
 * <br>
 * You can combine SKUArray with InventoryTrackingMethod.
 * For example, if you set this to SKU and you also pass in
 * SKUArray, the response only includes transactions for listings
 * that include InventoryTrackingMethod=SKU and one of the
 * requested SKUs.
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
 * <br> <B>Output property:</B> <code>Seller</code> - Contains information about the seller whose transactions are being returned.
 * See the reference guide for information about the fields of the Seller object
 * that are returned.
 * <br> <B>Output property:</B> <code>ReturnedTransactions</code> - List of Transaction objects representing the seller's recent sales.
 * Each Transaction object contains the data for one purchase
 * (of one or more items in the same listing).
 * See the reference guide for more information about the fields that are returned
 * for each transaction.
 * <br> <B>Output property:</B> <code>PayPalPreferred</code> - Indicates whether the seller has the preference enabled that shows that the seller
 * prefers PayPal as the method of payment for an item. This preference is indicated on
 * an item's View Item page and is intended to influence a buyer to use PayPal
 * to pay for the item.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class GetSellerTransactionsCall extends com.ebay.sdk.ApiCall
{
  
  private TimeFilter modifiedTimeFilter = null;
  private PaginationType pagination = null;
  private Boolean includeFinalValueFee = null;
  private Boolean includeContainingOrder = null;
  private SKUArrayType sKUArray = null;
  private TransactionPlatformCodeType platform = null;
  private Integer numberOfDays = null;
  private InventoryTrackingMethodCodeType inventoryTrackingMethod = null;
  private PaginationResultType paginationResult=null;
  private boolean hasMoreTransactions=false;
  private Integer returnedTransactionsPerPage=null;
  private Integer returnedPageNumber=null;
  private int returnedTransactionCountActual=0;
  private UserType seller=null;
  private TransactionType[] returnedTransactions=null;
  private boolean payPalPreferred=false;


  /**
   * Constructor.
   */
  public GetSellerTransactionsCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public GetSellerTransactionsCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Retrieves transaction information for the user for which the call is made (and
   * not for any other user), where a transaction is the information about the sale
   * of one or more items by one buyer from a single listing. (To retrieve
   * transactions for another seller's listing, use GetItemTransactions.)
   * 
   * <br>
   * @throws ApiException
   * @return The TransactionType[] object.
   */
  public TransactionType[] getSellerTransactions()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GetSellerTransactionsRequestType req;
    req = new GetSellerTransactionsRequestType();

    if( this.modifiedTimeFilter == null )
      throw new SdkException("ModifiedTimeFilter property is not set.");

    req.setDetailLevel(this.getDetailLevel());
    if (this.modifiedTimeFilter != null)
    {
      req.setModTimeFrom(this.modifiedTimeFilter.getTimeFrom());
      req.setModTimeTo(this.modifiedTimeFilter.getTimeTo());
    }
    if (this.pagination != null)
      req.setPagination(this.pagination);
    if (this.includeFinalValueFee != null)
      req.setIncludeFinalValueFee(this.includeFinalValueFee);
    if (this.includeContainingOrder != null)
      req.setIncludeContainingOrder(this.includeContainingOrder);
    if (this.sKUArray != null)
      req.setSKUArray(this.sKUArray);
    if (this.platform != null)
      req.setPlatform(this.platform);
    if (this.inventoryTrackingMethod != null)
      req.setInventoryTrackingMethod(this.inventoryTrackingMethod);

    GetSellerTransactionsResponseType resp = (GetSellerTransactionsResponseType) execute(req);

    this.paginationResult = resp.getPaginationResult();
    this.hasMoreTransactions = (resp.isHasMoreTransactions() == null? false: resp.isHasMoreTransactions().booleanValue());
    this.returnedTransactionsPerPage = resp.getTransactionsPerPage();
    this.returnedPageNumber = resp.getPageNumber();
    this.returnedTransactionCountActual = (resp.getReturnedTransactionCountActual() == null? 0: resp.getReturnedTransactionCountActual().intValue());
    this.seller = resp.getSeller();
    this.returnedTransactions = (resp.getTransactionArray() == null? null: resp.getTransactionArray().getTransaction());
    this.payPalPreferred = (resp.isPayPalPreferred() == null? false: resp.isPayPalPreferred().booleanValue());
    return this.getReturnedTransactions();
  }

  /**
   * Gets the GetSellerTransactionsRequestType.includeContainingOrder.
   * @return Boolean
   */
  public Boolean getIncludeContainingOrder()
  {
    return this.includeContainingOrder;
  }

  /**
   * Sets the GetSellerTransactionsRequestType.includeContainingOrder.
   * @param includeContainingOrder Boolean
   */
  public void setIncludeContainingOrder(Boolean includeContainingOrder)
  {
    this.includeContainingOrder = includeContainingOrder;
  }

  /**
   * Gets the GetSellerTransactionsRequestType.includeFinalValueFee.
   * @return Boolean
   */
  public Boolean getIncludeFinalValueFee()
  {
    return this.includeFinalValueFee;
  }

  /**
   * Sets the GetSellerTransactionsRequestType.includeFinalValueFee.
   * @param includeFinalValueFee Boolean
   */
  public void setIncludeFinalValueFee(Boolean includeFinalValueFee)
  {
    this.includeFinalValueFee = includeFinalValueFee;
  }

  /**
   * Gets the GetSellerTransactionsRequestType.inventoryTrackingMethod.
   * @return InventoryTrackingMethodCodeType
   */
  public InventoryTrackingMethodCodeType getInventoryTrackingMethod()
  {
    return this.inventoryTrackingMethod;
  }

  /**
   * Sets the GetSellerTransactionsRequestType.inventoryTrackingMethod.
   * @param inventoryTrackingMethod InventoryTrackingMethodCodeType
   */
  public void setInventoryTrackingMethod(InventoryTrackingMethodCodeType inventoryTrackingMethod)
  {
    this.inventoryTrackingMethod = inventoryTrackingMethod;
  }

  /**
   * Gets the GetSellerTransactionsRequestType.modifiedTimeFilter.
   * @return TimeFilter
   */
  public TimeFilter getModifiedTimeFilter()
  {
    return this.modifiedTimeFilter;
  }

  /**
   * Sets the GetSellerTransactionsRequestType.modifiedTimeFilter.
   * @param modifiedTimeFilter TimeFilter
   */
  public void setModifiedTimeFilter(TimeFilter modifiedTimeFilter)
  {
    this.modifiedTimeFilter = modifiedTimeFilter;
  }

  /**
   * Gets the GetSellerTransactionsRequestType.numberOfDays.
   * @return Integer
   */
  public Integer getNumberOfDays()
  {
    return this.numberOfDays;
  }

  /**
   * Sets the GetSellerTransactionsRequestType.numberOfDays.
   * @param numberOfDays Integer
   */
  public void setNumberOfDays(Integer numberOfDays)
  {
    this.numberOfDays = numberOfDays;
  }

  /**
   * Gets the GetSellerTransactionsRequestType.pagination.
   * @return PaginationType
   */
  public PaginationType getPagination()
  {
    return this.pagination;
  }

  /**
   * Sets the GetSellerTransactionsRequestType.pagination.
   * @param pagination PaginationType
   */
  public void setPagination(PaginationType pagination)
  {
    this.pagination = pagination;
  }

  /**
   * Gets the GetSellerTransactionsRequestType.platform.
   * @return TransactionPlatformCodeType
   */
  public TransactionPlatformCodeType getPlatform()
  {
    return this.platform;
  }

  /**
   * Sets the GetSellerTransactionsRequestType.platform.
   * @param platform TransactionPlatformCodeType
   */
  public void setPlatform(TransactionPlatformCodeType platform)
  {
    this.platform = platform;
  }

  /**
   * Gets the GetSellerTransactionsRequestType.sKUArray.
   * @return SKUArrayType
   */
  public SKUArrayType getSKUArray()
  {
    return this.sKUArray;
  }

  /**
   * Sets the GetSellerTransactionsRequestType.sKUArray.
   * @param sKUArray SKUArrayType
   */
  public void setSKUArray(SKUArrayType sKUArray)
  {
    this.sKUArray = sKUArray;
  }
/**
   * Get all seller transactions in specified date range.
   * It handles pagination internally and ignores the Pagination property.
   * GetSellerTransactionsCall.TimeFilter will be ignored.
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return TransactionType[]
   */
  public TransactionType[] getEntireSellerTransactions()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    if( this.modifiedTimeFilter == null )
      throw new SdkException("TimeFilter is not set.");
    return getEntireSellerTransactions(this.modifiedTimeFilter);
  }
/**
   * Get all seller transactions in specified date range.
   * It handles pagination internally and ignores the Pagination property.
   *
   * @param modifiedTimeFilter TimeFilter
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return TransactionType[]
   */
  public TransactionType[] getEntireSellerTransactions(TimeFilter modifiedTimeFilter)
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GetSellerTransactionsRequestType req = new GetSellerTransactionsRequestType();

    // Modified time filter is mandatory.
    req.setModTimeFrom(modifiedTimeFilter.getTimeFrom());
    req.setModTimeTo(modifiedTimeFilter.getTimeTo());

    int pageNum = 1;
    PaginationType pgn = new PaginationType();
    pgn.setEntriesPerPage(new Integer(100));
    pgn.setPageNumber(new Integer(pageNum++));

    req.setPagination(pgn);
    
    if (this.includeFinalValueFee != null)
        req.setIncludeFinalValueFee(this.includeFinalValueFee);
    if (this.includeContainingOrder != null)
        req.setIncludeContainingOrder(this.includeContainingOrder);
    if (this.sKUArray != null)
        req.setSKUArray(this.sKUArray);
    if (this.platform != null)
        req.setPlatform(this.platform);
    if (this.inventoryTrackingMethod != null)
        req.setInventoryTrackingMethod(this.inventoryTrackingMethod);

    ArrayList transList = new ArrayList();

    while(true)
    {
      GetSellerTransactionsResponseType resp = (GetSellerTransactionsResponseType)this.execute(req);
      TransactionArrayType tarray = resp.getTransactionArray();
      TransactionType[] tm = (tarray == null) ? null : tarray.getTransaction();
      if( tm != null )
      {
        for (int i = 0; i < tm.length; i++)
          transList.add(tm[i]);
      }

      if( resp.getReturnedTransactionCountActual() != null )
        this.returnedTransactionCountActual = resp.getReturnedTransactionCountActual().intValue();
      if( !resp.isHasMoreTransactions().booleanValue() )
        break;

      pgn.setPageNumber(new Integer(pageNum++));
    }

    // Build results.
    this.returnedTransactions = new TransactionType[transList.size()];
    for(int i = 0; i < this.returnedTransactions.length; i ++)
    {
      this.returnedTransactions[i] = (TransactionType)transList.get(i);
    }
    return this.returnedTransactions;
  }

/**
   * Executes the API. GetSellerTransactionsCall.TimeFilter will be ignored.
   * @param modifiedTimeFilter TimeFilter
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return TransactionType[]
   */
  public TransactionType[] getSellerTransactions(TimeFilter modifiedTimeFilter)
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
     this.modifiedTimeFilter = modifiedTimeFilter;
     return getSellerTransactions();
  }
/**
   *
   * @return TimeFilter
   */
  public TimeFilter getTimeFilter() {
    return this.modifiedTimeFilter;
  }
/**
   * Sets time filter for transactions to be returned.
   * @param timeFilter TimeFilter
   */
  public void setTimeFilter(TimeFilter timeFilter) {
    this.modifiedTimeFilter = timeFilter;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSellerTransactionsResponseType.hasMoreTransactions.
   * 
   * @return boolean
   */
  public boolean getHasMoreTransactions()
  {
    return this.hasMoreTransactions;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSellerTransactionsResponseType.paginationResult.
   * 
   * @return PaginationResultType
   */
  public PaginationResultType getPaginationResult()
  {
    return this.paginationResult;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSellerTransactionsResponseType.payPalPreferred.
   * 
   * @return boolean
   */
  public boolean getPayPalPreferred()
  {
    return this.payPalPreferred;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSellerTransactionsResponseType.returnedPageNumber.
   * 
   * @return Integer
   */
  public Integer getReturnedPageNumber()
  {
    return this.returnedPageNumber;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSellerTransactionsResponseType.returnedTransactionCountActual.
   * 
   * @return int
   */
  public int getReturnedTransactionCountActual()
  {
    return this.returnedTransactionCountActual;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSellerTransactionsResponseType.returnedTransactions.
   * 
   * @return TransactionType[]
   */
  public TransactionType[] getReturnedTransactions()
  {
    return this.returnedTransactions;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSellerTransactionsResponseType.returnedTransactionsPerPage.
   * 
   * @return Integer
   */
  public Integer getReturnedTransactionsPerPage()
  {
    return this.returnedTransactionsPerPage;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetSellerTransactionsResponseType.seller.
   * 
   * @return UserType
   */
  public UserType getSeller()
  {
    return this.seller;
  }

}

