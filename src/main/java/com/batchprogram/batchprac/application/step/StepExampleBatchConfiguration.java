package com.batchprogram.batchprac.application.step;

import com.batchprogram.batchprac.batch.Job;
import com.batchprogram.batchprac.batch.Step;
import com.batchprogram.batchprac.batch.StepJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class StepExampleBatchConfiguration {

    @Bean
    public Job stepExampleBatchJob(
            Step step1,
            Step step2,
            Step step3
    ) {
        final List<Step> steps = Arrays.asList(step1, step2, step3);

        return new StepJob(steps, null);
    }


    @Bean
    public Step step1() {
        //final Tasklet tasklet = new Tasklet() {} 익명객체
        //final Tasklet tasklet = () -> System.out.println("step1");

        //return new Step(tasklet);
        return new Step(
                () -> System.out.println("step1")
        );
    }

    @Bean
    public Step step2() {
        return new Step(
                () -> System.out.println("step2")
        );
    }

    @Bean
    public Step step3() {
        return new Step(
                () -> System.out.println("step3")
        );
    }
}
