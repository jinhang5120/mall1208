package com.jh.mall.product.feign;

import com.jh.common.TO.HasStockTo;
import com.jh.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("mall-ware")
public interface WmsWareFeignService {
    @PostMapping("/ware/waresku/hasStock")
    R<List<HasStockTo>> hasStock(@RequestBody List<Long> skuIds);
}
