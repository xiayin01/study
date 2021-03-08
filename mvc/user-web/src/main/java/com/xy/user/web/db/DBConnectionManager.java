package com.xy.user.web.db;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnectionManager {

    private final Logger logger = Logger.getLogger(DBConnectionManager.class.getName());

    @Resource(name = "jdbc/UserPlatform")
    private DataSource dataSource;

    @Resource(name = "bean/EntityManager")
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        logger.info("当前 EntityManager 实现类：" + entityManager.getClass().getName());
        return entityManager;
    }

    public Connection getConnection() {
        // 依赖查找
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        if (connection != null) {
            logger.log(Level.INFO, "获取 JNDI 数据库连接成功！");
        }
        return connection;
    }
}
