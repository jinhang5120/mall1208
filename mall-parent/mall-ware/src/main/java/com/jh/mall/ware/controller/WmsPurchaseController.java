package com.jh.mall.ware.controller;

import com.jh.common.utils.PageUtils;
import com.jh.common.utils.R;
import com.jh.mall.ware.entity.WmsPurchaseEntity;
import com.jh.mall.ware.service.WmsPurchaseService;
import com.jh.mall.ware.vo.MergeVo;
import com.jh.mall.ware.vo.PurchaseDoneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;



/**
 * 采购信息
 *
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 15:15:05
 */
@RestController
@RequestMapping("ware/purchase")
public class WmsPurchaseController {
    @Autowired
    private WmsPurchaseService wmsPurchaseService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("ware:wmspurchase:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wmsPurchaseService.queryPage(params);

        return R.ok().put("page", page);
    }
    @RequestMapping("/unreceive/list")
    //@RequiresPermissions("ware:wmspurchase:list")
    public R unreceiveList(@RequestParam Map<String, Object> params){
        PageUtils page = wmsPurchaseService.queryPageUnreceive(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("ware:wmspurchase:info")
    public R info(@PathVariable("id") Long id){
		WmsPurchaseEntity wmsPurchase = wmsPurchaseService.getById(id);

        return R.ok().put("wmsPurchase", wmsPurchase);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ware:wmspurchase:save")
    public R save(@RequestBody WmsPurchaseEntity wmsPurchase){
        wmsPurchase.setCreateTime(new Date());
        wmsPurchase.setUpdateTime(new Date());
		wmsPurchaseService.save(wmsPurchase);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ware:wmspurchase:update")
    public R update(@RequestBody WmsPurchaseEntity wmsPurchase){
        wmsPurchase.setUpdateTime(new Date());
		wmsPurchaseService.updateById(wmsPurchase);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ware:wmspurchase:delete")
    public R delete(@RequestBody Long[] ids){
		wmsPurchaseService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
    @RequestMapping("/merge")
    //@RequiresPermissions("ware:wmspurchase:delete")
    public R merge(@RequestBody MergeVo mergeVo){
		wmsPurchaseService.merge(mergeVo);

        return R.ok();
    }
    @RequestMapping("/receive")
    //@RequiresPermissions("ware:wmspurchase:delete")
    public R receive(@RequestBody Long[] purchaseIds){
		wmsPurchaseService.receive(purchaseIds);

        return R.ok();
    }
    @RequestMapping("/done")
    //@RequiresPermissions("ware:wmspurchase:delete")
    public R done(@RequestBody PurchaseDoneVo purchaseDoneVo){
		wmsPurchaseService.done(purchaseDoneVo);
        return R.ok();
    }

}
