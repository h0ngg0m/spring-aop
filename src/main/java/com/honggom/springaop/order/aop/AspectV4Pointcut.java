package com.honggom.springaop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class AspectV4Pointcut {

    @Around("com.honggom.springaop.order.aop.Pointcuts.allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); // join point 시그니처
        return joinPoint.proceed();
    }

    @Around("com.honggom.springaop.order.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[transaction] start {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("[transaction] commit {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            log.info("[transaction] rollback {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("[transaction] end {}", joinPoint.getSignature());
        }
    }

}
