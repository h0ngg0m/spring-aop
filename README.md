### 어드바이스 종류
- @Around: 메서드 호출 전후에 수행, 가장 강력한 어드바이스, 조인 포인트 실행 여부 선택, 반환 값 변환, 예외 변환 등이 가능
- @Before: 조인 포인트 실행 이전에 실행
- @After Returning: 조인 포인트가 정상 완료 후 실행
- @After Throwing: 메서드가 예외를 던지는 경우 실행
- @After: 조인 포인트가 정상 또는 예외에 관계없이 실행 (finally)

```java
@Around(...)
public Object doTransaction(ProceedingJoinPoint pjp) throws Throwable {
    try {
    // @Before
    Object result = pjp.proceed();
    // @AfterReturning
    // return result;
    } catch (Exception e) {
    // @AfterThrowing
    // throw e;
    } finally {
    // @After    
    }
}
```