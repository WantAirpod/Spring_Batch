package com.example.TestSpringFramework.P394_FlatFileItemWriter;

import com.example.TestSpringFramework.P359_ItemProcessor.ValidationJob;
import com.example.TestSpringFramework.P370_ItemProcessorAdapter.Customer;
import com.example.TestSpringFramework.P370_ItemProcessorAdapter.UpperCaseNameSerive;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.adapter.ItemProcessorAdapter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

@EnableBatchProcessing
@SpringBootApplication
public class FlatFileItemWriter {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public org.springframework.batch.item.adapter.ItemProcessorAdapter<Customer, Customer> itemProcessor(UpperCaseNameSerive service){
        org.springframework.batch.item.adapter.ItemProcessorAdapter<Customer, Customer> adapter =new ItemProcessorAdapter<>();
        adapter.setTargetObject(service);
        adapter.setTargetMethod("upperCase");

        return adapter;
    }

    @Bean
    @StepScope
    public FlatFileItemReader<com.example.TestSpringFramework.P359_ItemProcessor.Customer> custmerItemReader(
            @Value(("#{jobparameters['custmoerFile']}")) Resource inputFile) {
        return new FlatFileItemReaderBuilder<com.example.TestSpringFramework.P359_ItemProcessor.Customer>()
                .name("customerItemReader")
                .delimited() //가변성 데이터
                .names(new String[]{"firstName", "middleInitial", "lastName", "address", "city", "state", "zip"})
                .targetType(com.example.TestSpringFramework.P359_ItemProcessor.Customer.class)
                .resource(inputFile)
                .build();
    }

    @Bean
    public BeanValidatingItemProcessor<com.example.TestSpringFramework.P359_ItemProcessor.Customer> cusotomerValidatingItemProcessor(){
        return new BeanValidatingItemProcessor<>();
    }

    @Bean
    @StepScope
    public org.springframework.batch.item.file.FlatFileItemWriter<Customer> customerFlatFileItemWriter(
            @Value("#{jobParameters['outputFile']}") Resource outputFIle){
        return new FlatFileItemWriterBuilder<Customer>()
                .name("customerItemWIrter")
                .resource(outputFIle)

                .names(new String[] {"firstName","lastName"}).bulid();

    }


    @Bean
    public Job job() throws Exception{
        return this.jobBuilderFactory.get("job")
                .start(copyFIleStep())
                .build();
    }
    @Bean
    public Step copyFIleStep(){
        return this.stepBuilderFactory.get("copyFileStep")
                .<com.example.TestSpringFramework.P359_ItemProcessor.Customer, com.example.TestSpringFramework.P359_ItemProcessor.Customer>chunk(5)
                .reader(custmerItemReader(null))
                .processor(itemProcessor(null))
                .writer(customerFlatFileItemWriter(null))
                .build();
    }
    @Bean
    public static void main(String[] args) {
        SpringApplication.run(ValidationJob.class,"customerFile=/Users/cjy9249/Downloads/ItemProcessor/customer.csv");
    }


}
