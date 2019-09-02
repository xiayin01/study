package com.kedi.common.util.sql;

/**
 * @author xy
 */
public interface SqlIterator<T> {

    /**
     * sql
     *
     * @param var1 参数1
     * @param var2 参数2
     * @return sql
     */
    SQL iterate(T var1, int var2);
}
