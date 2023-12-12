package com.batchprogram.batchprac.application.dormant;

import com.batchprogram.batchprac.customer.Customer;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class PreDormantBatchItemProcessorTest {

    private PreDormantBatchItemProcessor preDormantBatchItemProcessor;

    @BeforeEach
    void setup() {
        preDormantBatchItemProcessor = new PreDormantBatchItemProcessor();
    }

    @Test
    @DisplayName("로그인_날짜가_오늘로부터_365일전이면 customer를 반환해야한다.")
    void test1() {

        log.info("메시지");
        //given
        final Customer customer = new Customer("woojeong", "woojeong@gmail.com");

        //오늘은 2023-12-12 예정자는 2022-12-19
        //customer.setLoginAt(LocalDateTime.of(2022,12,19,0,0)); < 하드 코딩되어 있으면 맨날맨날 바꿔줘야한다.
        customer.setLoginAt(LocalDateTime.now().minusDays(365).plusDays(7));
        //when
        final Customer result = preDormantBatchItemProcessor.process(customer);


        //then
        Assertions.assertThat(result).isEqualTo(customer);
        Assertions.assertThat(result).isNotNull();

    }

    @Test
    @DisplayName("로그인_날짜가_오늘로부터_365일전이_아니면_NULL을_반환해야한다.")
    void test2() {

        //given
        final Customer customer = new Customer("woojeong", "woojeong@gmail.com");

        //when
        final Customer result = preDormantBatchItemProcessor.process(customer);

        //then
        Assertions.assertThat(result).isNull();
    }

}