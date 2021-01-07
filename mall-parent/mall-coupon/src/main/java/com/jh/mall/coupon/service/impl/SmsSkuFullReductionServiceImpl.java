package com.jh.mall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.common.TO.SkuFullReductionTo;
import com.jh.common.utils.PageUtils;
import com.jh.common.utils.Query;
import com.jh.mall.coupon.dao.SmsSkuFullReductionDao;
import com.jh.mall.coupon.entity.SmsSkuFullReductionEntity;
import com.jh.mall.coupon.service.SmsSkuFullReductionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("smsSkuFullReductionService")
public class SmsSkuFullReductionServiceImpl extends ServiceImpl<SmsSkuFullReductionDao, SmsSkuFullReductionEntity> implements SmsSkuFullReductionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SmsSkuFullReductionEntity> page = this.page(
                new Query<SmsSkuFullReductionEntity>().getPage(params),
                new QueryWrapper<SmsSkuFullReductionEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSkuFullReductionTo(SkuFullReductionTo skuFullReductionTo) {
        SmsSkuFullReductionEntity smsSkuFullReductionEntity = new SmsSkuFullReductionEntity();
        BeanUtils.copyProperties(skuFullReductionTo,smsSkuFullReductionEntity);
        smsSkuFullReductionEntity.setAddOther(skuFullReductionTo.getPriceStatus());
        this.save(smsSkuFullReductionEntity);
    }
}