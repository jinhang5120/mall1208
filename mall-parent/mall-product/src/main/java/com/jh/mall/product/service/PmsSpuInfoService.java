package com.jh.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jh.common.utils.PageUtils;
import com.jh.mall.product.entity.PmsSpuInfoEntity;
import com.jh.mall.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * spu
 *
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 10:51:53
 */
public interface PmsSpuInfoService extends IService<PmsSpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfo(SpuSaveVo spuSaveVo);

    PageUtils queryPageByCondition(Map<String, Object> params);

    void up(Long spuId);
}

