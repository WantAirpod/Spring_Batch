package com.example.TestSpringFramework.P255_Custom.Main;

import com.example.TestSpringFramework.P199_JobExeplorer.DemoApplication;
import com.example.TestSpringFramework.P199_JobExeplorer.ExploringTasklet;
import com.example.TestSpringFramework.P255_Custom.Model.Customer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableBatchProcessing
@SpringBootApplication
public class Main {

    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step copFileStep(){
        return this.stepBuilderFactory.get("copyFileStep")
                .<Customer,Customer> chunk(10)
         //      .reader(customerItemReader("asd.xtt"))
        //        .writer(ItemWriter())
                .build();
    }
    @Bean
    public Job job(){
        return this.jobBuilderFactory.get("job").start(copFileStep()).build();

    }

    //public static void main(String[] args)
    //{
    //    SpringApplication.run(Main.class,args);
    //}
}
