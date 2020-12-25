package com.jh.mall.product.vo;

import com.jh.mall.product.entity.PmsAttrEntity;
import lombok.Data;

import java.util.List;

@Data
public class AttrGroupWithAttrsVo {
    private Long attrGroupId;
    private String attrGroupName;
    private Integer sort;
    private String descript;
    private String icon;
    private Long catelogId;
    private List<PmsAttrEntity> attrs;
}
