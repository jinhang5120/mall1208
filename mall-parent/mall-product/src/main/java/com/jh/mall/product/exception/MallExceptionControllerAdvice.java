package com.jh.mall.product.exception;

import com.jh.common.exception.BizCodeEnum;
import com.jh.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "com.jh.mall.product.controller")
@Slf4j
public class MallExceptionControllerAdvice {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e){
      log.error("数据检验异常:");
      log.error(e.getMessage());
      log.error(e.getClass().toString());
        Map map = new HashMap<String,String>();
        e.getBindingResult().getFieldErrors().forEach((item)->{
            map.put(item.getField(),item.getDefaultMessage());
        });
      return R.error(BizCodeEnum.VALID_EXCEPTION.getCode(),BizCodeEnum.VALID_EXCEPTION.getMessage()).put("data",map);
    }
    //统一处理所有异常
    @ExceptionHandler(value = Exception.class)
    public R handleException(Throwable throwable){
        System.out.println("+++++++++++throwable.getMessage() = " + throwable.getMessage());
        return R.error(BizCodeEnum.UNKNOWN_EXCEPTION.getCode(),BizCodeEnum.UNKNOWN_EXCEPTION.getMessage());
    }
}