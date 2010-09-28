
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccessRuleCurrentStatusCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AccessRuleCurrentStatusCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="NotSet"/>
 *     &lt;enumeration value="HourlyLimitExceeded"/>
 *     &lt;enumeration value="DailyLimitExceeded"/>
 *     &lt;enumeration value="PeriodicLimitExceeded"/>
 *     &lt;enumeration value="HourlySoftLimitExceeded"/>
 *     &lt;enumeration value="DailySoftLimitExceeded"/>
 *     &lt;enumeration value="PeriodicSoftLimitExceeded"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AccessRuleCurrentStatusCodeType")
@XmlEnum
public enum AccessRuleCurrentStatusCodeType {


    /**
     * 
     * 						(out) The rule is not set and usage limits do not apply.
     * 					
     * 
     */
    @XmlEnumValue("NotSet")
    NOT_SET("NotSet"),

    /**
     * 
     * 					 (out) Your application has exceeded its hourly hard limit.
     * 					
     * 
     */
    @XmlEnumValue("HourlyLimitExceeded")
    HOURLY_LIMIT_EXCEEDED("HourlyLimitExceeded"),

    /**
     * 
     * 						(out) Your application has exceeded its daily hard limit.
     * 					
     * 
     */
    @XmlEnumValue("DailyLimitExceeded")
    DAILY_LIMIT_EXCEEDED("DailyLimitExceeded"),

    /**
     * 
     * 						(out) Your application has exceeded its periodic hard limit.
     * 					
     * 
     */
    @XmlEnumValue("PeriodicLimitExceeded")
    PERIODIC_LIMIT_EXCEEDED("PeriodicLimitExceeded"),

    /**
     * 
     * 						(out) Your application has exceeded its hourly soft limit.
     * 					
     * 
     */
    @XmlEnumValue("HourlySoftLimitExceeded")
    HOURLY_SOFT_LIMIT_EXCEEDED("HourlySoftLimitExceeded"),

    /**
     * 
     * 						(out) Your application has exceeded its daily soft limit.
     * 					
     * 
     */
    @XmlEnumValue("DailySoftLimitExceeded")
    DAILY_SOFT_LIMIT_EXCEEDED("DailySoftLimitExceeded"),

    /**
     * 
     * 						(out) Your application has exceeded its periodic soft limit.
     * 					
     * 
     */
    @XmlEnumValue("PeriodicSoftLimitExceeded")
    PERIODIC_SOFT_LIMIT_EXCEEDED("PeriodicSoftLimitExceeded"),

    /**
     * 
     * 					 (out) Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    AccessRuleCurrentStatusCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AccessRuleCurrentStatusCodeType fromValue(String v) {
        for (AccessRuleCurrentStatusCodeType c: AccessRuleCurrentStatusCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
