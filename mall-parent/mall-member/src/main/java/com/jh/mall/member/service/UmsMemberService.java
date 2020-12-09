package com.jh.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jh.common.utils.PageUtils;
import com.jh.mall.member.entity.UmsMemberEntity;

import java.util.Map;

/**
 * 
 *
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 15:06:47
 */
public interface UmsMemberService extends IService<UmsMemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

