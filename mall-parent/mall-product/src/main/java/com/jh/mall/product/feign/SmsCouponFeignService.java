package com.jh.mall.product.feign;

import com.jh.common.TO.BoundsTo;
import com.jh.common.TO.MemberPriceTo;
import com.jh.common.TO.SkuFullReductionTo;
import com.jh.common.TO.SkuLadderTo;
import com.jh.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("mall-coupon")
public interface SmsCouponFeignService {

    @RequestMapping("/coupon/spubounds/save/BoundsTo")
    R saveBounds(@RequestBody BoundsTo boundsTo);

    @RequestMapping("/coupon/skufullreduction/save/SkuFullReductionTo")
    R saveSkuFullReductionTo(@RequestBody SkuFullReductionTo skuFullReductionTo);

    @RequestMapping("/coupon/skuladder/save/SkuLadderTo")
    R saveSkuLandderTo(@RequestBody  SkuLadderTo skuLadderTo);

    @RequestMapping("/coupon/memberprice/save/MemberPriceTo")
    R saveBatchMemberPriceTo(@RequestBody List<MemberPriceTo> memberPriceTos);
}
