package com.batchprogram.batchprac.application.dormant;

import com.batchprogram.batchprac.batch.Job;
import com.batchprogram.batchprac.batch.Step;
import com.batchprogram.batchprac.batch.StepJobBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DormantBatchConfiguration {

    //잡은 여러개의 스텝들로 구성되어 있다.
    @Bean
    public Job dormantBatchJob(
            Step preDormantBatchStep,
            Step dormantBatchStep,
            DormantBatchJobExecutionListener listener
    ) {
        return new StepJobBuilder()
                .start(preDormantBatchStep)
                .next(dormantBatchStep)
                .build();
    }
    @Bean
    public Step dormantBatchStep(
            AllCustomerItemReader itemReader,
            DormantBatchItemProcessor itemProcessor,
            DormantBatchItemWriter itemWriter
    ) {
        return Step.builder()
                .itemReader(itemReader)
                .itemProcessor(itemProcessor)
                .itemWriter(itemWriter)
                .build();
    }

    @Bean
    public Step preDormantBatchStep(
            AllCustomerItemReader itemReader, // 읽어오는기능은 공통이기때문에 공통으로 만든다
            PreDormantBatchItemProcessor itemProcessor,
            PreDormantBatchItemWriter itemWriter
    ) {
        return Step.builder()
                .itemReader(itemReader)
                .itemProcessor(itemProcessor)
                .itemWriter(itemWriter)
                .build();
    }


    //휴면전환 예정 1주일전 사람에게 이메일을 발송한다.
}
