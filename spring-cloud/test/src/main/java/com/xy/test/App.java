package com.xy.test;

import com.xy.test.spring.boot.servlet.MySpringBootFilter;
import com.xy.test.spring.boot.servlet.MySpringBootServlet;
import com.xy.test.spring.boot.servlet.MySpringBootServletRequestListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import javax.servlet.DispatcherType;
import javax.servlet.Servlet;

/**
 * 启动类
 *
 * @author xy
 */
@SpringCloudApplication
@EnableDiscoveryClient
@ServletComponentScan(basePackages = {"com.xy.test.servlet"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public static ServletRegistrationBean<Servlet> servletRegistrationBean() {
        ServletRegistrationBean<javax.servlet.Servlet> servletRegistrationBean = new ServletRegistrationBean<>();
        servletRegistrationBean.setServlet(new MySpringBootServlet());
        servletRegistrationBean.addUrlMappings("/my/spring/boot/servlet");
        servletRegistrationBean.setName("mySpringBootServlet");
        return servletRegistrationBean;
    }

    @Bean
    public static FilterRegistrationBean<MySpringBootFilter> filterRegistrationBean() {
        FilterRegistrationBean<MySpringBootFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new MySpringBootFilter());
        filterRegistrationBean.addServletNames("mySpringBootServlet");
        filterRegistrationBean.setDispatcherTypes(DispatcherType.ASYNC, DispatcherType.FORWARD,
                DispatcherType.INCLUDE, DispatcherType.REQUEST);
        return filterRegistrationBean;
    }

    @Bean
    public static ServletListenerRegistrationBean servletListenerRegistrationBean() {
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new MySpringBootServletRequestListener());
        return servletListenerRegistrationBean;
    }
}
