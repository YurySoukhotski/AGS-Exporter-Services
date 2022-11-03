package com.example.eximporter.exporter.workflow.processor;

import com.example.eximporter.exporter.service.converter.PageConverter;
import com.example.eximporter.exporter.model.extended.ExtendedPage;
import com.example.eximporter.exporter.model.xml.project.AdmediumPage;
import com.example.eximporter.exporter.model.xml.project.AdmediumPageDeleted;
import com.example.eximporter.exporter.model.xml.project.AdmediumPageExport;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class PageProcessor implements ItemProcessor<List<ExtendedPage>, AdmediumPageExport> {
    @Autowired
    private PageConverter pageConverter;

    @Override
    public AdmediumPageExport process(List<ExtendedPage> extendedPages) throws Exception {
        AdmediumPageExport admediumPageExport = new AdmediumPageExport();
        List<AdmediumPage> pages = new ArrayList<>();
        List<AdmediumPageDeleted> pagesDeleted = new ArrayList<>();
        for (ExtendedPage extendedPage : extendedPages) {
            if (extendedPage.getJournal().getChangeOperation() != null && !"DELETED".equalsIgnoreCase(extendedPage.getJournal().getChangeOperation())) {
                pages.add(pageConverter.convert(extendedPage));
            } else {
                pagesDeleted.add(pageConverter.convertDeleted(extendedPage));
            }
        }
        admediumPageExport.setAdmediumPage(pages);
        admediumPageExport.setAdmediumPageDeleted(pagesDeleted);
        return admediumPageExport;
    }
}
