package com.jh.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.common.utils.PageUtils;
import com.jh.common.utils.Query;
import com.jh.mall.product.dao.PmsSpuImagesDao;
import com.jh.mall.product.entity.PmsSpuImagesEntity;
import com.jh.mall.product.service.PmsSpuImagesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("pmsSpuImagesService")
public class PmsSpuImagesServiceImpl extends ServiceImpl<PmsSpuImagesDao, PmsSpuImagesEntity> implements PmsSpuImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PmsSpuImagesEntity> page = this.page(
                new Query<PmsSpuImagesEntity>().getPage(params),
                new QueryWrapper<PmsSpuImagesEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveBatch(Long id, List<String> images) {
        if(images.size()>0){
            List<PmsSpuImagesEntity> pmsSpuImagesEntities = images.stream().map(item -> {
                PmsSpuImagesEntity pmsSpuImagesEntity = new PmsSpuImagesEntity();
                pmsSpuImagesEntity.setSpuId(id);
                pmsSpuImagesEntity.setImgName("image" + id);
                pmsSpuImagesEntity.setImgUrl(item);
                return pmsSpuImagesEntity;
            }).collect(Collectors.toList());
            this.saveBatch(pmsSpuImagesEntities);
        }
    }
}