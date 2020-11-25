package com.example.testspring.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.testspring.model.userModel.LightUserEntity;
import com.example.testspring.req.LightException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Enumeration;

public class TokenInterceptor extends HandlerInterceptorAdapter {

    /**
     * 根据请求不同对token进行处理
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  throws Exception {
        // 预检请求，预检飞行
        if(CorsUtils.isCorsRequest(request) && "OPTIONS".equals(request.getMethod())){
            response.setCharacterEncoding( "UTF-8");
            response.setContentType( "application/json; charset=utf-8");
            response.setStatus(200);
            response.setHeader("Access-Control-Allow-Credentials","true");
            response.setHeader("Access-Control-Allow-Origin","http://localhost:9527");
            response.setHeader("Access-Control-Allow-Headers","x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN,token,username,client");
            response.setHeader("Access-Control-Expose-Headers","x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN,token,username,client");
            PrintWriter out = null ;
            try{
                JSONObject res = new JSONObject();
                res.put( "200", "sucess");
                out = response.getWriter();
                out.append(res.toString());
                return false;
            }
            catch (Exception e){
                e.printStackTrace();
                response.sendError( 500);
                return false;
            }
        }
        String accessToken = request.getHeader("Authorization");
        //System.out.println(request.getHeader("Authorization"));
        //String str=request.getParameter("Authorization");
        if (StringUtils.isNotBlank(accessToken)) {
            LightUserEntity subject = LightTokenUtil.getSubject(accessToken);
            request.getSession().setAttribute("USER_INFO", subject);
            return true;
        }
        throw new LightException("TOKEN不合法，访问拒绝");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
