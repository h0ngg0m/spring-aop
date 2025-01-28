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

### 포인트컷 execution 표현식
- `execution(접근제어자? 반환타입 선언타입?메서드이름(파라미터) 예외?)`
  - 메서드 실행 조인 포인트를 매칭한다.
  - ? 는 생략 가능을 뜻함.
  - * 같은 패턴 사용 가능.
