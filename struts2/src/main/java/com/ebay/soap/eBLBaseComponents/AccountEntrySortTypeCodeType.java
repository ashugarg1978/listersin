
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccountEntrySortTypeCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AccountEntrySortTypeCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="AccountEntryCreatedTimeAscending"/>
 *     &lt;enumeration value="AccountEntryCreatedTimeDescending"/>
 *     &lt;enumeration value="AccountEntryItemNumberAscending"/>
 *     &lt;enumeration value="AccountEntryItemNumberDescending"/>
 *     &lt;enumeration value="AccountEntryFeeTypeAscending"/>
 *     &lt;enumeration value="AccountEntryFeeTypeDescending"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AccountEntrySortTypeCodeType")
@XmlEnum
public enum AccountEntrySortTypeCodeType {


    /**
     * 
     * 					 (in) Sort by the AccountEntry.Date value in the response,
     * 					 in ascending order. Same as AccountEntryCreatedTimeAscending.
     * 					
     * 
     */
    @XmlEnumValue("None")
    NONE("None"),

    /**
     * 
     * 					 (in) Sort by the AccountEntry.Date value in the response,
     * 					 in ascending order.
     * 					
     * 
     */
    @XmlEnumValue("AccountEntryCreatedTimeAscending")
    ACCOUNT_ENTRY_CREATED_TIME_ASCENDING("AccountEntryCreatedTimeAscending"),

    /**
     * 
     * 					(in) Sort by the AccountEntry.Date value in the response, in
     * 					descending order.
     * 					
     * 
     */
    @XmlEnumValue("AccountEntryCreatedTimeDescending")
    ACCOUNT_ENTRY_CREATED_TIME_DESCENDING("AccountEntryCreatedTimeDescending"),

    /**
     * 
     * 					(in) Sort by the AccountEntry.ItemID value in the response, in
     * 					ascending order.
     * 					
     * 
     */
    @XmlEnumValue("AccountEntryItemNumberAscending")
    ACCOUNT_ENTRY_ITEM_NUMBER_ASCENDING("AccountEntryItemNumberAscending"),

    /**
     * 
     * 					(in) Sort by the AccountEntry.ItemID value in the response,
     * 					in descending order.
     * 					
     * 
     */
    @XmlEnumValue("AccountEntryItemNumberDescending")
    ACCOUNT_ENTRY_ITEM_NUMBER_DESCENDING("AccountEntryItemNumberDescending"),

    /**
     * 
     * 					(in) Sort by the value returned in AccountEntry.Description,
     * 					with entries starting with lowercase letters before entries starting
     * 					with uppercase letters and each group in alphabetical order.
     * 					
     * 
     */
    @XmlEnumValue("AccountEntryFeeTypeAscending")
    ACCOUNT_ENTRY_FEE_TYPE_ASCENDING("AccountEntryFeeTypeAscending"),

    /**
     * 
     * 					(in) Sort by the value returned in AccountEntry.Description, with entries
     * 					starting with uppercase letters before entries starting with lowercase
     * 					letters and each group in reverse alphabetical order.
     * 					
     * 
     */
    @XmlEnumValue("AccountEntryFeeTypeDescending")
    ACCOUNT_ENTRY_FEE_TYPE_DESCENDING("AccountEntryFeeTypeDescending"),

    /**
     * 
     * 						(out) Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    AccountEntrySortTypeCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AccountEntrySortTypeCodeType fromValue(String v) {
        for (AccountEntrySortTypeCodeType c: AccountEntrySortTypeCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
