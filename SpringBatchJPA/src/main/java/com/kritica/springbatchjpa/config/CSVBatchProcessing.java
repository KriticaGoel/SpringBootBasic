package com.kritica.springbatchjpa.config;

import com.kritica.springbatchjpa.model.Product;
import com.kritica.springbatchjpa.repository.ProductRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class CSVBatchProcessing {
//    @Value("${source.file.path}")
//    private String filePath;

    @Autowired
    private ProductRepository repository;
    //Reader

    //Reader class Object
//    @Bean
//    public FlatFileItemReader<Product> reader() {
//
//        FlatFileItemReader<Product> reader= new FlatFileItemReader<>();
//        reader.setResource(new ClassPathResource("/data.csv"));
//        reader.setLinesToSkip(1);
//
//        reader.setLineMapper(new DefaultLineMapper<>() {{
//            setLineTokenizer(new DelimitedLineTokenizer() {{
//                setDelimiter(DELIMITER_COMMA);
//                setNames("productId","title","description","price","discount");
//            }});
//
//            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
//                setTargetType(Product.class);
//            }});
//        }});
//
//        reader.setRecordSeparatorPolicy(new BlankLineRecordSeparatorPolicy());
//
//        return reader;
//    }
    @Bean
    public FlatFileItemReader<Product> reader() {
        return new FlatFileItemReaderBuilder<Product>()
                .name("ReadProductData")
                .resource(new ClassPathResource("data.csv"))
                .linesToSkip(1) // skip first line since its header
                .delimited()// line mapper read each line from csv and convert to java object
                .names("productId","title","description","price","discount")
                .strict(false)//Data not available consider as a null value
                .targetType(Product.class)
               // .recordSeparatorPolicy(new BlankLineRecordSeparatorPolicy())
                .build();

    }

    //Processor
//    @Bean
//    public ItemProcessor<Product, Product> processor(){
//        return product -> {
//            Double discount = product.getPrice()*(product.getDiscount()/100.0);
//            Double finalAmount= product.getPrice()-discount;
//            product.setFinalAmount(finalAmount);
//            return product;
//        };
//    }
    @Bean
    public ItemProcessor<Product, Product> processor(){
        return new CustomItemProcessor();
    }

    //Writer

//    @Bean
//    public ItemWriter<Product> writer(){
//        return product -> {
//            System.out.println("Saving Product Records: " +product);
//            repository.saveAll(product);
//        };
//    }

    @Bean
    public ItemWriter<Product> writer(){
        return new RepositoryItemWriterBuilder<Product>()
                .repository(repository)
                .methodName("save")
                .build();


    }

    //Step
//    @Bean
//    public Step steps(
//            JobRepository jobRepository,
//            PlatformTransactionManager  transactionManager
//    ){
//        return new StepBuilder("Step1",jobRepository)
//                .<Product,Product>chunk(2,transactionManager)
//                .reader(reader())
//                .processor(processor())
//                .writer(writer())
//                .build();
//
//    }

    @Bean
    public Step steps(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            ItemReader<Product> reader,
            ItemProcessor<Product,Product> processor,
            ItemWriter<Product> writer
    ){
        return new StepBuilder("Step1",jobRepository)
                .<Product,Product>chunk(10,transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();

    }

    //job
    @Bean
    public Job jobBean(JobRepository jobRepository,
                       JobCompletionNotificationImpl listener,
                       Step steps){
        return new JobBuilder("FirstJob",jobRepository)
                .listener(listener)
                .start(steps)
                .build();
    }


}
