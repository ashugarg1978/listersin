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
 * Wrapper class of the GetAccount call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>ViewType</code> - Specifies the report format in which to return account entries.
 * <br> <B>Input property:</B> <code>InvoiceDate</code> - Specifies the month and year of the invoice requested. The report includes
 * only the entries that appear on the seller's invoice in the specified month
 * and year. An entry can occur in one month and appear on the next month's
 * invoice. Used with SpecifiedInvoice reports.
 * <br> <B>Input property:</B> <code>ViewPeriod</code> - Helper wrapper to set GetAccountRequestType BeginDate, EndDate:
 * TimeFrom sets GetAccountRequestType.BeginDate: 
 * Specifies the beginning of a date range during which a credit or debit
 * occurred. Used when AccountHistorySelection is BetweenSpecifiedDates. Value
 * must be less than or equal to the value specified in EndDate. The allowed
 * date formats are YYYY-MM-DD and YYYY-MM-DD HH:mm:ss. You can retrieve
 * information that is up to 4 months old.
 * TimeTo sets GetAccountRequestType.EndDate: 
 * Specifies the end of a date range during which a credit or debit occurred.
 * Used when AccountHistorySelection is BetweenSpecifiedDates. Value must be
 * greater than or equal to the value specified in BeginDate. The allowed date
 * formats are YYYY-MM-DD and YYYY-MM-DD HH:mm:ss.
 * <br> <B>Input property:</B> <code>Pagination</code> - Controls pagination of the response. For this request, the valid values of
 * Pagination.EntriesPerPage are 0 to 2000, with a default of 500.
 * <br> <B>Input property:</B> <code>ExcludeBalance</code> - Specifies whether to calculate balances. Default is false, which calculates
 * balances. The value true means do not calculate balances. If true,
 * AccountEntry.Balance and AccountSummary.CurrentBalance are never returned
 * in the response.
 * <br> <B>Input property:</B> <code>ExcludeSummary</code> - Specifies whether to return account summary information in an
 * AccountSummary node. Default is true, to return AccountSummary.
 * <br> <B>Input property:</B> <code>AccountEntrySortType</code> - Specifies how account entries should be sorted in the response, by an
 * element and then in ascending or descending order.
 * <br> <B>Input property:</B> <code>Currency</code> - Specifies the currency used in the account report. Do not specify Currency
 * in the request unless the following conditions are met. First, the user has
 * or had multiple accounts under the same UserID. Second, the account
 * identified in the request uses the currency you specify in the request. An
 * error is returned if no account is found that uses the currency you specify
 * in the request.
 * <br> <B>Input property:</B> <code>ItemID</code> - Specifies the item ID for which to return account entries. If ItemID is
 * used, all other filters in the request are ignored. If the specified item
 * does not exist or if the requesting user is not the seller of the item, an
 * error is returned.
 * <br> <B>Output property:</B> <code>AccountID</code> - Specifies the seller's unique account number.
 * <br> <B>Output property:</B> <code>AccountSummary</code> - Contains summary data for the seller's account, such as the overall
 * balance, bank account and credit card information, and amount and
 * date of any past due balances. Can also contain data for
 * one or more additional accounts, if the user has changed country
 * of residence.
 * <br> <B>Output property:</B> <code>ReturnedCurrency</code> - Indicates the currency used for monetary amounts in the report.
 * <br> <B>Output property:</B> <code>AccountEntries</code> - Contains individual account entries, according to the report's scope and date range.
 * Each account entry represents one credit, debit, or administrative account action.
 * <br> <B>Output property:</B> <code>PaginationResult</code> - Contains the total number of pages (TotalNumberOfPages) and the total
 * number of account entries (TotalNumberOfEntries) that can be returned
 * on repeated calls with the same format and report criteria.
 * <br> <B>Output property:</B> <code>HasMoreEntries</code> - Indicates whether there are more items yet to be retrieved. Additional calls
 * must be made to retrieve those items.
 * <br> <B>Output property:</B> <code>ReturnedEntriesPerPage</code> - Specifies the number of items that are being returned per virtual page of date.
 * Value is the same as that specified in Pagination.EntriesPerPage.
 * <br> <B>Output property:</B> <code>ReturnedPageNumber</code> - Indicates which page of data was just returned. Value is the same as the value
 * specified in Pagination.PageNumber. If PageNumber in the request is higher than
 * the total number of pages, the call fails with an error.)
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class GetAccountCall extends com.ebay.sdk.ApiCall
{
  
  private AccountHistorySelectionCodeType viewType = AccountHistorySelectionCodeType.LAST_INVOICE;
  private Calendar invoiceDate = null;
  private TimeFilter viewPeriod = null;
  private PaginationType pagination = null;
  private Boolean excludeBalance = null;
  private Boolean excludeSummary = null;
  private AccountEntrySortTypeCodeType accountEntrySortType = null;
  private CurrencyCodeType currency = null;
  private String itemID = null;
  private String accountID=null;
  private AccountSummaryType accountSummary=null;
  private CurrencyCodeType returnedCurrency=null;
  private AccountEntryType[] accountEntries=null;
  private PaginationResultType paginationResult=null;
  private boolean hasMoreEntries=false;
  private Integer returnedEntriesPerPage=null;
  private Integer returnedPageNumber=null;


  /**
   * Constructor.
   */
  public GetAccountCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public GetAccountCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Returns a seller's invoice data for their eBay account, including the account's
   * summary data.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The AccountEntryType[] object.
   */
  public AccountEntryType[] getAccount()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GetAccountRequestType req;
    req = new GetAccountRequestType();

    if( this.viewType == null )
      throw new SdkException("ViewType property is not set.");

    if( this.viewType.equals(AccountHistorySelectionCodeType.BETWEEN_SPECIFIED_DATES) )
    {
      if( this.viewPeriod == null )
        throw new SdkException("GetAccountCall.ViewPeriod is not set.");
    }
    else if( this.viewType.equals(AccountHistorySelectionCodeType.SPECIFIED_INVOICE) )
    {
      if( this.invoiceDate == null )
        throw new SdkException("GetAccountCall.InvoiceDate is not set.");
    }

    req.setDetailLevel(this.getDetailLevel());
    if (this.viewType != null)
      req.setAccountHistorySelection(this.viewType);
    if (this.invoiceDate != null)
      req.setInvoiceDate(this.invoiceDate);
    if (this.viewPeriod != null)
    {
      req.setBeginDate(this.viewPeriod.getTimeFrom());
      req.setEndDate(this.viewPeriod.getTimeTo());
    }
    if (this.pagination != null)
      req.setPagination(this.pagination);
    if (this.excludeBalance != null)
      req.setExcludeBalance(this.excludeBalance);
    if (this.excludeSummary != null)
      req.setExcludeSummary(this.excludeSummary);
    if (this.accountEntrySortType != null)
      req.setAccountEntrySortType(this.accountEntrySortType);
    if (this.currency != null)
      req.setCurrency(this.currency);
    if (this.itemID != null)
      req.setItemID(this.itemID);

    GetAccountResponseType resp = (GetAccountResponseType) execute(req);

    this.accountID = resp.getAccountID();
    this.accountSummary = resp.getAccountSummary();
    this.returnedCurrency = resp.getCurrency();
    this.accountEntries = (resp.getAccountEntries() == null? null: resp.getAccountEntries().getAccountEntry());
    this.paginationResult = resp.getPaginationResult();
    this.hasMoreEntries = (resp.isHasMoreEntries() == null? false: resp.isHasMoreEntries().booleanValue());
    this.returnedEntriesPerPage = resp.getEntriesPerPage();
    this.returnedPageNumber = resp.getPageNumber();
    return this.getAccountEntries();
  }

  /**
   * Gets the GetAccountRequestType.accountEntrySortType.
   * @return AccountEntrySortTypeCodeType
   */
  public AccountEntrySortTypeCodeType getAccountEntrySortType()
  {
    return this.accountEntrySortType;
  }

  /**
   * Sets the GetAccountRequestType.accountEntrySortType.
   * @param accountEntrySortType AccountEntrySortTypeCodeType
   */
  public void setAccountEntrySortType(AccountEntrySortTypeCodeType accountEntrySortType)
  {
    this.accountEntrySortType = accountEntrySortType;
  }

  /**
   * Gets the GetAccountRequestType.currency.
   * @return CurrencyCodeType
   */
  public CurrencyCodeType getCurrency()
  {
    return this.currency;
  }

  /**
   * Sets the GetAccountRequestType.currency.
   * @param currency CurrencyCodeType
   */
  public void setCurrency(CurrencyCodeType currency)
  {
    this.currency = currency;
  }

  /**
   * Gets the GetAccountRequestType.excludeBalance.
   * @return Boolean
   */
  public Boolean getExcludeBalance()
  {
    return this.excludeBalance;
  }

  /**
   * Sets the GetAccountRequestType.excludeBalance.
   * @param excludeBalance Boolean
   */
  public void setExcludeBalance(Boolean excludeBalance)
  {
    this.excludeBalance = excludeBalance;
  }

  /**
   * Gets the GetAccountRequestType.excludeSummary.
   * @return Boolean
   */
  public Boolean getExcludeSummary()
  {
    return this.excludeSummary;
  }

  /**
   * Sets the GetAccountRequestType.excludeSummary.
   * @param excludeSummary Boolean
   */
  public void setExcludeSummary(Boolean excludeSummary)
  {
    this.excludeSummary = excludeSummary;
  }

  /**
   * Gets the GetAccountRequestType.invoiceDate.
   * @return Calendar
   */
  public Calendar getInvoiceDate()
  {
    return this.invoiceDate;
  }

  /**
   * Sets the GetAccountRequestType.invoiceDate.
   * @param invoiceDate Calendar
   */
  public void setInvoiceDate(Calendar invoiceDate)
  {
    this.invoiceDate = invoiceDate;
  }

  /**
   * Gets the GetAccountRequestType.itemID.
   * @return String
   */
  public String getItemID()
  {
    return this.itemID;
  }

  /**
   * Sets the GetAccountRequestType.itemID.
   * @param itemID String
   */
  public void setItemID(String itemID)
  {
    this.itemID = itemID;
  }

  /**
   * Gets the GetAccountRequestType.pagination.
   * @return PaginationType
   */
  public PaginationType getPagination()
  {
    return this.pagination;
  }

  /**
   * Sets the GetAccountRequestType.pagination.
   * @param pagination PaginationType
   */
  public void setPagination(PaginationType pagination)
  {
    this.pagination = pagination;
  }

  /**
   * Gets the GetAccountRequestType.viewPeriod.
   * @return TimeFilter
   */
  public TimeFilter getViewPeriod()
  {
    return this.viewPeriod;
  }

  /**
   * Sets the GetAccountRequestType.viewPeriod.
   * @param viewPeriod TimeFilter
   */
  public void setViewPeriod(TimeFilter viewPeriod)
  {
    this.viewPeriod = viewPeriod;
  }

  /**
   * Gets the GetAccountRequestType.viewType.
   * @return AccountHistorySelectionCodeType
   */
  public AccountHistorySelectionCodeType getViewType()
  {
    return this.viewType;
  }

  /**
   * Sets the GetAccountRequestType.viewType.
   * @param viewType AccountHistorySelectionCodeType
   */
  public void setViewType(AccountHistorySelectionCodeType viewType)
  {
    this.viewType = viewType;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetAccountResponseType.accountEntries.
   * 
   * @return AccountEntryType[]
   */
  public AccountEntryType[] getAccountEntries()
  {
    return this.accountEntries;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetAccountResponseType.accountID.
   * 
   * @return String
   */
  public String getAccountID()
  {
    return this.accountID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetAccountResponseType.accountSummary.
   * 
   * @return AccountSummaryType
   */
  public AccountSummaryType getAccountSummary()
  {
    return this.accountSummary;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetAccountResponseType.hasMoreEntries.
   * 
   * @return boolean
   */
  public boolean getHasMoreEntries()
  {
    return this.hasMoreEntries;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetAccountResponseType.paginationResult.
   * 
   * @return PaginationResultType
   */
  public PaginationResultType getPaginationResult()
  {
    return this.paginationResult;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetAccountResponseType.returnedCurrency.
   * 
   * @return CurrencyCodeType
   */
  public CurrencyCodeType getReturnedCurrency()
  {
    return this.returnedCurrency;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetAccountResponseType.returnedEntriesPerPage.
   * 
   * @return Integer
   */
  public Integer getReturnedEntriesPerPage()
  {
    return this.returnedEntriesPerPage;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetAccountResponseType.returnedPageNumber.
   * 
   * @return Integer
   */
  public Integer getReturnedPageNumber()
  {
    return this.returnedPageNumber;
  }

}

