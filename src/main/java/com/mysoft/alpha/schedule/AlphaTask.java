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
     * @Scheduled 定时任务，每天1，8，12，22点执行
     *
     */
    @Scheduled(cron ="0 */20 1,10 * * ? ")
    public void cpExcelToCustomerEnterprise(){
        cpExcelService.taskCpExcelToCustomerEnterprise();



    }

}
