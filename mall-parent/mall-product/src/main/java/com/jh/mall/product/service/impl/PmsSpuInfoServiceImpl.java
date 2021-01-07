package com.jh.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.common.TO.BoundsTo;
import com.jh.common.TO.HasStockTo;
import com.jh.common.TO.SkuEsModel;
import com.jh.common.constant.ProductConstant;
import com.jh.common.utils.PageUtils;
import com.jh.common.utils.Query;
import com.jh.common.utils.R;
import com.jh.mall.product.dao.PmsSpuInfoDao;
import com.jh.mall.product.entity.PmsBrandEntity;
import com.jh.mall.product.entity.PmsProductAttrValueEntity;
import com.jh.mall.product.entity.PmsSkuInfoEntity;
import com.jh.mall.product.entity.PmsSpuInfoEntity;
import com.jh.mall.product.feign.SearchFeignService;
import com.jh.mall.product.feign.SmsCouponFeignService;
import com.jh.mall.product.feign.WmsWareFeignService;
import com.jh.mall.product.service.*;
import com.jh.mall.product.vo.SpuSaveVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service("pmsSpuInfoService")
@Slf4j
public class PmsSpuInfoServiceImpl extends ServiceImpl<PmsSpuInfoDao, PmsSpuInfoEntity> implements PmsSpuInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PmsSpuInfoEntity> page = this.page(
                new Query<PmsSpuInfoEntity>().getPage(params),
                new QueryWrapper<PmsSpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Autowired
    PmsSpuInfoDescService pmsSpuInfoDescService;
    @Autowired
    PmsSpuImagesService pmsSpuImagesService;
    @Autowired
    PmsProductAttrValueService pmsProductAttrValueService;
    @Autowired
    PmsSkuInfoService pmsSkuInfoService;
    @Autowired
    SmsCouponFeignService smsCouponFeignService;
    @Override
    @Transactional
    public void saveSpuInfo(SpuSaveVo spuSaveVo) {
        //1.保存spu基本信息 pms_spu_info
        PmsSpuInfoEntity pmsSpuInfoEntity = new PmsSpuInfoEntity();
        BeanUtils.copyProperties(spuSaveVo, pmsSpuInfoEntity);
        pmsSpuInfoEntity.setCreateTime(new Date());
        pmsSpuInfoEntity.setUpdateTime(new Date());
        this.save(pmsSpuInfoEntity);
        //2.保存spu描述信息 pms_spu_info_desc
        pmsSpuInfoDescService.saveSpuInfoDescEntity(pmsSpuInfoEntity.getId(),spuSaveVo.getDecript());
        //3.保存spu的图片集 pms_spu_images
        pmsSpuImagesService.saveBatch(pmsSpuInfoEntity.getId(),spuSaveVo.getImages());
        //4.保存spu的规格参数 pms_product_attr_value
        pmsProductAttrValueService.saveBatch(pmsSpuInfoEntity.getId(),spuSaveVo.getBaseAttrs());
        //5.保存spu的积分信息 sms_spu_bounds
        BoundsTo boundsTo = new BoundsTo();
        BeanUtils.copyProperties(spuSaveVo.getBounds(),boundsTo);
        boundsTo.setSpuId(pmsSpuInfoEntity.getId());
        R r = smsCouponFeignService.saveBounds(boundsTo);
        System.out.println(r.get("code"));

        //6.保存spu对应的sku信息
        //6.1 sku的基本信息 pms_sku_info
        //6.2 sku的图片信息 pms_sku_images
        //6.3 sku的销售属性信息 pms_sku_sale_attr_value
        //6.4 sku的优惠、满减信息 sms_sku_ladder、sms_sku_full_reduction、sms_member_price
        pmsSkuInfoService.saveBatch(spuSaveVo.getSkus(),pmsSpuInfoEntity);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<PmsSpuInfoEntity> queryWrapper = new QueryWrapper<>();
        String catelogId = (String) params.get("catelogId");
        if (!StringUtils.isEmpty(catelogId)) {
            queryWrapper.eq("catalog_id", catelogId);
        }
        String brandId = (String) params.get("brandId");
        if (!StringUtils.isEmpty(catelogId)) {
            queryWrapper.eq("brandId",brandId);
        }
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            ////queryWrapper.and是对queryWrapper的查询条件进行嵌套，从而避免语义歧义的问题
            queryWrapper.and(item ->item.eq("id",key).or().like("spu_name",key));
        }

        IPage<PmsSpuInfoEntity> page = this.page(
                new Query<PmsSpuInfoEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Autowired
    PmsBrandService pmsBrandService;
    @Autowired
    PmsCategoryService pmsCategoryService;
    @Autowired
    PmsSkuSaleAttrValueService pmsSkuSaleAttrValueService;
    @Autowired
    PmsAttrService pmsAttrService;
    @Autowired
    WmsWareFeignService wmsWareFeignService;
    @Autowired
    SearchFeignService searchFeignService;
    @Override
    @Transactional
    public void up(Long spuId) {
        List<PmsSkuInfoEntity> pmsSkuInfoEntities = pmsSkuInfoService.selectBySpuId(spuId);

        //找到spuId对应可检索的所有属性
        List<PmsProductAttrValueEntity> pmsProductAttrValueEntities = pmsProductAttrValueService.baselistForSpu(spuId);
        List<Long> attrIds = pmsProductAttrValueEntities.stream().map(PmsProductAttrValueEntity::getAttrId).collect(Collectors.toList());
        //找到所有属性中标记为可检索的attrId
        List<Long> attrSearchIds = pmsAttrService.attrSearchIds(attrIds);
        Set<Long> attrSearchIdSet = new HashSet<>(attrSearchIds);
        List<SkuEsModel.Attr> attrs = pmsProductAttrValueEntities.stream().filter(pmsProductAttrValueEntity -> attrSearchIdSet.contains(pmsProductAttrValueEntity.getAttrId()))
                .map(pmsProductAttrValueEntity -> {
                    SkuEsModel.Attr attr = new SkuEsModel.Attr();
                    BeanUtils.copyProperties(pmsProductAttrValueEntity, attr);
                    return attr;
                }).collect(Collectors.toList());

        List<Long> skuIds = pmsSkuInfoEntities.stream().map(PmsSkuInfoEntity::getSkuId).collect(Collectors.toList());
        Map<Long, Boolean> hasStockMap = null;
        try {
            R<List<HasStockTo>> r = wmsWareFeignService.hasStock(skuIds);
            List<HasStockTo> data = r.getData();
            hasStockMap = data.stream().collect(Collectors.toMap(HasStockTo::getSkuId, HasStockTo::isHasStock));
        } catch (Exception e) {
            log.error("远程服务调用失败：wmsWareFeignService.hasStock(skuIds)"+e);
        }

        Map<Long, Boolean> finalHasStockMap = hasStockMap;
        List<SkuEsModel> collect = pmsSkuInfoEntities.stream()
                .map(pmsSkuInfoEntity -> {
                    SkuEsModel skuEsModel = new SkuEsModel();
                    BeanUtils.copyProperties(pmsSkuInfoEntity,skuEsModel);
                    skuEsModel.setSkuPrice(pmsSkuInfoEntity.getPrice());
                    skuEsModel.setSkuImage(pmsSkuInfoEntity.getSkuDefaultImg());
                    //发送远程调用，查询是否有库存
                    if(pmsSkuInfoEntity.getSkuId()==null){
                        skuEsModel.setHasStock(true);
                    }else{
                        skuEsModel.setHasStock(finalHasStockMap.get(pmsSkuInfoEntity.getSkuId()));
                    }

                    //TODO 热度评分是个复杂操作
                    skuEsModel.setHotScore(0L);
                    PmsBrandEntity pmsBrandEntity = pmsBrandService.getById(pmsSkuInfoEntity.getBrandId());
                    skuEsModel.setBrandName(pmsBrandEntity.getName());
                    skuEsModel.setBrandImg(pmsBrandEntity.getLogo());
                    skuEsModel.setCatalogName(pmsCategoryService.getById(pmsSkuInfoEntity.getCatalogId()).getName());
                    skuEsModel.setAttrs(attrs);

                    return skuEsModel;
                }).collect(Collectors.toList());
        //将数据放松给es进行保存
        R r = searchFeignService.productStatusUp(collect);
        if("0".equals(String.valueOf(r.get("code")))){
            log.error("插入成功");
            PmsSpuInfoEntity pmsSpuInfoEntity = new PmsSpuInfoEntity();
            pmsSpuInfoEntity.setPublishStatus(ProductConstant.spuStatus.SPU_UP.getStatus());
            this.update(pmsSpuInfoEntity,new UpdateWrapper<PmsSpuInfoEntity>().eq("id",spuId));
        }else{
            //远程调用失败
            //TODO 重复调用？接口幂等性问题，重试机制
            log.error("插入失败");
        }
    }
}