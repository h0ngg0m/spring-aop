//package com.honggom.springaop.internalcall;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class CallServiceV2 {
//
//    private final ObjectProvider<CallServiceV2> provider;
//
//    public CallServiceV2(ObjectProvider<CallServiceV2> provider) {
//        this.provider = provider;
//    }
//
//    public void external() {
//        log.info("external()");
//        CallServiceV2 callServiceV2 = provider.getObject();
//        callServiceV2.internal();
//    }
//
//    public void internal() {
//        log.info("internal()");
//    }
//}
