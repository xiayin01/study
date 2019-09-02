package com.kedi.common.util.sql;

import java.util.Map;

/**
 * @author xy
 */
public interface ValuesIterator<T> {

    /**
     * 值
     *
     * @param var1 参数1
     * @param var2 参数2
     * @return 集合
     */
    Map<String, String> values(T var1, int var2);
}
