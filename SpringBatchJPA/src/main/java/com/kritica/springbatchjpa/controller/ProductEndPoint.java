package com.kritica.springbatchjpa.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductEndPoint {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job jobBean;

    @GetMapping("/loadProduct")
    public void loadProduct() throws Exception{
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("start-at", System.currentTimeMillis()).toJobParameters();

        jobLauncher.run(jobBean,jobParameters);

    }

}
