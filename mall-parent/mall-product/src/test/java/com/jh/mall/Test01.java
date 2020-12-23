package com.jh.mall;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jh.mall.product.entity.PmsBrandEntity;
import com.jh.mall.product.service.PmsBrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = {ProductMain.class})
public class Test01 {
    @Autowired
    PmsBrandService pmsBrandService;

    @Test
    public void m1() {
        System.out.println("aaaaaa");
        System.out.println("pmsBrandService = " + pmsBrandService);
        PmsBrandEntity pmsBrandEntity = new PmsBrandEntity();
        pmsBrandEntity.setDescript("收到了封建士大夫");
        pmsBrandEntity.setName("name1");
        pmsBrandService.save(pmsBrandEntity);

        List<PmsBrandEntity> list = pmsBrandService.list(new QueryWrapper<PmsBrandEntity>().eq("name", "name1"));
        for (PmsBrandEntity brandEntity : list) {
            System.out.println("brandEntity = " + brandEntity);
        }
        System.out.println("++++++++++++++++++");
    }
}
