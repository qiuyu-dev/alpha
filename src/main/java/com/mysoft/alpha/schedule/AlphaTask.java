package com.mysoft.alpha.schedule;

import com.mysoft.alpha.service.CpExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Component 组件
 */
@Component
public class AlphaTask {

@Autowired
    private CpExcelService cpExcelService;
    /**
     * @Scheduled 定时任务，4小时执行一次
     *
     */
    @Scheduled(cron ="0 0 0/6 * * ? ")
    public void cpExcelToCustomerEnterprise(){
        cpExcelService.taskCpExcelToCustomerEnterprise();
    }

}
