package com.jh.mall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.common.TO.BoundsTo;
import com.jh.common.utils.PageUtils;
import com.jh.common.utils.Query;
import com.jh.mall.coupon.dao.SmsSpuBoundsDao;
import com.jh.mall.coupon.entity.SmsSpuBoundsEntity;
import com.jh.mall.coupon.service.SmsSpuBoundsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("smsSpuBoundsService")
public class SmsSpuBoundsServiceImpl extends ServiceImpl<SmsSpuBoundsDao, SmsSpuBoundsEntity> implements SmsSpuBoundsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SmsSpuBoundsEntity> page = this.page(
                new Query<SmsSpuBoundsEntity>().getPage(params),
                new QueryWrapper<SmsSpuBoundsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void save(BoundsTo boundsTo) {
        SmsSpuBoundsEntity smsSpuBoundsEntity = new SmsSpuBoundsEntity();
        BeanUtils.copyProperties(boundsTo,smsSpuBoundsEntity);
        this.save(smsSpuBoundsEntity);
    }
}