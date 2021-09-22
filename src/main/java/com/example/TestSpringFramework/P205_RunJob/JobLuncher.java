package com.example.TestSpringFramework.P205_RunJob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;

import javax.batch.runtime.JobExecution;

public interface JobLuncher {
    public JobExecution run(Job job, JobParameter jobParameter) throws Exception;
}
