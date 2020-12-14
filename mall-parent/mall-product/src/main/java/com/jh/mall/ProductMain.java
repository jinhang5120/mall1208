package com.jh.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jh.mall.product.dao")
public class ProductMain {
    public static void main(String[] args) {
        SpringApplication.run(ProductMain.class,args);
    }
}