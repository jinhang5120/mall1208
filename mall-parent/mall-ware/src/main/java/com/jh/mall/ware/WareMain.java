package com.jh.mall.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.jh.mall.ware.dao")
@EnableFeignClients("com.jh.mall.ware.feign")
public class WareMain {
    public static void main(String[] args) {
        SpringApplication.run(WareMain.class,args);
    }
}
