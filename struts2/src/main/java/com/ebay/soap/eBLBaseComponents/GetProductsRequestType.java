
package com.ebay.soap.eBLBaseComponents;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Searches for stock information and reviews for certain kinds of products,
 * 				such as a particular digital camera model.
 * 				<p>
 * 				GetProducts is designed to be useful to applications that support
 * 				shopping comparison, product reviews, or basic supply and
 * 				demand data.
 * 				</p>
 * 				<p>
 * 				GetProducts also supports tracking so that members of the
 * 				eBay Affiliates Program can get commissions for driving traffic to eBay.
 * 				</p>
 * 				<p class="tablenote"><b>Note:</b>
 * 				For selling use cases, use GetProductSearchResults and
 * 				GetProductSellingPages instead.
 * 				</p>
 * 				<p>
 * 				To use this call, you typically pass in keywords, and GetProducts finds
 * 				products with matching words in the product title, description, and/or
 * 				Item Specifics.
 * 				<p>
 * 				For each product of interest, you call GetProducts again to retrieve
 * 				additional details that would be useful to buyers:
 * 				</p>
 * 				<ul>
 * 				<li>Top reviews of the product by eBay members,
 * 				including part of the review text, plus links to the full text on the
 * 				eBay Web site.</li>
 * 				<li>Relevant buying guides (shopping advice) written by
 * 				eBay members and by eBay staff, including part of the guide text,
 * 				plus links to the full text
 * 				on the eBay Web site.</li>
 * 				<li>Up to 200 matching items on eBay (if any). (To find more matching
 * 				items, use GetSearchResults.)</li>
 * 				</ul>
 * 				<p>
 * 				<span class="tablenote"><b>Note:</b>
 * 				As catalog queries can take longer than item queries,
 * 				GetProducts can be slower than GetSearchResults.
 * 				Also, due to the way product data is cached, you may get a faster response
 * 				when you run the same query a second time.</span>
 * 			
 * 
 * <p>Java class for GetProductsRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetProductsRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractRequestType">
 *       &lt;sequence>
 *         &lt;element name="ProductSearch" type="{urn:ebay:apis:eBLBaseComponents}ProductSearchType" minOccurs="0"/>
 *         &lt;element name="ProductSort" type="{urn:ebay:apis:eBLBaseComponents}ProductSortCodeType" minOccurs="0"/>
 *         &lt;element name="IncludeItemArray" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IncludeReviewDetails" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IncludeBuyingGuideDetails" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IncludeHistogram" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AffiliateTrackingDetails" type="{urn:ebay:apis:eBLBaseComponents}AffiliateTrackingDetailsType" minOccurs="0"/>
 *         &lt;element name="HideDuplicateItems" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetProductsRequestType", propOrder = {
    "productSearch",
    "productSort",
    "includeItemArray",
    "includeReviewDetails",
    "includeBuyingGuideDetails",
    "includeHistogram",
    "affiliateTrackingDetails",
    "hideDuplicateItems"
})
public class GetProductsRequestType
    extends AbstractRequestType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "ProductSearch")
    protected ProductSearchType productSearch;
    @XmlElement(name = "ProductSort")
    protected ProductSortCodeType productSort;
    @XmlElement(name = "IncludeItemArray")
    protected Boolean includeItemArray;
    @XmlElement(name = "IncludeReviewDetails")
    protected Boolean includeReviewDetails;
    @XmlElement(name = "IncludeBuyingGuideDetails")
    protected Boolean includeBuyingGuideDetails;
    @XmlElement(name = "IncludeHistogram")
    protected Boolean includeHistogram;
    @XmlElement(name = "AffiliateTrackingDetails")
    protected AffiliateTrackingDetailsType affiliateTrackingDetails;
    @XmlElement(name = "HideDuplicateItems")
    protected Boolean hideDuplicateItems;

    /**
     * Gets the value of the productSearch property.
     * 
     * @return
     *     possible object is
     *     {@link ProductSearchType }
     *     
     */
    public ProductSearchType getProductSearch() {
        return productSearch;
    }

    /**
     * Sets the value of the productSearch property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductSearchType }
     *     
     */
    public void setProductSearch(ProductSearchType value) {
        this.productSearch = value;
    }

    /**
     * Gets the value of the productSort property.
     * 
     * @return
     *     possible object is
     *     {@link ProductSortCodeType }
     *     
     */
    public ProductSortCodeType getProductSort() {
        return productSort;
    }

    /**
     * Sets the value of the productSort property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductSortCodeType }
     *     
     */
    public void setProductSort(ProductSortCodeType value) {
        this.productSort = value;
    }

    /**
     * Gets the value of the includeItemArray property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeItemArray() {
        return includeItemArray;
    }

    /**
     * Sets the value of the includeItemArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeItemArray(Boolean value) {
        this.includeItemArray = value;
    }

    /**
     * Gets the value of the includeReviewDetails property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeReviewDetails() {
        return includeReviewDetails;
    }

    /**
     * Sets the value of the includeReviewDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeReviewDetails(Boolean value) {
        this.includeReviewDetails = value;
    }

    /**
     * Gets the value of the includeBuyingGuideDetails property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeBuyingGuideDetails() {
        return includeBuyingGuideDetails;
    }

    /**
     * Sets the value of the includeBuyingGuideDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeBuyingGuideDetails(Boolean value) {
        this.includeBuyingGuideDetails = value;
    }

    /**
     * Gets the value of the includeHistogram property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeHistogram() {
        return includeHistogram;
    }

    /**
     * Sets the value of the includeHistogram property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeHistogram(Boolean value) {
        this.includeHistogram = value;
    }

    /**
     * Gets the value of the affiliateTrackingDetails property.
     * 
     * @return
     *     possible object is
     *     {@link AffiliateTrackingDetailsType }
     *     
     */
    public AffiliateTrackingDetailsType getAffiliateTrackingDetails() {
        return affiliateTrackingDetails;
    }

    /**
     * Sets the value of the affiliateTrackingDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link AffiliateTrackingDetailsType }
     *     
     */
    public void setAffiliateTrackingDetails(AffiliateTrackingDetailsType value) {
        this.affiliateTrackingDetails = value;
    }

    /**
     * Gets the value of the hideDuplicateItems property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHideDuplicateItems() {
        return hideDuplicateItems;
    }

    /**
     * Sets the value of the hideDuplicateItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHideDuplicateItems(Boolean value) {
        this.hideDuplicateItems = value;
    }

}
