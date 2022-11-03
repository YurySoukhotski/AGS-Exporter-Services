package com.example.eximporter.exporter.controller;

import com.example.eximporter.exporter.configuration.CommonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Scheduling export configuration
 */
@Configuration
public class ExportSchedulerConfig
{
	@Autowired
	private CommonConfiguration commonConfiguration;

	@Bean
	public MethodInvokingJobDetailFactoryBean exportJob()
	{
		MethodInvokingJobDetailFactoryBean exportJob = new MethodInvokingJobDetailFactoryBean();
		exportJob.setTargetBeanName("exportController");
		exportJob.setTargetMethod("startExport");
		exportJob.setConcurrent(false);
		return exportJob;
	}

	@Bean
	public CronTriggerFactoryBean exportCronTrigger()
	{
		CronTriggerFactoryBean exportCronTrigger = new CronTriggerFactoryBean();
		exportCronTrigger.setJobDetail(exportJob().getObject());
		exportCronTrigger.setCronExpression(commonConfiguration.getSchedulerCronExpression());
		return exportCronTrigger;
	}

	@Bean
	public SchedulerFactoryBean exportSchedulerFactoryBean()
	{
		SchedulerFactoryBean exportSchedulerFactoryBean = new SchedulerFactoryBean();
		exportSchedulerFactoryBean.setTriggers(exportCronTrigger().getObject());
		exportSchedulerFactoryBean.setJobDetails(exportJob().getObject());
		return exportSchedulerFactoryBean;
	}
}
