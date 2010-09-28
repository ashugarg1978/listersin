
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NotificationEventTypeCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="NotificationEventTypeCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="OutBid"/>
 *     &lt;enumeration value="EndOfAuction"/>
 *     &lt;enumeration value="AuctionCheckoutComplete"/>
 *     &lt;enumeration value="FixedPriceEndOfTransaction"/>
 *     &lt;enumeration value="CheckoutBuyerRequestsTotal"/>
 *     &lt;enumeration value="Feedback"/>
 *     &lt;enumeration value="FeedbackForSeller"/>
 *     &lt;enumeration value="FixedPriceTransaction"/>
 *     &lt;enumeration value="SecondChanceOffer"/>
 *     &lt;enumeration value="AskSellerQuestion"/>
 *     &lt;enumeration value="ItemListed"/>
 *     &lt;enumeration value="ItemRevised"/>
 *     &lt;enumeration value="BuyerResponseDispute"/>
 *     &lt;enumeration value="SellerOpenedDispute"/>
 *     &lt;enumeration value="SellerRespondedToDispute"/>
 *     &lt;enumeration value="SellerClosedDispute"/>
 *     &lt;enumeration value="BestOffer"/>
 *     &lt;enumeration value="MyMessagesAlertHeader"/>
 *     &lt;enumeration value="MyMessagesAlert"/>
 *     &lt;enumeration value="MyMessageseBayMessageHeader"/>
 *     &lt;enumeration value="MyMessageseBayMessage"/>
 *     &lt;enumeration value="MyMessagesM2MMessageHeader"/>
 *     &lt;enumeration value="MyMessagesM2MMessage"/>
 *     &lt;enumeration value="INRBuyerOpenedDispute"/>
 *     &lt;enumeration value="INRBuyerRespondedToDispute"/>
 *     &lt;enumeration value="INRBuyerClosedDispute"/>
 *     &lt;enumeration value="INRSellerRespondedToDispute"/>
 *     &lt;enumeration value="Checkout"/>
 *     &lt;enumeration value="WatchedItemEndingSoon"/>
 *     &lt;enumeration value="ItemClosed"/>
 *     &lt;enumeration value="ItemSuspended"/>
 *     &lt;enumeration value="ItemSold"/>
 *     &lt;enumeration value="ItemExtended"/>
 *     &lt;enumeration value="UserIDChanged"/>
 *     &lt;enumeration value="EmailAddressChanged"/>
 *     &lt;enumeration value="PasswordChanged"/>
 *     &lt;enumeration value="PasswordHintChanged"/>
 *     &lt;enumeration value="PaymentDetailChanged"/>
 *     &lt;enumeration value="AccountSuspended"/>
 *     &lt;enumeration value="AccountSummary"/>
 *     &lt;enumeration value="ThirdPartyCartCheckout"/>
 *     &lt;enumeration value="ItemRevisedAddCharity"/>
 *     &lt;enumeration value="ItemAddedToWatchList"/>
 *     &lt;enumeration value="ItemRemovedFromWatchList"/>
 *     &lt;enumeration value="ItemAddedToBidGroup"/>
 *     &lt;enumeration value="ItemRemovedFromBidGroup"/>
 *     &lt;enumeration value="FeedbackLeft"/>
 *     &lt;enumeration value="FeedbackReceived"/>
 *     &lt;enumeration value="FeedbackStarChanged"/>
 *     &lt;enumeration value="BidPlaced"/>
 *     &lt;enumeration value="BidReceived"/>
 *     &lt;enumeration value="ItemWon"/>
 *     &lt;enumeration value="ItemLost"/>
 *     &lt;enumeration value="ItemUnsold"/>
 *     &lt;enumeration value="CounterOfferReceived"/>
 *     &lt;enumeration value="BestOfferDeclined"/>
 *     &lt;enumeration value="BestOfferPlaced"/>
 *     &lt;enumeration value="ItemsCanceled"/>
 *     &lt;enumeration value="TokenRevocation"/>
 *     &lt;enumeration value="BulkDataExchangeJobCompleted"/>
 *     &lt;enumeration value="CustomCode"/>
 *     &lt;enumeration value="ItemMarkedShipped"/>
 *     &lt;enumeration value="ItemMarkedPaid"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "NotificationEventTypeCodeType")
@XmlEnum
public enum NotificationEventTypeCodeType {


    /**
     * 
     * 					  Not a notification event.
     * 					
     * 
     */
    @XmlEnumValue("None")
    NONE("None"),

    /**
     * 
     * 						Sent to a user when another buyer has placed a higher maximum bid
     * 						and the user is no longer the current high bidder.
     * 						<br><br>
     * 						Applies to Buyers.
     * 					
     * 
     */
    @XmlEnumValue("OutBid")
    OUT_BID("OutBid"),

    /**
     * 
     * 						Sent when an auction ends. An auction ends either when its duration
     * 						expires or the buyer purchases an item with Buy It Now. Applies to
     * 						all competitive-bid auctions.
     * 						<br><br>
     * 						Applies to both Buyers and Sellers.
     * 					
     * 
     */
    @XmlEnumValue("EndOfAuction")
    END_OF_AUCTION("EndOfAuction"),

    /**
     * 
     * 						Sent to a seller when a buyer completes the checkout process for an
     * 						item. Not sent when an auction ends without bids.
     * 						<br><br>
     * 						Applies to Sellers.
     * 					
     * 
     */
    @XmlEnumValue("AuctionCheckoutComplete")
    AUCTION_CHECKOUT_COMPLETE("AuctionCheckoutComplete"),

    /**
     * 
     * 						Sent to a seller when a fixed-price item is sold and the buyer
     * 						completes the checkout process. Not sent when a fixed-price item's duration
     * 						expires without purchase.
     * 						<br><br>
     * 						Applies to Sellers.
     * 					
     * 
     */
    @XmlEnumValue("FixedPriceEndOfTransaction")
    FIXED_PRICE_END_OF_TRANSACTION("FixedPriceEndOfTransaction"),

    /**
     * 
     * 						Sent to a seller each time a buyer requests a total price. Occurs
     * 						before checkout is complete.
     * 						<br><br>
     * 						Applies to Sellers.
     * 					
     * 
     */
    @XmlEnumValue("CheckoutBuyerRequestsTotal")
    CHECKOUT_BUYER_REQUESTS_TOTAL("CheckoutBuyerRequestsTotal"),

    /**
     * 
     * 						When a user leaves feedback, this notification is sent to a third party who
     * 						has subscribed on a user's behalf. The user can be a buyer who leaves
     * 						feedback for a seller or a seller who leaves feedback for a buyer.
     * 						<br><br>
     * 						Applies to both Buyers and Sellers.
     * 					
     * 
     */
    @XmlEnumValue("Feedback")
    FEEDBACK("Feedback"),

    /**
     * 
     * 						If specified in SetNotificationPreferences, eBay drops this value
     * 						and uses Feedback instead.  Applications should remove any
     * 						dependencies on this value.
     * 						<br><br>
     * 						Use Feedback Instead.
     * 					
     * 
     */
    @XmlEnumValue("FeedbackForSeller")
    FEEDBACK_FOR_SELLER("FeedbackForSeller"),

    /**
     * 
     * 						Sent to a seller when a buyer purchases a fixed-price item.
     * 						<br><br>
     * 						Applies to Sellers.
     * 					
     * 
     */
    @XmlEnumValue("FixedPriceTransaction")
    FIXED_PRICE_TRANSACTION("FixedPriceTransaction"),

    /**
     * 
     * 						Reserved for future use.
     * 						<br><br>
     * 						Applies to Buyers.
     * 					
     * 
     */
    @XmlEnumValue("SecondChanceOffer")
    SECOND_CHANCE_OFFER("SecondChanceOffer"),

    /**
     * 
     * 						Sent to a seller when a question is posted about one of the seller's
     * 						active listings.
     * 						<br><br>
     * 						Applies to Sellers.
     * 					
     * 
     */
    @XmlEnumValue("AskSellerQuestion")
    ASK_SELLER_QUESTION("AskSellerQuestion"),

    /**
     * 
     * 						Sent to an eBay partner on behalf of a seller when a seller has
     * 						listed an item. Sent for each item the seller lists.
     * 						<br><br>
     * 						Applies to Sellers.
     * 					
     * 
     */
    @XmlEnumValue("ItemListed")
    ITEM_LISTED("ItemListed"),

    /**
     * 
     * 						Sent to an eBay partner on behalf of a seller when a seller has
     * 						revised an item.
     * 						<br><br>
     * 						Applies to Sellers.
     * 					
     * 
     */
    @XmlEnumValue("ItemRevised")
    ITEM_REVISED("ItemRevised"),

    /**
     * 
     * 						Sent to an eBay partner on behalf of a seller when a buyer responds
     * 						to a dispute the seller has opened. Sent for each response the buyer makes.
     * 						<br><br>
     * 						Applies to Sellers.
     * 					
     * 
     */
    @XmlEnumValue("BuyerResponseDispute")
    BUYER_RESPONSE_DISPUTE("BuyerResponseDispute"),

    /**
     * 
     * 						Sent to an eBay partner on behalf of a seller when a seller opens a
     * 						dispute. Sent for each dispute the seller opens.
     * 						<br><br>
     * 						Applies to Buyers.
     * 					
     * 
     */
    @XmlEnumValue("SellerOpenedDispute")
    SELLER_OPENED_DISPUTE("SellerOpenedDispute"),

    /**
     * 
     * 						Sent to an eBay partner on behalf of a seller when a seller responds
     * 						to a dispute they had opened. Sent for each response the seller makes.
     * 						<br><br>
     * 						Applies to Buyers.
     * 					
     * 
     */
    @XmlEnumValue("SellerRespondedToDispute")
    SELLER_RESPONDED_TO_DISPUTE("SellerRespondedToDispute"),

    /**
     * 
     * 						Sent to an eBay partner on behalf of a seller when a seller closes a
     * 						dispute they had opened. Sent for each closure the seller performs.
     * 						<br><br>
     * 						Applies to Sellers and Buyers.
     * 					
     * 
     */
    @XmlEnumValue("SellerClosedDispute")
    SELLER_CLOSED_DISPUTE("SellerClosedDispute"),

    /**
     * 
     * 						Sent to a seller when a bidder makes a best offer on an item opted
     * 						into the Best Offer feature by the seller.
     * 						<br><br>
     * 						Applies to Sellers.
     * 					
     * 
     */
    @XmlEnumValue("BestOffer")
    BEST_OFFER("BestOffer"),

    /**
     * 
     * 						A notification type where a specified user or application is notified
     * 						when an alert is sent to My Messages. This notification type sends a
     * 						GetMyMessages response at a detail level of ReturnHeaders.
     * 						MyMessagesAlertHeader and MyMessagesAlert cannot be subscribed to at the same
     * 						time or specified in the same call.
     * 						<br><br>
     * 						Applies to both Buyers and Sellers.
     * 					
     * 
     */
    @XmlEnumValue("MyMessagesAlertHeader")
    MY_MESSAGES_ALERT_HEADER("MyMessagesAlertHeader"),

    /**
     * 
     * 						A notification type where a specified user or application is
     * 						notified when an alert is sent to My Messages. This notification type sends
     * 						a GetMyMessages response at a detail level of ReturnMessages.
     * 						MyMessagesAlertHeader and MyMessagesAlert cannot be subscribed to at the
     * 						same time or specified in the same call.
     * 						<br><br>
     * 						Applies to both Buyers and Sellers.
     * 					
     * 
     */
    @XmlEnumValue("MyMessagesAlert")
    MY_MESSAGES_ALERT("MyMessagesAlert"),

    /**
     * 
     * 						A notification type where a specified user or application is
     * 						notified when a message from eBay is sent to My Messages. This notification
     * 						type sends a GetMyMessages response at a detail level of ReturnHeaders.
     * 						MyMessageseBayMessageHeader and MyMessageseBayMessage cannot be subscribed
     * 						to at the same time or specified in the same call.
     * 						<br><br>
     * 						Applies to both Buyers and Sellers.
     * 					
     * 
     */
    @XmlEnumValue("MyMessageseBayMessageHeader")
    MY_MESSAGESE_BAY_MESSAGE_HEADER("MyMessageseBayMessageHeader"),

    /**
     * 
     * 						A notification type where a specified user or application is
     * 						notified when a message from eBay is sent to My Messages. This notification
     * 						type sends a GetMyMessages response at a detail level of ReturnMessages.
     * 						MyMessageseBayMessageHeader and MyMessageseBayMessage cannot be subscribed
     * 						to at the same time or specified in the same call.
     * 						<br><br>
     * 						Applies to both Buyers and Sellers.
     * 					
     * 
     */
    @XmlEnumValue("MyMessageseBayMessage")
    MY_MESSAGESE_BAY_MESSAGE("MyMessageseBayMessage"),

    /**
     * 
     * 						A notification type where a specified user or application is
     * 						notified when a member to member (M2M) message is sent to My Messages. This
     * 						notification type sends a GetMyMessages response at a detail level of
     * 						ReturnHeaders. MyMessagesM2MMessageHeader and MyMessagesM2MMessage cannot
     * 						be subscribed to at the same time or specified in the same call.
     * 						<br><br>
     * 						Applies to both Buyers and Sellers.
     * 					
     * 
     */
    @XmlEnumValue("MyMessagesM2MMessageHeader")
    MY_MESSAGES_M_2_M_MESSAGE_HEADER("MyMessagesM2MMessageHeader"),

    /**
     * 
     * 						A notification type where a specified user or application is
     * 						notified when a member to member (M2M) message is sent to My Messages. This
     * 						notification type sends a GetMyMessages response at a detail level of
     * 						ReturnMessages. MyMessagesM2MMessageHeader and MyMessagesM2MMessage cannot
     * 						be subscribed to at the same time or specified in the same call.
     * 						<br><br>
     * 						Applies to both Buyers and Sellers.
     * 					
     * 
     */
    @XmlEnumValue("MyMessagesM2MMessage")
    MY_MESSAGES_M_2_M_MESSAGE("MyMessagesM2MMessage"),

    /**
     * 
     * 					  A notification type where a specified user or application is
     * 						notified on behalf of a buyer when a buyer opens an Item Not Received dispute.
     * 						Sent for each dispute the buyer opens.
     * 						<br><br>
     * 						Applies to Sellers.
     * 					
     * 
     */
    @XmlEnumValue("INRBuyerOpenedDispute")
    INR_BUYER_OPENED_DISPUTE("INRBuyerOpenedDispute"),

    /**
     * 
     * 						A notification type where a specified user or application is
     * 						notified on behalf of a buyer when a buyer responds to Item Not Received
     * 						dispute that buyer had opened. Sent for each response the buyer makes.
     * 						<br><br>
     * 						Applies to Sellers.
     * 					
     * 
     */
    @XmlEnumValue("INRBuyerRespondedToDispute")
    INR_BUYER_RESPONDED_TO_DISPUTE("INRBuyerRespondedToDispute"),

    /**
     * 
     * 						A notification type where a specified user or application is
     * 						notified on behalf of a buyer when a buyer closes Item Not Received
     * 						dispute that buyer had opened. Sent for each closure the buyer performs.
     * 						<br><br>
     * 						Applies to Sellers.
     * 					
     * 
     */
    @XmlEnumValue("INRBuyerClosedDispute")
    INR_BUYER_CLOSED_DISPUTE("INRBuyerClosedDispute"),

    /**
     * 
     * 						 A notification type where a specified user or application is
     * 						notified on behalf of a seller when a seller responds to an Item Not Received
     * 						dispute that buyer had opened. Sent for each response the seller makes.
     * 						<br><br>
     * 						Applies to Buyers.
     * 					
     * 
     */
    @XmlEnumValue("INRSellerRespondedToDispute")
    INR_SELLER_RESPONDED_TO_DISPUTE("INRSellerRespondedToDispute"),

    /**
     * 
     * 						Do not use. This notification event is tied to eBay Express functionality
     * 						which no longer exists.
     * 					
     * 
     */
    @XmlEnumValue("Checkout")
    CHECKOUT("Checkout"),

    /**
     * 
     * 						A notification type where the listing of the watched item is about
     * 						to end. This event has a property with which caller can specify the TimeLeft
     * 						before the listing ends.
     * 						<br><br>
     * 						Applies to Buyers.
     * 					
     * 
     */
    @XmlEnumValue("WatchedItemEndingSoon")
    WATCHED_ITEM_ENDING_SOON("WatchedItemEndingSoon"),

    /**
     * 
     * 						Specifies that an ItemClosed notification event has occurred. This event is triggered by
     * 						ItemWon, ItemSold, and ItemUnsold events.
     * 						<br><br>
     * 						Applies to both Buyers and Sellers.
     * 					
     * 
     */
    @XmlEnumValue("ItemClosed")
    ITEM_CLOSED("ItemClosed"),

    /**
     * 
     * 						Specifies an ItemSuspended notification event. Subscribe to this event to be notified when eBay has taken down a listing for a listing problem, for example, listing in the wrong category.
     * 						<br><br>
     * 						Applies to both Buyers and Sellers.
     * 					
     * 
     */
    @XmlEnumValue("ItemSuspended")
    ITEM_SUSPENDED("ItemSuspended"),

    /**
     * 
     * 						Specifies an ItemSold notification event, triggered when an eBay listing ends in a sale.
     * 						<br><br>
     * 						Applies to Sellers.
     * 					
     * 
     */
    @XmlEnumValue("ItemSold")
    ITEM_SOLD("ItemSold"),

    /**
     * 
     * 						Specifies an ItemExtended notification event, when a seller has extended the duration of a listing.
     * 						<br><br>
     * 						Applies to Sellers.
     * 					
     * 
     */
    @XmlEnumValue("ItemExtended")
    ITEM_EXTENDED("ItemExtended"),

    /**
     * 
     * 						The userid was changed. Available for SMS on the UK and Germany
     * 						sites. Not applicable to Platform Notifications.
     * 						<br><br>
     * 						Applies to both Buyers and Sellers.
     * 					
     * 
     */
    @XmlEnumValue("UserIDChanged")
    USER_ID_CHANGED("UserIDChanged"),

    /**
     * 
     * 						The email address was changed. Available for SMS on the UK and
     * 						Germany sites. Not applicable to Platform Notifications.
     * 						<br><br>
     * 						Applies to both Buyers and Sellers.
     * 					
     * 
     */
    @XmlEnumValue("EmailAddressChanged")
    EMAIL_ADDRESS_CHANGED("EmailAddressChanged"),

    /**
     * 
     * 						The password was changed. Available for SMS on the UK and Germany
     * 						sites. Not applicable to Platform Notifications.
     * 						<br><br>
     * 						Applies to Sellers.
     * 					
     * 
     */
    @XmlEnumValue("PasswordChanged")
    PASSWORD_CHANGED("PasswordChanged"),

    /**
     * 
     * 						The password hint was changed. Available for SMS on the UK and
     * 						Germany sites. Not applicable to Platform Notifications.
     * 						<br><br>
     * 						Applies to Sellers.
     * 					
     * 
     */
    @XmlEnumValue("PasswordHintChanged")
    PASSWORD_HINT_CHANGED("PasswordHintChanged"),

    /**
     * 
     * 						The payment detail was changed. Available for SMS on the UK and
     * 						Germany sites. Not applicable to Platform Notifications.
     * 						<br><br>
     * 						Applies to both Buyers and Sellers.
     * 					
     * 
     */
    @XmlEnumValue("PaymentDetailChanged")
    PAYMENT_DETAIL_CHANGED("PaymentDetailChanged"),

    /**
     * 
     * 						The account was suspended. Available for SMS on the UK and Germany
     * 						sites. Not applicable to Platform Notifications.
     * 						<br><br>
     * 						Applies to both Buyers and Sellers.
     * 					
     * 
     */
    @XmlEnumValue("AccountSuspended")
    ACCOUNT_SUSPENDED("AccountSuspended"),

    /**
     * 
     * 						An informational alert about account activity.
     * 						A user can subscribe to receive an account activity summary via SMS.
     * 						The user can specify the period (time range) for the account summary and can
     * 						select how often the summary is to be sent. It is not triggered by an event
     * 						but rather by an eBay daemon process that monitors a schedule database.
     * 						<br><br>
     * 						Applies to both Buyers and Sellers.
     * 					
     * 
     */
    @XmlEnumValue("AccountSummary")
    ACCOUNT_SUMMARY("AccountSummary"),

    /**
     * 
     * 						Not functional. This notification event is tied to eBay Express functionality,
     * 						which no longer exists.
     * 						<br><br>
     * 						Sent to a third party when their cart completes checkout on eBay Express.
     * 						<br><br>
     * 						Applies to Buyers.
     * 					
     * 
     */
    @XmlEnumValue("ThirdPartyCartCheckout")
    THIRD_PARTY_CART_CHECKOUT("ThirdPartyCartCheckout"),

    /**
     * 
     * 						Sent to an eBay partner on behalf of a seller when a seller has
     * 						revised an item and added charity.
     * 						<br><br>
     * 						Applies to Sellers.
     * 					
     * 
     */
    @XmlEnumValue("ItemRevisedAddCharity")
    ITEM_REVISED_ADD_CHARITY("ItemRevisedAddCharity"),

    /**
     * 
     * 						Sent to a subscribing third party when a user adds an item to her or his watch list.
     * 						<br><br>
     * 						Applies to Buyers.
     * 					
     * 
     */
    @XmlEnumValue("ItemAddedToWatchList")
    ITEM_ADDED_TO_WATCH_LIST("ItemAddedToWatchList"),

    /**
     * 
     * 						Sent to a subscribing third party when a user removes an item from her or his watch list.
     * 						<br><br>
     * 						Applies to Buyers.
     * 					
     * 
     */
    @XmlEnumValue("ItemRemovedFromWatchList")
    ITEM_REMOVED_FROM_WATCH_LIST("ItemRemovedFromWatchList"),

    /**
     * 
     * 						Sent to a subscribing third party when a user adds an item to her or his bid group.
     * 						<br><br>
     * 						Applies to Buyers.
     * 					
     * 
     */
    @XmlEnumValue("ItemAddedToBidGroup")
    ITEM_ADDED_TO_BID_GROUP("ItemAddedToBidGroup"),

    /**
     * 
     * 						Sent to a subscribing third party when a user removes an item from her or his bid group.
     * 						<br><br>
     * 						Applies to Buyers.
     * 					
     * 
     */
    @XmlEnumValue("ItemRemovedFromBidGroup")
    ITEM_REMOVED_FROM_BID_GROUP("ItemRemovedFromBidGroup"),

    /**
     * 
     * 						Sent to third parties subscribed on a user's behalf when
     * 						feedback comments are left by that user.
     * 						<br><br>
     * 						Applies to both Buyers and Sellers.
     * 					
     * 
     */
    @XmlEnumValue("FeedbackLeft")
    FEEDBACK_LEFT("FeedbackLeft"),

    /**
     * 
     * 						Sent to third parties subscribed on a user's behalf when
     * 						feedback comments are received by that user.
     * 						<br><br>
     * 						Applies to both Buyers and Sellers.
     * 					
     * 
     */
    @XmlEnumValue("FeedbackReceived")
    FEEDBACK_RECEIVED("FeedbackReceived"),

    /**
     * 
     * 						Sent to a subscribing third party when a user's feedback star level changes.
     * 						<br><br>
     * 						Applies to both Buyers and Sellers.
     * 					
     * 
     */
    @XmlEnumValue("FeedbackStarChanged")
    FEEDBACK_STAR_CHANGED("FeedbackStarChanged"),

    /**
     * 
     * 						Sent to a subscribing third party for the buyer when a user places a bid for an item.
     * 					  <br><br>
     * 						Applies to Sellers.
     * 					
     * 
     */
    @XmlEnumValue("BidPlaced")
    BID_PLACED("BidPlaced"),

    /**
     * 
     * 						Sent to a subscribing third party for the seller when a user places a bid for an item.
     * 					  <br><br>
     * 						Applies to Sellers.
     * 					
     * 
     */
    @XmlEnumValue("BidReceived")
    BID_RECEIVED("BidReceived"),

    /**
     * 
     * 						Sent to a subscribing third party for the buyer when a user bought an item.
     * 						<br><br>
     * 						Applies to Buyers.
     * 					
     * 
     */
    @XmlEnumValue("ItemWon")
    ITEM_WON("ItemWon"),

    /**
     * 
     * 						Sent to a subscribing third party for the buyer when a user lost a bid for an item.
     * 						<br><br>
     * 						Applies to Buyers.
     * 					
     * 
     */
    @XmlEnumValue("ItemLost")
    ITEM_LOST("ItemLost"),

    /**
     * 
     * 						Sent to a subscribing third party for the seller when an item was not sold.
     * 						<br><br>
     * 						Applies to Buyers.
     * 					
     * 
     */
    @XmlEnumValue("ItemUnsold")
    ITEM_UNSOLD("ItemUnsold"),

    /**
     * 
     * 						Sent to a subscribing third party for a buyer when a seller makes a counter offer to the buyer's best offer on an item opted	into the Best Offer feature by the seller.
     * 					  <br><br>
     * 						Applies to Buyers.
     * 					
     * 
     */
    @XmlEnumValue("CounterOfferReceived")
    COUNTER_OFFER_RECEIVED("CounterOfferReceived"),

    /**
     * 
     * 						Sent to a subscribing third-party for a buyer when a seller rejects the buyer's best offer on an item opted into
     * 						the Best Offer feature by the seller. Also sent to a buyer when the buyer rejects a seller's counteroffer.
     * 					  <br><br>
     * 						Applies to Buyers.
     * 					
     * 
     */
    @XmlEnumValue("BestOfferDeclined")
    BEST_OFFER_DECLINED("BestOfferDeclined"),

    /**
     * 
     * 						Sent to a subscribing third party for a buyer who makes a best offer on an item opted into the Best Offer feature by a seller.
     * 					  <br><br>
     * 						Applies to Sellers.
     * 					
     * 
     */
    @XmlEnumValue("BestOfferPlaced")
    BEST_OFFER_PLACED("BestOfferPlaced"),

    /**
     * 
     * 						This event is not functional.
     * 					
     * 
     */
    @XmlEnumValue("ItemsCanceled")
    ITEMS_CANCELED("ItemsCanceled"),

    /**
     * 
     * 						An informational alert sent to a subscribing application when a user token has been revoked.
     * 						<br><br>
     * 						Applies to both Buyers and Sellers.
     * 					
     * 
     */
    @XmlEnumValue("TokenRevocation")
    TOKEN_REVOCATION("TokenRevocation"),

    /**
     * 
     * 						An informational alert sent to a subscribing application when the Bulk exchange job completes.
     * 						<br><br>
     * 						Applies to Sellers.
     * 					
     * 
     */
    @XmlEnumValue("BulkDataExchangeJobCompleted")
    BULK_DATA_EXCHANGE_JOB_COMPLETED("BulkDataExchangeJobCompleted"),

    /**
     * 
     * 					  Reserved for future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode"),

    /**
     * 
     * 						Alert sent to a subscribing buyer (or buyer's application) when item marked as shipped by the seller.
     * 					
     * 
     */
    @XmlEnumValue("ItemMarkedShipped")
    ITEM_MARKED_SHIPPED("ItemMarkedShipped"),

    /**
     * 
     * 						Alert sent to a subscribing buyer (or buyer's application) when item marked as paid by the seller.
     * 					
     * 
     */
    @XmlEnumValue("ItemMarkedPaid")
    ITEM_MARKED_PAID("ItemMarkedPaid");
    private final String value;

    NotificationEventTypeCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NotificationEventTypeCodeType fromValue(String v) {
        for (NotificationEventTypeCodeType c: NotificationEventTypeCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
