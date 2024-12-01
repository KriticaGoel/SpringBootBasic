package com.kritica.springbatchjpa5.config;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class ProductListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {

        System.out.println("Job started at: "+ jobExecution.getStartTime());
        System.out.println("Status of the Job: "+jobExecution.getStatus());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

        System.out.println("Job Ended at: "+ jobExecution.getEndTime());
        System.out.println("Status of the Job: "+jobExecution.getStatus());
    }

}
