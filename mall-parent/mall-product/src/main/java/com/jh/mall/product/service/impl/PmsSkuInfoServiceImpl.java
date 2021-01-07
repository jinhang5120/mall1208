package com.jh.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.common.TO.MemberPriceTo;
import com.jh.common.TO.SkuFullReductionTo;
import com.jh.common.TO.SkuLadderTo;
import com.jh.common.utils.PageUtils;
import com.jh.common.utils.Query;
import com.jh.common.utils.R;
import com.jh.mall.product.dao.PmsSkuInfoDao;
import com.jh.mall.product.entity.PmsSkuInfoEntity;
import com.jh.mall.product.entity.PmsSpuInfoEntity;
import com.jh.mall.product.feign.SmsCouponFeignService;
import com.jh.mall.product.service.PmsSkuImagesService;
import com.jh.mall.product.service.PmsSkuInfoService;
import com.jh.mall.product.service.PmsSkuSaleAttrValueService;
import com.jh.mall.product.vo.Images;
import com.jh.mall.product.vo.Skus;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("pmsSkuInfoService")
public class PmsSkuInfoServiceImpl extends ServiceImpl<PmsSkuInfoDao, PmsSkuInfoEntity> implements PmsSkuInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PmsSkuInfoEntity> page = this.page(
                new Query<PmsSkuInfoEntity>().getPage(params),
                new QueryWrapper<PmsSkuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Autowired
    PmsSkuImagesService pmsSkuImagesService;
    @Autowired
    PmsSkuSaleAttrValueService pmsSkuSaleAttrValueService;
    @Autowired
    SmsCouponFeignService smsCouponFeignService;

    @Override
    @Transactional
    public void saveBatch(List<Skus> skus, PmsSpuInfoEntity pmsSpuInfoEntity) {
        if (skus.size() > 0) {
            for (Skus sku : skus) {
                PmsSkuInfoEntity pmsSkuInfoEntity = new PmsSkuInfoEntity();
                BeanUtils.copyProperties(sku, pmsSkuInfoEntity);
                pmsSkuInfoEntity.setSpuId(pmsSpuInfoEntity.getId());
                pmsSkuInfoEntity.setCatalogId(pmsSpuInfoEntity.getCatalogId());
                pmsSkuInfoEntity.setBrandId(pmsSpuInfoEntity.getBrandId());
                pmsSkuInfoEntity.setSaleCount(0L);
                for (Images image : sku.getImages()) {
                    if (image.getDefaultImg() == 1) {
                        pmsSkuInfoEntity.setSkuDefaultImg(image.getImgUrl());
                        break;
                    }
                }
                //6.1 sku的基本信息 pms_sku_info
                this.save(pmsSkuInfoEntity);
                //6.2 sku的图片信息 pms_sku_images
                pmsSkuImagesService.saveBatch(sku.getImages(), pmsSkuInfoEntity.getSkuId());
                //6.3 sku的销售属性信息 pms_sku_sale_attr_value
                pmsSkuSaleAttrValueService.saveBatch(sku.getAttr(), pmsSkuInfoEntity.getSkuId());
                //6.4 sku的优惠、满减信息 sms_sku_ladder、sms_sku_full_reduction、sms_member_price

                SkuFullReductionTo skuFullReductionTo = new SkuFullReductionTo();
                BeanUtils.copyProperties(sku, skuFullReductionTo);
                skuFullReductionTo.setSkuId(pmsSkuInfoEntity.getSkuId());
                R r1 = smsCouponFeignService.saveSkuFullReductionTo(skuFullReductionTo);
                System.out.println("r1 = " + r1);

                SkuLadderTo skuLadderTo = new SkuLadderTo();
                BeanUtils.copyProperties(sku, skuLadderTo);
                skuLadderTo.setSkuId(pmsSkuInfoEntity.getSkuId());
                R r2 = smsCouponFeignService.saveSkuLandderTo(skuLadderTo);
                System.out.println("r2 = " + r2);

                List<MemberPriceTo> memberPriceTos = sku.getMemberPrice().stream().map(item -> {
                    MemberPriceTo memberPriceTo = new MemberPriceTo();
                    BeanUtils.copyProperties(item, memberPriceTo);
                    memberPriceTo.setSkuId(pmsSkuInfoEntity.getSkuId());
                    memberPriceTo.setPriceStatus(sku.getPriceStatus());
                    return memberPriceTo;
                }).collect(Collectors.toList());
                R r3 = smsCouponFeignService.saveBatchMemberPriceTo(memberPriceTos);
                System.out.println("r3 = " + r3);
            }
        }

    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<PmsSkuInfoEntity> queryWrapper = new QueryWrapper<>();
        String catelogId = (String) params.get("catelogId");
        if (!"0".equals(catelogId)) {
            queryWrapper.eq("catalog_id", catelogId);
        }
        String brandId = (String) params.get("brandId");
        if (!"0".equals(brandId)) {
            queryWrapper.eq("brandId",brandId);
        }
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.and(item -> item.eq("sku_id", key).or().like("sku_name", key));
        }
        String min = (String) params.get("min");
        queryWrapper.ge("price", new BigDecimal(min));
        String max = (String) (params.get("max"));
        if (!"0".equals(max)) {
            queryWrapper.le("price",  new BigDecimal(max));
        }
        IPage<PmsSkuInfoEntity> page = this.page(
                new Query<PmsSkuInfoEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public List<PmsSkuInfoEntity> selectBySpuId(Long spuId) {
        return this.list(new QueryWrapper<PmsSkuInfoEntity>().eq("spu_id",spuId));
    }
}