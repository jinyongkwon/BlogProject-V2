package site.metacoding.blogv2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import site.metacoding.blogv2.config.intercepter.SessionIntercepter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionIntercepter())
                .addPathPatterns("/s/**"); // 전체포함이 *, ** 2중하나이니 확인해야한다
    }

    /*
     * // 참고용 => 더 등록할수도 있고 제외할수도있음
     * 
     * @Override
     * public void addInterceptors(InterceptorRegistry registry) {
     * registry.addInterceptor(new SessionIntercepter())
     * .addPathPatterns("/s/*")
     * .addPathPatterns("user/*")
     * .excludePathPatterns("/s/post/*");
     * }
     */
}
