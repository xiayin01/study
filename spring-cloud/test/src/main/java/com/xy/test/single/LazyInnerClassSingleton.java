package com.xy.test.single;

/**
 * 懒加载
 */
public class LazyInnerClassSingleton {

    private LazyInnerClassSingleton() {
        System.out.println("----LazyInnerClassSingleton----无参构造函数");
        if (null == LazyHolder.LAZY_INNER_CLASS_SINGLETON) {
            throw new RuntimeException("不允许创建多个实例");
        }
    }

    public static LazyInnerClassSingleton getInstance() {
        return LazyHolder.LAZY_INNER_CLASS_SINGLETON;
    }

    private static class LazyHolder {
        private static final LazyInnerClassSingleton LAZY_INNER_CLASS_SINGLETON = new LazyInnerClassSingleton();
    }
}
