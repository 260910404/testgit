package com.spring.aop.proxyCreator;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

public class MemberAdvice implements MethodBeforeAdvice {

	public void before(Method method, Object[] args, Object target) throws Throwable{
		System.out.println(target.getClass().toString());
		if(target instanceof ShoppingImplB){
			System.out.println("你是会员");
		}
	}
	
}
