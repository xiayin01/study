package com.xy.test.spring.boot.servlet;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * servlet
 *
 * @author xy
 */
public class MySpringBootServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.getServletContext().log("mySpringBootServlet...doGet...");
        doSomething();
        Writer writer = resp.getWriter();
        writer.write("<html><body>Hello,World...mySpringBootServlet...</body></html>");
    }

    /**
     * 获取HttpServletRequest
     */
    private void doSomething() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        request.getServletContext().log("mySpringBootServlet...doGet...");
    }
}
