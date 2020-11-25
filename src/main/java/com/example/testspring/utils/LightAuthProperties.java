package com.example.testspring.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = LightAuthProperties.PREFIX, ignoreInvalidFields = true)
@Getter
@Setter
@Component
public class LightAuthProperties {

    // status: on
    // lightAuthFilters: lightAuthShiroDefaultFilterImpl
    // userInfoService: lightDefaultUserInfoServiceImpl
    // multiAccountLogin: false
    // activeSessionsCacheName: lightActiveSessionCache
    public final static String PREFIX = "light.auth";

    private boolean status = Boolean.TRUE;

    private String[] excludes;

}
