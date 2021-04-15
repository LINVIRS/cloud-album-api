package com.ssy.api.exception;


import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.result.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;

/**
 * 异常处理器
 */
@RestControllerAdvice
@ResponseBody
public class ApiExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Token校验失败
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    private <T> RestResult<T> accessDeniedHandler(AccessDeniedException e) {
        logger.error("---------> User Ban!", e);
        return new RestResultBuilder<T>()
                .setCode(ResultCode.ACCESS_DENIED.getCode())
                .setMsg(ResultCode.ACCESS_DENIED.getMsg())
                .build();
    }

    /**
     * 业务错误
     */
    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.OK)
    private <T> RestResult<T> apiExceptionHandler(ApiException e) {
        logger.error("------->error !", e);
        return new RestResultBuilder<T>()
                .setCode(e.getCode())
                .setMsg(e.getMsg())
                .build();
    }

    /**
     * 运行时异常
     *
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    private <T> RestResult<T> runtimeExceptionHandler(ApiException e) {
        logger.error("------->error !", e);
        return new RestResultBuilder<T>()
                .setCode(e.getCode())
                .setMsg(e.getMsg())
                .build();
    }

    /**
     * Token过期
     */
    @ExceptionHandler(TokenExpireException.class)
    @ResponseStatus(HttpStatus.OK)
    private <T> RestResult<T> expireHandler(TokenExpireException e) {
        logger.error("---------> invalid expire!", e);
        return new RestResultBuilder<T>()
                .setCode(e.getCode())
                .setMsg(e.getMsg())
                .build();
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public <T> RestResult<T> handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.info("------->不支持当前媒体类型 !" + e);
        return new RestResultBuilder<T>()
                .setCode(ResultCode.UNSUPPORTED_MEDIA_TYPE.getCode())
                .setMsg(ResultCode.UNSUPPORTED_MEDIA_TYPE.getMsg())
                .build();
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public <T> RestResult<T> handleException(Exception e) {
        logger.info("------->未知错误 !", e.getMessage());
        String message = e.getMessage();
        if (message.contains(":")) {
            message = message.substring(0, message.lastIndexOf(":"));
        }
        return new RestResultBuilder<T>()
                .setCode(ResultCode.UNKNOWN_ERROR.getCode())
                .setMsg(message)
                .build();
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public <T> RestResult<T> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.info("------->参数解析失败 !", e);
        return new RestResultBuilder<T>()
                .setCode(ResultCode.MESSAGE_NOT_READABLE.getCode())
                .setMsg(ResultCode.MESSAGE_NOT_READABLE.getMsg())
                .build();
    }

    /**
     * 404 - Page not found
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public <T> RestResult<T> handlePageNotFoundException() {
        logger.info("------->链接不存在!");
        return new RestResultBuilder<T>()
                .setCode(ResultCode.PAGE_NOT_FOUND.getCode())
                .setMsg(ResultCode.PAGE_NOT_FOUND.getMsg())
                .build();
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public <T> RestResult<T> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.info("------->不支持当前请求方法 !", e);
        return new RestResultBuilder<T>()
                .setCode(ResultCode.METHOD_NOT_ALLOW.getCode())
                .setMsg(ResultCode.METHOD_NOT_ALLOW.getMsg())
                .build();
    }

    /**
     * 500 - 内部错误
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(IllegalArgumentException.class)
    public <T> RestResult<T> parameterException(IllegalArgumentException e) {
        logger.info("------->非法参数 !", e);
        return new RestResultBuilder<T>()
                .setCode(ResultCode.INVALID_PARAM.getCode())
                .setMsg(e.getMessage().isEmpty() ? ResultCode.INVALID_PARAM.getMsg() : e.getMessage())
                .build();
    }

    /**
     * spring绑定参数校验错误
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    private <T> RestResult<T> illegalParamsExceptionHandler(MethodArgumentNotValidException e) {
        logger.error("------->error !", e);
        return new RestResultBuilder<T>()
                .setCode(ResultCode.INVALID_PARAM.getCode())
                .setMsg(ResultCode.INVALID_PARAM.getMsg())
                .build();
    }

    /**
     * spring方法参数缺失错误
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.OK)
    private <T> RestResult<T> illegalParamsExceptionHandler(MissingServletRequestParameterException e) {
        logger.error("------->error !", e);
        return new RestResultBuilder<T>()
                .setCode(ResultCode.INVALID_PARAM.getCode())
                .setMsg(ResultCode.INVALID_PARAM.getMsg())
                .build();
    }


    /**
     * spring绑定参数校验错误
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.OK)
    private <T> RestResult<T> methodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {
        logger.error("---------> invalid request!", e);
        return new RestResultBuilder<T>()
                .setCode(ResultCode.INVALID_PARAM.getCode())
                .setMsg(ResultCode.INVALID_PARAM.getMsg())
                .build();
    }

    /**
     * Hibernate validate 参数校验错误
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    private <T> RestResult<T> bindExceptionHandler(BindException e) {
        logger.error("---------> invalid request!", e);
        return new RestResultBuilder<T>()
                .setCode(ResultCode.INVALID_PARAM.getCode())
                .setMsg(e.getFieldError().getDefaultMessage())
                .build();
    }

}
