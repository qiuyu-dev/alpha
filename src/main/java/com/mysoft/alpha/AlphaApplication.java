package com.mysoft.alpha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * @EnableScheduling 定时任务
 * @EnableCaching    缓存
 * @EnableTransactionManagement 事务管理
 * @SpringBootApplication 等价 @Configuration,@EnableAutoConfiguration,@ComponentScan
 */
//@EnableScheduling
//@EnableCaching
@EnableTransactionManagement
@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AlphaApplication {
	public static void main(String[] args) {
		SpringApplication.run(AlphaApplication.class, args);
	}
	
}
