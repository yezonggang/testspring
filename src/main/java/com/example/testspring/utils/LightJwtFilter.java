package com.example.testspring.utils;

import com.example.testspring.model.userModel.LightUserEntity;
import com.example.testspring.req.LightErrorCode;
import com.example.testspring.req.LightUapException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

public class LightJwtFilter  implements Filter {

    private String[] excludes;

    static final String TOKEN_PREFIX = "Bearer";

    public static final String HEADER_STRING = "Authorization";

    /**
     * 登陆的拦截器
     */
    /**
     * 初始化函数，获取需要排除在外的url
     */

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        String exclude = fConfig.getInitParameter("excludes");
        if (StringUtils.isNotEmpty(exclude)) {
            excludes = exclude.split(",");
        }
    }

    /*
     * 获取当前http请求中的token 解析token : 1、token是否存在 2、token格式是否正确 3、token是否已过期
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // 如果类型是OPTIONS放过
        String method = ((HttpServletRequest) request).getMethod();
        if (!HttpMethod.OPTIONS.matches(method)) {

            String path = httpServletRequest.getServletPath();
            if (!PathMatcherUtil.matches(Arrays.asList(excludes), path)) {
                // 没有匹配到表示不在URL白名单之中，需要校验Token
                String authorization = getAuthorizationToken(httpServletRequest);
                if (StringUtils.isEmpty(authorization)) {
                    throw new LightUapException(LightErrorCode.FORBIDDEN.getCode(),
                            "验签失败，请求路径为：" + httpServletRequest.getRequestURI());
                }
                boolean success = LightTokenUtil.verify(authorization);
                if (!success) {
                    // token校验失败
                    throw new LightUapException(LightErrorCode.INTERNAL_ERROR);
                }
                LightUserEntity subject = LightTokenUtil.getSubject(authorization);
                // UserUtil.setUserEntity(subject);
            }
        }

        // 验证成功
        filterChain.doFilter(request, response);

    }

    private String getAuthorizationToken(HttpServletRequest request) {
        // 从header中获取token
        String token = request.getHeader(HEADER_STRING);

        if (StringUtils.isNotEmpty(token)) {
            return token;
        }
        // 从 参数中获取 token
        token = request.getParameter(HEADER_STRING);

        if (StringUtils.isNotEmpty(token)) {
            return token;
        }

        // 从 cookie 中获取 token 此方法未验证

        // Cookie[] cookies = request.getCookies();
        // if (cookies != null && cookies.length > 0) {
        // List<Cookie> cookieList = Arrays.asList(cookies);
        // Cookie cookie = cookieList.stream().filter(c ->
        // StringUtils.equals(HEADER_STRING,
        // c.getValue())).findFirst().get();
        // token = cookie.getValue();
        // }

        return token;
    }

}
