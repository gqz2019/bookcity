package com.gqz.bookcity.entity;

import com.gqz.bookcity.constant.StatusCode;

import java.io.Serializable;

/**
 * 描述
 *
 * @author gqz20
 * @version 1.0
 * @package entity *
 * @since 1.0
 */
public class Result<T> implements Serializable {
    private boolean flag;//是否成功
    private Integer code;//返回码
    private String message;//返回消息
    private T data;//返回数据

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = (T) data;
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result() {
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = "操作成功!";
    }

    public Result<T> success(String message,T data){
        return new Result<T>(true,StatusCode.OK,message,data);
    }

    public Result<T> success(String message) {
        return new Result<T>(true,StatusCode.OK,message,null);
    }

    public Result<T> failure(String message) {
        return new Result<T>(false,StatusCode.ERROR,message,null);
    }
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
