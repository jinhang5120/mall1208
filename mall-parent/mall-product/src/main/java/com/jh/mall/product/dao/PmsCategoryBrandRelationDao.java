package com.jh.mall.product.dao;

import com.jh.mall.product.entity.PmsCategoryBrandRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Ʒ
 * 
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 10:51:53
 */
@Mapper
public interface PmsCategoryBrandRelationDao extends BaseMapper<PmsCategoryBrandRelationEntity> {
    //@Param注解方便xml文件写sql语句时，用#{catId}代表参数名
    void updateCategory(@Param("catId") Long catId, @Param("name") String name);
}
