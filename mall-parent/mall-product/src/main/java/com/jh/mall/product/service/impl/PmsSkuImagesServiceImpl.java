package com.jh.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.common.utils.PageUtils;
import com.jh.common.utils.Query;
import com.jh.mall.product.dao.PmsSkuImagesDao;
import com.jh.mall.product.entity.PmsSkuImagesEntity;
import com.jh.mall.product.service.PmsSkuImagesService;
import com.jh.mall.product.vo.Images;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("pmsSkuImagesService")
public class PmsSkuImagesServiceImpl extends ServiceImpl<PmsSkuImagesDao, PmsSkuImagesEntity> implements PmsSkuImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PmsSkuImagesEntity> page = this.page(
                new Query<PmsSkuImagesEntity>().getPage(params),
                new QueryWrapper<PmsSkuImagesEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveBatch(List<Images> images, Long skuId) {
        if(images.size()>0){
            List<PmsSkuImagesEntity> pmsSkuImagesEntities = images.stream().map(image -> {
                PmsSkuImagesEntity pmsSkuImagesEntity = new PmsSkuImagesEntity();
                BeanUtils.copyProperties(image, pmsSkuImagesEntity);
                pmsSkuImagesEntity.setSkuId(skuId);
                return pmsSkuImagesEntity;
            }).filter(item-> !StringUtils.isEmpty(item.getImgUrl())).collect(Collectors.toList());
            this.saveBatch(pmsSkuImagesEntities);
        }
    }
}