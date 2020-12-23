package com.jh.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.common.utils.PageUtils;
import com.jh.common.utils.Query;
import com.jh.mall.product.dao.PmsAttrDao;
import com.jh.mall.product.entity.PmsAttrAttrgroupRelationEntity;
import com.jh.mall.product.entity.PmsAttrEntity;
import com.jh.mall.product.entity.PmsAttrGroupEntity;
import com.jh.mall.product.entity.PmsCategoryEntity;
import com.jh.mall.product.service.PmsAttrAttrgroupRelationService;
import com.jh.mall.product.service.PmsAttrGroupService;
import com.jh.mall.product.service.PmsAttrService;
import com.jh.mall.product.service.PmsCategoryService;
import com.jh.mall.product.vo.AttrRespVo;
import com.jh.mall.product.vo.AttrVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("pmsAttrService")
public class PmsAttrServiceImpl extends ServiceImpl<PmsAttrDao, PmsAttrEntity> implements PmsAttrService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PmsAttrEntity> page = this.page(
                new Query<PmsAttrEntity>().getPage(params),
                new QueryWrapper<PmsAttrEntity>()
        );

        return new PageUtils(page);
    }

    @Autowired
    PmsAttrAttrgroupRelationService pmsAttrAttrgroupRelationService;
    @Override
    @Transactional
    public void saveAttrVo(AttrVo attrVo) {
        PmsAttrEntity pmsAttrEntity = new PmsAttrEntity();
        BeanUtils.copyProperties(attrVo,pmsAttrEntity);
        this.save(pmsAttrEntity);//mybatisplus自动封装的方法，插入pmsAttrEntity后，pmsAttrEntity自增的id值也会自动被注入
        PmsAttrAttrgroupRelationEntity pmsAttrAttrgroupRelationEntity = new PmsAttrAttrgroupRelationEntity();
        pmsAttrAttrgroupRelationEntity.setAttrId(pmsAttrEntity.getAttrId());
        pmsAttrAttrgroupRelationEntity.setAttrGroupId(attrVo.getAttrGroupId());
        pmsAttrAttrgroupRelationService.save(pmsAttrAttrgroupRelationEntity);
    }

    @Autowired
    PmsCategoryService pmsCategoryService;
    @Autowired
    PmsAttrGroupService pmsAttrGroupService;
    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catId) {
        QueryWrapper<PmsAttrEntity> queryWrapper = new QueryWrapper<>();
        if(catId!=0){
            queryWrapper.eq("catelog_id",catId);
        }
        String keyValue = (String) (params.get("key"));
        if(!StringUtils.isEmpty(keyValue)){
            queryWrapper.eq("attr_id",keyValue).or().like("attr_name",keyValue);
        }
        IPage<PmsAttrEntity> page = this.page(new Query<PmsAttrEntity>().getPage(params), queryWrapper);
        PageUtils pageUtils = new PageUtils(page);
        List<PmsAttrEntity> records = page.getRecords();
        List<AttrRespVo> attrRespVos = new ArrayList<>();
        for (PmsAttrEntity pmsAttrEntity : records) {
            AttrRespVo attrRespVo = new AttrRespVo();
            BeanUtils.copyProperties(pmsAttrEntity,attrRespVo);

            PmsCategoryEntity pmsCategoryEntity = pmsCategoryService.getById(pmsAttrEntity.getCatelogId());
            if(pmsCategoryEntity!=null){//一定要判断为空，逻辑层面查数据一定要判空
                attrRespVo.setCatelogName(pmsCategoryEntity.getName());
            }

            PmsAttrAttrgroupRelationEntity pmsAttrAttrgroupRelationEntity = pmsAttrAttrgroupRelationService.selectByAttrId(pmsAttrEntity.getAttrId());
            if(pmsAttrAttrgroupRelationEntity!=null){//一定要判断为空，逻辑层面查数据一定要判空
                attrRespVo.setGroupName(pmsAttrGroupService.getById(pmsAttrAttrgroupRelationEntity.getAttrGroupId()).getAttrGroupName());
            }
            attrRespVos.add(attrRespVo);
        }
        pageUtils.setList(attrRespVos);
        return pageUtils;
    }

    @Override
    public AttrRespVo getInfoById(Long attrId) {
        AttrRespVo attrRespVo = new AttrRespVo();
        PmsAttrEntity pmsAttrEntity = this.getById(attrId);
        BeanUtils.copyProperties(pmsAttrEntity,attrRespVo);
        PmsCategoryEntity pmsCategoryEntity = pmsCategoryService.getById(attrRespVo.getCatelogId());
        if(pmsCategoryEntity!=null){
            attrRespVo.setCatelogName(pmsCategoryEntity.getName());
        }
        PmsAttrAttrgroupRelationEntity pmsAttrAttrgroupRelationEntity = pmsAttrAttrgroupRelationService.selectByAttrId(attrRespVo.getAttrId());
        if(pmsAttrAttrgroupRelationEntity!=null){
            attrRespVo.setAttrGroupId(pmsAttrAttrgroupRelationEntity.getAttrGroupId());
            PmsAttrGroupEntity pmsAttrGroupEntity = pmsAttrGroupService.getById(pmsAttrAttrgroupRelationEntity.getAttrGroupId());
            if(pmsAttrGroupEntity!=null){
                attrRespVo.setGroupName(pmsAttrGroupEntity.getAttrGroupName());
            }
        }
        attrRespVo.setCatelogPath(pmsCategoryService.getCatelogPath(attrRespVo.getCatelogId()));
        return attrRespVo;
    }
}