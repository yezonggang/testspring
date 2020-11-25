package com.example.testspring.config;

import com.example.testspring.utils.LightAuthProperties;
import com.example.testspring.utils.LightJwtFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = LightAuthProperties.PREFIX, name = "status", havingValue = "true")
public class LightJwtConfigurationss {

    @Autowired
    private LightAuthProperties lightAuthProperties;

    @Bean
    public FilterRegistrationBean jwtFilterRegistrationBean() {
        FilterRegistrationBean<LightJwtFilter> registration = new FilterRegistrationBean<>(new LightJwtFilter());

        // 配置访问权限
        String[] excludes = this.lightAuthProperties.getExcludes();
        if (excludes != null && excludes.length > 1) {
            String join = StringUtils.join(excludes, ",");
            registration.addInitParameter("excludes", join);
        }
        registration.addUrlPatterns("/*");
        registration.setName("jwtFilter");
        return registration;
    }

}
