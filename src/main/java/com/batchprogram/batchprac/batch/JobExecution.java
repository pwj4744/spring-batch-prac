package com.batchprogram.batchprac.batch;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class JobExecution {
    //Job의 성공여부, Job의 실행/종료시간, 배치실행이 완료되었을때 알림을 위한 JobExecution

    private BatchStatus status;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

}
