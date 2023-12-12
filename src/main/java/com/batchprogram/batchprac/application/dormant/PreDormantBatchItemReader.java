package com.batchprogram.batchprac.application.dormant;

import com.batchprogram.batchprac.batch.ItemReader;
import com.batchprogram.batchprac.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class PreDormantBatchItemReader implements ItemReader<Customer> {
    @Override
    public Customer read() {
        return null;
    }
}
