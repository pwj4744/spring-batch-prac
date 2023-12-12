package com.batchprogram.batchprac.batch;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class StepJob implements Job{

        private final List<Step> steps;
        private final JobExecutionListener jobExecutionListener;



        public StepJob(List<Step> steps, JobExecutionListener jobExecutionListener) {
            this.steps = steps;
            this.jobExecutionListener = Objects.requireNonNullElseGet(jobExecutionListener, () -> new JobExecutionListener() {
                @Override
                public void beforeJob(JobExecution jobExecution) {

                }

                @Override
                public void afterJob(JobExecution jobExecution) {

                }
            });

        }


        @Override
        public JobExecution execute() {


            //JobExecution 도입을 위한 초기 set
            final JobExecution jobExecution = new JobExecution();
            jobExecution.setStatus(BatchStatus.STARTING);
            jobExecution.setStartTime(LocalDateTime.now());

            jobExecutionListener.beforeJob(jobExecution);

            //알람 batch

            try {
                //비즈니스 로직
        /*            while (true) {
                int pageNo = 0;
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


            }*/
                //tasklet.execute();
                steps.forEach(Step::execute);


                jobExecution.setStatus(BatchStatus.COMPLETED);
            } catch (Exception e) {
                jobExecution.setStatus(BatchStatus.FAILED);
            }

            jobExecution.setEndTime(LocalDateTime.now());

            //비즈니스 로직
        /*        emailProvider.send(
                "admin@gmail.com",
                "배치 완료 알림",
                "DormantBatchJob이 수행되었습니다. status : " + jobExecution.getStatus()
        );*/
            jobExecutionListener.afterJob(jobExecution);
            return jobExecution;
        }
    }

