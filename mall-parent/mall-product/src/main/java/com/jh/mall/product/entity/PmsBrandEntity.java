package com.jh.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jh.common.valid.AddGroup;
import com.jh.common.valid.StatusValue;
import com.jh.common.valid.UpdateGroup;
import com.jh.common.valid.UpdateStatusGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.io.Serializable;

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
	@Null(message = "brandId必须为空",groups = {AddGroup.class})
	@NotNull(message = "brandId必须非空",groups = {UpdateGroup.class, UpdateStatusGroup.class})
	private Long brandId;
	/**
	 * Ʒ
	 */
	@NotBlank(message = "name not blank",groups = {AddGroup.class,UpdateGroup.class})
	private String name;
	/**
	 * Ʒ
	 */
	@URL(message = "必须是链接地址",groups = {AddGroup.class,UpdateGroup.class})
	@NotBlank(groups = {AddGroup.class})
	private String logo;
	/**
	 * 
	 */
	private String descript;
	/**
	 * 
	 */
	@NotNull(groups = UpdateStatusGroup.class)
	@StatusValue(message = "showstatus wrong",groups = {AddGroup.class,UpdateStatusGroup.class})
	private Integer showStatus;
	/**
	 * 
	 */
	@Pattern(regexp = "^[a-zA-Z]",message = "首字母不对")
	@NotNull(groups = {AddGroup.class})
	private String firstLetter;
	/**
	 * 
	 */
	@Min(value = 0,message = "序号必须大于0")
	@NotNull(groups = {AddGroup.class})
	private Integer sort;

}
