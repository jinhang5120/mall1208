package com.jh.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jh.common.utils.PageUtils;
import com.jh.mall.product.entity.PmsCommentReplayEntity;

import java.util.Map;

/**
 * 
 *
 * @author jh
 * @email ***@gmail.com
 * @date 2020-12-09 10:51:53
 */
public interface PmsCommentReplayService extends IService<PmsCommentReplayEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

