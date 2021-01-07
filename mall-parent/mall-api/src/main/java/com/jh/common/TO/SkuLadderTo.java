package com.jh.common.TO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SkuLadderTo {
    private Long skuId;
    private int fullCount;
    private BigDecimal discount;
    private Integer countStatus;
}
