package com.jh.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jh.common.utils.PageUtils;
import com.jh.common.utils.Query;
import com.jh.mall.product.dao.PmsSpuInfoDescDao;
import com.jh.mall.product.entity.PmsSpuInfoDescEntity;
import com.jh.mall.product.service.PmsSpuInfoDescService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("pmsSpuInfoDescService")
public class PmsSpuInfoDescServiceImpl extends ServiceImpl<PmsSpuInfoDescDao, PmsSpuInfoDescEntity> implements PmsSpuInfoDescService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PmsSpuInfoDescEntity> page = this.page(
                new Query<PmsSpuInfoDescEntity>().getPage(params),
                new QueryWrapper<PmsSpuInfoDescEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSpuInfoDescEntity(Long id, List<String> decript) {
        PmsSpuInfoDescEntity pmsSpuInfoDescEntity = new PmsSpuInfoDescEntity();
        pmsSpuInfoDescEntity.setSpuId(id);
        pmsSpuInfoDescEntity.setDecript(String.join(";", decript));
        this.save(pmsSpuInfoDescEntity);
    }
}