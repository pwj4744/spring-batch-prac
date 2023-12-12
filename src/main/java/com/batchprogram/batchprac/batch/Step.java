package com.batchprogram.batchprac.batch;

import lombok.Builder;
import org.springframework.scheduling.config.Task;

public class Step {
    // 하나의 스텝에는 하나의 태스크릿을 갖는다.
    private final Tasklet tasklet;

    public Step(Tasklet tasklet) {
        this.tasklet = tasklet;
    }
    // 태스크릿은 아이템 3총사를 들고 있을 수 있다.
    @Builder
    public Step(ItemReader<?> itemReader, ItemProcessor<?, ?> itemProcessor, ItemWriter<?> itemWriter) {
        this.tasklet = new SimpleTasklet(itemReader, itemProcessor, itemWriter);
    }

    public void execute() {
        tasklet.execute();
    }
}
