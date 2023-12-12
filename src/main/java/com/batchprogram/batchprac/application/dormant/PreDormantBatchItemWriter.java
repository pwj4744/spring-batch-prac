package com.batchprogram.batchprac.application.dormant;

import com.batchprogram.batchprac.batch.ItemReader;
import com.batchprogram.batchprac.batch.ItemWriter;
import com.batchprogram.batchprac.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class PreDormantBatchItemWriter implements ItemWriter<Customer> {


    @Override
    public void write(Customer item) {

    }
}
