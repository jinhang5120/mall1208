package com.jh.mall;

import com.jh.common.utils.R;
import com.jh.mall.ware.feign.ProductFeignService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WareTest {
    @Autowired
    ProductFeignService productFeignService;
    @Test
    void m1(){
        System.out.println("productFeignService = " + productFeignService);
        R info = productFeignService.info(3L);
        System.out.println("info = " + info);
    }
}
