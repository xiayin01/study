package com.xy.test.spring.boot.servlet;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * filter
 *
 * @author xy
 */
public class MySpringBootFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        request.getServletContext().log("MySpringBootFilter...doFilter..." + request.getRequestURI());
        filterChain.doFilter(request, response);
    }

}
