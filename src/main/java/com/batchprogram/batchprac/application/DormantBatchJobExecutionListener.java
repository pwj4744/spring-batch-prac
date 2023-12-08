package com.batchprogram.batchprac.application;

import com.batchprogram.batchprac.EmailProvider;
import com.batchprogram.batchprac.batch.JobExecution;
import com.batchprogram.batchprac.batch.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class DormantBatchJobExecutionListener implements JobExecutionListener {

    public final EmailProvider emailProvider;

    public DormantBatchJobExecutionListener() {
        this.emailProvider = new EmailProvider.Fake();
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        // no
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        //비즈니스 로직
        emailProvider.send(
                "admin@gmail.com",
                "배치 완료 알림",
                "DormantBatchJob이 수행되었습니다. status : " + jobExecution.getStatus()
        );
    }
}
