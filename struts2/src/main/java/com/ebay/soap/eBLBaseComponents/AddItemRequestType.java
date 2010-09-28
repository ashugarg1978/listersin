
package com.ebay.soap.eBLBaseComponents;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Defines a single new item and lists it on a specified eBay site. Returns the
 * 				item ID for the new listing, and returns fees the seller will incur for the
 * 				listing (not including the Final Value Fee, which cannot be calculated until
 * 				the item is sold).
 * 				<br><br>
 * 				Sellers who engage in cross-border trade on sites that require a recoupment agreement, must agree to the
 * 				recoupment terms before adding items to the site. This agreement allows eBay to reimburse
 * 				a buyer during a dispute and then recoup the cost from the seller. Links to the agreement
 * 				will be posted to the eBay Developer Program website.
 * 				<br><br>
 * 				For the US, Canada (CA), French Canadian (CAFR), and US Motors sites, the FixedPriceItem listing format
 * 				will be replacing the StoresFixedPrice listing format, and the StoresFixedPrice format will be deprecated
 * 				in early 2011. As of March 30, 2010, we will start a migration phase where AddItem and AddFixedPriceItem
 * 				will accept either FixedPriceItem or StoresFixedPrice as listing formats, but the item will be displayed
 * 				as FixedPriceItem on the site and in search results. GetItem and other 'Get' calls will return the format you originally
 * 				used in the request. Therefore, the preferred format will be FixedPriceItem.
 * 				<br><br>
 * 				As part of the merge of the StoresFixedPrice and FixedPriceItem formats, the start price of all new
 * 				FixedPriceItems must be 99 cents or greater. This change will also go into effect on March 30, 2010.
 * 			
 * 
 * <p>Java class for AddItemRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddItemRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractRequestType">
 *       &lt;sequence>
 *         &lt;element name="Item" type="{urn:ebay:apis:eBLBaseComponents}ItemType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddItemRequestType", propOrder = {
    "item"
})
public class AddItemRequestType
    extends AbstractRequestType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "Item")
    protected ItemType item;

    /**
     * Gets the value of the item property.
     * 
     * @return
     *     possible object is
     *     {@link ItemType }
     *     
     */
    public ItemType getItem() {
        return item;
    }

    /**
     * Sets the value of the item property.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemType }
     *     
     */
    public void setItem(ItemType value) {
        this.item = value;
    }

}
