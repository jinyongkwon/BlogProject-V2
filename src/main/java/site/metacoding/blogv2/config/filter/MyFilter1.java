package site.metacoding.blogv2.config.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter1 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("MyFilter 1");
        HttpServletRequest req = (HttpServletRequest) request; // 다운캐스팅을 해야 사용이가능하다.
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getRequestURI();

        if (path.contains("fuck")) { // 해당 문자로 들어온 URI를 걸러줌
            resp.setContentType("text/plain; charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.println("욕하지마!!!");
            out.flush();
        } else {
            chain.doFilter(request, response); // 다음필터로 이동 만약 다음필터가 없으면 DS로 이동
        }
    }

}
