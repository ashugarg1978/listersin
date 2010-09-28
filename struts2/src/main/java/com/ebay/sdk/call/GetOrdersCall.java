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
import java.util.Calendar;

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the GetOrders call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>OrderIDArray</code> - A set of orders to retrieve.
 * <br><br>
 * Not applicable to Half.com.
 * <br> <B>Input property:</B> <code>CreateTimeFrom</code> - The starting date of the date range for the orders to retrieve.
 * <br><br>
 * Applicable to Half.com.
 * <br> <B>Input property:</B> <code>CreateTimeTo</code> - The ending date of the date range for the orders to retrieve.
 * <br><br>
 * Applicable to Half.com.
 * <br> <B>Input property:</B> <code>OrderRole</code> - Filters orders based on the role of the user making the GetOrders request.
 * <br><br>
 * Not applicable to Half.com.
 * <br> <B>Input property:</B> <code>OrderStatus</code> - Filters the returned orders by order status (Active or Completed).
 * To retrieve orders with a status of Inactive or Cancelled, you must
 * specify the order IDs (OrderIDArray.OrderID). When you specify
 * OrderIDArray.OrderID, no other filters are used.<br>
 * <br>
 * For Half.com, you can get some, but not all orders.
 * Orders on Half.com have different order status values from
 * eBay orders. When you set ListingType to Half, set OrderStatus
 * to Shipped. Otherwise, GetOrders may return incomplete information
 * or have indeterminate results.
 * <br> <B>Input property:</B> <code>ListingType</code> - Specify Half to retrieve Half.com orders.
 * <br><br>
 * <span class="tablenote"><strong>Note:</strong>
 * Do not use this field if you are retrieving eBay orders.
 * <br><br>
 * This field cannnot be used as a listing type filter on eBay.com. If not
 * provided, or if you specify any value other than Half, this field has
 * no useful effect and the call retrieves eBay orders of all types. Also,
 * you can't retrieve both eBay and Half.com orders in the same response.
 * </span>
 * <br> <B>Input property:</B> <code>Pagination</code> - Not applicable to eBay.com. Applicable to Half.com. If many orders are
 * available, you may need to call GetOrders multiple times to retrieve all
 * the data. Each result set is returned as a page of entries. Use this
 * Pagination information to indicate the maximum number of entries to
 * retrieve per page (i.e., per call), the page number to retrieve, and
 * other data.
 * <br> <B>Output property:</B> <code>ReturnedPaginationResult</code> - Applies only to Half.com. Contains information regarding the pagination of
 * data (if pagination is used), including total number of pages and total
 * number of entries.
 * <br> <B>Output property:</B> <code>ReturnedHasMoreOrders</code> - Applies only to Half.com. If true, there are more orders yet to be
 * retrieved. Additional GetOrders calls with higher page numbers or more
 * entries per page must be made to retrieve these orders. If false, no more
 * orders are available or no orders match the request (based on the input
 * filters).
 * <br> <B>Output property:</B> <code>ReturnedOrderArray</code> - The set of orders that match the order IDs or filter criteria specified.
 * Also applicable to Half.com (only returns orders that have not been marked as shipped).
 * <br> <B>Output property:</B> <code>ReturnedOrdersPerPage</code> - Applies only to Half.com. Indicates the number of orders that can be
 * returned per page of data (i.e., per call). This is the same as the value
 * specified in the Pagination.EntriesPerPage input (or the default value, if
 * EntriesPerPage was not specified). This is not necessarily the actual
 * number of orders returned per page (see ReturnedOrderCountActual).
 * <br> <B>Output property:</B> <code>ReturnedPageNumber</code> - Applies only to Half.com. Indicates which page of data holds the current
 * result set. Will be the same as the value specified in the
 * Pagination.PageNumber input. If orders are returned, the first page is 1.
 * <br> <B>Output property:</B> <code>ReturnedReturnedOrderCountActual</code> - Applies only to Half.com. Indicates the total number of orders returned.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class GetOrdersCall extends com.ebay.sdk.ApiCall
{
  
  private OrderIDArrayType orderIDArray = null;
  private Calendar createTimeFrom = null;
  private Calendar createTimeTo = null;
  private TradingRoleCodeType orderRole = null;
  private OrderStatusCodeType orderStatus = null;
  private ListingTypeCodeType listingType = null;
  private PaginationType pagination = null;
  private PaginationResultType returnedPaginationResult=null;
  private Boolean returnedHasMoreOrders=null;
  private OrderType[] returnedOrderArray=null;
  private Integer returnedOrdersPerPage=null;
  private Integer returnedPageNumber=null;
  private Integer returnedReturnedOrderCountActual=null;


  /**
   * Constructor.
   */
  public GetOrdersCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public GetOrdersCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Retrieves the orders for which the authenticated user is a participant, either as
   * the buyer or the seller. The call returns all the orders that meet the request
   * specifications.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The OrderType[] object.
   */
  public OrderType[] getOrders()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GetOrdersRequestType req;
    req = new GetOrdersRequestType();
    req.setDetailLevel(this.getDetailLevel());
    if (this.orderIDArray != null)
      req.setOrderIDArray(this.orderIDArray);
    if (this.createTimeFrom != null)
      req.setCreateTimeFrom(this.createTimeFrom);
    if (this.createTimeTo != null)
      req.setCreateTimeTo(this.createTimeTo);
    if (this.orderRole != null)
      req.setOrderRole(this.orderRole);
    if (this.orderStatus != null)
      req.setOrderStatus(this.orderStatus);
    if (this.listingType != null)
      req.setListingType(this.listingType);
    if (this.pagination != null)
      req.setPagination(this.pagination);

    GetOrdersResponseType resp = (GetOrdersResponseType) execute(req);

    this.returnedPaginationResult = resp.getPaginationResult();
    this.returnedHasMoreOrders = resp.isHasMoreOrders();
    this.returnedOrderArray = (resp.getOrderArray() == null? null: resp.getOrderArray().getOrder());
    this.returnedOrdersPerPage = resp.getOrdersPerPage();
    this.returnedPageNumber = resp.getPageNumber();
    this.returnedReturnedOrderCountActual = resp.getReturnedOrderCountActual();
    return this.getReturnedOrderArray();
  }

  /**
   * Gets the GetOrdersRequestType.createTimeFrom.
   * @return Calendar
   */
  public Calendar getCreateTimeFrom()
  {
    return this.createTimeFrom;
  }

  /**
   * Sets the GetOrdersRequestType.createTimeFrom.
   * @param createTimeFrom Calendar
   */
  public void setCreateTimeFrom(Calendar createTimeFrom)
  {
    this.createTimeFrom = createTimeFrom;
  }

  /**
   * Gets the GetOrdersRequestType.createTimeTo.
   * @return Calendar
   */
  public Calendar getCreateTimeTo()
  {
    return this.createTimeTo;
  }

  /**
   * Sets the GetOrdersRequestType.createTimeTo.
   * @param createTimeTo Calendar
   */
  public void setCreateTimeTo(Calendar createTimeTo)
  {
    this.createTimeTo = createTimeTo;
  }

  /**
   * Gets the GetOrdersRequestType.listingType.
   * @return ListingTypeCodeType
   */
  public ListingTypeCodeType getListingType()
  {
    return this.listingType;
  }

  /**
   * Sets the GetOrdersRequestType.listingType.
   * @param listingType ListingTypeCodeType
   */
  public void setListingType(ListingTypeCodeType listingType)
  {
    this.listingType = listingType;
  }

  /**
   * Gets the GetOrdersRequestType.orderIDArray.
   * @return OrderIDArrayType
   */
  public OrderIDArrayType getOrderIDArray()
  {
    return this.orderIDArray;
  }

  /**
   * Sets the GetOrdersRequestType.orderIDArray.
   * @param orderIDArray OrderIDArrayType
   */
  public void setOrderIDArray(OrderIDArrayType orderIDArray)
  {
    this.orderIDArray = orderIDArray;
  }

  /**
   * Gets the GetOrdersRequestType.orderRole.
   * @return TradingRoleCodeType
   */
  public TradingRoleCodeType getOrderRole()
  {
    return this.orderRole;
  }

  /**
   * Sets the GetOrdersRequestType.orderRole.
   * @param orderRole TradingRoleCodeType
   */
  public void setOrderRole(TradingRoleCodeType orderRole)
  {
    this.orderRole = orderRole;
  }

  /**
   * Gets the GetOrdersRequestType.orderStatus.
   * @return OrderStatusCodeType
   */
  public OrderStatusCodeType getOrderStatus()
  {
    return this.orderStatus;
  }

  /**
   * Sets the GetOrdersRequestType.orderStatus.
   * @param orderStatus OrderStatusCodeType
   */
  public void setOrderStatus(OrderStatusCodeType orderStatus)
  {
    this.orderStatus = orderStatus;
  }

  /**
   * Gets the GetOrdersRequestType.pagination.
   * @return PaginationType
   */
  public PaginationType getPagination()
  {
    return this.pagination;
  }

  /**
   * Sets the GetOrdersRequestType.pagination.
   * @param pagination PaginationType
   */
  public void setPagination(PaginationType pagination)
  {
    this.pagination = pagination;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetOrdersResponseType.returnedHasMoreOrders.
   * 
   * @return Boolean
   */
  public Boolean getReturnedHasMoreOrders()
  {
    return this.returnedHasMoreOrders;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetOrdersResponseType.returnedOrderArray.
   * 
   * @return OrderType[]
   */
  public OrderType[] getReturnedOrderArray()
  {
    return this.returnedOrderArray;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetOrdersResponseType.returnedOrdersPerPage.
   * 
   * @return Integer
   */
  public Integer getReturnedOrdersPerPage()
  {
    return this.returnedOrdersPerPage;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetOrdersResponseType.returnedPageNumber.
   * 
   * @return Integer
   */
  public Integer getReturnedPageNumber()
  {
    return this.returnedPageNumber;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetOrdersResponseType.returnedPaginationResult.
   * 
   * @return PaginationResultType
   */
  public PaginationResultType getReturnedPaginationResult()
  {
    return this.returnedPaginationResult;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetOrdersResponseType.returnedReturnedOrderCountActual.
   * 
   * @return Integer
   */
  public Integer getReturnedReturnedOrderCountActual()
  {
    return this.returnedReturnedOrderCountActual;
  }

}

