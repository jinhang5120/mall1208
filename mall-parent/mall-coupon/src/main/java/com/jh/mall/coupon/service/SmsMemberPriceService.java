package com.jh.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jh.common.TO.MemberPriceTo;
import com.jh.common.utils.PageUtils;
import com.jh.mall.coupon.entity.SmsMemberPriceEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 14:51:31
 */
public interface SmsMemberPriceService extends IService<SmsMemberPriceEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveBatchMemberPriceTo(List<MemberPriceTo> memberPriceTos);
}

