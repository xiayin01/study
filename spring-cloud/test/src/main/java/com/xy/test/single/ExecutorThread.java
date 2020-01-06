package com.xy.test.single;

/**
 * 线程测试懒加载
 */
public class ExecutorThread implements Runnable {

    @Override
    public void run() {
        LazyInnerClassSingleton lazyInnerClassSingleton = LazyInnerClassSingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + ":" + lazyInnerClassSingleton);
    }
}
