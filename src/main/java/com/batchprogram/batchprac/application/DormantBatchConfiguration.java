package com.batchprogram.batchprac.application;

import com.batchprogram.batchprac.Job;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DormantBatchConfiguration {

    @Bean
    public Job dormantBatchJob(
            DormantBatchTasklet dormantBatchTasklet,
            DormantBatchJobExecutionListener dormantBatchJobExecutionListener
    ) {
        return new Job(
                dormantBatchTasklet,
                dormantBatchJobExecutionListener
        );

    }

}
