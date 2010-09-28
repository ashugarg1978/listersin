
package com.ebay.soap.eBLBaseComponents;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Retrieves the specified user preferences for the authenticated caller.
 * 			
 * 
 * <p>Java class for GetUserPreferencesRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetUserPreferencesRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractRequestType">
 *       &lt;sequence>
 *         &lt;element name="ShowBidderNoticePreferences" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ShowCombinedPaymentPreferences" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ShowCrossPromotionPreferences" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ShowSellerPaymentPreferences" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ShowEndOfAuctionEmailPreferences" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ShowSellerFavoriteItemPreferences" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ShowProStoresPreferences" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ShowEmailShipmentTrackingNumberPreference" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ShowSellerExcludeShipToLocationPreference" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ShowUnpaidItemAssistancePreference" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ShowPurchaseReminderEmailPreferences" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetUserPreferencesRequestType", propOrder = {
    "showBidderNoticePreferences",
    "showCombinedPaymentPreferences",
    "showCrossPromotionPreferences",
    "showSellerPaymentPreferences",
    "showEndOfAuctionEmailPreferences",
    "showSellerFavoriteItemPreferences",
    "showProStoresPreferences",
    "showEmailShipmentTrackingNumberPreference",
    "showSellerExcludeShipToLocationPreference",
    "showUnpaidItemAssistancePreference",
    "showPurchaseReminderEmailPreferences"
})
public class GetUserPreferencesRequestType
    extends AbstractRequestType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "ShowBidderNoticePreferences")
    protected boolean showBidderNoticePreferences;
    @XmlElement(name = "ShowCombinedPaymentPreferences")
    protected boolean showCombinedPaymentPreferences;
    @XmlElement(name = "ShowCrossPromotionPreferences")
    protected boolean showCrossPromotionPreferences;
    @XmlElement(name = "ShowSellerPaymentPreferences")
    protected boolean showSellerPaymentPreferences;
    @XmlElement(name = "ShowEndOfAuctionEmailPreferences")
    protected Boolean showEndOfAuctionEmailPreferences;
    @XmlElement(name = "ShowSellerFavoriteItemPreferences")
    protected Boolean showSellerFavoriteItemPreferences;
    @XmlElement(name = "ShowProStoresPreferences")
    protected Boolean showProStoresPreferences;
    @XmlElement(name = "ShowEmailShipmentTrackingNumberPreference")
    protected Boolean showEmailShipmentTrackingNumberPreference;
    @XmlElement(name = "ShowSellerExcludeShipToLocationPreference")
    protected Boolean showSellerExcludeShipToLocationPreference;
    @XmlElement(name = "ShowUnpaidItemAssistancePreference")
    protected Boolean showUnpaidItemAssistancePreference;
    @XmlElement(name = "ShowPurchaseReminderEmailPreferences")
    protected Boolean showPurchaseReminderEmailPreferences;

    /**
     * Gets the value of the showBidderNoticePreferences property.
     * 
     */
    public boolean isShowBidderNoticePreferences() {
        return showBidderNoticePreferences;
    }

    /**
     * Sets the value of the showBidderNoticePreferences property.
     * 
     */
    public void setShowBidderNoticePreferences(boolean value) {
        this.showBidderNoticePreferences = value;
    }

    /**
     * Gets the value of the showCombinedPaymentPreferences property.
     * 
     */
    public boolean isShowCombinedPaymentPreferences() {
        return showCombinedPaymentPreferences;
    }

    /**
     * Sets the value of the showCombinedPaymentPreferences property.
     * 
     */
    public void setShowCombinedPaymentPreferences(boolean value) {
        this.showCombinedPaymentPreferences = value;
    }

    /**
     * Gets the value of the showCrossPromotionPreferences property.
     * 
     */
    public boolean isShowCrossPromotionPreferences() {
        return showCrossPromotionPreferences;
    }

    /**
     * Sets the value of the showCrossPromotionPreferences property.
     * 
     */
    public void setShowCrossPromotionPreferences(boolean value) {
        this.showCrossPromotionPreferences = value;
    }

    /**
     * Gets the value of the showSellerPaymentPreferences property.
     * 
     */
    public boolean isShowSellerPaymentPreferences() {
        return showSellerPaymentPreferences;
    }

    /**
     * Sets the value of the showSellerPaymentPreferences property.
     * 
     */
    public void setShowSellerPaymentPreferences(boolean value) {
        this.showSellerPaymentPreferences = value;
    }

    /**
     * Gets the value of the showEndOfAuctionEmailPreferences property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowEndOfAuctionEmailPreferences() {
        return showEndOfAuctionEmailPreferences;
    }

    /**
     * Sets the value of the showEndOfAuctionEmailPreferences property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowEndOfAuctionEmailPreferences(Boolean value) {
        this.showEndOfAuctionEmailPreferences = value;
    }

    /**
     * Gets the value of the showSellerFavoriteItemPreferences property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowSellerFavoriteItemPreferences() {
        return showSellerFavoriteItemPreferences;
    }

    /**
     * Sets the value of the showSellerFavoriteItemPreferences property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowSellerFavoriteItemPreferences(Boolean value) {
        this.showSellerFavoriteItemPreferences = value;
    }

    /**
     * Gets the value of the showProStoresPreferences property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowProStoresPreferences() {
        return showProStoresPreferences;
    }

    /**
     * Sets the value of the showProStoresPreferences property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowProStoresPreferences(Boolean value) {
        this.showProStoresPreferences = value;
    }

    /**
     * Gets the value of the showEmailShipmentTrackingNumberPreference property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowEmailShipmentTrackingNumberPreference() {
        return showEmailShipmentTrackingNumberPreference;
    }

    /**
     * Sets the value of the showEmailShipmentTrackingNumberPreference property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowEmailShipmentTrackingNumberPreference(Boolean value) {
        this.showEmailShipmentTrackingNumberPreference = value;
    }

    /**
     * Gets the value of the showSellerExcludeShipToLocationPreference property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowSellerExcludeShipToLocationPreference() {
        return showSellerExcludeShipToLocationPreference;
    }

    /**
     * Sets the value of the showSellerExcludeShipToLocationPreference property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowSellerExcludeShipToLocationPreference(Boolean value) {
        this.showSellerExcludeShipToLocationPreference = value;
    }

    /**
     * Gets the value of the showUnpaidItemAssistancePreference property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowUnpaidItemAssistancePreference() {
        return showUnpaidItemAssistancePreference;
    }

    /**
     * Sets the value of the showUnpaidItemAssistancePreference property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowUnpaidItemAssistancePreference(Boolean value) {
        this.showUnpaidItemAssistancePreference = value;
    }

    /**
     * Gets the value of the showPurchaseReminderEmailPreferences property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShowPurchaseReminderEmailPreferences() {
        return showPurchaseReminderEmailPreferences;
    }

    /**
     * Sets the value of the showPurchaseReminderEmailPreferences property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShowPurchaseReminderEmailPreferences(Boolean value) {
        this.showPurchaseReminderEmailPreferences = value;
    }

}
