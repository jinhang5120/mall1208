package com.jh.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.common.TO.HasStockTo;
import com.jh.common.utils.PageUtils;
import com.jh.common.utils.Query;
import com.jh.common.utils.R;
import com.jh.mall.ware.dao.WmsWareSkuDao;
import com.jh.mall.ware.entity.WmsWareSkuEntity;
import com.jh.mall.ware.feign.ProductFeignService;
import com.jh.mall.ware.service.WmsWareSkuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("wmsWareSkuService")
public class WmsWareSkuServiceImpl extends ServiceImpl<WmsWareSkuDao, WmsWareSkuEntity> implements WmsWareSkuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WmsWareSkuEntity> page = this.page(
                new Query<WmsWareSkuEntity>().getPage(params),
                new QueryWrapper<WmsWareSkuEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<WmsWareSkuEntity> queryWrapper = new QueryWrapper<>();
        String skuId = (String) params.get("skuId");
        if (!StringUtils.isEmpty(skuId)) {
            queryWrapper.eq("sku_id", skuId);
        }
        String wareId = (String) params.get("wareId");
        if (!StringUtils.isEmpty(wareId)) {
            queryWrapper.eq("ware_id", wareId);
        }
        IPage<WmsWareSkuEntity> page = this.page(
                new Query<WmsWareSkuEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Autowired
    ProductFeignService productFeignService;

    @Override
    @Transactional
    public void gotoStock(Long skuId, Integer skuNum, Long wareId) {
        WmsWareSkuEntity wmsWareSkuEntity = this.baseMapper.selectOne(new QueryWrapper<WmsWareSkuEntity>().eq("sku_id", skuId).eq("ware_id", wareId));
        if (wmsWareSkuEntity == null) {
            wmsWareSkuEntity = new WmsWareSkuEntity();
            wmsWareSkuEntity.setSkuId(skuId);
            wmsWareSkuEntity.setWareId(wareId);
            wmsWareSkuEntity.setStock(skuNum);
            try {//处理异常，即便有异常,事务也不回滚
                R r = productFeignService.info(skuId);
                if(0 == (Integer) r.get("code")){
                    Map<String, Object> pmsSkuInfo = (Map<String, Object>) r.get("pmsSkuInfo");
                    wmsWareSkuEntity.setSkuName((String) pmsSkuInfo.get("skuName"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            wmsWareSkuEntity.setStockLocked(0);
            this.save(wmsWareSkuEntity);
        } else {
            wmsWareSkuEntity.setStock(wmsWareSkuEntity.getStock() + skuNum);
            this.updateById(wmsWareSkuEntity);
        }
    }

    @Override
    public List<HasStockTo> hasStock(List<Long> skuIds) {
        return skuIds.stream().map(skuId->{
            HasStockTo hasStockTo = new HasStockTo();
            hasStockTo.setSkuId(skuId);
            Long count = this.baseMapper.hasStock(skuId);
            hasStockTo.setHasStock(count != null && count > 0);
            return hasStockTo;
        }).collect(Collectors.toList());
    }

}