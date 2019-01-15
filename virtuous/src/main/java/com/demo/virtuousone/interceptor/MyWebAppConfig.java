package com.demo.virtuousone.interceptor;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Auther: 吴宸煊
 * @Date: 2019/1/14
 * @Description: 拦截器
 */
public class MyWebAppConfig extends WebMvcConfigurationSupport {
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        // registry.addInterceptor(new SmokeApiInterceptor()).addPathPatterns("/**");
//        .excludePathPatterns("/userCtrl/*");

        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
