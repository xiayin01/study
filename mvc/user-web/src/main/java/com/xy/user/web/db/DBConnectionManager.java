package com.xy.user.web.db;

import com.xy.user.web.context.ComponentContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnectionManager {

    private final Logger logger = Logger.getLogger(DBConnectionManager.class.getName());

    public Connection getConnection() {
        ComponentContext context = ComponentContext.getInstance();
        Connection connection = null;
        DataSource dataSource = context.getComponent("jdbc/UserPlatform");
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        if (connection != null) {
            logger.log(Level.INFO, "jndi 获取连接成功！");
            System.out.println("jndi 获取连接成功！");
        }
        return connection;
    }
}
