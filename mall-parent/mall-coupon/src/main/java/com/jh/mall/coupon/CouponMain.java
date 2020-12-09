package com.jh.mall.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.jh.mall.coupon.dao")
@EnableDiscoveryClient
public class CouponMain {
    public static void main(String[] args) {
        SpringApplication.run(CouponMain.class,args);
    }
}
