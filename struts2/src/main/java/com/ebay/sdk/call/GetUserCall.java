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
 * Wrapper class of the GetUser call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>ItemID</code> - Specify the item ID for a successfully concluded listing in which the
 * requestor and target user were participants (one as seller and the other
 * as buyer). Necessary to return certain data (like an email address). Not
 * necessary if the requestor is retrieving their own data. ItemID is an
 * optional input.
 * <br> <B>Input property:</B> <code>UserID</code> - Specify the user whose data you want returned by the call. UserID is
 * optional. If not specified, eBay returns data pertaining to the
 * requesting user (as specified with the eBayAuthToken).
 * <br> <B>Input property:</B> <code>IncludeExpressRequirements</code> - Indicates if the response should include detailed information relating to
 * whether a user qualifies to list an item on Express. To list an item on
 * Express, a user must qualify based on conditions described in the eBay
 * Web Services Guide.
 * <br> <B>Input property:</B> <code>IncludeFeatureEligibility</code> - If IncludeFeatureEligibility is true, the response includes a
 * FeatureEligibility node. (The FeatureEligibility node indicates whether
 * the seller can list with certain features.)
 * <br> <B>Output property:</B> <code>ReturnedUser</code> - Contains the returned user data for the specified eBay user.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class GetUserCall extends com.ebay.sdk.ApiCall
{
  
  private String itemID = null;
  private String userID = null;
  private Boolean includeExpressRequirements = null;
  private Boolean includeFeatureEligibility = null;
  private UserType returnedUser=null;


  /**
   * Constructor.
   */
  public GetUserCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public GetUserCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Retrieves data pertaining to a single eBay user or transactions for which there is a
   * relationship between the requestor and the target user.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The UserType object.
   */
  public UserType getUser()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    GetUserRequestType req;
    req = new GetUserRequestType();
    req.setDetailLevel(this.getDetailLevel());
    if (this.itemID != null)
      req.setItemID(this.itemID);
    if (this.userID != null)
      req.setUserID(this.userID);
    if (this.includeExpressRequirements != null)
      req.setIncludeExpressRequirements(this.includeExpressRequirements);
    if (this.includeFeatureEligibility != null)
      req.setIncludeFeatureEligibility(this.includeFeatureEligibility);

    GetUserResponseType resp = (GetUserResponseType) execute(req);

    this.returnedUser = resp.getUser();
    return this.getReturnedUser();
  }

  /**
   * Gets the GetUserRequestType.includeExpressRequirements.
   * @return Boolean
   */
  public Boolean getIncludeExpressRequirements()
  {
    return this.includeExpressRequirements;
  }

  /**
   * Sets the GetUserRequestType.includeExpressRequirements.
   * @param includeExpressRequirements Boolean
   */
  public void setIncludeExpressRequirements(Boolean includeExpressRequirements)
  {
    this.includeExpressRequirements = includeExpressRequirements;
  }

  /**
   * Gets the GetUserRequestType.includeFeatureEligibility.
   * @return Boolean
   */
  public Boolean getIncludeFeatureEligibility()
  {
    return this.includeFeatureEligibility;
  }

  /**
   * Sets the GetUserRequestType.includeFeatureEligibility.
   * @param includeFeatureEligibility Boolean
   */
  public void setIncludeFeatureEligibility(Boolean includeFeatureEligibility)
  {
    this.includeFeatureEligibility = includeFeatureEligibility;
  }

  /**
   * Gets the GetUserRequestType.itemID.
   * @return String
   */
  public String getItemID()
  {
    return this.itemID;
  }

  /**
   * Sets the GetUserRequestType.itemID.
   * @param itemID String
   */
  public void setItemID(String itemID)
  {
    this.itemID = itemID;
  }

  /**
   * Gets the GetUserRequestType.userID.
   * @return String
   */
  public String getUserID()
  {
    return this.userID;
  }

  /**
   * Sets the GetUserRequestType.userID.
   * @param userID String
   */
  public void setUserID(String userID)
  {
    this.userID = userID;
  }

  /**
   * Valid after executing the API.
   * Gets the returned GetUserResponseType.returnedUser.
   * 
   * @return UserType
   */
  public UserType getReturnedUser()
  {
    return this.returnedUser;
  }

}

