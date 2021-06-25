package com.ssy.api.filter;


import com.ssy.api.util.RequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

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
    boolean isMultipart = false;
    isMultipart = ServletFileUpload.isMultipartContent((HttpServletRequest)request);
   //face查询接口 解决文件流关闭问题
    HttpServletRequest httpServletRequest=(HttpServletRequest) request;
    String servletPath = httpServletRequest.getServletPath();
    if (isMultipart||servletPath.equals("/face/search")){
      chain.doFilter(request, response);
    } else {
      ServletRequest requestWrapper = new RequestWrapper((HttpServletRequest) request);
      // 获取请求中的流，将取出来的字符串，再次转换成流，然后把它放入到新request对象中。
      // 在chain.doFiler方法中传递新的request对象
      if (requestWrapper == null) {
        chain.doFilter(request, response);
      } else {
        chain.doFilter(requestWrapper, response);
      }
    }
  }

  @Override
  public void destroy() {
    log.info("StreamFilter销毁...");
  }
}
