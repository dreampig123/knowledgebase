package org.pegcode.common.configs;

import org.pegcode.common.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * addPathPatterns：表示需要拦截的 URL，“**”表示拦截任意⽅法（也就是所有⽅法）。
     * excludePathPatterns：表示需要排除的 URL。
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**") // 拦截所有请求
                .excludePathPatterns("/user/login") // 排除不拦截的 url
//                .excludePathPatterns("/**/*.html")
//                .excludePathPatterns("/**/*.js")
//                .excludePathPatterns("/**/*.css")
                .excludePathPatterns("/user/reg"); // 排除不拦截的 url
    }
}
