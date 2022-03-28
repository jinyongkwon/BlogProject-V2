package site.metacoding.blogv2.config.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv2.domain.user.User;

@RequiredArgsConstructor
public class MyFilter3 implements Filter {

    private final HttpSession session;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("MyFilter 3");
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            System.out.println("세션 인증안됨 접근불가페이지");
        } else {
            chain.doFilter(request, response);
        }

    }

}
