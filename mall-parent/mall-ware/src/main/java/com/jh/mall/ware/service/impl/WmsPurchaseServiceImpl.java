package com.jh.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.common.constant.WareConstant;
import com.jh.common.utils.PageUtils;
import com.jh.common.utils.Query;
import com.jh.mall.ware.dao.WmsPurchaseDao;
import com.jh.mall.ware.entity.WmsPurchaseDetailEntity;
import com.jh.mall.ware.entity.WmsPurchaseEntity;
import com.jh.mall.ware.service.WmsPurchaseDetailService;
import com.jh.mall.ware.service.WmsPurchaseService;
import com.jh.mall.ware.service.WmsWareSkuService;
import com.jh.mall.ware.vo.MergeVo;
import com.jh.mall.ware.vo.PurchaseDoneVo;
import com.jh.mall.ware.vo.PurchaseItemVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service("wmsPurchaseService")
public class WmsPurchaseServiceImpl extends ServiceImpl<WmsPurchaseDao, WmsPurchaseEntity> implements WmsPurchaseService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WmsPurchaseEntity> queryWrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            queryWrapper.eq("id",key).or().like("assignee_id",key).or().like("ware_id",key);
        }
        IPage<WmsPurchaseEntity> page = this.page(
                new Query<WmsPurchaseEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageUnreceive(Map<String, Object> params) {
        QueryWrapper<WmsPurchaseEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",0).or().eq("status",1);
        IPage<WmsPurchaseEntity> page = this.page(
                new Query<WmsPurchaseEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Autowired
    WmsPurchaseDetailService wmsPurchaseDetailService;
    @Override
    @Transactional
    public void merge(MergeVo mergeVo) {
        Long purchaseId = mergeVo.getPurchaseId();
        if(purchaseId==null) {
            WmsPurchaseEntity wmsPurchaseEntity = new WmsPurchaseEntity();
            wmsPurchaseEntity.setCreateTime(new Date());
            wmsPurchaseEntity.setUpdateTime(new Date());
            wmsPurchaseEntity.setStatus(WareConstant.PurchaseStatus.CREATED.getStatusCode());
            this.save(wmsPurchaseEntity);
            purchaseId = wmsPurchaseEntity.getId();
        }
        Long finalPurchaseId = purchaseId;
        List<WmsPurchaseDetailEntity> wmsPurchaseDetailEntities = mergeVo.getItems().stream().map(id -> {
            WmsPurchaseDetailEntity wmsPurchaseDetailEntity = wmsPurchaseDetailService.getById(id);
            if(wmsPurchaseDetailEntity.getStatus().equals(WareConstant.PurchaseDetailStatus.CREATED.getStatusCode())){
                wmsPurchaseDetailEntity.setPurchaseId(finalPurchaseId);
                wmsPurchaseDetailEntity.setStatus(WareConstant.PurchaseDetailStatus.ASSIGNED.getStatusCode());
            }
            return wmsPurchaseDetailEntity;
        }).collect(Collectors.toList());
        wmsPurchaseDetailService.updateBatchById(wmsPurchaseDetailEntities);

        WmsPurchaseEntity wmsPurchaseEntity = new WmsPurchaseEntity();
        wmsPurchaseEntity.setId(purchaseId);
        wmsPurchaseEntity.setUpdateTime(new Date());
        this.updateById(wmsPurchaseEntity);
    }

    @Override
    @Transactional
    public void receive(Long[] purchaseIds) {
        List<WmsPurchaseEntity> wmsPurchaseEntities = this.baseMapper.selectBatchIds(Arrays.asList(purchaseIds));
        for (WmsPurchaseEntity wmsPurchaseEntity : wmsPurchaseEntities) {
            if(wmsPurchaseEntity.getStatus().equals(WareConstant.PurchaseStatus.CREATED.getStatusCode()) || wmsPurchaseEntity.getStatus().equals(WareConstant.PurchaseStatus.ASSIGNED.getStatusCode())){
                wmsPurchaseEntity.setStatus(WareConstant.PurchaseStatus.RECEIVED.getStatusCode());
                wmsPurchaseEntity.setUpdateTime(new Date());
                this.updateById(wmsPurchaseEntity);
                WmsPurchaseDetailEntity wmsPurchaseDetailEntity = new WmsPurchaseDetailEntity();
                wmsPurchaseDetailEntity.setStatus(WareConstant.PurchaseDetailStatus.PURCHASING.getStatusCode());
                wmsPurchaseDetailService.update(wmsPurchaseDetailEntity,new QueryWrapper<WmsPurchaseDetailEntity>().eq("purchase_id",wmsPurchaseEntity.getId()));
            }
        }
    }

    @Autowired
    WmsWareSkuService wmsWareSkuService;
    @Override
    @Transactional
    public void done(PurchaseDoneVo purchaseDoneVo) {
        List<PurchaseItemVo> purchaseItemVoList = purchaseDoneVo.getPurchaseItemVoList();
        List<WmsPurchaseDetailEntity> list = new ArrayList<>();
        boolean flag = false;
        for (PurchaseItemVo purchaseItemVo : purchaseItemVoList) {
            WmsPurchaseDetailEntity wmsPurchaseDetailEntity = wmsPurchaseDetailService.getById(purchaseItemVo.getItemId());
            wmsPurchaseDetailEntity.setStatus(purchaseItemVo.getStatus());
            list.add(wmsPurchaseDetailEntity);
            if(purchaseItemVo.getStatus().equals(WareConstant.PurchaseDetailStatus.PURCHASE_ERROR.getStatusCode())){
                flag = true;
            }else{
                //成功采购入库
                wmsWareSkuService.gotoStock(wmsPurchaseDetailEntity.getSkuId(),wmsPurchaseDetailEntity.getSkuNum(),wmsPurchaseDetailEntity.getWareId());
            }
        }
        //改变采购项状态
        wmsPurchaseDetailService.updateBatchById(list);

        WmsPurchaseEntity wmsPurchaseEntity = new WmsPurchaseEntity();
        wmsPurchaseEntity.setId(purchaseDoneVo.getId());
        if(flag){
            wmsPurchaseEntity.setStatus(WareConstant.PurchaseStatus.HAS_ERROR.getStatusCode());
        }else{
            wmsPurchaseEntity.setStatus(WareConstant.PurchaseStatus.FINISHED.getStatusCode());
        }
        wmsPurchaseEntity.setUpdateTime(new Date());
        //改变采购单状态
        this.updateById(wmsPurchaseEntity);


    }
}