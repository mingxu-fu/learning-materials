package com.yingxue.lesson.config;

import com.yingxue.lesson.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: FilterConfig
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Configuration
public class FilterConfig {

    @Bean
    public MyFilter myFilter(){
        return new MyFilter();
    }

    @Bean
    public FilterRegistrationBean getFilterRegistrationBean(MyFilter myFilter){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(myFilter);
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/api/*");
        filterRegistrationBean.setName("myFilter");
        return filterRegistrationBean;
    }
}
