
package com.artforyou.store.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AdminAuthInterceptor adminAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    	registry.addInterceptor(adminAuthInterceptor)
        .addPathPatterns(
            "/api/orders",
            "/api/orders/*/status",
            "/api/orders/*/tracking",
            "/api/orders/*/payment-proof",
            "/api/orders/*/payment-status"
        );
    }
}
