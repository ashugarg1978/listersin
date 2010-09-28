
package com.ebay.soap.eBLBaseComponents;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Enables a seller to create a new Unpaid Item dispute. (Item Not Received
 * 				disputes can only be created via the eBay web site.)
 * 			
 * 
 * <p>Java class for AddDisputeRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddDisputeRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractRequestType">
 *       &lt;sequence>
 *         &lt;element name="DisputeExplanation" type="{urn:ebay:apis:eBLBaseComponents}DisputeExplanationCodeType" minOccurs="0"/>
 *         &lt;element name="DisputeReason" type="{urn:ebay:apis:eBLBaseComponents}DisputeReasonCodeType" minOccurs="0"/>
 *         &lt;element name="ItemID" type="{urn:ebay:apis:eBLBaseComponents}ItemIDType" minOccurs="0"/>
 *         &lt;element name="TransactionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddDisputeRequestType", propOrder = {
    "disputeExplanation",
    "disputeReason",
    "itemID",
    "transactionID"
})
public class AddDisputeRequestType
    extends AbstractRequestType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "DisputeExplanation")
    protected DisputeExplanationCodeType disputeExplanation;
    @XmlElement(name = "DisputeReason")
    protected DisputeReasonCodeType disputeReason;
    @XmlElement(name = "ItemID")
    protected String itemID;
    @XmlElement(name = "TransactionID")
    protected String transactionID;

    /**
     * Gets the value of the disputeExplanation property.
     * 
     * @return
     *     possible object is
     *     {@link DisputeExplanationCodeType }
     *     
     */
    public DisputeExplanationCodeType getDisputeExplanation() {
        return disputeExplanation;
    }

    /**
     * Sets the value of the disputeExplanation property.
     * 
     * @param value
     *     allowed object is
     *     {@link DisputeExplanationCodeType }
     *     
     */
    public void setDisputeExplanation(DisputeExplanationCodeType value) {
        this.disputeExplanation = value;
    }

    /**
     * Gets the value of the disputeReason property.
     * 
     * @return
     *     possible object is
     *     {@link DisputeReasonCodeType }
     *     
     */
    public DisputeReasonCodeType getDisputeReason() {
        return disputeReason;
    }

    /**
     * Sets the value of the disputeReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link DisputeReasonCodeType }
     *     
     */
    public void setDisputeReason(DisputeReasonCodeType value) {
        this.disputeReason = value;
    }

    /**
     * Gets the value of the itemID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * Sets the value of the itemID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemID(String value) {
        this.itemID = value;
    }

    /**
     * Gets the value of the transactionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * Sets the value of the transactionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionID(String value) {
        this.transactionID = value;
    }

}
