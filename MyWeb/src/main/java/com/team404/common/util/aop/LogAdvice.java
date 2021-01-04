package com.team404.common.util.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


// bean으로 생성이 되어야함.
@Aspect    // AOP를 적용시킬 클래스 라는 의미를 가짐.
@Component     // bean으로 읽을수 있도록 하는데 외부파일이라는 의미를 가짐.
public class LogAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);
	
	/*
	@(Before, After, AfterThrowing, Around) ("execution(* 패키지주소.클래스명.메서드)")
	- Berfore : 타겟메서드 실행 전
	- After : 타겟메서드 실행 후
	- AfterThrowing : 타겟메서드 에러 발생 시
	- Around : 타겟 메서드와 결합 → ProceedingJoinPoint 매개변수 필수, Object반환타입 필수
		*proceed()(→타겟메서드의 실행) 메서드 기준으로 이전은 @Before, 이후는 @After라고 보면된다.
		*Before, After, AfterThrowing을 한번에 처리할수 있음
	 */
	
	//맨 앞에 있는 *는 접근제어자를 의미, 맨뒤에 *는 메서드를 의미함
	
	//@Before("execution(* com.team404.controller.*Controller.*(..))")
//	@Before("execution(* com.team404.*.service.*ServiceImpl.*(..))")
	public void beforeLog() {
		System.out.println("------ 메서드 실행전 ------");
	}
	
//	@After("execution(* com.team404.*.service.*ServiceImpl.*(..))")
	public void afterLog() {
		System.out.println("------ 메서드 실행후 ------");
	}
	
	// 에러가 발생했을때 동작 (throwing = "e") : 에러를 처리할 변수 
 	//@AfterThrowing(pointcut = "execution(* com.team404.*.service.*ServiceImpl.*(..))", throwing = "e")  // e라는 변수로 에러를 받아온다
	public void errorLog(Exception e) {
		System.out.println("에러로그 :" + e);
	}
	
	// 로그레벨 추가 (log4j.xml)
//	<logger name="com.team404.common.util.aop">
//		<level value="info" />
//	</logger>
	
	// before, after, afterThrowing 세가지를 한번에 처리할 수 있게 해주는 Around
	// 메서드 실행 권한을 가지고, 타겟 메서드랑 접목시켜서 사용
	// 규칙 - 반환은 Object, 매개변수로 메서드의 실행지점을 결정짓는 ProceedingJoinPoint를 선언합니다.
 	@Around("execution(* com.team404.*.service.*ServiceImpl.*(..))")
 	public Object aroundLog(ProceedingJoinPoint jp) {
 		
 		//System.out.println("메서드 실행전");
 		long start = System.currentTimeMillis();
 		
 		logger.info("실행클래스 : " + jp.getTarget());
 		logger.info("실행메서드 : " + jp.getSignature().toString());
 		logger.info("매개값 : " + Arrays.toString(jp.getArgs()));
 		
 		
 		Object result = null;
 		try {
			result = jp.proceed();
		} catch (Throwable e) {
			System.out.println("에러로그 :" + e);
			e.printStackTrace();
		}
 		
 		//System.out.println("메서드 실행후");
 		long end = System.currentTimeMillis();
 		
 		System.out.println("메서드 소요시간 : " +(end-start)*0.001 + "초");
 		return result;  // 프로시드의 결과를 반환해야 메서드의 정상 흐름으로 돌아간다.
 	}
	
}// class end
