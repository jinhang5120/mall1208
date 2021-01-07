package com.jh.common.TO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BoundsTo {
    private Long SpuId;
    private BigDecimal buyBounds;
    private BigDecimal growBounds;
}
