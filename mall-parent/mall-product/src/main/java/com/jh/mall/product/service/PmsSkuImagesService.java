package com.jh.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jh.common.utils.PageUtils;
import com.jh.mall.product.entity.PmsSkuImagesEntity;

import java.util.Map;

/**
 * skuͼƬ
 *
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 10:51:53
 */
public interface PmsSkuImagesService extends IService<PmsSkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

