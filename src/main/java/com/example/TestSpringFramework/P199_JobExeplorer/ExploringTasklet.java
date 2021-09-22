package com.example.TestSpringFramework.P199_JobExeplorer;

import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.List;

public class ExploringTasklet implements Tasklet {
    private JobExplorer explorer;

    public ExploringTasklet(JobExplorer explorer) {
        this.explorer = explorer;
    }

    public RepeatStatus execute(StepContribution stepContribution,
                                ChunkContext chunkContext) {
        String jobName = chunkContext.getStepContext().getJobName();

        List<JobInstance> instances = explorer.getJobInstances(jobName, 0, Integer.MAX_VALUE);

        //System.out.println(
        //String.format("There are %d job instances for the job %s"),instances.size(),jobName));
     /*
        for(JobInstance instance : instances ){
            Lin
        }*/
        return RepeatStatus.FINISHED;

    }
}

