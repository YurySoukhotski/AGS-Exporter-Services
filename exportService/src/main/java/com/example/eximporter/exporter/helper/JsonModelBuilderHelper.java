package com.example.eximporter.exporter.helper;

import com.example.eximporter.exporter.model.api.AttributeValues;
import com.example.eximporter.exporter.model.api.AttributesValues;
import com.example.eximporter.exporter.model.api.OrderedAttributeValue;
import com.example.eximporter.exporter.model.api.TableAttributeValueInner;
import org.springframework.util.CollectionUtils;

/**
 * Help to create or get attribute from JSON model api
 */
public class JsonModelBuilderHelper
{
	private JsonModelBuilderHelper()
	{
		throw new IllegalStateException("Utility class");
	}

	public static String getAttributeValue(AttributesValues attributesMap, String attributeName)
	{
		if (CollectionUtils.isEmpty(attributesMap) || CollectionUtils.isEmpty(attributesMap.get(attributeName)))
		{
			return null;
		}
		return attributesMap.get(attributeName).get(0).getValue();
	}



    public static String getTableAttributeValue(TableAttributeValueInner tableAttributeValueInner, String attributeName)
    {
        if (tableAttributeValueInner==null || tableAttributeValueInner.getCells().get(attributeName) ==null)
        {
            return null;
        }
        return tableAttributeValueInner.getCells().get(attributeName).get(0).getValue();
    }



	public static AttributeValues buildSimpleAttributeValues(String value)
	{
		AttributeValues attributeValues = new AttributeValues();
		attributeValues.add(buildOrderedAttributeValue(value, null));
		return attributeValues;
	}

	public static OrderedAttributeValue buildOrderedAttributeValue(String value, String language)
	{
		OrderedAttributeValue orderedAttributeValue = new OrderedAttributeValue();
		orderedAttributeValue.setValue(value);
		orderedAttributeValue.setLang(language);
		return orderedAttributeValue;
	}
}
