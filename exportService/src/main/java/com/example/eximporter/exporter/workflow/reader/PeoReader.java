package com.example.eximporter.exporter.workflow.reader;

import com.example.eximporter.exporter.controller.ExportController;
import com.example.eximporter.exporter.model.extended.ExtendedPeo;
import com.example.eximporter.exporter.service.http.PeoApiService;
import com.example.eximporter.exporter.model.api.Journal;
import com.example.eximporter.exporter.model.api.Peo;
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


@Component("peoReader")
@JobScope
public class PeoReader implements ItemReader<List<ExtendedPeo>>, ItemStream {
    private static final Logger LOGGER = LoggerFactory.getLogger(PeoReader.class);
    @Autowired
    private PeoApiService peoApiService;
    private static final int START_INDEX = 0;
    private List<ExtendedPeo> extendedPeos= new ArrayList<>();
    private int currentIndex = START_INDEX;
    private static final String CURRENT_INDEX = "peo.current.index";

    @Override
    public List<ExtendedPeo> read() throws RestException {
        if (extendedPeos.isEmpty()) {
            List<Journal> records = ExportController.storedRecords.get("PEO");
            //Peo peo = peoApiService.getPeoById(journal.getEntityId());
            for (Journal journal : records) {
                Peo peo = new Peo();
                peo.setId(123L);
                peo.setName("name");
                LOGGER.info("read journal with id {} ", journal.getEntityId());
                extendedPeos.add(new ExtendedPeo(journal, peo));
            }
        }
        if (extendedPeos!= null && currentIndex < 1) {
            currentIndex++;
            return extendedPeos;
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
