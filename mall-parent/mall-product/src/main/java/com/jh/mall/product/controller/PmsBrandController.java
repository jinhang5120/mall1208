package com.jh.mall.product.controller;

import com.jh.common.utils.PageUtils;
import com.jh.common.utils.R;
import com.jh.common.valid.AddGroup;
import com.jh.common.valid.UpdateGroup;
import com.jh.mall.product.entity.PmsBrandEntity;
import com.jh.mall.product.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
@RequestMapping("product/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService pmsBrandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:pmsbrand:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = pmsBrandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    //@RequiresPermissions("product:pmsbrand:info")
    public R info(@PathVariable("brandId") Long brandId){
		PmsBrandEntity pmsBrand = pmsBrandService.getById(brandId);

        return R.ok().put("brand", pmsBrand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")//使用全局统一异常处理，自己有异常，就自动触发@RestControllerAdvice
    //@RequiresPermissions("product:pmsbrand:save")
    //@Valid是规范，@Validated是Spring提供的注解
    public R save(@Validated(value = AddGroup.class) @RequestBody PmsBrandEntity pmsBrand/*, BindingResult result*/){//开启校验
//        if(result.hasErrors()){
//            Map map = new HashMap<String,String>();
//            result.getFieldErrors().forEach((item)->{
//                map.put(item.getField(),item.getDefaultMessage());
//            });
//            return R.error(400,"数据不对").put("data",map);
//        }else{
            pmsBrandService.save(pmsBrand);
            return R.ok();
//        }
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:pmsbrand:update")
    public R update(@Validated(value = {UpdateGroup.class}) @RequestBody PmsBrandEntity pmsBrand){
//        pmsBrandService.updateById(pmsBrand);
        pmsBrandService.updateDetail(pmsBrand);//跟新品牌id也要更新关联的其他数据库中的数据

        return R.ok();
    }
    @RequestMapping("/update/status")
    //@RequiresPermissions("product:pmsbrand:update")
    public R updateStatus(@RequestBody PmsBrandEntity pmsBrand){
		pmsBrandService.updateById(pmsBrand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:pmsbrand:delete")
    public R delete(@RequestBody Long[] brandIds){
		pmsBrandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
