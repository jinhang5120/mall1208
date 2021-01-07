package com.jh.mall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.common.TO.MemberPriceTo;
import com.jh.common.utils.PageUtils;
import com.jh.common.utils.Query;
import com.jh.mall.coupon.dao.SmsMemberPriceDao;
import com.jh.mall.coupon.entity.SmsMemberPriceEntity;
import com.jh.mall.coupon.service.SmsMemberPriceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("smsMemberPriceService")
public class SmsMemberPriceServiceImpl extends ServiceImpl<SmsMemberPriceDao, SmsMemberPriceEntity> implements SmsMemberPriceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SmsMemberPriceEntity> page = this.page(
                new Query<SmsMemberPriceEntity>().getPage(params),
                new QueryWrapper<SmsMemberPriceEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveBatchMemberPriceTo(List<MemberPriceTo> memberPriceTos) {
        List<SmsMemberPriceEntity> smsMemberPriceEntities = memberPriceTos.stream().map(item -> {
            SmsMemberPriceEntity smsMemberPriceEntity = new SmsMemberPriceEntity();
            smsMemberPriceEntity.setSkuId(item.getSkuId());
            smsMemberPriceEntity.setMemberLevelId(item.getId());
            smsMemberPriceEntity.setMemberLevelName(item.getName());
            smsMemberPriceEntity.setMemberPrice(item.getPrice());
            smsMemberPriceEntity.setAddOther(1);
            return smsMemberPriceEntity;
        }).collect(Collectors.toList());
        this.saveBatch(smsMemberPriceEntities);
    }
}