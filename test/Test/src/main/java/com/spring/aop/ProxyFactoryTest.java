package com.spring.aop;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodProxy;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class ProxyFactoryTest {

	@Test
	public void test(){
		//代理对象要实现的接口
		Class[] interfaces = new Class[]{HelloService.class,InvokeService.class};
		//利用springapi，创建代理工厂
		ProxyFactory proxyFactory = new ProxyFactory(interfaces);
		//设置目标对象
		proxyFactory.setTarget(new HelloServiceImpl());
		
		proxyFactory.setOpaque(true);
		
		proxyFactory.addAdvice(new MethodBeforeAdvice() {
			
			public void before(Method method, Object[] args, Object target)
					throws Throwable {
				System.out.println("--- 方法11111之前拦截 ---");
			}
		});
		
		proxyFactory.addAdvice(new MethodBeforeAdvice() {
			
			public void before(Method method, Object[] args, Object target)
					throws Throwable {
				System.out.println("--- 方法22222之前拦截 ---");
			}
		});
		
		proxyFactory.addAdvice(new AfterReturningAdvice() {
			public void afterReturning(Object returnValue, Method method,
					Object[] args, Object target) throws Throwable {
				System.out.println("--- 方法返回之后拦截1111 ---");
			}
		});
		
		proxyFactory.addAdvice(new AfterReturningAdvice() {
			public void afterReturning(Object returnValue, Method method,
					Object[] args, Object target) throws Throwable {
				System.out.println("--- 方法返回之后拦截2222 ---");
			}
		});
		
		proxyFactory.addAdvice(new MethodInterceptor() {
			public Object invoke(MethodInvocation invocation) throws Throwable {
				System.out.println("1111111环绕通知"); 
                Object[] params=invocation.getArguments(); 
                Method method=invocation.getMethod(); 
                Object target=invocation.getThis(); 
                Object bytes=method.invoke(target, params); 
                String result= (String)bytes; 
                System.out.println("1111111111环绕通知生成的结果--"+new String(result)); 
                return "北京生活圈"; 
			}
		});
		
		Object proxy = proxyFactory.getProxy(proxyFactory.getClass().getClassLoader());
		Class[] inters = proxy.getClass().getInterfaces();
		for(Class cls:inters){
			System.out.println(cls.getSimpleName());
		}
		System.out.println("-------------");
		
		HelloService helloService = (HelloService)proxy;
		System.out.println(helloService.sayHelloWorld("你好，spring aop"));
	}
}
