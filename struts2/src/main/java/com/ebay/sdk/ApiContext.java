/*
Copyright (c) 2006, 2007 eBay, Inc.

This program is licensed under the terms of the eBay Common Development and
Distribution License (CDDL) Version 1.0 (the "License") and any subsequent
version thereof released by eBay.  The then-current version of the License
can be found at https://www.codebase.ebay.com/Licenses.html and in the
eBaySDKLicense file that is under the eBay SDK install directory.
*/

/*
*/

package com.ebay.sdk;

import com.ebay.soap.eBLBaseComponents.*;

/**
 * Defines API context under which the API call will be made. To successfully
 * make eBay API call, you need both API account and a normal eBay account.
 * ApiContext also controls additional things like logging, CallRetry etc.
 * <p>If you use eBay token as credential to make the API call you only need
 * to set the eBayToken property. Otherwise you need to use setApiAccount() and
 * seteBayAccount() together to set the account credential for the API call.
 * <br><br>
 * <a href="{@docRoot}/images/ApiContext.jpg">View UML diagram of the class.</a>
 * <br>
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004 - 2007</p>
 * <p>Company: eBay Inc.</p>
 * @author Weijun Li
 * @version 1.0
 */
public class ApiContext {

  private int timeout = 0;
  private String apiServerUrl = null;
  private String epsServerUrl = null;
  private String signInUrl = null;
  private ApiCredential apiCredential = new ApiCredential();
  private java.lang.String errorLanguage;
  private ApiLogging apiLogging = new ApiLogging();
  private CallRetry callRetry = null;
  private int totalCalls = 0;
  private SiteCodeType site = SiteCodeType.US;
  private String requestXml;
  private String responseXml;
  private String wsdlVersion = "673";
  private String ruName = null;

  /**
   * Rounting is no longer needed.
   */
  private String routing = null;  //"beta";

  /**
   * Constructor.
   */
  public ApiContext() {
  }

 /**
  * Get timeout for HTTP connection.
  * @return the timeout value in milliseconds.
  */
  public int getTimeout() {
    return this.timeout;
  }

  /**
   * Set timeout of HTTP connection.
   * @param timeout the timeout value to set. In milliseconds.
   */
  public void setTimeout(int timeout) {
    this.timeout = timeout;
  }

  /**
   * Get URL of the eBay API server to which the API call will be made.
   * @return URL string of the eBay API server.
   */
  public String getApiServerUrl() {
    return this.apiServerUrl;
  }

  /**
   * Set the URL of the eBay API server to which the API call will be made.
   * @param url The URL string to set.
   */
  public void setApiServerUrl(String url) {
    this.apiServerUrl = url;
  }
  
  /**
   * Get SignIn Url.
   * @return string SignIn Url.
   */
  public String getSignInUrl() {
    return this.signInUrl;
  }

  /**
   * Set SignIn Url.
   * param string SignIn Url.
   */
  public void setSignInUrl(String url) {
    this.signInUrl = url;
  }

  /**
   * Get the ApiAccount to use.
   * @return the ApiAccount instance.
   */
  public ApiCredential getApiCredential(){
    return this.apiCredential;
  }

  /**
   * Set the ApiCredential to use.
   * @param apiCredential the ApiCredential instance to be set to.
   */
  public void setApiCredential(ApiCredential apiCredential) {
    this.apiCredential = apiCredential;
  }

  /**
   * Get language string to return error.
   * @return the error language string.
   */
  public java.lang.String getErrorLanguage() {
    return errorLanguage;
  }

  /**
   * Set the error language string.
   * @param errorLanguage the error language string to be set to.
   */
  public void setErrorLanguage(java.lang.String errorLanguage) {
    this.errorLanguage = errorLanguage;
  }

  /**
   * Gets the ApiLogging property.
   * @return The ApiLogging object.
   */
  public ApiLogging getApiLogging() {
    return this.apiLogging;
  }

  /**
   * Sets the ApiLogging property.
   * @param apiLogging ApiLogging
   */
  public void setApiLogging(ApiLogging apiLogging) {
    this.apiLogging = apiLogging;
  }

  /**
   * Get the CallRetry object.
   * @return The CallRetry object.
   */
  public CallRetry getCallRetry() {
    return this.callRetry;
  }

  /**
   * Set the CallRetry object to be used to control the retry behaviour of calls.
   * It controls the Call-Retry behavior of all API calls made with this ApiContext
   * object. If you set ApiCall.CallRetry property of each individual call,
   * the ApiContext.CallRetry will be overriden or ignored.
   * @param callRetry the CallRetry object to use.
   */
  public void setCallRetry(CallRetry callRetry) {
    this.callRetry = callRetry;
  }

  /**
   * Get total number of API calls that have been made with the ApiContext
   * object. Each time when you successfully made an API call with the ApiContext
   * object, the ApiCall instance will call setTotalCalls() to increment
   * the total number of API calls.
   * @return Total number of calls made.
   */
  public int getTotalCalls() {
    return this.totalCalls;
  }

  /**
   * Set total number of API calls made with the ApiContext object. This method
   * is usually called by ApiCall object only.
   * @param totalCalls Total number of calls to be set to.
   */
  public void setTotalCalls(int totalCalls) {
    this.totalCalls = totalCalls;
  }

  /**
   * Increase number of total calls by one.
   * @return The number of total calls made.
   */
  public int incrementTotalCalls() {
    return (++this.totalCalls);
  }

  /**
   * Returns the epsServerUrl.
   * @return String
   */
  public String getEpsServerUrl() {
	return epsServerUrl;
  }

  /**
   * Sets the gateway routing.
   * @param routing The routing to set
   */
  public void setRouting(String routing) {
    this.routing = routing;
  }

  /**
   * Returns the routing.
   * @return String
   */
  public String getRouting() {
        return routing;
  }

/**
 * Sets the epsServerUrl.
 * @param epsServerUrl The epsServerUrl to set
 */
  public void setEpsServerUrl(String epsServerUrl) {
    this.epsServerUrl = epsServerUrl;
  }

  /**
   * Gets the site for API calls under the context.
   * @return SiteCodeType
   */
  public SiteCodeType getSite() {
    return site;
  }

  /**
   * Sets the common eBay site that for all the APIs calls made with the
   * ApiContext object. If an call object (inherited from ApiCall, e.g., AddItemCall)
   * sets its Site property (ApiCall.setSite()) it will override this common eBay
   * site property.
   * @param site SiteCodeType
   */
  public void setSite(SiteCodeType site) {
    this.site = site;
  }

  /**
   * Gets the version of WSDL version to be used.
   * @return String
   */
  public String getWSDLVersion() {
    return wsdlVersion;
  }

  /**
   * Sets the version of WSDL version to be used.
   * @param wsdlVersion String
   */
  public void setWSDLVersion(String wsdlVersion) {
    this.wsdlVersion = wsdlVersion;
  }

	public String getRequestXml() {
		return requestXml;
	}

	public void setRequestXml(String requestXml) {
		this.requestXml = requestXml;
	}

	public String getResponseXml() {
		return responseXml;
	}

	public void setResponseXml(String responseXml) {
		this.responseXml = responseXml;
	}

	public String getRuName() {
		return ruName;
	}

	public void setRuName(String ruName) {
		this.ruName = ruName;
	}

}
