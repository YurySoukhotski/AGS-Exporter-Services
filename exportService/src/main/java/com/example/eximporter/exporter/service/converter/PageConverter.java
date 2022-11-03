package com.example.eximporter.exporter.service.converter;

import com.example.eximporter.exporter.helper.JsonModelBuilderHelper;
import com.example.eximporter.exporter.model.api.Project;
import com.example.eximporter.exporter.model.extended.ExtendedPage;
import com.example.eximporter.exporter.model.xml.project.AdmediumPage;
import com.example.eximporter.exporter.model.xml.project.AdmediumPageDeleted;
import com.example.eximporter.exporter.service.http.ProjectApiService;
import com.example.eximporter.exporter.service.http.RestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.eximporter.exporter.helper.MappingAttributeHelper.*;

@Service
public class PageConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageConverter.class);

    @Autowired
    private ProjectApiService projectApiService;

    public AdmediumPage convert(ExtendedPage extendedPage) throws RestException {
        Project page = projectApiService.getProjectById(extendedPage.getJournal().getEntityId());
        Project language =projectApiService.getProjectById(page.getParentId());
        AdmediumPage admediumPage = new AdmediumPage();
        admediumPage.setPageTechId(JsonModelBuilderHelper.getAttributeValue(page.getAttributes(), ATTR_SEITE_FP_ID));
        admediumPage.setWorkPageLabel(JsonModelBuilderHelper.getAttributeValue(page.getAttributes(), ATTR_ARBEITSSEITE));
        admediumPage.setPageKey(JsonModelBuilderHelper.getAttributeValue(page.getAttributes(), ATTR_SEITE_FP_PAGEKEY));
        admediumPage.setMaster(Boolean.getBoolean(JsonModelBuilderHelper.getAttributeValue(page.getAttributes(), ATTR_SEITE_FP_ISMASTERPAGE)));
        admediumPage.setAdmediumVersionId(JsonModelBuilderHelper.getAttributeValue(language.getAttributes(), ATTR_LANGUAGE_FP_ID));
        return admediumPage;
    }


    public AdmediumPageDeleted convertDeleted(ExtendedPage extendedPage) throws RestException {
        AdmediumPageDeleted admediumPageDeleted = new AdmediumPageDeleted();
        Project page;
        try {
            page = projectApiService.getProjectById(extendedPage.getJournal().getEntityId());
        } catch (RestException e) {
            LOGGER.error("Error loading page ", e);
            throw new RestException("Error loading page ", e);
        }
        admediumPageDeleted.setPageTechId(JsonModelBuilderHelper.getAttributeValue(page.getAttributes(), ATTR_SEITE_FP_ID));
        return admediumPageDeleted;
    }
}
