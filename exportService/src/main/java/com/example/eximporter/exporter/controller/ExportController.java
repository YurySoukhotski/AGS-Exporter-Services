package com.example.eximporter.exporter.controller;

import com.example.eximporter.exporter.file.ExportType;
import com.example.eximporter.exporter.model.api.Journal;
import com.example.eximporter.exporter.service.http.JournalApiService;
import com.example.eximporter.exporter.service.js.notification.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.eximporter.exporter.controller.ExportController.TaskStatus.*;


/**
 * Service to manage import process
 */
@Service
public class ExportController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExportController.class);
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job exportPageFromOmn;
    @Autowired
    private Job exportPeoFromOmn;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private JournalApiService journalApiService;

    public static Map<String, TaskStatus> executedTasks = new HashMap<>();
    public static Map<String, List<Journal>> storedRecords = new HashMap<>();
//todo how to get date for check and

    public void startExport() {
        try {
            LOGGER.debug("------------- Start checking new journal actions -------------");
            Journal[] journalRecords = journalApiService.getAllJournalRecords("2015-04-04 08:00", "root");
            if (journalRecords.length != 0) {
                sortRecords(journalRecords);
                LOGGER.info("Start process journal records");
                if (storedRecords.get(ExportType.PAGE.name()) != null) {
                    startJob("PAGE");
                }
                if (storedRecords.get(ExportType.PEO.name()) != null) {
                    startJob("PEO");
                }
            } else {
                LOGGER.debug("No records found. Jobs not started");
            }
        } catch (Exception e) {
            LOGGER.error("Exception while running Job", e);
        }
    }


    private void startJob(String type) {
        LOGGER.info("Start job ");
        if (!executedTasks.containsValue(RUN)) {
            filterJob(type);
        }
    }

    private void filterJob(String entityType) {
        try {
            if (executedTasks.get(entityType) != FAIL) {
                executedTasks.put(entityType, RUN);
                launchJob(entityType);
            } else
                LOGGER.error("Job not starter because last processed file is failed. Send message");
        } catch (JobParametersInvalidException | JobRestartException | JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException e) {
            LOGGER.error("Can't run job ", e);
        }
    }

    /**
     * Run separate job
     *
     * @throws JobParametersInvalidException
     * @throws JobExecutionAlreadyRunningException
     * @throws JobRestartException
     * @throws JobInstanceAlreadyCompleteException
     */
    private boolean launchJob(String type)
            throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException {
        Job currentJob;
        switch (type) {
            case "PAGE":
                LOGGER.info(" --------- Start Export Page Job ---------");
                currentJob = exportPageFromOmn;
                break;
            case "PEO":
                LOGGER.info(" --------- Start Export Peo Job ---------");
                currentJob = exportPeoFromOmn;
                break;
            default:
                LOGGER.error("Can't determine the type of job {} ", type);
                return false;
        }
        jobLauncher.run(currentJob,
                new JobParametersBuilder().addLong("Start Time", new Date().getTime()).addString(StepListener.JOB_TYPE, type)
                        .toJobParameters());
        return true;
    }


    private void sortRecords(Journal[] records) {
        List<Journal> sortedPages = new ArrayList<>();
        List<Journal> sortedPeos = new ArrayList<>();
        for (Journal journal : records) {
            if (journal.getEntityType().equals(ExportType.PAGE.name())) {
                sortedPages.add(journal);
            } else {
                sortedPeos.add(journal);
            }
        }
        storedRecords.put(ExportType.PAGE.name(), sortedPages);
        storedRecords.put(ExportType.PEO.name(), sortedPeos);
    }


    public enum TaskStatus {
        RUN, FAIL, DONE
    }
}
