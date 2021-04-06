package com.ssy.api.result;

/** Created by D丶Cheng on 2017/5/23. */
public enum ResultCode {
  SYSTEM_BUSY(-1, "system too busy"),
  SUCCESS(0, "返回成功"),
  ERROR(1, "fail"),
  UNKNOWN_ERROR(100000, "unknown error"),

  ILLEGAL_ARGUMENT(100001, "illegal argument"),
  METHOD_NOT_ALLOW(100002, "method not allow"),
  MESSAGE_NOT_READABLE(100003, "message not readable"),
  UNSUPPORTED_MEDIA_TYPE(100004, "unsupported media type"),
  PAGE_NOT_FOUND(100005, "page not found"),

  TOKEN_EXPIRE(200001, "token expire"),
  ACCESS_DENIED(200002, "access denied"),
  ACCOUNT_EXIST(200003, "用户已经存在"),
  USER_BAN(200004, "您的账户已被封禁"),
  USER_NOT_FOUND(200005, "用户不存在"),
  USER_PASSWORD_NOT_MATCH(200006, "帐号或密码错误"),
  USER_FIRST_LOGIN(200007, "用户第一次登陆"),
  USER_HAS_BEEN_DELETE(200008, "用户账户已被注销"),
  LOGIN_ELSEWHERE(200009, "账户已在别处登录"),
  USER_HAS_DELETED(200010, "用户已被删除"),
  USER_REGISTER_SUCCESS(200011,"用户注册成功"),

  INVALID_PARAM(40002, "invalid param"),
  NULL_PARAM(40003, "null param"),
  NULL_POINT(40005, "Null Point Exception"),
  JSON_EXCEPTION(4006, "JSON转换异常"),
  INVALID_SMS_CODE(50003, "invalid sms code"),
  INVALID_OPERATING(50004, "invalid operating");

  private int code;
  private String msg;

  ResultCode(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public int getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }
}
