/*
Copyright (c) 2009 eBay, Inc.

This program is licensed under the terms of the eBay Common Development and 
Distribution License (CDDL) Version 1.0 (the "License") and any subsequent 
version thereof released by eBay.  The then-current version of the License 
can be found at https://www.codebase.ebay.com/Licenses.html and in the 
eBaySDKLicense file that is under the eBay SDK install directory.
*/

package com.ebay.sdk.call;

import java.lang.String;

import com.ebay.sdk.*;
import com.ebay.soap.eBLBaseComponents.*;
/**
 * Wrapper class of the SetUserNotes call of eBay SOAP API.
 * <br>
 * <p>Title: SOAP API wrapper library.</p>
 * <p>Description: Contains wrapper classes for eBay SOAP APIs.</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: eBay Inc.</p>
 * <br> <B>Input property:</B> <code>ItemID</code> - ID of the item to which the My eBay note will be
 * attached. Notes can only be added to items that are
 * currently being tracked in My eBay.
 * <br> <B>Input property:</B> <code>Action</code> - Specifies whether to add/update the note or delete.
 * <br> <B>Input property:</B> <code>NoteText</code> - Text of the note. Maximum 250 characters. Required only
 * if the Action is AddOrUpdate. This note text will
 * completely replace any existing My eBay note for the
 * specified item.
 * <br> <B>Input property:</B> <code>TransactionID</code> - ID of the transaction to which the My eBay note will be
 * attached. Notes can only be added to transactions that are
 * currently being tracked in My eBay.
 * You can see it in the Won list of GetMyeBayBuying if you are the buyer.
 * You can see it from Sold list of GetMyeBaySelling if you are the seller.
 * <br> <B>Input property:</B> <code>VariationSpecifics</code> - Name-value pairs that identify (match) one variation within the
 * listing identified by ItemID. Ignored if used in combination
 * with TransactionID.
 * <br> <B>Input property:</B> <code>SKU</code> - Variation-level SKU that uniquely identifes a variation within
 * the listing identified by ItemID. Only applicable when the
 * seller listed the item with variation-level SKU (Variation.SKU)
 * values. Retrieves all the usual Item fields, but limits the
 * Variations content to the specified variation. <br>
 * <br>
 * As buyers should not know a variation's SKU, this field should
 * only be used when the item's seller is setting a note on the
 * variation.<br>
 * <br>
 * Ignored if used in combination with TransactionID.
 * 
 * @author Ron Murphy
 * @version 1.0
 */

public class SetUserNotesCall extends com.ebay.sdk.ApiCall
{
  
  private String itemID = null;
  private SetUserNotesActionCodeType action = null;
  private String noteText = null;
  private String transactionID = null;
  private NameValueListArrayType variationSpecifics = null;
  private String sKU = null;


  /**
   * Constructor.
   */
  public SetUserNotesCall() {
  }

  /**
   * Constructor.
   * @param apiContext The ApiContext object to be used to make the call.
   */
  public SetUserNotesCall(ApiContext apiContext) {
    super(apiContext);
    

  }

  /**
   * Enables users to add, replace, and delete My eBay notes for
   * items that are being tracked in the My eBay All Selling and
   * All Buying areas.
   * 
   * <br>
   * @throws ApiException
   * @throws SdkException
   * @throws Exception
   * @return The void object.
   */
  public void setUserNotes()
      throws com.ebay.sdk.ApiException, com.ebay.sdk.SdkException, java.lang.Exception
  {
    SetUserNotesRequestType req;
    req = new SetUserNotesRequestType();

    if( this.itemID == null )
      throw new SdkException("ItemID property is not set.");
    if( this.action == null )
      throw new SdkException("Action property is not set.");

    if (this.itemID != null)
      req.setItemID(this.itemID);
    if (this.action != null)
      req.setAction(this.action);
    if (this.noteText != null)
      req.setNoteText(this.noteText);
    if (this.transactionID != null)
      req.setTransactionID(this.transactionID);
    if (this.variationSpecifics != null)
      req.setVariationSpecifics(this.variationSpecifics);
    if (this.sKU != null)
      req.setSKU(this.sKU);

    SetUserNotesResponseType resp = (SetUserNotesResponseType) execute(req);


  }

  /**
   * Gets the SetUserNotesRequestType.action.
   * @return SetUserNotesActionCodeType
   */
  public SetUserNotesActionCodeType getAction()
  {
    return this.action;
  }

  /**
   * Sets the SetUserNotesRequestType.action.
   * @param action SetUserNotesActionCodeType
   */
  public void setAction(SetUserNotesActionCodeType action)
  {
    this.action = action;
  }

  /**
   * Gets the SetUserNotesRequestType.itemID.
   * @return String
   */
  public String getItemID()
  {
    return this.itemID;
  }

  /**
   * Sets the SetUserNotesRequestType.itemID.
   * @param itemID String
   */
  public void setItemID(String itemID)
  {
    this.itemID = itemID;
  }

  /**
   * Gets the SetUserNotesRequestType.noteText.
   * @return String
   */
  public String getNoteText()
  {
    return this.noteText;
  }

  /**
   * Sets the SetUserNotesRequestType.noteText.
   * @param noteText String
   */
  public void setNoteText(String noteText)
  {
    this.noteText = noteText;
  }

  /**
   * Gets the SetUserNotesRequestType.sKU.
   * @return String
   */
  public String getSKU()
  {
    return this.sKU;
  }

  /**
   * Sets the SetUserNotesRequestType.sKU.
   * @param sKU String
   */
  public void setSKU(String sKU)
  {
    this.sKU = sKU;
  }

  /**
   * Gets the SetUserNotesRequestType.transactionID.
   * @return String
   */
  public String getTransactionID()
  {
    return this.transactionID;
  }

  /**
   * Sets the SetUserNotesRequestType.transactionID.
   * @param transactionID String
   */
  public void setTransactionID(String transactionID)
  {
    this.transactionID = transactionID;
  }

  /**
   * Gets the SetUserNotesRequestType.variationSpecifics.
   * @return NameValueListArrayType
   */
  public NameValueListArrayType getVariationSpecifics()
  {
    return this.variationSpecifics;
  }

  /**
   * Sets the SetUserNotesRequestType.variationSpecifics.
   * @param variationSpecifics NameValueListArrayType
   */
  public void setVariationSpecifics(NameValueListArrayType variationSpecifics)
  {
    this.variationSpecifics = variationSpecifics;
  }

}

