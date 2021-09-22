package com.example.TestSpringFramework.P157_ConditionalJob;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

public class ConditionalJob_decider {


    @EnableBatchProcessing
    @SpringBootApplication
    public class BatchConfiguration {
        @Autowired
        private JobBuilderFactory jobBuilderFactory;
        @Autowired
        private StepBuilderFactory stepBuilderFactory;



        @Bean
        public Tasklet passTasklet(){
            return (stepContribution, chunkContext) -> {
                return RepeatStatus.FINISHED;
            } ;
        }

        @Bean
        public Tasklet successTasklet(){
            return(stepContribution, chunkContext) -> {
                System.out.println("Success");
                return RepeatStatus.FINISHED;
            } ;
        }
        @Bean
        public Tasklet failTasklet(){
            return(stepContribution, chunkContext) -> {
                System.out.println("fail");
                return RepeatStatus.FINISHED;
            } ;
        }
     /*   @Bean
        public Job job()
        {
            return this.jobBuilderFactory.get("conditionalJob")
            .start(firstStep()).next(decider()).from(decider()).on("FAILED").to(failureStep())
                    .from(decider()).on("*").to(suceesStep()).end().build();

        }
        @Bean
        public JobExecutionDecider decider()
        {
            return new RuntimeException;
        }*/

        @Bean
        public Step firstStep(){
            return this.stepBuilderFactory.get("firstStep")
                    .tasklet(passTasklet())
                    .build();
        }
        @Bean
        public Step suceesStep()
        {
            return this.stepBuilderFactory.get("succeeStep")
                    .tasklet(successTasklet())
                    .build();
        }
        @Bean
        public Step failureStep()
        {
            return this.stepBuilderFactory.get("failureStep")
                    .tasklet(failTasklet())
                    .build();
        }


    }
  //  public static void main(String[] args){
  //      SpringApplication.run(ConditionalJob.class,args);
  //  }
}
