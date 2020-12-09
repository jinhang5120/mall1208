package com.jh.mall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 14:51:31
 */
@Data
@TableName("sms_spu_bounds")
public class SmsSpuBoundsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long spuId;
	/**
	 * 
	 */
	private BigDecimal growBounds;
	/**
	 * 
	 */
	private BigDecimal buyBounds;
	/**
	 * 
	 */
	private Integer work;

}
