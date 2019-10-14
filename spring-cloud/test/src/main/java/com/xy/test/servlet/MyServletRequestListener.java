package com.xy.test.servlet;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * listener
 *
 * @author xy
 */
@WebListener
public class MyServletRequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        sre.getServletContext().log("myServletRequestListener...init...");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        sre.getServletContext().log("myServletRequestListener...destroy...");
    }
}
