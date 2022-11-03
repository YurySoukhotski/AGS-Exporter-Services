package com.example.eximporter.exporter.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AttributeConfiguration {
    @Value("${configuration.attribute.ftp.host}")
    private String ftpHostAttribute;
    @Value("${configuration.attribute.ftp.port}")
    private String ftpPortAttribute;
    @Value("${configuration.attribute.ftp.user.name}")
    private String ftpUserNameAttribute;
    @Value("${configuration.attribute.ftp.user.password}")
    private String ftpUserPasswordAttribute;
    @Value("${configuration.attribute.ftp.page.destination.folder}")
    private String ftpDestinationPageFolder;
    @Value("${configuration.attribute.ftp.peo.destination.folder}")
    private String ftpDestinationPeoFolder;

    @Value("${configuration.attribute.export.cron.expression}")
    private String cronExpression;

    @Value("${configuration.attribute.export.page.folder}")
    private String exportPageFolder;
    @Value("${configuration.attribute.export.peo.folder}")
    private String exportPeoFolder;


    @Value("${configuration.attribute.peo.template.name}")
    private String peoTemplate;
    @Value("${configuration.attribute.peo.template.id}")
    private String peoTemplateId;

    @Value("${configuration.attribute.mandator.identity}")
    private String mandatorId;
    @Value("${configuration.attribute.notification.script.name}")
    private String notificationScript;

    @Value("${configuration.attribute.checkin.script.name}")
    private String checkinScript;
    @Value("${configuration.attribute.workflow.js.webservice.url}")
    private String webServiceUrl;

    public String getFtpHostAttribute() {
        return ftpHostAttribute;
    }

    public String getMandatorId() {
        return mandatorId;
    }

    public String getFtpPortAttribute() {
        return ftpPortAttribute;
    }

    public String getFtpUserNameAttribute() {
        return ftpUserNameAttribute;
    }

    public String getFtpUserPasswordAttribute() {
        return ftpUserPasswordAttribute;
    }

    public String getFtpDestinationPageFolder() {
        return ftpDestinationPageFolder;
    }

    public String getFtpDestinationPeoFolder() {
        return ftpDestinationPeoFolder;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public String getExportPageFolder() {
        return exportPageFolder;
    }

    public String getExportPeoFolder() {
        return exportPeoFolder;
    }

    public String getPeoTemplate() {
        return peoTemplate;
    }

    public String getPeoTemplateId() {
        return peoTemplateId;
    }

    public String getNotificationScript() {
        return notificationScript;
    }

    public String getCheckinScript() {
        return checkinScript;
    }

    public String getWebServiceUrl() {
        return webServiceUrl;
    }
}
