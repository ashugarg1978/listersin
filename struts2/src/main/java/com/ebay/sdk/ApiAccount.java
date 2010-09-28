/*
Copyright (c) 2006, 2007 eBay, Inc.

This program is licensed under the terms of the eBay Common Development and
Distribution License (CDDL) Version 1.0 (the "License") and any subsequent
version thereof released by eBay.  The then-current version of the License
can be found at https://www.codebase.ebay.com/Licenses.html and in the
eBaySDKLicense file that is under the eBay SDK install directory.
*/

package com.ebay.sdk;

/**
 * Defines eBay API account object. You need apply for an API account first
 * before making any eBay API call.
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: eBay Inc.</p>
 * @author Weijun Li
 * @version 1.0
 */

public class ApiAccount {
  private String developer;
  private String application;
  private String certificate;

  /**
   * Constructor.
   */
  public ApiAccount() {
  }

  /**
   * Get API developer string.
   * @return the API developer string.
   */
  public String getDeveloper() {
    return this.developer;
  }

  /**
   * Set API developer string.
   * @param developer the API developer string to set.
   */
  public void setDeveloper(String developer) {
    this.developer = developer;
  }

  /**
   * Get API application string.
   * @return the API application string.
   */
  public String getApplication() {
    return this.application;
  }

  /**
   * Set API application string.
   * @param application the API application string to set.
   */
  public void setApplication(String application) {
    this.application = application;
  }

  /**
   * Get API certificate string.
   * @return the API certificate string.
   */
  public String getCertificate() {
    return this.certificate;
  }

  /**
   * Set API certificate string.
   * @param certificate the API certificate string to set.
   */
  public void setCertificate(String certificate) {
    this.certificate = certificate;
  }

}
