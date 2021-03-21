package com.xy.user.web.listener;

import com.xy.common.mvc.context.ComponentContext;
import com.xy.user.web.db.DBConnectionManager;
import com.xy.user.web.domain.User;
import com.xy.user.web.microprofile.config.source.DefaultResourceConfigSource;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * 测试类
 */
@Deprecated
public class TestingListener implements ServletContextListener {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    public static final String DROP_USERS_TABLE_DDL_SQL = "DROP TABLE users";

    public static final String CREATE_USERS_TABLE_DDL_SQL = "CREATE TABLE users(" +
            "id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
            "name VARCHAR(16) NOT NULL, " +
            "password VARCHAR(64) NOT NULL, " +
            "email VARCHAR(64) NOT NULL, " +
            "phoneNumber VARCHAR(64) NOT NULL" +
            ")";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ComponentContext context = ComponentContext.getInstance();
        DBConnectionManager dbConnectionManager = context.getComponent("bean/DBConnectionManager");
        dbConnectionManager.getConnection();
        try {
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        testUser(dbConnectionManager.getEntityManager());
        logger.info("所有的 JNDI 组件名称：[");
        context.getComponentNames().forEach(logger::info);
        logger.info("]");
        logger.info("---servletContext----" + sce.getServletContext().getInitParameter("application.name"));
        MBeanServer mBeanServer = (MBeanServer) sce.getServletContext().getAttribute("mBeanServer");
        try {
            MBeanInfo mBeanInfo = mBeanServer.getMBeanInfo(new ObjectName("com.xy.user.web.jolokia:type=Color"));
            logger.info("--mBeanInfo---" + mBeanInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ConfigProviderResolver configProviderResolver = ConfigProviderResolver.instance();
        ConfigBuilder configBuilder = configProviderResolver.getBuilder();
        configBuilder.forClassLoader(sce.getServletContext().getClassLoader());
        configBuilder.addDefaultSources();
        configBuilder.addDefaultSources();
        configBuilder.withSources(new DefaultResourceConfigSource());
        Config config = configBuilder.build();
        config.getConfigSources().forEach(configSource -> {
            System.out.println("----microProfile----" + configSource.getValue("application.name"));
        });
    }

    private void testUser(EntityManager entityManager) {
        User user = new User();
        user.setName("test");
        user.setPassword("123456");
        user.setEmail("123@qq.com");
        user.setPhoneNumber("18611111111");
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        System.out.println(entityManager.find(User.class, user.getId()));
    }

    private void createTable() throws SQLException {
        ComponentContext context = ComponentContext.getInstance();
        DBConnectionManager dbConnectionManager = context.getComponent("bean/DBConnectionManager");
        Connection connection = dbConnectionManager.getConnection();

        Statement statement = connection.createStatement();
        // 删除 users 表
        System.out.println(statement.execute(DROP_USERS_TABLE_DDL_SQL)); // false
        // 创建 users 表
        System.out.println(statement.execute(CREATE_USERS_TABLE_DDL_SQL)); // false
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
