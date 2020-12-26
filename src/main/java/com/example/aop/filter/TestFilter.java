package com.example.aop.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Order(0)
@Component
public class TestFilter extends OncePerRequestFilter { // implements Filter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(request, response);
    }

    /*@Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("필터 잘 동작합니다!");

        HttpServletRequest https = new ProtocolFixRequest((HttpServletRequest) request, "https");
        log.info("필터 잘 동작합니다!222 {}", https);
        chain.doFilter(https, response);
    }

    @Override
    public void destroy() {

    }*/
}
