package com.ssy.api.exception;


import com.ssy.api.result.ResultCode;

/**
 * Created by chenpan on 2017/5/23.
 */
public class TokenExpireException extends ApiException {


    public TokenExpireException() {
        super(ResultCode.TOKEN_EXPIRE.getMsg(), ResultCode.TOKEN_EXPIRE.getCode());
    }

    public TokenExpireException(String msg, Integer code) {
        super(msg, code);
    }
}
