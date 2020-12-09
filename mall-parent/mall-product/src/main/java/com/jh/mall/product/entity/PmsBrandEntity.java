package com.jh.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * Ʒ
 * 
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 10:51:53
 */
@Data
@TableName("pms_brand")
public class PmsBrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Ʒ
	 */
	@TableId
	private Long brandId;
	/**
	 * Ʒ
	 */
	private String name;
	/**
	 * Ʒ
	 */
	private String logo;
	/**
	 * 
	 */
	private String descript;
	/**
	 * 
	 */
	private Integer showStatus;
	/**
	 * 
	 */
	private String firstLetter;
	/**
	 * 
	 */
	private Integer sort;

}
