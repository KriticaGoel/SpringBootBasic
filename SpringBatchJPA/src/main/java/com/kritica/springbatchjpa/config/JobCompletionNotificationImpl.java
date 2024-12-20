package com.kritica.springbatchjpa.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationImpl implements JobExecutionListener {
   private Logger logger = LoggerFactory.getLogger(JobCompletionNotificationImpl.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("My Job execution started");
        System.out.println("Before Job " + jobExecution.getJobInstance().getJobName());
        System.out.println("Job Params " + jobExecution.getJobParameters());
        System.out.println("Job Exec Context " + jobExecution.getExecutionContext());

        jobExecution.getExecutionContext().put("jec", "jec value");
    }


    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("After Job " + jobExecution.getJobInstance().getJobName());
        System.out.println("Job Params " + jobExecution.getJobParameters());
        System.out.println("Job Exec Context " + jobExecution.getExecutionContext());
        if(jobExecution.getStatus() == BatchStatus.COMPLETED)
            logger.info("My Job execution ended");
    }
}