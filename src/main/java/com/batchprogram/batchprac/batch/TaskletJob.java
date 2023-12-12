package com.batchprogram.batchprac.batch;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Objects;


public class TaskletJob extends AbstractJob { // @Component로 등록하지 않아도 됨 DormantBatchConfiguration에서 등록
    private final Tasklet tasklet;

    public TaskletJob(Tasklet tasklet) {
        super(null);
        this.tasklet = tasklet;
    }

    @Builder
    public TaskletJob(ItemReader<?> itemReader, ItemProcessor<?, ?> itemProcessor, ItemWriter<?> itemWriter, JobExecutionListener jobExecutionListener) {
        super(jobExecutionListener);
        this.tasklet = new SimpleTasklet(itemReader, itemProcessor, itemWriter);
    }

    @Override
    public void doExecute() {
        tasklet.execute();
    }

}
