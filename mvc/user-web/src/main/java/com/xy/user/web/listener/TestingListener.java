package com.xy.user.web.listener;

import com.xy.user.web.context.ComponentContext;
import com.xy.user.web.db.DBConnectionManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 测试类
 */
@Deprecated
public class TestingListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ComponentContext context = ComponentContext.getInstance();
        DBConnectionManager connectionManager = context.getComponent("bean/DBConnectionManager");
        connectionManager.getConnection();

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ComponentContext context = ComponentContext.getInstance();
        context.destroy();
    }

}
