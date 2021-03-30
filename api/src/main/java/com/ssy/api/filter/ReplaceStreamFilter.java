package com.ssy.api.filter;


import com.ssy.api.util.RequestWrapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @program: client_api
 * @description: 处理前端 request参数问题 不然出现 stream close 流关闭
 * @author: wl
 * @created: 2020/08/13 11:19
 */
@Slf4j
public class ReplaceStreamFilter implements Filter {
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    log.info("StreamFilter初始化...");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    ServletRequest requestWrapper = new RequestWrapper((HttpServletRequest) request);
    chain.doFilter(requestWrapper, response);
  }

  @Override
  public void destroy() {
    log.info("StreamFilter销毁...");
  }
}
