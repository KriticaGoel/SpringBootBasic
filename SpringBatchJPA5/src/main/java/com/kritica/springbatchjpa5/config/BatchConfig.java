package com.kritica.springbatchjpa5.config;

import com.kritica.springbatchjpa5.model.Product;
import com.kritica.springbatchjpa5.repository.ProductRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {
    //Reader class Object
    @Bean
    public FlatFileItemReader<Product> reader() {

        FlatFileItemReader<Product> reader= new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("/data.csv"));
        reader.setLinesToSkip(1);

        reader.setLineMapper(new DefaultLineMapper<>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setDelimiter(DELIMITER_COMMA);
                setNames("productId","title","description","price","discount");
            }});

            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(Product.class);
            }});
        }});

        reader.setRecordSeparatorPolicy(new BlankLineRecordSeparatorPolicy());

        return reader;
    }

    //Autowire InvoiceRepository
    @Autowired
    ProductRepository repository;

    //Writer class Object
    @Bean
    public ItemWriter<Product> writer(){
        return product -> {
            System.out.println("Saving Product Records: " +product);
            repository.saveAll(product);
        };
    }

    //Processor class Object
    @Bean
    public ItemProcessor<Product, Product> processor(){
       return product -> {
            Double discount = product.getPrice()*(product.getDiscount()/100.0);
            Double finalAmount= product.getPrice()-discount;
           product.setFinalAmount(finalAmount);
            return product;
        };
    }


    //Listener class Object
    @Bean
    public JobExecutionListener listener() {
        return new ProductListener();
    }

    //Autowire StepBuilderFactory

    //Step Object

    @Bean
    public Step steps(
            JobRepository jobRepository,
            PlatformTransactionManager  transactionManager
            ){
        return new StepBuilder("Step1",jobRepository)
                .<Product,Product>chunk(2,transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();

    }

    //JOB
    @Bean
    public Job jobBean(JobRepository jobRepository,
                       Step steps){
        return new JobBuilder("FirstJob",jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .start(steps)
                .build();
    }



}
