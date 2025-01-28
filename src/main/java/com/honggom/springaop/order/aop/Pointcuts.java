package com.honggom.springaop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* com.honggom.springaop.order..*(..))")
    public void allOrder() {
    } // 포인트컷 시그니쳐

    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {
    }

    @Pointcut("allOrder() && allService()")
    public void orderAndService() {
    }

}
