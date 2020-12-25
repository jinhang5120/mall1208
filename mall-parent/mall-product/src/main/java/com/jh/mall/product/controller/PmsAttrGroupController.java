package com.jh.mall.product.controller;

import com.jh.common.utils.PageUtils;
import com.jh.common.utils.R;
import com.jh.mall.product.entity.PmsAttrGroupEntity;
import com.jh.mall.product.service.PmsAttrGroupService;
import com.jh.mall.product.service.PmsAttrService;
import com.jh.mall.product.vo.AttrGroupRelationVo;
import com.jh.mall.product.vo.AttrGroupWithAttrsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;



/**
 * 
 *
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 11:42:32
 */
@RestController
@RequestMapping("product/attrgroup")
public class PmsAttrGroupController {
    @Autowired
    private PmsAttrGroupService pmsAttrGroupService;

    /**
     * 列表
     */
    @RequestMapping("/{attrGroupId}/attr/relation")
    public R attrRelationSelect(@PathVariable("attrGroupId") Long attrGroupId){
        return R.ok().put("data",pmsAttrGroupService.attrRelation(attrGroupId));
    }
    @RequestMapping("/attr/relation/delete")
    public R attrRelationDelete(@RequestBody AttrGroupRelationVo[] attrGroupRelationVos){
        pmsAttrGroupService.deleteRelation(attrGroupRelationVos);
        return R.ok();
    }
    @Autowired
    PmsAttrService pmsAttrService;
    @RequestMapping("/{attrGroupId}/noattr/relation")
    //@RequiresPermissions("product:pmsattrgroup:list")
    public R noRelationAtrr(@RequestParam Map<String, Object> params,@PathVariable("attrGroupId") Long attrGroupId){
        PageUtils page = pmsAttrService.queryNoRelationAtrr(params,attrGroupId);
        return R.ok().put("page", page);
    }
    @RequestMapping("/attr/relation")
    public R saveAttrRelation(@RequestBody AttrGroupRelationVo[] attrGroupRelationVos){
        pmsAttrGroupService.saveAttrRelation(attrGroupRelationVos);
        return R.ok();
    }
    @RequestMapping("/{catelogId}/withattr")
    public R catelogIdWithAttr(@PathVariable("catelogId") Long catelogId){
        List<AttrGroupWithAttrsVo> data = pmsAttrGroupService.catelogIdWithAttr(catelogId);
        return R.ok().put("data",data);
    }


    @RequestMapping("/list")
    //@RequiresPermissions("product:pmsattrgroup:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = pmsAttrGroupService.queryPage(params);

        return R.ok().put("page", page);
    }
    @RequestMapping("/list/{categoryId}")
    //@RequiresPermissions("product:pmsattrgroup:list")
    public R list(@RequestParam Map<String, Object> params,@PathVariable("categoryId") Long categoryId){
//        PageUtils page = pmsAttrGroupService.queryPage(params);
        System.out.println("============================");
        PageUtils page = pmsAttrGroupService.queryPage(params,categoryId);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    //@RequiresPermissions("product:pmsattrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
		PmsAttrGroupEntity pmsAttrGroup = pmsAttrGroupService.getById(attrGroupId);

        return R.ok().put("attrGroup", pmsAttrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:pmsattrgroup:save")
    public R save(@RequestBody PmsAttrGroupEntity pmsAttrGroup){
		pmsAttrGroupService.save(pmsAttrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:pmsattrgroup:update")
    public R update(@RequestBody PmsAttrGroupEntity pmsAttrGroup){
		pmsAttrGroupService.updateById(pmsAttrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:pmsattrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		pmsAttrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
