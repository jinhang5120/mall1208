package com.jh.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jh.common.utils.PageUtils;
import com.jh.mall.product.entity.PmsAttrEntity;
import com.jh.mall.product.entity.PmsAttrGroupEntity;
import com.jh.mall.product.vo.AttrGroupRelationVo;
import com.jh.mall.product.vo.AttrGroupWithAttrsVo;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 10:51:53
 */
public interface PmsAttrGroupService extends IService<PmsAttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Long categoryId);

    List<PmsAttrEntity> attrRelation(Long attrGroupId);

    void deleteRelation(AttrGroupRelationVo[] attrGroupRelationVos);

    void saveAttrRelation(AttrGroupRelationVo[] attrGroupRelationVos);

    List<AttrGroupWithAttrsVo> catelogIdWithAttr(Long catelogId);
}

