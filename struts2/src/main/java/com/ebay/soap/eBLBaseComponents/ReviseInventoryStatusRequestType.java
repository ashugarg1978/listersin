
package com.ebay.soap.eBLBaseComponents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Enables a seller to change the price and quantity of a currently-
 * 				active, fixed-price listing.
 * 				<br>
 * 				<br>
 * 				Be aware that changing the price or quantity of a currently-
 * 				active, fixed-price listing does not reset the Best Match performance score.
 * 				For Best Match information related to multi-variation listings, see the Best
 * 				Match information at the following topic:
 * 				<a href="http://pages.ebay.com/sell/variation/">Multi-quantity Fixed Price
 * 				listings with variations</a>.
 * 				<b>Note:</b> When you use ReviseInventoryStatus within a Merchant Data file,
 * 				it must be enclosed within two BulkDataExchangeRequest tags.
 * 				After release 637, a namespace is returned in the BulkDataExchangeResponseType
 * 				(top level) of the response. The BulkDataExchange tags are not shown in the call
 * 				input/output descriptions.
 * 			
 * 
 * <p>Java class for ReviseInventoryStatusRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReviseInventoryStatusRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractRequestType">
 *       &lt;sequence>
 *         &lt;element name="InventoryStatus" type="{urn:ebay:apis:eBLBaseComponents}InventoryStatusType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReviseInventoryStatusRequestType", propOrder = {
    "inventoryStatus"
})
public class ReviseInventoryStatusRequestType
    extends AbstractRequestType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "InventoryStatus")
    protected List<InventoryStatusType> inventoryStatus;

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link InventoryStatusType }
     *     
     */
    public InventoryStatusType[] getInventoryStatus() {
        if (this.inventoryStatus == null) {
            return new InventoryStatusType[ 0 ] ;
        }
        return ((InventoryStatusType[]) this.inventoryStatus.toArray(new InventoryStatusType[this.inventoryStatus.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link InventoryStatusType }
     *     
     */
    public InventoryStatusType getInventoryStatus(int idx) {
        if (this.inventoryStatus == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.inventoryStatus.get(idx);
    }

    public int getInventoryStatusLength() {
        if (this.inventoryStatus == null) {
            return  0;
        }
        return this.inventoryStatus.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link InventoryStatusType }
     *     
     */
    public void setInventoryStatus(InventoryStatusType[] values) {
        this._getInventoryStatus().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.inventoryStatus.add(values[i]);
        }
    }

    protected List<InventoryStatusType> _getInventoryStatus() {
        if (inventoryStatus == null) {
            inventoryStatus = new ArrayList<InventoryStatusType>();
        }
        return inventoryStatus;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link InventoryStatusType }
     *     
     */
    public InventoryStatusType setInventoryStatus(int idx, InventoryStatusType value) {
        return this.inventoryStatus.set(idx, value);
    }

}
