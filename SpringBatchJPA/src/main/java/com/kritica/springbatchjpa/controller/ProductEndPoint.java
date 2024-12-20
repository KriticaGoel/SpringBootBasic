package com.kritica.springbatchjpa.controller;

import com.kritica.springbatchjpa.model.Product;
import com.kritica.springbatchjpa.service.ProductService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductEndPoint {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job jobBean;//Same as job method name in Batch Config clas

    @GetMapping("/start/{jobName}")
    public String startJob(@PathVariable String jobName) throws Exception{
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("start-at", System.currentTimeMillis()).toJobParameters();
        if(jobName.equalsIgnoreCase("FirstJob"))
            jobLauncher.run(jobBean,jobParameters);
        else
            return String.valueOf(new JobExecutionException(jobName +" is not configured"));
        return jobName +" started...";

    }

    @Autowired
    private ProductService  productService;

    @GetMapping("/product")
    public List<Product> getproduct() throws Exception{
        return productService.getProduct();

    }

}
