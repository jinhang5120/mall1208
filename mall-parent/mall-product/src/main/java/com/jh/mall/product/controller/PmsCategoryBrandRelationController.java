package com.jh.mall.product.controller;

import com.jh.common.utils.PageUtils;
import com.jh.common.utils.R;
import com.jh.mall.product.entity.PmsCategoryBrandRelationEntity;
import com.jh.mall.product.service.PmsCategoryBrandRelationService;
import com.jh.mall.product.vo.BrandRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;



/**
 * Ʒ
 *
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 11:42:32
 */
@RestController
@RequestMapping("product/categorybrandrelation")
public class PmsCategoryBrandRelationController {
    @Autowired
    private PmsCategoryBrandRelationService pmsCategoryBrandRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:pmscategorybrandrelation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = pmsCategoryBrandRelationService.queryPage(params);

        return R.ok().put("page", page);
    }
    @RequestMapping("/brands/list")
    //@RequiresPermissions("product:pmscategorybrandrelation:list")
    public R brandsList(@RequestParam(value = "catId",required = true) Long catId){
        List<BrandRespVo> data = pmsCategoryBrandRelationService.brandsList(catId);
        return R.ok().put("data", data);
    }
    @GetMapping("/catelog/list")
    //@RequiresPermissions("product:pmscategorybrandrelation:list")
    public R list(@RequestParam("brandId") Long brandId ){
//        PageUtils page = pmsCategoryBrandRelationService.queryPage(params);
        List<PmsCategoryBrandRelationEntity> data = pmsCategoryBrandRelationService.selectListByBrandId(brandId);
        return R.ok().put("data", data);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:pmscategorybrandrelation:info")
    public R info(@PathVariable("id") Long id){
		PmsCategoryBrandRelationEntity pmsCategoryBrandRelation = pmsCategoryBrandRelationService.getById(id);

        return R.ok().put("pmsCategoryBrandRelation", pmsCategoryBrandRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:pmscategorybrandrelation:save")
    public R save(@RequestBody PmsCategoryBrandRelationEntity pmsCategoryBrandRelation){
//		pmsCategoryBrandRelationService.save(pmsCategoryBrandRelation);
		pmsCategoryBrandRelationService.saveDetail(pmsCategoryBrandRelation);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:pmscategorybrandrelation:update")
    public R update(@RequestBody PmsCategoryBrandRelationEntity pmsCategoryBrandRelation){
		pmsCategoryBrandRelationService.updateById(pmsCategoryBrandRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:pmscategorybrandrelation:delete")
    public R delete(@RequestBody Long[] ids){
		pmsCategoryBrandRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
