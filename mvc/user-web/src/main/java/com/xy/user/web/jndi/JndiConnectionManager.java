package com.xy.user.web.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JndiConnectionManager {

    private Connection connection;

    public JndiConnectionManager() {
        try {
            Context ctx = new InitialContext();
            Context context = (Context) ctx.lookup("java:/comp/env");
            Object dataSourceRef = context.lookup("jdbc/user-platform");
            DataSource ds = (DataSource) dataSourceRef;
            this.connection = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void releaseConnection() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e.getCause());
            }
        }
    }
}
