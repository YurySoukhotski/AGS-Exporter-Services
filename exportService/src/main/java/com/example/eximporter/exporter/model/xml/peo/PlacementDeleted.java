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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class PlacementDeleted extends BasePlacement {

    @XmlAttribute(name = "placementTechId")
    protected String placementTechId;

    @Override
    public String getPlacementTechId() {
        return placementTechId;
    }

    @Override
    public void setPlacementTechId(String placementTechId) {
        this.placementTechId = placementTechId;
    }
}
