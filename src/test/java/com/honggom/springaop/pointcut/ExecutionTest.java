package com.honggom.springaop.pointcut;

import com.honggom.springaop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ExecutionTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method method;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        method = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void printMethod() {
        log.info("method: {}", method);
    }

    @Test
    void exactMatch() {
        pointcut.setExpression("execution(public String com.honggom.springaop.member.MemberServiceImpl.hello(String))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void allMatch() {
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatch() {
        pointcut.setExpression("execution(* hello(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchStar1() {
        pointcut.setExpression("execution(* hel*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchStar2() {
        pointcut.setExpression("execution(* *el*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchFail() {
        pointcut.setExpression("execution(* foo(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isFalse();
    }

    @Test
    void packageExactMatch() {
        pointcut.setExpression("execution(* com.honggom.springaop.member.MemberServiceImpl.hello(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageMatch() {
        pointcut.setExpression("execution(* com.honggom.springaop.member.*.*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageMatchFail() {
        pointcut.setExpression("execution(* com.honggom.springaop.*.*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isFalse();
    }

    @Test
    void packageMatchSubPackage1() {
        pointcut.setExpression("execution(* com.honggom.springaop..*.*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageMatchSubPackage2() {
        pointcut.setExpression("execution(* com.honggom..*.*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeExactMatch() {
        pointcut.setExpression("execution(* com.honggom.springaop.member.MemberServiceImpl.*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchSuperType() {
        pointcut.setExpression("execution(* com.honggom.springaop.member.MemberService.*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchInternal() throws NoSuchMethodException {
        pointcut.setExpression("execution(* com.honggom.springaop.member.MemberService.*(..))");
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    void argsMatch() {
        pointcut.setExpression("execution(* *(String))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void argsMatchNoArgs() {
        pointcut.setExpression("execution(* *())");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isFalse();
    }


    // 하나의 파라미터, 모든 타입 허용
    @Test
    void argsMatchStar() {
        pointcut.setExpression("execution(* *(*))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    // 파라미터 숫자 무관, 모든 타입 허용
    @Test
    void argsMatchAll() {
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    // String 타입으로 시작, 숫자와 타입 무관
    // ex: (String), (String, int), (String, int, int)
    @Test
    void argsMatchComplex() {
        pointcut.setExpression("execution(* *(String, ..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }
}
