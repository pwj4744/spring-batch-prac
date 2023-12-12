package com.batchprogram.batchprac.application.dormant;

import com.batchprogram.batchprac.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PreDormantBatchItemWriterTest {


    private PreDormantBatchItemWriter preDormantBatchItemWriter;
    @Test
    @DisplayName("1주일 뒤에 휴면계정전환 예정자라고 이메일을 전송해야한다.")
    void test1() {
        // given
        final Customer customer = new Customer("wooJeong" , "wooJeong@gmail.com");
        this.preDormantBatchItemWriter = new PreDormantBatchItemWriter();
        // when

        preDormantBatchItemWriter.write(customer);

        // then





    }

}