package com.batchprogram.batchprac.batch;

import org.springframework.stereotype.Component;

//SimpleTasklet은 Bean이 될 필요 X
public class SimpleTasklet<I, O> implements Tasklet {

    private final ItemReader<I> itemReader;
    private final ItemProcessor<I, O> itemProcessor;
    private final ItemWriter<O> itemWriter;

    public SimpleTasklet(ItemReader<I> itemReader, ItemProcessor<I, O> itemProcessor, ItemWriter<O> itemWriter) {
        this.itemReader = itemReader;
        this.itemProcessor = itemProcessor;
        this.itemWriter = itemWriter;
    }


    @Override
    public void execute() {
        while (true) {

            //read
            final I read = itemReader.read();
            if(read == null) break;

            //process
            final O process = itemProcessor.process(read);
            if(process == null) continue;

            //write
            itemWriter.write(process);

        }

    }
}
