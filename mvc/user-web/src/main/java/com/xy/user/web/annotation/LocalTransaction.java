package com.xy.user.web.annotation;

import java.lang.annotation.*;
import java.sql.Connection;

import static java.sql.Connection.TRANSACTION_READ_COMMITTED;

/**
 * 本地事务
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface LocalTransaction {

    int PROPAGATION_REQUIRED = 0;

    int PROPAGATION_REQUIRES_NEW = 3;

    int PROPAGATION_NESTED = 6;

    /**
     * 事务传播
     *
     * @return
     */
    int propagation() default PROPAGATION_REQUIRED;

    /**
     * 事务隔离级别
     *
     * @return
     * @see Connection#TRANSACTION_READ_COMMITTED
     */
    int isolation() default TRANSACTION_READ_COMMITTED;
}
