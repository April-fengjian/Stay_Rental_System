package com.laioffer.staybooking.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
//这个filter放在filter链条的前面，不加的的话就不放进filter链条里
@Order(Ordered.HIGHEST_PRECEDENCE)
//OncePerRequestFilter：每个请求都要经过这个filter
public class CorsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //'*'表示前端所有的地址都支持
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");

        if ("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod())) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            //调用下一层的filter
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
//跨域访问请求发送之前，前端会先发一个options请求，后端会回复是否接受跨域访问，前端收到回复后再发送post/get请求

