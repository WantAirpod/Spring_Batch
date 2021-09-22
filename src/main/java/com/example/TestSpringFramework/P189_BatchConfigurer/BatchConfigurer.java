package com.example.TestSpringFramework.P189_BatchConfigurer;

import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.transaction.PlatformTransactionManager;

public interface BatchConfigurer {
    JobRepository getJobRepository() throws Exception;
    PlatformTransactionManager getTransactionManager() throws Exception;
    JobLauncher getJobLauncher() throws Exception;
    JobExplorer getJobExporer() throws Exception;
}
