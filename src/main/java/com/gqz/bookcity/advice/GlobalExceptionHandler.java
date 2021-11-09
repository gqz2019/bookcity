package com.gqz.bookcity.advice;

import com.gqz.bookcity.constant.StatusCode;
import com.gqz.bookcity.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>全局异常处理</p>
 *
 * @author gqz20
 * @create 2021-10-25 18:44
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e) {
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage(),null);
    }
}
