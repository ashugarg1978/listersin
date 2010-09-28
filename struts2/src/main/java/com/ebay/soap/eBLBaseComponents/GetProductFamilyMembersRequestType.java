
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
 * GetProductFamilyMembers is intended to be used combination with GetProductSearchResults.
 * If a search result returned from GetProductSearchResults does not return all
 * product versions in a family, and if the user wants to see more versions (editions)
 * of the product, you can use GetProductFamilyMembers to retrieve all versions of the product.
 * That is, if GetProductSearchResultsonly returns the product family header (ParentProduct),
 * use this call to zoom in on a particular family of product versions.
 * (This situation usually occurs when you call GetProductSearchResults and more more
 * matches are found than the MaxChildrenPerFamily value you specified.)<br>
 * <br>
 * The structure of tGetProductFamilyMembers is similar to that of GetProductSearchResults.
 * Instead of passing in a query, you pass in a product ID. This product ID is used to
 * find all the members of the product family that the specified product is a member of.
 * The results are compatible with the results from GetProductSearchResults,
 * so similar application logic can be used to handle both requests and responses.
 * This call supports batch requests. This means you can retrieve products in
 * multiple families by using a single request. To perform a batch request,
 * pass an array of ProductSearch objects in the call.<br>
 * <br>
 * For each ProductSearch object, GetProductFamilyMembers returns a list of all the
 * products in the specified product family. Each product is represented as a list of
 * attributes (Item Specifics) plus other identifying information, such as a product ID
 * and a stock photo.<br>
 * <br>
 * Once the user selects a product from the results, pass its ID in a GetProductSellingPages
 * request to retrieve more detailed information about the product, including a set of
 * optional Item Specifics that the seller can use in addition to the
 * pre-filled Item Specifics (see GetProductSellingPages). <br>
 * <br>
 * To use this data in a listing, pass the product ID and the optional Item Specifics
 * in the seller's listing request (AddItem).
 * 			
 * 
 * <p>Java class for GetProductFamilyMembersRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetProductFamilyMembersRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractRequestType">
 *       &lt;sequence>
 *         &lt;element name="ProductSearch" type="{urn:ebay:apis:eBLBaseComponents}ProductSearchType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetProductFamilyMembersRequestType", propOrder = {
    "productSearch"
})
public class GetProductFamilyMembersRequestType
    extends AbstractRequestType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "ProductSearch")
    protected List<ProductSearchType> productSearch;

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link ProductSearchType }
     *     
     */
    public ProductSearchType[] getProductSearch() {
        if (this.productSearch == null) {
            return new ProductSearchType[ 0 ] ;
        }
        return ((ProductSearchType[]) this.productSearch.toArray(new ProductSearchType[this.productSearch.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link ProductSearchType }
     *     
     */
    public ProductSearchType getProductSearch(int idx) {
        if (this.productSearch == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.productSearch.get(idx);
    }

    public int getProductSearchLength() {
        if (this.productSearch == null) {
            return  0;
        }
        return this.productSearch.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link ProductSearchType }
     *     
     */
    public void setProductSearch(ProductSearchType[] values) {
        this._getProductSearch().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.productSearch.add(values[i]);
        }
    }

    protected List<ProductSearchType> _getProductSearch() {
        if (productSearch == null) {
            productSearch = new ArrayList<ProductSearchType>();
        }
        return productSearch;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link ProductSearchType }
     *     
     */
    public ProductSearchType setProductSearch(int idx, ProductSearchType value) {
        return this.productSearch.set(idx, value);
    }

}
