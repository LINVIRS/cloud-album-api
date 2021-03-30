package com.ssy.api.exception;

/**
 * 自定义异常
 */
public class ApiException extends Exception{

    private String msg = "error";
    private Integer code = 1;

    public ApiException(){}

    public ApiException(String msg) {
        this.msg = msg;
    }

    public ApiException(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
