
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
 * 				Returns data that indicates the categories that are mapped to characteristics sets,
 * 				for the eBay site to which the call was routed.
 * 				Retrieves all mappings or just the one that matches the category ID passed as input.
 * 				The data is returned in a CategoryArrayType object, which can contain multiple mappings.
 * 				The response also contains information about categories for which the mappings have changed.<br>
 * 				<br>
 * 				<span class="tablenote"><b>Note:</b> The Pre-filled Item Information feature depends on the Item Specifics feature.
 * 				This means the set of catalog-enabled categories is a subset of the categories
 * 				that are mapped to characteristic sets. That is, there are no catalog-enabled categories
 * 				that are not mapped to characteristic sets.</span>
 * 			
 * 
 * <p>Java class for GetCategory2CSResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetCategory2CSResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractResponseType">
 *       &lt;sequence>
 *         &lt;element name="MappedCategoryArray" type="{urn:ebay:apis:eBLBaseComponents}CategoryArrayType" minOccurs="0"/>
 *         &lt;element name="UnmappedCategoryArray" type="{urn:ebay:apis:eBLBaseComponents}CategoryArrayType" minOccurs="0"/>
 *         &lt;element name="AttributeSystemVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SiteWideCharacteristicSets" type="{urn:ebay:apis:eBLBaseComponents}SiteWideCharacteristicsType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetCategory2CSResponseType", propOrder = {
    "mappedCategoryArray",
    "unmappedCategoryArray",
    "attributeSystemVersion",
    "siteWideCharacteristicSets"
})
public class GetCategory2CSResponseType
    extends AbstractResponseType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "MappedCategoryArray")
    protected CategoryArrayType mappedCategoryArray;
    @XmlElement(name = "UnmappedCategoryArray")
    protected CategoryArrayType unmappedCategoryArray;
    @XmlElement(name = "AttributeSystemVersion")
    protected String attributeSystemVersion;
    @XmlElement(name = "SiteWideCharacteristicSets")
    protected List<SiteWideCharacteristicsType> siteWideCharacteristicSets;

    /**
     * Gets the value of the mappedCategoryArray property.
     * 
     * @return
     *     possible object is
     *     {@link CategoryArrayType }
     *     
     */
    public CategoryArrayType getMappedCategoryArray() {
        return mappedCategoryArray;
    }

    /**
     * Sets the value of the mappedCategoryArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoryArrayType }
     *     
     */
    public void setMappedCategoryArray(CategoryArrayType value) {
        this.mappedCategoryArray = value;
    }

    /**
     * Gets the value of the unmappedCategoryArray property.
     * 
     * @return
     *     possible object is
     *     {@link CategoryArrayType }
     *     
     */
    public CategoryArrayType getUnmappedCategoryArray() {
        return unmappedCategoryArray;
    }

    /**
     * Sets the value of the unmappedCategoryArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoryArrayType }
     *     
     */
    public void setUnmappedCategoryArray(CategoryArrayType value) {
        this.unmappedCategoryArray = value;
    }

    /**
     * Gets the value of the attributeSystemVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributeSystemVersion() {
        return attributeSystemVersion;
    }

    /**
     * Sets the value of the attributeSystemVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributeSystemVersion(String value) {
        this.attributeSystemVersion = value;
    }

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link SiteWideCharacteristicsType }
     *     
     */
    public SiteWideCharacteristicsType[] getSiteWideCharacteristicSets() {
        if (this.siteWideCharacteristicSets == null) {
            return new SiteWideCharacteristicsType[ 0 ] ;
        }
        return ((SiteWideCharacteristicsType[]) this.siteWideCharacteristicSets.toArray(new SiteWideCharacteristicsType[this.siteWideCharacteristicSets.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link SiteWideCharacteristicsType }
     *     
     */
    public SiteWideCharacteristicsType getSiteWideCharacteristicSets(int idx) {
        if (this.siteWideCharacteristicSets == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.siteWideCharacteristicSets.get(idx);
    }

    public int getSiteWideCharacteristicSetsLength() {
        if (this.siteWideCharacteristicSets == null) {
            return  0;
        }
        return this.siteWideCharacteristicSets.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link SiteWideCharacteristicsType }
     *     
     */
    public void setSiteWideCharacteristicSets(SiteWideCharacteristicsType[] values) {
        this._getSiteWideCharacteristicSets().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.siteWideCharacteristicSets.add(values[i]);
        }
    }

    protected List<SiteWideCharacteristicsType> _getSiteWideCharacteristicSets() {
        if (siteWideCharacteristicSets == null) {
            siteWideCharacteristicSets = new ArrayList<SiteWideCharacteristicsType>();
        }
        return siteWideCharacteristicSets;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link SiteWideCharacteristicsType }
     *     
     */
    public SiteWideCharacteristicsType setSiteWideCharacteristicSets(int idx, SiteWideCharacteristicsType value) {
        return this.siteWideCharacteristicSets.set(idx, value);
    }

}
