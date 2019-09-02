package com.kedi.common.util.sql;

/**
 * @author xy
 */
public interface SqlProcessor {
    /**
     * sql处理方法,把当前sql对象调用权限转移给调用者，可以直接操作当前sql对象
     *
     * @param var1 参数1
     */
    void process(SQL var1);
}
