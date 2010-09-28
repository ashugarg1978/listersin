
package com.ebay.soap.eBLBaseComponents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * 
 * 				Base type definition of the request payload, which can carry any type of payload
 * 				content plus optional versioning information and detail level requirements. All
 * 				concrete request types (e.g., AddItemRequestType) are derived from the abstract
 * 				request type. The naming convention we use for the concrete type names is the name
 * 				of the service (the verb or call name) followed by "RequestType":
 * 				VerbNameRequestType
 * 			
 * 
 * <p>Java class for AbstractRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AbstractRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DetailLevel" type="{urn:ebay:apis:eBLBaseComponents}DetailLevelCodeType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ErrorLanguage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MessageID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EndUserIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ErrorHandling" type="{urn:ebay:apis:eBLBaseComponents}ErrorHandlingCodeType" minOccurs="0"/>
 *         &lt;element name="InvocationID" type="{urn:ebay:apis:eBLBaseComponents}UUIDType" minOccurs="0"/>
 *         &lt;element name="OutputSelector" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="WarningLevel" type="{urn:ebay:apis:eBLBaseComponents}WarningLevelCodeType" minOccurs="0"/>
 *         &lt;element name="BotBlock" type="{urn:ebay:apis:eBLBaseComponents}BotBlockRequestType" minOccurs="0"/>
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
@XmlType(name = "AbstractRequestType", propOrder = {
    "detailLevel",
    "errorLanguage",
    "messageID",
    "version",
    "endUserIP",
    "errorHandling",
    "invocationID",
    "outputSelector",
    "warningLevel",
    "botBlock",
    "any"
})
@XmlSeeAlso({
    GetItemsAwaitingFeedbackRequestType.class,
    EndItemRequestType.class,
    GetSearchResultsRequestType.class,
    RespondToWantItNowPostRequestType.class,
    ValidateChallengeInputRequestType.class,
    GetItemShippingRequestType.class,
    RelistFixedPriceItemRequestType.class,
    ReviseSellingManagerProductRequestType.class,
    GetBidderListRequestType.class,
    ReviseFixedPriceItemRequestType.class,
    GetPromotionalSaleDetailsRequestType.class,
    DeleteSellingManagerTemplateAutomationRuleRequestType.class,
    GetCategoryFeaturesRequestType.class,
    GetVeROReasonCodeDetailsRequestType.class,
    DeleteSellingManagerItemAutomationRuleRequestType.class,
    GetProductSearchPageRequestType.class,
    GetStoreOptionsRequestType.class,
    GetDescriptionTemplatesRequestType.class,
    EndFixedPriceItemRequestType.class,
    CompleteSaleRequestType.class,
    GetCategoriesRequestType.class,
    GetOrdersRequestType.class,
    SetUserPreferencesRequestType.class,
    SetStorePreferencesRequestType.class,
    GetSellerTransactionsRequestType.class,
    GeteBayDetailsRequestType.class,
    GetMyeBayBuyingRequestType.class,
    AddMemberMessageRTQRequestType.class,
    GetSellingManagerItemAutomationRuleRequestType.class,
    GetSellingManagerInventoryRequestType.class,
    EndItemsRequestType.class,
    GetSellingManagerEmailLogRequestType.class,
    GeteBayOfficialTimeRequestType.class,
    GetStoreRequestType.class,
    GetBestOffersRequestType.class,
    AddSecondChanceItemRequestType.class,
    SetStoreCustomPageRequestType.class,
    GetMyMessagesRequestType.class,
    PlaceOfferRequestType.class,
    GetNotificationPreferencesRequestType.class,
    ReviseInventoryStatusRequestType.class,
    GetAccountRequestType.class,
    LeaveFeedbackRequestType.class,
    GetUserRequestType.class,
    GetMyeBayRemindersRequestType.class,
    GetSellingManagerInventoryFolderRequestType.class,
    GetProductSearchResultsRequestType.class,
    GetSellingManagerAlertsRequestType.class,
    DeleteMyMessagesRequestType.class,
    GetShippingDiscountProfilesRequestType.class,
    GetPictureManagerDetailsRequestType.class,
    SellerReverseDisputeRequestType.class,
    ReviseItemRequestType.class,
    RevokeTokenRequestType.class,
    GetPromotionRulesRequestType.class,
    GetWantItNowSearchResultsRequestType.class,
    GetAttributesXSLRequestType.class,
    GetCategory2CSRequestType.class,
    SetPromotionalSaleListingsRequestType.class,
    SetPromotionalSaleRequestType.class,
    AddMemberMessagesAAQToBidderRequestType.class,
    GetAdFormatLeadsRequestType.class,
    ReviseSellingManagerInventoryFolderRequestType.class,
    ReviseCheckoutStatusRequestType.class,
    GetSellerListRequestType.class,
    FetchTokenRequestType.class,
    GetHighBiddersRequestType.class,
    GetCategorySpecificsRequestType.class,
    GetSuggestedCategoriesRequestType.class,
    GetCategoryListingsRequestType.class,
    AddDisputeRequestType.class,
    GetSellerEventsRequestType.class,
    GetSellerDashboardRequestType.class,
    GetAllBiddersRequestType.class,
    GetTaxTableRequestType.class,
    ReviseMyMessagesFoldersRequestType.class,
    MoveSellingManagerInventoryFolderRequestType.class,
    GetCharitiesRequestType.class,
    AddSellingManagerInventoryFolderRequestType.class,
    GetProductFinderXSLRequestType.class,
    DeleteSellingManagerInventoryFolderRequestType.class,
    GetItemRequestType.class,
    SetNotificationPreferencesRequestType.class,
    GetChallengeTokenRequestType.class,
    GetCategoryMappingsRequestType.class,
    GetProductSellingPagesRequestType.class,
    GetDisputeRequestType.class,
    AddItemRequestType.class,
    GetCrossPromotionsRequestType.class,
    GetStoreCategoryUpdateStatusRequestType.class,
    ReviseSellingManagerSaleRecordRequestType.class,
    GetProductFamilyMembersRequestType.class,
    ExtendSiteHostedPicturesRequestType.class,
    GetOrderTransactionsRequestType.class,
    AddFixedPriceItemRequestType.class,
    ValidateTestUserRegistrationRequestType.class,
    SetSellingManagerFeedbackOptionsRequestType.class,
    AddSellingManagerTemplateRequestType.class,
    GetUserContactDetailsRequestType.class,
    SetMessagePreferencesRequestType.class,
    GetFeedbackRequestType.class,
    GetApiAccessRulesRequestType.class,
    GetContextualKeywordsRequestType.class,
    SetStoreRequestType.class,
    GetMemberMessagesRequestType.class,
    AddMemberMessageAAQToPartnerRequestType.class,
    AddItemFromSellingManagerTemplateRequestType.class,
    GetStoreCustomPageRequestType.class,
    GetPictureManagerOptionsRequestType.class,
    GetPopularKeywordsRequestType.class,
    VerifyAddSecondChanceItemRequestType.class,
    GetSellerPaymentsRequestType.class,
    DisableUnpaidItemAssistanceRequestType.class,
    AddItemsRequestType.class,
    GetVeROReportStatusRequestType.class,
    SetTaxTableRequestType.class,
    AddToItemDescriptionRequestType.class,
    ReviseSellingManagerTemplateRequestType.class,
    GetSessionIDRequestType.class,
    AddTransactionConfirmationItemRequestType.class,
    ConfirmIdentityRequestType.class,
    SetStoreCategoriesRequestType.class,
    GetUserPreferencesRequestType.class,
    RemoveFromWatchListRequestType.class,
    DeleteSellingManagerProductRequestType.class,
    SetUserNotesRequestType.class,
    GetProductFinderRequestType.class,
    SendInvoiceRequestType.class,
    GetClientAlertsAuthTokenRequestType.class,
    GetSellingManagerTemplateAutomationRuleRequestType.class,
    GetSellingManagerSaleRecordRequestType.class,
    AddOrderRequestType.class,
    SetSellingManagerItemAutomationRuleRequestType.class,
    GetUserDisputesRequestType.class,
    RespondToBestOfferRequestType.class,
    GetProductsRequestType.class,
    UploadSiteHostedPicturesRequestType.class,
    RespondToFeedbackRequestType.class,
    VeROReportItemsRequestType.class,
    ReviseMyMessagesRequestType.class,
    GetAttributesCSRequestType.class,
    AddToWatchListRequestType.class,
    SetShippingDiscountProfilesRequestType.class,
    VerifyAddFixedPriceItemRequestType.class,
    SetPictureManagerDetailsRequestType.class,
    DeleteSellingManagerTemplateRequestType.class,
    VerifyRelistItemRequestType.class,
    GetStorePreferencesRequestType.class,
    AddSellingManagerProductRequestType.class,
    GetMessagePreferencesRequestType.class,
    GetItemTransactionsRequestType.class,
    AddDisputeResponseRequestType.class,
    GetMyeBaySellingRequestType.class,
    GetSellingManagerSoldListingsRequestType.class,
    GetItemRecommendationsRequestType.class,
    GetSellingManagerTemplatesRequestType.class,
    GetWantItNowPostRequestType.class,
    VerifyAddItemRequestType.class,
    GetTokenStatusRequestType.class,
    GetNotificationsUsageRequestType.class,
    SaveItemToSellingManagerTemplateRequestType.class,
    SetSellingManagerTemplateAutomationRuleRequestType.class,
    RelistItemRequestType.class,
    IssueRefundRequestType.class
})
public abstract class AbstractRequestType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "DetailLevel")
    protected List<DetailLevelCodeType> detailLevel;
    @XmlElement(name = "ErrorLanguage")
    protected String errorLanguage;
    @XmlElement(name = "MessageID")
    protected String messageID;
    @XmlElement(name = "Version")
    protected String version;
    @XmlElement(name = "EndUserIP")
    protected String endUserIP;
    @XmlElement(name = "ErrorHandling")
    protected ErrorHandlingCodeType errorHandling;
    @XmlElement(name = "InvocationID")
    protected String invocationID;
    @XmlElement(name = "OutputSelector")
    protected List<String> outputSelector;
    @XmlElement(name = "WarningLevel")
    protected WarningLevelCodeType warningLevel;
    @XmlElement(name = "BotBlock")
    protected BotBlockRequestType botBlock;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link DetailLevelCodeType }
     *     
     */
    public DetailLevelCodeType[] getDetailLevel() {
        if (this.detailLevel == null) {
            return new DetailLevelCodeType[ 0 ] ;
        }
        return ((DetailLevelCodeType[]) this.detailLevel.toArray(new DetailLevelCodeType[this.detailLevel.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link DetailLevelCodeType }
     *     
     */
    public DetailLevelCodeType getDetailLevel(int idx) {
        if (this.detailLevel == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.detailLevel.get(idx);
    }

    public int getDetailLevelLength() {
        if (this.detailLevel == null) {
            return  0;
        }
        return this.detailLevel.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link DetailLevelCodeType }
     *     
     */
    public void setDetailLevel(DetailLevelCodeType[] values) {
        this._getDetailLevel().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.detailLevel.add(values[i]);
        }
    }

    protected List<DetailLevelCodeType> _getDetailLevel() {
        if (detailLevel == null) {
            detailLevel = new ArrayList<DetailLevelCodeType>();
        }
        return detailLevel;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link DetailLevelCodeType }
     *     
     */
    public DetailLevelCodeType setDetailLevel(int idx, DetailLevelCodeType value) {
        return this.detailLevel.set(idx, value);
    }

    /**
     * Gets the value of the errorLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorLanguage() {
        return errorLanguage;
    }

    /**
     * Sets the value of the errorLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorLanguage(String value) {
        this.errorLanguage = value;
    }

    /**
     * Gets the value of the messageID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageID() {
        return messageID;
    }

    /**
     * Sets the value of the messageID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageID(String value) {
        this.messageID = value;
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
     * Gets the value of the endUserIP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndUserIP() {
        return endUserIP;
    }

    /**
     * Sets the value of the endUserIP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndUserIP(String value) {
        this.endUserIP = value;
    }

    /**
     * Gets the value of the errorHandling property.
     * 
     * @return
     *     possible object is
     *     {@link ErrorHandlingCodeType }
     *     
     */
    public ErrorHandlingCodeType getErrorHandling() {
        return errorHandling;
    }

    /**
     * Sets the value of the errorHandling property.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorHandlingCodeType }
     *     
     */
    public void setErrorHandling(ErrorHandlingCodeType value) {
        this.errorHandling = value;
    }

    /**
     * Gets the value of the invocationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvocationID() {
        return invocationID;
    }

    /**
     * Sets the value of the invocationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvocationID(String value) {
        this.invocationID = value;
    }

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link String }
     *     
     */
    public String[] getOutputSelector() {
        if (this.outputSelector == null) {
            return new String[ 0 ] ;
        }
        return ((String[]) this.outputSelector.toArray(new String[this.outputSelector.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link String }
     *     
     */
    public String getOutputSelector(int idx) {
        if (this.outputSelector == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.outputSelector.get(idx);
    }

    public int getOutputSelectorLength() {
        if (this.outputSelector == null) {
            return  0;
        }
        return this.outputSelector.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link String }
     *     
     */
    public void setOutputSelector(String[] values) {
        this._getOutputSelector().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.outputSelector.add(values[i]);
        }
    }

    protected List<String> _getOutputSelector() {
        if (outputSelector == null) {
            outputSelector = new ArrayList<String>();
        }
        return outputSelector;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public String setOutputSelector(int idx, String value) {
        return this.outputSelector.set(idx, value);
    }

    /**
     * Gets the value of the warningLevel property.
     * 
     * @return
     *     possible object is
     *     {@link WarningLevelCodeType }
     *     
     */
    public WarningLevelCodeType getWarningLevel() {
        return warningLevel;
    }

    /**
     * Sets the value of the warningLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link WarningLevelCodeType }
     *     
     */
    public void setWarningLevel(WarningLevelCodeType value) {
        this.warningLevel = value;
    }

    /**
     * Gets the value of the botBlock property.
     * 
     * @return
     *     possible object is
     *     {@link BotBlockRequestType }
     *     
     */
    public BotBlockRequestType getBotBlock() {
        return botBlock;
    }

    /**
     * Sets the value of the botBlock property.
     * 
     * @param value
     *     allowed object is
     *     {@link BotBlockRequestType }
     *     
     */
    public void setBotBlock(BotBlockRequestType value) {
        this.botBlock = value;
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
