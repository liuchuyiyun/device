package com.base.intercepter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Jxx on 2016/7/19.
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new MyInterceptorLoginLog()).addPathPatterns("/login");
        registry.addInterceptor(new MyIntercepterLoginTest()).addPathPatterns("/ycwlw/**");
       //registry.addInterceptor(new MyIntercepter2()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
//
//    @Bean
//    public RemoteIpFilter remoteIpFilter() {
//        return new RemoteIpFilter();
//    }
//
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        return new LocaleChangeInterceptor();
//    }
////    @Override
////    public void addInterceptors(InterceptorRegistry registry {
////        registry.addInterceptor(localeChangeInterceptor());
////    }

}