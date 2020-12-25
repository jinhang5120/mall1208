package com.jh.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.common.utils.PageUtils;
import com.jh.common.utils.Query;
import com.jh.mall.product.dao.PmsSpuInfoDao;
import com.jh.mall.product.entity.PmsSpuInfoEntity;
import com.jh.mall.product.service.PmsSpuInfoService;
import com.jh.mall.product.vo.SpuSaveVo;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("pmsSpuInfoService")
public class PmsSpuInfoServiceImpl extends ServiceImpl<PmsSpuInfoDao, PmsSpuInfoEntity> implements PmsSpuInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PmsSpuInfoEntity> page = this.page(
                new Query<PmsSpuInfoEntity>().getPage(params),
                new QueryWrapper<PmsSpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSpuInfo(SpuSaveVo spuSaveVo) {
        //1.保存spu基本信息 pms_spu_info

        //2.保存spu描述信息 pms_spu_info_desc

        //3.保存spu的图片集 pms_spu_images

        //4.保存spu的规格参数 pms_product_attr_value

        //5.保存spu的积分信息 sms_spu_bounds

        //6.保存spu对应的sku信息
        //6.1 sku的基本信息 pms_sku_info
        //6.2 sku的图片信息 pms_sku_images
        //6.3 sku的销售属性信息 pms_sku_sale_attr_value
        //6.4 sku的优惠、满减信息 sms_sku_ladder、sms_sku_full_reduction、sms_member_price
    }

}