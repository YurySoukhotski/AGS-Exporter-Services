package com.example.eximporter.exporter.controller;

import com.example.eximporter.exporter.helper.MappingAttributeHelper;
import com.example.eximporter.exporter.service.js.JSParameter;
import com.example.eximporter.exporter.service.js.notification.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;


/**
 * Component check the status of the job
 */
@Component
public class StepListener implements StepExecutionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(StepListener.class);

    public static final String PARTIALLY_PROCESSED_MESSAGE = "partiallyProcessedMessage";
    public static final String PARTIALLY_FILE_NAME = "partiallyFileName";
    public static final String JOB_TYPE = "jobType";

    @Autowired
    private NotificationService notificationService;

    @Override
    public void beforeStep(StepExecution stepExecution) {
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        LOGGER.info("  ----  Checking status of job ---------- ");

        String file = stepExecution.getJobParameters().getString(PARTIALLY_FILE_NAME);
        String jobType = stepExecution.getJobParameters().getString(JOB_TYPE);
        Boolean isPartially = false;
        String partiallyMessage =
                String.valueOf(stepExecution.getJobExecution().getExecutionContext().get(PARTIALLY_PROCESSED_MESSAGE));
        if (!"null".equalsIgnoreCase(partiallyMessage)) {
            isPartially = Boolean.TRUE;
        }
        Map<JSParameter, String> parameters = new EnumMap<>(JSParameter.class);
        if (stepExecution.getExitStatus().equals(ExitStatus.COMPLETED) && !isPartially) {
            //todo delete all page by id - write rest api for delete with id list
            LOGGER.info("Job is finished. Status of job : {} ", stepExecution.getExitStatus().getExitCode());
            ExportController.executedTasks.put(jobType, ExportController.TaskStatus.DONE);
            ExportController.storedRecords.remove(jobType);
            parameters.put(JSParameter.MSG_TEMPLATE, MappingAttributeHelper.getMessageMapping().get("FINISH_EXPORT_" + jobType));
            parameters.put(JSParameter.FILE_NAME, file);
            parameters.put(JSParameter.MESSAGE, "OK"); //todo add stat
            notificationService.call(parameters);

        } else if (stepExecution.getExitStatus().equals(ExitStatus.COMPLETED) && isPartially) {
            LOGGER.info("Job is partially finished. XML File is not written");
            parameters.put(JSParameter.MSG_TEMPLATE, MappingAttributeHelper.getMessageMapping().get("PROBLEM_WITH_" + jobType));
            parameters.put(JSParameter.FILE_NAME, file);
            parameters.put(JSParameter.MESSAGE, partiallyMessage);
            notificationService.call(parameters);
        } else {
            LOGGER.error("Job is failed. Status of job : {} ", stepExecution.getExitStatus().getExitCode());
            ExportController.executedTasks.put(jobType, ExportController.TaskStatus.FAIL);
            ExportController.storedRecords.remove(jobType);   //todo not clean all records to date
            parameters.put(JSParameter.MSG_TEMPLATE, MappingAttributeHelper.getMessageMapping().get("ERROR_EXPORT_" + jobType));
            parameters.put(JSParameter.FILE_NAME, file);
            notificationService.call(parameters);
        }
        return null;

    }
}
