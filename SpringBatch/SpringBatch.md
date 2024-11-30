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
10. Processor - process the dat before write
```java
@Bean
    public ItemProcessor<Product, Product> processor(){
        return new CustomItemProcessor();
    }
```

```java
public class CustomItemProcessor implements ItemProcessor<Product, Product> {
    @Override
    public Product process(Product item)  {
        try {
            int discountper = Integer.parseInt(item.getDiscount().trim());
            Double price = Double.parseDouble(item.getPrice().trim());
            Double finalPrice = price - (price * (discountper / 100));
            item.setFinalPrice(String.valueOf(finalPrice));
            return item;
        }catch(NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return item;
    }
}
```
11. writer- Write data in db
```java
@Bean
    public ItemWriter<Product> writer(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Product>()
                .sql("insert into products(product_id,title,description,price,discount,final_price)" +
                        "value(:productId,:title,:description,:price,:discount,:finalPrice)")
                .dataSource(dataSource)
                .beanMapped()
                .build();


    }
```

Final BatchConfig
```java
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
                .sql("insert into products(product_id,title,description,price,discount,final_price)" +
                        "value(:productId,:title,:description,:price,:discount,:finalPrice)")
                .dataSource(dataSource)
                .beanMapped()
                .build();


    }
```