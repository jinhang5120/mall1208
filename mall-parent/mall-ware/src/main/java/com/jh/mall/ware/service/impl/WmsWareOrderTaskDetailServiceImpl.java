package com.jh.mall.ware.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.common.utils.PageUtils;
import com.jh.common.utils.Query;

import com.jh.mall.ware.dao.WmsWareOrderTaskDetailDao;
import com.jh.mall.ware.entity.WmsWareOrderTaskDetailEntity;
import com.jh.mall.ware.service.WmsWareOrderTaskDetailService;


@Service("wmsWareOrderTaskDetailService")
public class WmsWareOrderTaskDetailServiceImpl extends ServiceImpl<WmsWareOrderTaskDetailDao, WmsWareOrderTaskDetailEntity> implements WmsWareOrderTaskDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WmsWareOrderTaskDetailEntity> page = this.page(
                new Query<WmsWareOrderTaskDetailEntity>().getPage(params),
                new QueryWrapper<WmsWareOrderTaskDetailEntity>()
        );

        return new PageUtils(page);
    }

}