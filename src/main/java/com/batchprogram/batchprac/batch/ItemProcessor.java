package com.batchprogram.batchprac.batch;

public interface ItemProcessor<I, O>{
    O process(I item);
}
