package com.ssy.api.exception;


import com.ssy.api.result.ResultCode;

/**
 * @Company: NJBandou
 * @Author: Chen Pan
 * @Description:
 * @Date: 2018/7/5 下午2:22
 */
public class InvalidParamException extends ApiException {

    public InvalidParamException() {
        super(ResultCode.INVALID_PARAM.getMsg(), ResultCode.INVALID_PARAM.getCode());
    }
}
