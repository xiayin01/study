package com.xy.test.spring.boot.servlet;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * listener
 *
 * @author xy
 */
public class MySpringBootServletRequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        sre.getServletContext().log("mySpringBootServletRequestListener...init...");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        sre.getServletContext().log("mySpringBootServletRequestListener...destroy...");
    }
}
