package com.example.testspring.config;

import com.example.testspring.utils.TokenInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
@ConditionalOnProperty(havingValue = "true", prefix = "light", name = "tokenInterceptor")
public class TokenInterceptorConfiguration implements WebMvcConfigurer {

    @Bean
    public TokenInterceptor tokenInterceptor() {
        log.info("===TokenInterceptor已注入===");
        return new TokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("===TokenInterceptor已装载===");
        registry.addInterceptor(tokenInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/login**")
                .excludePathPatterns("/swagger**/**");
    }
}
