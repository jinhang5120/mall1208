package com.jh.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jh.common.utils.PageUtils;
import com.jh.mall.product.entity.PmsCategoryBrandRelationEntity;
import com.jh.mall.product.vo.BrandRespVo;

import java.util.List;
import java.util.Map;

/**
 * Ʒ
 *
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 10:51:53
 */
public interface PmsCategoryBrandRelationService extends IService<PmsCategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<PmsCategoryBrandRelationEntity> selectListByBrandId(Long brandId);

    void saveDetail(PmsCategoryBrandRelationEntity pmsCategoryBrandRelation);

    void updateBrand(Long brandId, String name);

    void updateCategory(Long catId, String name);

    List<BrandRespVo> brandsList(Long catId);
}

