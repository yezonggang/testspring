package com.example.testspring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(filterName = "CORSFilter", urlPatterns = {"/*"})
//@Order(value = 1)
//@Configuration
public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)throws IOException, ServletException {
        System.out.println("Filter 过滤器 执行 了");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 响应标头指定 指定可以访问资源的URI路径
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:9527");
        //响应标头指定响应访问所述资源到时允许的一种或多种方法
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT,OPTION,OPTIONS");
        //设置 缓存可以生存的最大秒数
        response.setHeader("Access-Control-Max-Age", "36000");
        //设置  受支持请求标头
        response.setHeader("Access-Control-Allow-Headers", "*");
        // 指示的请求的响应是否可以暴露于该页面。当true值返回时它可以被暴露
        response.setHeader("Access-Control-Allow-Credentials","true");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
