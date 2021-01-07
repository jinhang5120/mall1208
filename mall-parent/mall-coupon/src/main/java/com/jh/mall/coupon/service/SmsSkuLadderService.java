package com.jh.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jh.common.TO.SkuLadderTo;
import com.jh.common.utils.PageUtils;
import com.jh.mall.coupon.entity.SmsSkuLadderEntity;

import java.util.Map;

/**
 * 
 *
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 14:51:31
 */
public interface SmsSkuLadderService extends IService<SmsSkuLadderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuLandderTo(SkuLadderTo skuLadderTo);
}

