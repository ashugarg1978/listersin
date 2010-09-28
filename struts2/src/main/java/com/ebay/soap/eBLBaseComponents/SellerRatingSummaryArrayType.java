
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
 * 				Contains DSR feedback metrics for different periods.
 * 			
 * 
 * <p>Java class for SellerRatingSummaryArrayType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SellerRatingSummaryArrayType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AverageRatingSummary" type="{urn:ebay:apis:eBLBaseComponents}AverageRatingSummaryType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SellerRatingSummaryArrayType", propOrder = {
    "averageRatingSummary"
})
public class SellerRatingSummaryArrayType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "AverageRatingSummary")
    protected List<AverageRatingSummaryType> averageRatingSummary;

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link AverageRatingSummaryType }
     *     
     */
    public AverageRatingSummaryType[] getAverageRatingSummary() {
        if (this.averageRatingSummary == null) {
            return new AverageRatingSummaryType[ 0 ] ;
        }
        return ((AverageRatingSummaryType[]) this.averageRatingSummary.toArray(new AverageRatingSummaryType[this.averageRatingSummary.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link AverageRatingSummaryType }
     *     
     */
    public AverageRatingSummaryType getAverageRatingSummary(int idx) {
        if (this.averageRatingSummary == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.averageRatingSummary.get(idx);
    }

    public int getAverageRatingSummaryLength() {
        if (this.averageRatingSummary == null) {
            return  0;
        }
        return this.averageRatingSummary.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link AverageRatingSummaryType }
     *     
     */
    public void setAverageRatingSummary(AverageRatingSummaryType[] values) {
        this._getAverageRatingSummary().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.averageRatingSummary.add(values[i]);
        }
    }

    protected List<AverageRatingSummaryType> _getAverageRatingSummary() {
        if (averageRatingSummary == null) {
            averageRatingSummary = new ArrayList<AverageRatingSummaryType>();
        }
        return averageRatingSummary;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link AverageRatingSummaryType }
     *     
     */
    public AverageRatingSummaryType setAverageRatingSummary(int idx, AverageRatingSummaryType value) {
        return this.averageRatingSummary.set(idx, value);
    }

}
