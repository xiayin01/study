package com.xy.common.util.sql;

/**
 * sql断言器
 *
 * @author xy
 */
public interface SqlAssert {
    /**
     * 检查是否满足前提条件，false则直接跳过当前sql拼接
     *
     * @return 是否满足
     */
    boolean check();

    /**
     * 返回当前要拼接的sql字符串
     *
     * @return sql字符串
     */
    String create();

    /**
     * 返回当前要拼接的sql字符串数组，用于set/values这种特殊sql场合
     *
     * @return sql字符串数组
     */
    String[] createArr();
}
