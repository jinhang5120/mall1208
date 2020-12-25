package com.jh.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jh.common.utils.PageUtils;
import com.jh.mall.product.entity.PmsAttrEntity;
import com.jh.mall.product.vo.AttrRespVo;
import com.jh.mall.product.vo.AttrVo;

import java.util.Map;

/**
 * 
 *
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 10:51:53
 */
public interface PmsAttrService extends IService<PmsAttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttrVo(AttrVo attrVo);

    PageUtils queryPage(Map<String, Object> params, Long catId,String attrType);

    AttrRespVo getInfoById(Long attrId);

    void updateAttrVoById(AttrVo attrVo);

    PageUtils queryNoRelationAtrr(Map<String, Object> params, Long attrGroupId);
}

