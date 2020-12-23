package com.jh.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.common.utils.PageUtils;
import com.jh.common.utils.Query;
import com.jh.mall.product.dao.PmsCategoryBrandRelationDao;
import com.jh.mall.product.entity.PmsBrandEntity;
import com.jh.mall.product.entity.PmsCategoryBrandRelationEntity;
import com.jh.mall.product.entity.PmsCategoryEntity;
import com.jh.mall.product.service.PmsBrandService;
import com.jh.mall.product.service.PmsCategoryBrandRelationService;
import com.jh.mall.product.service.PmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("pmsCategoryBrandRelationService")
public class PmsCategoryBrandRelationServiceImpl extends ServiceImpl<PmsCategoryBrandRelationDao, PmsCategoryBrandRelationEntity> implements PmsCategoryBrandRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PmsCategoryBrandRelationEntity> page = this.page(
                new Query<PmsCategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<PmsCategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<PmsCategoryBrandRelationEntity> selectListByBrandId(Long brandId) {
        return this.baseMapper.selectList(new QueryWrapper<PmsCategoryBrandRelationEntity>().eq("brand_id",brandId));
    }

    @Autowired
    PmsCategoryService pmsCategoryService;
    @Autowired
    PmsBrandService pmsBrandService;
    @Override
    public void saveDetail(PmsCategoryBrandRelationEntity pmsCategoryBrandRelation) {
        pmsCategoryBrandRelation.setCatelogName(pmsCategoryService.getById(new QueryWrapper<PmsCategoryEntity>().eq("cat_id",pmsCategoryBrandRelation.getCatelogId())).getName());
        pmsCategoryBrandRelation.setBrandName(pmsBrandService.getById(new QueryWrapper<PmsBrandEntity>().eq("brand_id",pmsCategoryBrandRelation.getBrandId())).getName());
        this.save(pmsCategoryBrandRelation);
    }

    @Override
    public void updateBrand(Long brandId, String name) {
        PmsCategoryBrandRelationEntity pmsCategoryBrandRelationEntity = new PmsCategoryBrandRelationEntity();
        pmsCategoryBrandRelationEntity.setBrandId(brandId);
        pmsCategoryBrandRelationEntity.setBrandName((name));
        this.update(pmsCategoryBrandRelationEntity,new UpdateWrapper<PmsCategoryBrandRelationEntity>().eq("brand_id",brandId));
    }

    @Override
    public void updateCategory(Long catId, String name) {
        this.baseMapper.updateCategory(catId,name);
    }
}