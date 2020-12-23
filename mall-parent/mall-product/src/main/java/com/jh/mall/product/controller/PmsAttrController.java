package com.jh.mall.product.controller;

import com.jh.common.utils.PageUtils;
import com.jh.common.utils.R;
import com.jh.mall.product.entity.PmsAttrEntity;
import com.jh.mall.product.service.PmsAttrService;
import com.jh.mall.product.vo.AttrRespVo;
import com.jh.mall.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
@RequestMapping("product/attr")
public class PmsAttrController {
    @Autowired
    private PmsAttrService pmsAttrService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:pmsattr:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = pmsAttrService.queryPage(params);

        return R.ok().put("page", page);
    }
    @RequestMapping("/base/list/{catId}")//restful传参不能用${}
    //@RequiresPermissions("product:pmsattr:list")
    public R baseList(@RequestParam Map<String, Object> params,@PathVariable("catId") Long catId){
        PageUtils page = pmsAttrService.queryPage(params,catId);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    //@RequiresPermissions("product:pmsattr:info")
    public R info(@PathVariable("attrId") Long attrId){
//		PmsAttrEntity pmsAttr = pmsAttrService.getById(attrId);
		AttrRespVo attrRespVo = pmsAttrService.getInfoById(attrId);

        return R.ok().put("attr", attrRespVo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:pmsattr:save")
    public R save(@RequestBody AttrVo attrVo){
//		pmsAttrService.save(pmsAttr);
        pmsAttrService.saveAttrVo(attrVo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:pmsattr:update")
    public R update(@RequestBody PmsAttrEntity pmsAttr){
		pmsAttrService.updateById(pmsAttr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:pmsattr:delete")
    public R delete(@RequestBody Long[] attrIds){
		pmsAttrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
