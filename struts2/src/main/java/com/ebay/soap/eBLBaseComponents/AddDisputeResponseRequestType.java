
package com.ebay.soap.eBLBaseComponents;

import java.io.Serializable;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 * 				Allows a seller to respond to or close an Item Not Received dispute.
 * 				This can be used to add a comment to an Unpaid Item Dispute
 * 				only if the request version was lower than 637 when the
 * 				dispute was created.
 * 			
 * 
 * <p>Java class for AddDisputeResponseRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddDisputeResponseRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractRequestType">
 *       &lt;sequence>
 *         &lt;element name="DisputeID" type="{urn:ebay:apis:eBLBaseComponents}DisputeIDType" minOccurs="0"/>
 *         &lt;element name="MessageText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DisputeActivity" type="{urn:ebay:apis:eBLBaseComponents}DisputeActivityCodeType" minOccurs="0"/>
 *         &lt;element name="ShippingCarrierUsed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipmentTrackNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShippingTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddDisputeResponseRequestType", propOrder = {
    "disputeID",
    "messageText",
    "disputeActivity",
    "shippingCarrierUsed",
    "shipmentTrackNumber",
    "shippingTime"
})
public class AddDisputeResponseRequestType
    extends AbstractRequestType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "DisputeID")
    protected String disputeID;
    @XmlElement(name = "MessageText")
    protected String messageText;
    @XmlElement(name = "DisputeActivity")
    protected DisputeActivityCodeType disputeActivity;
    @XmlElement(name = "ShippingCarrierUsed")
    protected String shippingCarrierUsed;
    @XmlElement(name = "ShipmentTrackNumber")
    protected String shipmentTrackNumber;
    @XmlElement(name = "ShippingTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar shippingTime;

    /**
     * Gets the value of the disputeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisputeID() {
        return disputeID;
    }

    /**
     * Sets the value of the disputeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisputeID(String value) {
        this.disputeID = value;
    }

    /**
     * Gets the value of the messageText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageText() {
        return messageText;
    }

    /**
     * Sets the value of the messageText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageText(String value) {
        this.messageText = value;
    }

    /**
     * Gets the value of the disputeActivity property.
     * 
     * @return
     *     possible object is
     *     {@link DisputeActivityCodeType }
     *     
     */
    public DisputeActivityCodeType getDisputeActivity() {
        return disputeActivity;
    }

    /**
     * Sets the value of the disputeActivity property.
     * 
     * @param value
     *     allowed object is
     *     {@link DisputeActivityCodeType }
     *     
     */
    public void setDisputeActivity(DisputeActivityCodeType value) {
        this.disputeActivity = value;
    }

    /**
     * Gets the value of the shippingCarrierUsed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShippingCarrierUsed() {
        return shippingCarrierUsed;
    }

    /**
     * Sets the value of the shippingCarrierUsed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShippingCarrierUsed(String value) {
        this.shippingCarrierUsed = value;
    }

    /**
     * Gets the value of the shipmentTrackNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipmentTrackNumber() {
        return shipmentTrackNumber;
    }

    /**
     * Sets the value of the shipmentTrackNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipmentTrackNumber(String value) {
        this.shipmentTrackNumber = value;
    }

    /**
     * Gets the value of the shippingTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getShippingTime() {
        return shippingTime;
    }

    /**
     * Sets the value of the shippingTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShippingTime(Calendar value) {
        this.shippingTime = value;
    }

}
