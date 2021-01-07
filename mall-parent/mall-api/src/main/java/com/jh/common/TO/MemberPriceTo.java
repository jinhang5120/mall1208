package com.jh.common.TO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MemberPriceTo {
    private Long skuId;
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer priceStatus;
}
