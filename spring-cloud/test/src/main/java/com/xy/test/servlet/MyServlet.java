package com.xy.test.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
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
@WebServlet(name = "myServlet",
        urlPatterns = {"/my/servlet"},
        initParams = {
                @WebInitParam(name = "servlet", value = "myServlet")
        })
public class MyServlet extends HttpServlet {

    private String value;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        value = servletConfig.getInitParameter("servlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().log("myServlet...doGet...");
        Writer writer = resp.getWriter();
        writer.write("<html><body>Hello,World..." + value + "</body></html>");
    }
}
