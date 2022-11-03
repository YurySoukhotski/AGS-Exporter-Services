package com.example.eximporter.exporter.service.http;

import com.example.eximporter.exporter.model.api.Journal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;


/**
 * Interacts with api to perform operations with peo.
 */
@Service
public class JournalApiService extends BaseApiService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JournalApiService.class);
    private static final String GET_JOURNAL_WITH_PARAMETERS =  "?fromRecord=%s&excludeChangedBy=%s";

    public Journal[] getAllJournalRecords(String dateFrom, String exclude) throws RestException {
        LOGGER.info("Try to find journal with dateFrom={} and exclude result for {}", dateFrom, exclude);
        try {
            String updatePath = String.format(GET_JOURNAL_WITH_PARAMETERS, dateFrom,exclude);
            ResponseEntity<Journal[]> response = doGet(Journal[].class, getEndPointURL(updatePath));
            return response.getBody();
        } catch (RestClientException e) {
            LOGGER.error("Can't find Journal records with dateFrom {} and exclude for {} ", dateFrom,exclude, e);
            throw new RestException("Api returns error during finding Journal with dateFrom = " + dateFrom, e);
        }
    }

    @Override
    protected String getUrl() {
        return apiConfiguration.getApiJournalPath();
    }
}
