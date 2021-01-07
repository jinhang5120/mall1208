package com.jh.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jh.common.TO.SkuFullReductionTo;
import com.jh.common.utils.PageUtils;
import com.jh.mall.coupon.entity.SmsSkuFullReductionEntity;

import java.util.Map;

/**
 * 
 *
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 14:51:31
 */
public interface SmsSkuFullReductionService extends IService<SmsSkuFullReductionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuFullReductionTo(SkuFullReductionTo skuFullReductionTo);
}

