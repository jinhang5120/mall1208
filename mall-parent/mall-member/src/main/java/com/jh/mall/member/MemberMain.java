package com.jh.mall.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.jh.mall.member.dao")
@EnableFeignClients(basePackages = "com.jh.mall.member.feign")
public class MemberMain {
    public static void main(String[] args) {
        SpringApplication.run(MemberMain.class,args);
    }
}