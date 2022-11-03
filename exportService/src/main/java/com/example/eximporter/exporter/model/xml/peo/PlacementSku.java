//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.11.16 at 01:05:52 PM MSK 
//


package com.example.eximporter.exporter.model.xml.peo;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="skuTechId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="countryIsoCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="advertisedPrice" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="markDownPrice" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="priceReductionRoundedDown" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="priceReduction5PercRounded" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="advertisedPriceDeletedIndicator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "value"
})
public class PlacementSku {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "skuTechId")
    protected String skuTechId;
    @XmlAttribute(name = "countryIsoCode")
    protected String countryIsoCode;
    @XmlAttribute(name = "advertisedPrice")
    protected String advertisedPrice;
    @XmlAttribute(name = "markDownPrice")
    protected String markDownPrice;
    @XmlAttribute(name = "priceReductionRoundedDown")
    protected String priceReductionRoundedDown;
    @XmlAttribute(name = "priceReduction5PercRounded")
    protected String priceReduction5PercRounded;
    @XmlAttribute(name = "advertisedPriceDeletedIndicator")
    protected String advertisedPriceDeletedIndicator;
    @XmlAttribute(name = "currency")
    protected String currency;

    /**
     * Gets the value of the value property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the skuTechId property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSkuTechId() {
        return skuTechId;
    }

    /**
     * Gets the value of the countryIsoCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    /**
     * Sets the value of the countryIsoCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCountryIsoCode(String value) {
        this.countryIsoCode = value;
    }

    /**
     * Gets the value of the advertisedPrice property.
     *
     * @return possible object is
     * {@link Float }
     */
    public String getAdvertisedPrice() {
        return advertisedPrice;
    }

    /**
     * Sets the value of the advertisedPrice property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAdvertisedPrice(String value) {
        this.advertisedPrice = value;
    }

    /**
     * Gets the value of the markDownPrice property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMarkDownPrice() {
        return markDownPrice;
    }

    /**
     * Sets the value of the markDownPrice property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMarkDownPrice(String value) {
        this.markDownPrice = value;
    }

    /**
     * Gets the value of the priceReductionRoundedDown property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPriceReductionRoundedDown() {
        return priceReductionRoundedDown;
    }

    /**
     * Gets the value of the priceReduction5PercRounded property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPriceReduction5PercRounded() {
        return priceReduction5PercRounded;
    }

    /**
     * Sets the value of the priceReduction5PercRounded property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPriceReduction5PercRounded(String value) {
        this.priceReduction5PercRounded = value;
    }

    /**
     * Gets the value of the advertisedPriceDeletedIndicator property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAdvertisedPriceDeletedIndicator() {
        return advertisedPriceDeletedIndicator;
    }

    /**
     * Sets the value of the advertisedPriceDeletedIndicator property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAdvertisedPriceDeletedIndicator(String value) {
        this.advertisedPriceDeletedIndicator = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setSkuTechId(String skuTechId) {
        this.skuTechId = skuTechId;
    }

    public void setPriceReductionRoundedDown(String priceReductionRoundedDown) {
        this.priceReductionRoundedDown = priceReductionRoundedDown;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}