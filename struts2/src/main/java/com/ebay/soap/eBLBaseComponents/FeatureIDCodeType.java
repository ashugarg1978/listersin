
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FeatureIDCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FeatureIDCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="ListingDurations"/>
 *     &lt;enumeration value="BestOfferEnabled"/>
 *     &lt;enumeration value="DutchBINEnabled"/>
 *     &lt;enumeration value="ShippingTermsRequired"/>
 *     &lt;enumeration value="UserConsentRequired"/>
 *     &lt;enumeration value="HomePageFeaturedEnabled"/>
 *     &lt;enumeration value="AdFormatEnabled"/>
 *     &lt;enumeration value="DigitalDeliveryEnabled"/>
 *     &lt;enumeration value="BestOfferCounterEnabled"/>
 *     &lt;enumeration value="BestOfferAutoDeclineEnabled"/>
 *     &lt;enumeration value="ProPack"/>
 *     &lt;enumeration value="BasicUpgradePack"/>
 *     &lt;enumeration value="ValuePack"/>
 *     &lt;enumeration value="ProPackPlus"/>
 *     &lt;enumeration value="LocalMarketSpecialitySubscription"/>
 *     &lt;enumeration value="LocalMarketRegularSubscription"/>
 *     &lt;enumeration value="LocalMarketPremiumSubscription"/>
 *     &lt;enumeration value="LocalMarketNonSubscription"/>
 *     &lt;enumeration value="ExpressEnabled"/>
 *     &lt;enumeration value="ExpressPicturesRequired"/>
 *     &lt;enumeration value="ExpressConditionRequired"/>
 *     &lt;enumeration value="SellerContactDetailsEnabled"/>
 *     &lt;enumeration value="CustomCode"/>
 *     &lt;enumeration value="MinimumReservePrice"/>
 *     &lt;enumeration value="TransactionConfirmationRequestEnabled"/>
 *     &lt;enumeration value="StoreInventoryEnabled"/>
 *     &lt;enumeration value="LocalListingDistances"/>
 *     &lt;enumeration value="SkypeMeTransactionalEnabled"/>
 *     &lt;enumeration value="SkypeMeNonTransactionalEnabled"/>
 *     &lt;enumeration value="ClassifiedAdPaymentMethodEnabled"/>
 *     &lt;enumeration value="ClassifiedAdShippingMethodEnabled"/>
 *     &lt;enumeration value="ClassifiedAdBestOfferEnabled"/>
 *     &lt;enumeration value="ClassifiedAdCounterOfferEnabled"/>
 *     &lt;enumeration value="ClassifiedAdAutoDeclineEnabled"/>
 *     &lt;enumeration value="ClassifiedAdContactByEmailEnabled"/>
 *     &lt;enumeration value="ClassifiedAdContactByPhoneEnabled"/>
 *     &lt;enumeration value="SafePaymentRequired"/>
 *     &lt;enumeration value="MaximumBestOffersAllowed"/>
 *     &lt;enumeration value="ClassifiedAdMaximumBestOffersAllowed"/>
 *     &lt;enumeration value="ClassifiedAdContactByEmailAvailable"/>
 *     &lt;enumeration value="ClassifiedAdPayPerLeadEnabled"/>
 *     &lt;enumeration value="ItemSpecificsEnabled"/>
 *     &lt;enumeration value="PaisaPayFullEscrowEnabled"/>
 *     &lt;enumeration value="ClassifiedAdAutoAcceptEnabled"/>
 *     &lt;enumeration value="BestOfferAutoAcceptEnabled"/>
 *     &lt;enumeration value="CrossBorderTradeEnabled"/>
 *     &lt;enumeration value="PayPalBuyerProtectionEnabled"/>
 *     &lt;enumeration value="BuyerGuaranteeEnabled"/>
 *     &lt;enumeration value="INEscrowWorkflowTimeline"/>
 *     &lt;enumeration value="CombinedFixedPriceTreatment"/>
 *     &lt;enumeration value="GalleryFeaturedDurations"/>
 *     &lt;enumeration value="PayPalRequired"/>
 *     &lt;enumeration value="eBayMotorsProAdFormatEnabled"/>
 *     &lt;enumeration value="eBayMotorsProContactByPhoneEnabled"/>
 *     &lt;enumeration value="eBayMotorsProContactByAddressEnabled"/>
 *     &lt;enumeration value="eBayMotorsProCompanyNameEnabled"/>
 *     &lt;enumeration value="eBayMotorsProContactByEmailEnabled"/>
 *     &lt;enumeration value="eBayMotorsProBestOfferEnabled"/>
 *     &lt;enumeration value="eBayMotorsProAutoAcceptEnabled"/>
 *     &lt;enumeration value="eBayMotorsProAutoDeclineEnabled"/>
 *     &lt;enumeration value="eBayMotorsProPaymentMethodCheckOutEnabled"/>
 *     &lt;enumeration value="eBayMotorsProShippingMethodEnabled"/>
 *     &lt;enumeration value="eBayMotorsProCounterOfferEnabled"/>
 *     &lt;enumeration value="eBayMotorsProSellerContactDetailsEnabled"/>
 *     &lt;enumeration value="LocalMarketAdFormatEnabled"/>
 *     &lt;enumeration value="LocalMarketContactByPhoneEnabled"/>
 *     &lt;enumeration value="LocalMarketContactByAddressEnabled"/>
 *     &lt;enumeration value="LocalMarketCompanyNameEnabled"/>
 *     &lt;enumeration value="LocalMarketContactByEmailEnabled"/>
 *     &lt;enumeration value="LocalMarketBestOfferEnabled"/>
 *     &lt;enumeration value="LocalMarketAutoAcceptEnabled"/>
 *     &lt;enumeration value="LocalMarketAutoDeclineEnabled"/>
 *     &lt;enumeration value="LocalMarketPaymentMethodCheckOutEnabled"/>
 *     &lt;enumeration value="LocalMarketShippingMethodEnabled"/>
 *     &lt;enumeration value="LocalMarketCounterOfferEnabled"/>
 *     &lt;enumeration value="LocalMarketSellerContactDetailsEnabled"/>
 *     &lt;enumeration value="ClassifiedAdContactByAddressEnabled"/>
 *     &lt;enumeration value="ClassifiedAdCompanyNameEnabled"/>
 *     &lt;enumeration value="SpecialitySubscription"/>
 *     &lt;enumeration value="RegularSubscription"/>
 *     &lt;enumeration value="PremiumSubscription"/>
 *     &lt;enumeration value="NonSubscription"/>
 *     &lt;enumeration value="IntangibleEnabled"/>
 *     &lt;enumeration value="PayPalRequiredForStoreOwner"/>
 *     &lt;enumeration value="ReviseQuantityAllowed"/>
 *     &lt;enumeration value="RevisePriceAllowed"/>
 *     &lt;enumeration value="StoreOwnerExtendedListingDurationsEnabled"/>
 *     &lt;enumeration value="StoreOwnerExtendedListingDurations"/>
 *     &lt;enumeration value="ReturnPolicyEnabled"/>
 *     &lt;enumeration value="HandlingTimeEnabled"/>
 *     &lt;enumeration value="PaymentMethods"/>
 *     &lt;enumeration value="MaxFlatShippingCost"/>
 *     &lt;enumeration value="MaxFlatShippingCostCBTExempt"/>
 *     &lt;enumeration value="Group1MaxFlatShippingCost"/>
 *     &lt;enumeration value="Group2MaxFlatShippingCost"/>
 *     &lt;enumeration value="Group3MaxFlatShippingCost"/>
 *     &lt;enumeration value="VariationsEnabled"/>
 *     &lt;enumeration value="AttributeConversionEnabled"/>
 *     &lt;enumeration value="FreeGalleryPlusEnabled"/>
 *     &lt;enumeration value="FreePicturePackEnabled"/>
 *     &lt;enumeration value="CompatibilityEnabled"/>
 *     &lt;enumeration value="MinCompatibleApplications"/>
 *     &lt;enumeration value="MaxCompatibleApplications"/>
 *     &lt;enumeration value="ConditionEnabled"/>
 *     &lt;enumeration value="ConditionValues"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FeatureIDCodeType")
@XmlEnum
public enum FeatureIDCodeType {


    /**
     * 
     * 						Returns information about the durations of listings you can use in a
     * 						given category.
     * 						<br><br>
     * 						<bold>Note:</bold> Durations for Local Market listings are not
     * 						supported by GetCategoryFeatures. Refer to the Local Market Listing
     * 						documentation in the
     * 						Web Services Guide for valid listing durations.
     * 					
     * 
     */
    @XmlEnumValue("ListingDurations")
    LISTING_DURATIONS("ListingDurations"),

    /**
     * 
     * 						Returns information about whether a category supports best offers.
     * 					
     * 
     */
    @XmlEnumValue("BestOfferEnabled")
    BEST_OFFER_ENABLED("BestOfferEnabled"),

    /**
     * 
     * 						Returned if a site and category allow Buy It Now for Dutch auctions.
     * 						<br><br>
     * 						<span class="tablenote"><strong>Note:</strong>
     * 							As of version 619, Dutch-style (multi-item) competitive-bid auctions are deprecated.
     * 							eBay throws an error if you submit a Dutch item listing with AddItem
     * 							or VerifyAddItem. If you use RelistItem to update a Dutch auction listing,
     * 							eBay generates a warning and resets the Quantity value to 1.
     * 						</span>
     * 						<br>
     * 					
     * 
     */
    @XmlEnumValue("DutchBINEnabled")
    DUTCH_BIN_ENABLED("DutchBINEnabled"),

    /**
     * 
     * 						Returns information about whether a seller is required to
     * 						specify at least one domestic shipping
     * 						service and associated cost in order to list the item.
     * 					
     * 
     */
    @XmlEnumValue("ShippingTermsRequired")
    SHIPPING_TERMS_REQUIRED("ShippingTermsRequired"),

    /**
     * 
     * 						Returns information about whether a bidder must consent to the bid
     * 						by confirming that he or she read and agrees to the terms in eBay's privacy policy.
     * 					
     * 
     */
    @XmlEnumValue("UserConsentRequired")
    USER_CONSENT_REQUIRED("UserConsentRequired"),

    /**
     * 
     * 						Indicates whether or not it is possible to enhance a listing by putting
     * 						it into a rotation for display on a special area of the eBay home page.
     * 						Support for this feature varies by site. Item or feedback restrictions may apply.
     * 					
     * 
     */
    @XmlEnumValue("HomePageFeaturedEnabled")
    HOME_PAGE_FEATURED_ENABLED("HomePageFeaturedEnabled"),

    /**
     * 
     * 						Indicates whether a category supports ad-format.
     * 					
     * 
     */
    @XmlEnumValue("AdFormatEnabled")
    AD_FORMAT_ENABLED("AdFormatEnabled"),

    /**
     * 
     * 						Returns information about whether a category supports digital delivery items. The
     * 						digital delivery feature has been disabled.
     * 					
     * 
     */
    @XmlEnumValue("DigitalDeliveryEnabled")
    DIGITAL_DELIVERY_ENABLED("DigitalDeliveryEnabled"),

    /**
     * 
     * 						Returns information about whether a category supports counter-offers for Best Offers.
     * 					
     * 
     */
    @XmlEnumValue("BestOfferCounterEnabled")
    BEST_OFFER_COUNTER_ENABLED("BestOfferCounterEnabled"),

    /**
     * 
     * 						Returns information about whether a category supports auto-decline for Best Offers.
     * 					
     * 
     */
    @XmlEnumValue("BestOfferAutoDeclineEnabled")
    BEST_OFFER_AUTO_DECLINE_ENABLED("BestOfferAutoDeclineEnabled"),

    /**
     * 
     * 						Returns information about ProPackBundle (a feature pack). Applies only to US
     * 						and Canadian eBay motor vehicle sellers. Contains the Bold, Border, Featured
     * 						and Highlight features.
     * 					
     * 
     */
    @XmlEnumValue("ProPack")
    PRO_PACK("ProPack"),

    /**
     * 
     * 						Not applicable to any site.
     * 						Formerly, Australia site (site ID 15, abbreviation AU) only, and
     * 						returned information about the BasicUpgradePack bundle (a feature pack),
     * 						which combined the features Gallery and Subtitle for a discounted price.
     * 					
     * 
     */
    @XmlEnumValue("BasicUpgradePack")
    BASIC_UPGRADE_PACK("BasicUpgradePack"),

    /**
     * 
     * 						Returns information about the ValuePack bundle (a feature pack), which
     * 						combines the features Gallery, Subtitle, and Listing Designer for a discounted
     * 						price. Support for this feature varies by site and category.
     * 					
     * 
     */
    @XmlEnumValue("ValuePack")
    VALUE_PACK("ValuePack"),

    /**
     * 
     * 						Returns information about the ProPackPlus bundle (a feature pack), which
     * 						combines the features BoldTitle, Border, Highlight, Featured, and Gallery for
     * 						a discounted price. Support for this feature varies by site and category.
     * 					
     * 
     */
    @XmlEnumValue("ProPackPlus")
    PRO_PACK_PLUS("ProPackPlus"),

    /**
     * 
     * 						Returns information about whether a category supports Local Market listings
     * 						for sellers subscribed to Local Market for Specialty Vehicles.
     * 					
     * 
     */
    @XmlEnumValue("LocalMarketSpecialitySubscription")
    LOCAL_MARKET_SPECIALITY_SUBSCRIPTION("LocalMarketSpecialitySubscription"),

    /**
     * 
     * 						Returns information about whether a category supports Local Market listings
     * 						for sellers subscribed to Local Market for Vehicles.
     * 					
     * 
     */
    @XmlEnumValue("LocalMarketRegularSubscription")
    LOCAL_MARKET_REGULAR_SUBSCRIPTION("LocalMarketRegularSubscription"),

    /**
     * 
     * 						Returns information about whether a category supports Local Market listings
     * 						for sellers with a premium Local Market subscription.
     * 					
     * 
     */
    @XmlEnumValue("LocalMarketPremiumSubscription")
    LOCAL_MARKET_PREMIUM_SUBSCRIPTION("LocalMarketPremiumSubscription"),

    /**
     * 
     * 						Returns information about whether a category supports Local Market listings
     * 						for sellers without a Local Market subscription.
     * 					
     * 
     */
    @XmlEnumValue("LocalMarketNonSubscription")
    LOCAL_MARKET_NON_SUBSCRIPTION("LocalMarketNonSubscription"),

    /**
     * 
     * 						Returns information about whether a category is eligible for eBay Express.
     * 					
     * 
     */
    @XmlEnumValue("ExpressEnabled")
    EXPRESS_ENABLED("ExpressEnabled"),

    /**
     * 
     * 						Returns information about whether a category requires pictures to be listed on Express.
     * 					
     * 
     */
    @XmlEnumValue("ExpressPicturesRequired")
    EXPRESS_PICTURES_REQUIRED("ExpressPicturesRequired"),

    /**
     * 
     * 						Returns information about whether a category requires item condition to be
     * 						listed on Express.
     * 					
     * 
     */
    @XmlEnumValue("ExpressConditionRequired")
    EXPRESS_CONDITION_REQUIRED("ExpressConditionRequired"),

    /**
     * 
     * 						Returns information about whether a category allows seller-level
     * 						contact information for Classified Ad listings.
     * 					
     * 
     */
    @XmlEnumValue("SellerContactDetailsEnabled")
    SELLER_CONTACT_DETAILS_ENABLED("SellerContactDetailsEnabled"),

    /**
     * 
     * 						(out) Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode"),

    /**
     * 
     * 						Returns information about whether a category supports Minimum Reserve Price.
     * 					
     * 
     */
    @XmlEnumValue("MinimumReservePrice")
    MINIMUM_RESERVE_PRICE("MinimumReservePrice"),

    /**
     * 
     * 						Returns information about whether a category supports the Transaction
     * 						Confirmation Request feature.
     * 					
     * 
     */
    @XmlEnumValue("TransactionConfirmationRequestEnabled")
    TRANSACTION_CONFIRMATION_REQUEST_ENABLED("TransactionConfirmationRequestEnabled"),

    /**
     * 
     * 						Indicates whether the category supports Store Inventory Format.
     * 					
     * 
     */
    @XmlEnumValue("StoreInventoryEnabled")
    STORE_INVENTORY_ENABLED("StoreInventoryEnabled"),

    /**
     * 
     * 						Returns information about the supported local listing distances for different
     * 						levels of Local Market subscription.
     * 					
     * 
     */
    @XmlEnumValue("LocalListingDistances")
    LOCAL_LISTING_DISTANCES("LocalListingDistances"),

    /**
     * 
     * 						Returns information about whether a category supports the addition of Skype
     * 						buttons to listings for transactional formats (e.g., the Chinese auction
     * 						format).
     * 					
     * 
     */
    @XmlEnumValue("SkypeMeTransactionalEnabled")
    SKYPE_ME_TRANSACTIONAL_ENABLED("SkypeMeTransactionalEnabled"),

    /**
     * 
     * 						Returns information about whether a category supports the addition of Skype
     * 						buttons to listings for non-transactional formats (e.g., the advertisement
     * 						format).
     * 					
     * 
     */
    @XmlEnumValue("SkypeMeNonTransactionalEnabled")
    SKYPE_ME_NON_TRANSACTIONAL_ENABLED("SkypeMeNonTransactionalEnabled"),

    /**
     * 
     * 						Returns information about whether the payment method is displayed
     * 						to the user for Classified Ad listings in a category.
     * 						Even if payment method is displayed, checkout may or may not be enabled.
     * 					
     * 
     */
    @XmlEnumValue("ClassifiedAdPaymentMethodEnabled")
    CLASSIFIED_AD_PAYMENT_METHOD_ENABLED("ClassifiedAdPaymentMethodEnabled"),

    /**
     * 
     * 						Returns information about whether shipping options are available for
     * 						Classified Ad listings in this category.
     * 					
     * 
     */
    @XmlEnumValue("ClassifiedAdShippingMethodEnabled")
    CLASSIFIED_AD_SHIPPING_METHOD_ENABLED("ClassifiedAdShippingMethodEnabled"),

    /**
     * 
     * 						Returns information about whether Best Offer is enabled for Classified
     * 						Ad listings.
     * 					
     * 
     */
    @XmlEnumValue("ClassifiedAdBestOfferEnabled")
    CLASSIFIED_AD_BEST_OFFER_ENABLED("ClassifiedAdBestOfferEnabled"),

    /**
     * 
     * 						Returns information about whether counter offers are allowed on
     * 						Classified Ad listings for a category.
     * 						Returned only if this category overrides the site default.
     * 					
     * 
     */
    @XmlEnumValue("ClassifiedAdCounterOfferEnabled")
    CLASSIFIED_AD_COUNTER_OFFER_ENABLED("ClassifiedAdCounterOfferEnabled"),

    /**
     * 
     * 						Returns information about whether automatic decline for best
     * 						offers are allowed for Classified Ad listings in a category.
     * 						Returned only if this category overrides the site default.
     * 					
     * 
     */
    @XmlEnumValue("ClassifiedAdAutoDeclineEnabled")
    CLASSIFIED_AD_AUTO_DECLINE_ENABLED("ClassifiedAdAutoDeclineEnabled"),

    /**
     * 
     * 						Returns information about whether a category supports including an email
     * 						address in the seller's contact information.
     * 					
     * 
     */
    @XmlEnumValue("ClassifiedAdContactByEmailEnabled")
    CLASSIFIED_AD_CONTACT_BY_EMAIL_ENABLED("ClassifiedAdContactByEmailEnabled"),

    /**
     * 
     * 						Returns information about whether a category supports including a telephone
     * 						number in the seller's contact information.
     * 					
     * 
     */
    @XmlEnumValue("ClassifiedAdContactByPhoneEnabled")
    CLASSIFIED_AD_CONTACT_BY_PHONE_ENABLED("ClassifiedAdContactByPhoneEnabled"),

    /**
     * 
     * 						Returns information about whether a category requires
     * 						a safe payment method. If a seller has a 'SafePaymentExempt'
     * 						status (see GetUser), they are exempt from the requirement to offer at least
     * 						one safe payment method when listing an item.
     * 					
     * 
     */
    @XmlEnumValue("SafePaymentRequired")
    SAFE_PAYMENT_REQUIRED("SafePaymentRequired"),

    /**
     * 
     * 						Returns information about the number of Best Offers
     * 						allowed for a non-Ad-Format-Listings buyer.
     * 					
     * 
     */
    @XmlEnumValue("MaximumBestOffersAllowed")
    MAXIMUM_BEST_OFFERS_ALLOWED("MaximumBestOffersAllowed"),

    /**
     * 
     * 						Returns information about the number of Best Offers
     * 						allowed for a Classified-Ad-Format-Listings buyer.
     * 					
     * 
     */
    @XmlEnumValue("ClassifiedAdMaximumBestOffersAllowed")
    CLASSIFIED_AD_MAXIMUM_BEST_OFFERS_ALLOWED("ClassifiedAdMaximumBestOffersAllowed"),

    /**
     * 
     * 						Returns information about whether a category supports including an email
     * 						address in the seller's contact information for classified-ad listings.
     * 					
     * 
     */
    @XmlEnumValue("ClassifiedAdContactByEmailAvailable")
    CLASSIFIED_AD_CONTACT_BY_EMAIL_AVAILABLE("ClassifiedAdContactByEmailAvailable"),

    /**
     * 
     * 						Returns information about whether a category supports pay-per-lead listings.
     * 					
     * 
     */
    @XmlEnumValue("ClassifiedAdPayPerLeadEnabled")
    CLASSIFIED_AD_PAY_PER_LEAD_ENABLED("ClassifiedAdPayPerLeadEnabled"),

    /**
     * 
     * 						Returns information about whether categories support
     * 						custom Item Specifics, and whether categories have been converted
     * 						from the older ID-based attribute format to custom Item Specifics.
     * 					
     * 
     */
    @XmlEnumValue("ItemSpecificsEnabled")
    ITEM_SPECIFICS_ENABLED("ItemSpecificsEnabled"),

    /**
     * 
     * 						Returns information about whether PaisaPayEscrow payment method is enabled.
     * 					
     * 
     */
    @XmlEnumValue("PaisaPayFullEscrowEnabled")
    PAISA_PAY_FULL_ESCROW_ENABLED("PaisaPayFullEscrowEnabled"),

    /**
     * 
     * 						Returns information about whether auto-accept for Best
     * 						Offers is allowed for Classified Ad listings in this category.
     * 					
     * 
     */
    @XmlEnumValue("ClassifiedAdAutoAcceptEnabled")
    CLASSIFIED_AD_AUTO_ACCEPT_ENABLED("ClassifiedAdAutoAcceptEnabled"),

    /**
     * 
     * 						Returns information about whether auto-accept for Best
     * 						Offers is allowed for listings in a category.
     * 					
     * 
     */
    @XmlEnumValue("BestOfferAutoAcceptEnabled")
    BEST_OFFER_AUTO_ACCEPT_ENABLED("BestOfferAutoAcceptEnabled"),

    /**
     * 
     * 							Returns information about whether a category allows listings to
     * 							be displayed in another site's default search results.
     * 						
     * 
     */
    @XmlEnumValue("CrossBorderTradeEnabled")
    CROSS_BORDER_TRADE_ENABLED("CrossBorderTradeEnabled"),

    /**
     * 
     * 						On the Australia site, returns information about whether a category allows
     * 						PayPal buyer protection.
     * 					
     * 
     */
    @XmlEnumValue("PayPalBuyerProtectionEnabled")
    PAY_PAL_BUYER_PROTECTION_ENABLED("PayPalBuyerProtectionEnabled"),

    /**
     * 
     * 						On the Australia site, returns information about whether a category allows
     * 						PayPal buyer protection.
     * 					
     * 
     */
    @XmlEnumValue("BuyerGuaranteeEnabled")
    BUYER_GUARANTEE_ENABLED("BuyerGuaranteeEnabled"),

    /**
     * 
     * 						On the India site, returns information about the escrow workflow version
     * 						applicable to the given category, if the category supports PaisaPayFullEscrow.
     * 					
     * 
     */
    @XmlEnumValue("INEscrowWorkflowTimeline")
    IN_ESCROW_WORKFLOW_TIMELINE("INEscrowWorkflowTimeline"),

    /**
     * 
     * 						Returns information about whether a category allows
     * 						combined fixed price treatment of the following two listing types: Store
     * 						Inventory Format and Basic Fixed Price.
     * 					
     * 
     */
    @XmlEnumValue("CombinedFixedPriceTreatment")
    COMBINED_FIXED_PRICE_TREATMENT("CombinedFixedPriceTreatment"),

    /**
     * 
     * 						Returns information about whether a category allows
     * 						durations for "Gallery Featured".
     * 					
     * 
     */
    @XmlEnumValue("GalleryFeaturedDurations")
    GALLERY_FEATURED_DURATIONS("GalleryFeaturedDurations"),

    /**
     * 
     * 						Returns information about whether listings in a category
     * 						require PayPal as a payment method.
     * 					
     * 
     */
    @XmlEnumValue("PayPalRequired")
    PAY_PAL_REQUIRED("PayPalRequired"),

    /**
     * 
     * 						Indicates whether the category supports ad-format. Added for eBay Motors Pro users.
     * 					
     * 
     */
    @XmlEnumValue("eBayMotorsProAdFormatEnabled")
    E_BAY_MOTORS_PRO_AD_FORMAT_ENABLED("eBayMotorsProAdFormatEnabled"),

    /**
     * 
     * 						Returns information about whether the telephone can be a contact method
     * 						for the category. Added for eBay Motorss Pro users.
     * 					
     * 
     */
    @XmlEnumValue("eBayMotorsProContactByPhoneEnabled")
    E_BAY_MOTORS_PRO_CONTACT_BY_PHONE_ENABLED("eBayMotorsProContactByPhoneEnabled"),

    /**
     * 
     * 						Returns information about whether a category supports including an address
     * 						in the seller's contact information. Added for eBay Motors Pro users.
     * 					
     * 
     */
    @XmlEnumValue("eBayMotorsProContactByAddressEnabled")
    E_BAY_MOTORS_PRO_CONTACT_BY_ADDRESS_ENABLED("eBayMotorsProContactByAddressEnabled"),

    /**
     * 
     * 						Returns information about whether a category supports including a company
     * 						name in the seller's contact information. Added for eBay Motors Pro users.
     * 					
     * 
     */
    @XmlEnumValue("eBayMotorsProCompanyNameEnabled")
    E_BAY_MOTORS_PRO_COMPANY_NAME_ENABLED("eBayMotorsProCompanyNameEnabled"),

    /**
     * 
     * 						Returns information about whether a category supports including an email
     * 						address in the seller's contact information. Added for eBay Motors Pro users.
     * 					
     * 
     */
    @XmlEnumValue("eBayMotorsProContactByEmailEnabled")
    E_BAY_MOTORS_PRO_CONTACT_BY_EMAIL_ENABLED("eBayMotorsProContactByEmailEnabled"),

    /**
     * 
     * 						Returns information about whether a category supports Best Offers.
     * 						Added for eBay Motors Pro users.
     * 					
     * 
     */
    @XmlEnumValue("eBayMotorsProBestOfferEnabled")
    E_BAY_MOTORS_PRO_BEST_OFFER_ENABLED("eBayMotorsProBestOfferEnabled"),

    /**
     * 
     * 						Returns information about whether a category supports auto accept.
     * 						Added for eBay Motors Pro users.
     * 					
     * 
     */
    @XmlEnumValue("eBayMotorsProAutoAcceptEnabled")
    E_BAY_MOTORS_PRO_AUTO_ACCEPT_ENABLED("eBayMotorsProAutoAcceptEnabled"),

    /**
     * 
     * 						Returns information about whether a category supports auto decline.
     * 						Added for eBay Motors Pro users.
     * 					
     * 
     */
    @XmlEnumValue("eBayMotorsProAutoDeclineEnabled")
    E_BAY_MOTORS_PRO_AUTO_DECLINE_ENABLED("eBayMotorsProAutoDeclineEnabled"),

    /**
     * 
     * 						Returns information about whether a category supports payment method checkout.
     * 						Added for eBay Motors Pro users.
     * 					
     * 
     */
    @XmlEnumValue("eBayMotorsProPaymentMethodCheckOutEnabled")
    E_BAY_MOTORS_PRO_PAYMENT_METHOD_CHECK_OUT_ENABLED("eBayMotorsProPaymentMethodCheckOutEnabled"),

    /**
     * 
     * 						Returns information about whether shipping options are available in the category.
     * 						Added for eBay Motors Pro users.
     * 					
     * 
     */
    @XmlEnumValue("eBayMotorsProShippingMethodEnabled")
    E_BAY_MOTORS_PRO_SHIPPING_METHOD_ENABLED("eBayMotorsProShippingMethodEnabled"),

    /**
     * 
     * 						Returns information about whether counter offers are allowed for a category.
     * 						Added for eBay Motors Pro users.
     * 					
     * 
     */
    @XmlEnumValue("eBayMotorsProCounterOfferEnabled")
    E_BAY_MOTORS_PRO_COUNTER_OFFER_ENABLED("eBayMotorsProCounterOfferEnabled"),

    /**
     * 
     * 						Returns information about whether a category allows seller-level
     * 						contact information for Classified Ad listings. Added for eBay Motors Pro users.
     * 					
     * 
     */
    @XmlEnumValue("eBayMotorsProSellerContactDetailsEnabled")
    E_BAY_MOTORS_PRO_SELLER_CONTACT_DETAILS_ENABLED("eBayMotorsProSellerContactDetailsEnabled"),

    /**
     * 
     * 						Indicates whether a category supports ad-format. Added for Local Market users.
     * 					
     * 
     */
    @XmlEnumValue("LocalMarketAdFormatEnabled")
    LOCAL_MARKET_AD_FORMAT_ENABLED("LocalMarketAdFormatEnabled"),

    /**
     * 
     * 						Returns information about whether a category supports including a telephone
     * 						number in the seller's contact information. Added for Local Market users.
     * 					
     * 
     */
    @XmlEnumValue("LocalMarketContactByPhoneEnabled")
    LOCAL_MARKET_CONTACT_BY_PHONE_ENABLED("LocalMarketContactByPhoneEnabled"),

    /**
     * 
     * 						Returns information about whether a category supports including an address
     * 						in the seller's contact information. Added for Local Market users.
     * 					
     * 
     */
    @XmlEnumValue("LocalMarketContactByAddressEnabled")
    LOCAL_MARKET_CONTACT_BY_ADDRESS_ENABLED("LocalMarketContactByAddressEnabled"),

    /**
     * 
     * 						Returns information about whether a category supports including a company
     * 						name in the seller's contact information. Added for Local Market users.
     * 					
     * 
     */
    @XmlEnumValue("LocalMarketCompanyNameEnabled")
    LOCAL_MARKET_COMPANY_NAME_ENABLED("LocalMarketCompanyNameEnabled"),

    /**
     * 
     * 						Returns information about whether a category supports including an email
     * 						address in the seller's contact information. Added for Local Market users.
     * 					
     * 
     */
    @XmlEnumValue("LocalMarketContactByEmailEnabled")
    LOCAL_MARKET_CONTACT_BY_EMAIL_ENABLED("LocalMarketContactByEmailEnabled"),

    /**
     * 
     * 						Returns information about whether a category supports Best Offers for
     * 						Classified Ad listings. Added for Local Market users.
     * 					
     * 
     */
    @XmlEnumValue("LocalMarketBestOfferEnabled")
    LOCAL_MARKET_BEST_OFFER_ENABLED("LocalMarketBestOfferEnabled"),

    /**
     * 
     * 						Returns information about whether a category supports auto accept.
     * 						Added for Local Market users.
     * 					
     * 
     */
    @XmlEnumValue("LocalMarketAutoAcceptEnabled")
    LOCAL_MARKET_AUTO_ACCEPT_ENABLED("LocalMarketAutoAcceptEnabled"),

    /**
     * 
     * 						Returns information about whether a category supports auto decline.
     * 						Added for Local Market users.
     * 					
     * 
     */
    @XmlEnumValue("LocalMarketAutoDeclineEnabled")
    LOCAL_MARKET_AUTO_DECLINE_ENABLED("LocalMarketAutoDeclineEnabled"),

    /**
     * 
     * 						Returns information about whether a category supports payment method checkout.
     * 						Added for Local Market users.
     * 					
     * 
     */
    @XmlEnumValue("LocalMarketPaymentMethodCheckOutEnabled")
    LOCAL_MARKET_PAYMENT_METHOD_CHECK_OUT_ENABLED("LocalMarketPaymentMethodCheckOutEnabled"),

    /**
     * 
     * 						Returns information about whether shipping options are available for
     * 						 listings in a category. Added for Local Market users.
     * 					
     * 
     */
    @XmlEnumValue("LocalMarketShippingMethodEnabled")
    LOCAL_MARKET_SHIPPING_METHOD_ENABLED("LocalMarketShippingMethodEnabled"),

    /**
     * 
     * 						Returns information about whether counter offers are allowed for a category .
     * 						Added for Local Market users.
     * 					
     * 
     */
    @XmlEnumValue("LocalMarketCounterOfferEnabled")
    LOCAL_MARKET_COUNTER_OFFER_ENABLED("LocalMarketCounterOfferEnabled"),

    /**
     * 
     * 						Returns information about whether a category allows seller-level
     * 						contact information. Added for Local Market users.
     * 					
     * 
     */
    @XmlEnumValue("LocalMarketSellerContactDetailsEnabled")
    LOCAL_MARKET_SELLER_CONTACT_DETAILS_ENABLED("LocalMarketSellerContactDetailsEnabled"),

    /**
     * 
     * 						Returns information about whether the contact by address is enabled.
     * 						Added for For Sale By Owner.
     * 					
     * 
     */
    @XmlEnumValue("ClassifiedAdContactByAddressEnabled")
    CLASSIFIED_AD_CONTACT_BY_ADDRESS_ENABLED("ClassifiedAdContactByAddressEnabled"),

    /**
     * 
     * 						Returns information about whether a category supports including a company
     * 						name in the seller's contact information. Added for For Sale By Owmer.
     * 					
     * 
     */
    @XmlEnumValue("ClassifiedAdCompanyNameEnabled")
    CLASSIFIED_AD_COMPANY_NAME_ENABLED("ClassifiedAdCompanyNameEnabled"),

    /**
     * 
     * 						Returns information about whether a category supports Local Market listings
     * 						for sellers subscribed to Local Market for Specialty Vehicles. Added for
     * 						subscription.
     * 					
     * 
     */
    @XmlEnumValue("SpecialitySubscription")
    SPECIALITY_SUBSCRIPTION("SpecialitySubscription"),

    /**
     * 
     * 						Returns information about whether a category supports Local Market listings
     * 						for sellers subscribed to Local Market for Vehicles. Added for subscription.
     * 					
     * 
     */
    @XmlEnumValue("RegularSubscription")
    REGULAR_SUBSCRIPTION("RegularSubscription"),

    /**
     * 
     * 						Returns information about whether a category supports Local Market listings
     * 						for sellers with a premium Local Market subscription. Added for subscription.
     * 					
     * 
     */
    @XmlEnumValue("PremiumSubscription")
    PREMIUM_SUBSCRIPTION("PremiumSubscription"),

    /**
     * 
     * 						Returns information about whether a category supports Local Market listings
     * 						for sellers without a Local Market subscription. Added for subscription.
     * 					
     * 
     */
    @XmlEnumValue("NonSubscription")
    NON_SUBSCRIPTION("NonSubscription"),

    /**
     * 
     * 						Returns information about whether Intangible flag is set or not
     * 					
     * 
     */
    @XmlEnumValue("IntangibleEnabled")
    INTANGIBLE_ENABLED("IntangibleEnabled"),

    /**
     * 
     * 						Defines if PayPal is required for Store Owners on fixed price listings (FixedPriceItem and StoresFixedPrice).
     * 					
     * 
     */
    @XmlEnumValue("PayPalRequiredForStoreOwner")
    PAY_PAL_REQUIRED_FOR_STORE_OWNER("PayPalRequiredForStoreOwner"),

    /**
     * 
     * 						Defines if quantity can be revised while a listing is in semi or fully restricted mode.
     * 					
     * 
     */
    @XmlEnumValue("ReviseQuantityAllowed")
    REVISE_QUANTITY_ALLOWED("ReviseQuantityAllowed"),

    /**
     * 
     * 						Defines if price can be revised while a listing is in semi or fully restricted mode.
     * 					
     * 
     */
    @XmlEnumValue("RevisePriceAllowed")
    REVISE_PRICE_ALLOWED("RevisePriceAllowed"),

    /**
     * 
     * 						Defines if extended store owner listing durations are enabled on fixed price listings (FixedPriceItem and StoreFormatItems).
     * 					
     * 
     */
    @XmlEnumValue("StoreOwnerExtendedListingDurationsEnabled")
    STORE_OWNER_EXTENDED_LISTING_DURATIONS_ENABLED("StoreOwnerExtendedListingDurationsEnabled"),

    /**
     * 
     * 						Provides additional listing durations that are supported by store owners.
     * 					
     * 
     */
    @XmlEnumValue("StoreOwnerExtendedListingDurations")
    STORE_OWNER_EXTENDED_LISTING_DURATIONS("StoreOwnerExtendedListingDurations"),

    /**
     * 
     * 						Defines if listings in a category support a return policy.
     * 					
     * 
     */
    @XmlEnumValue("ReturnPolicyEnabled")
    RETURN_POLICY_ENABLED("ReturnPolicyEnabled"),

    /**
     * 
     * 						Defines if listings require a handling time (dispatch time).
     * 						The handling time is the maximum number of business days the seller
     * 						commits to for preparing an item to be shipped after receiving a
     * 						cleared payment. The seller's handling time does not include the
     * 						shipping time (the carrier's transit time).
     * 					
     * 
     */
    @XmlEnumValue("HandlingTimeEnabled")
    HANDLING_TIME_ENABLED("HandlingTimeEnabled"),

    /**
     * 
     * 						Returns information about applicable payment methods.
     * 					
     * 
     */
    @XmlEnumValue("PaymentMethods")
    PAYMENT_METHODS("PaymentMethods"),

    /**
     * 
     * 						Returns elements related to the maximum cost the seller can charge for
     * 						the first domestic flat rate shipping service for a category (or a
     * 						combination of category and shipping service group).
     * 					
     * 
     */
    @XmlEnumValue("MaxFlatShippingCost")
    MAX_FLAT_SHIPPING_COST("MaxFlatShippingCost"),

    /**
     * 
     * 						Specifies the default site setting for whether a maximum flat rate shipping cost
     * 						is imposed on sellers who list in categories on this site yet are shipping an
     * 						item into the country of this site from another country.
     * 					
     * 
     */
    @XmlEnumValue("MaxFlatShippingCostCBTExempt")
    MAX_FLAT_SHIPPING_COST_CBT_EXEMPT("MaxFlatShippingCostCBTExempt"),

    /**
     * 
     * 						Returns the applicable max cap per shipping cost for shipping service group1.
     * 					
     * 
     */
    @XmlEnumValue("Group1MaxFlatShippingCost")
    GROUP_1_MAX_FLAT_SHIPPING_COST("Group1MaxFlatShippingCost"),

    /**
     * 
     * 						Returns the applicable max cap per shipping cost for shipping service group2.
     * 					
     * 
     */
    @XmlEnumValue("Group2MaxFlatShippingCost")
    GROUP_2_MAX_FLAT_SHIPPING_COST("Group2MaxFlatShippingCost"),

    /**
     * 
     * 						Returns the applicable max cap per shipping cost for shipping service group3.
     * 					
     * 
     */
    @XmlEnumValue("Group3MaxFlatShippingCost")
    GROUP_3_MAX_FLAT_SHIPPING_COST("Group3MaxFlatShippingCost"),

    /**
     * 
     * 						Returns settings that indicate which categories support
     * 						multi-variation listings. Variations are items within
     * 						the same listing that are logically the same product,
     * 						but that vary in their manufacturing details or packaging.
     * 					
     * 
     */
    @XmlEnumValue("VariationsEnabled")
    VARIATIONS_ENABLED("VariationsEnabled"),

    /**
     * 
     * 						Not operational. Use ItemSpecificsEnabled instead.
     * 					
     * 
     */
    @XmlEnumValue("AttributeConversionEnabled")
    ATTRIBUTE_CONVERSION_ENABLED("AttributeConversionEnabled"),

    /**
     * 
     * 						Returns information about whether
     * 						free, automatic upgrades to Gallery Plus
     * 						occur a category.
     * 					
     * 
     */
    @XmlEnumValue("FreeGalleryPlusEnabled")
    FREE_GALLERY_PLUS_ENABLED("FreeGalleryPlusEnabled"),

    /**
     * 
     * 						Returns information about whether
     * 						free, automatic upgrades to Picture Pack
     * 						occur a category.
     * 					
     * 
     */
    @XmlEnumValue("FreePicturePackEnabled")
    FREE_PICTURE_PACK_ENABLED("FreePicturePackEnabled"),

    /**
     * 
     * 						Returns whether parts compatibility is enabled for the given category and, if
     * 						so, the mode of compatibility (by application or by specification).
     * 						<br><br> 
     * 						Parts Compatibility is supported in limited Parts & Accessories
     * 						categories for the eBay Motors (US) site (site ID 100) only.
     * 					
     * 
     */
    @XmlEnumValue("CompatibilityEnabled")
    COMPATIBILITY_ENABLED("CompatibilityEnabled"),

    /**
     * 
     * 						Returns the minimum number of compatible applications that must 
     * 						be specified when listing in the given category. This is relevant for 
     * 						specifying parts compatibility by application only. 
     * 						<br><br> 
     * 						Parts Compatibility is supported in limited Parts & Accessories
     * 						categories for the eBay Motors (US) site (site ID 100) only.
     * 					
     * 
     */
    @XmlEnumValue("MinCompatibleApplications")
    MIN_COMPATIBLE_APPLICATIONS("MinCompatibleApplications"),

    /**
     * 
     * 						Returns the maximum number of compatible applications that can 
     * 						be specified when listing in the given category. This is relevant for 
     * 						specifying parts compatibility by application manually 
     * 						(<b class="con">Item.ItemCompatibilityList</b>) only.
     * 						<br><br> 
     * 						Parts Compatibility is supported in limited Parts & Accessories
     * 						categories for the eBay Motors (US) site (site ID 100) only.
     * 					
     * 
     */
    @XmlEnumValue("MaxCompatibleApplications")
    MAX_COMPATIBLE_APPLICATIONS("MaxCompatibleApplications"),

    /**
     * 
     * 						Returns information about which categories support
     * 						(or require) Item.ConditionID to specify 
     * 						an item's condition in listings.
     * 					
     * 
     */
    @XmlEnumValue("ConditionEnabled")
    CONDITION_ENABLED("ConditionEnabled"),

    /**
     * 
     * 						Returns information about the list of conditions
     * 						that categories support (for categories that support 
     * 						Item.ConditionID in listings).
     * 					
     * 
     */
    @XmlEnumValue("ConditionValues")
    CONDITION_VALUES("ConditionValues");
    private final String value;

    FeatureIDCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FeatureIDCodeType fromValue(String v) {
        for (FeatureIDCodeType c: FeatureIDCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
