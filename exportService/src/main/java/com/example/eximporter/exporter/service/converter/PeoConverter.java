package com.example.eximporter.exporter.service.converter;

import com.example.eximporter.exporter.helper.JsonModelBuilderHelper;
import com.example.eximporter.exporter.helper.MappingAttributeHelper;
import com.example.eximporter.exporter.model.api.TableAttributeValueInner;
import com.example.eximporter.exporter.model.extended.ExtendedPeo;
import com.example.eximporter.exporter.model.xml.peo.Placement;
import com.example.eximporter.exporter.model.xml.peo.PlacementDeleted;
import com.example.eximporter.exporter.model.xml.peo.PlacementSku;
import com.example.eximporter.exporter.service.http.PeoApiService;
import com.example.eximporter.exporter.service.http.RestException;
import com.example.eximporter.exporter.model.api.Peo;
import com.example.eximporter.exporter.model.api.TableAttributeValue;
import com.example.eximporter.exporter.model.api.TableAttributesValues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts Peo to Placement
 */
@Service
public class PeoConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeoConverter.class);

    @Autowired
    private PeoApiService peoApiService;

    public Placement convert(ExtendedPeo extendedPeo) throws RestException {
        Peo peo;
        Placement placement = new Placement();
        try {
            peo = peoApiService.findById(extendedPeo.getJournal().getEntityId());
        } catch (RestException e) {
            LOGGER.error("Error loading peo ", e);
            throw new RestException("Error loading peo ", e);
        }
        placement.setPlacementTechId(JsonModelBuilderHelper.getAttributeValue(peo.getAttributes(), MappingAttributeHelper.PLACEMENT_ID_IDENTIFIER));
        placement.setPageKey(JsonModelBuilderHelper.getAttributeValue(peo.getAttributes(), MappingAttributeHelper.FP_PAGE_ID));
        placement.setOrderNumber(JsonModelBuilderHelper.getAttributeValue(peo.getAttributes(), MappingAttributeHelper.ORDER_NUMBER_IDENTIFIER));
        placement.setPosition(JsonModelBuilderHelper.getAttributeValue(peo.getAttributes(), MappingAttributeHelper.POSITION_IDENTIFIER));
        placement.setPageSlice(JsonModelBuilderHelper.getAttributeValue(peo.getAttributes(), MappingAttributeHelper.PAGE_SLICE_IDENTIFIER));
        placement.setHasFabricSampleIndicator(JsonModelBuilderHelper.getAttributeValue(peo.getAttributes(), MappingAttributeHelper.FABRIC_SAMPLE_INDICATOR_IDENTIFIER));
        placement.setArticleTechId(JsonModelBuilderHelper.getAttributeValue(peo.getAttributes(), MappingAttributeHelper.ATTR_AGS_PEO_DEF_FP_ARTICLE_TECH_ID));
        List<PlacementSku> placementSkus = buildPlacementSkus(peo);
        placement.setPlacementSku(placementSkus);

        return placement;
    }

    public PlacementDeleted convertDeleted(ExtendedPeo extendedPeo) throws RestException {
        Peo peo;
        PlacementDeleted placementDeleted = new PlacementDeleted();

        try {
            peo = peoApiService.findById(extendedPeo.getJournal().getEntityId());
        } catch (RestException e) {
            LOGGER.error("Error loading peo ", e);
            throw new RestException("Error loading peo ", e);
        }
        placementDeleted.setPlacementTechId(JsonModelBuilderHelper.getAttributeValue(peo.getAttributes(), MappingAttributeHelper.PLACEMENT_ID_IDENTIFIER));
        return placementDeleted;
    }


    private List<PlacementSku> buildPlacementSkus(Peo peo) {
        List<PlacementSku> placementSkus = new ArrayList<>();
        TableAttributesValues tableAttributes = peo.getTableAttributes();
        if (tableAttributes!=null) {
            TableAttributeValue tableAttributeValueInners = tableAttributes.get(MappingAttributeHelper.AGS_PEO_DEF_TABLE_PLACEMENT_SKU);
            if (tableAttributeValueInners!=null){
                for (TableAttributeValueInner tableAttributeValueInner: tableAttributeValueInners){
                    PlacementSku placementSku = new PlacementSku();
                    placementSku.setSkuTechId(JsonModelBuilderHelper.getTableAttributeValue(tableAttributeValueInner, MappingAttributeHelper.SKU_ID_IDENTIFIER));
                    placementSku.setCountryIsoCode(JsonModelBuilderHelper.getTableAttributeValue(tableAttributeValueInner, MappingAttributeHelper.COUNTRY_IDENTIFIER));
                    placementSku.setAdvertisedPrice(JsonModelBuilderHelper.getTableAttributeValue(tableAttributeValueInner, MappingAttributeHelper.PRICE_IDENTIFIER));
                    placementSku.setMarkDownPrice(JsonModelBuilderHelper.getTableAttributeValue(tableAttributeValueInner, MappingAttributeHelper.MARKDOWN_PRICE_IDENTIFIER));
                    placementSku.setCurrency(JsonModelBuilderHelper.getTableAttributeValue(tableAttributeValueInner, MappingAttributeHelper.ATTR_AGS_PEO_DEF_ADV_CURRENCY));
                    placementSku.setPriceReductionRoundedDown(JsonModelBuilderHelper.getTableAttributeValue(tableAttributeValueInner, MappingAttributeHelper.PRICE_REDUCTION_ROUNDED_DOWN_IDENTIFIER));
                    placementSku.setPriceReduction5PercRounded(JsonModelBuilderHelper.getTableAttributeValue(tableAttributeValueInner, MappingAttributeHelper.PRICE_REDUCTION_5_PERC_ROUNDED_IDENTIFIER));
                    placementSku.setAdvertisedPriceDeletedIndicator(JsonModelBuilderHelper.getTableAttributeValue(tableAttributeValueInner, MappingAttributeHelper.PRICE_DELETED_FLAG_IDENTIFIER));
                    placementSkus.add(placementSku);
                }
            }
        }
        return placementSkus;
    }

}
