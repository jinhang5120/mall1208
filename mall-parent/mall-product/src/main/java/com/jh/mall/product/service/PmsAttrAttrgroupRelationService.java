package com.jh.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jh.common.utils.PageUtils;
import com.jh.mall.product.entity.PmsAttrAttrgroupRelationEntity;
import com.jh.mall.product.vo.AttrGroupRelationVo;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 10:51:53
 */
public interface PmsAttrAttrgroupRelationService extends IService<PmsAttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PmsAttrAttrgroupRelationEntity selectByAttrId(Long attrId);

    PmsAttrAttrgroupRelationEntity selectByAttrIdAndAttrGroupId(Long attrId, Long attrGroupId);

    List<PmsAttrAttrgroupRelationEntity> selectByGroupId(Long attrGroupId);

    void deleteBatchByAttrIdAndAttrGroupId(AttrGroupRelationVo[] attrGroupRelationVos);
}

