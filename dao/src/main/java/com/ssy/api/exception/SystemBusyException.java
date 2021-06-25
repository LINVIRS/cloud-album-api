package com.ssy.api.exception;


import com.ssy.api.result.ResultCode;

/**
 * @Company: NJBandou
 * @Author: Chen Pan
 * @Description:
 * @Date: 2018/7/11 上午10:15
 */
public class SystemBusyException extends ApiException{

    public SystemBusyException() {
        super(ResultCode.SYSTEM_BUSY.getMsg(), ResultCode.SYSTEM_BUSY.getCode());
    }
}
