package com.tsm.job;

import com.tsm.service.TsmGetMsgService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;


@Component
@AllArgsConstructor
public class PostMsgJob {


    private final TsmGetMsgService tsmGetMsgService;
    //每5分钟扫描一次目录
    final static String cronStr = "0 0/5 * * * ?";


    @Scheduled(cron = cronStr)
    public void timeTask() throws IOException {

        System.out.println("运行JOB当前时间: " + LocalDateTime.now());
        tsmGetMsgService.runtime();


    }


}