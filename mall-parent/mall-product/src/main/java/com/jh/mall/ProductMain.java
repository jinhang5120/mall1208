package com.jh.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.jh.mall.product.dao")
@EnableFeignClients("com.jh.mall.product.feign")
@EnableTransactionManagement //开启事务
//就算不标注，也能扫描到，带有@FeignClient的接口，前提是主配置类是一个父包，其他都是子包，那就能扫描到子包里面内容
//如果自己额外写了配置类，父子不同包，就一定要显示的声明
public class ProductMain {
    public static void main(String[] args) {
        SpringApplication.run(ProductMain.class,args);
    }
}