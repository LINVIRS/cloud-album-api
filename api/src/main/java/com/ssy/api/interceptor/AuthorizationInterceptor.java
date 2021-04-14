package com.ssy.api.interceptor;

import com.ssy.api.annotation.Authorization;
import com.ssy.api.constant.ParameterConstant;
import com.ssy.api.exception.AccessDeniedException;
import com.ssy.api.redis.TokenRedisDao;
import com.ssy.api.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/** Created by D丶Cheng on 2017/5/25. */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

  @Autowired private TokenRedisDao tokenRedisDao;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    if (!(handler instanceof HandlerMethod)) {
      return true;
    }

    HandlerMethod handlerMethod = (HandlerMethod) handler;
    Method method = handlerMethod.getMethod();

    if (method.getAnnotation(Authorization.class) == null) {
      return true;
    }

    // 从header中得到token
    String authorization = request.getHeader(ParameterConstant.AUTHORIZATION);

    if (authorization == null) {
      throw new AccessDeniedException();
    }

    // 验证token真伪
    Map map = JWTUtils.getClaims(ParameterConstant.JWT_SECRET, authorization);
    if (map == null) {
      throw new AccessDeniedException();
    }

    // 从Redis读取token
    //        UserTokenVO userToken = new UserTokenVO().fromMap(map);
    //        if (!tokenRedisDao.checkToken(ParameterConstant.WX_TOKEN_PREFIX,userToken.getId(),
    // authorization)){
    //            throw new TokenExpireException();
    //        }

    return true;
  }

  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView)
      throws Exception {}

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {}

  @Override
  public void afterConcurrentHandlingStarted(
      HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {}
}
