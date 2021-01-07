package com.jh.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.common.utils.PageUtils;
import com.jh.common.utils.Query;
import com.jh.mall.product.dao.PmsProductAttrValueDao;
import com.jh.mall.product.entity.PmsProductAttrValueEntity;
import com.jh.mall.product.service.PmsAttrService;
import com.jh.mall.product.service.PmsProductAttrValueService;
import com.jh.mall.product.vo.BaseAttrs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("pmsProductAttrValueService")
public class PmsProductAttrValueServiceImpl extends ServiceImpl<PmsProductAttrValueDao, PmsProductAttrValueEntity> implements PmsProductAttrValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PmsProductAttrValueEntity> page = this.page(
                new Query<PmsProductAttrValueEntity>().getPage(params),
                new QueryWrapper<PmsProductAttrValueEntity>()
        );

        return new PageUtils(page);
    }

    @Autowired
    PmsAttrService pmsAttrService;
    @Override
    public void saveBatch(Long id, List<BaseAttrs> baseAttrs) {
        if (baseAttrs.size()>0) {
            List<PmsProductAttrValueEntity> pmsProductAttrValueEntities = baseAttrs.stream().map(item -> {
                PmsProductAttrValueEntity pmsProductAttrValueEntity = new PmsProductAttrValueEntity();
                pmsProductAttrValueEntity.setSpuId(id);
                pmsProductAttrValueEntity.setAttrId(item.getAttrId());
                pmsProductAttrValueEntity.setAttrName(pmsAttrService.getById(pmsProductAttrValueEntity.getAttrId()).getAttrName());
                pmsProductAttrValueEntity.setAttrValue(item.getAttrValues());
                pmsProductAttrValueEntity.setQuickShow(item.getShowDesc());
                return pmsProductAttrValueEntity;
            }).collect(Collectors.toList());
            this.saveBatch(pmsProductAttrValueEntities);
        }
    }

    @Override
    public List<PmsProductAttrValueEntity> baselistForSpu(Long spuId) {
        return this.baseMapper.selectList(new QueryWrapper<PmsProductAttrValueEntity>().eq("spu_id",spuId));
    }

    @Override
    public void attrUpdateBySpuId(Long spuId, List<PmsProductAttrValueEntity> pmsProductAttrValueEntities) {
        this.remove(new QueryWrapper<PmsProductAttrValueEntity>().eq("spu_id",spuId));
        List<PmsProductAttrValueEntity> collect = pmsProductAttrValueEntities.stream().map(item -> {
            item.setSpuId(spuId);
            return item;
        }).collect(Collectors.toList());
        this.saveBatch(collect);
    }
}