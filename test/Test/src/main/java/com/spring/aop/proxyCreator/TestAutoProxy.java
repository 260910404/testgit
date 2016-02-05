package com.spring.aop.proxyCreator;

import java.io.File;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

/**
 * 测试 DefaultAdvisorAutoProxyCreator
 * @author shaojiabing
 *
 */
public class TestAutoProxy {
	
	public static void main(String[] args) {
		String filePath = System.getProperty("user.dir")+File.separator+"src/main/java/com/spring/aop/proxyCreator"+File.separator+"autoProxy.xml";
		
		//只装配beans，不注册aop
//		BeanFactory factory = new XmlBeanFactory(new FileSystemResource(filePath));
		
		//注册aop
		ApplicationContext ctx = new FileSystemXmlApplicationContext(filePath);

		Shopping shoppingA = null;
		Shopping shoppingB = null;
		
		shoppingA = (Shopping)ctx.getBean("buyBean");
		shoppingB = (Shopping)ctx.getBean("sellBean");

		shoppingA.buySomething("something");
		shoppingA.buyAnything("anything");
		shoppingB.sellAnything("anything");
		shoppingB.sellSomething("something");
		
		//不注册aop
//		shoppingA = (Shopping)factory.getBean("buyBean");
//		shoppingB = (Shopping)factory.getBean("sellBean");
//		shoppingA.buySomething("something");
//		shoppingA.buyAnything("anything");
//		shoppingB.sellAnything("anything");
//		shoppingB.sellSomething("something");
	
	
	}

}
