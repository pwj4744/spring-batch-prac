package com.batchprogram.batchprac.batch;

import java.util.ArrayList;
import java.util.List;

public class StepJobBuilder {

    private final List<Step> steps;
    private JobExecutionListener jobExecutionListener;


    // 어레이 리스트 생성
    public StepJobBuilder() {
        this.steps = new ArrayList<>();
    }

    //
    public StepJobBuilder start(Step step) {
        if(steps.isEmpty()) {
            steps.add(step); //비어있으면
        } else {
            steps.set(0, step); // 비어있지 않으면
        }
        return this; // 자기자신 리턴
    }

    public StepJobBuilder next(Step step) {
        steps.add(step);
        return this;
    }

    public StepJobBuilder listener(JobExecutionListener jobExecutionListener) {
        this.jobExecutionListener = jobExecutionListener;
        return this;
    }

    public StepJob build() {
        return new StepJob(steps, jobExecutionListener);
    }

}
