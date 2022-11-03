package com.example.eximporter.exporter.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {
	@Value("${configuration.project.id}")
	private Long configurationProjectId;
	@Value("${configuration.scheduler.cron.expression}")
	private String schedulerCronExpression;

	@Value("${export.peo.templateId}")
	private Long peoTemplateId;

	@Value("${export.peo.template.name}")
	private String peoTemplateName;

	//script
	@Value("${default.workflow.js.webservice.url}")
	private String workflowJsWebserviceUrl;
	@Value("${default.mandator.id}")
	private Long mandatorId;
	@Value("${default.notification.script.name}")
	private String notificationScriptName;
	@Value("${default.checkin.script.name}")
	private String checkinScriptName;

	//
	@Value("${default.ftp.host}")
	private String host;
	@Value("${default.ftp.port}")
	private String port;
	@Value("${default.ftp.user.name}")
	private String userName;
	@Value("${default.ftp.user.password}")
	private String userPassword;
	@Value("${default.ftp.page.destination.folder}")
	private String pageDestinationFolder;
	@Value("${default.ftp.peo.destination.folder}")
	private String peoDestinationFolder;


	@Value("${default.peo.export.folder}")
	private String peoExportFolder;
	@Value("${default.page.export.folder}")
	private String pageExportFolder;

	public Long getConfigurationProjectId() {
		return configurationProjectId;
	}

	public void setConfigurationProjectId(Long configurationProjectId) {
		this.configurationProjectId = configurationProjectId;
	}

	public String getSchedulerCronExpression() {
		return schedulerCronExpression;
	}

	public void setSchedulerCronExpression(String schedulerCronExpression) {
		this.schedulerCronExpression = schedulerCronExpression;
	}

	public Long getPeoTemplateId() {
		return peoTemplateId;
	}

	public void setPeoTemplateId(Long peoTemplateId) {
		this.peoTemplateId = peoTemplateId;
	}

	public String getPeoTemplateName() {
		return peoTemplateName;
	}

	public void setPeoTemplateName(String peoTemplateName) {
		this.peoTemplateName = peoTemplateName;
	}

	public String getWorkflowJsWebserviceUrl() {
		return workflowJsWebserviceUrl;
	}

	public void setWorkflowJsWebserviceUrl(String workflowJsWebserviceUrl) {
		this.workflowJsWebserviceUrl = workflowJsWebserviceUrl;
	}

	public Long getMandatorId() {
		return mandatorId;
	}

	public void setMandatorId(Long mandatorId) {
		this.mandatorId = mandatorId;
	}

	public String getNotificationScriptName() {
		return notificationScriptName;
	}

	public void setNotificationScriptName(String notificationScriptName) {
		this.notificationScriptName = notificationScriptName;
	}

	public String getCheckinScriptName() {
		return checkinScriptName;
	}

	public void setCheckinScriptName(String checkinScriptName) {
		this.checkinScriptName = checkinScriptName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getPageDestinationFolder() {
		return pageDestinationFolder;
	}

	public void setPageDestinationFolder(String pageDestinationFolder) {
		this.pageDestinationFolder = pageDestinationFolder;
	}

	public String getPeoDestinationFolder() {
		return peoDestinationFolder;
	}

	public void setPeoDestinationFolder(String peoDestinationFolder) {
		this.peoDestinationFolder = peoDestinationFolder;
	}

	public String getPeoExportFolder() {
		return peoExportFolder;
	}

	public void setPeoExportFolder(String peoExportFolder) {
		this.peoExportFolder = peoExportFolder;
	}

	public String getPageExportFolder() {
		return pageExportFolder;
	}

	public void setPageExportFolder(String pageExportFolder) {
		this.pageExportFolder = pageExportFolder;
	}
}
