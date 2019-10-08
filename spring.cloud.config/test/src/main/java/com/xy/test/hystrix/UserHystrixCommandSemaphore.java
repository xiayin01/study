package com.xy.test.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.xy.test.entity.User;

/**
 * hystrix线程隔离-信号量
 *
 * @author xy
 */
public class UserHystrixCommandSemaphore extends HystrixCommand<User> {

    private User user;

    public UserHystrixCommandSemaphore(User user) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("user_hystrix_command_semaphore"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("getUser"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withCircuitBreakerEnabled(true)
                        //至少有10个请求，熔断才会进行错误率的计算
                        .withCircuitBreakerRequestVolumeThreshold(10)
                        //熔断器中断请求5秒后会进入半打开状态,放部分流量过去重试
                        .withExecutionTimeoutInMilliseconds(5000)
                        //错误率达到50开启熔断保护
                        .withCircuitBreakerErrorThresholdPercentage(50)
                        //信号量
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        //最大并发请求量
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(10)));
        this.user = user;
    }

    @Override
    protected User run() throws Exception {
        return user;
    }

    @Override
    protected User getFallback() {
        System.out.println("进入回滚方法。。。。");
        User user = new User();
        user.setName("回滚");
        return user;
    }
}