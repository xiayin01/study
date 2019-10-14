package com.xy.auth.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * 数据库层配置类
 *
 * @author xy
 */
@Configuration
@MapperScan(basePackages = {"spring.cloud.config.mapper.**"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class DataBaseConfiguration {
    @Value("${hikaricp.dataSource.jdbc.driverClassName}")
    private String driverClassName;
    @Value("${hikaricp.dataSource.url}")
    private String jdbcUrl;
    @Value("${hikaricp.dataSource.username}")
    private String username;
    @Value("${hikaricp.dataSource.password}")
    private String password;
    @Value("${hikaricp.dataSource.connectionTestQuery}")
    private String connectionTestQuery;
    @Value("${hikaricp.dataSource.connectionTimeout}")
    private long connectionTimeout;
    @Value("${hikaricp.dataSource.idleTimeout}")
    private long idleTimeout;
    @Value("${hikaricp.dataSource.maxLifetime}")
    private long maxLifetime;
    @Value("${hikaricp.dataSource.maximumPoolSize}")
    private int maximumPoolSize;

    /**
     * 注入一个hikaricp dataSource
     */
    @Bean(value = "dataSource", destroyMethod = "close")
    public HikariDataSource hikariDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setConnectionTestQuery(connectionTestQuery);
        hikariConfig.setConnectionTimeout(connectionTimeout);
        hikariConfig.setIdleTimeout(idleTimeout);
        hikariConfig.setMaxLifetime(maxLifetime);
        hikariConfig.setMaximumPoolSize(maximumPoolSize);
        return new HikariDataSource(hikariConfig);
    }

    /**
     * 注入一个sqlSessionFactory
     */
    @Bean(value = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(hikariDataSource());
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 主动注入一个transactionManger,适用多数据库事务管理器环境
     */
    @Bean(value = "transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(HikariDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
