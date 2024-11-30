1. Add database configurations in application.properties
2. create .sql file
```mysql
create table if not exists products
(
    product_id int primary key,
    description varchar(200),
    title varchar(200),
    price varchar(10),
    discount varchar(2),
    discounted_price varchar(10)
    );
```
3. Add below configuration in application.properties. to read .sql file and create table in database
   spring.sql.init.mode=always
4. Copy data.csv file in the resources folder
5. Batch Configurations are as follows
6. Job creation
   ```java
   @Configuration
   public class BatchConfig {
    @Bean
    public Job jobBean(JobRepository jobRepository){
        return new JobBuilder("FirstJob",jobRepository)
                .listener(listener)
                .start(steps)
                .build();
    }
   }
   ```
   **JobRepository** - Contains meta data about job
7. JobCompletionListener:
```java
@Component
public class JobCompletionNotificationImpl implements JobExecutionListener {
   private Logger logger = LoggerFactory.getLogger(JobCompletionNotificationImpl.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("Job execution started");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED)
            logger.info("Job execution ended");
    }
}
```
8. Steps—Creating steps and give reference to a job too.
```java
@Bean
    public Step steps(
            JobRepository jobRepository,
            DataSourceTransactionManager transactionManager,
            ){
        return new StepBuilder("Step1",jobRepository)
                .chunk(5,transactionManager)
                .reader()
                .processor()
                .writer()
                .build();

    }
```
9. Reader -
```java
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
```
10. Processor