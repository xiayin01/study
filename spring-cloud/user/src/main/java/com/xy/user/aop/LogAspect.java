package com.xy.user.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

/**
 * 切面
 *
 * @author xy
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 切入方法
     */
    @Pointcut("execution(* com.xy.user.service.*.*(..))")
    private void pointCut() {

    }

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        //获取参数
        Object[] args = joinPoint.getArgs();
        System.out.println("" + methodName + "前置通知。。。@Before,请求参数:" + Arrays.toString(args));
    }

    @After("pointCut()")
    public void logEnd() {
        System.out.println("后置通知。。。@After");
    }

    /**
     * 正常返回结果
     *
     * @param result 结果值
     */
    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(Object result) {
        System.out.println("正常结束通知。。。@AfterReturning,返回结果:" + result);
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logThrowing(JoinPoint joinPoint, Exception exception) {
        System.out.println("异常通知。。。@AfterThrowing,异常:" + exception);
    }

    @Around("com.xy.user.aop.LogAspect.pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        StopWatch stopWatch = new StopWatch(getClass().getSimpleName());
        try {
            stopWatch.start(joinPoint.getSignature().getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stopWatch.stop();
        }
        long end = System.currentTimeMillis();
        log.info("运行时间：{}", (end - start));
        return joinPoint.proceed();
    }
}
