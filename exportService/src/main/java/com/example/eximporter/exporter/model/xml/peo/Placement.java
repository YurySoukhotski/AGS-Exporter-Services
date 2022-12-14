//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.11.16 at 01:05:52 PM MSK 
//


package com.example.eximporter.exporter.model.xml.peo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="placementSku" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="skuTechId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="countryIsoCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="advertisedPrice" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="markDownPrice" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="priceReductionRoundedDown" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="priceReduction5PercRounded" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="advertisedPriceDeletedIndicator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="placementTechId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="articleTechId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="orderNumber" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="pageKey" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="position" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *       &lt;attribute name="pageSlice" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="hasFabricSampleIndicator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "placementSku"
})
public class Placement extends BasePlacement {

    protected List<PlacementSku> placementSku;
    @XmlAttribute(name = "placementTechId")
    protected String placementTechId;
    @XmlAttribute(name = "articleTechId")
    protected String articleTechId;
    @XmlAttribute(name = "orderNumber")
    protected String orderNumber;
    @XmlAttribute(name = "pageKey")
    protected String pageKey;
    @XmlAttribute(name = "position")
    protected String position;
    @XmlAttribute(name = "pageSlice")
    protected String pageSlice;
    @XmlAttribute(name = "hasFabricSampleIndicator")
    protected String hasFabricSampleIndicator;
    /**
     * Gets the value of the placementSku property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the placementSku property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlacementSku().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlacementSku }
     * 
     * 
     */
    public List<PlacementSku> getPlacementSku() {
        if (placementSku == null) {
            placementSku = new ArrayList<>();
        }
        return this.placementSku;
    }

    /**
     * Gets the value of the placementTechId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlacementTechId() {
        return placementTechId;
    }

    /**
     * Sets the value of the placementTechId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlacementTechId(String value) {
        this.placementTechId = value;
    }

    /**
     * Gets the value of the articleTechId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArticleTechId() {
        return articleTechId;
    }

    /**
     * Sets the value of the articleTechId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArticleTechId(String value) {
        this.articleTechId = value;
    }

    /**
     * Gets the value of the orderNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * Sets the value of the orderNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOrderNumber(String value) {
        this.orderNumber = value;
    }

    /**
     * Gets the value of the pageKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPageKey() {
        return pageKey;
    }

    /**
     * Sets the value of the pageKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPageKey(String value) {
        this.pageKey = value;
    }

    /**
     * Gets the value of the position property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosition(String value) {
        this.position = value;
    }

    /**
     * Gets the value of the pageSlice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPageSlice() {
        return pageSlice;
    }

    /**
     * Sets the value of the pageSlice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPageSlice(String value) {
        this.pageSlice = value;
    }

    /**
     * Gets the value of the hasFabricSampleIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHasFabricSampleIndicator() {
        return hasFabricSampleIndicator;
    }

    /**
     * Sets the value of the hasFabricSampleIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHasFabricSampleIndicator(String value) {
        this.hasFabricSampleIndicator = value;
    }

    public void setPlacementSku(List<PlacementSku> placementSku) {
        this.placementSku = placementSku;
    }

}
