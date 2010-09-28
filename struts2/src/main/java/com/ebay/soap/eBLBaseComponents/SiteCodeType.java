
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SiteCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SiteCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="US"/>
 *     &lt;enumeration value="Canada"/>
 *     &lt;enumeration value="UK"/>
 *     &lt;enumeration value="Australia"/>
 *     &lt;enumeration value="Austria"/>
 *     &lt;enumeration value="Belgium_French"/>
 *     &lt;enumeration value="France"/>
 *     &lt;enumeration value="Germany"/>
 *     &lt;enumeration value="Italy"/>
 *     &lt;enumeration value="Belgium_Dutch"/>
 *     &lt;enumeration value="Netherlands"/>
 *     &lt;enumeration value="Spain"/>
 *     &lt;enumeration value="Switzerland"/>
 *     &lt;enumeration value="Taiwan"/>
 *     &lt;enumeration value="eBayMotors"/>
 *     &lt;enumeration value="HongKong"/>
 *     &lt;enumeration value="Singapore"/>
 *     &lt;enumeration value="India"/>
 *     &lt;enumeration value="China"/>
 *     &lt;enumeration value="Ireland"/>
 *     &lt;enumeration value="Malaysia"/>
 *     &lt;enumeration value="Philippines"/>
 *     &lt;enumeration value="Poland"/>
 *     &lt;enumeration value="Sweden"/>
 *     &lt;enumeration value="CustomCode"/>
 *     &lt;enumeration value="CanadaFrench"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SiteCodeType")
@XmlEnum
public enum SiteCodeType {


    /**
     * 
     * 						USA, site ID 0, abbreviation US, currency USD.
     * 					
     * 
     */
    US("US"),

    /**
     * 
     * 						Canada, site ID 2, abbreviation CA, currencies CAD and USD.
     * 					
     * 
     */
    @XmlEnumValue("Canada")
    CANADA("Canada"),

    /**
     * 
     * 						United Kingdom, site ID 3, abbreviation UK, currency GBP.
     * 					
     * 
     */
    UK("UK"),

    /**
     * 
     * 						Australia, site ID 15, abbreviation AU, currency AUD.
     * 					
     * 
     */
    @XmlEnumValue("Australia")
    AUSTRALIA("Australia"),

    /**
     * 
     * 						Austria, site ID 16, abbreviation AT, currency EUR.
     * 					
     * 
     */
    @XmlEnumValue("Austria")
    AUSTRIA("Austria"),

    /**
     * 
     * 						Belgium (French), site ID 23, abbreviation BEFR, currency EUR.
     * 					
     * 
     */
    @XmlEnumValue("Belgium_French")
    BELGIUM_FRENCH("Belgium_French"),

    /**
     * 
     * 						France, site ID 71, abbreviation FR, currency EUR.
     * 					
     * 
     */
    @XmlEnumValue("France")
    FRANCE("France"),

    /**
     * 
     * 						Germany, site ID 77, abbreviation DE, currency EUR.
     * 					
     * 
     */
    @XmlEnumValue("Germany")
    GERMANY("Germany"),

    /**
     * 
     * 						Italy, site ID 101, abbreviation IT, currency EUR.
     * 					
     * 
     */
    @XmlEnumValue("Italy")
    ITALY("Italy"),

    /**
     * 
     * 						Belgium (Dutch), site ID 123, abbreviation BENL, currency EUR.
     * 					
     * 
     */
    @XmlEnumValue("Belgium_Dutch")
    BELGIUM_DUTCH("Belgium_Dutch"),

    /**
     * 
     * 						Netherlands, site ID 146, abbreviation NL, currency EUR.
     * 					
     * 
     */
    @XmlEnumValue("Netherlands")
    NETHERLANDS("Netherlands"),

    /**
     * 
     * 						Spain, site ID 186, abbreviation ES, currency EUR.
     * 					
     * 
     */
    @XmlEnumValue("Spain")
    SPAIN("Spain"),

    /**
     * 
     * 						Switzerland, site ID 193, abbreviation CH, currency CHF.
     * 					
     * 
     */
    @XmlEnumValue("Switzerland")
    SWITZERLAND("Switzerland"),

    /**
     * 
     * 						Taiwan, currency TWD. Note that the old eBay Taiwan site is no longer in operation, and the new site does not use this API.
     * 					
     * 
     */
    @XmlEnumValue("Taiwan")
    TAIWAN("Taiwan"),

    /**
     * 
     * 						eBay Motors, site ID 100, currency USD.
     * 					
     * 
     */
    @XmlEnumValue("eBayMotors")
    E_BAY_MOTORS("eBayMotors"),

    /**
     * 
     * 						Hong Kong, site ID 201, abbreviation HK, currency HKD.
     * 					
     * 
     */
    @XmlEnumValue("HongKong")
    HONG_KONG("HongKong"),

    /**
     * 
     * 						Singapore, site ID 216, abbreviation SG, currency SGD.
     * 					
     * 
     */
    @XmlEnumValue("Singapore")
    SINGAPORE("Singapore"),

    /**
     * 
     * 						India, site ID 203, abbreviation IN, currency INR.
     * 					
     * 
     */
    @XmlEnumValue("India")
    INDIA("India"),

    /**
     * 
     * 						China, currency CNY. Note that there is no longer an eBay China site.
     * 					
     * 
     */
    @XmlEnumValue("China")
    CHINA("China"),

    /**
     * 
     * 						Ireland, site ID 205, abbreviation IE, currency EUR.
     * 					
     * 
     */
    @XmlEnumValue("Ireland")
    IRELAND("Ireland"),

    /**
     * 
     * 						Malaysia, site ID 207, abbreviation MY, currency MYR.
     * 					
     * 
     */
    @XmlEnumValue("Malaysia")
    MALAYSIA("Malaysia"),

    /**
     * 
     * 						Philippines, site ID 211, abbreviation PH, currency PHP.
     * 					
     * 
     */
    @XmlEnumValue("Philippines")
    PHILIPPINES("Philippines"),

    /**
     * 
     * 						Poland, site ID 212, abbreviation PL, currency PLN.
     * 					
     * 
     */
    @XmlEnumValue("Poland")
    POLAND("Poland"),

    /**
     * 
     * 						Sweden, site ID 218, abbreviation SE, currency SEK.
     * 					
     * 
     */
    @XmlEnumValue("Sweden")
    SWEDEN("Sweden"),

    /**
     * 
     * 						Reserved for internal or future use
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode"),

    /**
     * 
     * 						CanadaFrench, site ID 210, abbreviation CAFR, currencies CAD and USD.
     * 					
     * 
     */
    @XmlEnumValue("CanadaFrench")
    CANADA_FRENCH("CanadaFrench");
    private final String value;

    SiteCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SiteCodeType fromValue(String v) {
        for (SiteCodeType c: SiteCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
