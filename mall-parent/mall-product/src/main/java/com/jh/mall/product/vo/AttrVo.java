package com.jh.mall.product.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AttrVo implements Serializable {//可以直接继承
    private static final long serialVersionUID = 1L;

    private Long attrId;
    private String attrName;
    private Integer searchType;
    private String icon;
    private String valueSelect;
    private Integer attrType;
    private Long enable;
    private Long catelogId;
    private Integer showDesc;
    private Integer valueType;
    //额外增添的属性，用于接收前端的数据
    private Long attrGroupId;
}
