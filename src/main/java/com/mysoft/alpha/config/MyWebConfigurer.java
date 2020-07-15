package com.mysoft.alpha.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.*;

import com.mysoft.alpha.interceptor.LoginInterceptor;

@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {
	
//    @Bean
//    public LoginInterceptor getLoginIntercepter() {
//        return new LoginInterceptor();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(getLoginIntercepter())
//        .addPathPatterns("/**")
//        .excludePathPatterns("/index.html")
//        .excludePathPatterns("/api/login")
//        .excludePathPatterns("/api/logout");
//    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //所有请求都允许跨域，使用这种配置方法就不能在 interceptor 中再配置 header 了
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "D:/upload/file");
    }

}
