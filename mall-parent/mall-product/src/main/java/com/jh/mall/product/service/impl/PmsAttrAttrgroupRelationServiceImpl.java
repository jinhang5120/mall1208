package com.jh.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.common.utils.PageUtils;
import com.jh.common.utils.Query;
import com.jh.mall.product.dao.PmsAttrAttrgroupRelationDao;
import com.jh.mall.product.entity.PmsAttrAttrgroupRelationEntity;
import com.jh.mall.product.service.PmsAttrAttrgroupRelationService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("pmsAttrAttrgroupRelationService")
public class PmsAttrAttrgroupRelationServiceImpl extends ServiceImpl<PmsAttrAttrgroupRelationDao, PmsAttrAttrgroupRelationEntity> implements PmsAttrAttrgroupRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PmsAttrAttrgroupRelationEntity> page = this.page(
                new Query<PmsAttrAttrgroupRelationEntity>().getPage(params),
                new QueryWrapper<PmsAttrAttrgroupRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PmsAttrAttrgroupRelationEntity selectByAttrId(Long attrId) {
        return this.baseMapper.selectOne(new QueryWrapper<PmsAttrAttrgroupRelationEntity>().eq("attr_id",attrId));
    }

}