package com.poscodx.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Spring AOP(Aspect-Oriented Programming)
 * 메서드의 실행 시간을 측정하는 역할을 하는 Aspect 클래스
 * Aspect는 애플리케이션의 여러 부분에서 공통 관심사(로깅, 성능 측정 등)를 캡슐화하는 데 사용
 */
@Component
@Aspect
public class MeasureExecutionTimeAspect {
	@Around("execution(* *..*.repository.*.*(..)) || execution(* *..*.service.*.*(..)) || execution(* *..*.controller.*.*(..))")
	public Object adviceAround(ProceedingJoinPoint pjp) throws Throwable{
		// before
		StopWatch sw = new StopWatch();
		sw.start();
		
		Object result = pjp.proceed(); // 원본 메서드 실행
		
		//after
		sw.stop();
		long totalTime = sw.getTotalTimeMillis();
		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		String taskName = className + "." + methodName;
		
		System.out.println("[Execution Time][" + taskName + "] " + totalTime + "mills");
		
		return result;
	}
}
