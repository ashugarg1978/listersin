/*
Copyright (c) 2009 eBay, Inc.

This program is licensed under the terms of the eBay Common Development and 
Distribution License (CDDL) Version 1.0 (the "License") and any subsequent 
version thereof released by eBay.  The then-current version of the License 
can be found at https://www.codebase.ebay.com/Licenses.html and in the 
eBaySDKLicense file that is under the eBay SDK install directory.
*/

package com.ebay.sdk.call;

import java.util.Calendar;

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the AddOrder call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>Order</code> - The order that is being created.
 * <br> <B>Output property:</B> <code>ReturnedOrderID</code> - The unique identifier for the order.
 * <br> <B>Output property:</B> <code>ReturnedCreatedTime</code> - The date and time the order was created.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class AddOrderCall extends com.ebay.sdk.ApiCall
{
  
  private OrderType order = null;
  private String returnedOrderID=null;
  private Calendar returnedCreatedTime=null;


  /**
   * Constructor.
   */
  public AddOrderCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public AddOrderCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Combines two or more transactions into a single order, enabling a buyer
   * to pay for all of those transactions with a single payment (and, if
   * so arranged, ship all of the items together).
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The AddOrderResponseType object.
   */
  public AddOrderResponseType addOrder()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    AddOrderRequestType req;
    req = new AddOrderRequestType();
    if (this.order != null)
      req.setOrder(this.order);

    AddOrderResponseType resp = (AddOrderResponseType) execute(req);

    this.returnedOrderID = resp.getOrderID();
    this.returnedCreatedTime = resp.getCreatedTime();
    return resp;
  }

  /**
   * Gets the AddOrderRequestType.order.
   * @return OrderType
   */
  public OrderType getOrder()
  {
    return this.order;
  }

  /**
   * Sets the AddOrderRequestType.order.
   * @param order OrderType
   */
  public void setOrder(OrderType order)
  {
    this.order = order;
  }

  /**
   * Valid after executing the API.
   * Gets the returned AddOrderResponseType.returnedCreatedTime.
   * 
   * @return Calendar
   */
  public Calendar getReturnedCreatedTime()
  {
    return this.returnedCreatedTime;
  }

  /**
   * Valid after executing the API.
   * Gets the returned AddOrderResponseType.returnedOrderID.
   * 
   * @return String
   */
  public String getReturnedOrderID()
  {
    return this.returnedOrderID;
  }

}

