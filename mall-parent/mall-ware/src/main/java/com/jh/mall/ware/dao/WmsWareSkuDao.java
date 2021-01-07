package com.jh.mall.ware.dao;

import com.jh.mall.ware.entity.WmsWareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商品库存
 * 
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 15:15:05
 */
@Mapper
public interface WmsWareSkuDao extends BaseMapper<WmsWareSkuEntity> {

    Long hasStock(@Param("skuId") Long skuId);
}
