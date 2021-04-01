package com.ssy.api.handler;


import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @program: client_api
 * @description: 全局返回定义
 * @author: wl
 * @created: 2020/07/08 16:03
 */

/**
 * 全局返回与swagger 有一定冲突  swagger无法识别全局返回中内容
 */

/**
 * 解决方案 只需要使其扫描指定包 即可 如下
 * @ControllerAdvice(basePackages = "com.ssy.api.controller")
 */

@ControllerAdvice(basePackages = "com.ssy.api.contrller")
@RestControllerAdvice(annotations = RestController.class)
public class GlobalResponseHandler implements ResponseBodyAdvice {

  /** 此处如果返回false , 则不执行当前Advice的业务 */
  @Override
  public boolean supports(MethodParameter returnType, Class converterType) {
    return true;
  }

  /**
   * /** 处理响应的具体方法
   *
   * @param body
   * @param returnType
   * @param selectedContentType
   * @param selectedConverterType
   * @param request
   * @param response
   * @return
   */
  @Override
  public Object beforeBodyWrite(
      Object body,
      MethodParameter returnType,
      MediaType selectedContentType,
      Class selectedConverterType,
      ServerHttpRequest request,
      ServerHttpResponse response) {
    if (body instanceof RestResult) {
      return body;
    } else {
      return new RestResultBuilder<>().success(body);
    }
  }
}
