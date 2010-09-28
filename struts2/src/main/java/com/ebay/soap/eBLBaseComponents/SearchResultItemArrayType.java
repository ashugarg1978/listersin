
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
 * 				Container for a list of search result items, such as returned by
 * 				GetSearchResults. Will contain zero, one, or multiple
 * 				SearchResultItemType items, each of which represents one item listing
 * 				that was found by the search.
 * 			
 * 
 * <p>Java class for SearchResultItemArrayType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchResultItemArrayType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SearchResultItem" type="{urn:ebay:apis:eBLBaseComponents}SearchResultItemType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchResultItemArrayType", propOrder = {
    "searchResultItem"
})
public class SearchResultItemArrayType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "SearchResultItem")
    protected List<SearchResultItemType> searchResultItem;

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link SearchResultItemType }
     *     
     */
    public SearchResultItemType[] getSearchResultItem() {
        if (this.searchResultItem == null) {
            return new SearchResultItemType[ 0 ] ;
        }
        return ((SearchResultItemType[]) this.searchResultItem.toArray(new SearchResultItemType[this.searchResultItem.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link SearchResultItemType }
     *     
     */
    public SearchResultItemType getSearchResultItem(int idx) {
        if (this.searchResultItem == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.searchResultItem.get(idx);
    }

    public int getSearchResultItemLength() {
        if (this.searchResultItem == null) {
            return  0;
        }
        return this.searchResultItem.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link SearchResultItemType }
     *     
     */
    public void setSearchResultItem(SearchResultItemType[] values) {
        this._getSearchResultItem().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.searchResultItem.add(values[i]);
        }
    }

    protected List<SearchResultItemType> _getSearchResultItem() {
        if (searchResultItem == null) {
            searchResultItem = new ArrayList<SearchResultItemType>();
        }
        return searchResultItem;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link SearchResultItemType }
     *     
     */
    public SearchResultItemType setSearchResultItem(int idx, SearchResultItemType value) {
        return this.searchResultItem.set(idx, value);
    }

}
