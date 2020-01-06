package com.xy.test.single;

/**
 * 测试类
 */
public class LazySingletonTest {

    public static void main(String[] args) {
        Thread t1 = new Thread(new ExecutorThread());
        Thread t2 = new Thread(new ExecutorThread());
        t1.start();
        t2.start();

        LazySimpleSingleton lazySimpleSingleton = LazySimpleSingleton.getInstance();
        System.out.println(lazySimpleSingleton);
    }
}
