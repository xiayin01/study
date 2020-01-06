package com.xy.test.single;

/**
 * 多重验证，实现懒加载（线程安全）
 */
public class LazySimpleSingleton {

    private volatile static LazySimpleSingleton lazy = null;

    private LazySimpleSingleton() {
    }

    public static LazySimpleSingleton getInstance() {
        if (null == lazy) {
            synchronized (LazySimpleSingleton.class) {
                if (null == lazy) {
                    lazy = new LazySimpleSingleton();
                }
            }
        }
        return lazy;
    }

}
