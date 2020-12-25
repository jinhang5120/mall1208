package com.jh.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.common.utils.PageUtils;
import com.jh.common.utils.Query;
import com.jh.mall.product.dao.PmsAttrAttrgroupRelationDao;
import com.jh.mall.product.entity.PmsAttrAttrgroupRelationEntity;
import com.jh.mall.product.service.PmsAttrAttrgroupRelationService;
import com.jh.mall.product.vo.AttrGroupRelationVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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

    @Override
    public PmsAttrAttrgroupRelationEntity selectByAttrIdAndAttrGroupId(Long attrId, Long attrGroupId) {
        return this.baseMapper.selectOne(new QueryWrapper<PmsAttrAttrgroupRelationEntity>().eq("attr_id",attrId).eq("attr_group_id",attrGroupId));
    }

    @Override
    public List<PmsAttrAttrgroupRelationEntity> selectByGroupId(Long attrGroupId) {
        return this.list(new QueryWrapper<PmsAttrAttrgroupRelationEntity>().eq("attr_group_id",attrGroupId));
    }

    @Override
    public void deleteBatchByAttrIdAndAttrGroupId(AttrGroupRelationVo[] attrGroupRelationVos) {
        List<PmsAttrAttrgroupRelationEntity> PmsAttrAttrgroupRelationEntities = Arrays.stream(attrGroupRelationVos)
                .map((attrGroupRelationVo)->{
                    PmsAttrAttrgroupRelationEntity pmsAttrAttrgroupRelationEntity = new PmsAttrAttrgroupRelationEntity();
                    BeanUtils.copyProperties(attrGroupRelationVo,pmsAttrAttrgroupRelationEntity);
                    return pmsAttrAttrgroupRelationEntity;
                })
                .collect(Collectors.toList());
        this.baseMapper.deleteBatchByAttrIdAndAttrGroupId(PmsAttrAttrgroupRelationEntities);
    }
}