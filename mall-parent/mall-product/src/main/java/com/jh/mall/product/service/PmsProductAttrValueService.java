package com.jh.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jh.common.utils.PageUtils;
import com.jh.mall.product.entity.PmsProductAttrValueEntity;
import com.jh.mall.product.vo.BaseAttrs;

import java.util.List;
import java.util.Map;

/**
 * spu
 *
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 10:51:53
 */
public interface PmsProductAttrValueService extends IService<PmsProductAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveBatch(Long id, List<BaseAttrs> baseAttrs);

    List<PmsProductAttrValueEntity> baselistForSpu(Long spuId);

    void attrUpdateBySpuId(Long spuId, List<PmsProductAttrValueEntity> pmsProductAttrValueEntities);
}

