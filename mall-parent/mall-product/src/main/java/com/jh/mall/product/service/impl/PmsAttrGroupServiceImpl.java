package com.jh.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.common.utils.PageUtils;
import com.jh.common.utils.Query;
import com.jh.mall.product.dao.PmsAttrGroupDao;
import com.jh.mall.product.entity.PmsAttrAttrgroupRelationEntity;
import com.jh.mall.product.entity.PmsAttrEntity;
import com.jh.mall.product.entity.PmsAttrGroupEntity;
import com.jh.mall.product.service.PmsAttrAttrgroupRelationService;
import com.jh.mall.product.service.PmsAttrGroupService;
import com.jh.mall.product.service.PmsAttrService;
import com.jh.mall.product.vo.AttrGroupRelationVo;
import com.jh.mall.product.vo.AttrGroupWithAttrsVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("pmsAttrGroupService")
public class PmsAttrGroupServiceImpl extends ServiceImpl<PmsAttrGroupDao, PmsAttrGroupEntity> implements PmsAttrGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PmsAttrGroupEntity> page = this.page(
                new Query<PmsAttrGroupEntity>().getPage(params),
                new QueryWrapper<PmsAttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long categoryId) {
        QueryWrapper<PmsAttrGroupEntity> queryWrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            queryWrapper.and(
                    (obj)->{obj.eq("attr_group_id",key).or().like("attr_group_name",key);}
            );
        }
        if(categoryId != 0){
            queryWrapper.eq("catelog_id", categoryId);
        }
        return new PageUtils(this.page(new Query<PmsAttrGroupEntity>().getPage(params),queryWrapper));
    }

    @Autowired
    PmsAttrAttrgroupRelationService pmsAttrAttrgroupRelationService;
    @Autowired
    PmsAttrService pmsAttrService;
    @Override
    public List<PmsAttrEntity> attrRelation(Long attrGroupId) {
        List<PmsAttrAttrgroupRelationEntity> pmsAttrAttrgroupRelationEntities = pmsAttrAttrgroupRelationService.selectByGroupId(attrGroupId);
        if(pmsAttrAttrgroupRelationEntities!=null){//记得一定要判断为空
            List<Long> attrIds = pmsAttrAttrgroupRelationEntities.stream()
                    .map(PmsAttrAttrgroupRelationEntity::getAttrId).collect(Collectors.toList());
            if (attrIds.size() > 0) {//记得一定要判断为空
                return pmsAttrService.listByIds(attrIds);//尽量避免循环查表，用封装的api方法批量去查
            }
        }
        return new ArrayList<>();
    }

    @Override
    public void deleteRelation(AttrGroupRelationVo[] attrGroupRelationVos) {
        pmsAttrAttrgroupRelationService.deleteBatchByAttrIdAndAttrGroupId(attrGroupRelationVos);
    }

    @Override
    public void saveAttrRelation(AttrGroupRelationVo[] attrGroupRelationVos) {
        List<PmsAttrAttrgroupRelationEntity> pmsAttrAttrgroupRelationEntities = Arrays.stream(attrGroupRelationVos)
                .map((item) -> {
                    PmsAttrAttrgroupRelationEntity pmsAttrAttrgroupRelationEntity = new PmsAttrAttrgroupRelationEntity();
                    BeanUtils.copyProperties(item, pmsAttrAttrgroupRelationEntity);
                    return pmsAttrAttrgroupRelationEntity;
                }).collect(Collectors.toList());
        pmsAttrAttrgroupRelationService.saveBatch(pmsAttrAttrgroupRelationEntities);
    }

    @Override
    public List<AttrGroupWithAttrsVo> catelogIdWithAttr(Long catelogId) {
        List<PmsAttrGroupEntity> pmsAttrGroupEntities = this.list(new QueryWrapper<PmsAttrGroupEntity>().eq("catelog_id", catelogId));
        if(pmsAttrGroupEntities.size()>0){
            return pmsAttrGroupEntities
                    .stream()
                    .map(pmsAttrGroupEntity -> {
                        AttrGroupWithAttrsVo attrGroupWithAttrsVo = new AttrGroupWithAttrsVo();
                        BeanUtils.copyProperties(pmsAttrGroupEntity,attrGroupWithAttrsVo);
                        attrGroupWithAttrsVo.setAttrs(this.attrRelation(pmsAttrGroupEntity.getAttrGroupId()));
                        return attrGroupWithAttrsVo;
                    }).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}