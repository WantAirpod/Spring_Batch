package com.example.TestSpringFramework.P359_ItemProcessor;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

@EnableBatchProcessing
@SpringBootApplication
public class ValidationJob {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    @StepScope
    public FlatFileItemReader<Customer> custmerItemReader(
            @Value(("#{jobparameters['custmoerFile']}")) Resource inputFile) {
        return new FlatFileItemReaderBuilder<Customer>()
                .name("customerItemReader")
                .delimited() //가변성 데이터
                .names(new String[]{"firstName", "middleInitial", "lastName", "address", "city", "state", "zip"})
                .targetType(Customer.class)
                .resource(inputFile)
                .build();
    }



    @Bean
    public BeanValidatingItemProcessor<Customer> cusotomerValidatingItemProcessor(){
        return new BeanValidatingItemProcessor<>();
    }

    @Bean
    public Step copyFIleStep(){
        return this.stepBuilderFactory.get("copyFileStep")
                .<Customer,Customer>chunk(5)
                .reader(custmerItemReader(null))
                .processor(cusotomerValidatingItemProcessor())
                //.writer(itemWriter)
                .build();
    }

    @Bean
    public Job job() throws Exception{
        return this.jobBuilderFactory.get("job")
                .start(copyFIleStep())
                .build();
    }

    @Bean
    public static void main(String[] args) {
        SpringApplication.run(ValidationJob.class,"customerFile=/Users/cjy9249/Downloads/ItemProcessor/customer.csv");
    }










}
