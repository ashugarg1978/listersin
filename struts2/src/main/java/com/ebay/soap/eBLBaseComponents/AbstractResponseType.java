
package com.ebay.soap.eBLBaseComponents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3c.dom.Element;


/**
 * 
 * 				Base type definition of a response payload that can carry any
 * 				type of payload content with following optional elements:<br>
 * 				- timestamp of response message<br>
 * 				- application-level acknowledgement<br>
 * 				- application-level (business-level) errors and warnings
 * 			
 * 
 * <p>Java class for AbstractResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AbstractResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Ack" type="{urn:ebay:apis:eBLBaseComponents}AckCodeType" minOccurs="0"/>
 *         &lt;element name="CorrelationID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Errors" type="{urn:ebay:apis:eBLBaseComponents}ErrorType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Build" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NotificationEventName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DuplicateInvocationDetails" type="{urn:ebay:apis:eBLBaseComponents}DuplicateInvocationDetailsType" minOccurs="0"/>
 *         &lt;element name="RecipientUserID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EIASToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NotificationSignature" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HardExpirationWarning" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BotBlock" type="{urn:ebay:apis:eBLBaseComponents}BotBlockResponseType" minOccurs="0"/>
 *         &lt;element name="ExternalUserData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;any/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractResponseType", propOrder = {
    "timestamp",
    "ack",
    "correlationID",
    "errors",
    "message",
    "version",
    "build",
    "notificationEventName",
    "duplicateInvocationDetails",
    "recipientUserID",
    "eiasToken",
    "notificationSignature",
    "hardExpirationWarning",
    "botBlock",
    "externalUserData",
    "any"
})
@XmlSeeAlso({
    GetTaxTableResponseType.class,
    PlaceOfferResponseType.class,
    VerifyAddSecondChanceItemResponseType.class,
    ReviseItemResponseType.class,
    RespondToWantItNowPostResponseType.class,
    GetMyeBaySellingResponseType.class,
    GetNotificationPreferencesResponseType.class,
    GetAttributesCSResponseType.class,
    GetProductSearchPageResponseType.class,
    SetStorePreferencesResponseType.class,
    GetCategoriesResponseType.class,
    RespondToBestOfferResponseType.class,
    GetItemRecommendationsResponseType.class,
    ReviseFixedPriceItemResponseType.class,
    DeleteSellingManagerProductResponseType.class,
    AddItemFromSellingManagerTemplateResponseType.class,
    GetPromotionRulesResponseType.class,
    SetPromotionalSaleListingsResponseType.class,
    SetSellingManagerTemplateAutomationRuleResponseType.class,
    GetStoreCustomPageResponseType.class,
    ReviseInventoryStatusResponseType.class,
    GetStoreCategoryUpdateStatusResponseType.class,
    GetSellingManagerTemplateAutomationRuleResponseType.class,
    MoveSellingManagerInventoryFolderResponseType.class,
    GetPromotionalSaleDetailsResponseType.class,
    AddOrderResponseType.class,
    ReviseSellingManagerSaleRecordResponseType.class,
    GetItemShippingResponseType.class,
    GetProductFamilyMembersResponseType.class,
    VerifyAddItemResponseType.class,
    GetUserContactDetailsResponseType.class,
    SetUserNotesResponseType.class,
    ReviseSellingManagerInventoryFolderResponseType.class,
    GetSellingManagerInventoryFolderResponseType.class,
    DeleteMyMessagesResponseType.class,
    GetUserPreferencesResponseType.class,
    GetCrossPromotionsResponseType.class,
    GetSellerDashboardResponseType.class,
    GetSellerPaymentsResponseType.class,
    GetSearchResultsResponseType.class,
    SetStoreResponseType.class,
    GetWantItNowSearchResultsResponseType.class,
    ReviseMyMessagesResponseType.class,
    VerifyAddFixedPriceItemResponseType.class,
    GetOrderTransactionsResponseType.class,
    ValidateChallengeInputResponseType.class,
    GetItemTransactionsResponseType.class,
    GetProductFinderXSLResponseType.class,
    EndItemResponseType.class,
    GetDisputeResponseType.class,
    EndItemsResponseType.class,
    GetSellerTransactionsResponseType.class,
    GetBestOffersResponseType.class,
    GetSellingManagerItemAutomationRuleResponseType.class,
    GetSellingManagerAlertsResponseType.class,
    GetVeROReasonCodeDetailsResponseType.class,
    EndFixedPriceItemResponseType.class,
    SetMessagePreferencesResponseType.class,
    AddSellingManagerInventoryFolderResponseType.class,
    GetPictureManagerDetailsResponseType.class,
    GetSellingManagerTemplatesResponseType.class,
    AddMemberMessagesAAQToBidderResponseType.class,
    SetPromotionalSaleResponseType.class,
    GetProductsResponseType.class,
    UploadSiteHostedPicturesResponseType.class,
    GetCategoryMappingsResponseType.class,
    GetStoreOptionsResponseType.class,
    AddDisputeResponseResponseType.class,
    CompleteSaleResponseType.class,
    AddTransactionConfirmationItemResponseType.class,
    SetUserPreferencesResponseType.class,
    SetSellingManagerFeedbackOptionsResponseType.class,
    GetUserDisputesResponseType.class,
    GetDescriptionTemplatesResponseType.class,
    RelistItemResponseType.class,
    GetCategorySpecificsResponseType.class,
    GetOrdersResponseType.class,
    GetProductSellingPagesResponseType.class,
    GetAdFormatLeadsResponseType.class,
    GetStorePreferencesResponseType.class,
    AddToWatchListResponseType.class,
    ReviseSellingManagerProductResponseType.class,
    DisableUnpaidItemAssistanceResponseType.class,
    RelistFixedPriceItemResponseType.class,
    GetMessagePreferencesResponseType.class,
    GetApiAccessRulesResponseType.class,
    ConfirmIdentityResponseType.class,
    SellerReverseDisputeResponseType.class,
    GetSellingManagerInventoryResponseType.class,
    ReviseMyMessagesFoldersResponseType.class,
    AddMemberMessageRTQResponseType.class,
    GetMemberMessagesResponseType.class,
    GetAttributesXSLResponseType.class,
    SaveItemToSellingManagerTemplateResponseType.class,
    GetCharitiesResponseType.class,
    GetMyeBayRemindersResponseType.class,
    NotificationMessageType.class,
    GetStoreResponseType.class,
    SetNotificationPreferencesResponseType.class,
    DeleteSellingManagerTemplateAutomationRuleResponseType.class,
    AddItemResponseType.class,
    DeleteSellingManagerInventoryFolderResponseType.class,
    GetItemsAwaitingFeedbackResponseType.class,
    GetSellingManagerSaleRecordResponseType.class,
    GetSuggestedCategoriesResponseType.class,
    SetSellingManagerItemAutomationRuleResponseType.class,
    GetAllBiddersResponseType.class,
    GetMyMessagesResponseType.class,
    GetNotificationsUsageResponseType.class,
    AddItemsResponseType.class,
    SetStoreCustomPageResponseType.class,
    LeaveFeedbackResponseType.class,
    GetUserResponseType.class,
    VerifyRelistItemResponseType.class,
    GeteBayDetailsResponseType.class,
    GetFeedbackResponseType.class,
    IssueRefundResponseType.class,
    GetWantItNowPostResponseType.class,
    GetProductFinderResponseType.class,
    GetTokenStatusResponseType.class,
    ValidateTestUserRegistrationResponseType.class,
    SetPictureManagerDetailsResponseType.class,
    GetMyeBayBuyingResponseType.class,
    SetTaxTableResponseType.class,
    DeleteSellingManagerTemplateResponseType.class,
    GetChallengeTokenResponseType.class,
    ReviseSellingManagerTemplateResponseType.class,
    GetShippingDiscountProfilesResponseType.class,
    AddToItemDescriptionResponseType.class,
    RemoveFromWatchListResponseType.class,
    AddMemberMessageAAQToPartnerResponseType.class,
    GetCategory2CSResponseType.class,
    GetSessionIDResponseType.class,
    GetHighBiddersResponseType.class,
    GetSellerListResponseType.class,
    GetBidderListResponseType.class,
    SetShippingDiscountProfilesResponseType.class,
    RespondToFeedbackResponseType.class,
    SendInvoiceResponseType.class,
    RevokeTokenResponseType.class,
    GetAccountResponseType.class,
    GetSellingManagerSoldListingsResponseType.class,
    SetStoreCategoriesResponseType.class,
    GetItemResponseType.class,
    AddSecondChanceItemResponseType.class,
    GetSellingManagerEmailLogResponseType.class,
    ExtendSiteHostedPicturesResponseType.class,
    ReviseCheckoutStatusResponseType.class,
    GeteBayOfficialTimeResponseType.class,
    FetchTokenResponseType.class,
    AddSellingManagerTemplateResponseType.class,
    DeleteSellingManagerItemAutomationRuleResponseType.class,
    VeROReportItemsResponseType.class,
    GetCategoryFeaturesResponseType.class,
    AddFixedPriceItemResponseType.class,
    AddDisputeResponseType.class,
    GetCategoryListingsResponseType.class,
    GetPopularKeywordsResponseType.class,
    AddSellingManagerProductResponseType.class,
    GetContextualKeywordsResponseType.class,
    GetVeROReportStatusResponseType.class,
    GetProductSearchResultsResponseType.class,
    GetPictureManagerOptionsResponseType.class,
    GetSellerEventsResponseType.class,
    GetClientAlertsAuthTokenResponseType.class,
    ItemsCanceledEventType.class
})
public abstract class AbstractResponseType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "Timestamp", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar timestamp;
    @XmlElement(name = "Ack")
    protected AckCodeType ack;
    @XmlElement(name = "CorrelationID")
    protected String correlationID;
    @XmlElement(name = "Errors")
    protected List<ErrorType> errors;
    @XmlElement(name = "Message")
    protected String message;
    @XmlElement(name = "Version")
    protected String version;
    @XmlElement(name = "Build")
    protected String build;
    @XmlElement(name = "NotificationEventName")
    protected String notificationEventName;
    @XmlElement(name = "DuplicateInvocationDetails")
    protected DuplicateInvocationDetailsType duplicateInvocationDetails;
    @XmlElement(name = "RecipientUserID")
    protected String recipientUserID;
    @XmlElement(name = "EIASToken")
    protected String eiasToken;
    @XmlElement(name = "NotificationSignature")
    protected String notificationSignature;
    @XmlElement(name = "HardExpirationWarning")
    protected String hardExpirationWarning;
    @XmlElement(name = "BotBlock")
    protected BotBlockResponseType botBlock;
    @XmlElement(name = "ExternalUserData")
    protected String externalUserData;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimestamp(Calendar value) {
        this.timestamp = value;
    }

    /**
     * Gets the value of the ack property.
     * 
     * @return
     *     possible object is
     *     {@link AckCodeType }
     *     
     */
    public AckCodeType getAck() {
        return ack;
    }

    /**
     * Sets the value of the ack property.
     * 
     * @param value
     *     allowed object is
     *     {@link AckCodeType }
     *     
     */
    public void setAck(AckCodeType value) {
        this.ack = value;
    }

    /**
     * Gets the value of the correlationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrelationID() {
        return correlationID;
    }

    /**
     * Sets the value of the correlationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrelationID(String value) {
        this.correlationID = value;
    }

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link ErrorType }
     *     
     */
    public ErrorType[] getErrors() {
        if (this.errors == null) {
            return new ErrorType[ 0 ] ;
        }
        return ((ErrorType[]) this.errors.toArray(new ErrorType[this.errors.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link ErrorType }
     *     
     */
    public ErrorType getErrors(int idx) {
        if (this.errors == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.errors.get(idx);
    }

    public int getErrorsLength() {
        if (this.errors == null) {
            return  0;
        }
        return this.errors.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link ErrorType }
     *     
     */
    public void setErrors(ErrorType[] values) {
        this._getErrors().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.errors.add(values[i]);
        }
    }

    protected List<ErrorType> _getErrors() {
        if (errors == null) {
            errors = new ArrayList<ErrorType>();
        }
        return errors;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorType }
     *     
     */
    public ErrorType setErrors(int idx, ErrorType value) {
        return this.errors.set(idx, value);
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the build property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuild() {
        return build;
    }

    /**
     * Sets the value of the build property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuild(String value) {
        this.build = value;
    }

    /**
     * Gets the value of the notificationEventName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotificationEventName() {
        return notificationEventName;
    }

    /**
     * Sets the value of the notificationEventName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotificationEventName(String value) {
        this.notificationEventName = value;
    }

    /**
     * Gets the value of the duplicateInvocationDetails property.
     * 
     * @return
     *     possible object is
     *     {@link DuplicateInvocationDetailsType }
     *     
     */
    public DuplicateInvocationDetailsType getDuplicateInvocationDetails() {
        return duplicateInvocationDetails;
    }

    /**
     * Sets the value of the duplicateInvocationDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link DuplicateInvocationDetailsType }
     *     
     */
    public void setDuplicateInvocationDetails(DuplicateInvocationDetailsType value) {
        this.duplicateInvocationDetails = value;
    }

    /**
     * Gets the value of the recipientUserID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecipientUserID() {
        return recipientUserID;
    }

    /**
     * Sets the value of the recipientUserID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecipientUserID(String value) {
        this.recipientUserID = value;
    }

    /**
     * Gets the value of the eiasToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEIASToken() {
        return eiasToken;
    }

    /**
     * Sets the value of the eiasToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEIASToken(String value) {
        this.eiasToken = value;
    }

    /**
     * Gets the value of the notificationSignature property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotificationSignature() {
        return notificationSignature;
    }

    /**
     * Sets the value of the notificationSignature property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotificationSignature(String value) {
        this.notificationSignature = value;
    }

    /**
     * Gets the value of the hardExpirationWarning property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHardExpirationWarning() {
        return hardExpirationWarning;
    }

    /**
     * Sets the value of the hardExpirationWarning property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHardExpirationWarning(String value) {
        this.hardExpirationWarning = value;
    }

    /**
     * Gets the value of the botBlock property.
     * 
     * @return
     *     possible object is
     *     {@link BotBlockResponseType }
     *     
     */
    public BotBlockResponseType getBotBlock() {
        return botBlock;
    }

    /**
     * Sets the value of the botBlock property.
     * 
     * @param value
     *     allowed object is
     *     {@link BotBlockResponseType }
     *     
     */
    public void setBotBlock(BotBlockResponseType value) {
        this.botBlock = value;
    }

    /**
     * Gets the value of the externalUserData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalUserData() {
        return externalUserData;
    }

    /**
     * Sets the value of the externalUserData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalUserData(String value) {
        this.externalUserData = value;
    }

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link Object }
     *     {@link Element }
     *     
     */
    public Object[] getAny() {
        if (this.any == null) {
            return new Object[ 0 ] ;
        }
        return ((Object[]) this.any.toArray(new Object[this.any.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link Object }
     *     {@link Element }
     *     
     */
    public Object getAny(int idx) {
        if (this.any == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.any.get(idx);
    }

    public int getAnyLength() {
        if (this.any == null) {
            return  0;
        }
        return this.any.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link Object }
     *     {@link Element }
     *     
     */
    public void setAny(Object[] values) {
        this._getAny().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.any.add(values[i]);
        }
    }

    protected List<Object> _getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return any;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     {@link Element }
     *     
     */
    public Object setAny(int idx, Object value) {
        return this.any.set(idx, value);
    }

}
