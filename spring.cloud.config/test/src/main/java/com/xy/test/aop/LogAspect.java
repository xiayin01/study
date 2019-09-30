package com.xy.test.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.Arrays;

/**
 * 切面
 *
 * @author xy
 */
@Aspect
public class LogAspect {

    /**
     * 切入方法
     */
    @Pointcut("execution(* com.xy.test.business.MathCalculator.*(..))")
    public void pointCut() {

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

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        final Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
        StopWatch stopWatch = new StopWatch(getClass().getSimpleName());
        try {
            stopWatch.start(joinPoint.getSignature().getName());
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            logger.debug(stopWatch.prettyPrint());
        }
    }
}
