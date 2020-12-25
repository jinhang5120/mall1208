package com.jh.mall.product.dao;

import com.jh.mall.product.entity.PmsAttrAttrgroupRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 10:51:53
 */
@Mapper
public interface PmsAttrAttrgroupRelationDao extends BaseMapper<PmsAttrAttrgroupRelationEntity> {

    void deleteBatchByAttrIdAndAttrGroupId(@Param("entities") List<PmsAttrAttrgroupRelationEntity> pmsAttrAttrgroupRelationEntities);
}
