package com.xy.test.hystrix;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 计数
 *
 * @author xy
 */
@Service
public class CountServiceImpl implements CountService {

    private AtomicInteger orderIdCounter = new AtomicInteger(0);

    @Override
    public int countNum() {
        int c = orderIdCounter.getAndIncrement();
        int count = 10;
        if (c < count) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
            }
        }
        return c;
    }
}
