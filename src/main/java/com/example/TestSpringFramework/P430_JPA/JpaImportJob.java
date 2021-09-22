package com.example.TestSpringFramework.P430_JPA;

import com.example.TestSpringFramework.P359_ItemProcessor.Customer;
import com.example.TestSpringFramework.P359_ItemProcessor.ValidationJob;
import com.example.TestSpringFramework.P370_ItemProcessorAdapter.UpperCaseNameSerive;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.adapter.ItemProcessorAdapter;
import org.springframework.batch.item.database.HibernateItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.HibernateItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import javax.persistence.EntityManagerFactory;

@EnableBatchProcessing
@SpringBootApplication
public class JpaImportJob {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    private JpaImportJob(JobBuilderFactory jobBuilderFactory,StepBuilderFactory stepBuilderFactory)
    {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }


    @Bean
    public org.springframework.batch.item.adapter.ItemProcessorAdapter<com.example.TestSpringFramework.P370_ItemProcessorAdapter.Customer, com.example.TestSpringFramework.P370_ItemProcessorAdapter.Customer> itemProcessor(UpperCaseNameSerive service){
        org.springframework.batch.item.adapter.ItemProcessorAdapter<com.example.TestSpringFramework.P370_ItemProcessorAdapter.Customer, com.example.TestSpringFramework.P370_ItemProcessorAdapter.Customer> adapter =new ItemProcessorAdapter<>();
        adapter.setTargetObject(service);
        adapter.setTargetMethod("upperCase");

        return adapter;
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Customer> custmerItemReader(
            @Value(("#{jobparameters['custmoerFile']}")) Resource inputFile) {
        return new FlatFileItemReaderBuilder<Customer>()
                .name("customerItemReader")
                .delimited() //가변성 데이터
                .names(new String[]{"firstName", "middleInitial", "lastName", "address", "city", "state", "zip"})
                .targetType(com.example.TestSpringFramework.P359_ItemProcessor.Customer.class)
                .resource(inputFile)
                .build();
    }


    @Bean
    public BeanValidatingItemProcessor<Customer> cusotomerValidatingItemProcessor(){
        return new BeanValidatingItemProcessor<>();
    }



    @Bean
    @StepScope
    public JpaItemWriter<com.example.TestSpringFramework.P421_Hibernate.Customer> jpaItemWriter(
            EntityManagerFactory entityManagerFactory){
        JpaItemWriter<Customer> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemWriter
    }
    @Bean
    public Step jpaFormatStep()throws Exception
    {
        return this.stepBuilderFactory.get("jobFormatStep")
                .<com.example.TestSpringFramework.P421_Hibernate.Customer, com.example.TestSpringFramework.P421_Hibernate.Customer>chunk(10)
                .reader(custmerItemReader(null))
                .processor(itemProcessor(null))
                .writer(jpaItemWriter(null))
                .build();
    }

    @Bean
    public Job job() throws Exception{
        return this.jobBuilderFactory.get("job")
                .start(jpaFormatStep())
                .build();
    }

    @Bean
    public static void main(String[] args) {
        SpringApplication.run(ValidationJob.class,"customerFile=/Users/cjy9249/Downloads/ItemProcessor/customer.csv");
    }
}
