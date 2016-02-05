package com.spring.aop.proxyByName;

import java.io.File;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

import com.spring.aop.proxyCreator.Shopping;

/**
 * BeanNameAutoProxyCreator
 * @author shaojiabing
 *
 */
public class TestProxyByName {

	public static void main(String[] args) {
		String filePath = System.getProperty("user.dir")+File.separator+"src/main/java/com/spring/aop/proxyByName"+File.separator+"proxyByName.xml";
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
	}
}
