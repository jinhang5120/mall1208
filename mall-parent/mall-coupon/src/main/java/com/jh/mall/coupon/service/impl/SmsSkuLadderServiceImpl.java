package com.jh.mall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.common.TO.SkuLadderTo;
import com.jh.common.utils.PageUtils;
import com.jh.common.utils.Query;
import com.jh.mall.coupon.dao.SmsSkuLadderDao;
import com.jh.mall.coupon.entity.SmsSkuLadderEntity;
import com.jh.mall.coupon.service.SmsSkuLadderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("smsSkuLadderService")
public class SmsSkuLadderServiceImpl extends ServiceImpl<SmsSkuLadderDao, SmsSkuLadderEntity> implements SmsSkuLadderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SmsSkuLadderEntity> page = this.page(
                new Query<SmsSkuLadderEntity>().getPage(params),
                new QueryWrapper<SmsSkuLadderEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSkuLandderTo(SkuLadderTo skuLadderTo) {
        SmsSkuLadderEntity smsSkuLadderEntity = new SmsSkuLadderEntity();
        BeanUtils.copyProperties(skuLadderTo,smsSkuLadderEntity);
        smsSkuLadderEntity.setAddOther(skuLadderTo.getCountStatus());
        this.save(smsSkuLadderEntity);
    }

}