package com.spring.aop.proxyCreator;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

public class WelcomeAdvice implements MethodBeforeAdvice {

	public void before(Method method, Object[] args, Object target) throws Throwable{
		System.out.println("Hello welcome to bye ");
	}
	
}
