package com.ssy.api.exception;


import com.ssy.api.result.ResultCode;

/**
 * @Author: D丶Cheng
 * @Description: 必填参数为空时抛出
 */
public class IncompleteParamException extends ApiException {

    public IncompleteParamException() {
        super(ResultCode.NULL_PARAM.getMsg(),ResultCode.NULL_PARAM.getCode());
    }


}
