package site.metacoding.blogv2.config;

import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.config.filter.MyFilter1;
import site.metacoding.blogv2.config.filter.MyFilter2;
import site.metacoding.blogv2.config.filter.MyFilter3;

// Controller, RestController ,Repository, Service, Component, Configuration  == IOC컨테이너에 등록할수있는 어노테이션들

@RequiredArgsConstructor
// @Configuration
public class FilterConfig {

    private final HttpSession session;

    @Bean // IOC컨테이너에 필터 설정파일 등록
    public FilterRegistrationBean<?> filter1() {
        FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>(new MyFilter1()); // 객체에 필터를 등록해줌
        bean.addUrlPatterns("/*"); // 내 서버에 접근하는 모든 path를 잡아끔
        bean.setOrder(1); // 실행순서를 지정해줌 => 낮은 번호의 필터가 가장 먼저 실행됨.
        return bean; // 이 return되는 객체가 등록되는것.
    }

    @Bean
    public FilterRegistrationBean<?> filter2() {
        FilterRegistrationBean<MyFilter2> bean = new FilterRegistrationBean<>(new MyFilter2());
        bean.addUrlPatterns("/*");
        bean.setOrder(2);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<?> filter3() {
        FilterRegistrationBean<MyFilter3> bean = new FilterRegistrationBean<>(new MyFilter3(session));
        bean.addUrlPatterns("/s/*");
        bean.setOrder(3);
        return bean;
    }
}
