package com.xy.user.web.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class JndiConnectionManager {

    private Connection connection;

    public JndiConnectionManager() {
        try {
            Context context = new InitialContext();
            Object dataSourceRef = context.lookup("java:jdbc/UserPlatformDB");
            DataSource ds = (DataSource) dataSourceRef;
            this.connection = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
