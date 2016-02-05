package com.spring.aop.proxyByName;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.spring.aop.proxyCreator.Shopping;

/**
 * AspectJAwareAdvisorAutoProxyCreator
 * @author shaojiabing
 *
 */
public class TestProxyAspectJ {

	public static void main(String[] args) {
		String filePath = System.getProperty("user.dir")+File.separator+"src/main/java/com/spring/aop/proxyByName"+File.separator+"proxyByAspectJ.xml";
		//注册aop
		ApplicationContext ctx = new FileSystemXmlApplicationContext(filePath);
		
		Shopping shoppingA = null;
		Shopping shoppingB = null;
		
		shoppingA = (Shopping)ctx.getBean("buyBean");
//		shoppingB = (Shopping)ctx.getBean("sellBean");

		shoppingA.buySomething("something");
//		shoppingA.buyAnything("anything");
//		shoppingB.sellAnything("anything");
//		shoppingB.sellSomething("something");
	}
}
