package com.jh.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 10:51:53
 */
@Data
@TableName("pms_attr_group")
public class PmsAttrGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long attrGroupId;
	/**
	 * 
	 */
	private String attrGroupName;
	/**
	 * 
	 */
	private Integer sort;
	/**
	 * 
	 */
	private String descript;
	/**
	 * 
	 */
	private String icon;
	/**
	 * 
	 */
	private Long catelogId;

}
