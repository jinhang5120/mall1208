package com.jh.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jh.common.TO.HasStockTo;
import com.jh.common.utils.PageUtils;
import com.jh.mall.ware.entity.WmsWareSkuEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 15:15:05
 */
public interface WmsWareSkuService extends IService<WmsWareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageByCondition(Map<String, Object> params);

    void gotoStock(Long skuId, Integer skuNum, Long wareId);

    List<HasStockTo> hasStock(List<Long> skuIds);
}

