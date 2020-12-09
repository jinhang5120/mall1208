package com.jh.mall.product.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jh.mall.product.entity.PmsSkuSaleAttrValueEntity;
import com.jh.mall.product.service.PmsSkuSaleAttrValueService;
import com.jh.common.utils.PageUtils;
import com.jh.common.utils.R;



/**
 * sku
 *
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 11:42:32
 */
@RestController
@RequestMapping("product/pmsskusaleattrvalue")
public class PmsSkuSaleAttrValueController {
    @Autowired
    private PmsSkuSaleAttrValueService pmsSkuSaleAttrValueService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:pmsskusaleattrvalue:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = pmsSkuSaleAttrValueService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:pmsskusaleattrvalue:info")
    public R info(@PathVariable("id") Long id){
		PmsSkuSaleAttrValueEntity pmsSkuSaleAttrValue = pmsSkuSaleAttrValueService.getById(id);

        return R.ok().put("pmsSkuSaleAttrValue", pmsSkuSaleAttrValue);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:pmsskusaleattrvalue:save")
    public R save(@RequestBody PmsSkuSaleAttrValueEntity pmsSkuSaleAttrValue){
		pmsSkuSaleAttrValueService.save(pmsSkuSaleAttrValue);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:pmsskusaleattrvalue:update")
    public R update(@RequestBody PmsSkuSaleAttrValueEntity pmsSkuSaleAttrValue){
		pmsSkuSaleAttrValueService.updateById(pmsSkuSaleAttrValue);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:pmsskusaleattrvalue:delete")
    public R delete(@RequestBody Long[] ids){
		pmsSkuSaleAttrValueService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
