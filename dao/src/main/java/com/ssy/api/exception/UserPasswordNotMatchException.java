package com.ssy.api.exception;


import com.ssy.api.result.ResultCode;

public class UserPasswordNotMatchException extends ApiException{

    public UserPasswordNotMatchException() {
        super( ResultCode.USER_PASSWORD_NOT_MATCH.getMsg(), ResultCode.USER_PASSWORD_NOT_MATCH.getCode());
    }

}
