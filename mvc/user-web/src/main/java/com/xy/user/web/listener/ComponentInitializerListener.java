package com.xy.user.web.listener;

import com.xy.common.mvc.context.ComponentContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * {@link ComponentContext} 初始化器
 * 类似ContextLoaderListener
 */
public class ComponentInitializerListener implements ServletContextListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.servletContext = sce.getServletContext();
        ComponentContext context = new ComponentContext();
        context.init(servletContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /*ComponentContext context = (ComponentContext) servletContext.getAttribute(ComponentContext.CONTEXT_NAME);
        context.destroy();*/
        servletContext.log("容器销毁");
    }


}
