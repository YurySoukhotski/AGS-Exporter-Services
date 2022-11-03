package com.example.eximporter.exporter.helper;

import com.example.eximporter.exporter.configuration.CommonConfiguration;
import com.example.eximporter.exporter.configuration.AttributeConfiguration;
import com.example.eximporter.exporter.model.api.AttributeValues;
import com.example.eximporter.exporter.model.api.AttributesValues;
import com.example.eximporter.exporter.model.api.Project;
import com.example.eximporter.exporter.service.http.AuthService;
import com.example.eximporter.exporter.service.http.ProjectApiService;
import com.example.eximporter.exporter.service.http.RestException;
import org.apache.commons.lang3.math.NumberUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.Date;

/**
 * Helps to read configuration project
 */
@Service
public class ImportPropertyHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImportPropertyHelper.class);
    /**
     * Name of trigger to start export
     */
    private static final String EXPORT_CRON_TRIGGER = "exportCronTrigger";
    @Autowired
    private ProjectApiService projectApiService;
    @Autowired
    private AuthService authService;
    @Autowired
    private SchedulerFactoryBean exportSchedulerFactoryBean;
    @Autowired
    private CommonConfiguration commonConfiguration;

    @Autowired
    private AttributeConfiguration attributeConfiguration;

    /**
     * Initialization of cron expression
     */
    @PostConstruct
    public void initCronExpression() throws RestException {
        LOGGER.info("Getting token before start");
        if (authService.getTokenString() == null) {
            getLoginToken();
        }
        LOGGER.info("Initialize cron expression");
        updateExportSchedulerCron();
    }

    /**
     * Method updates cron expression to schedule task from configuration project
     */
    @Scheduled(cron = "${configuration.scheduler.cron.expression}")
    public void updateExportSchedulerCron() throws RestException {
        LOGGER.info("Checking token while working");
        if (authService.getTokenString() == null) {
            getLoginToken();
        } else {
            if (!authService.validateToken()) {
                getLoginToken();
            }
        }
        updateConfiguration();
        rescheduleExport();
    }

    private void updateConfiguration() {
        try {
            Project configProject = getConfigurationProject();
            if (configProject != null) {
                updateConfigurationAttributes(configProject.getAttributes());
            }

        } catch (RestException e) {
            LOGGER.error("Can't access to config project. Cron expression won't be set", e);
        }
    }


    private void updateConfigurationAttributes(AttributesValues values) {
        commonConfiguration.setHost(getValue(attributeConfiguration.getFtpHostAttribute(), values));
        commonConfiguration.setPort(getValue(attributeConfiguration.getFtpPortAttribute(), values));
        commonConfiguration.setUserName(getValue(attributeConfiguration.getFtpUserNameAttribute(), values));
        commonConfiguration.setUserPassword(getValue(attributeConfiguration.getFtpUserPasswordAttribute(), values));


        commonConfiguration.setCheckinScriptName(getValue(attributeConfiguration.getCheckinScript(), values));
        commonConfiguration.setNotificationScriptName(getValue(attributeConfiguration.getNotificationScript(), values));

        commonConfiguration.setMandatorId(NumberUtils.createDouble(getValue(attributeConfiguration.getMandatorId(), values)).longValue());

        commonConfiguration.setPeoTemplateId(NumberUtils.createDouble(getValue(attributeConfiguration.getPeoTemplateId(), values)).longValue());
        commonConfiguration.setPeoTemplateName(getValue(attributeConfiguration.getPeoTemplate(), values));

        commonConfiguration.setPageExportFolder(getValue(attributeConfiguration.getExportPageFolder(), values));
        commonConfiguration.setPeoExportFolder(getValue(attributeConfiguration.getExportPeoFolder(), values));

        commonConfiguration.setPageDestinationFolder(getValue(attributeConfiguration.getFtpDestinationPageFolder(), values));
        commonConfiguration.setPeoDestinationFolder(getValue(attributeConfiguration.getFtpDestinationPeoFolder(), values));

        commonConfiguration.setSchedulerCronExpression(getValue(attributeConfiguration.getCronExpression(), values));


    }


    private String getValue(String attributeIdentifier, AttributesValues values) {
        AttributeValues attributeValue = values.get(attributeIdentifier);
        Assert.notNull(attributeValue, "Can't found value for attribute " + attributeIdentifier);
        return attributeValue.get(0).getValue();

    }

    private void rescheduleExport() {
        Scheduler scheduler = exportSchedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(EXPORT_CRON_TRIGGER);
        try {
            CronTriggerImpl trigger = (CronTriggerImpl) scheduler.getTrigger(triggerKey);
            trigger.setCronExpression(commonConfiguration.getSchedulerCronExpression());
            Date nextExecutionDate = scheduler.rescheduleJob(triggerKey, trigger);
            LOGGER.info("Cron expression has been set to {}", commonConfiguration.getSchedulerCronExpression());
            LOGGER.info("Next execution of export at {}", nextExecutionDate);
        } catch (ParseException e) {
            LOGGER.error("Wrong cron expression", e);
        } catch (SchedulerException e) {
            LOGGER.error("Can't reschedule export task with trigger name {}", EXPORT_CRON_TRIGGER, e);
        }
    }

    /**
     * Get configuration project
     *
     * @return {@link Project}
     * @throws RestException config project can't be found by api
     */
    private Project getConfigurationProject() throws RestException {
        LOGGER.info("Try to retrieve config project with id={}", commonConfiguration.getConfigurationProjectId());
        try {
            return projectApiService.getProjectById(commonConfiguration.getConfigurationProjectId());
        } catch (RestException e) {
            LOGGER.error("Can't find configuration project", e);
            throw e;
        }
    }


    /**
     * Get configuration project
     *
     * @return {@link Project}
     * @throws RestException config project can't be found by api
     */
    private void getLoginToken() throws RestException {
        LOGGER.info("Try to get new token");
        try {
            authService.getToken();
        } catch (RestException e) {
            LOGGER.error("Can't login to Server", e);
            throw e;
        }
    }
}
