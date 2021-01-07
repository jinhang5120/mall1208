package com.jh.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jh.common.utils.PageUtils;
import com.jh.mall.product.entity.PmsSkuSaleAttrValueEntity;
import com.jh.mall.product.vo.Attr;

import java.util.List;
import java.util.Map;

/**
 * sku
 *
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 10:51:53
 */
public interface PmsSkuSaleAttrValueService extends IService<PmsSkuSaleAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveBatch(List<Attr> attr, Long skuId);
}

