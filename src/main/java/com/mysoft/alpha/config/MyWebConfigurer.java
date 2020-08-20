package com.mysoft.alpha.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {

    /**
     *如下在付费开始前
     */
    public static final String STATUS1 = "已触发待申请";//客户-采购方
    public static final String STATUS2 = "重新触发待申请";//客户-采购方
    public static final String STATUS_3 = "申请未通过";//客户或采购方-服务方
    public static final String STATUS3 = "已申请待审核";//客户或采购方-服务方
    public static final String STATUS4 = "重新申请待审核";//客户或采购方-服务方
    public static final String STATUS_5 = "审核未通过";//服务方-客户或采购方
    public static final String STATUS5 = "审核通过可付费";//服务方-客户或采购方
    /**
     * 如下在付费开始后，服务开始前
     */
    public static final String STATUS6 = "付费完成待收款";//客户或采购方-服务方
    public static final String STATUS_7 = "未收款";//服务方-客户或采购方
    /**
     * 如下在服务开始后
     */
    public static final String STATUS7 = "确认收款服务中";//服务方-客户或采购方
    public static final String STATUS8 = "服务完成";//服务方-客户或采购方
    public static final String STATUS9 = "服务完成且评价";//服务方-客户或采购方


    @Autowired
    AlphaConfig alphaConfig;
    
    /**
     * 解决异常信息：
     *  java.lang.IllegalArgumentException:
     *      Invalid character found in the request target. The valid characters are defined in RFC 7230 and RFC 3986
     * @return
     */
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
            @Override
            public void customize(Connector connector) {
                connector.setProperty("relaxedQueryChars", "|{}[]");
            }
        });
        return factory;
    }

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
        registry.addMapping("/**").allowCredentials(true).allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE").allowedHeaders("*").maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + alphaConfig.getUploadFolder());
    }

}
