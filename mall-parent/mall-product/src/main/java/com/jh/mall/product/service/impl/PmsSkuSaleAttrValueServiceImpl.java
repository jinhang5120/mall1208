package com.jh.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.common.utils.PageUtils;
import com.jh.common.utils.Query;
import com.jh.mall.product.dao.PmsSkuSaleAttrValueDao;
import com.jh.mall.product.entity.PmsSkuSaleAttrValueEntity;
import com.jh.mall.product.service.PmsSkuSaleAttrValueService;
import com.jh.mall.product.vo.Attr;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("pmsSkuSaleAttrValueService")
public class PmsSkuSaleAttrValueServiceImpl extends ServiceImpl<PmsSkuSaleAttrValueDao, PmsSkuSaleAttrValueEntity> implements PmsSkuSaleAttrValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PmsSkuSaleAttrValueEntity> page = this.page(
                new Query<PmsSkuSaleAttrValueEntity>().getPage(params),
                new QueryWrapper<PmsSkuSaleAttrValueEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveBatch(List<Attr> attr, Long skuId) {
        if(attr.size()>0){
            List<PmsSkuSaleAttrValueEntity> pmsSkuSaleAttrValueEntities = attr.stream().map(item -> {
                PmsSkuSaleAttrValueEntity pmsSkuSaleAttrValueEntity = new PmsSkuSaleAttrValueEntity();
                BeanUtils.copyProperties(item, pmsSkuSaleAttrValueEntity);
                pmsSkuSaleAttrValueEntity.setSkuId(skuId);
                return pmsSkuSaleAttrValueEntity;
            }).collect(Collectors.toList());
            this.saveBatch(pmsSkuSaleAttrValueEntities);
        }
    }

}