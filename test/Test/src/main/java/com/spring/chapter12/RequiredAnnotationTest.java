package com.spring.chapter12;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RequiredAnnotationTest {
	
	private static String configLocation ="classpath:RequiredAnnotation.xml";

	private static ApplicationContext ctx= new ClassPathXmlApplicationContext(configLocation);
	
	@Test
	public void testRequiredSetter(){
		TestBean testBean = ctx.getBean("testBean", TestBean.class);
		Assert.assertEquals("", testBean.getMessage());
	}
}
