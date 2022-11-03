package com.example.eximporter.exporter.helper;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MappingAttributeHelper {
    // product general attributes

    public static final String ATTR_AGS_ART_DEF_FP_ID = "AGS_ART_DEF_FP_ID";

    public static final String ATTR_WERBEMITTEL_FP_ID = "WERBEMITTEL_FP_ID";
    public static final String ATTR_LANGUAGE_FP_ID = "LANGUAGE_FP_ID";
    public static final String ATTR_SEITE_FP_ID = "SEITE_FP_ID";

    public static final String ATTR_SAISON = "SAISON";

    public static final String ATTR_ARBEITSSEITE = "ARBEITSSEITE";
    public static final String ATTR_SEITE_FP_PAGEKEY = "SEITE_FP_PAGEKEY";
    public static final String ATTR_SEITE_FP_ISMASTERPAGE = "SEITE_FP_ISMASTERPAGE";

    //peo service attributes
    public static final String PLACEMENT_ID_IDENTIFIER = "AGS_PEO_DEF_FP_PLACEMENT_ID";
    public static final String ORDER_NUMBER_IDENTIFIER = "AGS_ART_DEF_ARTICLE_PLACEMENT_ORDER_NUMBER";
    public static final String POSITION_IDENTIFIER = "AGS_PEO_DEF_FP_POSITION";
    public static final String PAGE_SLICE_IDENTIFIER = "AGS_PEO_DEF_FP_PAGESLICE";
    public static final String FP_PAGE_ID = "AGS_PEO_DEF_FP_PAGEID";
    public static final String FABRIC_SAMPLE_INDICATOR_IDENTIFIER = "AGS_ART_DEF_HAS_FABRIC_SAMPLE_INDICATOR";
    public static final String SKU_ID_IDENTIFIER = "AGS_PEO_DEF_SKU_ID";
    public static final String COUNTRY_IDENTIFIER = "AGS_PEO_DEF_COUNTRY";
    public static final String PRICE_IDENTIFIER = "AGS_PEO_DEF_ADV_PRICE";
    public static final String ATTR_AGS_PEO_DEF_ADV_CURRENCY = "AGS_PEO_DEF_ADV_CURRENCY";
    public static final String MARKDOWN_PRICE_IDENTIFIER = "AGS_PEO_DEF_MARKDOWN_PRICE";
    public static final String PRICE_REDUCTION_ROUNDED_DOWN_IDENTIFIER = "AGS_PEO_DEF_MAX_PRICE_REDUCTION_ROUNDED_DOWN";
    public static final String PRICE_REDUCTION_5_PERC_ROUNDED_IDENTIFIER = "AGS_PEO_DEF_MAX_PRICE_REDUCTION5_PERC_ROUNDED";
    public static final String PRICE_DELETED_FLAG_IDENTIFIER = "AGS_PEO_DEF_PF_ADVERTICED_PRICE_DELETED_FLAG";
    public static final String AGS_PEO_DEF_TABLE_PLACEMENT_SKU = "AGS_PEO_DEF_TABLE_PLACEMENT_SKU";
    public static final String ATTR_AGS_PEO_DEF_FP_ARTICLE_TECH_ID = "AGS_PEO_DEF_FP_ARTICLE_TECH_ID";

    private static final Map<String, String> messageMapping = new HashMap<>();


    static {
        messageMapping.put("ERROR_EXPORT_PAGE", "Error during export Page");
        messageMapping.put("ERROR_EXPORT_PEO", "Error during export Placement");
        messageMapping.put("PROBLEM_WITH_PAGE", "Problem with export Page");
        messageMapping.put("PROBLEM_WITH_PEO", "Problem with export Placement");
        messageMapping.put("FINISH_EXPORT_PAGE", "Page export is finished");
        messageMapping.put("FINISH_EXPORT_PEO", "Placement export is finished");
    }

    private MappingAttributeHelper() {
    }

    public static Map<String, String> getMessageMapping() {
        return messageMapping;
    }
}
