package com.jh.common.TO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SkuFullReductionTo {
    private Long skuId;

    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private Integer priceStatus;
}
