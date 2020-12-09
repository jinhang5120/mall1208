package com.jh.mall.member.feign;

import com.jh.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("mall-coupon") //调用远程服务的服务应用名
public interface CouponFeignService {
    @RequestMapping("/coupon/smscoupon/member/list")//必须按照服务应用名的全路径名，mall-coupon/coupon/smscoupon/member/list
    public R memberCoupons();//R=Result,返回给前端的结构集，封装给接口的返回值
}