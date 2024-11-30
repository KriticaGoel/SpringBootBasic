package com.kritica.springbatch.config;

import com.kritica.springbatch.model.Product;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {

    @Bean
    public Job jobBean(JobRepository jobRepository,
                       JobCompletionNotificationImpl listener,
                       Step steps){
        return new JobBuilder("FirstJob",jobRepository)
                .listener(listener)
                .start(steps)
                .build();
    }


    @Bean
    public Step steps(
            JobRepository jobRepository,
            DataSourceTransactionManager transactionManager,
            ItemReader<Product> reader,
            ItemProcessor<Product,Product> processor,
            ItemWriter<Product> writer
            ){
        return new StepBuilder("Step1",jobRepository)
                .<Product,Product>chunk(5,transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();

    }

    //reader
    @Bean
    public FlatFileItemReader<Product> reader(){
        return new FlatFileItemReaderBuilder<Product>()
                .name("itemReader")
                .resource(new ClassPathResource("data.csv"))
                .delimited()
                .names("productId","title","description","price","discount")
                .targetType(Product.class)
                .build();
    }


    //Processor
    @Bean
    public ItemProcessor<Product, Product> processor(){
        return new CustomItemProcessor();
    }

    //writter
    @Bean
    public ItemWriter<Product> writer(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Product>()
                .sql("insert into products(product_id,title,description,price,discount,discounted_price)" +
                        "value(:productId,:title,:description,:price,:discount,:discountedPrice)")
                .dataSource(dataSource)
                .beanMapped()
                .build();


    }
}
