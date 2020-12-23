package com.jh.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.common.utils.PageUtils;
import com.jh.common.utils.Query;
import com.jh.mall.product.dao.PmsBrandDao;
import com.jh.mall.product.entity.PmsBrandEntity;
import com.jh.mall.product.service.PmsBrandService;
import com.jh.mall.product.service.PmsCategoryBrandRelationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service("pmsBrandService")
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandDao, PmsBrandEntity> implements PmsBrandService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<PmsBrandEntity> queryWrapper = new QueryWrapper<>();
        String keyValue = (String) (params.get("key"));
        if(!StringUtils.isEmpty(keyValue)){
            queryWrapper.eq("brand_id",keyValue).or().like("name",keyValue);
        }
        IPage<PmsBrandEntity> page = this.page(
                new Query<PmsBrandEntity>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Autowired
    PmsCategoryBrandRelationService pmsCategoryBrandRelationService;
    @Override
    @Transactional //开启事务，只有在有注解@EnableTransactionManagement的时候才生效
    public void updateDetail(PmsBrandEntity pmsBrand) {
        this.updateById(pmsBrand);
        pmsCategoryBrandRelationService.updateBrand(pmsBrand.getBrandId(),pmsBrand.getName());

        //TODO,更新其他关联
    }

}