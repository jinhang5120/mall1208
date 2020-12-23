package com.jh.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.common.utils.PageUtils;
import com.jh.common.utils.Query;
import com.jh.mall.product.dao.PmsCategoryDao;
import com.jh.mall.product.entity.PmsCategoryEntity;
import com.jh.mall.product.service.PmsCategoryBrandRelationService;
import com.jh.mall.product.service.PmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service("pmsCategoryService")
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryDao, PmsCategoryEntity> implements PmsCategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PmsCategoryEntity> page = this.page(
                new Query<PmsCategoryEntity>().getPage(params),
                new QueryWrapper<PmsCategoryEntity>()
        );

        return new PageUtils(page);
    }

/*    @Autowired(required = false)
    PmsCategoryDao pmsCategoryDao;*/
    @Override
    public List<PmsCategoryEntity> listWithTree() {
        //查出所有分类，组装成树形结构
        //baseMapper通过继承父类的泛型，在这里实质上就是PmsCategoryDao pmsCategoryDao
        List<PmsCategoryEntity> all = baseMapper.selectList(null);
        return all
                .stream()
                .filter(entity -> entity.getParentCid() == 0)//取一级菜单
                .map(entity -> {
                    entity.setChildren(getTheChildren(entity,all));
                    return entity;})
//                .sorted((entity1,entity2) -> (entity1.getSort()==null?0:entity1.getSort()) - (entity2.getSort()==null?0:entity2.getSort())) //排序
                .sorted(Comparator.comparingInt(entity -> Optional.ofNullable(entity.getSort()).orElse(0)))
                .collect(Collectors.toList());
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        //TODO 1.检查当前删除的菜单，是否被别的地方引用
        baseMapper.deleteBatchIds(asList);
    }

    @Autowired
    PmsCategoryBrandRelationService pmsCategoryBrandRelationService;
    @Override
    @Transactional
    public void updateDetail(PmsCategoryEntity pmsCategory) {
        this.updateById(pmsCategory);
        pmsCategoryBrandRelationService.updateCategory(pmsCategory.getCatId(),pmsCategory.getName());
        //TODO，修改要关联其他数据，牵一发动全身
    }

    @Override
    public Long[] getCatelogPath(Long catId) {
        Long[] longs = new Long[3];
        List<Long> catelogPathList = new ArrayList<>();
        List<PmsCategoryEntity> list = this.list();
        while(catId!=0){
            for (PmsCategoryEntity pmsCategoryEntity : list) {
                if(pmsCategoryEntity.getCatId().equals(catId)){
                    catelogPathList.add(0,catId);
                    catId = pmsCategoryEntity.getParentCid();
                    break;
                }
            }
        }
        longs = catelogPathList.toArray(longs);
        return longs;
    }

    //递归查找所有菜单的子菜单，不管几级菜单都可以查询出来
    private List<PmsCategoryEntity> getTheChildren(PmsCategoryEntity parentEntity, List<PmsCategoryEntity> all) {
        return all
                .stream()
                .filter(entity -> entity.getParentCid().equals(parentEntity.getCatId()))//注意在map前，防止递归死循环
                .map(entity -> {
                    entity.setChildren(getTheChildren(entity,all));//递归
                    return entity;})
//                .sorted((entity1,entity2) -> (entity1.getSort()==null?0:entity1.getSort()) - (entity2.getSort()==null?0:entity2.getSort())) //排序
                .sorted(Comparator.comparingInt(entity -> Optional.ofNullable(entity.getSort()).orElse(0)))
                .collect(Collectors.toList());
    }
}