package com.jh.mall.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jh.mall.ware.dao")
public class WareMain {
    public static void main(String[] args) {
        SpringApplication.run(WareMain.class,args);
    }
}
