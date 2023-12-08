package com.batchprogram.batchprac.application;

import com.batchprogram.batchprac.EmailProvider;
import com.batchprogram.batchprac.batch.Tasklet;
import com.batchprogram.batchprac.customer.Customer;
import com.batchprogram.batchprac.customer.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DormantBatchTasklet implements Tasklet {
    //스프링 레파지토리 JPA 사용하기 위해 Component 설정
    private final CustomerRepository customerRepository;
    private final EmailProvider emailProvider;

    public DormantBatchTasklet(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.emailProvider = new EmailProvider.Fake();
    }

    @Override
    public void execute() {
        //비즈니스 로직
                int pageNo = 0;
                while (true) {

                // 1. 유저를 조회한다.
                final PageRequest pageRequest = PageRequest.of(pageNo, 1, Sort.by("id").ascending()); // 소트를 해주어야 원하는 결과로
                final Page<Customer> page = customerRepository.findAll(pageRequest);

                final Customer customer;
                if (page.isEmpty()) {
                    break;
                } else {
                    pageNo++;
                    customer = page.getContent().get(0);
                }
                // 2. 휴면계정 대상을 추출 및 변환한다.
                final boolean isDormantTarget = LocalDate.now() //오늘로부터 365일 전이 로그인 날자보다 이후면 휴면전환 대상
                        .minusDays(365) //  고객의 마지막 로그인 날  > 1년전 : 대상
                        .isAfter(customer.getLoginAt().toLocalDate());

                if (isDormantTarget) {
                    customer.setStatus(Customer.Status.DORMANT);
                } else {
                    continue;
                }

                // 3. 휴면계쩡으로 상태를 변경한다.
                customerRepository.save(customer);

                // 4. 메일을 보낸다.
                emailProvider.send(customer.getEmail(), "휴면전환 안내메일입니다.", "내용");


            }
    }
}
