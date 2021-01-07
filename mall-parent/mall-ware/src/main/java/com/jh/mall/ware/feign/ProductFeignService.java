package com.jh.mall.ware.feign;

import com.jh.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("mall-gateway")
public interface ProductFeignService {
    @RequestMapping("/api/product/skuinfo/info/{skuId}")//给网关的请求必须带/api/
    public R info(@PathVariable("skuId") Long skuId);
}