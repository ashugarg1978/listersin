
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CurrencyCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CurrencyCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="AFA"/>
 *     &lt;enumeration value="ALL"/>
 *     &lt;enumeration value="DZD"/>
 *     &lt;enumeration value="ADP"/>
 *     &lt;enumeration value="AOA"/>
 *     &lt;enumeration value="ARS"/>
 *     &lt;enumeration value="AMD"/>
 *     &lt;enumeration value="AWG"/>
 *     &lt;enumeration value="AZM"/>
 *     &lt;enumeration value="BSD"/>
 *     &lt;enumeration value="BHD"/>
 *     &lt;enumeration value="BDT"/>
 *     &lt;enumeration value="BBD"/>
 *     &lt;enumeration value="BYR"/>
 *     &lt;enumeration value="BZD"/>
 *     &lt;enumeration value="BMD"/>
 *     &lt;enumeration value="BTN"/>
 *     &lt;enumeration value="INR"/>
 *     &lt;enumeration value="BOV"/>
 *     &lt;enumeration value="BOB"/>
 *     &lt;enumeration value="BAM"/>
 *     &lt;enumeration value="BWP"/>
 *     &lt;enumeration value="BRL"/>
 *     &lt;enumeration value="BND"/>
 *     &lt;enumeration value="BGL"/>
 *     &lt;enumeration value="BGN"/>
 *     &lt;enumeration value="BIF"/>
 *     &lt;enumeration value="KHR"/>
 *     &lt;enumeration value="CAD"/>
 *     &lt;enumeration value="CVE"/>
 *     &lt;enumeration value="KYD"/>
 *     &lt;enumeration value="XAF"/>
 *     &lt;enumeration value="CLF"/>
 *     &lt;enumeration value="CLP"/>
 *     &lt;enumeration value="CNY"/>
 *     &lt;enumeration value="COP"/>
 *     &lt;enumeration value="KMF"/>
 *     &lt;enumeration value="CDF"/>
 *     &lt;enumeration value="CRC"/>
 *     &lt;enumeration value="HRK"/>
 *     &lt;enumeration value="CUP"/>
 *     &lt;enumeration value="CYP"/>
 *     &lt;enumeration value="CZK"/>
 *     &lt;enumeration value="DKK"/>
 *     &lt;enumeration value="DJF"/>
 *     &lt;enumeration value="DOP"/>
 *     &lt;enumeration value="TPE"/>
 *     &lt;enumeration value="ECV"/>
 *     &lt;enumeration value="ECS"/>
 *     &lt;enumeration value="EGP"/>
 *     &lt;enumeration value="SVC"/>
 *     &lt;enumeration value="ERN"/>
 *     &lt;enumeration value="EEK"/>
 *     &lt;enumeration value="ETB"/>
 *     &lt;enumeration value="FKP"/>
 *     &lt;enumeration value="FJD"/>
 *     &lt;enumeration value="GMD"/>
 *     &lt;enumeration value="GEL"/>
 *     &lt;enumeration value="GHC"/>
 *     &lt;enumeration value="GIP"/>
 *     &lt;enumeration value="GTQ"/>
 *     &lt;enumeration value="GNF"/>
 *     &lt;enumeration value="GWP"/>
 *     &lt;enumeration value="GYD"/>
 *     &lt;enumeration value="HTG"/>
 *     &lt;enumeration value="HNL"/>
 *     &lt;enumeration value="HKD"/>
 *     &lt;enumeration value="HUF"/>
 *     &lt;enumeration value="ISK"/>
 *     &lt;enumeration value="IDR"/>
 *     &lt;enumeration value="IRR"/>
 *     &lt;enumeration value="IQD"/>
 *     &lt;enumeration value="ILS"/>
 *     &lt;enumeration value="JMD"/>
 *     &lt;enumeration value="JPY"/>
 *     &lt;enumeration value="JOD"/>
 *     &lt;enumeration value="KZT"/>
 *     &lt;enumeration value="KES"/>
 *     &lt;enumeration value="AUD"/>
 *     &lt;enumeration value="KPW"/>
 *     &lt;enumeration value="KRW"/>
 *     &lt;enumeration value="KWD"/>
 *     &lt;enumeration value="KGS"/>
 *     &lt;enumeration value="LAK"/>
 *     &lt;enumeration value="LVL"/>
 *     &lt;enumeration value="LBP"/>
 *     &lt;enumeration value="LSL"/>
 *     &lt;enumeration value="LRD"/>
 *     &lt;enumeration value="LYD"/>
 *     &lt;enumeration value="CHF"/>
 *     &lt;enumeration value="LTL"/>
 *     &lt;enumeration value="MOP"/>
 *     &lt;enumeration value="MKD"/>
 *     &lt;enumeration value="MGF"/>
 *     &lt;enumeration value="MWK"/>
 *     &lt;enumeration value="MYR"/>
 *     &lt;enumeration value="MVR"/>
 *     &lt;enumeration value="MTL"/>
 *     &lt;enumeration value="EUR"/>
 *     &lt;enumeration value="MRO"/>
 *     &lt;enumeration value="MUR"/>
 *     &lt;enumeration value="MXN"/>
 *     &lt;enumeration value="MXV"/>
 *     &lt;enumeration value="MDL"/>
 *     &lt;enumeration value="MNT"/>
 *     &lt;enumeration value="XCD"/>
 *     &lt;enumeration value="MZM"/>
 *     &lt;enumeration value="MMK"/>
 *     &lt;enumeration value="ZAR"/>
 *     &lt;enumeration value="NAD"/>
 *     &lt;enumeration value="NPR"/>
 *     &lt;enumeration value="ANG"/>
 *     &lt;enumeration value="XPF"/>
 *     &lt;enumeration value="NZD"/>
 *     &lt;enumeration value="NIO"/>
 *     &lt;enumeration value="NGN"/>
 *     &lt;enumeration value="NOK"/>
 *     &lt;enumeration value="OMR"/>
 *     &lt;enumeration value="PKR"/>
 *     &lt;enumeration value="PAB"/>
 *     &lt;enumeration value="PGK"/>
 *     &lt;enumeration value="PYG"/>
 *     &lt;enumeration value="PEN"/>
 *     &lt;enumeration value="PHP"/>
 *     &lt;enumeration value="PLN"/>
 *     &lt;enumeration value="USD"/>
 *     &lt;enumeration value="QAR"/>
 *     &lt;enumeration value="ROL"/>
 *     &lt;enumeration value="RUB"/>
 *     &lt;enumeration value="RUR"/>
 *     &lt;enumeration value="RWF"/>
 *     &lt;enumeration value="SHP"/>
 *     &lt;enumeration value="WST"/>
 *     &lt;enumeration value="STD"/>
 *     &lt;enumeration value="SAR"/>
 *     &lt;enumeration value="SCR"/>
 *     &lt;enumeration value="SLL"/>
 *     &lt;enumeration value="SGD"/>
 *     &lt;enumeration value="SKK"/>
 *     &lt;enumeration value="SIT"/>
 *     &lt;enumeration value="SBD"/>
 *     &lt;enumeration value="SOS"/>
 *     &lt;enumeration value="LKR"/>
 *     &lt;enumeration value="SDD"/>
 *     &lt;enumeration value="SRG"/>
 *     &lt;enumeration value="SZL"/>
 *     &lt;enumeration value="SEK"/>
 *     &lt;enumeration value="SYP"/>
 *     &lt;enumeration value="TWD"/>
 *     &lt;enumeration value="TJS"/>
 *     &lt;enumeration value="TZS"/>
 *     &lt;enumeration value="THB"/>
 *     &lt;enumeration value="XOF"/>
 *     &lt;enumeration value="TOP"/>
 *     &lt;enumeration value="TTD"/>
 *     &lt;enumeration value="TND"/>
 *     &lt;enumeration value="TRL"/>
 *     &lt;enumeration value="TMM"/>
 *     &lt;enumeration value="UGX"/>
 *     &lt;enumeration value="UAH"/>
 *     &lt;enumeration value="AED"/>
 *     &lt;enumeration value="GBP"/>
 *     &lt;enumeration value="USS"/>
 *     &lt;enumeration value="USN"/>
 *     &lt;enumeration value="UYU"/>
 *     &lt;enumeration value="UZS"/>
 *     &lt;enumeration value="VUV"/>
 *     &lt;enumeration value="VEB"/>
 *     &lt;enumeration value="VND"/>
 *     &lt;enumeration value="MAD"/>
 *     &lt;enumeration value="YER"/>
 *     &lt;enumeration value="YUM"/>
 *     &lt;enumeration value="ZMK"/>
 *     &lt;enumeration value="ZWD"/>
 *     &lt;enumeration value="ATS"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CurrencyCodeType")
@XmlEnum
public enum CurrencyCodeType {

    AFA("AFA"),
    ALL("ALL"),
    DZD("DZD"),
    ADP("ADP"),
    AOA("AOA"),
    ARS("ARS"),
    AMD("AMD"),
    AWG("AWG"),
    AZM("AZM"),
    BSD("BSD"),
    BHD("BHD"),
    BDT("BDT"),
    BBD("BBD"),
    BYR("BYR"),
    BZD("BZD"),
    BMD("BMD"),
    BTN("BTN"),

    /**
     * 
     * 					Indian Rupee.
     * 					For eBay, you can only specify this currency for listings you submit to the
     * 					India site (site ID 203).
     * 					
     * 
     */
    INR("INR"),
    BOV("BOV"),
    BOB("BOB"),
    BAM("BAM"),
    BWP("BWP"),
    BRL("BRL"),
    BND("BND"),
    BGL("BGL"),
    BGN("BGN"),
    BIF("BIF"),
    KHR("KHR"),

    /**
     * 
     * 						Canadian Dollar.
     * 						For eBay, you can only specify this currency for listings you submit to the
     * 						Canada site (site ID 2)
     * 						(Items listed on the Canada site can also specify USD.)
     * 					
     * 
     */
    CAD("CAD"),
    CVE("CVE"),
    KYD("KYD"),
    XAF("XAF"),
    CLF("CLF"),
    CLP("CLP"),
    CNY("CNY"),
    COP("COP"),
    KMF("KMF"),
    CDF("CDF"),
    CRC("CRC"),
    HRK("HRK"),
    CUP("CUP"),
    CYP("CYP"),
    CZK("CZK"),
    DKK("DKK"),
    DJF("DJF"),
    DOP("DOP"),
    TPE("TPE"),
    ECV("ECV"),
    ECS("ECS"),
    EGP("EGP"),
    SVC("SVC"),
    ERN("ERN"),
    EEK("EEK"),
    ETB("ETB"),
    FKP("FKP"),
    FJD("FJD"),
    GMD("GMD"),
    GEL("GEL"),
    GHC("GHC"),
    GIP("GIP"),
    GTQ("GTQ"),
    GNF("GNF"),
    GWP("GWP"),
    GYD("GYD"),
    HTG("HTG"),
    HNL("HNL"),

    /**
     * 
     * 						Hong Kong Dollar.
     * 						For eBay, you can only specify this currency for listings you submit to the
     * 						Hong Kong site (site ID 201).
     * 					
     * 
     */
    HKD("HKD"),
    HUF("HUF"),
    ISK("ISK"),
    IDR("IDR"),
    IRR("IRR"),
    IQD("IQD"),
    ILS("ILS"),
    JMD("JMD"),
    JPY("JPY"),
    JOD("JOD"),
    KZT("KZT"),
    KES("KES"),

    /**
     * 
     * 						Australian Dollar.
     * 						For eBay, you can only specify this currency for listings you submit to the
     * 						Australia site (site ID 15).
     * 					
     * 
     */
    AUD("AUD"),
    KPW("KPW"),
    KRW("KRW"),
    KWD("KWD"),
    KGS("KGS"),
    LAK("LAK"),
    LVL("LVL"),
    LBP("LBP"),
    LSL("LSL"),
    LRD("LRD"),
    LYD("LYD"),

    /**
     * 
     * 						Swiss Franc.
     * 						For eBay, you can only specify this currency for listings you submit to the
     * 						Switzerland site (site ID 193).
     * 					
     * 
     */
    CHF("CHF"),
    LTL("LTL"),
    MOP("MOP"),
    MKD("MKD"),
    MGF("MGF"),
    MWK("MWK"),

    /**
     * 
     * 						Malaysian Ringgit.
     * 						For eBay, you can only specify this currency for listings you submit to the
     * 						Malaysia site (site ID 207).
     * 					
     * 
     */
    MYR("MYR"),
    MVR("MVR"),
    MTL("MTL"),

    /**
     * 
     * 						Euro.
     * 						For eBay, you can only specify this currency for listings you submit to these sites:
     * 						Austria (site 16), Belgium_French (site 23),
     * 						France (site 71), Germany (site 77), Italy (site 101), Belgium_Dutch (site 123),
     * 						Netherlands (site 146), Spain (site 186), Ireland (site 205).
     * 					
     * 
     */
    EUR("EUR"),
    MRO("MRO"),
    MUR("MUR"),
    MXN("MXN"),
    MXV("MXV"),
    MDL("MDL"),
    MNT("MNT"),
    XCD("XCD"),
    MZM("MZM"),
    MMK("MMK"),
    ZAR("ZAR"),
    NAD("NAD"),
    NPR("NPR"),
    ANG("ANG"),
    XPF("XPF"),
    NZD("NZD"),
    NIO("NIO"),
    NGN("NGN"),
    NOK("NOK"),
    OMR("OMR"),
    PKR("PKR"),
    PAB("PAB"),
    PGK("PGK"),
    PYG("PYG"),
    PEN("PEN"),

    /**
     * 
     * 						Philippines Peso.
     * 						For eBay, you can only specify this currency for listings you submit to the
     * 						Philippines site (site ID 211).
     * 					
     * 
     */
    PHP("PHP"),

    /**
     * 
     * 						Poland, Zloty.
     * 						For eBay, you can only specify this currency for listings you submit to the
     * 						Poland site (site ID 212).
     * 					
     * 
     */
    PLN("PLN"),

    /**
     * 
     * 						US Dollar.
     * 						For eBay, you can only specify this currency for listings you submit to the
     * 						US (site ID 0), eBayMotors (site 100), and Canada (site 2) sites.
     * 					
     * 
     */
    USD("USD"),
    QAR("QAR"),
    ROL("ROL"),
    RUB("RUB"),
    RUR("RUR"),
    RWF("RWF"),
    SHP("SHP"),
    WST("WST"),
    STD("STD"),
    SAR("SAR"),
    SCR("SCR"),
    SLL("SLL"),

    /**
     * 
     * 						Singapore Dollar.
     * 						For eBay, you can only specify this currency for listings you submit to the
     * 						Singapore site (site 216).
     * 					
     * 
     */
    SGD("SGD"),
    SKK("SKK"),
    SIT("SIT"),
    SBD("SBD"),
    SOS("SOS"),
    LKR("LKR"),
    SDD("SDD"),
    SRG("SRG"),
    SZL("SZL"),

    /**
     * 
     * 						 Swedish Krona.
     * 						 For eBay, you can only specify this currency for listings you submit to the
     * 						 Sweden site (site 218).
     * 					
     * 
     */
    SEK("SEK"),
    SYP("SYP"),

    /**
     * 
     * 						New Taiwan Dollar. Note that there is no longer an eBay Taiwan site.
     * 					
     * 
     */
    TWD("TWD"),
    TJS("TJS"),
    TZS("TZS"),
    THB("THB"),
    XOF("XOF"),
    TOP("TOP"),
    TTD("TTD"),
    TND("TND"),
    TRL("TRL"),
    TMM("TMM"),
    UGX("UGX"),
    UAH("UAH"),
    AED("AED"),

    /**
     * 
     * 						Pound Sterling.
     * 						For eBay, you can only specify this currency for listings you submit to the
     * 						UK site (site ID 3).
     * 					
     * 
     */
    GBP("GBP"),
    USS("USS"),
    USN("USN"),
    UYU("UYU"),
    UZS("UZS"),
    VUV("VUV"),
    VEB("VEB"),
    VND("VND"),
    MAD("MAD"),
    YER("YER"),
    YUM("YUM"),
    ZMK("ZMK"),
    ZWD("ZWD"),
    ATS("ATS"),

    /**
     * 
     * 						Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    CurrencyCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CurrencyCodeType fromValue(String v) {
        for (CurrencyCodeType c: CurrencyCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
