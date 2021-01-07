package com.jh.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jh.common.utils.PageUtils;
import com.jh.mall.product.entity.PmsSkuInfoEntity;
import com.jh.mall.product.entity.PmsSpuInfoEntity;
import com.jh.mall.product.vo.Skus;

import java.util.List;
import java.util.Map;

/**
 * sku
 *
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 10:51:53
 */
public interface PmsSkuInfoService extends IService<PmsSkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveBatch(List<Skus> skus, PmsSpuInfoEntity pmsSpuInfoEntity);

    PageUtils queryPageByCondition(Map<String, Object> params);

    List<PmsSkuInfoEntity> selectBySpuId(Long spuId);
}

