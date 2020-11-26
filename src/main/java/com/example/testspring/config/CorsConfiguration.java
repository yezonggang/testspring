package com.example.testspring.config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Slf4j
// @Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("===CORS已注入===");
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:9527")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT","OPTION","OPTIONS")
                .allowedHeaders("x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN,token,username,client")
                .exposedHeaders("x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN,token,username,client")
                .maxAge(18000);

    }
}
