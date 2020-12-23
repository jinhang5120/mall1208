package com.jh.mall.product.controller;

import com.jh.common.utils.R;
import com.jh.mall.product.entity.PmsCategoryEntity;
import com.jh.mall.product.service.PmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

//import org.apache.shiro.authz.annotation.RequiresPermissions;



/**
 * 商品三级分类
 *
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 11:42:32
 */
@RestController
@RequestMapping("product/category")
public class PmsCategoryController {
    @Autowired
    private PmsCategoryService pmsCategoryService;

    /**
     * 查出所有分类以及子分类，以树形结构展示
     */
    @RequestMapping("/list/tree")
    //@RequiresPermissions("product:pmscategory:list")
    public R listTree(){//以树形结构返回所有数据
        List<PmsCategoryEntity> pmsCategoryEntities = pmsCategoryService.listWithTree();
        return R.ok().put("pmsCategoryEntities", pmsCategoryEntities);
    }
    @RequestMapping("/list")
    //@RequiresPermissions("product:pmscategory:list")
    public R list(){//以树形结构返回所有数据
        List<PmsCategoryEntity> pmsCategoryEntities = pmsCategoryService.list();
        return R.ok().put("pmsCategoryEntities", pmsCategoryEntities);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
    //@RequiresPermissions("product:pmscategory:info")
    public R info(@PathVariable("catId") Long catId){
		PmsCategoryEntity pmsCategory = pmsCategoryService.getById(catId);

        return R.ok().put("data", pmsCategory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:pmscategory:save")
    public R save(@RequestBody PmsCategoryEntity pmsCategory){
		pmsCategoryService.save(pmsCategory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:pmscategory:update")
    public R update(@RequestBody PmsCategoryEntity pmsCategory){
//		pmsCategoryService.updateById(pmsCategory);
		pmsCategoryService.updateDetail(pmsCategory);
        return R.ok();
    }
    @RequestMapping("/update/sort")
    //@RequiresPermissions("product:pmscategory:update")
    public R update(@RequestBody PmsCategoryEntity[] pmsCategory){
		pmsCategoryService.updateBatchById(Arrays.asList(pmsCategory));
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:pmscategory:delete")
    public R delete(@RequestBody Long[] catIds){//只有post请求有请求体，而且请求体数据必须是json数据
//		pmsCategoryService.removeByIds(Arrays.asList(catIds));
        //TODO 1.检查当前删除的菜单，是否被别的地方引用
        //默认是逻辑删除
        pmsCategoryService.removeMenuByIds(Arrays.asList(catIds));
        return R.ok();
    }
}
