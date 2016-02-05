package com.spring.aop.proxyByName;

import java.io.File;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

import com.spring.aop.proxyCreator.Shopping;

/**
 * ProxyFactoryBean
 * @author shaojiabing
 *
 */
public class TestProxyFactoryBean {

	public static void main(String[] args) {
		String filePath = System.getProperty("user.dir")+File.separator+"src/main/java/com/spring/aop/proxyByName"+File.separator+"proxyByFactoryBean.xml";
		//注册aop
		ApplicationContext ctx = new FileSystemXmlApplicationContext(filePath);
		
		Shopping shoppingB = null;
		
		shoppingB = (Shopping)ctx.getBean("shoppingProxy");

		shoppingB.sellAnything("anything");
		shoppingB.sellSomething("something");
	}
}
