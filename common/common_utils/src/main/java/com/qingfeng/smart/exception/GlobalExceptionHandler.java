package com.qingfeng.smart.exception;

import com.qingfeng.smart.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/2
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 指定全局对那个异常做处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail();
    }

    /**
     * 对自定义异常进行拦截处理
     * @param e
     * @return
     */
    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public Result error(GlobalException e){
        e.printStackTrace();
        return Result.fail();
    }
}
