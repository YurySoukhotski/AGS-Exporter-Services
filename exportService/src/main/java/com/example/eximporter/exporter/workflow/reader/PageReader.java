package com.example.eximporter.exporter.workflow.reader;

import com.example.eximporter.exporter.controller.ExportController;
import com.example.eximporter.exporter.model.extended.ExtendedPage;
import com.example.eximporter.exporter.model.api.Journal;
import com.example.eximporter.exporter.model.api.Project;
import com.example.eximporter.exporter.service.http.ProjectApiService;
import com.example.eximporter.exporter.service.http.RestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component("pageReader")
@JobScope
public class PageReader implements ItemReader<List<ExtendedPage>>, ItemStream {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageReader.class);
    @Autowired
    private ProjectApiService projectApiService;
    private static final int START_INDEX = 0;
    private List<ExtendedPage> extendedPages= new ArrayList<>();
    private int currentIndex = START_INDEX;
    private static final String CURRENT_INDEX = "projects.current.index";

    @Override
    public List<ExtendedPage> read() throws RestException {
        if (extendedPages.isEmpty()) {
            List<Journal> records = ExportController.storedRecords.get("PAGE");
            //Project page = projectApiService.getProjectById(journal.getEntityId());
            for (Journal journal : records) {
                Project page = new Project();
                page.setId(123L);
                page.setDisplayName("name");
                page.setParentId(100L);
                LOGGER.info("read journal with id {} ", journal.getEntityId());
                extendedPages.add(new ExtendedPage(journal, page));
            }
        }
        if (extendedPages != null && currentIndex < 1) {
            currentIndex++;
            return extendedPages;
        }
        return null;
    }

    @Override
    public void open(ExecutionContext executionContext) {
        if (executionContext.containsKey(CURRENT_INDEX)) {
            currentIndex = (int) executionContext.getLong(CURRENT_INDEX);
        } else {
            currentIndex = START_INDEX;
        }
    }

    @Override
    public void update(ExecutionContext executionContext) {
        executionContext.putLong(CURRENT_INDEX, currentIndex);
    }

    @Override
    public void close() {
        // not need specific logic
    }


}
