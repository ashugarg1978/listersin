
package com.ebay.soap.eBLBaseComponents;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PhotoDisplayCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PhotoDisplayCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="SlideShow"/>
 *     &lt;enumeration value="SuperSize"/>
 *     &lt;enumeration value="PicturePack"/>
 *     &lt;enumeration value="SiteHostedPictureShow"/>
 *     &lt;enumeration value="VendorHostedPictureShow"/>
 *     &lt;enumeration value="SuperSizePictureShow"/>
 *     &lt;enumeration value="CustomCode"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PhotoDisplayCodeType")
@XmlEnum
public enum PhotoDisplayCodeType {


    /**
     * 
     * 						No special image display options. Valid for US Motors listings.
     * 					
     * 
     */
    @XmlEnumValue("None")
    NONE("None"),

    /**
     * 
     * 						Display multiple pictures in a slideshow format. Only available for
     * 						site-hosted pictures. Deprecated with request version 381. Use
     * 						SiteHostedPictureShow or VendorHostedPictureShow instead. Not valid
     * 						for US Motors listings.
     * 					
     * 
     */
    @XmlEnumValue("SlideShow")
    SLIDE_SHOW("SlideShow"),

    /**
     * 
     * 						Increase the size of each image and allow buyers to enlarge images
     * 						further. Only available for site-hosted (EPS) images. Not valid for US Motors
     * 						listings. For all sites that do not automatically upgrade SuperSize to
     * 						PicturePack (see note below), specifying no SuperSize-qualified images is now
     * 						accepted in the request.
     * 						<br><br>
     * 						<span class="tablenote"><b>Note:</b>
     * 						SuperSize is automatically upgraded to PicturePack for the same SuperSize
     * 						fee on the US Motors Parts & Accessories Category and US (site ID 0)
     * 						and CA (site ID 2) and CAFR (site ID 210). This upgrade applies
     * 						only to EPS images (including Picture Manager images).
     * 						</span>
     * 						<br><br>
     * 					
     * 
     */
    @XmlEnumValue("SuperSize")
    SUPER_SIZE("SuperSize"),

    /**
     * 
     * 						Increase the number of images displayed. Only available for
     * 						images hosted with eBay. See GetCategoryFeatures and the 
     * 						online Help (on the eBay site) for additional 
     * 						information. Picture Pack applies to all sites
     * 						(including US	Motors), except for NL (site ID 146). You can specify a minimum of 
     * 						one EPS picture, or no SuperSize-qualified EPS pictures in the request. For 
     * 						the NL site, PicturePack is replaced with SuperSize.
     * 					
     * 
     */
    @XmlEnumValue("PicturePack")
    PICTURE_PACK("PicturePack"),

    /**
     * 
     * 						This value is no longer applicable. Formerly, the value displays
     * 						multiple, site-hosted pictures in a slideshow format.
     * 					
     * 
     */
    @XmlEnumValue("SiteHostedPictureShow")
    SITE_HOSTED_PICTURE_SHOW("SiteHostedPictureShow"),

    /**
     * 
     * 						This value is no longer applicable. Formerly, the value displays
     * 						multiple, vendor-hosted pictures in a slideshow format.
     * 						Not valid for US Motors listings.
     * 					
     * 
     */
    @XmlEnumValue("VendorHostedPictureShow")
    VENDOR_HOSTED_PICTURE_SHOW("VendorHostedPictureShow"),

    /**
     * 
     * 						This value is no longer applicable. Use SuperSize instead.
     * 						Formerly, the value displays multiple, super-size, site-hosted pictures in a
     * 						slideshow format.
     * 						Valid for US Motors listings.
     * 					
     * 
     */
    @XmlEnumValue("SuperSizePictureShow")
    SUPER_SIZE_PICTURE_SHOW("SuperSizePictureShow"),

    /**
     * 
     * 							Reserved for internal or future use.
     * 					
     * 
     */
    @XmlEnumValue("CustomCode")
    CUSTOM_CODE("CustomCode");
    private final String value;

    PhotoDisplayCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PhotoDisplayCodeType fromValue(String v) {
        for (PhotoDisplayCodeType c: PhotoDisplayCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
