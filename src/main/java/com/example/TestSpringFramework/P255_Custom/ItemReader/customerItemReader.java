package com.example.TestSpringFramework.P255_Custom.ItemReader;

import com.example.TestSpringFramework.P255_Custom.Model.Customer;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

public class customerItemReader {

    @Bean
    @StepScope
    public FlatFileItemReader<Customer> customerFlatFileItemReader(
            @Value("#(jobParameters['cutomerFile']}") Resource inputFIle) {

        return new FlatFileItemReaderBuilder<Customer>()
                .name("custmoerItemReader")
                .delimited() //가변적이다.
                .names(new String[] { "firstName","middleInital", "lastName", "addressNumber","street","city","state",
                "zipCode"}).targetType(Customer.class)
                .targetType(Customer.class)
                .resource(inputFIle)
                .build();
    }

}
