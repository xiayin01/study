package com.xy.test.hystrix;

import com.netflix.hystrix.*;

/**
 * hystrix线程隔离-线程池
 *
 * @author xy
 */
public class UserHystrixCommand extends HystrixCommand<Integer> {

    private CountService countService;

    public UserHystrixCommand(CountService countService) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("user_hystrix_command"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("getUser"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withCircuitBreakerEnabled(true)
                        //至少有10个请求，熔断才会进行错误率的计算
                        .withCircuitBreakerRequestVolumeThreshold(10)
                        //熔断器中断请求500ms后会进入半打开状态,放部分流量过去重试
                        .withExecutionTimeoutInMilliseconds(500)
                        //错误率达到50开启熔断保护
                        .withCircuitBreakerErrorThresholdPercentage(50))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        //20个线程
                        .withCoreSize(10)));
        this.countService = countService;
    }

    @Override
    protected Integer run() throws Exception {
        return countService.countNum();
    }

    @Override
    protected Integer getFallback() {
        System.out.println("进入回滚方法。。。。");
        return -1;
    }

}
