package com.example.TestSpringFramework.TaskHelloWorld;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class BatchConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job(){
        return this.jobBuilderFactory.get("job")
                .start(step1())
                .build();
    }
    @Bean
    public Step step1(){
        return this.stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) ->{
                            System.out.println("hello,World");
                            return RepeatStatus.FINISHED;
                        }).build();
    }

}
