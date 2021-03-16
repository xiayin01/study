package com.xy.user.web.listener;

import com.xy.common.mvc.context.ComponentContext;
import com.xy.user.web.domain.Color;
import com.xy.user.web.jolokia.ColorImpl;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.NotCompliantMBeanException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.management.ManagementFactory;

/**
 * {@link ComponentContext} 初始化器
 * 类似ContextLoaderListener
 */
public class ComponentInitializerListener implements ServletContextListener {

    private ServletContext servletContext;
    private MBeanServer mBeanServer;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.servletContext = sce.getServletContext();
        ComponentContext context = new ComponentContext();
        context.init(servletContext);
        this.mBeanServer = ManagementFactory.getPlatformMBeanServer();
        sce.getServletContext().setAttribute("mBeanServer", this.mBeanServer);
        Color color = new Color("红色", 1);
        ColorImpl colorImpl = new ColorImpl(color);
        try {
            //http://localhost:8080/user_web/jolokia/read/com.xy.user.web.jolokia:type=Color/ColorName
            this.mBeanServer.registerMBean(colorImpl, colorImpl.getObjectName());
        } catch (InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /*ComponentContext context = (ComponentContext) servletContext.getAttribute(ComponentContext.CONTEXT_NAME);
        context.destroy();*/
        servletContext.log("容器销毁");
    }


}
