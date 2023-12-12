package com.batchprogram.batchprac.application.dormant;

import com.batchprogram.batchprac.EmailProvider;
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

        //테스트 코드 mock 도입 .
        // given
        final EmailProvider mockEmailProvider = mock(EmailProvider.class);
        this.preDormantBatchItemWriter = new PreDormantBatchItemWriter(mockEmailProvider);

        final Customer customer = new Customer("wooJoeng", "wooJoeng@gmail.com");

        // when
        preDormantBatchItemWriter.write(customer);

        // then

        verify(mockEmailProvider, atLeastOnce()).send(any(), any(), any());// 최소한 한번이라도 불렸는지.




    }

}